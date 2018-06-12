package services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ConversionService;
import services.SentenceService;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class XMLConversionServiceImpl implements ConversionService {
    private static final String WORD_TAG_NAME = "word";
    private static final String SENTENCE_TAG_NAME = "sentence";
    private static final String TEXT_TAG_NAME = "text";

    @Autowired
    private SentenceService sentenceService;

    @Override
    public void convert(final String source, final String destination) {
        XMLOutputFactory xmlFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlWriter = null;

        try {
            xmlWriter = xmlFactory.createXMLStreamWriter(new FileOutputStream(destination), ENCODING);

            writeHeader(xmlWriter);
            writeBeginPart(xmlWriter);
            setMainPart(xmlWriter, source);
            writeEndPart(xmlWriter);

        } catch (Exception e) {
            System.out.println("ERROR: File conversion interrupted. Cannot write into file: " + destination);
        } finally {
            closeXmlWriter(xmlWriter);
        }
    }

    private void setMainPart(XMLStreamWriter xmlStreamWriter, final String source) {
        try (Stream<String> lines = Files.lines(Paths.get(source))) {
            lines.forEach(line -> sentenceService.getSentences(line)
                    .forEach(sentence -> {
                        try {
                            writeBodyPart(xmlStreamWriter, sentence.getWords());
                        } catch (Exception e) {
                            throw new RuntimeException("ERROR: Cannot write into file.");
                        }
                    }));
        } catch (IOException e) {
            System.out.println("ERROR: Cannot open file " + source + " for reading.");
        }
    }

    private void writeHeader(XMLStreamWriter xmlWriter) throws XMLStreamException {
        // Write the default XML declaration
        xmlWriter.writeStartDocument(ENCODING, "1.0");
        xmlWriter.writeCharacters("\n");
    }

    private void writeBeginPart(XMLStreamWriter xmlWriter) throws XMLStreamException {
        // Write the root element "text"
        xmlWriter.writeStartElement(TEXT_TAG_NAME);
        xmlWriter.writeCharacters("\n");
    }

    private void writeBodyPart(XMLStreamWriter xmlWriter, List<String> words) throws XMLStreamException {
        // Write the "sentence" element
        xmlWriter.writeStartElement(SENTENCE_TAG_NAME);

        for (String word : words) {
            // Write the "word" element
            xmlWriter.writeStartElement(WORD_TAG_NAME);
            xmlWriter.writeCharacters(word);

            // End the "word" element
            xmlWriter.writeEndElement();
        }
        // End the "sentence" element
        xmlWriter.writeEndElement();
        xmlWriter.writeCharacters("\n");
    }

    private void writeEndPart(XMLStreamWriter xmlWriter) throws XMLStreamException {
        // End the "text" element
        xmlWriter.writeEndElement();

        // End the XML document
        xmlWriter.writeEndDocument();
    }

    private void closeXmlWriter(XMLStreamWriter xmlStreamWriter) {
        // Close the XMLStreamWriter to free up resources
        try {
            if (!Objects.isNull(xmlStreamWriter)) {
                xmlStreamWriter.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR: Cannot complete writing operation to output file.");
        }
    }
}