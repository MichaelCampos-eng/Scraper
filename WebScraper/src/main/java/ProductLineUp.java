import java.util.ArrayList;
import java.util.Comparator;

public class ProductLineUp {
    private ArrayList<ProductContents> products;

    public ProductLineUp(ArrayList<ProductContents> givenProducts) {
        products = givenProducts;
    }

    public int getSize() {
        return products.size();
    }

    public ProductContents getCheapestProduct() {
        products.sort(new PriceComparator());
        return products.get(0);
    }

    public ProductContents getHighestRatedProduct() {
        products.sort(new RatingsComparator());
        return products.get(0);
    }

    public class PriceComparator implements Comparator<ProductContents> {
        @Override
        public int compare(ProductContents o1, ProductContents o2) {
            return Float.compare(Float.parseFloat(o1.getPrice()), Float.parseFloat(o2.getPrice()));
        }
    }

    public class RatingsComparator implements Comparator<ProductContents> {
        @Override
        public int compare(ProductContents o1, ProductContents o2) {
            return Integer.compare(Integer.valueOf(o1.getRatings()), Integer.valueOf(o2.getRatings()));
        }
    }

}
