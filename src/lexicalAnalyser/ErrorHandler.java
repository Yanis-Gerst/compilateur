package lexicalAnalyser;

import shared.Global;

public class ErrorHandler {

    public static final int EXCEED_LENGTH_IDENT = 2;
    public static final int EXCEED_LENGTH_STRING = 3;
    public static final int EXCEED_LENGTH_INT = 4;

    public static void raiseError(Integer errorNumber) {
        throw new RuntimeException("Error: " + errorNumber + " " + getMessage(errorNumber) + "\n In line: " + Global.getNumLigne() + " " + Global.getCarlu());
    }

    private static String getMessage(Integer errorNumber) {
        switch (errorNumber) {
            case EXCEED_LENGTH_IDENT -> {
                return "L'identificateur dépasse la taille maximale";
            }
            case EXCEED_LENGTH_STRING -> {
                return "La chaîne de caractère dépasse la taille maximale";
            }
            case EXCEED_LENGTH_INT -> {
                return "Le nombre dépasse la taille maximale";
            }default -> {
                return "Une erreur inconue est survenue";
            }
        }
    }





}
