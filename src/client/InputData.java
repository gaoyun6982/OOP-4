package client;

import java.io.File;

/**
 * Created by Artem on 28.04.16.
 */
public class InputData {

    File inputFile;

    public InputData(File file){

        inputFile = file;

    }

    public String getFileName(){

        return inputFile.getName();

    }

}
