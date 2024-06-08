import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

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
        primaryStage.setTitle("Contact Manager");

        Button addButton = new Button("1. Add New Contact");
        Button removeButton = new Button("2. Remove Contact");
        Button editButton = new Button("3. Edit Contact");
        Button displayButton = new Button("4. Display Contacts");
        Button exitButton = new Button("5. Exit");

        addButton.setOnAction(event -> showAddContactWindow());
        removeButton.setOnAction(event -> showRemoveContactWindow());
        editButton.setOnAction(event -> showEditContactWindow());
        displayButton.setOnAction(event -> showDisplayContactsWindow());
        exitButton.setOnAction(event -> primaryStage.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(addButton, removeButton, editButton, displayButton, exitButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddContactWindow() {
        Stage addStage = new Stage();
        addStage.setTitle("Add New Contact");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label lastnameLabel = new Label("Lastname:");
        TextField lastnameField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

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

        VBox vboxAdd = new VBox(nameLabel, nameField, lastnameLabel, lastnameField, phoneLabel, phoneField, emailLabel, emailField, saveButton);
        Scene scene = new Scene(vboxAdd, 300, 200);
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
            VBox vboxRemove=new VBox(new Label("Your Phone book is empty!"), okayButton);
            Scene scene = new Scene(vboxRemove, 300, 200);
            removeStage.setScene(scene);
        }else {
            Label removelabel = new Label("Enter the name and the surname of contact you want to remove");

            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();

            Label lastnameLabel = new Label("Lastname:");
            TextField lastnameField = new TextField();

            Button removeButton = new Button("Remove");
            removeButton.setOnAction(event -> {
                for (PhoneContact contact : contacts) {
                    if (nameField.getText().equals(contact.getName()) && lastnameField.getText().equals(contact.getLastName())) {
                        contacts.remove(contact);
                        removeStage.close();
                    }
                }
            });
            VBox vboxRemove = new VBox(removelabel, nameLabel, nameField, lastnameLabel, lastnameField, removeButton);
            Scene scene = new Scene(vboxRemove, 300, 200);
            removeStage.setScene(scene);
        }
        removeStage.show();
    }

    private void showEditContactWindow() {
        Stage editStage = new Stage();
        editStage.setTitle("Edit Contact");
        if(contacts.isEmpty()){
            Label emptylabel=new Label("The Phonebook is Empty!");
            Button okayButton=new Button("Okay");
            okayButton.setOnAction(event->{
                editStage.close();
            });
            VBox vboxedit=new VBox(emptylabel, okayButton);
            Scene scene = new Scene(vboxedit, 300, 200);
            editStage.setScene(scene);
            editStage.show();
        } else {
            Label editLabel = new Label("Which contact do you want to edit?");
            Label nameLabel = new Label("Enter the name:");
            TextField nameField = new TextField();
            Label surnameLabel = new Label("Enter the surname:");
            TextField surnameField = new TextField();
            Button enterButton = new Button("Enter");
            enterButton.setOnAction(event -> {
                for (PhoneContact contact : contacts) {
                    if (nameField.getText().equals(contact.getName()) && surnameField.getText().equals(contact.getLastName())) {
                        Stage editStage2 = new Stage();
                        Label question = new Label("What do you want to edit?\n");
                        Button opt1 = new Button("Name");
                        Button opt2 = new Button("Surname");
                        Button opt3 = new Button("Phone Number");
                        Button opt4 = new Button("Email");
                        Button opt5 = new Button("Done");
                        opt1.setOnAction(event1 -> {
                            Stage nameStage = new Stage();
                            Label currentName=new Label("Current Name is: "+contact.getName()+"\n");
                            Label namelabel1 = new Label("Enter new Name:");
                            TextField nameField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!nameField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setName(nameField1.getText());
                                    nameStage.close();
                                });
                            }
                            VBox vboxname = new VBox(currentName,namelabel1, nameField1, updateButton);
                            Scene nameScene = new Scene(vboxname, 300, 200);
                            nameStage.setScene(nameScene);
                            nameStage.show();
                        });
                        opt2.setOnAction(event1 -> {
                            Stage surnameStage = new Stage();
                            Label currentSurname=new Label("Current Surname is: "+contact.getLastName()+"\n");
                            Label surnamelabel1 = new Label("Enter new Surname:");
                            TextField surnameField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!surnameField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setLastName(surnameField1.getText());
                                    surnameStage.close();
                                });
                            }
                            VBox vboxsurname = new VBox(currentSurname,surnamelabel1, surnameField1, updateButton);
                            Scene surnameScene = new Scene(vboxsurname, 300, 200);
                            surnameStage.setScene(surnameScene);
                            surnameStage.show();
                        });
                        opt3.setOnAction(event1 -> {
                            Stage phonenumberStage = new Stage();
                            Label currentPhoneNumber=new Label("Current Phone Number is: "+contact.getNumber()+"\n");
                            Label phonenumberlabel1 = new Label("Enter new Phone Number:");
                            TextField phonenumberField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!phonenumberField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setNumber(phonenumberField1.getText());
                                    phonenumberStage.close();
                                });
                            }
                            VBox vboxphonenumber = new VBox(currentPhoneNumber,phonenumberlabel1, phonenumberField1, updateButton);
                            Scene phonenumberScene = new Scene(vboxphonenumber, 300, 200);
                            phonenumberStage.setScene(phonenumberScene);
                            phonenumberStage.show();
                        });
                        opt4.setOnAction(event1 -> {
                            Stage emailStage = new Stage();
                            Label currentEmail=new Label("Current Email is: "+contact.getEmail()+"\n");
                            Label emaillabel1 = new Label("Enter new Email:");
                            TextField emailField1 = new TextField();
                            Button updateButton = new Button("Update");
                            if(!emailField1.getText().isEmpty()){
                                updateButton.setOnAction(event2 -> {
                                    contact.setEmail(emailField1.getText());
                                    emailStage.close();
                                });
                            }
                            VBox vboxemail = new VBox(currentEmail,emaillabel1, emailField1, updateButton);
                            Scene emailScene = new Scene(vboxemail, 300, 200);
                            emailStage.setScene(emailScene);
                            emailStage.show();
                        });
                        opt5.setOnAction(event1 -> {
                            editStage2.close();
                        });
                        VBox vboxedit2 = new VBox(question, opt1, opt2, opt3, opt4, opt5);
                        Scene scene2 = new Scene(vboxedit2, 300, 200);
                        editStage2.setScene(scene2);
                        editStage2.show();
                    }
                }
                editStage.close();
            });
            VBox vboxedit = new VBox(editLabel, nameLabel, nameField, surnameLabel, surnameField, enterButton);
            Scene scene = new Scene(vboxedit, 300, 200);
            editStage.setScene(scene);
            editStage.show();
        }
    }

    private void showDisplayContactsWindow() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display Contacts");
        VBox vboxdisplay = new VBox();
        Button okayButton=new Button("Okay");
        okayButton.setOnAction(event -> {
            displayStage.close();
        });
        if(contacts.isEmpty()){
            vboxdisplay.getChildren().add(new Label("Your Phonebook is Empty!"));
            vboxdisplay.getChildren().add(okayButton);
        }else {
            int index = 0;
            for (PhoneContact contact : contacts) {
                index++;
                vboxdisplay.getChildren().add(new Label("N" + index + ")\n" + contact.displayContact() + "\n"));
            }
            vboxdisplay.getChildren().add(okayButton);
        }
        Scene scene = new Scene(vboxdisplay, 300, 200);
        displayStage.setScene(scene);
        displayStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//public class Main {
