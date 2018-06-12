package services;

import components.SentenceCollector;
import configuration.ApplicationConfiguration;
import model.Sentence;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.SentenceServiceImpl;
import utils.TestHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class SentenceServiceTest {

    @Mock
    private SentenceCollector sentenceCollector;

    @InjectMocks
    private SentenceService sentenceService = new SentenceServiceImpl();

    @Autowired
    private SentenceService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(sentenceCollector.getSentenceParts(TestHelper.TEXT_LINE))
                .thenReturn(Lists.newArrayList(TestHelper.SENTENCE_PART));
    }

    @Test
    public void testGetSentences() {
        List<Sentence> sentences = sentenceService.getSentences(TestHelper.TEXT_LINE);

        assertThat(sentences).isNotNull();
        assertThat(sentences).isNotEmpty();
        assertThat(sentences.size()).isEqualTo(1);
        assertThat(sentences.get(0).getWordCount()).isEqualTo(TestHelper.SENTENCE.getWordCount());
        assertThat(sentences.get(0).getWords()).hasSameElementsAs(TestHelper.SENTENCE.getWords());
    }

    @Test
    public void testGetMaxWordCount() {
        int maxWordCount = service.getMaxWordCount(TestHelper.SMALL_INPUT_PATH);

        assertThat(maxWordCount).isEqualTo(33);
    }

}
