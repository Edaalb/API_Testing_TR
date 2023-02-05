package test;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class C22_Put_DeSerialization extends JsonPlaceHolderBaseUrl{

       //Map'i JSON'a çevirdiğimizde Serialization,
       //JSON'ı map'e çevirdiğimizde De-Serialization

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
        body’e sahip bir PUT request yolladigimizda donen response’in
        response body’sinin asagida verilen ile ayni oldugunu test ediniz
        Request Body
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
        Expected Data :
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
         */
    @Test
    public void put01(){

        // 1 - URL ve Body hazirla

        specJsonPlace.pathParams("pp1","posts","pp2",70);

        //object üzerinden testdata class'ında hazırladığımız method'u çağırırmak için
        //bir object oluştururuz
        TestDataJsonPlaceHolder testDataJsonPlaceHolder = new TestDataJsonPlaceHolder();

        //yukarıda oluşturduğumuz object üzerinden methodu çağırırız
        //bir işlem yapabilemek için bunu kaydederiz
        //kaydettiğimiz variable'ın data tipi method bize bir map döndürdüğü için map olmalıdır
        HashMap<String,Object> reqBody = testDataJsonPlaceHolder.requestBodyOlusturMap();

        System.out.println("reqBody map = " + reqBody);

        // 2 - Expected Data hazirla

        HashMap<String,Object> expDataMap = testDataJsonPlaceHolder.requestBodyOlusturMap();

        // 3 - Response'i kaydet

        //işlemimizi Java üzerinden yapıyor olsak da, çalışacak olan api'nin data türü
        //belirlenmiş olan 4 türün dışına çıkamayız. bu yüzden yine JSON kullanırız
        //bilgileri map'e koymuş olsak da götürürken json götürecek
        Response response = given().
                spec(specJsonPlace).
                contentType(ContentType.JSON).
                when().
                body(reqBody).
                put("/{pp1}/{pp2}");
        //öncesinde toString'e çeviriyorduk çünkü JSONObject Java'ya ait bir format değil
        //ancak Map Java'ya ait bir format olduğu için çevirmek zorunda değiliz

        response.prettyPrint();

        // 4 - Assertion

        //önceki sorgularımızda JSONPath'e dönüştürüyorduk, şimdi HashMap'e dönüştüreceğiz

        // Not : Bizim hazirlamis oldugumuz Expected Data Map formatinda.
        // Bize response'dan donen Response Body ise Json formatinda
        // Ikisini Assert methodlari icerisinde kiyaslayabilmemiz icin oncelikle
        // response'i map formatina parse etmemiz gerekiyor.


        HashMap<String,Object> respMap = response.as(HashMap.class);

        Assert.assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(expDataMap.get("title"),respMap.get("title"));
        Assert.assertEquals(expDataMap.get("body"),respMap.get("body"));
        Assert.assertEquals(expDataMap.get("id"),respMap.get("id"));
        Assert.assertEquals(expDataMap.get("userId"),respMap.get("userId"));
    }
}
