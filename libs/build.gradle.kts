plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlinx.kover)
}

group = "com.example.apiservices"

android {
    namespace = "com.example.apiservices"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
    kotlin {
        jvmToolchain(17)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.truth.java8.extension)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.testing)
//    testImplementation(libs.mockwebserver)
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
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