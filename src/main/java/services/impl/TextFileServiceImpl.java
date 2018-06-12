package services.impl;

import components.impl.ConverterFactory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ConversionService;
import services.TextFileService;
import utils.FileFormat;

@Service
public class TextFileServiceImpl implements TextFileService {

    @Autowired
    private ConverterFactory converterFactory;

    @Override
    public void performConversion(final String source, final String destination) {
        String fileExtension = FilenameUtils.getExtension(destination);
        ConversionService converter = converterFactory.getConverter(FileFormat.getByName(fileExtension));
        converter.convert(source, destination);
    }
}
