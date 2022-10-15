import com.gargoylesoftware.htmlunit.html.Html;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.safari.SafariDriver;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HardwareWebsite implements Website {
    protected HashMap<String, ProductContents> productRepo = new HashMap<>();
    protected WebDriver driver = new HtmlUnitDriver();
    protected String website;
    protected String productPath;
    protected String brandPath;
    protected String descriptionPath;
    protected String pricePath;

    public void openWebsite(String productName) throws InterruptedException, IOException {
    }

    public WebElement getProductRoot(WebElement product) {
        return product;
    }
    
    public void search(String productName) throws InterruptedException, IOException {
        // Input search query
        openWebsite(productName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-selector='splp-prd-act-$']")));

        driver.navigate().refresh();
        System.out.println(driver.getPageSource());
        /**
        // Parse products
        List<WebElement> products = driver.findElements(By.xpath(productPath));
        System.out.println(products.size());
        for (WebElement product : products) {
            product = getProductRoot(product);
            WebElement description = product.findElement(By.xpath(descriptionPath));
            WebElement brand = product.findElement(By.xpath(brandPath));
            System.out.println(product.findElement(By.className("slpl-price-info")));
            WebElement price = product.findElement(By.xpath(pricePath));
            System.out.println("Price: " + price);
            WebElement url = product.findElement(By.xpath(".//a"));
            productRepo.put(brand.getText(), new ProductContents(brand.getText(), description.getText(),
                    formatPrice(price.getText()), website + url.getText()));
            System.out.println(brand.getText() + " | " + price.getText() + " | " + url.getText() + " | " + description.getText());
        } */
        driver.close();
    }

    public float formatPrice(String price) {
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher match = pattern.matcher(price);
        if (!price.contains(".") & match.find()) {
            return Float.parseFloat(new StringBuilder(match.group(0)).insert(match.group(0).length() - 2, '.').toString());
        }
        return Float.parseFloat(match.group(0));
    }

    public ProductContents getProduct(String name) {
        return productRepo.get(name);
    }

    public int getResultAmount() {
        return productRepo.size();
    }
}
