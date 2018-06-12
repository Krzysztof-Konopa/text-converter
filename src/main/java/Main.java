import configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.TextFileService;

public class Main {

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("Missing input parameters: [source] [destination]");
            return;
        }

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        TextFileService textFileService = (TextFileService) context.getBean("textFileService");

        TextFileService.validateFileInput(args[0], args[1]);

        System.out.println("Converting...");

        textFileService.performConversion(args[0], args[1]);
        System.out.println("Conversion complete.");
    }
}
