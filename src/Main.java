import javafx.application.Application;
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
            VBox vboxRemove=new VBox(new Label("Your Phone book is empty!"));
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
        VBox vbox = new VBox(new Button("Edit Contact"));
        Scene scene = new Scene(vbox, 300, 200);
        editStage.setScene(scene);
        editStage.show();
    }

    private void showDisplayContactsWindow() {
        Stage displayStage = new Stage();
        displayStage.setTitle("Display Contacts");
        VBox vboxdisplay = new VBox();
        if(contacts.isEmpty()){
            vboxdisplay.getChildren().add(new Label("Your Phonebook is Empty!"));
        }else {
            int index = 0;
            for (PhoneContact contact : contacts) {
                index++;
                vboxdisplay.getChildren().add(new Label("N" + index + ")\n" + contact.displayContact() + "\n"));
            }
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