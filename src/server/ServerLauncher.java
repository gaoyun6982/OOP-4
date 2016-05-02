package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Artem on 28.04.16.
 */
public class ServerLauncher {

    public static void main(String[] args) {

        Socket client = null;
        System.out.println("Server started.");

        String result;

        ServerWorker worker;

        try {

            try (ServerSocket serverSocket = new ServerSocket(50000)) {

                client = serverSocket.accept();

            }

            System.out.println("Client connected");

            InputStream in = client.getInputStream();
            DataInputStream din = new DataInputStream(in);

            System.out.println("Create complete.");

            String message = din.readUTF();
            int controlNum = din.readInt();

            worker = new ServerWorker(message);

            System.out.println("Worker begin.");

            result = worker.work(controlNum);

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
