package com.kanda.pac.satudua;

/**
 *
 * @author Muh Kanda <surel.muhkanda@gmail.com>
 */

public class ReverseCapitalization {

    public static void main(String[] args) {
        // Input data
        String inputData = "Saya SeDAng beLaJar BaHasa PemrOrgRaman JAVA";

        // Output data
        String outputData = reverseCapitalization(inputData);

        // Tampilkan output
        System.out.println("Input data : " + inputData);
        System.out.println("Output data: " + outputData);
    }

    // Fungsi untuk membalikkan kapitalisasi pada setiap karakter dalam string
    private static String reverseCapitalization(String input) {
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char currentChar = charArray[i];

            if (Character.isUpperCase(currentChar)) {
                charArray[i] = Character.toLowerCase(currentChar);
            } else if (Character.isLowerCase(currentChar)) {
                charArray[i] = Character.toUpperCase(currentChar);
            }
        }

        return new String(charArray);
    }

}