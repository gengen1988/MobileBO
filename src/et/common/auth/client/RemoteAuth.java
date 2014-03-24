package et.common.auth.client;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.client.fluent.Request;

public class RemoteAuth implements Author {
    
    private String url;
    
    @Override
    public void init(JSONObject config) {
        url = "http://" + config.getString("remote") + "/mobile/auth";
    }

    @Override
    public Result auth(String username, String password) throws IOException {
        String result = Request.Get(url)
            .addHeader("username", username)
            .addHeader("password", password)
            .execute()
            .returnContent()
            .asString();
        
        if ("true".equals(result)) {
            return Result.SUCCESS;
        }
        return Result.NOT_MATCH;
    }

}
