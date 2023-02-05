package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; // * ile tüm matchers methodları
public class C06_Post_ResponseBodyTesti {

     /*  https://jsonplaceholder.typicode.com/posts
         url’ine asagidaki body ile bir POST request gonderdigimizde
        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }

        donen Response’un,
        status code’unun 201,
        ve content type’inin application/json
        ve Response Body'sindeki,
        "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini
        test edin.
      */

    @Test
    public void post01(){

        // 1 - URL ve Body hazirla

        String url = "https://jsonplaceholder.typicode.com/posts";

        //post olduğu için body'e ihtiyaç vardır
        JSONObject reqBody = new JSONObject();

        reqBody.put( "title","API");
        reqBody.put( "body","API ogrenmek ne guzel");
        reqBody.put( "userId",10);

        System.out.println(reqBody);

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        //post method'u kullanacağımız için body göndermemiz gerekiyor
        //body varsa pre-condition vardır
        //data formatımızı söylemek zorundayız
        Response response = given().
                contentType(ContentType.JSON).
                when().//başka pre-condtion olmadığ için when ile devam ederiz
                body(reqBody.toString()).//body methodu içerisine hazırladığımız reqBody'i koyarız
                post(url); //post method'unu çağırıp içine url koyup göndeririz

        response.prettyPrint(); //response'ın dönen body'sini yazdırırız

        //herzaman gönderdiğimiz body'nin aynısı dönmez
        //biz title, body gönderdik o bize id de döndürdü, gönderdiğimiz id'e atadı
        //her request'e bir farklı bir id verir
        //ancak placeholder sitesi bir uyarıda bulunur -->
        //biz bir post ya da put request'te bulunduğumuzda olmuş gibi gösterir
        //ancak bunu server'da değiştirmez, fake'dir.

        // 4 - Assertion
        response.
                then().
                assertThat().
                statusCode(201).
                contentType("application/json").
                body("title", equalTo("API")).
                body("userId",lessThan(100)).
                body("body",Matchers.containsString("API"));

        //yazdırma komutları sisteme yüktür, bu yüzden ilk çalıştırmadan sonra silinmesi gerekir
    }

    @Test
    public void post02(){

        // 1 - URL ve Body hazirla

        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject reqBody = new JSONObject();

        reqBody.put( "title","API");
        reqBody.put( "body","API ogrenmek ne guzel");
        reqBody.put( "userId",10);

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post(url);

        // 4 - Assertion

        response.
                then().
                assertThat().
                statusCode(201).
                contentType("application/json").
                body("title", equalTo("API"),
                        "userId", lessThan(100),
                        "body", containsString("API"));

        //Test01'den farklı olarak her seferinde tek tek body yazmak yerine
        //aynı body içerisinde tek seferde assertion yaptık

        //herbirinin başına tek tek matchers yazmak zorunda değiliz, matchers'dan import edebiliriz
        //ayrıca equalTo ve lessThan şeklinde ayrı ayrı import etmek yerine
        //import static org.hamcrest.Matchers.*; kullanmamız yeterlidir
        //böylece matchers class'ının sağlamış olduğu bütün methodları getirir

    }
}
