package com.qainfotech.com.RestAssuredJSONTesting;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;

public class RestAssuredJson {
	 GetRequest getRequest;
	 Response response;
	 List<String> jsonResponse;
	 Response response1;
	 List<String> jsonResponse1;
	 String check,check1;

	 @Test
  public void isBothJsonFileParsed() {
    	 RestAssured.defaultParser = Parser.JSON;
       
      //first Json file
       response = getRequest.doGetRequest("https://jsonplaceholder.typicode.com/users");
       jsonResponse = response.jsonPath().getList("$");
       
       //Second Json file
       response1 = getRequest.doGetRequest("https://jsonplaceholder.typicode.com/todos");
       jsonResponse1 = response1.jsonPath().getList("$");
       check=jsonResponse.toString();
       check1=jsonResponse1.toString();
       Assert.assertFalse(jsonResponse1.isEmpty());
    }
	
  @Test(dependsOnMethods= {"isBothJsonFileParsed"})
  public void isIDAndUserIDSame() {
	  for(int i=1;i<10;i++)
	Assert.assertTrue(check.contains("id="+i)==check1.contains("id="+i));
  }

  @Test(dependsOnMethods= {"isBothJsonFileParsed"})
  public void isSizeCompareable() {
	   Assert.assertTrue((jsonResponse1.size()/20)==jsonResponse.size());
  }
  
  
  
}
