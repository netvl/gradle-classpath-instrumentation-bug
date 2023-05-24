plugins {
    `java-gradle-plugin`
}

gradlePlugin {
    plugins.create("plugin") {
        id = "com.example.plugin"
        implementationClass = "com.example.ExamplePlugin"
    }
}