//    public static void startScreen(){
//        System.out.println("1. Add New Contact");
//        System.out.println("2. Remove Contact");
//        System.out.println("3. Edit Contact");
//        System.out.println("4. Display Contacts");
//        System.out.println("5. Exit");
//    }
//    public static void editScreen(){
//        System.out.println("1. Name");
//        System.out.println("2. Lastname");
//        System.out.println("3. Number");
//        System.out.println("4. Email");
//    }
//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        ArrayList<PhoneContact>contacts=new ArrayList<PhoneContact>();
//        while(true){
//            startScreen();
//            int ans=scanner.nextInt();
//            if(ans==1){
//                PhoneContact phoneContact=PhoneContact.addPhoneContact();
//                contacts.add(phoneContact);
//                System.out.println(phoneContact.getName()+" "+phoneContact.getLastName()+" Has Been Added To The Phonebook!");
//                System.out.println("---------------------------------------------------------------------------------------");
//            }else if(ans==2){
//                PhoneContact.removePhoneContact(contacts);
//            }else if(ans==3){
//                if(contacts.isEmpty()){
//                    System.out.println("Phonebook is Empty! Add Contacts First!");
//                    System.out.println("---------------------------------------------------------------------------------------");
//                }else{
//                    System.out.println("Which Contact Do You Want To Edit?");
//                    String error=scanner.nextLine();
//                    System.out.println("Enter The Name: ");
//                    String name=scanner.nextLine();
//                    System.out.println(("Enter The Lastname: "));
//                    String lastName=scanner.nextLine();
//                    for(int i=0; i<contacts.size(); i++){
//                        if(name.equals(contacts.get(i).getName()) && lastName.equals(contacts.get(i).getLastName())) {
//                            System.out.println("What Do You Want To Edit? ");
//                            editScreen();
//                            int ans2 = scanner.nextInt();
//                            String error2 = scanner.nextLine();
//                            boolean b = true;
//                            while (b) {
//                                switch (ans2) {
//                                    case 1:
//                                        System.out.println("Enter New Name: ");
//                                        String newName = scanner.nextLine();
//                                        contacts.get(i).setName(newName);
//                                        b = false;
//                                        break;
//                                    case 2:
//                                        System.out.println("Enter New Lastname: ");
//                                        String newLastName = scanner.nextLine();
//                                        contacts.get(i).setLastName(newLastName);
//                                        b = false;
//                                        break;
//                                    case 3:
//                                        System.out.println("Enter New Number: ");
//                                        String newNumber = scanner.nextLine();
//                                        contacts.get(i).setNumber(newNumber);
//                                        b = false;
//                                        break;
//                                    case 4:
//                                        System.out.println("Enter New Email: ");
//                                        String newEmail = scanner.nextLine();
//                                        contacts.get(i).setEmail(newEmail);
//                                        b = false;
//                                        break;
//                                    default:
//                                        System.out.println(ans2 + " is not valid option!");
//                                        break;
//                                }
//                            }
//                            System.out.println("Contact Edited Successfully!");
//                            System.out.println("---------------------------------------------------------------------------------------");
//                        }
//                    }
//                }
//            }else if(ans==4){
//                if(contacts.isEmpty()){
//                    System.out.println("Phonebook is Empty!");
//                }else{
//                    for(int i=0; i< contacts.size(); i++){
//                        System.out.println("Contact N"+(i+1)+")");
//                        System.out.println("Name: "+contacts.get(i).getName());
//                        System.out.println("Lastname: "+contacts.get(i).getLastName());
//                        System.out.println("Number: "+contacts.get(i).getNumber());
//                        System.out.println("Email: "+contacts.get(i).getEmail());
//                    }
//                }
//                System.out.println("---------------------------------------------------------------------------------------");
//            }else if(ans==5){
//                System.out.println("Exited Phonebook Successfully!");
//                break;
//            }else{
//                System.out.println(ans+" is not an option!");
//                System.out.println("---------------------------------------------------------------------------------------");
//            }
//        }
//    }
//}


