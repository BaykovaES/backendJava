package HwLesson3;

import HwLesson3.AbstractTest;
import io.restassured.http.Method;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class spoonacularTest extends AbstractTest {

    @Test
    void getRecipesTest() {

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=burger&apiKey=" +getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("totalResults", equalTo(54))
                .body("results.title", hasItems("Falafel Burger"))
                .body("results.title", hasItems("The Unagi Burger"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=chicken&type=salad&maxReadyTime=30&apiKey=" +getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("results.title", hasItems("Greek Yogurt Chicken Salad"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=rice&cuisine=Korean&apiKey=" +getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("results.title", hasItems("Rice Pilaf"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=chicken&type=soup&apiKey=" +getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("results.title", hasItems("Chicken Porridge"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=eggs&cuisine=German&type=breakfast&apiKey=" +getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("results.title", hasItems("Scotch Eggs"));
    }

    @Test
    void postRecipesTest() {
        given()
                .when()
                .request(Method.POST,getBaseUrl()+"recipes/cuisine?" +
                        "title={Title}&apiKey={apiKey}", "Falafel Burger", getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("cuisine", hasItems("Middle Eastern"));

        given()
                .when()
                .request(Method.POST,getBaseUrl()+"recipes/cuisine?" +
                        "title={Title}&apiKey={apiKey}", "The Unagi Burger", getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("cuisine", hasItems("American"));

        given()
                .when()
                .request(Method.POST,getBaseUrl()+"recipes/cuisine?" +
                        "title={Title}&apiKey={apiKey}", "Greek Yogurt Chicken Salad", getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("cuisine", hasItems("Mediterranean"));

        given()
                .when()
                .request(Method.POST,getBaseUrl()+"recipes/cuisine?" +
                        "title={Title}&apiKey={apiKey}", "Rice Pilaf", getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("cuisine", hasItems("Mediterranean"));

        given()
                .when()
                .request(Method.POST,getBaseUrl()+"recipes/cuisine?" +
                        "title={Title}&apiKey={apiKey}", "Chicken Porridge", getApiKey())
                .then()
                .statusCode(200)
                .assertThat()
                .body("cuisine", hasItems("European"));


    }

}
