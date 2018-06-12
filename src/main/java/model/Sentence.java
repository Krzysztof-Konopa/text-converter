package model;

import java.util.List;

public class Sentence {

    private long id;

    private int wordCount;

    private List<String> words;

    public Sentence(long id, int wordCount, List<String> words) {
        this.id = id;
        this.wordCount = wordCount;
        this.words = words;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
