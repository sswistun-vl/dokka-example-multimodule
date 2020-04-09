import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.gradle.DokkaMultimoduleTask

plugins {
    id("java")
    id("org.jetbrains.kotlin.multiplatform") version "1.3.50"
//    id("org.jetbrains.dokka") version "0.10.1-SNAPSHOT"
    id("org.jetbrains.dokka") version "0.11.0-SNAPSHOT"
//    id("org.jetbrains.kotlinx.knit") version "0.1.2"
}

group = "org.jetbrains.dokka"
version = "1.0-SNAPSHOT"


repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
}

dependencies {
    testCompile(group ="junit", name = "junit", version = "4.12")
//    dokkaPlugins("org.jetbrains.dokka", "kotlin-as-java-plugin", "0.11.0-SNAPSHOT")
    dokkaPlugins("org.jetbrains.dokka", "dokka-base", "0.11.0-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
//    compileClasspath("org.jetbrains.kotlinx:kotlinx-knit:0.1.2")
}

kotlin {
    jvm()
    js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val jvmAndJsSecondCommonMain by creating {
            dependsOn(commonMain)
        }

        val jvmMain by getting {
            dependsOn(jvmAndJsSecondCommonMain)
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }

        val jsMain by getting {
            dependsOn(jvmAndJsSecondCommonMain)
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
    }
}

tasks {
    val dokkaOutputDir = "dokka"

    val clean = getByName("clean", Delete::class){
        delete(rootProject.buildDir)
        delete(dokkaOutputDir)
    }

//    val dokka by getting(DokkaTask::class) {
//        dependsOn(clean)
//
//        outputDirectory = dokkaOutputDir
////        outputFormat = "markdown"
//        outputFormat = "html"
////        unresolvedTypePolicy = "skip"
//
//        multiplatform {
//            val js by creating {}
//            val jvm by creating {}
//        }
//    }

    val dokkaMultimodule by getting(DokkaMultimoduleTask::class) {
        documentationFile = "README.md"
    }
}