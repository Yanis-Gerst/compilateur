import lexicalAnalyser.LexicalAnalyser;
import lexicalAnalyser.SourceReader;
import shared.Global;

public class Main {
    public static void main(String[] args) {
        LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
        while (Global.getCarlu() != '.') {
            System.out.println(lexicalAnalyser.analex());
        }
        lexicalAnalyser.close();


    }
}