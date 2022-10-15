import org.junit.jupiter.api.Test;
import java.io.IOException;

class ScraperTest {

    @Test
    public void test() throws IOException, InterruptedException {
        Website lowes = new Lowes();
        lowes.search("Hammer");
    }
}