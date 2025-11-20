/**
 * Ad Soyad: [furkan durkaç]
 * Öğrenci No: [250541045]
 * Proje: Proje 1 - Öğrenci Not Sistemi
 * Tarih: [20.11.2025]
 */

import java.util.Scanner;

public class Proje1_NotSistemi {

    // 1) Ortalama Hesaplama
    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    // 2) Geçme Kalma
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // 3) Harf Notu
    public static char getLetterGrade(double ort) {
        if (ort >= 90) return 'A';
        else if (ort >= 80) return 'B';
        else if (ort >= 70) return 'C';
        else if (ort >= 60) return 'D';
        else return 'F';
    }

    // 4) Onur listesi (ort ≥ 85 VE tüm notlar ≥ 70)
    public static boolean isHonorList(double ort, double vize, double fin, double odev) {
        return (ort >= 85) && (vize >= 70 && fin >= 70 && odev >= 70);
    }

    // 5) Bütünleme hakkı (40 ≤ ort < 50)
    public static boolean hasRetakeRight(double ort) {
        return (ort >= 40 && ort < 50);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Vize notu: ");
        double vize = sc.nextDouble();

        System.out.print("Final notu: ");
        double fin = sc.nextDouble();

        System.out.print("Ödev notu: ");
        double odev = sc.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        char harf = getLetterGrade(ort);
        boolean gecti = isPassingGrade(ort);
        boolean onur = isHonorList(ort, vize, fin, odev);
        boolean butunleme = hasRetakeRight(ort);

        System.out.println("\n===== SONUÇLAR =====");
        System.out.printf("Ortalama: %.2f\n", ort);
        System.out.println("Harf Notu: " + harf);

        if (gecti)
            System.out.println("Durum: GEÇTİ");
        else
            System.out.println("Durum: KALDI");

        System.out.println("Onur Listesi: " + (onur ? "EVET" : "HAYIR"));
        System.out.println("Bütünleme Hakkı: " + (butunleme ? "EVET" : "HAYIR"));
    }
}
