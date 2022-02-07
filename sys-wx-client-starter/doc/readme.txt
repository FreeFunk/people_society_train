引入次启动器的项目需要配置配置文件中:
app.commonWxServiceUrl: http://wx.qhd12328.com/wxplant
非相同域名（包含localhost）的登录链接:
http://wx.qhd12328.com/wxplant/wx/login.jsn?d=http://ip:port/contextpath/wxLogin.jsn?dwr=登录成功后跳转的位置
相同域名的登录的登录链接：
http://wx.qhd12328.com/wxplant/wx/login.jsn?d=登录成功后跳转同域名下的服务地址
