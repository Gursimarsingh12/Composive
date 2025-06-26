import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.vanniktech.maven.publish") version "0.30.0"
    id("org.jetbrains.dokka") version "1.9.20"
}

val hostOs = System.getProperty("os.name")
val isMacOs = hostOs == "Mac OS X"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        publishLibraryVariants("release", "debug")
    }

    if (isMacOs) {
        val iosTargets = listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        )
        iosTargets.forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposiveResponsiveAdaptive"
                isStatic = true
            }
        }
    }

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

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
            api(libs.cupertino.adaptive)
            api(libs.cupertino)
            api(libs.material3.adaptive)
            api(libs.material3.adaptive.layout)
            implementation(libs.material3.window.size.class1)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.material)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
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
    namespace = "io.github.gursimarsingh12.composive"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    
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
    
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputDirectory.set(layout.buildDirectory.dir("dokka"))
}

publishing {
    publications {
        withType<MavenPublication> {
            artifactId = when (name) {
                "kotlinMultiplatform" -> "composive-responsive-adaptive"
                "androidRelease", "androidDebug", "android" -> "composive-responsive-adaptive-android"
                else -> "composive-responsive-adaptive-$name"
            }
            version = "1.0.0"
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.gursimarsingh12",
        artifactId = "composive-responsive-adaptive",
        version = "1.0.0"
    )
    pom {
        name.set("Composive Responsive Adaptive")
        description.set("Composive is a powerful Kotlin Multiplatform UI library for building responsive and adaptive user interfaces across Android, iOS, Desktop, and Web. Effortlessly create cross-platform apps with automatic theme adaptation, responsive layouts, and platform-aware components using Jetpack Compose Multiplatform.")
        inceptionYear.set("2025")
        url.set("https://github.com/Gursimarsingh12/composive")
        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            }
        }
        developers {
            developer {
                id.set("gursimar")
                name.set("Gursimar Singh")
                email.set("anonymouslike083@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/Gursimarsingh12/composive")
            connection.set("scm:git:git://github.com/Gursimarsingh12/composive.git")
            developerConnection.set("scm:git:ssh://github.com/Gursimarsingh12/composive.git")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}