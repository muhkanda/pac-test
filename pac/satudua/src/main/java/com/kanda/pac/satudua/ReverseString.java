package com.kanda.pac.satudua;

/**
 *
 * @author Muh Kanda <surel.muhkanda@gmail.com>
 */

public class ReverseString {

    public static void main(String[] args) {
        // Input data
        String inputData = "saya sedang belajar berenang";

        // Output data
        String outputData = reverseString(inputData);

        // Tampilkan output
        System.out.println("Input data : " + inputData);
        System.out.println("Output data: " + outputData);
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