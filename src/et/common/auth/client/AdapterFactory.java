package et.common.auth.client;

import et.common.config.CommonConfig;

public class AdapterFactory {
    
    public Adapter newAdapter() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String adapterName = CommonConfig.getValue("common.mobile.auth.implement");
        return (Adapter) Class.forName(adapterName).newInstance();
    }
    
}
