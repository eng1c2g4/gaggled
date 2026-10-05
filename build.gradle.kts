group = "com.github.eng1c2g4"
version = "0.1.0-SNAPSHOT"

allprojects {
    plugins.apply("idea")
}

subprojects {
    plugins.apply("java")

    version = rootProject.version
    repositories {
        mavenCentral()
        maven("https://central.sonatype.com/repository/maven-snapshots/")
    }

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}