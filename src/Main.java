import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MailGonderici mailGonderici = new MailGonderici();
        //bu sınıfı kullanacağım için burdan bir obje oluşturdum

        while (true) {
            System.out.println("1- Elit üye ekleme");
            System.out.println("2- Genel üye ekleme");
            System.out.println("3- Mail gönderme");
            // kullanıcıdan yapacağı işlemleri yazdım

            int secim = scanner.nextInt();
            // burdan seçimini bir integer olarak alıp casede yapacağı işleme yönlendiriyorum.

            switch (secim) {
                case 1:
                    System.out.print("İsim: ");
                    String elitUyeIsim = scanner.next();
                    System.out.print("Soyisim: ");
                    String elitUyeSoyisim = scanner.next();
                    System.out.print("Email: ");
                    String elitUyeEmail = scanner.next();
                    // BURDA BİLGİLERİ ALIYORUM
                    DosyaIslemleri.uyeKaydet(new ElitUye(elitUyeIsim, elitUyeSoyisim, elitUyeEmail), "uyeler.txt");
                    // burdan aldığım bilgileri DosyaIslemleri classının uyeKaydet methoduna yönlendiriyorum.
                    // uyeler.txt dosyasında kayıt işlemi gerçekleşiyor.
                    System.out.println("Elit üye kaydedildi.");
                    break;

                case 2:
                    System.out.print("İsim: ");
                    String genelUyeIsim = scanner.next();
                    System.out.print("Soyisim: ");
                    String genelUyeSoyisim = scanner.next();
                    System.out.print("Email: ");
                    String genelUyeEmail = scanner.next();
                    // BURDA BİLGİLERİ ALIYORUM
                    DosyaIslemleri.uyeKaydet(new GenelUye(genelUyeIsim, genelUyeSoyisim, genelUyeEmail), "uyeler.txt");
                    // burdan aldığım bilgileri DosyaIslemleri classının uyeKaydet methoduna yönlendiriyorum.
                    // uyeler.txt dosyasında kayıt işlemi gerçekleşiyor.
                    System.out.println("Genel üye kaydedildi.");
                    break;

                case 3:
                    System.out.println("1- Tüm üyelere mail gönder");
                    System.out.println("2- Elit üyelere mail gönder");
                    System.out.println("3- Genel üyelere mail gönder");
                    //burda hangi üyelere yada tüm üyelere mail gönderme seçeneğini sundum
                    int mailSecim = scanner.nextInt();
                    // burdan seçimini bir integer olarak alıp if-else bloğunda yapacağı işleme yönlendiriyorum.
                    scanner.nextLine();
                    System.out.print("Mail konusu: ");
                    String mailKonusu = scanner.nextLine();
                    //kullanıcı göndereceği mailinin konusunu yazmasını istedim
                    System.out.print("Mail metni: ");
                    //kullanıcı göndereceği mailinin textini yazmasını istedim
                    String mailMetni = scanner.nextLine();

                    ArrayList<Uye> uyeler = (ArrayList<Uye>) DosyaIslemleri.uyeleriGetir("uyeler.txt");

                    if (mailSecim == 1) {
                        mailGonderici.tumUyelereMailGonder(mailKonusu, mailMetni, uyeler);
                    } else if (mailSecim == 2) {
                        mailGonderici.elitUyelereMailGonder(mailKonusu, mailMetni, uyeler);
                    } else if (mailSecim == 3) {
                        mailGonderici.genelUyelereMailGonder(mailKonusu, mailMetni, uyeler);
                    } else {
                        System.out.println("Geçersiz seçim.");
                    }

                    break;

                default:
                    System.out.println("Geçersiz seçim.");
                    break;
            }
        }
    }
}