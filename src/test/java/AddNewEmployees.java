import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.XLUtility;
import utils.XLUtility_972003;

import java.io.IOException;

public class AddNewEmployees {

    @Test(dataProvider = "empDataProviderByExcel")
    void postNewEmployees(String ename,String esalary,String eage){

        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
        RequestSpecification httpRequest=RestAssured.given();

        JSONObject requestParams=new JSONObject();
        requestParams.put("name",ename);
        requestParams.put("salary",esalary);
        requestParams.put("age",eage);

        httpRequest.header("Content-Type","application/json");

        //Add to JSON body to request
        httpRequest.body(requestParams.toJSONString());
        Response response=httpRequest.request(Method.POST,"/create");

        String responseBody=response.getBody().asString();
        System.out.println("Response:"+responseBody);
        Assert.assertEquals(responseBody.contains(ename),true);
    }

    @DataProvider(name="empDataProvider")
    Object [][] getEmployeeData(){
        String employeeData[][]={{"Gamze","8000","29"},{"Umut","4000","21"},{"Yong","8000","31"}};
        return employeeData;
    }

    @DataProvider(name="empDataProviderByExcel97")
    Object [][] getEmployeeDataByExcel97() throws IOException{

        //read data from excel
        String path=System.getProperty("user.dir")+"/src/test/java/utils/Kitap2.xls";

        int rows= XLUtility_972003.getRowCount(path,"Sayfa1");
        int colcount= XLUtility_972003.getCellCount(path,"Sayfa1",1);


        String employeeData[][]=new String[rows][colcount];

        for(int i=1;i<=rows;i++){
            for(int j=0;j<colcount;j++){
                employeeData[i-1][j]= XLUtility_972003.getCellData(path,"Sayfa1",i,j);
            }
        }
        return employeeData;
    }

    @DataProvider(name="empDataProviderByExcel")
    Object [][] getEmployeeDataByExcel() throws IOException{

        //read data from excel
        String path=System.getProperty("user.dir")+"/src/test/java/utils/Kitap1.xlsx";

        int rows= XLUtility.getRowCount(path,"Sayfa1");
        int colcount= XLUtility.getCellCount(path,"Sayfa1",1);


        String employeeData[][]=new String[rows][colcount];

        for(int i=1;i<=rows;i++){
            for(int j=0;j<colcount;j++){
                employeeData[i-1][j]= XLUtility.getCellData(path,"Sayfa1",i,j);
            }
        }
        return employeeData;
    }
}
