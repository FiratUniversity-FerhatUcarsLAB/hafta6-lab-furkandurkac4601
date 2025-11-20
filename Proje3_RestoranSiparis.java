/**
 * Ad Soyad: [furkan durkaç]
 * Öğrenci No: [250541045]
 * Proje: Proje 3 - Restoran Sipariş Sistemi
 * Tarih: [20.11.2025]
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Künefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo menü mü? (Ana + İçecek + Tatlı varsa)
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy hour (14:00–17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double ind = 0;

        // Combo menü: %15
        if (combo) ind += tutar * 0.15;

        // Happy hour → içeceklerde %20 (hesap main’de yapılacak)
        // Bu metot içecek indirimini değil toplam indirimleri toplar

        // 200 TL üzeri → %10
        if (tutar >= 200) ind += tutar * 0.10;

        // Hafta içi öğrenci indirimi → %10
        if (ogrenci) ind += tutar * 0.10;

        return ind;
    }

    // 8) Bahşiş hesaplama (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // -------------------------------------
    // MAIN METHOD
    // -------------------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== ANA YEMEKLER =====");
        System.out.println("1) Izgara Tavuk (85 TL)");
        System.out.println("2) Adana Kebap (120 TL)");
        System.out.println("3) Levrek (110 TL)");
        System.out.println("4) Mantı (65 TL)");
        System.out.print("Seçim (0=Alma): ");
        int anaSecim = sc.nextInt();

        System.out.println("\n===== BAŞLANGIÇLAR =====");
        System.out.println("1) Çorba (25 TL)");
        System.out.println("2) Humus (45 TL)");
        System.out.println("3) Sigara Böreği (55 TL)");
        System.out.print("Seçim (0=Alma): ");
        int baslangicSecim = sc.nextInt();

        System.out.println("\n===== İÇECEKLER =====");
        System.out.println("1) Kola (15 TL)");
        System.out.println("2) Ayran (12 TL)");
        System.out.println("3) Meyve Suyu (35 TL)");
        System.out.println("4) Limonata (25 TL)");
        System.out.print("Seçim (0=Alma): ");
        int icecekSecim = sc.nextInt();

        System.out.println("\n===== TATLILAR =====");
        System.out.println("1) Künefe (65 TL)");
        System.out.println("2) Baklava (55 TL)");
        System.out.println("3) Sütlaç (35 TL)");
        System.out.print("Seçim (0=Alma): ");
        int tatliSecim = sc.nextInt();

        System.out.print("\nSaat kaç? (0–23): ");
        int saat = sc.nextInt();

        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        boolean ogrenci = sc.nextInt() == 1;

        // -------------------------------------
        // Fiyatların alınması
        // -------------------------------------
        double anaF = getMainDishPrice(anaSecim);
        double basF = getAppetizerPrice(baslangicSecim);
        double icecekF = getDrinkPrice(icecekSecim);
        double tatliF = getDessertPrice(tatliSecim);

        // Happy hour içecek indirimi → %20
        if (icecekSecim != 0 && isHappyHour(saat)) {
            icecekF = icecekF * 0.80;
        }

        double araToplam = anaF + basF + icecekF + tatliF;

        // Combo kontrol
        boolean combo = isComboOrder(anaSecim != 0, icecekSecim != 0, tatliSecim != 0);

        // Genel indirimler
        double indirim = calculateDiscount(araToplam, combo, ogrenci, saat);

        double toplam = araToplam - indirim;
        double bahsis = calculateServiceTip(toplam);

        // -------------------------------------
        // ÇIKTI
        // -------------------------------------
        System.out.println("\n===== SİPARİŞ ÖZETİ =====");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("Toplam İndirim: %.2f TL\n", indirim);
        System.out.printf("Ödenecek Tutar: %.2f TL\n", toplam);
        System.out.printf("Bahşiş Önerisi (%%10): %.2f TL\n", bahsis);
    }
}
