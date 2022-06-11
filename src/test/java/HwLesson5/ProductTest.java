package HwLesson5;

import HWLesson5.RetrofitUtils;
import HWLesson5.api.ProductService;
import HWLesson5.dto.Product;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;


import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import retrofit2.Response;


import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductTest {

    static ProductService productService;
    Product product = null;

    int id;


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }



    @SneakyThrows
    @Test
        void getProductTest() {
        Response<ResponseBody> response = productService.getProducts()
                .execute();

        assertThat(response.code(), Matchers.is(200));
    }


    @Test
       void createProductTest() throws IOException {
        product = new Product()
                .withTitle("Cherry")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), Matchers.is(201));

        Response<Product> response1 = productService.getProductById(id)
                .execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.code(), Matchers.is(200));

        Response<ResponseBody> response2 = productService.deleteProduct(id).execute();
        assertThat(response2.isSuccessful(), CoreMatchers.is(true));
    }



    @Test
        void modifyProductTest() throws IOException {
        product = new Product()
                .withTitle("Cherry")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), Matchers.is(201));

        Response<Product> response1 = productService.getProductById(id)
                .execute();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
        assertThat(response1.code(), Matchers.is(200));


        product = new Product()
                .withId(id)
                .withTitle("Apple")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
        Response<Product> response2 = productService.modifyProduct(product)
                .execute();

        assertThat(response2.code(), Matchers.is(200));

        Response<Product> response3 = productService.getProductById(id)
                .execute();
        assertThat(response3.isSuccessful(), CoreMatchers.is(true));
        assertThat(response3.code(), Matchers.is(200));

        Response<ResponseBody> response4 = productService.deleteProduct(id).execute();
        assertThat(response4.isSuccessful(), CoreMatchers.is(true));
    }








}
