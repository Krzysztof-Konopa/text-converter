package services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import services.ConversionService;
import services.SentenceService;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class CSVConversionServiceImpl implements ConversionService {
    private static final String COLUMN_NAME = "Word";
    private static final String ROW_NAME = "Sentence";

    @Autowired
    private SentenceService sentenceService;

    @Override
    public void convert(final String source, final String destination) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(destination);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, ENCODING);
             BufferedWriter csvWriter = new BufferedWriter(outputStreamWriter)) {

            int headerCount = sentenceService.getMaxWordCount(source);

            writeHeader(csvWriter, createHeader(headerCount));
            writeBody(csvWriter, source);
        } catch (IOException e) {
            System.out.println("ERROR: File conversion interrupted. Cannot write into file: " + destination);
        }
    }

    private void writeHeader(BufferedWriter csvWriter, final String header) throws IOException {
        csvWriter.write(header);
    }

    private void writeBody(BufferedWriter csvWriter, final String source) {
        try(Stream<String> lines = Files.lines(Paths.get(source))) {
            lines.forEach(line -> sentenceService.getSentences(line)
                    .forEach(sentence -> {
                        StringBuilder rowBuilder = new StringBuilder();
                        rowBuilder.append(ROW_NAME).append(" ").append(sentence.getId());
                        sentence.getWords().forEach(word -> rowBuilder.append(", ").append(word));
                        rowBuilder.append("\n");
                        try {
                            csvWriter.write(rowBuilder.toString());
                        } catch (IOException e) {
                            throw new RuntimeException("ERROR: Cannot write into file.");
                        }
                    }));
        } catch (IOException e) {
            System.out.println("ERROR: Cannot open file " + source + " for reading.");
        }
    }

    private String createHeader(int headerCount) {
        StringBuilder headerBuilder = new StringBuilder();

        for(int headerIndex=1; headerIndex <= headerCount; headerIndex++) {
            headerBuilder.append(", ").append(COLUMN_NAME).append(" ").append(headerIndex);
        }
        return headerBuilder.append("\n").toString();
    }
}
