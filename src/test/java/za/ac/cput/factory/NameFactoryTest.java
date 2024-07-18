package za.ac.cput.factory;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
public class NameFactoryTest {
    @Test
    public void testCreateName() {
        Name name = NameFactory.createName("John", "Fred", "Doe");
        assertNotNull(name);
        System.out.println(name);
    }
    @Test
    public void testCreateNameWithFail() {
        Name name = NameFactory.createName("John", "", "Doe");
        assertNull(name);
        System.out.println(name);
    }
}