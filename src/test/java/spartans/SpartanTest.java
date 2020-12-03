package spartans;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

public class SpartanTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://100.26.130.128:8000" ;
        RestAssured.basePath = "/api" ;
    }


    @Test
    public void test1(){
        Assertions.assertEquals(7, 4+3);
    }


    @AfterAll
    public static void tearDown(){
        RestAssured.reset();
    }


}
