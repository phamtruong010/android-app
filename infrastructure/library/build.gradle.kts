plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.infrastructure"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
kotlin {
//    jvmToolchain(libs.versions.jdk.get().toInt())
}

dependencies {

    debugImplementation(libs.mmkv)
    
    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.paging.common.ktx)
    ksp(libs.room.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // okHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.sse)
    implementation(libs.logging.interceptor)

    // GSON
    implementation(libs.gson)
    

    // Hilt
    implementation(libs.hilt.dagger.android)
    implementation(libs.hilt.work)
    //ksp(libs.hilt.dagger.compiler)
    //ksp(libs.hilt.compiler)


    debugImplementation("com.github.amitshekhariitbhu:PRDownloader:1.0.1")
    // Serialization
    implementation(libs.kotlinx.serialization.json)

    implementation(project(path = ":adapter"))
}