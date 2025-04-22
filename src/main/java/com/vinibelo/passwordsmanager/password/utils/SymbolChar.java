package com.vinibelo.passwordsmanager.password.utils;

import java.security.SecureRandom;

public class SymbolChar {
    SecureRandom random;

    public SymbolChar(SecureRandom random) {
        this.random = random;
    }
    
    public char randomSymbolChar() {
        int[][] ranges = {
                {33, 47},   // ! " # $ % & ' ( ) * + , - . /
                {58, 64},   // : ; < = > ? @
                {91, 96},   // [ \ ] ^ _ `
                {123, 126}  // { | } ~
        };

        int[] range = ranges[random.nextInt(ranges.length)];
        return (char) random.nextInt(range[0], range[1] + 1);
    }

}
