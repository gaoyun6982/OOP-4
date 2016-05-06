package server;

/**
 * Created by Artem on 02.05.16.
 */
public class ServerWorker {

    String outString;

    int i=0, i1=0;
    int num=0;

    char[] stream;
    char[] out;

    public ServerWorker(String input, int num){

        stream = new char[input.length()];
        out = new char[input.length()];
        input.getChars(0, input.length(), stream, 0);
        System.out.println(stream);
        System.out.println(stream.length);
        this.num = num;

    }

    public String work(){

        while(i < stream.length){

            if(num>0){

                if((stream[i]>47)&&(stream[i]<58)){

                    num--;
                    out[i1]=stream[i];
                    i1++;

                }

            }

            System.out.println("Cycle. "+i);
            i++;

        }
        outString = "Found numbers: ";

        for(int j=0; j<i1; j++){

            outString += out[j];

        }

        return outString;

    }

}
