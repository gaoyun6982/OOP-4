package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Artem on 28.04.16.
 */
public class ClientUI extends JFrame {

    JButton fileName;
    JLabel fileLabel;
    JLabel controlNumLabel;
    JTextField controlNum;
    JButton sendButton;

    InputData inputFile = null;

    int num=0;

    public ClientUI(){

        setSize(320, 240);
        setTitle("Клиент поиска слов в тексте");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addComponentsToFrame(getContentPane());

        setVisible(true);

    }

    private void addComponentsToFrame(Container pane){

        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;

        fileLabel = new JLabel();
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pane.add(fileLabel, gbc);

        fileName = new JButton("Исходный файл");
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pane.add(fileName, gbc);

        fileName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jfc = new JFileChooser();

                int fileChooserFlag = jfc.showDialog(null, "Выбрать файл");
                if(fileChooserFlag == jfc.APPROVE_OPTION){
                    inputFile = new InputData(jfc.getSelectedFile());
                    fileLabel.setText(inputFile.getFileName());
                }

            }
        });

        controlNumLabel = new JLabel("Искомое количество букв в слове");
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 2;
        pane.add(controlNumLabel, gbc);

        controlNum = new JTextField();
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pane.add(controlNum, gbc);

        sendButton = new JButton("Отправить");
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 4;
        pane.add(sendButton, gbc);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    num = Integer.parseInt(controlNum.getText().toString());

                } catch (Exception ex){

                    controlNum.setText("Неправильно введено число или неуказан файл.");

                }

                if((num<1)||(inputFile == null)){

                    controlNum.setText("Неправильно введено число или неуказан файл.");

                } else{

                    send();

                }

            }
        });

    }

    public void send(){

        /*try(Socket serverSocket = new Socket(InetAddress.getLocalHost(), 50000)){

            OutputStream serverStream = serverSocket.getOutputStream();
            DataOutputStream serverDataStream = new DataOutputStream(serverStream);

            System.out.println("Connection for number succes.");

            serverDataStream.write(num);
            serverDataStream.flush();

            System.out.println("Number send");

        } catch (UnknownHostException e) {

            e.printStackTrace();
            System.out.println("Connection failed.");

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Input error.");

        }*/

        try(Socket serverSocket = new Socket(InetAddress.getLocalHost(), 50000)) {

            System.out.println("Connection for message succes.");

            OutputStream serverStream = serverSocket.getOutputStream();
            DataOutputStream serverDataStream = new DataOutputStream(serverStream);

            serverDataStream.writeUTF(inputFile.getInputFile());
            serverDataStream.flush();

            System.out.println("File send.");

            InputStream clientStream = serverSocket.getInputStream();
            DataInputStream din = new DataInputStream(clientStream);

            System.out.println("Ready for input.");

            String res = din.readUTF();

            System.out.println(res);

        } catch (UnknownHostException e) {

            e.printStackTrace();
            System.out.println("Connection failed.");

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Input error.");

        }

    }

}
