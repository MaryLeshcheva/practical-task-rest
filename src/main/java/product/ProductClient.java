package product;

import httpClient.HttpClient;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.response.ValidatableResponse;
import pojos.ProductPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class ProductClient extends HttpClient {
    private static final String GET_PATH = "/api/food";
    private static final String POST_PATH = "/api/food";
    private static final String POST_RESET_PATH = "/api/data/reset";


    @Step("Добавление нового товара")
    public ValidatableResponse addProduct (ProductPojo productPojo) {
        return given()
                .spec(getSpec())
                .body(productPojo)
                .when()
                .post(POST_PATH)
                .then();
    }


    @Step("Проверка добавления товара через API")
    public ValidatableResponse checkProduct(ProductPojo productPojo, Cookie cookie) {
        return given()
                .spec(getSpec())
                .cookie(cookie)
                .when()
                .get(GET_PATH)
                .then()
                .assertThat()
                .body("name", hasItem(productPojo.getName()));
    }


    @Step("Сброс тестовых данных")
    public ValidatableResponse reset() {
        return given()
                .spec(getSpec())
                .when()
                .post(POST_RESET_PATH)
                .then();
    }

}
