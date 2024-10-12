plugins {
    alias(libs.plugins.amazingshop.android.library)
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