package com.eteng.mobile;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import et.common.auth.client.Adapter;
import et.common.auth.client.AdapterFactory;
import et.common.config.Constants;
import et.common.logging.Log;
import et.common.logging.LogFactory;
import et.common.vo.base.BusinessException;
import et.common.vo.base.SuperVO;

/**
 * @author GG
 *
 */
public abstract class MobileBO {
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, MobileBO.class);
    
    /**
     * ȷ�� BO �Ƿ���Ҫ���У��
     * @param condition ����
     * @return true ΪУ�飬false Ϊ��У�飻���� CAS ����У��
     */
    protected abstract boolean needSignIn(JSONObject condition);
    protected abstract JSONArray onCall(JSONObject params) throws Exception;
    
    private ArrayList<String> adaptReturning(JSONArray message) {
        ArrayList<String> rets = new ArrayList<String>();
        rets.add(null);
        rets.add("true");
        rets.add("");
        rets.add("");
        rets.add(message.toString());
        return rets;
    }
    
    public ArrayList<String> adaptPageList(SuperVO[] vos, JSONObject webInfos) throws Exception {
        JSONObject params = webInfos.getJSONObject("params");
        try {
            String username = params.getString("username");
            String password = params.getString("password");
            params.remove("username");
            params.remove("password");
            if (needSignIn(params)) {
                doAuth(username, password);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.warn("����֤��Ϣ");
        }
        return adaptReturning(onCall(params));
    }
    
    protected IUser getUser() {
        return null;
    }
    
    protected IUser getUser(String username) {
        return null;
    }
    
    private void doAuth(String username, String password) throws Exception {
        log.info("�û���֤");
        
        Adapter adapter = new AdapterFactory().newAdapter();
        Object result = adapter.auth(username, password);
        
        if ("false".equals(result)) {
            throw new BusinessException("not login");
        }
    }
    
    interface IUser {
        void pushMessage();
    }
    
    class MobileUser implements IUser {

        @Override
        public void pushMessage() {
        }
    }

}
