package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C04_Put_ResponseBilgileriAssertion {
     /*
        https://jsonplaceholder.typicode.com/posts/70 url’ine asagidaki
        Json formatindaki body ile bir PUT request gonderdigimizde
                {
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
        donen Response’un,
            status code’unun 200,
            ve content type’inin application/json; charset=utf-8,
            ve Server isimli Header’in degerinin cloudflare,
            ve status Line’in HTTP/1.1 200 OK
      */

    @Test
    public void put01(){

        //get sorgularında body göndermeye ihtiyaç yoktur
        //3 p'de body'e ihtiyaç vardır

        // 1 - Request URL ve Body hazirla

        String url = "https://jsonplaceholder.typicode.com/posts/70";

        /*
        {  Bu bilgiler IPA dökümanında bize verilmiş olmalıdır
                "title":"Ahmet",
                "body":"Merhaba",
                "userId":10,
                "id":70
                }
         */

        //request body'i hazırlarız
        JSONObject reqBody = new JSONObject();
        //oluşturduğumuz Json Object'e değerleri atarız
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        System.out.println(reqBody);

        // 2 - Soruda istendiyse Expected Data hazirla

        // 3 - Response'i kaydet

        //given'dan sonra varsa ve yapılması gerekiyorsa pre-conditions yazılır
        //body gönderdiğimiz herhangi bir durumda pre conditions
        //olarak formatımızı belirlememiz gerekir.
        Response response = given().
                contentType(ContentType.JSON).//content type Json şeklinde belirtiriz
                when().
                body(reqBody.toString()). //hazırladığımız request body'i body methodu ile toString'e çevirip göndeririz
                put(url); //request'imiz put methodu ile çalıştığından url'i put ile göndeririz
        response.prettyPrint();//yazdırırız

        // 4 - Assertion

        response.
                then(). //then ile assertion'lar yapılır
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                header("Server","cloudflare").
                statusLine("HTTP/1.1 200 OK");
        //yerleri önemli değil, farklı sıralama ile yazıldığında sorun çıkmaz

    }

    //Java direk olarak JSONObjecti tanımaz, böyle bir data tipi yoktur.
    //bunu JSON dependency yardımıyla hazırlayabiliriz. Bu yüzden toString olmadan göndermez
    //Map olarak hazırlandığında Java argümanı olduğu için toString'e ihtiyaç yoktur.
}
