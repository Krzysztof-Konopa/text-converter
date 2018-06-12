package components;

import configuration.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.TestHelper;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class SentenceCollectorTest {

    @Autowired
    private SentenceCollector sentenceCollector;

    @Test
    public void testGetSentenceParts() {
        String uncompleteSentence = " What he  shouted was shocking:";

        List<String> sentenceParts = sentenceCollector.getSentenceParts(TestHelper.TEXT_LINE);

        assertThat(sentenceParts.size()).isEqualTo(1);
        assertThat(sentenceParts.get(0)).isEqualTo(TestHelper.SENTENCE_PART);

        sentenceParts = sentenceCollector.getSentenceParts(uncompleteSentence);

        assertThat(sentenceParts).isEmpty();
    }
}
