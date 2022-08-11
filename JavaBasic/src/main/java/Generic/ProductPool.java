package Generic;

import java.util.ArrayList;
import java.util.Random;

/**
 * <p>Title: ProductPool</p>
 * <p>Description: 奖品池</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/7/3</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class ProductPool<T> {

    private T product;

    Random random = new Random();


    ArrayList<T> productList = new ArrayList<>();

    public T getProduct() {
        product = productList.get(random.nextInt(productList.size()));
        return product;
    }

    public void setProduct(T product) {
        productList.add(product);
    }
}
