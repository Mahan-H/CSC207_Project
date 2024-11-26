package osiris.entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String access_code) {
        return new CommonUser(name, password, access_code);
    }
}
