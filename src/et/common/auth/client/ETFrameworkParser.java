package et.common.auth.client;

import et.common.auth.client.Author.Result;

public class ETFrameworkParser implements Parser {

    @Override
    public Object parse(Result result) {
        switch (result) {
        case SUCCESS:
            return "true";
        default:
            return "false";
        }
    }
    
}
