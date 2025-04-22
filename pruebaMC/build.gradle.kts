plugins {
    kotlin("jvm") version "2.1.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://jitpack.io") }  // Repositorio adicional
}

val javafxVersion = "17"

dependencies {

    implementation(kotlin("stdlib"))

    // JavaFX para Windows
    implementation("org.openjfx:javafx-controls:$javafxVersion:win")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:win")
    implementation("org.openjfx:javafx-base:$javafxVersion:win")
    //iconos
    implementation("de.jensd:fontawesomefx:8.9")

}

application {
    mainClass.set("org.example.MainKt")
}

tasks.withType<JavaExec> {
    val osName = System.getProperty("os.name")
    val platform = when {
        osName.startsWith("Windows") -> "win"
        osName.startsWith("Mac") -> "mac"
        osName.startsWith("Linux") -> "linux"
        else -> throw Error("Unknown OS: $osName")
    }

    jvmArgs = listOf(
        "--module-path", classpath.asPath,
        "--add-modules", "javafx.controls,javafx.base,javafx.graphics"
    )
}
