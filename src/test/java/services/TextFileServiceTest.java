package services;

import configuration.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.TestHelper;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TextFileServiceTest {

    @Autowired
    private TextFileService textFileService;

    @Test
    public void testValidateConversionInput() {

        try {
            TextFileService.validateFileInput(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }

        try {
            TextFileService.validateFileInput("input", "");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NullPointerException.class);
        }

        try {
            TextFileService.validateFileInput(TestHelper.SMALL_INPUT_PATH, "out");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        try {
            TextFileService.validateFileInput(TestHelper.SMALL_INPUT_PATH,
                    Paths.get("parent", "out.xml").toString());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

    }
}
