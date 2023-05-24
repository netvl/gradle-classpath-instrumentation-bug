plugins {
    id("com.example.plugin")
}

val makeJar by tasks.registering(Zip::class) {
    destinationDirectory.set(layout.buildDirectory)
    archiveBaseName.set("example")
    from("README.md") {
        rename { "test.txt" }
    }
}

tasks.exampleTask {
    inputFile.set(makeJar.flatMap { it.archiveFile })
}
