package et.common.auth.client;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;

public interface Author {
    void init(JSONObject config);
    Result auth(String username, String password) throws ClientProtocolException, IOException, ClassNotFoundException;
    enum Result {
        SUCCESS,
        NO_USER,
        NOT_MATCH
    }
}