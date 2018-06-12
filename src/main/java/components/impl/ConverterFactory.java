package components.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import services.ConversionService;
import utils.FileFormat;

@Component
public class ConverterFactory {

    @Autowired
    private ApplicationContext context;

    public ConversionService getConverter(FileFormat fileFormat){
        switch(fileFormat) {
            case CSV: return (ConversionService) context.getBean("csvConverter");
            case XML: return (ConversionService) context.getBean("xmlConverter");
            default:
                throw new IllegalArgumentException("Given file format is not supported, cannot write results into file.");
        }
    }
}
