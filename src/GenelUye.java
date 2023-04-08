public class GenelUye extends Uye {
   private String nick;

   public GenelUye(String isim, String soyisim, String email) {
      super(isim, soyisim, email);
      this.nick = nick;
   }

   @Override
   public String getType() {
      return "Genel Üye";
   }

   @Override
   public String toString() {
      return super.toString() + ", " + nick;
   }
}
