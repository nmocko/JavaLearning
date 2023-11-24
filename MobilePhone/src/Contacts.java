import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts implements ContactsInterface {

    private static ArrayList<Contact> contactsDb = new ArrayList<>();

    private static final Scanner in = new Scanner(System.in);

    public static ArrayList<Contact> getContactsDb() {
        return contactsDb;
    }


    public Contacts() {
    }


    public static void showAllContacts() {
        if (!contactsDb.isEmpty()) {
            int contactsSize = contactsDb.size();
            System.out.format("\n%-5s%-20s%-10s\n", "No.", "Name", "Number");
            for (int i = 0; i < contactsSize; i++) {
                String printName = contactsDb.get(i).getName();
                if (contactsDb.get(i).getName().length() > 19) {
                    printName = contactsDb.get(i).getName().substring(0, 16) + "...";
                }
                System.out.format("%-5s%-20s%-10s\n", i + 1, printName, contactsDb.get(i).getNumber());
            }
            System.out.println();
        }
        else {
            System.out.println("Contact list is empty");
        }

    }


    @Override
    public void addNewContact() {

        System.out.println("\nEnter name of contact or enter to quit:");
        String name = in.nextLine();

        if (name.isEmpty()) {
            return;
        }

        while (true) {
            System.out.println("Enter number od contact or 'Q' to quit:");
            String number = in.nextLine();

            if (number.equals("Q")) {
                return;
            }

            Pattern pattern = Pattern.compile("^\\+?[0-9]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(number);
            boolean matchFound = matcher.find();

            if (!matchFound) {
                System.out.println("Invalid number, number have to contain only numbers and eventually + at the beginning");
                continue;
            }

            Contact newContact = new Contact(name, number);
            contactsDb.add(newContact);
            System.out.println("Successfully added contact :)");
            return;

        }
    }

    @Override
    public void searchContact() {
        if (!contactsDb.isEmpty()) {
            StringBuilder found = new StringBuilder();
            System.out.println("Enter name or phone number to find:\n");
            String toFind = in.next(); // User input
            Pattern pattern = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);
            for (Contact contact : contactsDb) {
                Matcher nameMatch = pattern.matcher(contact.getName());
                Matcher numMatch = pattern.matcher(contact.getNumber());
                boolean nameFind = nameMatch.find();
                boolean numFind = numMatch.find();
                if (nameFind || numFind) {
                    // founded.append("Found contact: " + contact.getName() + "  " + contact.getNumber() + "\n");
                    found.append("Found contact: ").append(contact.getName()).append("  ").append(contact.getNumber()).append("\n");

                }

            }
            if (!found.isEmpty()) {
                System.out.println("Found contacts:\n" + found);
            } else {
                System.out.println("No contacts found for a given phrase :(");
            }
        }
        else {
            System.out.println("The contact list is empty.");
        }
    }

    @Override
    public void deleteContact() {
        if (!contactsDb.isEmpty()) {
            System.out.println("Press '0' to quit or chose a contact number to delete:\n");
            showAllContacts();
            int contactsSize = contactsDb.size();
            int choice = Phone.getChoice(contactsSize, in);
            if (choice == 0) {
                return;
            } else {
                Contact contactDel = contactsDb.get(choice - 1);
                contactsDb.remove(choice - 1);
                System.out.println("Deleted contact: " + contactDel.getName() + "  " + contactDel.getNumber());
            }
        }
        else {
            System.out.println("Contact list is empty");
        }
    }

    @Override
    public void useContactsApp() {
        System.out.println("""
                ========================================
                Welcome in contacts section
                    
                """);
        while(true) {
            System.out.println("""                
                    1. Show all contacts
                    2. Add a new contact
                    3. Search for a contact
                    4. Delete a contact
                    5. Go back to the previous menu
                    
                    Enter a number to chose option:""");

            int choice = 0;

            while (true) {
                try {
                    choice = Integer.parseInt(in.nextLine());
                    if (choice > 0 && choice < 6) {
                        break;
                    }
                } catch(Exception ignored){
                    ;
                }

                System.out.println("Enter the number between 1 and 5:");
            }

            switch (choice) {
                case 1:
                    showAllContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Quiting contacts...");
                    return;

            }
        }
    }
}
