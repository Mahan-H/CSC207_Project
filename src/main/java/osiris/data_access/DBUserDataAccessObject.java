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

    private static final int SUCCESS_CODE = 200;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/schema_name";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Divnoor123";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwords";
    private static final String MESSAGE = "message";
    private static final String ACCESS_CODE = "accessCode";
    private static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private final UserFactory userFactory;
    private String currentEmail;

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

                pstmt.setString(ONE, username);

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
    public void setCurrentEmail(String name) {
        this.currentEmail = name;
    }

    @Override
    public boolean existsByName(String username) {
        final String sql = "SELECT ONE FROM users WHERE username = ?";
        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(ONE, username);
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

                pstmt.setString(ONE, user.getEmail());
                pstmt.setString(TWO, user.getPassword());
                pstmt.setString(THREE, user.getAccessCode());

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

                pstmt.setString(ONE, user.getEmail());
                pstmt.setString(TWO, user.getPassword());
                pstmt.setString(THREE, user.getAccessCode());

                pstmt.executeUpdate();
            }
        }
        catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error changing password: " + ex.getMessage(), ex);
        }
    }

    @Override
    public String getCurrentEmail() {
        return currentEmail;
    }

    public void saveVerificationCode(String email, String verificationCode) {
        final OkHttpClient client = new OkHttpClient.Builder().build();
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("verificationCode", verificationCode);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/saveVerificationCode")
                .post(body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getVerificationCode(String email) {
        final OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/getVerificationCode?email=%s", email))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return responseBody.getString("verificationCode");
            } else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
