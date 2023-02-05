package test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    //Jnit ile Hard Assert yaparız

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
    	                Request body
    	           {
    	                "firstname" : "Ahmet",
    	                "lastname" : “Bulut",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }
    	            	Response Body
    	           {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */
    @Test
    public void post01(){

        // 1 - URL ve body hazirla

        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject innerBody = new JSONObject();

        innerBody.put("checkin", "2021-06-01");
        innerBody.put("checkout", "2021-06-10");

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname" , "Ali");
        reqBody.put("lastname" , "Bak");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put("bookingdates" ,innerBody);
        reqBody.put("additionalneeds" , "wi-fi");

        // System.out.println("reqBody = " + reqBody);

        // 2 - Expected Data hazirla

        JSONObject expBody = new JSONObject();

        //bookingid doğrulamasını yapmayacağız, bu yüzden hangi değeri yazdığımızın önemi yok
       //ancak expected body oluştururken mutlaka key değerini gönderip ona bir value atamamız gerekir
        expBody.put("bookingid",24);
        expBody.put("booking",reqBody);
        //booking'in değeri request body'dir

        // System.out.println("expBody = " + expBody);

        // 3 - Response'i kaydet

        //post request'te body göndeririz, body varsa format bildirmek zorundayız
        Response response = given().
                contentType(ContentType.JSON). //pre-condition
                when().
                body(reqBody.toString()).
                post(url); //çalıştığında response kaydedilmiş olacak
        //reqBody --> bizim hazırladığımız
        //expBody --> ile ilgili işlemleri sadece Assertion'da yapacağız

        // System.out.println("response = ");
        // response.prettyPrint();

        // 4 - Assertion

        //ilk etapta JSON response'ı JSONPath'e dönüştürürüz
        JsonPath resJsonPath = response.jsonPath();

        //Java JSON objectlerine bir bütün olarak bakar, path'ini vermiş olsak bile içerisine direk olarak giremeyeiz
        //bu yüzden getJSONObject kullanırız
        assertEquals(expBody.getJSONObject("booking").get("firstname"),resJsonPath.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),resJsonPath.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),resJsonPath.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),resJsonPath.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),resJsonPath.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                resJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                resJsonPath.get("booking.bookingdates.checkout"));
    }
}
