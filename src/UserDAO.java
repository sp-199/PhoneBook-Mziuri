import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private static final String INSERT_PHONE_BOOK_SQL = "INSERT INTO phone_book (name, lastname, phone_number, email) VALUES (?, ?, ?, ?)";

    public void insertContact(PhoneContact contact) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHONE_BOOK_SQL)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getNumber());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public void updateContact(PhoneContact contact) throws SQLException {
        String UPDATE_PHONE_BOOK_SQL = "UPDATE phone_book SET name=?, lastname=?, phone_number=?, email=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PHONE_BOOK_SQL)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getNumber());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public void updateContactName(String phoneNumber, String newName) throws SQLException {
        String UPDATE_NAME_SQL = "UPDATE phone_book SET name=? WHERE phone_number=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_SQL)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
        }
    }

    public void updateContactLastName(String phoneNumber, String newLastName) throws SQLException {
        String UPDATE_LASTNAME_SQL = "UPDATE phone_book SET lastname=? WHERE phone_number=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LASTNAME_SQL)) {
            preparedStatement.setString(1, newLastName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
        }
    }

    public void updateContactEmail(String phoneNumber, String newEmail) throws SQLException {
        String UPDATE_EMAIL_SQL = "UPDATE phone_book SET email=? WHERE phone_number=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMAIL_SQL)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.executeUpdate();
        }
    }
    public void removeContact(String phoneNumber) throws SQLException {
        String DELETE_USER_SQL = "DELETE FROM phone_book WHERE phone_number=?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.executeUpdate();
        }
    }
}
