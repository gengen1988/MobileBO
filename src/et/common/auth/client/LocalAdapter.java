package et.common.auth.client;

public class LocalAdapter extends Adapter {
    
    @Override
    protected void init() {
        log.info("ʹ��Ӧ����У��");
    }
    
    @Override
    protected Author getAuthor() {
        return new LocalAuthor();
    }

    @Override
    protected Parser getParser() {
        return new ETFrameworkParser();
    }

}
