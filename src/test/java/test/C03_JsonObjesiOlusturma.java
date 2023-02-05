package test;

import org.json.JSONObject;
import org.junit.Test;

public class C03_JsonObjesiOlusturma {
    /*
 Asagidaki JSON Objesini olusturup konsolda yazdirin.
 {
     "title":"Ahmet",
     "body":"Merhaba",
     "userId":1
 }
 */
    @Test
    public void jsonObje01(){
        //Temel bir JSON Objesi oluşturmak için pom.xml'e eklediğimiz JSON isimli dependency
        //aracılığıyla Java'da JSON object tarzında dataları hafızada tutabiliriz

            //dataları Json Object tarzında tutabiliriz.
        JSONObject ilkJsonObje = new JSONObject();
         //map'teki gibi key-value ikililerini ekleyebiliriz
        ilkJsonObje.put("title","Ahmet");
        ilkJsonObje.put("body","Merhaba");
        ilkJsonObje.put("userId",1);

        System.out.println(ilkJsonObje); //direk yazdırılabilir.
    }

    @Test
    public void jsonObje02(){

        /*
                {
                 "firstname":"Jim",
                 "additionalneeds":"Breakfast",
                 "bookingdates":{
                         "checkin":"2018-01-01",
                         "checkout":"2019-01-01"
                    },
                  "totalprice":111,
                  "depositpaid":true,
                  "lastname":"Brown"
                  }
         */

        JSONObject innerJsonObje = new JSONObject();
        //iç içe Json Objeleri
        innerJsonObje.put("checkin","2018-01-01");
        innerJsonObje.put("checkout","2019-01-01");

        JSONObject body = new JSONObject();
        //Json objeleri key olarak sadece String kabul eder
        //value olarak String, int, boolean, Object kabul edebilir
        body.put("firstname","Jim");
        body.put("additionalneeds","Breakfast");
        body.put("bookingdates",innerJsonObje);
        body.put("totalprice",111);
        body.put("depositpaid",true);
        body.put("lastname","Brown");

        System.out.println(body);

    }
}
