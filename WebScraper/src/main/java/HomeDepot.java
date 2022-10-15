import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeDepot extends HardwareWebsite {

    public HomeDepot() {
        super();
        website = "https://www.homedepot.com";
        productPath = "//div[@data-type='product']";
        descriptionPath = ".//div[@class='product-pod__title product-pod__title__product']";
        pricePath = ".//div[@class='product-pod__price--4pxpb']";
        brandPath = ".//span[@class='product-pod__title__brand--bold']";
    }

    @Override
    public void openWebsite(String productName) throws InterruptedException {
        driver.get(website);
        Thread.sleep(1000);
        WebElement form = driver.findElement(By.id("headerSearchForm"));
        WebElement inputBar = form.findElement(By.xpath("//button[@id='headerSearchButton']"));
        inputBar.sendKeys(productName);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='headerSearchButton']")));
        button.click();
    }
}
