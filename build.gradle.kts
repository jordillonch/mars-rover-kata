import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.dsl.Coroutines

val ktorVersion = "0.9.4"
val junitVersion = "5.3.0"

plugins {
    application
    kotlin("jvm") version "1.2.70"
    java
}

application {
    mainClassName = "org.jordillonch.katas.mars_rover.application.MarsRoverApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "org.jordillonch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
}

dependencies {
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("io.ktor:ktor-gson:$ktorVersion")
    compile("io.ktor:ktor-server-netty:$ktorVersion")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("net.logstash.logback:logstash-logback-encoder:5.2")
    testImplementation("com.github.javafaker:javafaker:0.16")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.9")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("io.mockk:mockk:1.8.7")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

kotlin {
    // configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>
    experimental.coroutines = Coroutines.ENABLE
}
