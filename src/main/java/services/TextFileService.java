package services;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public interface TextFileService {

    void performConversion(final String source, final String destination);

    static void validateFileInput(final String source, final String destination) {
        if(Objects.isNull(source) || source.isEmpty()) {
            throw new NullPointerException("Source file location is [null] or empty.");
        }

        if(Objects.isNull(destination) || destination.isEmpty()) {
            throw new NullPointerException("Destination file location is [null] or empty.");
        }

        if(!(new File(source).isFile())) {
            throw new IllegalArgumentException("Source file location does not point to valid file.");
        }

        if(FilenameUtils.getExtension(destination).isEmpty()) {
            throw new IllegalArgumentException("Invalid destination file name.");
        }

        Path directoryPath = Paths.get(destination).getParent();

        if(!Objects.isNull(directoryPath)) {
            if(!Files.isDirectory(directoryPath)) {
                throw new IllegalArgumentException("Destination file location does not point to valid directory.");
            }
        }
    }

}
