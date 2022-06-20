package HwLesson4;

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
                        "query=burger&apiKey=")
                .then()
                .assertThat()
                .body("totalResults", equalTo(54))
                .body("results.title", hasItems("Falafel Burger"))
                .body("results.title", hasItems("The Unagi Burger"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=chicken&type=salad&maxReadyTime=30&apiKey=")
                .then()
                .assertThat()
                .body("results.title", hasItems("Greek Yogurt Chicken Salad"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=rice&cuisine=Korean&apiKey=")
                .then()
                .assertThat()
                .body("results.title", hasItems("Rice Pilaf"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=chicken&type=soup&apiKey=" )
                .then()
                .assertThat()
                .body("results.title", hasItems("Chicken Porridge"));

        given()
                .when()
                .get(getBaseUrl()+"recipes/complexSearch?" +
                        "query=eggs&cuisine=German&type=breakfast&apiKey=")
                .then()
                .assertThat()
                .body("results.title", hasItems("Scotch Eggs"));
    }

    @Test
    void postRecipesTest() {
        given()
                .when()
                .formParam("title","Falafel Burger")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .body("cuisine", equalTo("Middle Eastern"));

        given()
                .when()
                .formParam("title","The Unagi Burger")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .body("cuisine", equalTo("American"));

        given()
                .when()
                .formParam("title","Greek Yogurt Chicken Salad")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .body("cuisine", equalTo("Mediterranean"));

        given()
                .when()
                .formParam("title","Rice Pilaf")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .body("cuisine", equalTo("Mediterranean"));

        given()
                .when()
                .formParam("title","Chicken Porridge")
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .body("cuisine", equalTo("Mediterranean"));


    }
    @Test
    void mealPlanTest() {
        given()
                .queryParam("timeFrame", "day")
                .queryParam("diet", "vegetarian")
                .when()
                .request(Method.GET,getBaseUrl()+"mealplanner/generate")
                .then();

        String id = given()
                .queryParam("hash", "c09631a23625faeb81821521f41c6108a838314a")
                .pathParam("username", "baykovaes")
                .body("{\n" +
                        "    \"date\": 1589500800,\n" +
                        "    \"slot\": 1,\n" +
                        "    \"position\": 0,\n" +
                        "    \"type\": \"RECIPE\",\n" +
                        "    \"value\": {\n" +
                        "        \"id\": 296213,\n" +
                        "        \"servings\": 2,\n" +
                        "        \"title\": \"Spinach Salad with Roasted Vegetables and Spiced Chickpea\",\n" +
                        "        \"imageType\": \"jpg\",\n" +
                        "    }\n" +
                        "}")

                .when()
                .post("https://api.spoonacular.com/mealplanner/{username}/items")
                .then()
                .extract()
                .jsonPath()
                .get("id")
                .toString();



        given()
                .queryParam("hash", "c09631a23625faeb81821521f41c6108a838314a")
                .pathParam("id", id)
                .when()
                .delete("https://api.spoonacular.com/mealplanner/baykovaes/items/{id}")
                .then();
    }

    @Test
    void ShoppingListTest() {
        String id = given()
                .queryParam("hash", "c09631a23625faeb81821521f41c6108a838314a")
                .pathParam("username", "baykovaes")
                .body("{\n"+
                        "\"item\": \"1 package baking powder\",\n"+
                "\"aisle\": \"Baking\",\n" +
                "}")

                .when()
                .post("https://api.spoonacular.com/mealplanner/{username}/shopping-list/items")
                .then()
                .extract()
                .jsonPath()
                .get("id")
                .toString();


        given()
                .queryParam("hash", "c09631a23625faeb81821521f41c6108a838314a")
                .pathParam("username", "baykovaes")

                .when()
                .get("https://api.spoonacular.com/mealplanner/{username}/shopping-list")
                .then();


        given()
                .queryParam("hash", "c09631a23625faeb81821521f41c6108a838314a")
                .pathParam("username", "baykovaes")
                .pathParam("id", id)
                .when()
                .delete("https://api.spoonacular.com/mealplanner/{username}/shopping-list/items/{id}")
                .then();
    }
    }
