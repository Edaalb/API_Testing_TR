package baseURL;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    protected RequestSpecification specJsonPlace;
    //access modifers objectimizin ulaşabileceği scope'u belirler
    //protected yaptığımızda kardeş package'ların altındaki class'lardan da
    //inheritence methodları ile ulaşılabilir
    @Before
    public void setUp(){

        specJsonPlace = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com").
                build();
    }
}
