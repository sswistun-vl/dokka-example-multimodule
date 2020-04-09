import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaMultimoduleTask

plugins {
    id("java")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.dokka")
}

repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
}

//dependencies {
//    implementation(kotlin("stdlib-jdk8"))
//    testCompile("junit", "junit", "4.12")
//}

kotlin {
    jvm()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonMain.kotlin.srcDirs("main")
    }
}

tasks {
    val dokkaOutputDir = "dokka"
//
    val clean = getByName("clean", Delete::class) {
        delete(rootProject.buildDir)
        delete(dokkaOutputDir)
    }

    val dokka by getting(DokkaTask::class) {
        dependsOn(clean)

        outputDirectory = dokkaOutputDir
//        outputFormat = "markdown"
        outputFormat = "html"
//        unresolvedTypePolicy = "skip"

//        multiplatform {
//            val js by creating {}
//            val jvm by creating {}
//        }
    }

//    val dokkaMultimodule by getting(DokkaMultimoduleTask::class) {
//
//    }
}