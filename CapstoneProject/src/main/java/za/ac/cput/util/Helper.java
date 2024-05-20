package za.ac.cput.util;
import java.util.UUID;
public class Helper {
    // Method to check if a string is null or empty
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
    // Method to generate a unique ID using UUID
    public static String generateID(){
        return UUID.randomUUID().toString(); // Returns a randomly generated UUID converted to string
    }
}