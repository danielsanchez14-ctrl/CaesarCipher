/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.caesarcipherproject;

import com.mycompany.models.CaesarEncrypter;

/**
 *
 * @author kosmo
 */
public class CaesarCipherProject {

    public static void main(String[] args) {
        CaesarEncrypter encrypter
                = new CaesarEncrypter(CaesarEncrypter.Alphabet.ASCII_ALPHABET, 5);
        try {
        String palabra = encrypter.encrypt("pshermancalle#$%=!|||");
        System.out.println(palabra);
        String palabra2 = encrypter.decrypt(palabra);
        System.out.println(palabra2);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
