package lexicalAnalyser;

import shared.Global;
import shared.KeyWord;
import shared.LexicalUnit;

public class Recognizer {
    private SourceReader reader;

    public Recognizer(SourceReader reader) {
        this.reader = reader;
    }

    public LexicalUnit recoInt() {
        String numberText = "";
        while (Character.isDigit(Global.getCarlu())) {
            numberText += Global.getCarlu();
            reader.lireCar();
        }
        int numberValue = Integer.parseInt(numberText);
        if (numberValue > Global.MAX_INT) ErrorHandler.raiseError(ErrorHandler.EXCEED_LENGTH_INT);
        Global.setNombre(numberValue);
        return LexicalUnit.ent;
    }

    public LexicalUnit recoString() {
        reader.lireCar();
        String currentString = "";
        Character prev = ' ';
        while (Global.getCarlu() != '\'' || prev == '\\') {
            currentString += Global.getCarlu();
            prev = Global.getCarlu();
            reader.lireCar();
        }
        reader.lireCar();
        if (currentString.length() > Global.LONG_MAX_CHAINE) ErrorHandler.raiseError(ErrorHandler.EXCEED_LENGTH_STRING);
        Global.setChaine(currentString);
        return LexicalUnit.ch;
    }

    public LexicalUnit recoIdentOrReserveWord() {
        String word = "";
        while (Character.isDigit(Global.getCarlu()) || Character.isLetter(Global.getCarlu()) || Global.getCarlu() == '_') {
            word += Global.getCarlu();
            reader.lireCar();
        }
        if (word.length() > Global.LONG_MAX_INDENT) ErrorHandler.raiseError(ErrorHandler.EXCEED_LENGTH_IDENT);
        Global.setChaine(word.toUpperCase());
        if (KeyWord.isKeyWord(word)) return LexicalUnit.motcle;
        return LexicalUnit.ident;
    }

    public LexicalUnit recoSymbol() {
        String currentSymbole = "";
        currentSymbole += Global.getCarlu();
        boolean lastCharIsTreated = true;
        if (Symbol.isPartOfComposeSymbol(currentSymbole)) {
            reader.lireCar();
            String nextCharacter = Global.getCarlu().toString();
            if (Symbol.isSymbol(nextCharacter) && Symbol.getComposeSymbolNextSymbol().get(currentSymbole).contains(nextCharacter)) {
                currentSymbole += nextCharacter;
            } else lastCharIsTreated = false;
        }
        if (lastCharIsTreated) reader.lireCar();
        LexicalUnit lexicalUnit = Symbol.getSymbolToLexicalUnit().get(currentSymbole);
        if (lexicalUnit == null) throw new RuntimeException("Le symbole " + currentSymbole + " n'est pas une unité lexicale");
        return lexicalUnit;

    }
}
