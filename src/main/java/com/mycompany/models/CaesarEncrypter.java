/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kosmo
 */
public final class CaesarEncrypter {

    public enum Alphabet {
        ASCII_ALPHABET(generateASCIIAlphabet()),
        SPA_ALPHABET(generateSpanishAlphabet()),
        ENG_ALPHABET(generateEnglishAlphabet());

        private final char[] characters;

        Alphabet(char[] characters) {
            this.characters = characters;

        }

        private static char[] generateASCIIAlphabet() {
            char[] array = new char[95];
            for (int i = 0; i < array.length; i++) {
                array[i] = (char) (32 + i);
            }
            return array;
        }

        private static char[] generateEnglishAlphabet() {
            char[] array = new char[26 * 2 + 1];
            int i = 0;
            for (char c = 'A'; c <= 'Z'; c++) {
                array[i] = c;
                i = i + 1;
            }
            for (char c = 'a'; c <= 'z'; c++) {
                array[i] = c;
                i = i + 1;
            }
            array[array.length - 1] = ' ';
            return array;
        }

        private static char[] generateSpanishAlphabet() {
            // Lista explícita: Unicode no es coherente para acentos ni ñ
            return new char[]{
                // Mayúsculas
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü',
                // Minúsculas
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'á', 'é', 'í', 'ó', 'ú', 'ü', ' '
            };
        }

        /**
         * @return the characters
         */
        public char[] getCharacters() {
            return characters;
        }
    }

    private final Alphabet alphabet;
    private final int key;

    public CaesarEncrypter(Alphabet alphabet, int key) {
        this.alphabet = alphabet;
        this.key = key;
    }

    public String encrypt(String word) {
        char[] alphabetArray = this.alphabet.getCharacters();
        int[] numeratedWord = new int[word.length()];
        Map<Character, Integer> indexMap = new HashMap<>();

        //Llenar el diccionario o hashmap
        int i = 0;
        for (char c : alphabetArray) {
            indexMap.put(c, i);
            i = i + 1;
        }

        i = 0;
        for (char c : word.toCharArray()) {
            Integer index = indexMap.get(c);

            if (index == null) {
                throw new IllegalArgumentException(
                        "Character '" + c + "' does not match the selected alphabet: "
                        + alphabet.name()
                );
            }

            numeratedWord[i] = index;
            i++;
        }

        //Se le suma la clave mod n
        for (i = 0; i < numeratedWord.length; i++) {
            numeratedWord[i] = (numeratedWord[i] + key) % alphabetArray.length;
        }

        StringBuilder encryptedWord = new StringBuilder();

        //Se convierte de nuevo la secuencia de números a una letra
        for (int j : numeratedWord) {
            encryptedWord.append(alphabetArray[j]);
        }

        return new String(encryptedWord);
    }

    public String decrypt(String word) {
        char[] alphabetArray = this.alphabet.getCharacters();
        int[] numeratedWord = new int[word.length()];
        Map<Character, Integer> indexMap = new HashMap<>();

        //Llenar el diccionario o hashmap
        int i = 0;
        for (char c : alphabetArray) {
            indexMap.put(c, i);
            i = i + 1;
        }
        //Indexar la palabra
        i = 0;
        for (char c : word.toCharArray()) {
            Integer index = indexMap.get(c);

            if (index == null) {
                throw new IllegalArgumentException(
                        "Character '" + c + "' does not match the selected alphabet: "
                        + alphabet.name()
                );
            }

            numeratedWord[i] = index;
            i++;
        }
        //Se le resta la clave mod n
        for (i = 0; i < numeratedWord.length; i++) {
            numeratedWord[i]
                    = (numeratedWord[i] - key + alphabetArray.length) % alphabetArray.length;
        }

        StringBuilder encryptedWord = new StringBuilder();

        //Se convierte de nuevo la secuencia de números a una letra
        for (int j : numeratedWord) {
            encryptedWord.append(alphabetArray[j]);
        }

        return new String(encryptedWord);

    }

    /**
     * @return the alphabet
     */
    public Alphabet getAlphabet() {
        return alphabet;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

}
