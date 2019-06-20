package base;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
public interface AccountService {
    boolean signIn(String login, String password);

    boolean signUp(String login, String password);
}
