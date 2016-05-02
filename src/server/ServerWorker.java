package server;

/**
 * Created by Artem on 02.05.16.
 */
public class ServerWorker {

    String outString;

    int i=0, i1=0;
    int num=0;
    int result;

    char[] stream;

    public ServerWorker(String input){

        stream = new char[input.length()];
        input.getChars(0, input.length(), stream, 0);

    }

    public String work(int controlNum){

        while(stream[i] != '\0'){

            if(stream[i]>40){

                num++;

            } else {

                i1 = num;
                num=0;

            }
            if(i1 == controlNum) {

                result++;
                i1=0;

            }

        }

        outString = "Found "+result+" words with length "+controlNum;

        return outString;

    }

}
