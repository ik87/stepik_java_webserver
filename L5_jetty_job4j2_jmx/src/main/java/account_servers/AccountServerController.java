package account_servers;

/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 * */
public class AccountServerController implements AccountServerControllerMBean {

    private AccountServers accountServers;

    public AccountServerController(AccountServers accountServers) {
        this.accountServers = accountServers;
    }

    @Override
    public void setLimit(Integer limit) {
        accountServers.setLimit(limit);

    }

    @Override
    public Integer getLimit() {
        return accountServers.getLimit();
    }
}
