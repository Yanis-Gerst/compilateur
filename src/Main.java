import lexicalAnalyser.LexicalAnalyser;
import semanticAnalyzer.ProductionRules;
import shared.Global;
import shared.LexicalUnit;
import tableIdentifier.IdentifierTable;
import tableIdentifier.IdentifierType;

public class Main {
    public static void main(String[] args) {
        LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
        IdentifierTable table = new IdentifierTable();
        ProductionRules productionRules = new ProductionRules(lexicalAnalyser);
        try {
            while (true) {
                Global.setUnilex(lexicalAnalyser.analex());
                if (!productionRules.dispatch()) throw new RuntimeException("Erreur synt");
            }
        } catch (Exception e) {
            e.printStackTrace();
            table.displayTable();
        }


    }
}