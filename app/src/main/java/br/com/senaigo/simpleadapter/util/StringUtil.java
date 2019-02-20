package br.com.senaigo.simpleadapter.util;

public class StringUtil {
    public static Boolean isEmpty(String texto){
        return texto.trim().equals("");
    }

    public static Boolean isNotEmpty(String texto){
        return !isEmpty(texto);
    }

    public static Boolean isNull(String texto){
        return texto == null;
    }

    public static Boolean isNotNull(String texto){
        return !isNull(texto);
    }

    public static Boolean isNullOrEmpty(String texto){
        return isNull(texto) || isEmpty(texto);
    }

    public static Boolean isNotNullOrEmpty(String texto){
        return isNotNull(texto) && isNotEmpty(texto);
    }
}
