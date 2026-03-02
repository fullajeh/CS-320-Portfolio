
import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) throw new IllegalArgumentException();
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        contacts.remove(contactId);
    }

    public void updateContact(String id,String f,String l,String p,String a){
        Contact c = contacts.get(id);
        if(c==null) throw new IllegalArgumentException();
        if(f!=null) c.setFirstName(f);
        if(l!=null) c.setLastName(l);
        if(p!=null) c.setPhone(p);
        if(a!=null) c.setAddress(a);
    }
}
