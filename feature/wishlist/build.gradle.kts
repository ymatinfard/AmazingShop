plugins {
    alias(libs.plugins.amazingshop.android.feature)
    alias(libs.plugins.amazingshop.android.library.compose)
}

android {
    namespace = "com.matin.amazingshop.feature.wishlist"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)
}