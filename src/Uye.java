public class Uye {
   private String isim;
   private String soyisim;
   private String email;

   // counstractırlarımız
   public Uye(String isim, String soyisim, String email) {
      this.isim = isim;
      this.soyisim = soyisim;
      this.email = email;
   }

   @Override
   public String toString() {
      return isim + " " + soyisim + " " + email;
   }

   // fromString methodu sayesinde dosyadan -
   // isim soyisim ve mail alabiliyoruz
   public static Uye fromString(String line) {
      String[] satirParcalari = line.split(" ");

      if (satirParcalari[0].equals("#ELIT ÜYE#")) {
         String isim = satirParcalari[2];
         String soyisim = satirParcalari[3];
         String email = satirParcalari[4];
         return new ElitUye(isim, soyisim, email);
      } else if (satirParcalari[0].equals("#GENEL ÜYE#")) {
         String isim = satirParcalari[1];
         String soyisim = satirParcalari[2];
         String email = satirParcalari[3];
         return new GenelUye(isim, soyisim, email);
      } else {
         throw new IllegalArgumentException("Satırda yeterli bilgi yok: " + line);
      }
   }
   public String getIsim() {
      return isim;
   }
   // bu kodu hata için öneri seçeneğini seçip edindim
   public String getSoyisim() {
      return soyisim;
   }
   // bu kodu hata için öneri seçeneğini seçip edindim
   public String getEmail() {
      return email;
   }
   // bu kodu hata için öneri seçeneğini seçip edindim
   public String getType() {
      return null;
   }
}
// bu kodu hata için öneri seçeneğini seçip edindim