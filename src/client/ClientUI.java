package client;

import sun.nio.ch.Net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Artem on 28.04.16.
 */
public class ClientUI extends JFrame {

    JButton fileName;
    JLabel fileLabel;
    InputData inputFile;

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

        fileName = new JButton("Указать файл");
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


    }

    public void send(){



    }

}
