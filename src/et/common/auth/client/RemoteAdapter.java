package et.common.auth.client;

public class RemoteAdapter extends Adapter {
    
    @Override
    protected void init() {
        log.info("ʹ��Զ�̣�CAS��У��");
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
