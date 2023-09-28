
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"


}

android {
    namespace = "com.example.tracktask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tracktask"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val appcompat_version = "1.6.1"
    val constraintlayout_version = "2.1.4"
    val core_ktx_version = "1.12.0"
    val lifecycle_version = "2.6.2"
    val material_version = "1.9.0"
    val room_version = "2.5.2"
    //splashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.appcompat:appcompat:$appcompat_version")
    implementation ("androidx.constraintlayout:constraintlayout:$constraintlayout_version")
    implementation ("androidx.core:core-ktx:$core_ktx_version")
    implementation ("com.google.android.material:material:$material_version")

    // Lifecycle libraries
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Room libraries
    implementation( "androidx.room:room-runtime:$room_version")
    ksp ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    //test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
