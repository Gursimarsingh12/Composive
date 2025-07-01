plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeHotReload) apply false
}

tasks.register("printVersion") {
    doLast {
        // Read version from composive-responsive-adaptive/build.gradle.kts
        val libraryBuildFile = File(projectDir, "composive-responsive-adaptive/build.gradle.kts")
        val versionRegex = """version\s*=\s*["']([^"']+)["']""".toRegex()
        val version = libraryBuildFile.readText().let { content ->
            versionRegex.find(content)?.groupValues?.get(1) ?: "1.0.2"
        }
        println(version)
    }
}