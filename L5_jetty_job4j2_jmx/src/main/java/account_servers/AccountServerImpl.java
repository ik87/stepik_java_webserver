package account_servers;
/**
 * @author Kosolapov Ilya (d_dexter@mail.ru)
 * @version 0.1
 * */
public class AccountServerImpl implements AccountServers{

    private Integer limit;
    public AccountServerImpl(Integer limit) {
        this.limit = limit;

    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }
}
