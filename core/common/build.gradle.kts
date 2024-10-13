plugins {
    alias(libs.plugins.amazingshop.android.library)
    alias(libs.plugins.amazingshop.android.hilt)
}

android {
    namespace = "com.matin.amazingshop.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
