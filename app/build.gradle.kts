
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")



}

android {
    namespace = "com.example.tracktask"
    compileSdk = 33

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
    packagingOptions{
        exclude ("META-INF/atomicfu.kotlin_module")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.room:room-common:2.5.2")
    val activityVersion = "1.4.0"
    val appCompatVersion = "1.4.0"
    val constraintLayoutVersion = "2.1.2"
     val coreTestingVersion = "2.1.0"
    val coroutines = "1.5.2"
   val lifecycleVersion = "2.4.0"
   val materialVersion = "1.4.0"
   val roomVersion = "2.3.0"
    // testing
  val  junitVersion = "4.13.2"
   val espressoVersion = "3.4.0"
     val androidxJunitVersion = "1.1.3"

    //implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation ("androidx.appcompat:appcompat:$appCompatVersion")
    implementation ("androidx.activity:activity-ktx:$activityVersion")

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation ("androidx.room:room-ktx:$roomVersion")
    kapt ("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$roomVersion")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    // Kotlin components
    //implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")

    // UI
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
    implementation ("com.google.android.material:material:$materialVersion")

    // Testing
    testImplementation ("junit:junit:$junitVersion")
    androidTestImplementation ("androidx.arch.core:core-testing:$coreTestingVersion")
    androidTestImplementation ("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation( "androidx.test.ext:junit:$androidxJunitVersion")
}