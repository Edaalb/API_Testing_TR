package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// notasyonları yerleştiririz
@Data //buraya tanımladığımız variable'ların getter, setter, toString'ini hazırlar
@NoArgsConstructor //parametresiz constructor oluşturur
@AllArgsConstructor //bütün argümanları içeren constructor oluşturur
//daha önce bunları generate'den yapıyorduk

public class PojoHerokuappBookingDates {
    /*
  {
     "checkin":"2021-06-01",
     "checkout":"2021-06-10"
   }
   */

    //yalnızca data tiplerine uygun variable'ları
    private String checkin;
    private String checkout;
}
