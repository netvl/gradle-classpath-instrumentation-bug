package com.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Map;

public abstract class ExampleTask extends DefaultTask {
    @InputFile
    public abstract RegularFileProperty getInputFile();

    @TaskAction
    public void run() throws Exception {
        final var path = getInputFile().get().getAsFile().toPath();
        final var uri = new URI("jar:" + path.toUri());
        try (final var fs = FileSystems.newFileSystem(uri, Map.of())) {
            final var innerPath = fs.getRootDirectories().iterator().next().resolve("test.txt");
            // Files.readString() throws from Gradle instrumentation.
            System.out.println(Files.readString(innerPath));
        }
    }
}
