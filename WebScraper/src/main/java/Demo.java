import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Demo {
    WebClient client = new WebClient();

    public void launchBrowser() throws IOException {
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getPage("https://www.lowes.com");
    }

    public void searchProduct() throws InterruptedException{
        Thread.sleep(2000);
    }

    public void printTitle() {
        System.out.println(client.getCurrentWindow());
        System.out.println(client.getBrowserVersion());
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Demo demo = new Demo();
        demo.launchBrowser();
        demo.searchProduct();
        demo.printTitle();
    }
}
