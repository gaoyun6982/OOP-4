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

        Socket client = null;
        Socket clientNum = null;
        System.out.println("Server started.");

        String result;
        String controlNum = "5";

        ServerWorker worker;

        /*try(ServerSocket serverSocket = new ServerSocket(50001)){

            try {

                clientNum = serverSocket.accept();

            } catch (IOException e) {

                e.printStackTrace();

            }

            System.out.println("Client connected.");

            InputStream in = clientNum.getInputStream();
            DataInputStream din = new DataInputStream(in);

            controlNum = din.readUTF();

            System.out.println("Control number read.");

        } catch (IOException e) {

            e.printStackTrace();

        }

        finally {

            try {

                if (clientNum != null) {

                    clientNum.close();

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }
        }*/

        try {

            try (ServerSocket serverSocket = new ServerSocket(50000)) {

                client = serverSocket.accept();

            }

            System.out.println("Client connected.");

            InputStream in = client.getInputStream();
            DataInputStream din = new DataInputStream(in);

            System.out.println("Create complete.");

            String message = din.readUTF();

            System.out.println("Message read.");

            worker = new ServerWorker(message, 5);

            System.out.println("Worker begin.");

            int controlNumber = Integer.parseInt(controlNum);

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
