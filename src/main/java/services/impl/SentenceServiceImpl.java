package services.impl;

import mappings.SentenceMapper;
import model.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.SentenceService;
import components.SentenceCollector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    private SentenceCollector sentenceCollector;

    @Override
    public List<Sentence> getSentences(final String textLine) {
        List<String> rawSentences = sentenceCollector.getSentenceParts(textLine);
        return rawSentences.stream()
                .map(SentenceMapper::mapRawSentenceToSentence)
                .collect(Collectors.toList());
    }

    @Override
    public int getMaxWordCount(final String source) {
        try(Stream<String> lines = Files.lines(Paths.get(source))) {
            return lines.map(wordCountOfLongestSentence)
                    .reduce(Integer::max)
                    .orElse(0);
        } catch (IOException e) {
            System.out.println("ERROR: Cannot open file " + source + " for reading.");
        }
        return 0;
    }

    private Function<String, Integer> wordCountOfLongestSentence = (String textLine) ->
            sentenceCollector.getSentenceParts(textLine).stream()
                    .mapToInt(rawSentence -> SentenceMapper.splitRawSentenceToWords(rawSentence).size())
                    .max()
                    .orElse(0);

}
