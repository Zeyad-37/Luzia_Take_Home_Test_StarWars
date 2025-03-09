plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.luzia.test_base"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
    implementation(libs.junit.jupiter)
    implementation(libs.junit.jupiter.api)
    implementation(libs.junit.jupiter.engine)
    implementation(libs.junit.jupiter.params)
    implementation(libs.junit.vintage.engine)

    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(libs.mockito.core)
    api(libs.mockito.kotlin)
    api(libs.turbine)
    api(libs.kotlinx.coroutines.test)
    api(libs.junit)
    api(libs.junit.jupiter)
    api(libs.junit.jupiter.api)
    api(libs.junit.jupiter.engine)
    api(libs.junit.jupiter.params)
    api(libs.junit.vintage.engine)
    api(libs.androidx.room.testing)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.runner)
    api(libs.androidx.espresso.core)
    api(libs.androidx.junit)
}