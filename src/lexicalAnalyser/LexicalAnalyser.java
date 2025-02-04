package lexicalAnalyser;

import shared.Global;
import shared.LexicalUnit;

public class LexicalAnalyser {

    private SourceReader reader;

    public LexicalAnalyser() {
        this.init();
    }

    public void init() {
        Global.setNumLigne(0);
        Global.setSource("src/mini-pascal.txt");
//        Global.initTableMotsReserves();
        SourceReader myFileReader = new SourceReader();
        this.reader = myFileReader;
        myFileReader.lireCar();
    }

    public void close() {
        this.reader.close();
    }

    public LexicalUnit analex() {
        Recognizer recognizer = new Recognizer(reader);
        Character c = Global.getCarlu();
        if (SeparatorTrimmer.isSpace(c) || c == '{') {
            SeparatorTrimmer.trim(reader);
            return analex();
        } else if (Character.isDigit(c)) {
            return recognizer.recoInt();
        } else if (Character.isLetter(c)) {
            return recognizer.recoIdentOrReserveWord();
        } else if (c == '\'') {
           return  recognizer.recoString();
        } else if (Symbol.getSimpleSymbol().contains(c.toString())) {
            return recognizer.recoSymbol();
        }
        throw new RuntimeException("le character " + c + " correspond à aucune unité léxicale");
    }
}
