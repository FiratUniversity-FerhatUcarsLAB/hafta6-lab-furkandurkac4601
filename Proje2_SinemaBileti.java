/**
 * Ad Soyad: [furkan durkaç]
 * Öğrenci No: [250541045]
 * Proje: Proje 2 - Sinema Bileti Fiyatlandırma Sistemi
 * Tarih: [20.11.2025]
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 2) Matine mi?
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;       // Hafta içi matine
        if (!weekend && !matinee) return 65;      // Hafta içi normal
        if (weekend && matinee) return 55;        // Hafta sonu matine
        return 85;                                // Hafta sonu normal
    }

    // 4) İndirim hesabı (yüzde döndürür)
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // Meslek: 1=Öğrenci, 2=Öğretmen, 3=Diğer
        double ind = 0;

        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) ind = 0.20; // Pzt–Prş %20
                else ind = 0.15;                     // Cum–Paz %15
                break;

            case 2: // Öğretmen
                if (gun == 3) ind = 0.35;            // Çarşamba %35
                break;

            default:
                break;
        }

        // Yaş indirimi
        if (yas >= 65)
            ind = Math.max(ind, 0.30);  // En yüksek uygula
        else if (yas < 12)
            ind = Math.max(ind, 0.25);

        return ind;
    }

    // 5) Film formatı ekstraları
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);

        double discounted = base - (base * discountRate);
        return discounted + extra;
    }

    // 7) Bilet bilgisi yazdırma
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        System.out.println("\n===== BİLET BİLGİSİ =====");
        System.out.printf("Temel Fiyat: %.2f TL\n", base);
        System.out.printf("İndirim Oranı: %.0f%%\n", discountRate * 100);
        System.out.printf("Format Ücreti: %.2f TL\n", extra);
        System.out.printf("Ödenecek Tutar: %.2f TL\n", finalPrice);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Gün seç (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        System.out.print("Saat gir (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        System.out.println("Meslek seç: 1=Öğrenci, 2=Öğretmen, 3=Diğer");
        int meslek = sc.nextInt();

        System.out.println("Film türü: 1=2D, 2=3D, 3=IMAX, 4=4DX");
        int filmTuru = sc.nextInt();

        generateTicketInfo(gun, saat, yas, meslek, filmTuru);
    }
}
