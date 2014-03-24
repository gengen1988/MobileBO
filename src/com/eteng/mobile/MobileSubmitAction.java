package com.eteng.mobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import et.common.bc.client.ManClientEnvironment;
import et.common.config.CommonConfig;
import et.common.facade.CCommonFascadeImpl;
import et.common.facade.ICCommonFascade;
import et.common.servlet.ConfigKeyControl;
import et.common.vo.lang.UFDate;
import et.common.vo.pub.CorpVO;
import et.common.vo.sys.SystemAccountVO;
import et.common.web.ParamInit;

public class MobileSubmitAction extends HttpServlet {
    
    private static final long serialVersionUID = -8854915382674017439L;
    private String systemAccount;
    
    final private String BODY_CLASSNAME = "et.common.vo.query.QueryVO";
    final private String CONFIGKEY_SYSTEMACCOUNT = "common.mobile.systemAccount";
    final private String WEBINFO_COLLECTION = "WebInfo_Collection";
    final private String RETURN_TYPE = "application/json";
    final private String RETURN_CHARSET = "utf-8";
    
    @Override
    public void init() throws ServletException {
        this.systemAccount = CommonConfig.getValue(CONFIGKEY_SYSTEMACCOUNT);
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(RETURN_TYPE);
        resp.setCharacterEncoding(RETURN_CHARSET);
        String info = ParamInit.getString(req, WEBINFO_COLLECTION);
        JSONObject webInfoCollection = JSONObject.fromObject(info);
        
        try {
            this.initClientEnviroment();
            String result = this.invokeBo(webInfoCollection);
            resp.getWriter().println(result);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void initClientEnviroment() {
        ManClientEnvironment env = new ManClientEnvironment();
        
        CorpVO corp = new CorpVO();
        corp.setCorp_id("dummy");
        UFDate date = new UFDate(System.currentTimeMillis());
        SystemAccountVO account = new SystemAccountVO(systemAccount);
        
        env.setCurrSysAccount(account);
        env.setCurrDate(date);
        env.setCurrCorp(corp);
        
        ConfigKeyControl.setEnv(env);
    }

    private String invokeBo(JSONObject webInfoCollection) throws Exception {
        ICCommonFascade iFascade = new CCommonFascadeImpl();
        return iFascade.invokeBusinessImpl(null, webInfoCollection, BODY_CLASSNAME);
    }
}
