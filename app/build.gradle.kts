plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.dokka)
    alias(libs.plugins.kotlin.dokka.javadoc)
}

android {
    namespace = "com.example.sendmessage"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.sendmessage"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    // Libreria de Github que permite crear una actividad AboutUS

}

// Configuración de rutas personalizadas para Dokka (plugin V2)
dokka {
    dokkaPublications.html {
        // Redirige el formato HTML a la raíz del proyecto
        outputDirectory.set(rootDir.resolve("documentation/html"))
    }
    dokkaPublications.javadoc {
        // Redirige el formato Javadoc a la raíz del proyecto
        outputDirectory.set(rootDir.resolve("documentation/javadoc"))
    }
}

