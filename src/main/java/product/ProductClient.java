package product;

import httpClient.HttpClient;
import io.restassured.response.ValidatableResponse;
import pojos.ProductPojo;

import static io.restassured.RestAssured.given;

public class ProductClient extends HttpClient {
    private static final String GET_PATH = "/api/food";
    private static final String POST_PATH = "/api/food";
    private static final String POST_RESET_PATH = "/api/data/reset";

//    @Step("Добавление нового товара")
    public ValidatableResponse addProduct (ProductPojo productPojo) {
        return given()
                .spec(getSpec())
                .body(productPojo)
                .when()
                .post(POST_PATH)
                .then();
    }

//    @Step("Получить список товаров")
    public ValidatableResponse getListProducts() {
        return given()
                .spec(getSpec())
                .when()
                .get(GET_PATH)
                .then();
    }

//    @Step("Сброс тестовых данных")
    public ValidatableResponse reset() {
        return given()
                .spec(getSpec())
                .when()
                .post(POST_RESET_PATH)
                .then();
    }

}
