plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android.plugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.compose_praticle_demo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.compose_praticle_demo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    ///hilt
    implementation(libs.hilt.android)
    implementation(libs.play.services.location)
    implementation(libs.androidx.appcompat.resources)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.credentials)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)


    ///retrofit
    // Retrofit, OkHttp, Jackson interceptor
    implementation(platform(libs.okhttp.bom))
    implementation(libs.converter.jackson)
    implementation(libs.logging.interceptor)
    implementation(libs.gson.converter)

    ///Compose Navigation
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)


    implementation(libs.accompanist.placeholder.material)
    // Add this in your build.gradle
    implementation (libs.accompanist.systemuicontroller);

}