package org.amused.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.amused.common.Constants;
import org.amused.model.Data;
import org.amused.model.ProductData;
import org.amused.testUtils.TestBase;
import org.amused.utils.CSVDataBinderUtil;
import org.amused.utils.JsonToObjectJacsonUtil;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class updateProductDataTest extends TestBase {

    @Test(dataProvider = "UpdateProductData")
    public void validateUpdateProductDetails(String name,String year,Double price,String cpuModel,String color,String capacity) {

        extentTest = extentReports.createTest("Validate update product details");


        ProductData productData_BeforeUpdate,productData_TobeUpdate,productData_AfterUpdate;

        productData_BeforeUpdate = createNewSingleDataRecord();
        String originalProductID = productData_BeforeUpdate.getId();

        productData_TobeUpdate= new ProductData();
        productData_TobeUpdate.setName(name);

        Data data = new Data();
        data.setColor(color);
        data.setCapacity(capacity);
        data.setYear(year);
        data.setPrice(price);
        data.setCPUModel(cpuModel);
        productData_TobeUpdate.setData(data);

        Response response = given()
                .relaxedHTTPSValidation()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(productData_TobeUpdate)
                .when()
                .put("/objects/"+originalProductID)
                .then()
                .extract()
                .response();


        assertEquals(HttpStatus.SC_OK, response.statusCode());

        String jsonResponse = response.asString();
        productData_AfterUpdate = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData.class);

//        System.out.println(productData_AfterUpdate.getName());
//        System.out.println(productData_BeforeUpdate.getName());
//        System.out.println(productData_AfterUpdate.getData().getPrice());
//        System.out.println(data.getPrice());
//        System.out.println(price);


        assertEquals(name, productData_AfterUpdate.getName(),"Name is not matching");
        assertEquals(year, productData_AfterUpdate.getData().getYear(),"Year is not matching");
        assertEquals(price, data.getPrice(),"Price is not matching");
        assertEquals(cpuModel, data.getCPUModel(),"CPU Model is not matching");
        assertEquals(color, data.getColor(),"Color is not matching");
        assertEquals(capacity, data.getCapacity(),"Capacity is not matching");
        assertTrue(!productData_AfterUpdate.getUpdatedAt().isEmpty(),"Product Update At fields is missing");


    }

    @DataProvider(name = "UpdateProductData")
    public Iterator<Object[]> productData() throws IOException {

        return CSVDataBinderUtil.readCSV(Constants.VALID_UPDATE_DATAFILE_PATH);

    }
}
