package com.kanda.pac.satudua;

/**
 *
 * @author Muh Kanda <surel.muhkanda@gmail.com>
 */

public class PalindromeChecker {

    public static void main(String[] args) {
        // Input data
        String[] inputArray = {"KATAK", "MAKAN", "MALAM", "LEVEL", "RADAR", "KANDA"};

        // Tampilkan output
        System.out.println("Output data:");
        for (String input : inputArray) {
            checkPalindrome(input);
        }
    }

    // Fungsi untuk mengecek apakah sebuah kata merupakan palindrom
    private static void checkPalindrome(String input) {
        // Balikan urutan karakter dari kata inputan
        String reversed = reverseString(input);

        if (input.equals(reversed)) {
            System.out.println(input + " merupakan palindrom");
        } else {
            System.out.println(input + " bukan palindrom");
        }
    }

    // Fungsi untuk membalikkan urutan karakter dalam string
    private static String reverseString(String input) {
        char[] charArray = input.toCharArray();
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            // Tukar karakter pada posisi left dan right
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Pindahkan pointer left ke kanan dan pointer right ke kiri
            left++;
            right--;
        }

        return new String(charArray);
    }

}