package org.amused.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.amused.function.GetAllObjects;
import org.amused.model.ProductData;
import org.amused.testUtils.TestBase;
import org.amused.utils.JsonToObjectJacsonUtil;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

public class getAllObjectsTest extends TestBase {

    @Test
    public void ValidateGetAllObjects() {
        extentTest = extentReports.createTest("Validate get all objects");
        Response response = given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .when()
                .get("/objects")
                .then().statusCode(HttpStatus.SC_OK)
                .extract()
                .response();


        String jsonResponse = response.asString();

        ProductData[] product = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData[].class);

        ProductData firstProduct = product[0];
        assertEquals("1", firstProduct.getId());
        assertEquals("Google Pixel 6 Pro", firstProduct.getName());
        assertNotNull(firstProduct.getData());
        assertEquals("Cloudy White", firstProduct.getData().getColor());
        assertEquals("128 GB", firstProduct.getData().getCapacity());
    }

    @Test
    public void ValidateALlTheObjectsAreHavingID() {
        extentTest = extentReports.createTest("Validate all objects are having the fields ID");


        Response response = given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .when()
                .get("/objects")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());

        String jsonResponse = response.asString();

        ProductData[] product = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData[].class);

        GetAllObjects getAllObjects = new GetAllObjects();
        assertTrue(getAllObjects.isAllObjetsHaveID(product),"Not all the objects have ID");


    }

    @Test
    public void ValidateALlTheObjectsAreHavingName() {

        extentTest = extentReports.createTest("Validate all objects are having the fields Name");

        Response response = given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .when()
                .get("/objects")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        String jsonResponse = response.asString();
        ProductData[] product = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData[].class);

        GetAllObjects getAllObjects = new GetAllObjects();
        assertTrue(getAllObjects.isAllObjetsHaveName(product),"Not all the objects have Name");


    }
}
