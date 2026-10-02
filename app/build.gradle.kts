plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.industri.fintechbilling"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.industri.fintechbilling"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation(libs.androidx.constraintlayout)

    // Parser Engine Gson & Retrofit Converter
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}