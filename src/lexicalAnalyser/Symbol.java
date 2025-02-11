package lexicalAnalyser;

import shared.LexicalUnit;

import java.util.*;

public class Symbol {
    private static List<String> simpleSymbol = new ArrayList<>();
    private static List<String> composeSymbol = new ArrayList<>();

    private static final Map<String, LexicalUnit> symbolToLexicalUnit = new HashMap<>();
    private static Map<String, List<String>> composeSymbolNextSymbol = new HashMap<>();

    public static List<String> getSimpleSymbol() {
        if (simpleSymbol.isEmpty()) {
            simpleSymbol.add(",");
            simpleSymbol.add(";");
            simpleSymbol.add(".");
            simpleSymbol.add(":");
            simpleSymbol.add("(");
            simpleSymbol.add(")");
            simpleSymbol.add("<");
            simpleSymbol.add(">");
            simpleSymbol.add("=");
            simpleSymbol.add("+");
            simpleSymbol.add("_");
            simpleSymbol.add("*");
            simpleSymbol.add("/");
        }
        return simpleSymbol;
    }

    public static List<String> getComposeSymbol() {
        if (composeSymbol.isEmpty()) {
            composeSymbol.add(">=");
            composeSymbol.add("<=");
            composeSymbol.add("<>");
            composeSymbol.add(":=");
        }
        return composeSymbol;
    }

    public static Map<String, List<String>> getComposeSymbolNextSymbol() {
        if (composeSymbolNextSymbol.isEmpty()) {
            composeSymbolNextSymbol.put(">", List.of("="));
            composeSymbolNextSymbol.put("<", List.of("=", ">"));
            composeSymbolNextSymbol.put(":", List.of("="));
        }
        return composeSymbolNextSymbol;
    }

    public static boolean isPartOfComposeSymbol(String symbol) {
        return getComposeSymbol().stream().anyMatch(composeSymbol -> composeSymbol.contains(symbol));
    }

    public static Map<String, LexicalUnit> getSymbolToLexicalUnit() {
        if (symbolToLexicalUnit.isEmpty()) {
            symbolToLexicalUnit.put(",", LexicalUnit.virg);
            symbolToLexicalUnit.put(";", LexicalUnit.ptvirg);
            symbolToLexicalUnit.put(".", LexicalUnit.point);
            symbolToLexicalUnit.put(":", LexicalUnit.deuxpts);
            symbolToLexicalUnit.put("(", LexicalUnit.parouv);
            symbolToLexicalUnit.put(")", LexicalUnit.parfer);
            symbolToLexicalUnit.put("<", LexicalUnit.inf);
            symbolToLexicalUnit.put(">", LexicalUnit.sup);
            symbolToLexicalUnit.put("=", LexicalUnit.eg);
            symbolToLexicalUnit.put("+", LexicalUnit.plus);
            symbolToLexicalUnit.put("-", LexicalUnit.moins);
            symbolToLexicalUnit.put("*", LexicalUnit.mult);
            symbolToLexicalUnit.put("/", LexicalUnit.divi);
            symbolToLexicalUnit.put(">=", LexicalUnit.supe);
            symbolToLexicalUnit.put("<=", LexicalUnit.infe);
            symbolToLexicalUnit.put("<>", LexicalUnit.diff);
            symbolToLexicalUnit.put(":=", LexicalUnit.aff);
        }
        return symbolToLexicalUnit;
    }

    public static boolean isSymbol(String c) {
        return getSimpleSymbol().contains(c);
    }


}
