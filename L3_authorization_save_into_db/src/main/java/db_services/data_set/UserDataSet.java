package db_services.data_set;

/**
 * User Profile
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
@SuppressWarnings("UnusedDeclaration")
public class UserDataSet {
    private Long id;
    private String login;
    private String password;

    public UserDataSet(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }
}
