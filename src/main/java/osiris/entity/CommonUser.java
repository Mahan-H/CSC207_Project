package osiris.entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final String access_code;

    public CommonUser(String name, String password, String access_code) {
        this.name = name;
        this.password = password;
        this.access_code = access_code;
    }

    @Override
    public String getUser() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getAccessCode() {
        return access_code;
    }

    @Override
    public String getItemID() {
        return "";
    }

}
