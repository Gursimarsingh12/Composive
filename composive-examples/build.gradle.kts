import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val hostOs = System.getProperty("os.name")
    val isMacOs = hostOs == "Mac OS X"

    if (isMacOs) {
        val iosTargets = listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        )
        iosTargets.forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposiveExamples"
                isStatic = true
            }
        }
    }

    jvm("desktop")

    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val androidMain by getting
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)
            implementation(libs.cupertino.adaptive)
            implementation(libs.cupertino)
            implementation(libs.cupertino.icons.extended)

            implementation(projects.composiveResponsiveAdaptive)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        if (isMacOs) {
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting

            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

android {
    namespace = "com.gursimar.composive.examples"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    
    lint {
        disable.addAll(
            listOf(
                "NullSafeMutableLiveData",
                "ObsoleteLintCustomCheck", 
                "LintError"
            )
        )
        abortOnError = false
        checkReleaseBuilds = false
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}