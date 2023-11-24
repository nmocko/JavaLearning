public class Message {

    private final Contact contact;
    private final String text;

    public Message(Contact contact, String text) {
        this.contact = contact;
        this.text = text;
    }

    public Contact getContact() {
        return contact;
    }

    public String getText() {
        return text;
    }

}
