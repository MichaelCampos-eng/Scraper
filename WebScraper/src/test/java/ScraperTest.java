import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ScraperTest {

    @Test
    public void test() throws IOException, InterruptedException {
        Google google = new Google();
        google.search("hammer");
        google.getCheapProducts();
    }

}