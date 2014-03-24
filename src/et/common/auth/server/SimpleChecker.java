package et.common.auth.server;

import et.common.bs.industry.SuperDMO;
import et.common.config.Constants;
import et.common.logging.Log;
import et.common.logging.LogFactory;
import et.common.security.UnixCrypt;
import et.common.vo.pub.UserVO;

public class SimpleChecker implements Checker {
    
    protected static Log log = LogFactory.getLog(Constants.COMMON_CONFIGKEY, SimpleChecker.class);
    
    @Override
    public void init() throws Exception {
        log.info("使用平台演示方法验证用户");
    }

    @Override
    public boolean check(String username, String password) throws Exception {
        SuperDMO dmo = new SuperDMO();
        dmo.setWherePart("pub_user.user_code=?");
        dmo.setParams(username);
        
        UserVO[] users = (UserVO[]) dmo.queryBySQL(UserVO.class);
        if (users != null) {
            for (UserVO user: users) {
                if (UnixCrypt.matches(user.getUser_password(), password)) {
                    return true;
                }
            }
        }
        return false;
    }

}
