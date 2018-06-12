package components.impl;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceChunker;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.Strings;
import org.springframework.stereotype.Component;
import components.SentenceCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class SentenceCollectorImpl implements SentenceCollector {
    private final SentenceChunker sentenceChunker;
    private String remainText;

    public SentenceCollectorImpl() {
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        SentenceModel sentenceModel = new IndoEuropeanSentenceModel();

        this.sentenceChunker = new SentenceChunker(tokenizerFactory, sentenceModel);
        this.remainText = Strings.EMPTY_STRING;
    }

    @Override
    public List<String> getSentenceParts(final String text) {
        String textInput = remainText + " " + text;
        Chunking chunking = sentenceChunker.chunk(textInput.toCharArray(), 0, textInput.length());
        Set<Chunk> sentences = chunking.chunkSet();
        String slice = chunking.charSequence().toString();

        if(sentences.size() < 1) {
            this.remainText = slice;
            return Collections.emptyList();
        }

        List<String> sentenceParts = new ArrayList<>();

        sentences.forEach(sentence -> {
            sentenceParts.add(slice.substring(sentence.start(), sentence.end()));
            this.remainText = slice.substring(sentence.end());
        });
        return sentenceParts;
    }
}
