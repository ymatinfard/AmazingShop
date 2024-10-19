plugins {
    alias(libs.plugins.amazingshop.android.library)
    alias(libs.plugins.amazingshop.android.library.compose)
}

android {
    namespace ="com.matin.amazingshop.core.designsystem"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.core.ktx)
    api(libs.coil3.coil.compose)
    api(libs.coil.network.okhttp)
}