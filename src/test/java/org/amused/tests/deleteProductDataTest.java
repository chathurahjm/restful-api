package org.amused.tests;

import io.restassured.response.Response;
import org.amused.model.ProductData;
import org.amused.testUtils.TestBase;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class deleteProductDataTest extends TestBase {

        @Test
        public void validateDeleteProductByID() {
            extentTest = extentReports.createTest("Validate delete product objects by ID");
            ProductData productData_NewlyCreated,productData_TobeUpdate,productData_AfterUpdate;

            productData_NewlyCreated = createNewSingleDataRecord();
            String originalProductID = productData_NewlyCreated.getId();

            Response response = given()
                    .relaxedHTTPSValidation()
                    .log()
                    .all()
                    .when()
                    .delete("/objects/" + originalProductID)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .response();

            String jsonResponse = response.asString();
            System.out.println("Response: " + jsonResponse);
            System.out.println("Object with id = " + originalProductID + " has been deleted.");
            assertTrue(jsonResponse.contains(originalProductID + " has been deleted."),"Output message is not as expected");

        }
}
