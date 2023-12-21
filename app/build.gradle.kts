plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myhydrovative"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myhydrovative"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://story-api.dicoding.dev/v1/\"")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        mlModelBinding = true
    }
    dataBinding {
        isEnabled = true
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    implementation("org.tensorflow:tensorflow-lite-metadata:0.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment:2.7.0")

    implementation("androidx.navigation:navigation-common:2.7.0")
    implementation("androidx.navigation:navigation-common-ktx:2.7.0")

    implementation("androidx.navigation:navigation-runtime:2.7.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.0")

    implementation("androidx.navigation:navigation-ui-ktx:2.7.0")
    implementation("androidx.navigation:navigation-ui:2.7.0")

    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Kamera
    val cameraxVersion = "1.2.3"
    implementation("androidx.camera:camera-camera2:$cameraxVersion")
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
    implementation("androidx.camera:camera-view:$cameraxVersion")

    // Data Store
    val ds_version = "1.0.0"
    implementation("androidx.datastore:datastore-preferences-core:${ds_version}")
    implementation("androidx.datastore:datastore-preferences:${ds_version}")
    implementation("androidx.datastore:datastore-preferences-rxjava2:${ds_version}")
    implementation("androidx.datastore:datastore-preferences-rxjava3:${ds_version}")

    // Retrofit
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    // Android KTX
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation("com.github.kirich1409:viewbindingpropertydelegate:1.5.3")

    // Lifecycle
    val lifecycle_version = "2.4.1"
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}")

    // Firebase
    implementation("com.google.firebase:firebase-database:20.3.0")

    // GLide
    implementation("com.github.bumptech.glide:compiler:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")
}