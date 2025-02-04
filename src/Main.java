//import lexicalAnalyser.LexicalAnalyser;
//import lexicalAnalyser.SourceReader;
//import shared.Global;
//
//public class Main {
//    public static void main(String[] args) {
//        LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
//        while (Global.getCarlu() != '.') {
//            System.out.println(lexicalAnalyser.analex());
//        }
//        lexicalAnalyser.close();
//
//    }
//}

import lexicalAnalyser.LexicalAnalyser;
import shared.Global;
import tableIdentifier.IdentifierTable;
import tableIdentifier.IdentifierType;
import shared.LexicalUnit;

public class Main {
    public static void main(String[] args) {
        LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
        IdentifierTable identifierTable = new IdentifierTable();

        while (Global.getCarlu() != '.') {
            LexicalUnit unit = lexicalAnalyser.analex();
            System.out.println(unit);

            if (unit == LexicalUnit.ident) {
                String name = Global.getChaine();

                int index = identifierTable.search(name);
                if (index == -1) {
                    // Par défaut, on considère que c'est une variable
                    // Le type réel sera déterminé lors de l'analyse sémantique
                    identifierTable.insert(name, IdentifierType.VARIABLE);
                }
            }
        }

        lexicalAnalyser.close();
        identifierTable.displayTable();
    }
}