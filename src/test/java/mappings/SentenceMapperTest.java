package mappings;

import configuration.ApplicationConfiguration;
import model.Sentence;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class SentenceMapperTest {
    private static final String RAW_SENTENCE = "What he  shouted was shocking:  停在那儿, 你这肮脏的掠夺者!";
    private static final List<String> WORDS = Lists.newArrayList(
            "he", "shocking", "shouted", "was", "What", "你这肮脏的掠夺者", "停在那儿");

    @Test
    public void testMapRawSentenceToSentence() {
        Sentence sentence = SentenceMapper.mapRawSentenceToSentence(RAW_SENTENCE);

        assertThat(sentence).isNotNull();
        assertThat(sentence.getId()).isEqualTo(1);
        assertThat(sentence.getWordCount()).isEqualTo(WORDS.size());
        assertThat(sentence.getWords()).containsSequence(WORDS);
    }

    @Test
    public void testSplitRawSentenceToWords() {
        List<String> words = SentenceMapper.splitRawSentenceToWords(RAW_SENTENCE);
        assertThat(words).hasSameElementsAs(WORDS);
    }
}
