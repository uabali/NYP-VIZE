import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailGonderici {
   final String mailGonderen = "umutabali63@gmail.com";
   final String mailGonderenSifre = "yqbhtynuzlqudjwx";

   public void tumUyelereMailGonder(String konu, String icerik, ArrayList<Uye> uyeler) {
      ArrayList<String> tumMailler = new ArrayList<>();
      for (Uye uye : uyeler) {
         tumMailler.add(uye.getEmail());
      }
      mailGonder(konu, icerik, tumMailler);
   }

   public void elitUyelereMailGonder(String konu, String icerik, ArrayList<Uye> uyeler) {
      ArrayList<String> elitMailler = new ArrayList<>();
      for (Uye uye : uyeler) {
         if (uye instanceof ElitUye) {
            elitMailler.add(uye.getEmail());
         }
      }
      mailGonder(konu, icerik, elitMailler);
   }

   public void genelUyelereMailGonder(String konu, String icerik, ArrayList<Uye> uyeler) {
      ArrayList<String> genelMailler = new ArrayList<>();
      for (Uye uye : uyeler) {
         if (uye instanceof GenelUye) {
            genelMailler.add(uye.getEmail());
         }
      }
      mailGonder(konu, icerik, genelMailler);
   }

   private void mailGonder(String konu, String icerik, ArrayList<String> aliciMailler) {
      Properties mailAyarlari = new Properties();
      mailAyarlari.put("mail.smtp.auth", "true");
      mailAyarlari.put("mail.smtp.starttls.enable", "true");
      mailAyarlari.put("mail.smtp.host", "smtp.gmail.com");
      mailAyarlari.put("mail.smtp.port", "587");

      Session oturum = Session.getInstance(mailAyarlari, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(mailGonderen, mailGonderenSifre);
         }
      });

      try {
         MimeMessage mesaj = new MimeMessage(oturum);
         mesaj.setFrom(new InternetAddress(mailGonderen));
         for (String aliciMail : aliciMailler) {
            if (aliciMail != null && !aliciMail.trim().isEmpty()) {
               mesaj.addRecipient(Message.RecipientType.TO, new InternetAddress(aliciMail));
            }
         }
         mesaj.setSubject(konu);
         mesaj.setText(icerik);
         Transport.send(mesaj);
         System.out.println("Mail başarıyla gönderildi.");
      } catch (MessagingException e) {
         System.out.println("Mail gönderimi sırasında bir hata oluştu: " + e.getMessage());
      }
   }

}