/*public class Main extends Application {
    Button button;
    public static void main(String[] args) {
        launch(args);
    }

    private static List<String> Colors =  new ArrayList<>(Arrays.asList(
            "red",
            "green",
            "blue",
            "yellow",
            "pink",
            "brown",
            "purple",
            "black",
            "white"));

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello");

        button = new Button();
        button.setText("knopka");
        button.setStyle("-fx-background-color:red; -fx-fill:white;");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        AtomicInteger count = new AtomicInteger();
        button.setOnMouseClicked(mouseEvent -> buttonAction(mouseEvent,count,layout));

        Scene scene = new Scene(layout, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void buttonAction(MouseEvent event, AtomicInteger count, StackPane layout) {
        Random rand = new Random();
        var randomX = rand.nextInt(300) - 150;
        var randomY = rand.nextInt(300) - 150;
        var color = Colors.get(rand.nextInt(Colors.size()));

        Button newButton = new Button();
        newButton.setStyle(String.format("-fx-background-color: %s;",color));
        newButton.setTranslateX(randomX);
        newButton.setTranslateY(randomY);
        count.getAndIncrement();
        newButton.setText("knopka #" + count);
        newButton.setOnMouseClicked(mouszxc -> buttonAction(mouszxc,count,layout));
        layout.getChildren().add(newButton);
    }
}*/