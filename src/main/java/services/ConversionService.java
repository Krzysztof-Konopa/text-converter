package services;

public interface ConversionService {

    String ENCODING = "UTF-8";

    void convert(final String source, final String destination);

}
