import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductContents {
    private String name;
    private String price;
    private String store;
    private String ratings;
    private String reviews;

    public ProductContents(String inputName, String inputPrice, String inputStore, String inputRatings, String inputReviews) {
        name = inputName;
        price = inputPrice;
        store = inputStore;
        ratings = inputRatings;
        reviews = inputReviews;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRatings() {
        return ratings;
    }

    private void parseProductComponents() {
        Pattern price = Pattern.compile("\\$(\\d+\\.\\d+)");
        Pattern shippingPrice = Pattern.compile("\\$(\\S+) Shipping");
        Pattern store = Pattern.compile("from (\\S+\\s+\\S+)");
        Pattern product = Pattern.compile("(\\w+(?!Shipping)(?! shipping)\\:*\\-*\\.*\\s*)*");
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPrice: " + price + "\nStore: " + store + "\nRatings: " + ratings;
    }

    // Ensure that all necessary elements exist before parsing
    public static Optional<ProductContents> create(WebElement item) {
        Optional<String> itemTitle = Optional.ofNullable(item.findElement(By.xpath(".//h3[@class='sh-np__product-title translate-content']")).getText());
        Optional<String> itemPrice = Optional.ofNullable(item.findElement(By.className("T14wmb")).getText());
        Optional<String> itemStore = Optional.ofNullable(item.findElement(By.className("E5ocAb")).getText());
        Optional<String> itemRatings = Optional.empty();

        // There exists no method where I can retrieve a class if it exists or null, programs throws element doesn't exist
        // otherwise
        if (Arrays.asList(item.getAttribute("class").split(" ")).contains("U6puSd")) {
            itemRatings = Optional.ofNullable(item.findElement(By.className("U6puSd")).getText());
        }

        if (itemTitle.isPresent() && itemPrice.isPresent() &&  itemStore.isPresent()) {
            String ratingsItem = itemRatings.isPresent() ? itemRatings.get() : "unknown";
            Matcher priceMatcher = Pattern.compile("\\$(\\d+\\.\\d+)").matcher(itemPrice.get());
            Matcher ratingsMatcher = Pattern.compile("(\\w+(\\d+)(' stars')\\:*\\-*\\.*\\s*)*").matcher(ratingsItem);
            String price = priceMatcher.matches() ? priceMatcher.group(1) : "unknown";
            String ratings = ratingsMatcher.matches() ? ratingsMatcher.group(1) : "3";
            String reviews = ratingsMatcher.matches() ? ratingsMatcher.group(2) : "0";
            return Optional.of(new ProductContents(itemTitle.get(), price, itemStore.get(), ratings, reviews));
        }

        return Optional.empty();
    }

}
