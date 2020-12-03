package spartans;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

public class SpartanTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://100.26.130.128:8000" ;
        RestAssured.basePath = "/api" ;
    }
    @DisplayName("Testing /api/hello endpoint")
    @Test
    public void test1(){
        //Assertions.assertEquals(7, 4+3);
        given()
                .accept(ContentType.TEXT).
        when()
                .get("/hello").
        then()
                .statusCode(200)
                .body( is("Hello from Sparta") )
                .header("content-type" , "text/plain;charset=UTF-8") ;
    }

    @DisplayName("Testing /api/spartans Endpoint return json result")
    @Test
    public void testAllSpartansGetJsonResult(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                ;
    }

    @DisplayName("Testing /api/spartans Endpoint return xml result")
    @Test
    public void testAllSpartansGetXMLResult(){

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .contentType(ContentType.XML)
        ;
    }

    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
    }


}
