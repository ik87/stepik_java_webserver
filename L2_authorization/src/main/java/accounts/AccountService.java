package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Defined service that responsibility for storage users profile
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public class AccountService {
    private Map<String, UserProfile> userProfileMap;

    public AccountService() {
        this.userProfileMap = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        userProfileMap.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUser(String login) {
        return userProfileMap.get(login);
    }
}
