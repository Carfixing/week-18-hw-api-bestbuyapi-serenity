package com.bestbuyapi.producstinfo;

import com.bestbuyapi.bestbuyapiinfo.ProductsSteps;

import com.bestbuyapi.testbase.ProductsTestBase;
import com.bestbuyapi.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCURDTestWithSteps extends ProductsTestBase {
    static String name = " AA Battery" + TestUtils.getRandomValue();
    static String type = "Rechargeable";
    static int price = 500; // + TestUtils.getRandomValue();
    static int shipping = 0; // + TestUtils.getRandomValue();
    static String upc = "zinc";// + TestUtils.getRandomValue();
    static String description = "Compatible with select electronic devices; ANY size; Power Preserve technology; 8-pack";
    static String manufacturer = "Duracell";
    static String model = "MN" + TestUtils.getRandomValue() + "YZ";
    static String url = "http://www.bestbuy.com/site/duracell-batteries-8-pack/185267.p?id=1051384046551&skuId=185267&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/185285/185267_sa.jpg";
    static int productsId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new Product")
    @Test
    public void test01() {
        productsSteps.createProducts(name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(201).log().all();
    }
    @Title("Verify if the product was added to the application")
    @Test
    public void test02 (){
        HashMap<String, Object> productsMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productsMap, hasValue(name));
        productsId = (int) productsMap.get("id");
        System.out.println(productsId);
    }
    @Title("Update the product information and verify the updated information")
    @Test
    public void test03() {
        name = "BetaBatteryAA";
        type = "Long Lasting";
        price = 10;
        shipping = 3;
        upc = "0398000111113";
        description = "Compatible with select electronic devices; D size; DURALOCK Power Preserve technology; 6-pack";
        manufacturer = "KODAK";
        model = "Selenium" + TestUtils.getRandomValue() + "YZ";
        url = "http://www.bestbuy.com/site/duracell-d-batteries-4-pack/185267.p?id=1051384046551&skuId=185267&cmp=RMXCC";
        image = "http://img.bbystatic.com/BestBuy_US/images/products/1852/185267_sa.jpg";
        productsSteps.updateProduct(productsId, name, type, price, shipping, upc, description, manufacturer, model, url, image).log().all().statusCode(200);
        HashMap<String, Object> productMap = productsSteps.getProductInfoByName(name);
        Assert.assertThat(productMap, hasValue(name));
    }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test04() {
        //  productsId = 9999709;
        productsSteps.deleteProduct(productsId).statusCode(200).log().status();
        productsSteps.getProductById(productsId).statusCode(404).log().status();
    }
}

