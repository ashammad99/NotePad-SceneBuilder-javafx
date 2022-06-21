/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed Hammad
 */
public class FXMLDocumentController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    File chosenFile = new File("");
    
    @FXML
    private TextArea textArea;

    @FXML
    void newItem(ActionEvent event) {
        textArea.setText("");
        Notepad.mainStage.setTitle("Untitled - Notpad");

    }

    @FXML
    void newWindowItem(ActionEvent event) {
        try {
            new Notepad().start(new Stage());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    void openItem(ActionEvent event) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files Only", "*.txt"),
                new FileChooser.ExtensionFilter("PDF Files Only", "*.pdf"),
                new FileChooser.ExtensionFilter("PDF and text Files", "*.txt", "*.pdf")
        );
        chosenFile = fileChooser.showOpenDialog(Notepad.mainStage);
        /*----------------------*/
        try {
            BufferedReader br = new BufferedReader(new FileReader(chosenFile));
            Notepad.mainStage.setTitle(chosenFile.getName() + "- Notpad");
            String line = "";
            textArea.setText("");
            while ((line = br.readLine()) != null) {
                textArea.appendText(line + "\n");
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    void saveItem(ActionEvent event) {
        if (!chosenFile.isFile()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files Only", "*.txt"));
            chosenFile = fileChooser.showSaveDialog(Notepad.mainStage);
            Notepad.mainStage.setTitle(chosenFile.getName() + "- Notepad");
            try {
                FileWriter fw = new FileWriter(chosenFile);
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                FileWriter fw = new FileWriter(chosenFile);
                fw.write(textArea.getText());
                fw.close();
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }



    @FXML
    void saveAsItem(ActionEvent event) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files Only", "*.txt"));
        chosenFile = fileChooser.showSaveDialog(Notepad.mainStage);
        Notepad.mainStage.setTitle(chosenFile.getName() + "- Notepad");
        try {
            FileWriter fw = new FileWriter(chosenFile);
            fw.write(textArea.getText());
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void exitItem(ActionEvent event) {
        System.out.println("Exit Button is Pressed");
        Notepad.mainStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
