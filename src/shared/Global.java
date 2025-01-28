package shared;

import java.util.List;


public class Global {
    private static String source;
    private static Character carlu;
    private static Integer nombre;
    private static String chaine; //A changer en un type ENUM
    private static Integer numLigne;
    private static List<String> tableMotsReserve; //String à changer
    public static final Integer NB_MOT_RESERVES = 7;
    public static final Integer LONG_MAX_INDENT = 20;
    public static final Integer LONG_MAX_CHAINE = 50;

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
        return tableMotsReserve;
    }

    public static void setTableMotsReserve(List<String> tableMotsReserve) {
        Global.tableMotsReserve = tableMotsReserve;
    }
}
