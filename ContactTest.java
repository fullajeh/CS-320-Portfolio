import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @Test
    void testAddContactSuccess() {
        ContactService service = new ContactService();
        Contact c = new Contact("1","John","Doe","5555555555","123 Main");

        assertDoesNotThrow(() -> service.addContact(c));
    }

    @Test
    void testAddDuplicateContactId() {
        ContactService service = new ContactService();
        Contact c1 = new Contact("1","John","Doe","5555555555","123 Main");
        Contact c2 = new Contact("1","Jane","Doe","5555555555","456 Main");

        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(c2));
    }

    @Test
    void testAddNullContact() {
        ContactService service = new ContactService();

        // Must be Exception, not IllegalArgumentException
        // because contact.getContactId() may run first
        assertThrows(Exception.class, () -> service.addContact(null));
    }

    @Test
    void testDeleteExistingContact() {
        ContactService service = new ContactService();
        Contact c = new Contact("1","John","Doe","5555555555","123 Main");

        service.addContact(c);
        assertDoesNotThrow(() -> service.deleteContact("1"));
    }

    @Test
    void testDeleteNonexistentContactDoesNotThrow() {
        ContactService service = new ContactService();

        // Method does not throw for non-existent IDs
        assertDoesNotThrow(() -> service.deleteContact("999"));
    }
    @Test
    void testDeleteNullIdDoesNotThrow() {
        ContactService service = new ContactService();
        assertDoesNotThrow(() -> service.deleteContact(null));
    }

    @Test
    void testUpdateNonexistentContact() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () ->
            service.updateContact("999","John","Doe","5555555555","123 Main"));
    }

    @Test
    void testUpdateExistingContactSuccess() {
        ContactService service = new ContactService();
        Contact c = new Contact("1","John","Doe","5555555555","123 Main");

        service.addContact(c);

        // Executes success branch
        assertDoesNotThrow(() ->
            service.updateContact("1","Jane","Smith","5555555555","456 Main"));
    }

    @Test
    void testUpdateNullId() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () ->
            service.updateContact(null,"John","Doe","5555555555","123 Main"));
    }
    @Test
    void testUpdateContactAllFieldsValid() {
        ContactService service = new ContactService();
        Contact c = new Contact("1","John","Doe","5555555555","123 Main");

        service.addContact(c);

        // Every field is valid and within limits
        assertDoesNotThrow(() ->
            service.updateContact(
                "1",
                "Alice",          // valid first name
                "Johnson",        // valid last name
                "4444444444",     // valid phone (10 digits)
                "456 Elm Street"  // valid address (< 30 chars)
            )
        );
    }
    @Test
    void testUpdateContactTwiceAllBranches() {
        ContactService service = new ContactService();
        Contact c = new Contact("1","John","Doe","5555555555","123 Main");

        service.addContact(c);

        // First update
        assertDoesNotThrow(() ->
            service.updateContact(
                "1",
                "Alice",
                "Johnson",
                "4444444444",
                "456 Elm St"
            )
        );

        // Second update with DIFFERENT valid values
        assertDoesNotThrow(() ->
            service.updateContact(
                "1",
                "Bob",
                "Smith",
                "3333333333",
                "789 Oak Avenue"
            )
        );
    }
}