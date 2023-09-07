import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pojos.ProductPojo;
import product.ProductClient;

import java.sql.SQLException;
import java.util.stream.Stream;

public class CheckAddProductsParameterizedTest extends BaseTestDB{

    public static Stream<Arguments> getProductsData() {
        return Stream.of(
                Arguments.of("Ананас", "FRUIT", true),
                Arguments.of("Редис", "VEGETABLE", false)
        );
    }

    @ParameterizedTest
    @MethodSource("getProductsData")
    @DisplayName("Добавление новых товаров: экзотический фрукт, овощ.")
    public void CheckAddProducts(String name, String type, boolean exotic) throws SQLException {

        ProductClient productClient = new ProductClient();
        ProductPojo productPojo = new ProductPojo(name, type, exotic);

        ValidatableResponse responseAdd = productClient.addProduct(productPojo); // добавление товара через API

        checkProductAvailability(name, type, exotic); //проверка добавления товара через БД
        productClient.reset(); //сброс тестовых данных через API
    }

}
