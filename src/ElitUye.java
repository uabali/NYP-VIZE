public class ElitUye extends Uye {
   // bu class Uye sınıfını extends ediyor
   private String unvan;

   public ElitUye(String isim, String soyisim, String email) {
      super(isim, soyisim, email);
      this.unvan = unvan;
      // burdaki this sınıfa referans veriyor
   }

   @Override
   public String getType() {
      return "Elit Üye";
   }
   // kodun düzgün çalışması için gerekli getType
   @Override
   public String toString() {
      return super.toString() + ", " + unvan;
   }
}
