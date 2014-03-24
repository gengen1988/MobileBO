package et.common.auth.client;

import java.io.IOException;

import net.sf.json.JSONObject;
import et.common.auth.server.Checker;

public class LocalAuthor implements Author {
    
    String classname;
    
    @Override
    public void init(JSONObject config) {
        classname = config.getString("local");
    }

    @Override
    public Result auth(String username, String password) throws IOException {
        try {
            Checker checker = (Checker) Class.forName(classname).newInstance();
            checker.init();
            boolean success = checker.check(username, password);
            if (success) {
                return Result.SUCCESS;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return Result.NOT_MATCH;
    }

}
