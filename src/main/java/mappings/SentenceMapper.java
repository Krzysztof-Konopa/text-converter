package mappings;

import model.Sentence;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public interface SentenceMapper {

    AtomicInteger sentenceId = new AtomicInteger(0);

    static Sentence mapRawSentenceToSentence(String rawSentence) {
        List<String> words = splitRawSentenceToWords(rawSentence).stream()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());

        return new Sentence(sentenceId.incrementAndGet(), words.size(), words);
    }

    static List<String> splitRawSentenceToWords(String rawSentence) {
        rawSentence = rawSentence.substring(0, rawSentence.length()-1);

        Reader reader = new StringReader(rawSentence);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        List<String> words = new ArrayList<>();
        tokenizer.wordChars('\'', '\'');

        try {
            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (tokenizer.ttype == StreamTokenizer.TT_NUMBER
                        || tokenizer.ttype == StreamTokenizer.TT_WORD) {
                    words.add(tokenizer.sval);
                }
            }
            return words;
        } catch (IOException e) {
            System.out.println("ERROR: cannot split sentence into words.");
        }
        return Collections.emptyList();
    }
}
