import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven ("https://jitpack.io" )
}

dependencies {
    // Web Scraping
    implementation("org.jsoup:jsoup:1.14.1")

    // FFmpeg converter
    implementation("org.bytedeco:javacv-platform:1.5.6")

    // JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")

    testImplementation(kotlin("test"))
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
    implementation("com.google.firebase:firebase-admin:9.1.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}