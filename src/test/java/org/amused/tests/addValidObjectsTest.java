package org.amused.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.amused.common.Constants;
import org.amused.model.Data;
import org.amused.model.ProductData;
import org.amused.testUtils.TestBase;
import org.amused.utils.CSVDataBinderUtil;
import org.amused.utils.JsonToObjectJacsonUtil;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class addValidObjectsTest extends TestBase {

    @Test(dataProvider = "productData")
    public void validateCreateNewProductObjects(String name,String year,Double price,String cpuModel,String color,String capacity) {

        extentTest = extentReports.createTest("Validate create new product objects");

        ProductData productData = new ProductData();
        productData.setName(name);

        Data data = new Data();
        data.setColor(color);
        data.setCapacity(capacity);
        data.setYear(year);
        data.setPrice(price);
        data.setCPUModel(cpuModel);
        productData.setData(data);

        Response response = given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(productData)
                .when()
                .post("/objects")
                .then()
                .extract()
                .response();


        assertEquals(HttpStatus.SC_OK, response.statusCode());

        String jsonResponse = response.asString();

        productData = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData.class);

        System.out.println(productData.getId());
        assertEquals(name, productData.getName(),"Name is not matching");
        assertEquals(year, data.getYear(),"Year is not matching");
        assertEquals(price, data.getPrice(),"Price is not matching");
        assertEquals(cpuModel, data.getCPUModel(),"CPU Model is not matching");
        assertEquals(color, data.getColor(),"Color is not matching");
        assertEquals(capacity, data.getCapacity(),"Capacity is not matching");


    }

    @DataProvider(name = "productData")
    public Iterator<Object[]> productData() throws IOException {

        return CSVDataBinderUtil.readCSV(Constants.VALID_DATAFILE_PATH);

    }
}