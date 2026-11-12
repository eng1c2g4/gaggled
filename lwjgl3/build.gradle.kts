plugins {
    application
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(libs.libgdx.backend.lwjgl3)
    implementation("com.badlogicgames.gdx:gdx-platform:${libs.versions.libgdx.get()}:natives-desktop")
    implementation(project(":core"))

    constraints {
        implementation(libs.bundles.lwjgl)
    }
}

application {
    mainClass = "com.github.eng1c2g4.eng1c2g4.lwjgl3.Launcher"
}

tasks.jar {
    archiveBaseName = rootProject.name
    manifest {
        attributes("Main-Class" to "com.github.eng1c2g4.eng1c2g4.lwjgl3.Launcher")
    }
}

tasks.shadowJar {
    archiveBaseName = rootProject.name
    from("../") {
        include("assets/**/*")
    }
}
