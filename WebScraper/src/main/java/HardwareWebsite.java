import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.io.IOException;
import java.util.ArrayList;
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

        driver.navigate().refresh();

        // Parse products
        List<WebElement> products = driver.findElements(By.xpath(productPath));

        for (WebElement product : products) {
            product = getProductRoot(product);
            WebElement description = product.findElement(By.xpath(descriptionPath));
            WebElement brand = product.findElement(By.xpath(brandPath));
            WebElement price = product.findElement(By.xpath(pricePath));
            WebElement url = product.findElement(By.xpath(".//a"));
            productRepo.put(brand.getText(), new ProductContents(brand.getText(), description.getText(),
                    price.getText(), website + url.getText()));
            System.out.println(brand.getText() + " | " + price.getText() + " | " + url.getText() + " | " + description.getText());
        }
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
