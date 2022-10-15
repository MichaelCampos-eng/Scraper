import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HarborFreights extends HardwareWebsite {

    public HarborFreights() {
        super();
        website = "https://www.harborfreight.com";
        productPath = "//li[@class='grid__item--0TQUnn']";
        brandPath = ".//span[@class='grid__brand--qgy-aw']";
        descriptionPath = ".//p[@class='grid__name--HGDVEt']";
        pricePath = ".//div[@class='grid__price--NVcgVt']";
    }



    @Override
    public void openWebsite(String productName) throws InterruptedException {
        driver.get(website);
        Thread.sleep(2000);
        WebElement form = driver.findElement(By.xpath("//form[@role='presentation']"));
        System.out.println("Got Website!");
        Thread.sleep(5000);
        form.findElement(By.xpath(".//input[@name='search']")).sendKeys(productName);
        Thread.sleep(5000);
        System.out.println("Sent productName");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit search']")));
        button.click();
        Thread.sleep(5000);
        System.out.println("Clicked!");
        System.out.println(driver.getCurrentUrl());
    }
}
