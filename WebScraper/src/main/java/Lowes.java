import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.Html;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Lowes extends HardwareWebsite {
    WebClient client = new WebClient();

    /**
     * Page source is different from the HTML code from inspect element
     */

    public Lowes() {
        super();
        website = "https://www.lowes.com";
        productPath = "//div[@class='DescriptionRatingSpecsSection-sc-4v6c0e-29 cpSFkx description-section']";
        brandPath = ".//span[@data-selector='splp-prd-brd-nm']";
        descriptionPath = ".//span[@class='description-spn']";
        pricePath = ".//div[@data-selector='prd-price-holder']";
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }

    @Override
    public WebElement getProductRoot(WebElement product) {
        return product.findElement(By.xpath("//div[@class='DescriptionRatingSpecsSection-sc-4v6c0e-29 cpSFkx description-section']/.."));
    }

    @Override
    public void openWebsite(String productName) throws InterruptedException, IOException {
        driver.get(website);
        Thread.sleep(1000);
        WebElement form = driver.findElement(By.id("frmQuickSearch"));
        WebElement inputBar = form.findElement(By.id("search-query"));
        inputBar.sendKeys(productName);
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("sb-search-icon")));
        button.click();
        Thread.sleep(10000);
    }

}
