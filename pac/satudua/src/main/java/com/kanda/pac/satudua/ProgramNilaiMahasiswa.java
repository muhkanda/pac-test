package com.kanda.pac.satudua;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Muh Kanda <surel.muhkanda@gmail.com>
 */

public class ProgramNilaiMahasiswa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input jumlah mahasiswa
        System.out.print("Masukkan Jumlah Mahasiswa: ");
        int jumlahMahasiswa = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> nimList = new ArrayList<>();
        ArrayList<String> namaList = new ArrayList<>();
        ArrayList<Double> nilaiKehadiranList = new ArrayList<>();
        ArrayList<Double> nilaiMidtestList = new ArrayList<>();
        ArrayList<Double> nilaiUASList = new ArrayList<>();

        // Input data mahasiswa
        for (int i = 1; i <= jumlahMahasiswa; i++) {
            System.out.println("\nMahasiswa ke-" + i + ":");
            System.out.print("Masukkan NIM: ");
            nimList.add(scanner.nextLine());

            System.out.print("Masukkan Nama: ");
            namaList.add(scanner.nextLine());

            System.out.print("Masukkan Nilai Kehadiran: ");
            nilaiKehadiranList.add(scanner.nextDouble());

            System.out.print("Masukkan Nilai Midtest: ");
            nilaiMidtestList.add(scanner.nextDouble());

            System.out.print("Masukkan Nilai UAS: ");
            nilaiUASList.add(scanner.nextDouble());

            scanner.nextLine();
        }

        // Output header
        System.out.println("\n================================================================");
        System.out.printf("%-4s%-11s%-8s%-12s%s%n", "No.", "NIM", "Nama", "Nilai Akhir", "Grade");
        System.out.println("================================================================");

        // Hitung dan tampilkan nilai akhir
        int jumlahLulus = 0;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            double nilaiAkhir = hitungNilaiAkhir(nilaiKehadiranList.get(i), nilaiMidtestList.get(i), nilaiUASList.get(i));
            String grade = getGrade(nilaiAkhir);
            System.out.printf("%-4d%-11s%-8s%-12.2f%s%n", i + 1, nimList.get(i), namaList.get(i), nilaiAkhir, grade);

            // Menghitung jumlah mahasiswa yang lulus
            if (grade.equals("A") || grade.equals("B") || grade.equals("C")) {
                jumlahLulus++;
            }
        }

        // Output footer
        System.out.println("================================================================");
        System.out.println("Jumlah Mahasiswa : " + jumlahMahasiswa);
        System.out.println("Jumlah Mahasiswa yang Lulus : " + jumlahLulus);
        System.out.println("Jumlah Mahasiswa yang Tidak Lulus : " + (jumlahMahasiswa - jumlahLulus));
        System.out.println("================================================================");

        // Simpan hasil ke dalam file
        simpanKeFile(nimList, namaList, nilaiKehadiranList, nilaiMidtestList, nilaiUASList);

        // Tutup scanner
        scanner.close();
    }

    // Fungsi untuk menghitung nilai akhir
    private static double hitungNilaiAkhir(double nilaiKehadiran, double nilaiMidtest, double nilaiUAS) {
        return (0.2 * nilaiKehadiran) + (0.4 * nilaiMidtest) + (0.4 * nilaiUAS);
    }

    // Fungsi untuk mendapatkan grade berdasarkan nilai akhir
    private static String getGrade(double nilaiAkhir) {
        if (nilaiAkhir >= 85 && nilaiAkhir <= 100) {
            return "A";
        } else if (nilaiAkhir >= 76 && nilaiAkhir < 85) {
            return "B";
        } else if (nilaiAkhir >= 61 && nilaiAkhir < 76) {
            return "C";
        } else if (nilaiAkhir >= 46 && nilaiAkhir < 61) {
            return "D";
        } else {
            return "E";
        }
    }

    // Fungsi untuk menyimpan hasil ke dalam file
    private static void simpanKeFile(
            ArrayList<String> nimList, ArrayList<String> namaList,
            ArrayList<Double> nilaiKehadiranList, ArrayList<Double> nilaiMidtestList, ArrayList<Double> nilaiUASList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("nilai-pemrograman-java.txt"))) {
            writer.println("Daftar Nilai Mahasiswa Pemrograman Java");
            writer.println("================================================================");
            writer.printf("%-4s%-11s%-8s%-12s%s%n", "No.", "NIM", "Nama", "Nilai Akhir", "Grade");
            writer.println("================================================================");

            for (int i = 0; i < nimList.size(); i++) {
                double nilaiAkhir = hitungNilaiAkhir(nilaiKehadiranList.get(i), nilaiMidtestList.get(i), nilaiUASList.get(i));
                String grade = getGrade(nilaiAkhir);
                writer.printf("%-4d%-11s%-8s%-12.2f%s%n", i + 1, nimList.get(i), namaList.get(i), nilaiAkhir, grade);
            }

            writer.println("================================================================");
            writer.println("Jumlah Mahasiswa : " + nimList.size());
            writer.println("Jumlah Mahasiswa yang Lulus : " + hitungJumlahLulus(nimList, nilaiKehadiranList, nilaiMidtestList, nilaiUASList));
            writer.println("Jumlah Mahasiswa yang Tidak Lulus : " + (nimList.size() - hitungJumlahLulus(nimList, nilaiKehadiranList, nilaiMidtestList, nilaiUASList)));
            writer.println("================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fungsi untuk menghitung jumlah mahasiswa yang lulus
    private static int hitungJumlahLulus(
            ArrayList<String> nimList, ArrayList<Double> nilaiKehadiranList,
            ArrayList<Double> nilaiMidtestList, ArrayList<Double> nilaiUASList) {
        int jumlahLulus = 0;
        for (int i = 0; i < nimList.size(); i++) {
            double nilaiAkhir = hitungNilaiAkhir(nilaiKehadiranList.get(i), nilaiMidtestList.get(i), nilaiUASList.get(i));
            String grade = getGrade(nilaiAkhir);
            if (grade.equals("A") || grade.equals("B") || grade.equals("C")) {
                jumlahLulus++;
            }
        }
        return jumlahLulus;
    }

}
