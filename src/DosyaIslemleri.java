import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DosyaIslemleri {

   public static void uyeKaydet(Uye uye, String s) {
      String baslik = "";
      if (uye instanceof ElitUye) {
         baslik = "#ELIT ÜYE#";
      } else if (uye instanceof GenelUye) {
         baslik = "#GENEL ÜYE#";
      }

      try {
         BufferedReader reader = new BufferedReader(new FileReader(s));
         List<String> dosyaIcerigi = new ArrayList<>();
         String line;

         // Dosyayı tamamen okuyoruz
         while ((line = reader.readLine()) != null) {
            dosyaIcerigi.add(line);
         }

         reader.close();

         // Başlık var mı kontrol ediyoruz
         boolean baslikVarMi = false;
         int baslikIndeksi = -1;
         for (int i = 0; i < dosyaIcerigi.size(); i++) {
            String satir = dosyaIcerigi.get(i);
            if (satir.trim().equals(baslik)) {
               baslikVarMi = true;
               baslikIndeksi = i;
               break;
            }
         }

         // Başlık yoksa yeni bir tane oluşturuyoruz
         if (!baslikVarMi) {
            baslikIndeksi = dosyaIcerigi.size();
            dosyaIcerigi.add(baslik);
         }

         // Uye'yi dosyaya ekliyoruz
         dosyaIcerigi.add(baslikIndeksi + 1, uye.toString());

         // Dosyayı yeniden yazıyoruz
         BufferedWriter writer = new BufferedWriter(new FileWriter(s));
         for (String satir : dosyaIcerigi) {
            writer.write(satir + "\n");
         }

         writer.close();
         System.out.println("Üye kaydedildi.");
      } catch (IOException e) {
         System.out.println("Dosya yazma hatası: " + e.getMessage());
      }
   }





   public static void dosyayiTemizle() {
      try {
         FileWriter writer = new FileWriter("Kullanıcılar.txt");
         writer.write("");
         writer.close();
      } catch (IOException e) {
         System.out.println("Dosya yazma hatası: " + e.getMessage());
      }
   }

   public static List<Uye> uyeleriGetir(String dosyaAdi) {
      List<Uye> uyeler = new ArrayList<>();

      try (Scanner scanner = new Scanner(new File(dosyaAdi))) {
         String tip = "";
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("#ELIT ÜYE#")) {
               tip = "ELIT";
            } else if (line.startsWith("#GENEL ÜYE#")) {
               tip = "GENEL";
            } else {
               String[] satirParcalari = line.split(" ");
               if (satirParcalari.length >= 3) {
                  String isim = satirParcalari[0];
                  String soyisim = satirParcalari[1];
                  String email = satirParcalari[2];
                  if (tip.equals("ELIT")) {
                     uyeler.add(new ElitUye(isim, soyisim, email));
                  } else if (tip.equals("GENEL")) {
                     uyeler.add(new GenelUye(isim, soyisim, email));
                  }
               } else {
                  throw new IllegalArgumentException("Satırda yeterli bilgi yok: " + line);
               }
            }
         }
      } catch (FileNotFoundException e) {
         System.out.println("Dosya bulunamadı: " + dosyaAdi);
      }

      return uyeler;
   }




   public static void dosyayiYazdir() {
      try {
         File file = new File("Kullanıcılar.txt");
         Scanner scanner = new Scanner(file);
         String currentType = "";
         while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Uye uye = Uye.fromString(line);
            if (!uye.getType().equals(currentType)) {
               System.out.println("\n" + uye.getType() + " Üyeler:");
               currentType = uye.getType();
            }
            System.out.println(uye);
         }
         scanner.close();
      } catch (IOException e) {
         System.out.println("Dosya okuma hatası: " + e.getMessage());
      }
   }
}