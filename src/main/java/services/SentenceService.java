package services;

import model.Sentence;

import java.util.List;

public interface SentenceService {

    List<Sentence> getSentences(final String textLine);

    int getMaxWordCount(final String source);
}