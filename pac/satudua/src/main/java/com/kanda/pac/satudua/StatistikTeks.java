package com.kanda.pac.satudua;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Muh Kanda <surel.muhkanda@gmail.com>
 */

public class StatistikTeks {

    public static void main(String[] args) {
        String teks = "Java adalah bahasa pemrograman yang dapat dijalankan di berbagai komputer termasuk telepon genggam. Bahasa ini awalnya dibuat oleh James Gosling saat masih bergabung di Sun Microsystems, yang saat ini merupakan bagian dari Oracle dan dirilis tahun 1995. Bahasa ini banyak mengadopsi sintaksis yang terdapat pada C dan C++ namun dengan sintaksis model objek yang lebih sederhana serta dukungan rutin-rutin aras bawah yang minimal. Aplikasi-aplikasi berbasis java umumnya dikompilasi ke dalam bytecode dan dapat dijalankan pada berbagai Mesin Virtual Java.\n\nJava merupakan bahasa pemrograman yang bersifat umum dan secara khusus didesain untuk memanfaatkan dependensi implementasi seminimal mungkin. Karena fungsionalitasnya yang memungkinkan aplikasi java mampu berjalan di beberapa platform sistem operasi yang berbeda, java dikenal pula dengan slogannya, Tulis sekali, jalankan di mana pun. Saat ini java merupakan bahasa pemrograman yang paling populer digunakan, dan secara luas dimanfaatkan dalam pengembangan berbagai jenis perangkat lunak";

        // Pecah teks menjadi array kata
        String[] kataArray = teks.split("\\s+");

        // Hitung jumlah kata
        int jumlahKata = kataArray.length;
        System.out.println("Jumlah kata dari teks: " + jumlahKata);

        // Hitung kemunculan tiap kata
        Map<String, Integer> kemunculanKata = new HashMap<>();
        for (String kata : kataArray) {
            kemunculanKata.put(kata, kemunculanKata.getOrDefault(kata, 0) + 1);
        }

        // Tampilkan jumlah kemunculan tiap kata
        System.out.println("Jumlah kemunculan tiap kata:");
        for (Map.Entry<String, Integer> entry : kemunculanKata.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Hitung jumlah kata yang muncul hanya satu kali
        int jumlahKataHanyaSatuKali = (int) kemunculanKata.entrySet().stream().filter(entry -> entry.getValue() == 1).count();
        System.out.println("Jumlah kata yang muncul hanya satu kali: " + jumlahKataHanyaSatuKali);

        // Temukan kata yang paling banyak muncul
        String kataTerbanyak = "";
        int jumlahTerbanyak = 0;
        for (Map.Entry<String, Integer> entry : kemunculanKata.entrySet()) {
            if (entry.getValue() > jumlahTerbanyak) {
                kataTerbanyak = entry.getKey();
                jumlahTerbanyak = entry.getValue();
            }
        }
        System.out.println("Kata yang paling banyak muncul: " + kataTerbanyak + " (" + jumlahTerbanyak + " kali)");

        // Temukan kata yang paling sedikit muncul
        String kataTersedikit = "";
        int jumlahTersedikit = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : kemunculanKata.entrySet()) {
            if (entry.getValue() < jumlahTersedikit) {
                kataTersedikit = entry.getKey();
                jumlahTersedikit = entry.getValue();
            }
        }
        System.out.println("Kata yang paling sedikit muncul: " + kataTersedikit + " (" + jumlahTersedikit + " kali)");
    }

}