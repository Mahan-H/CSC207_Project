package osiris.entity;

import org.springframework.stereotype.Component;

/**
 * Factory for creating CommonUser objects.
 */
@Component
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String access_code) {
        return new CommonUser(name, password, access_code);
    }
}
