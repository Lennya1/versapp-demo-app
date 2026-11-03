plugins {
    alias(libs.plugins.android.application)
}

// ── Versión de la app ────────────────────────────────────────────────────────
// Para sacar una versión nueva, se cambian estos dos valores y se sube a la rama:
//   VERSION_NAME: la que ve la persona (1.0.0, 1.1.0, 2.0.0…)
//   VERSION_CODE: número entero que SIEMPRE tiene que subir (Android no instala
//                 una actualización con un versionCode menor o igual)
// GitHub Actions puede pisarlos con -PversionName=… -PversionCode=… si en VersApp
// se escribe una versión al compilar. Si no, se usan estos. Al compilar quedan en el
// AndroidManifest.xml del APK, que es de donde VersApp lee la versión.
val VERSION_NAME = "1.0.0"
val VERSION_CODE = 1
val versionNameApp = (findProperty("versionName") as String?)?.takeIf { it.isNotBlank() } ?: VERSION_NAME
val versionCodeApp = (findProperty("versionCode") as String?)?.takeIf { it.isNotBlank() }?.toInt() ?: VERSION_CODE

android {
    namespace = "com.versapp.demo"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.versapp.demo"
        minSdk = 24
        targetSdk = 37
        versionCode = versionCodeApp
        versionName = versionNameApp

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
            // Solo para la demo de la tesis: el APK release se firma con la clave de
            // depuración que Gradle genera sola, así se puede instalar en un teléfono.
            // Una app real usaría su propia clave de publicación (guardada como secreto).
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}