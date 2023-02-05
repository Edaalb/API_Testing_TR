package test;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
public class C01_Get_ApiSorgulama {
    /*
        https://restful-booker.herokuapp.com/booking/9856 url’ine
        bir GET request gonderdigimizde donen Response’un,
        status code’unun 200,
        ve content type’inin application/json; charset=utf-8,
        ve Server isimli Header’in degerinin Cowboy,
        ve status Line’in HTTP/1.1 200 OK
        ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz.
*/
    // 1 - Gonderecegimiz Request icin gerekli olan URL ve ihtiyacimiz varsa Body hazirla
    // 2 - Eger soruda bize verilmisse Expected Data hazirla
    // 3 - Bize donen Response'i Actual Data olarak kaydet
    // 4 - Expected Data ile Actual datanin karsilastirmasi - Assertion
    @Test
    public void get01(){

        // 1 - Gonderecegimiz Request icin gerekli olan URL ve ihtiyacimiz varsa Body hazirla

        String url = "https://restful-booker.herokuapp.com/booking/1840";

        // 2 - Eger soruda bize verilmisse Expected Data hazirla

        // 3 - Bize donen Response'i Actual Data olarak kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        System.out.println("Status Code : " +response.getStatusCode());
        System.out.println("Content Type : " +response.getContentType());
        System.out.println("Server Header'inin Degeri : " +response.getHeader("Server"));
        System.out.println("Status Line : " +response.getStatusLine());
        System.out.println("Response Suresi : " +response.getTime());

        // 4 - Expected Data ile Actual datanin karsilastirmasi - Assertion

        //response'a dair temel bilgileri çağırıp yazdırarak dönen sonuçları gözümüzle manuel olarak
        //test ettik
    }

    //bu class'ta manuel bir test yaptık. API sorgusunu buradan çalıştırmış olmak ile Postman
    //üzerinden çalıştırmış olmak arasında teknik olarak bir fark yoktur
    //postman yerine pom.xml'imize yüklediğimiz rest-assured kütüphanesi ile sorguyu Intelij
    //üzerinden yapmış olduk

}
