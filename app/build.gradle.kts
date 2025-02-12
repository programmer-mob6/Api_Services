plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.kover)
}

android {
    namespace = "com.example.apiservices"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apiservices"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

    // Testing
    testImplementation(libs.truth)
    testImplementation(libs.truth.java8.extension)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)
}

koverReport {
    val excludePackages = listOf(
        "dagger.hilt.internal.aggregatedroot.codegen.*",
        "hilt_aggregated_deps.*",
        "com.example.apiservices.*.di.*",
        "com.example.apiservices.*.Hilt_*",
        "com.example.apiservices.*.*_Factory*",
        "com.example.apiservices.*.*_HiltModules*",
        "com.example.apiservices.*.*Module_*",
        "com.example.apiservices.*.*MembersInjector*",
        "com.example.apiservices.*.*_Impl*",
        "com.example.apiservices.ComposableSingletons*",
        "com.example.apiservices.BuildConfig*",
        "com.example.apiservices.*.Fake*",
        "com.example.apiservices.app.ComposableSingletons*",
        "*_*Factory.*",
        "*_*Factory*",
        "*_Factory.*",
        "Hilt_*",
        "*_Hilt*",
        "*.navigation.*"
    )

    val includePackages = listOf(
        "com.example.apiservices.data.*",
        "com.example.apiservices.domain*",
        "com.example.apiservices.ui.*.viewmodel",
        "com.example.apiservices.ui.*.uistate",
        "com.example.apiservices.ui.*.model",
    )

    filters {
        excludes {
            classes(
                "dagger.hilt.internal.aggregatedroot.codegen.*",
                "hilt_aggregated_deps.*",
                "com.example.apiservices.*.di.*",
                "com.example.apiservices.*.Hilt_*",
                "com.example.apiservices.*.*_Factory*",
                "com.example.apiservices.*.*_HiltModules*",
                "com.example.apiservices.*.*Module_*",
                "com.example.apiservices.*.*MembersInjector*",
                "com.example.apiservices.*.*_Impl*",
                "com.example.apiservices.ComposableSingletons*",
                "com.example.apiservices.BuildConfig*",
                "com.example.apiservices.*.Fake*",
                "com.example.apiservices.app.ComposableSingletons*"
            )

            packages(
                "kotlinx.coroutines.*"
            )
        }
    }

    androidReports("debug") {
        xml {
            onCheck = true

            setReportFile(file("result.xml"))

            filters {
                excludes {
                    classes(
                        excludePackages
                    )

                    packages(
                        "kotlinx.coroutines.*"
                    )
                }

                includes {
                    packages(
                        includePackages
                    )
                }
            }
        }
        html {
            title = "Kover Report"

            charset = "UTF-8"

            onCheck = true

            filters {
                excludes {
                    classes(
                        excludePackages
                    )

                    packages(
                        "kotlinx.coroutines.*"
                    )
                }

                includes {
                    packages(
                        includePackages
                    )
                }
            }
        }
    }
}