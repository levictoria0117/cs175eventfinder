import com.android.build.gradle.internal.utils.getDesugarLibDependencyGraph

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "edu.sjsu.android.localeventfinder"
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.sjsu.android.localeventfinder"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.cronet.embedded)
    implementation(libs.room.common)
    implementation(libs.room.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)
    annotationProcessor(libs.room.compiler)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
}

