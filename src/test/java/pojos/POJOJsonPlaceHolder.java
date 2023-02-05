package pojos;

public class POJOJsonPlaceHolder {

    //pojo'da bir kalıp oluştururuz, sonra o kalıp üzerine istediğimiz şeyi ekleriz
    //bir şablon gibidir, istediğimiz objecti üzerinden elde ederiz

    /*
    {
    "title":"Ahmet",
    "body":"Merhaba",
    "userId":10,
    "id":70
    }
     */

    // 1. Adim : Objemizin icerisinde varolan tum Key degerlerini private Variable olarak tanimlayalim:

    private String title;
    private String body;
    private int id;
    private int userId;


    // 2. Adim : Getter ve Setter hazirla

    //sağ tık - generate - Getter Setter - hepsini seç
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // 3. Adim : Tum parametreleri iceren Constructor create et

     // Generate - Constructor - tüm parametreleri seç
    public POJOJsonPlaceHolder(String title, String body, int id, int userId) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.userId = userId;
    }

    // 4. Adim : Parametresiz Constructor create et
    public POJOJsonPlaceHolder() { //generate - constructor - select none
    }

    // 5. Adim : toString() metodu create et
    @Override
    public String toString() {
        return "POJOJsonPlaceHolder{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                '}';
    }
}
