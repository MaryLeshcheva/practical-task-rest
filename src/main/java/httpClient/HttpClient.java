package httpClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class HttpClient {

    private static final String BASE_URL ="http://localhost:8080";

    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
