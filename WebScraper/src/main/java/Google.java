import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Google {
    private WebClient client = new WebClient();
    private WebDriver driver = new HtmlUnitDriver();
    private HashMap<String, ProductLineUp>  repo = new HashMap<>();

    public Google() {
        client.getOptions().setCssEnabled(true);
        client.getOptions().setJavaScriptEnabled(true);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.google.com");
    }

    public void search(String productName) {
        if (!repo.containsKey(productName)) {
            WebElement form = driver.findElement(By.xpath(".//form[@role='search']"));
            WebElement inputBar = form.findElement(By.xpath(".//input[@title='Search']"));
            inputBar.sendKeys(productName);
            inputBar.submit();
            driver.navigate().refresh();
            List<WebElement> elements = driver.findElements(By.xpath("//a[@class='eZt8xd']"));
            elements.get(1).click();
            driver.navigate().refresh();
            storeProducts(productName);
        }
    }

    private void storeProducts(String productName) {
        List<WebElement> items = driver.findElements(By.className("ljqwrc"));
        if (!items.isEmpty()) {
            ArrayList<ProductContents> itemsContents = new ArrayList<>();
            for (WebElement item : items) {
                Optional<ProductContents> contents = ProductContents.create(item);
                if (contents.isPresent()) {
                    itemsContents.add(contents.get());
                }
            }
            repo.put(productName, new ProductLineUp(itemsContents));
        }
    }

    public void getCheapProducts() {
        for (String name : repo.keySet()) {
            System.out.println(repo.get(name).getCheapestProduct().toString());
        }
    }
}
