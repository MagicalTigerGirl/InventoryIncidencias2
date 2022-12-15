package com.example.inventoryincidencias.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    /**
     * Método que comprueba que la contraseña tenga
     * TODO
     */
    public static boolean isPasswordValid(String password) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=]).{8,20}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    /**
     * Método que comprueba que el nombre corto tenga 3 caracteres
     * - Tres caracteres LETRA
     */
    public static boolean isShortNameValid(String shortName) {
        Pattern pattern;
        Matcher matcher;

        final String SHORTNAME_PATTERN = "^(?=.*\\w).{3}$";

        pattern = Pattern.compile(SHORTNAME_PATTERN);
        matcher = pattern.matcher(shortName);

        return matcher.matches();
    }
}
