plugins {
    alias(libs.plugins.amazingshop.android.library)
    alias(libs.plugins.amazingshop.android.hilt)
    alias(libs.plugins.amazingshop.android.room)
}

android {
    namespace = "com.matin.amazingshop.core.database"
}

dependencies {

    implementation(projects.core.model)
    implementation(projects.core.common)
}
