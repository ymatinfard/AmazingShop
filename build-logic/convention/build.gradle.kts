import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.matin.amazingshop.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("jvmLibrary") {
            id = "amazingshop.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidLibrary") {
            id = "amazingshop.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "amazingshop.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidApplication") {
            id = "amazingshop.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "amazingshop.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}