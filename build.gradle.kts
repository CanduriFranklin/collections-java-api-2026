plugins {
    id("java")
}

group = "me.dio"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

// Configuración para OpenJ9 / IBM Semeru
tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Xshareclasses",
        "-Xscmx128m",
        "-Xquickstart"
    )
}
