import java.io.IOException;
import java.net.URISyntaxException;

public interface Website {
    void search(String productName) throws IOException, InterruptedException;

    ProductContents getProduct(String name);

    int getResultAmount();
}
