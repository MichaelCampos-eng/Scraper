import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Google {
    private WebClient client = new WebClient();
    private WebDriver driver = new HtmlUnitDriver();


    public Google() {
        client.getOptions().setCssEnabled(true);
        client.getOptions().setJavaScriptEnabled(true);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.google.com");
    }

    public void search(String productName) {
        WebElement form = driver.findElement(By.xpath(".//form[@role='search']"));
        WebElement inputBar = form.findElement(By.xpath(".//input[@title='Search']"));
        inputBar.sendKeys(productName);
        inputBar.submit();
        driver.navigate().refresh();
        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='eZt8xd']"));
        elements.get(1).click();
        driver.navigate().refresh();
        storeProducts();
    }

    private void storeProducts() {
        List<WebElement> items = driver.findElements(By.className("ljqwrc"));
        ArrayList<ProductContents> itemsContents = new ArrayList<>();
        int counter = 1;
        for (WebElement item : items) {
            System.out.println("ITEM: " + counter);
            Optional<ProductContents> contents = ProductContents.create(item);
            if (contents.isPresent()) {
                itemsContents.add(contents.get());
                System.out.println(contents.get().toString());
            }
            counter += 1;
        }
    }
}
