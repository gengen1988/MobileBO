package et.common.auth.client;

public class RemoteAdapter extends Adapter {
    
    @Override
    protected void init() {
        log.info("使用远程（CAS）校验");
    }

    @Override
    protected Author getAuthor() {
        return new RemoteAuth();
    }

    @Override
    protected Parser getParser() {
        return new ETFrameworkParser();
    }
    
}
