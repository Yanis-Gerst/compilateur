package lexicalAnalyser;

import shared.Global;

import java.util.Stack;

public class SeparatorTrimmer {

    public static boolean isSpace(Character c) {
        return c == ' ' || c == '\t';
    }

    public static boolean isStartOfCommentary(Character c) {
        return c == '{';
    }

    public static boolean isEndOfCommentary(Character c) {
        return c == '}';
    }

    public static void trim(SourceReader reader) {
        if (isSpace(Global.getCarlu())) trimSpace(reader);
        else if (isStartOfCommentary(Global.getCarlu())) trimCommentary(reader);
    }

    private static void trimCommentary(SourceReader reader) {
        Stack<Character> bracketStack = new Stack<>();
        bracketStack.push(Global.getCarlu());
        reader.lireCar();
        while (!bracketStack.isEmpty()) {
            if (isStartOfCommentary(Global.getCarlu())) bracketStack.push(Global.getCarlu());
            else if (isEndOfCommentary(Global.getCarlu())) bracketStack.pop();
            reader.lireCar();
        }
    }
    private static void trimSpace(SourceReader reader) {
        while (isSpace(Global.getCarlu())) reader.lireCar();
    }
}
