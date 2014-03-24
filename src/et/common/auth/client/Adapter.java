package et.common.auth.client;

import java.io.IOException;

import net.sf.json.JSONObject;
import et.common.auth.client.Author.Result;
import et.common.config.CommonConfig;
import et.common.config.Constants;
import et.common.logging.Log;
import et.common.logging.LogFactory;

public abstract class Adapter {
    
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, Adapter.class);
    
    public Object auth(String username, String password) throws IOException, ClassNotFoundException {
        String config = CommonConfig.getValue("common.mobile.auth.config");
        JSONObject jsonConfig = JSONObject.fromObject(config);
        
        init();
        Author author = this.getAuthor();
        author.init(jsonConfig);
        Result result = author.auth(username, password);
        
        Object reply = this.getParser().parse(result);
        return reply;
    }
    
    protected abstract void init();
    protected abstract Author getAuthor();
    protected abstract Parser getParser();

}
