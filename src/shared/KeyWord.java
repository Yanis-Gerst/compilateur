package shared;

public class KeyWord {

    public static boolean isKeyWord(String word) {
        return Global.getTableMotsReserve().contains(word);
    }
}