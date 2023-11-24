import java.util.Scanner;

public class Phone implements PhoneInterface{

    private static Phone instance;
    private final Contacts contacts;
    private final Messages messages;

    public static synchronized Phone getInstance() {
        if (null == instance) {
            instance = new Phone();
        }
        return instance;
    }

    private Phone() {
        this.contacts = new Contacts();
        this.messages = new Messages();
    }

    static int getChoice(int contactsSize, Scanner in) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(in.nextLine());
                if (choice >= 0 && choice < contactsSize + 1) {
                    break;
                }
            } catch (Exception ignored) {

            }
            System.out.println("Enter the number between 1 and " + contactsSize + ", or '0' to quit");
        }
        return choice;
    }

    @Override
    public void phoneOn() {
        System.out.println("Phone starting...");
        Scanner myScanner = new Scanner(System.in);

        while (true) {
            int choice;
            System.out.println("""
                    ======================
                    Choose an option:
                      1. Manage Contacts
                      2. Messages
                      3. Quit
                    """);
            while (true) {
                try {
                    choice = Integer.parseInt(myScanner.nextLine());
                    if (choice > 0 && choice < 4) {
                        break;
                    }
                } catch(Exception ignored){

                }

                System.out.println("Enter the number between 1 and 3:");
            }

            System.out.println("Chosen int: " + choice);

            switch (choice){
                case 1:
                    this.getContacts().useContactsApp();
                    break;
                case 2:
                    this.getMessages().useMessagesApp();
                    break;
                case 3:
                    phoneOff();
                    return;
            }
        }
    }

    @Override
    public void phoneOff() {
        System.out.println("Phone switching off..." +
                "\nPhone switched off.");
    }

    public Contacts getContacts() {
        return contacts;
    }

    public Messages getMessages() {
        return messages;
    }

}