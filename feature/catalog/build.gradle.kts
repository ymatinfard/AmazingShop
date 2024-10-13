plugins {
    alias(libs.plugins.amazingshop.android.feature)
}

android {
    namespace = "com.matin.amazingshop.feature.catalog"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.data)
}
