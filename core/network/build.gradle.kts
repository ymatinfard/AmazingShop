plugins {
    alias(libs.plugins.amazingshop.android.library)
    alias(libs.plugins.amazingshop.android.hilt)
}

android {
    namespace = "com.matin.amazingshop.core.network"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.serialization.json)
}