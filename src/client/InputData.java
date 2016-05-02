package client;

import java.io.*;

/**
 * Created by Artem on 28.04.16.
 */
public class InputData {

    File inputFile;

    int symbols;

    public InputData(File file){

        inputFile = file;

    }

    public String getFileName(){

        return inputFile.getName();

    }

    public int getSymbols(){

        symbols = inputFile.toString().length();

        return symbols;

    }

    public String getInputFile() throws IOException {

        BufferedReader streamReader = new BufferedReader(new FileReader(inputFile));

        String line;
        String res = "";

        while ((line = streamReader.readLine()) != null){
            //System.out.println(line);
            res += line;
        }

        return res;
    }

}
