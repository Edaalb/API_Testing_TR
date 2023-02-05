package test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class C05_Get_ResponseBodyTesti {

    //bu class itibariyle body'e ait bilgilerin assertion'ınını yapmaya başlarız
    //bu aşamaya kadar dönen response ile ilgili temel bilgileri sorguladık, content type vb.
    //artık dönen response body içerisindeki key-value değerlerini sorgulayacağız
    //bunun için Matchers class'ını kullanırız
    //bu sayede dönen response body içerisindeki değerleri kontrol edebiliriz

    /*
      https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
      donen Response’in
           status code'unun 200,
           ve content type'inin ContentType.JSON,
           ve response body'sinde bulunan userId'nin 5,
           ve response body'sinde bulunan title'in "optio dolor molestias sit"
           oldugunu test edin.
       */

    //GET request olduğu için body'e ihtiyacımız yoktur
    @Test
    public void get01(){

        // 1 - URL hazirla

        String url = "https://jsonplaceholder.typicode.com/posts/44";

        // 2 - Soruda isteniyorsa Expected Data hazirla

        //soruda response body içerisindeki bir değeri istiyor,
        //response body'i bütünüyle test etmemizi beklemiyor.

        // 3 - Response'i kaydet

        //response data tipinden bir response object'i oluştururuz
        //restassured'dan import edilecek
        //pre-condition olmadığından  when'e direk geçiş yapabiliriz
        //kullanacağımız http methodu get'i çağırıp içine yukarıda hazırladığımız url'i gömeriz
        Response response = given().when().get(url);
        //bunu çağırdığımızda talebimiz gidecek. response olarak kaydettik
        //ancak bunu bize direk olarak göstermez
        //görmek için prettyPrint ile yazdırmamız gerekir
        response.prettyPrint();

        // 4 - Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON). //application/json; charset=utf-8
                body("userId", Matchers.equalTo(5)).
                body("title",Matchers.equalTo("optio dolor molestias sit"));
    }  //Matchers yalnızca body içindir
}
