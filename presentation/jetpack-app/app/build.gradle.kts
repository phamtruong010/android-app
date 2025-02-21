import java.util.regex.Pattern

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

fun getCurrentVariant(): String? {
    val taskRequestsStr = gradle.startParameter.taskRequests.toString()
    val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("bundle(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(taskRequestsStr)
    return if (matcher.find()) {
        matcher.group(1).lowercase()
    } else {
        null
    }
}

fun myTask() {
    val flavor = if (getCurrentVariant() == null) {
        "env"
    } else {
        getCurrentVariant()
    }

    println("flavor" + flavor)

    var f = File("${project.rootDir}/app/env/.${flavor}")
    println("file" + f)
    val env = mutableMapOf<String, String>()

    if (f.exists()) {

        val lines = f.readLines()

        for (line in lines) {
            val parts = line.split("=")
            env[parts[0].trim()] = parts[1].trim()

        }
        project.ext.set("env", env)

        val envMap = project.ext.get("env") as Map<String, String>

        val bundleIdentifier = envMap["BUNDLE_IDENTIFIER"]

    } else {
        println("**************************")
        println("*** Missing .env file ****")
        println("**************************")
    }

}
myTask()
tasks.create("MyTask") {
    myTask()
}
android {

    val envMap = project.ext.get("env") as Map<String, String>
    namespace = "com.helloworld"
    compileSdk = 35

    defaultConfig {
        applicationId = envMap["BUNDLE_IDENTIFIER"]
        minSdk = 28
        targetSdk = 34
        versionCode = envMap["VERSION_CODE"]?.toInt()
        versionName = envMap["VERSION_NAME"]
        envMap.forEach { (k, v) ->
            val escaped = v.replace("%", "\\u0025")
            buildConfigField("String", k, "\"$v\"")
            resValue("string", k, "\"$escaped\"")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    flavorDimensions.add("default")

    productFlavors {
        create("equixdev") {
//            applicationIdSuffix = ".mock"
        }
        create("tradeforgooddev") {
//            applicationIdSuffix = ".mock"
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
    }
}

dependencies {

    // Kotlin
    implementation(libs.kotlin.stdlib.jdk7)

    // AndroidX
    implementation(libs.appcompat)
    implementation(libs.lifecycle.viewmodel.ktx)

    // Extensions
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.extensions)

    // okHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp.sse)

    // GSON
    implementation(libs.gson)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.dagger.android)
    implementation(libs.hilt.work)
    ksp(libs.hilt.dagger.compiler)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Paging
    implementation(libs.paging.compose)

    // WorkManager
    implementation(libs.work.runtime.ktx)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.animation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.runtime.saveable)
    implementation(libs.compose.material3)
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons.extended)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.glide)
    implementation("com.himanshoe:charty:2.0.0-alpha01")
//    implementation(libs.himanshoe.charty)

    // Coil
    implementation(libs.coil.compose)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    // Serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.mmkv)

//    implementation(libs.prdownloader)
    debugImplementation("com.github.amitshekhariitbhu:PRDownloader:1.0.1")

    // Datetime
    implementation(libs.kotlinx.datetime)
    implementation(libs.biometric)

    // Sub Modules
    implementation(project(path = ":domain"))
    implementation(project(path = ":adapter"))
    implementation(project(path = ":infrastructure"))


    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")
    implementation("com.google.accompanist:accompanist-pager:0.13.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.13.0")

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-auth:23.1.0")
    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation("com.google.firebase:firebase-firestore:25.1.1")
    implementation("com.google.firebase:firebase-appcheck-ktx:18.0.0")
    implementation("com.google.firebase:firebase-appcheck-debug:18.0.0")
    implementation("com.google.firebase:firebase-appcheck-playintegrity:18.0.0")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.1")
    implementation("com.google.firebase:firebase-messaging-ktx:24.1.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}