package shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Global {
    private static String source;
    private static Character carlu;
    private static Integer nombre;
    private static String chaine; //A changer en un type ENUM
    private static Integer numLigne = 0;
    private static List<String> tableMotsReserve = new ArrayList<>(); //String à changer
    public static final Integer NB_MOT_RESERVES = 8;
    public static final Integer LONG_MAX_INDENT = 20;
    public static final Integer LONG_MAX_CHAINE = 50;
    public static final Integer MAX_INT = 32767;

    public static String getSource() {
        return source;
    }

    public static void setSource(String source) {
        Global.source = source;
    }

    public static Character getCarlu() {
        return carlu;
    }

    public static void setCarlu(Character carlu) {
        Global.carlu = carlu;
    }

    public static Integer getNombre() {
        return nombre;
    }

    public static void setNombre(Integer nombre) {
        Global.nombre = nombre;
    }

    public static String getChaine() {
        return chaine;
    }

    public static void setChaine(String chaine) {
        Global.chaine = chaine;
    }

    public static Integer getNumLigne() {
        return numLigne;
    }

    public static void setNumLigne(Integer numLigne) {
        Global.numLigne = numLigne;
    }

    public static List<String> getTableMotsReserve() {
        if (tableMotsReserve.isEmpty()) {
            String[] mots = {"DEBUT", "FIN", "VAR", "ECRIRE", "LIRE", "SI", "PROGRAMME", "CONST"};
            tableMotsReserve.addAll(Arrays.asList(mots));
        }
        return tableMotsReserve;
    }

    public static void setTableMotsReserve(List<String> tableMotsReserve) {
        Global.tableMotsReserve = tableMotsReserve;
    }

//    private static void insereTableMotsReserves(String mot) {
//        // Recherche de la position d'insertion pour maintenir l'ordre alphabétique
//        int i = 0;
//        while (i < tableMotsReserve.size() && tableMotsReserve.get(i).compareTo(mot) < 0) {
//            i++;
//        }
//        tableMotsReserve.add(i, mot);
//    }
//
//    public static void initTableMotsReserves() {
//        tableMotsReserve.clear();
//        String[] mots = {"PROGRAMME", "DEBUT", "FIN", "CONST", "VAR", "ECRIRE", "LIRE"};
//        for (String mot : mots) {
//            insereTableMotsReserves(mot);
//        }
//    }
}

