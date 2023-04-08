public class ElitUye extends Uye {
   private String unvan;

   public ElitUye(String isim, String soyisim, String email) {
      super(isim, soyisim, email);
      this.unvan = unvan;
   }

   @Override
   public String getType() {
      return "Elit Üye";
   }

   @Override
   public String toString() {
      return super.toString() + ", " + unvan;
   }
}
