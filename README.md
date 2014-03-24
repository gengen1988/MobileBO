MobileBO
========

易腾时代手机平台独立后台中间件


使用方法
---

# 将 `build` 文件夹中的 jar 加入 buildpath
# 在 web.xml 中加入
```
<servlet>
    <servlet-name>MobileSubmitAction</servlet-name>
    <servlet-class>com.eteng.mobile.MobileSubmitAction</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>MobileSubmitAction</servlet-name>
    <url-pattern>/servlet/MobileSubmitAction</url-pattern>
</servlet-mapping> 
```
# 确保 common_config.xml 中，在 `<common>` 标签下有如下配置
```
<!-- 手机后台 -->
<mobile>
    <auth>
        <!-- 身份验证方法，该类必须实现et.common.auth.client.Adapter -->
        <implement>et.common.auth.client.LocalAdapter</implement>
        <!-- 配置 -->
        <config>{
            // 远程验证地址（CAS验证）范例
            // "remote": "192.168.0.60:8080",
            // 本地验证（应用内验证）范例
            "local": "et.common.auth.server.SimpleChecker"
        }</config>
    </auth>
    <!-- 使用数据库的帐套 -->
    <systemAccount>etframework</systemAccount>
</mobile>
```
# 继承 MobileBO，实现模板方法
