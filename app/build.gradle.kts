plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nurullahsevinckan.movieapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nurullahsevinckan.movieapp"
        minSdk = 27
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
    kotlinOptions {
        jvmTarget = "1.8"
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


    // Compose dependencies
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.google.accompanist.flowlayout)


    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)


    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.lifecycle.runtime.ktx)


    //Dagger - Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    kapt (libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.hilt.work)
    implementation (libs.work.runtime.ktx)


    // Retrofit
    implementation (libs.retrofit2.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)


    // Coil
    implementation (libs.coil.kt.coil.compose)


    //RoomDB
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)


}

kapt {
    correctErrorTypes = true
}
