package testData;

import org.json.JSONObject;

import java.util.HashMap;
public class TestDataDummy {
    public int basariliStatusCode = 200;
    public String contentType = "application/json";

    /*
    {
    "status":"success",
    "data": {
            "id": 3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
    "message":"Successfully! Record has been fetched."
    }
     */

    //önce içerdeki JSON Object'i bir method'a atarız,
    //o method'u diğerinin içerisinden call ederiz
    public JSONObject innerJSONBody (){

        JSONObject innerBody = new JSONObject();

        innerBody.put("id", 3);
        innerBody.put("employee_name","Ashton Cox");
        innerBody.put("employee_salary",86000);
        innerBody.put("employee_age",66);
        innerBody.put("profile_image","");

        return innerBody;
    }
    public JSONObject expectedBodyOlusturJson (){

        JSONObject expBody = new JSONObject();

        expBody.put("status","success");
        expBody.put("message","Successfully! Record has been fetched.");
        expBody.put("data",innerJSONBody()); // yukarıda oluşturduğumuz innerbody method'u call ettik

        return expBody;
    }
    public HashMap innerBodyOlusturMap (){

        HashMap<String ,Object> innerBodyMap = new HashMap<>();

        innerBodyMap.put("id",3.0);
        innerBodyMap.put("employee_name","Ashton Cox");
        innerBodyMap.put("employee_salary",86000.0);
        innerBodyMap.put("employee_age",66.0);
        innerBodyMap.put("profile_image","");

        return innerBodyMap;
    }

    public HashMap expectedBodyOlusturMap(){

        HashMap<String ,Object> expBody = new HashMap<>();

        expBody.put("status","success");
        expBody.put("message","Successfully! Record has been fetched.");
        expBody.put("data",innerBodyOlusturMap());
                          //method'u yazarız

        return expBody;
    }
}
