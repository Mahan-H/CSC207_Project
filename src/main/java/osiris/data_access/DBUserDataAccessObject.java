package osiris.data_access;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.use_case.change_password.ChangePasswordUserDataAccessInterface;
import osiris.use_case.grabtransactions.GrabTransactionUserDataAccessInterface;
import osiris.use_case.login.LoginUserDataAccessInterface;
import osiris.use_case.logout.LogoutUserDataAccessInterface;
import osiris.use_case.plaid.PlaidDataBaseUserAccessObjectInterface;
import osiris.use_case.signup.SignupUserDataAccessInterface;

/**
 * The Data Access Object (DAO) for user data.
 */
@Component
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        PlaidDataBaseUserAccessObjectInterface,
        GrabTransactionUserDataAccessInterface {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/osiris";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "csc207";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwords";
    private static final String ACCESS_CODE = "accessCode";
    private static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private final UserFactory userFactory;

    /**
     * Constructs a DBUserDataAccessObject with the specified UserFactory.
     *
     * @param userFactory The UserFactory to create user instances.
     */
    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public User get(String username) {
        final String sql = "SELECT username, passwords, accessCode FROM users WHERE username = ?";
        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        final String name = rs.getString(USERNAME);
                        final String passwords = rs.getString(PASSWORD);
                        final String accessCode = rs.getString(ACCESS_CODE);

                        return userFactory.create(name, passwords, accessCode);
                    }
                }
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error retrieving user: " + ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        final String sql = "SELECT 1 FROM users WHERE username = ?";
        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error checking if user exists: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO users (username, passwords, accessCode) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE passwords = VALUES(passwords), accessCode = VALUES(accessCode);";

        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, user.getUser());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getAccessCode());

                pstmt.executeUpdate();
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error saving user: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final String sql = "INSERT INTO users (username, passwords, accessCode) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE passwords = VALUES(passwords), accessCode = VALUES(accessCode);";
        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, user.getUser());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getAccessCode());

                pstmt.executeUpdate();
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error changing password: " + ex.getMessage(), ex);
        }
    }

    @Override
    public String getCurrentUser() {
        return null;
    }

    @Override
    public void setCurrentUser(String user) {

    }
}
