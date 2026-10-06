// Top-level build file where you can add configuration options common to all subprojects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.dokka) apply false
    alias(libs.plugins.kotlin.dokka.javadoc) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}