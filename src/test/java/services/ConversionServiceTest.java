package services;

import configuration.ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.CSVConversionServiceImpl;
import services.impl.XMLConversionServiceImpl;
import utils.TestHelper;

import java.io.File;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ConversionServiceTest {

    @Mock
    private SentenceService sentenceService;

    @InjectMocks
    private ConversionService csvConversionService = new CSVConversionServiceImpl();

    @InjectMocks
    private ConversionService xmlConversionService = new XMLConversionServiceImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(sentenceService.getSentences(TestHelper.TEXT_LINE))
                .thenReturn(Arrays.asList(TestHelper.SENTENCE));

        when(sentenceService.getMaxWordCount(TestHelper.CONVERSION_INPUT_PATH))
                .thenReturn(8);
    }

    @Test
    public void testConvertCSV() {
        csvConversionService.convert(TestHelper.CONVERSION_INPUT_PATH, TestHelper.CONVERSION_CSV_PATH);

        File output = new File(TestHelper.CONVERSION_CSV_PATH);
        File reference = new File(TestHelper.CONVERSION_CSV_REF_PATH);

        assertThat(output).hasSameContentAs(reference);
    }

    @Test
    public void testConvertXML() {
        xmlConversionService.convert(TestHelper.CONVERSION_INPUT_PATH, TestHelper.CONVERSION_XML_PATH);

        File output = new File(TestHelper.CONVERSION_XML_PATH);
        File reference = new File(TestHelper.CONVERSION_XML_REF_PATH);

        assertThat(output).hasSameContentAs(reference);
    }
}
