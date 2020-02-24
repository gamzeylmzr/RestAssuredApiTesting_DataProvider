import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test2_Post_Request {
    @Test
    public void successfulRegistration(){

        //Specify Base Url
        RestAssured.baseURI="http://restapi.demoqa.com/customer";

        //Request object
        RequestSpecification httpRequest=RestAssured.given();

        //Request payload sending along with post request
        JSONObject requestParams=new JSONObject();
        requestParams.put("FirstName","Gamze");
        requestParams.put("LastName","Y");
        requestParams.put("UserName","Gseaya");
        requestParams.put("Password","12345");
        requestParams.put("Email","gy@havelsan.com.tr");

        httpRequest.header("Content type is","application/json");

        httpRequest.body(requestParams.toJSONString()); // attach above data to request

        //Response object
        Response response=httpRequest.request(Method.POST,"/register");


        //Print response in console
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);

        //status code validation
        int statusCode=response.getStatusCode();
        System.out.println("Status code is: "+statusCode);
        Assert.assertEquals(statusCode,201);

        //success code validation
        String successCode=response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");

    }

}
