package osiris.data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import osiris.entity.User;
import osiris.entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import osiris.use_case.change_password.ChangePasswordUserDataAccessInterface;
import osiris.use_case.grabtransactions.GrabTransactionUserDataAccessInterface;
import osiris.use_case.login.LoginUserDataAccessInterface;
import osiris.use_case.logout.LogoutUserDataAccessInterface;
import osiris.use_case.plaid.PlaidDataBaseUserAccessObjectInterface;
import osiris.use_case.signup.SignupUserDataAccessInterface;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DAO for user data.
 */
@Component
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface, PlaidDataBaseUserAccessObjectInterface,
        GrabTransactionUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/osiris";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "csc207";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwords";
    private static final String MESSAGE = "message";
    private static final String ACCESS_CODE = "accessCode";
    private final UserFactory userFactory;
    private String name;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        String sql = "SELECT username, passwords, accessCode FROM users WHERE username = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString(USERNAME);
                        String passwords = rs.getString(PASSWORD);
                        String accessCode = rs.getString(ACCESS_CODE);

                        return userFactory.create(name, passwords, accessCode);
                    }
                    else {
                        return null;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving user: " + e.getMessage(), e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    @Override
    public void setCurrentEmail(String name) {
        this.name = name;
    }

    @Override
    public boolean existsByName(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error checking if user exists: " + e.getMessage(), e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    @Override
    public void save(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
        String sql = "INSERT INTO users (username, passwords, accessCode) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE passwords = VALUES(passwords), accessCode = VALUES(accessCode);";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getAccessCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }


    @Override
    public void changePassword(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }

        String sql = "INSERT INTO users (username, passwords, accessCode) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE password = VALUES(password), accessCode = VALUES(accessCode);";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getAccessCode());

            pstmt.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error changing password: " + e.getMessage(), e);
        }
    }

    @Override
    public String getCurrentEmail() {
        return name;
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
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
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
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
