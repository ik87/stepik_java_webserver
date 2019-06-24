package account_servers;
/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 * */
@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {
    void setLimit(Integer limit);
    Integer getLimit();
}
