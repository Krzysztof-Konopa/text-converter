package components;

import components.impl.ConverterFactory;
import configuration.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.impl.CSVConversionServiceImpl;
import services.impl.XMLConversionServiceImpl;
import utils.FileFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ConverterFactoryTest {

    @Autowired
    private ConverterFactory converterFactory;

    @Test
    public void testGetConverter() {
        assertThat(converterFactory.getConverter(FileFormat.CSV)).isInstanceOf(CSVConversionServiceImpl.class);
        assertThat(converterFactory.getConverter(FileFormat.XML)).isInstanceOf(XMLConversionServiceImpl.class);
    }
}
