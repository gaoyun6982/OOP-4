package server;

import java.io.*;
import java.net.InterfaceAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Artem on 28.04.16.
 */
public class ServerLauncher {

    public static void main(String[] args) {

        //qwe

        Socket client = null;
        System.out.println("Server started.");

        String result;
        String controlNum = "5";

        ServerWorker worker;

        try {

            try (ServerSocket serverSocket = new ServerSocket(50000)) {

                client = serverSocket.accept();

            }

            System.out.println("Client connected.");

            InputStream in = client.getInputStream();
            DataInputStream din = new DataInputStream(in);

            System.out.println("Create complete.");

            String message = din.readUTF();

            byte[] buffer = new byte[4];
            din.read(buffer, 0, 4);

            int num = 0;
            for(int j=0; j<4; j++){
                num <<= 8;
                num += ((256 + buffer[j]) % 256);
            }

            System.out.println("Message read.\nNum = "+num);

            worker = new ServerWorker(message, num);

            System.out.println("Worker begin.");

            //int controlNumber = Integer.parseInt(controlNum);

            result = worker.work();

            OutputStream out = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);

            System.out.println("writing...");

            dataOutputStream.writeUTF(result);

            System.out.println("Writing complete");

            din.close();
            in.close();

        } catch (IOException ex) {

            System.err.println("Port is used");

        } finally {

            try {

                if (client != null) {

                    client.close();

                }
            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }
    }

}
