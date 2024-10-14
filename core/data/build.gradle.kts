plugins {
    alias(libs.plugins.amazingshop.android.library)
    alias(libs.plugins.amazingshop.android.hilt)
}

android {
    namespace = "com.matin.amazingshop.core.data"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(libs.kotlinx.coroutines.test)
}