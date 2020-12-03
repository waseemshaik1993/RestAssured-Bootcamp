package spartans;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanTest {

    private static int idFromPostTest ;

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

    @DisplayName("Testing POST /api/spartans Endpoint")
    @Test
    @Order(1)
    public void testAddData(){

        Map<String,Object> spartanMap = new HashMap<>();
        spartanMap.put("name" , "RE-BOOTCAMP");
        spartanMap.put("gender" , "Male");
        spartanMap.put("phone" , 1234567890);

        idFromPostTest =
        given()
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!") )
                .body("data.name", is("RE-BOOTCAMP") )
                .body("data.gender", is("Male") )
                .body("data.phone", equalTo(1234567890) )
                .extract()
                .body()
                .jsonPath().getInt("data.id")
        ;

    }
    @Order(2)
    @DisplayName("Testing GET /api/spartans/{id} Endpoint")
    @Test
    public void testGet1Data(){

        given()
                .log().all()
                .pathParams("id", idFromPostTest).
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(idFromPostTest) )
                .body("name", is("RE-BOOTCAMP") )
                .body("gender", is("Male") )
                .body("phone", is(1234567890) )


        ;

    }



    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
    }


}
