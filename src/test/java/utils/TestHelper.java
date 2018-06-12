package utils;

import model.Sentence;
import org.assertj.core.util.Lists;

import java.nio.file.Paths;
import java.util.List;

public interface TestHelper {

    String RESOURCES_PATH = Paths.get(Paths.get("").toAbsolutePath().toString(),
            "src", "test", "resources").toString();
    String SMALL_INPUT_PATH = Paths.get(RESOURCES_PATH,"small.in").toString();

    String CONVERSION_INPUT_PATH = Paths.get(RESOURCES_PATH, "conversionTestInput.in").toString();
    String CONVERSION_CSV_PATH = Paths.get(RESOURCES_PATH, "conversionTestOut.csv").toString();
    String CONVERSION_XML_PATH = Paths.get(RESOURCES_PATH, "conversionTestOut.xml").toString();
    String CONVERSION_CSV_REF_PATH = Paths.get(RESOURCES_PATH, "conversionReference.csv").toString();
    String CONVERSION_XML_REF_PATH = Paths.get(RESOURCES_PATH, "conversionReference.xml").toString();

    String TEXT_LINE = "Mr. and Ms. Smith met Dr. Jekyll outside.  What he  shouted was shocking:";
    String SENTENCE_PART = "Mr. and Ms. Smith met Dr. Jekyll outside.";
    List<String> WORDS = Lists.newArrayList(
            "and", "Dr.", "Jekyll", "met", "Mr.", "Ms.", "outside", "Smith");
    Sentence SENTENCE = new Sentence(1, 8, WORDS);

}
