package configuration;

import components.SentenceCollector;
import components.impl.ConverterFactory;
import components.impl.SentenceCollectorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.ConversionService;
import services.SentenceService;
import services.TextFileService;
import services.impl.CSVConversionServiceImpl;
import services.impl.SentenceServiceImpl;
import services.impl.TextFileServiceImpl;
import services.impl.XMLConversionServiceImpl;

@Configuration
@SuppressWarnings("unused")
public class ApplicationConfiguration {

    @Bean
    public TextFileService textFileService() {
        return new TextFileServiceImpl();
    }

    @Bean
    public SentenceService sentenceService() {
        return new SentenceServiceImpl();
    }

    @Bean
    public ConverterFactory converterFactory() {
        return new ConverterFactory();
    }

    @Bean
    public ConversionService csvConverter() {
        return new CSVConversionServiceImpl();
    }

    @Bean
    public ConversionService xmlConverter() {
        return new XMLConversionServiceImpl();
    }

    @Bean
    public SentenceCollector sentenceCollector() {
        return new SentenceCollectorImpl();
    }
}
