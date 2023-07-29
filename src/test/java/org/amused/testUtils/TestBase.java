package org.amused.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.amused.common.Constants;
import org.amused.model.Data;
import org.amused.model.ProductData;
import org.amused.utils.JsonToObjectJacsonUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TestBase {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = Constants.BASE_PATH;

    }

    public ProductData createNewSingleDataRecord() {
        ProductData productData = new ProductData();
        productData.setName("TestData_Dell XPS 13");

        Data data = new Data();
        data.setColor("TestData_Mercury Silver");
        data.setCapacity("999 GB");
        data.setYear("2021");
        data.setPrice(9999.9);
        data.setCPUModel("Intel Core i7");
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


        assertEquals(200, response.statusCode());
        String jsonResponse = response.asString();

        ProductData product = JsonToObjectJacsonUtil.fromJson(jsonResponse, ProductData.class);
        return product;
    }

    @AfterMethod
    public void extendReporting(ITestResult result)
    {
        if(result.getStatus()== ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus()==ITestResult.SUCCESS) {
            extentTest.pass(result.getThrowable());
        }
        else if (result.getStatus()==ITestResult.SKIP) {
            extentTest.skip(result.getThrowable());
        }
        else {
            extentTest.fail(result.getThrowable());
        }


    }


    @BeforeSuite
    public void createReport()
    {
        LocalDate dateTime = LocalDate.now();
        extentReports = new ExtentReports();
        extentReports.attachReporter(new ExtentSparkReporter("src/test/results/TestResultReport_"+dateTime+".html"));

    }

    @AfterSuite
    public void closeReport()
    {
        extentReports.flush();
    }
}
