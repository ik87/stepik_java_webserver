package db_services.data_set;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User Profile
 *
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 */
@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name = "users")
public class UserDataSet  implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", unique = true, updatable = false)
    private String login;
    @Column(name = "password")
    private String password;

    public UserDataSet(String login, String password) {
        this.login = login;
        this.password = password;
    }

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet() {
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
