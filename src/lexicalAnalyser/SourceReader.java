package lexicalAnalyser;

import shared.Global;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SourceReader {
    private BufferedReader reader;
    private String currentLine;
    private int lineIndex;

    public SourceReader() {
        this.reader = initReader();
        setNextLine();
        this.lineIndex = 0;

    }

    public void close() {
        try {
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedReader initReader() {
        try {
            FileReader fileReader = new FileReader(Global.getSource());
            return new BufferedReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setNextLine() {
        try {
            this.currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lireCar() {
        if (this.lineIndex == this.currentLine.length()) {
            Global.setNumLigne(Global.getNumLigne() + 1);
            this.lineIndex = 0;
            setNextLine();
        }
        if (this.currentLine == null) {
            ErrorHandler.raiseError(1);
        }
        Global.setCarlu(this.currentLine.charAt(this.lineIndex));
        this.lineIndex++;
    }
}
