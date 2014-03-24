package et.common.auth.server;


public interface Checker {
    
    void init() throws Exception;
    boolean check(String username, String password) throws Exception;

}
