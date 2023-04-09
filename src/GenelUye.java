public class GenelUye extends Uye {
   // bu class Uye sınıfını extends ediyor
   private String mahlas;

   public GenelUye(String isim, String soyisim, String email) {
      super(isim, soyisim, email);
      this.mahlas = mahlas;
   }

   @Override
   public String getType() {
      return "Genel Üye";
   }
   // kodun düzgün çalışması için gerekli getType

   @Override
   public String toString() {
      return super.toString() + ", " + mahlas;
   }
}
