package et.common.auth.client;

import et.common.auth.client.Author.Result;

public interface Parser {
    
    Object parse(Result result);

}
