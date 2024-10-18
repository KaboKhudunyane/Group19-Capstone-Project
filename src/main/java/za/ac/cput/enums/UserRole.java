package za.ac.cput.enums;

public enum UserRole {
    ADMIN,
    USER;

    // Convert String to UserRole
    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }

    // Convert UserRole to String
    @Override
    public String toString() {
        return name();
    }
}
