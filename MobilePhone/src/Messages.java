import java.util.ArrayList;
import java.util.Scanner;

public class Messages extends MessagesAbstract{

    private static ArrayList<Message> messagesDb = new ArrayList<>();

    private static final Scanner in = new Scanner(System.in);

    public static ArrayList<Message> getMessage() {
        return messagesDb;
    }

    public Messages() {
        super();
    }


    public void useMessagesApp() {
        System.out.println("""
                ========================================
                Welcome in messages section
                
                """);
        while(true) {
            System.out.println("""                         
                    1. Show all messages
                    2. Write new message
                    3. Go back to the previous menu
                    
                    Enter a number to choose option:""");

            int choice = 0;

            while(true) {
                try {
                    choice = Integer.parseInt(in.nextLine());
                    if (choice > 0 && choice < 6) {
                        break;
                    } else {
                        throw new Exception("Number must be integer between 1 and 2");
                    }
                } catch (Exception e) {
                    System.out.println("You have to enter integer between 1 and 2");
                }
            }

            switch (choice) {
                case 1:
                    seeAllMessages();
                    break;
                case 2:
                    newMessage();
                    break;
                case 3:
                    System.out.println("Quiting messages...");
                    return;

            }
        }
    }

    @Override
    public void seeAllMessages() {
        if (!messagesDb.isEmpty()) {
            System.out.printf("%-5s%-20s%-13s%-10s\n", "No.", "CONTACT", "NUMBER", "MESSAGE");
            for (int i=0; i<messagesDb.size(); i++) {
                Contact curContact = messagesDb.get(i).getContact();
                String printName = curContact.getName();
                String printNum = curContact.getNumber();
                if (printName.length() > 19) {
                    printName = printName.substring(0, 16) + "...";
                }
                if (printNum.length() > 9) {
                    printNum = printNum.substring(0, 9) + "...";
                }
                System.out.printf("%-5d%-20s%-13s%-10s\n", (i+1), printName, printNum, messagesDb.get(i).getText());
            }
        } else {
            System.out.println("Message list is empty.");
        }
    }

    @Override
    public void newMessage() {
        if (!Contacts.getContactsDb().isEmpty()) {
            System.out.println("Select message receiver number or enter 0 to quit: ");
            Contacts.showAllContacts();
            int choice;
            int contactsSize = Contacts.getContactsDb().size();
            choice = Phone.getChoice(contactsSize, in);
            if (choice != 0) {
                Contact receiver = Contacts.getContactsDb().get(choice-1);
                String message;
                System.out.println("Enter message to send:\n");
                message = in.nextLine();
                Message newMessage = new Message(receiver, message);
                messagesDb.add(newMessage);

            }
        } else {
            System.out.println("Contact list is empty, add contact first.");
        }
    }
}
