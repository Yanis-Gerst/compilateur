package semanticAnalyzer;

import lexicalAnalyser.LexicalAnalyser;
import shared.Global;
import shared.LexicalUnit;


public class ProductionRules {

    private LexicalAnalyser lexicalAnalyser;

    public ProductionRules(LexicalAnalyser lexicalAnalyser) {
        this.lexicalAnalyser = lexicalAnalyser;
    }

    public boolean dispatch() {
        LexicalUnit unilex = Global.getUnilex();
        String chaine = Global.getChaine();

        if (unilex == LexicalUnit.motcle) {
            if ("PROGRAMME".equals(chaine)) { return prog(); }
            if ("CONST".equals(chaine)) { return declConst(); }
            if ("VAR".equals(chaine)) {  return declVar(); }
            if ("DEBUT".equals(chaine)) { return bloc(); }
            if ("LIRE".equals(chaine)) { return lecture(); }
            if ("ECRIRE".equals(chaine)) {  return ecriture(); }
            return false;
        }
        if (unilex == LexicalUnit.ident) {  return affectation(); }
        if (unilex == LexicalUnit.ent || unilex == LexicalUnit.ch) { return exp(); }
        if (unilex == LexicalUnit.plus || unilex == LexicalUnit.moins || unilex == LexicalUnit.mult || unilex == LexicalUnit.divi) { return op_bin(); }

        return false;
    }

    public boolean prog() {
        boolean success = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("PROGRAMME")) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.ident) {
                Global.setUnilex(lexicalAnalyser.analex());
                success =  Global.getUnilex() == LexicalUnit.ptvirg;
            }
        }
        if (success) System.out.println("Prog");
        return success;
    }
    public boolean declConst() {
        boolean success = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("CONST")) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.ident) {
                Global.setUnilex(lexicalAnalyser.analex());
                if (Global.getUnilex() == LexicalUnit.eg) {
                    Global.setUnilex(lexicalAnalyser.analex());
                    if (Global.getUnilex() == LexicalUnit.ent || Global.getUnilex() == LexicalUnit.ch) {
                        Global.setUnilex(lexicalAnalyser.analex());
                        success = Global.getUnilex() == LexicalUnit.ptvirg;
                    }
                }
            }
            if (success) System.out.println("DeclConst");
            return success;
        }
        return false;
    }

    public boolean declVar() {
        boolean sucess = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("VAR")) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.ident) {
                Global.setUnilex(lexicalAnalyser.analex());
                sucess = Global.getUnilex() == LexicalUnit.ptvirg;
            }
        }
        if (sucess) System.out.println("DeclVar");
        return sucess;
    }

    public boolean bloc() {
        boolean success = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("DEBUT")) {
            Global.setUnilex(lexicalAnalyser.analex());
            System.out.println("Debut Bloc " );

            System.out.print("Bloc: " );
            if (instruction()) {

                while (Global.getUnilex() == LexicalUnit.ptvirg) {
                    Global.setUnilex(lexicalAnalyser.analex());
                    System.out.print("Bloc: ");
                    if (!instruction())  {
                        success = Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("FIN");
                        if (success) {
                            System.out.println("Fin Bloc");
                        }
                        return success;
                    };
                }
                success = Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("FIN");
            }
            if (success) {
                System.out.println("Fin Bloc");
            }
        }
        return false;
    }

    public boolean instruction() {
        LexicalUnit unilex = Global.getUnilex();
        String chaine = Global.getChaine();
        boolean result;
        System.out.print("Instruction: ");
        if (unilex == LexicalUnit.ident) {
            return affectation();
        } else if (unilex == LexicalUnit.motcle) {
            switch (chaine) {
                case "LIRE":
                    result = lecture();
                    Global.setUnilex(lexicalAnalyser.analex());
                    return result;
                case "ECRIRE":
                    result = ecriture();
                    Global.setUnilex(lexicalAnalyser.analex());
                    return result;
                case "DEBUT":
                    return bloc();
                default:
                    return false;
            }
        }

        return false;
    }

    public boolean affectation() {
        boolean sucess = false;
        if (Global.getUnilex() == LexicalUnit.ident) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.eg) {
                Global.setUnilex(lexicalAnalyser.analex());
                sucess = exp();
            }
        }
        if (sucess) {
            System.out.println("Affectation");
        }
        return sucess;
    }

    public boolean lecture() {
        boolean sucess = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("LIRE")) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.parouv) {
                Global.setUnilex(lexicalAnalyser.analex());
                if (Global.getUnilex() == LexicalUnit.ident) {
                    Global.setUnilex(lexicalAnalyser.analex());
                    sucess = Global.getUnilex() == LexicalUnit.parfer;
                }
            }
        }
        if (sucess) {
            System.out.println("Lecture");
        }
        return sucess;
    }

    public boolean ecriture() {
        boolean sucess = false;
        if (Global.getUnilex() == LexicalUnit.motcle && Global.getChaine().equals("ECRIRE")) {
            Global.setUnilex(lexicalAnalyser.analex());
            if (Global.getUnilex() == LexicalUnit.parouv) {
                Global.setUnilex(lexicalAnalyser.analex());
                if (ecrExp()) {

                    Global.setUnilex(lexicalAnalyser.analex());
                    while (Global.getUnilex() == LexicalUnit.virg) {
                        Global.setUnilex(lexicalAnalyser.analex());
                        if (!ecrExp()) return false;
                    }

                }
                sucess =  Global.getUnilex() == LexicalUnit.parfer;
            }
        }
        if (sucess) {
            System.out.println("Ecriture");
        }
        return sucess;
    }

    public boolean ecrExp() {
        return  Global.getUnilex() == LexicalUnit.ch || exp();
    }

    public boolean exp() {
        if (terme()) {
            Global.setUnilex(lexicalAnalyser.analex());
            return suiteTerme();
        }
        return false;
    }

    public boolean suiteTerme() {
        if (op_bin()) {
            Global.setUnilex(lexicalAnalyser.analex());
            return exp();
        }
        return true;
    }

    public boolean op_bin() {
        return (Global.getUnilex() == LexicalUnit.plus || Global.getUnilex() == LexicalUnit.moins || Global.getUnilex() == LexicalUnit.mult || Global.getUnilex() == LexicalUnit.divi);
    }

    public boolean terme() {
         if (Global.getUnilex() == LexicalUnit.ent || Global.getUnilex() == LexicalUnit.ident) return true;
         if (Global.getUnilex() == LexicalUnit.parouv) {
             Global.setUnilex(lexicalAnalyser.analex());
             if (exp()) {
                 Global.setUnilex(lexicalAnalyser.analex());
                 return Global.getUnilex() == LexicalUnit.parfer;
             }
         }
         return false;
    }
}
