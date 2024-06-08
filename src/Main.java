import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    ArrayList<PhoneContact> contacts=new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PhoneBook");
        Button addButton = new Button("Add New Contact");
        Button removeButton = new Button("Remove Contact");
        Button editButton = new Button("Edit Contact");
        Button displayButton = new Button("Display Contacts");
        Button exitButton = new Button("Exit");

        addButton.setOnAction(event -> showAddContactWindow());
        removeButton.setOnAction(event -> showRemoveContactWindow());
        editButton.setOnAction(event -> showEditContactWindow());
        displayButton.setOnAction(event -> showDisplayContactsWindow());
        exitButton.setOnAction(event -> primaryStage.close());

        addButton.setStyle("-fx-background-color: lightgreen;");
        addButton.setOnMouseEntered(event->{addButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
        addButton.setOnMouseExited(event->{addButton.setStyle("-fx-background-color: lightgreen;");});
        removeButton.setStyle("-fx-background-color: red;");
        removeButton.setOnMouseEntered(event->{removeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
        removeButton.setOnMouseExited(event->{removeButton.setStyle("-fx-background-color: red;");});
        editButton.setStyle("-fx-background-color: yellow;");
        editButton.setOnMouseEntered(event->{editButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
        editButton.setOnMouseExited(event->{editButton.setStyle("-fx-background-color: yellow;");});
        displayButton.setStyle("-fx-background-color: lightblue;");
        displayButton.setOnMouseEntered(event->{displayButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
        displayButton.setOnMouseExited(event->{displayButton.setStyle("-fx-background-color: lightblue;");});
        exitButton.setStyle("-fx-background-color: grey;");
        exitButton.setOnMouseEntered(event->exitButton.setStyle("-fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
        exitButton.setOnMouseExited(event->{exitButton.setStyle("-fx-background-color: grey;");});

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-background-color: lightpink; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 18px;");
        vbox.getChildren().addAll(addButton, removeButton, editButton, displayButton, exitButton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddContactWindow() {
        Stage addStage = new Stage();
        addStage.setTitle("Add New Contact");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameLabel.setStyle("-fx-text-fill: darkgreen; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");

        Label lastnameLabel = new Label("Lastname:");
        TextField lastnameField = new TextField();
        lastnameLabel.setStyle("-fx-text-fill: darkgreen; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold;");

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneLabel.setStyle("-fx-text-fill: darkgreen; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailLabel.setStyle("-fx-text-fill: darkgreen; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");

        Button saveButton = new Button("Save");

        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            String lastname = lastnameField.getText();
            String number = phoneField.getText();
            String email = emailField.getText();
            if (!name.isEmpty() && !lastname.isEmpty() && !number.isEmpty() && !email.isEmpty()) {
                contacts.add(new PhoneContact(name, lastname, number, email));
                addStage.close();
            }
        });
        saveButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: green; -fx-text-fill: white;");
        saveButton.setOnMouseEntered(event->{saveButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: darkgreen; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
        saveButton.setOnMouseExited(event->{saveButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: green; -fx-text-fill: white;");});

        HBox nameBox = new HBox(5, nameLabel, nameField);
        HBox lastnameBox = new HBox(5, lastnameLabel, lastnameField);
        HBox phoneBox = new HBox(5, phoneLabel, phoneField);
        HBox emailBox=new HBox(5, emailLabel, emailField);
        VBox vboxAdd=new VBox(10);
        vboxAdd.getChildren().addAll(nameBox, lastnameBox, phoneBox, emailBox, saveButton);
        vboxAdd.setStyle("-fx-background-color: lightgreen;");
        vboxAdd.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(vboxAdd, 600, 400);
        addStage.setScene(scene);
        addStage.show();
    }


    private void showRemoveContactWindow() {
        Stage removeStage = new Stage();
        removeStage.setTitle("Remove Contact");
        if(contacts.isEmpty()){
            Button okayButton=new Button("Okay");
            okayButton.setOnAction(event -> {
                removeStage.close();
            });
            okayButton.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 60px; -fx-pref-height: 15px; -fx-font-size:70px;");
            okayButton.setOnMouseEntered(event->okayButton.setStyle("-fx-font-size:18px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
            okayButton.setOnMouseExited(event->{okayButton.setStyle("-fx-font-size:18px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});
            Label emptyLabel=new Label("THE PHONEBOOK IS EMPTY!");
            emptyLabel.setStyle("-fx-text-fill: darkred; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 25px;");
            VBox vboxRemove=new VBox(20);
            vboxRemove.getChildren().addAll(emptyLabel, okayButton);
            vboxRemove.setStyle("-fx-background-color: pink;");
            vboxRemove.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vboxRemove, 600, 400);
            removeStage.setScene(scene);
        }else {
            Label removelabel = new Label("Enter the Name and the Lastname of the contact you want to Remove");
            removelabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-color: darkred; -fx-font-size:17px;");

            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';");

            Label lastnameLabel = new Label("Lastname:");
            TextField lastnameField = new TextField();
            lastnameLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold;");

            HBox nameBox = new HBox(5, nameLabel, nameField);
            HBox lastnameBox = new HBox(5, lastnameLabel, lastnameField);

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: red; -fx-text-fill: white;");
            removeButton.setOnMouseEntered(event->{removeButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: darkred; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;");});
            removeButton.setOnMouseExited(event->{removeButton.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: red; -fx-text-fill: white;");});
            removeButton.setOnAction(event -> {
                for (PhoneContact contact : contacts) {
                    if (nameField.getText().equals(contact.getName()) && lastnameField.getText().equals(contact.getLastName())) {
                        contacts.remove(contact);
                        removeStage.close();
                    }
                }
            });
            VBox vboxRemove=new VBox(10);
            vboxRemove.setAlignment(Pos.CENTER_LEFT);
            vboxRemove.getChildren().addAll(removelabel, nameBox, lastnameBox, removeButton);
            vboxRemove.setStyle("-fx-background-color:pink;");
            Scene scene = new Scene(vboxRemove, 600, 400);
            removeStage.setScene(scene);
        }
        removeStage.show();
    }

    private void showEditContactWindow() {
        Stage editStage = new Stage();
        editStage.setTitle("Edit Contact");
        if(contacts.isEmpty()){
            Label emptylabel=new Label("THE PHONEBOOK IS EMPTY!");
            emptylabel.setStyle("-fx-text-fill: darkorange; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 25px;");
            Button okayButton=new Button("Okay");
            okayButton.setOnAction(event->{
                editStage.close();
            });
            okayButton.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 60px; -fx-pref-height: 15px; -fx-font-size:70px;");
            okayButton.setOnMouseEntered(event->okayButton.setStyle("-fx-font-size:18px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
            okayButton.setOnMouseExited(event->{okayButton.setStyle("-fx-font-size:18px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});
            VBox vboxEdit=new VBox(20);
            vboxEdit.setStyle("-fx-background-color: yellow;");
            vboxEdit.getChildren().addAll(emptylabel, okayButton);
            vboxEdit.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vboxEdit, 600, 400);
            editStage.setScene(scene);
            editStage.show();
        } else {
            Label editLabel = new Label("Which contact do you want to edit?");
            editLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-text-fill: darkorange; -fx-font-size:17px;");

            Label nameLabel = new Label("Enter the name:");
            TextField nameField = new TextField();
            nameLabel.setStyle("-fx-text-fill: darkorange; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS', -fx-font-size:17;");

            Label surnameLabel = new Label("Enter the surname:");
            TextField surnameField = new TextField();
            surnameLabel.setStyle("-fx-text-fill: darkorange; -fx-font-weight: bold; -fx-font-family: 'Comic Sans MS';-fx-font-size:17;");

            HBox nameBox = new HBox(5, nameLabel, nameField);
            HBox surnameBox = new HBox(5, surnameLabel, surnameField);

            Button enterButton = new Button("Enter");
            enterButton.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 60px; -fx-pref-height: 15px; -fx-font-size:17px;");
            enterButton.setOnMouseEntered(event->enterButton.setStyle("-fx-font-size:17px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
            enterButton.setOnMouseExited(event->{enterButton.setStyle("-fx-font-size:17px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});
            enterButton.setOnAction(event -> {
                for (PhoneContact contact : contacts) {
                    if (nameField.getText().equals(contact.getName()) && surnameField.getText().equals(contact.getLastName())) {
                        Stage editStage2 = new Stage();
                        Label question = new Label("What do you want to edit?\n");
                        question.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:18px;");
                        Button opt1 = new Button("Name");
                        opt1.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                        opt1.setOnMouseEntered(event2->{opt1.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        opt1.setOnMouseExited(event2->{opt1.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        Button opt2 = new Button("Surname");
                        opt2.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                        opt2.setOnMouseEntered(event2->{opt2.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        opt2.setOnMouseExited(event2->{opt2.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        Button opt3 = new Button("Phone Number");
                        opt3.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                        opt3.setOnMouseEntered(event2->{opt3.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        opt3.setOnMouseExited(event2->{opt3.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        Button opt4 = new Button("Email");
                        opt4.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                        opt4.setOnMouseEntered(event2->{opt4.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        opt4.setOnMouseExited(event2->{opt4.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                        Button opt5 = new Button("Done");
                        opt5.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 60px; -fx-pref-height: 15px; -fx-font-size:18px;");
                        opt5.setOnMouseEntered(event2->opt5.setStyle("-fx-font-size:18px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
                       opt5.setOnMouseExited(event2->{opt5.setStyle("-fx-font-size:18px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});

                        opt1.setOnAction(event1 -> {
                            Stage nameStage = new Stage();
                            Label currentName=new Label("Current Name is: "+contact.getName()+"\n");
                            currentName.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:17px;");
                            Label namelabel1 = new Label("Enter new Name:");
                            namelabel1.setStyle("-fx-font-weight: bold; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:16px;");
                            TextField nameField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!nameField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setName(nameField1.getText());
                                    nameStage.close();
                                });
                            }
                            updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            VBox vboxName=new VBox(10);
                            vboxName.setStyle("-fx-background-color:yellow;");
                            vboxName.setAlignment(Pos.CENTER_LEFT);
                            vboxName.getChildren().addAll(currentName,namelabel1, nameField1, updateButton);
                            Scene nameScene = new Scene(vboxName, 600, 400);
                            nameStage.setScene(nameScene);
                            nameStage.show();
                        });
                        opt2.setOnAction(event1 -> {
                            Stage surnameStage = new Stage();
                            Label currentSurname=new Label("Current Surname is: "+contact.getLastName()+"\n");
                            currentSurname.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:17px;");
                            Label surnamelabel1 = new Label("Enter new Surname:");
                            surnamelabel1.setStyle("-fx-font-weight: bold; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:16px;");
                            TextField surnameField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!surnameField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setLastName(surnameField1.getText());
                                    surnameStage.close();
                                });
                            }
                            updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            VBox vboxSurname=new VBox(10);
                            vboxSurname.setStyle("-fx-background-color:yellow;");
                            vboxSurname.setAlignment(Pos.CENTER_LEFT);
                            vboxSurname.getChildren().addAll(currentSurname,surnamelabel1, surnameField1, updateButton);
                            Scene surnameScene = new Scene(vboxSurname, 600, 400);
                            surnameStage.setScene(surnameScene);
                            surnameStage.show();
                        });
                        opt3.setOnAction(event1 -> {
                            Stage phonenumberStage = new Stage();
                            Label currentPhoneNumber=new Label("Current Phone Number is: "+contact.getNumber()+"\n");
                            currentPhoneNumber.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:17px;");
                            Label phonenumberlabel1 = new Label("Enter new Phone Number:");
                            phonenumberlabel1.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:16px;");
                            TextField phonenumberField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!phonenumberField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setNumber(phonenumberField1.getText());
                                    phonenumberStage.close();
                                });
                            }
                            updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            VBox vboxphonenumber = new VBox(10,currentPhoneNumber,phonenumberlabel1, phonenumberField1, updateButton);
                            vboxphonenumber.setStyle("-fx-background-color:yellow;");
                            vboxphonenumber.setAlignment(Pos.CENTER_LEFT);
                            vboxphonenumber.setStyle("-fx-background-color:yellow;");
                            Scene phonenumberScene = new Scene(vboxphonenumber, 600, 400);
                            phonenumberStage.setScene(phonenumberScene);
                            phonenumberStage.show();
                        });
                        opt4.setOnAction(event1 -> {
                            Stage emailStage = new Stage();
                            Label currentEmail=new Label("Current Email is: "+contact.getEmail()+"\n");
                            currentEmail.setStyle("-fx-font-weight: bold; -fx-font-style:italic; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:17px;");
                            Label emaillabel1 = new Label("Enter new Email:");
                            emaillabel1.setStyle("-fx-font-weight: bold; -fx-text-fill:darkorange; -fx-font-family:'Comic Mans MS'; -fx-font-size:16px;");
                            TextField emailField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!emailField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setEmail(emailField1.getText());
                                    emailStage.close();
                                });
                            }
                            updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-font-style:italic; -fx-background-color: darkorange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            updateButton.setOnMouseExited(event2->{updateButton.setStyle("-fx-background-color: orange; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-family:'Comic Mans MS'; -fx-font-size: 17px;");});
                            VBox vboxemail = new VBox(10,currentEmail,emaillabel1, emailField1, updateButton);
                            vboxemail.setStyle("-fx-background-color:yellow;");
                            vboxemail.setAlignment(Pos.CENTER_LEFT);
                            Scene emailScene = new Scene(vboxemail, 600,400);
                            emailStage.setScene(emailScene);
                            emailStage.show();
                        });
                        opt5.setOnAction(event1 -> {
                            editStage2.close();
                        });
                        VBox vboxedit2 = new VBox(10,question, opt1, opt2, opt3, opt4, opt5);
                        vboxedit2.setStyle("-fx-background-color:yellow; -fx-font-size: 17px;");
                        Scene scene2 = new Scene(vboxedit2, 600,400);
                        editStage2.setScene(scene2);
                        editStage2.show();
                    }
                }
                editStage.close();
            });
            VBox vboxedit = new VBox(5,editLabel, nameBox, surnameBox, enterButton);
            vboxedit.setStyle("-fx-background-color:yellow;");
            Scene scene = new Scene(vboxedit, 600,400);
            editStage.setScene(scene);
            editStage.show();
        }
    }

    private void showDisplayContactsWindow() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display Contacts");
        VBox vboxdisplay = new VBox(5);
        Button okayButton=new Button("Okay");
        okayButton.setOnAction(event -> {
            displayStage.close();
        });
        if(contacts.isEmpty()){
            Label emptylabel=new Label("THE PHONEBOOK IS EMPTY!");
            emptylabel.setStyle("-fx-text-fill: darkblue; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 25px;");
            vboxdisplay.getChildren().add(emptylabel);
            vboxdisplay.getChildren().add(okayButton);
            okayButton.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-font-size:18px;");
            okayButton.setOnMouseEntered(event->okayButton.setStyle("-fx-font-size:18px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
            okayButton.setOnMouseExited(event->{okayButton.setStyle("-fx-font-size:18px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});
            vboxdisplay.setAlignment(Pos.CENTER);
        }else {
            int index = 0;
            for (PhoneContact contact : contacts) {
                index++;
                Label info=new Label("N" + index + ")\n" + contact.displayContact() + "\n");
                info.setStyle("-fx-text-fill: darkblue; -fx-font-family: 'Arial Narrow'; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 16px;");
                vboxdisplay.getChildren().add(info);
            }
            okayButton.setStyle("-fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-font-size:17px;");
            okayButton.setOnMouseEntered(event->okayButton.setStyle("-fx-font-size:17px; -fx-pref-width: 70px; -fx-pref-height: 15px; -fx-background-color: darkgrey; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-style: italic;"));
            okayButton.setOnMouseExited(event->{okayButton.setStyle("-fx-font-size:17px; -fx-background-color: grey; -fx-font-weight:bold; -fx-pref-width: 70px; -fx-pref-height: 15px;");});
            vboxdisplay.getChildren().add(okayButton);
        }
        vboxdisplay.setStyle("-fx-background-color:lightblue;");
        Scene scene = new Scene(vboxdisplay, 600,400);
        displayStage.setScene(scene);
        displayStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}