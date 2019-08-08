## 01-security-freemarker
功能描述: 基于freemarker视图模版的简单的用户登录

示例演示流程：

- http://localhost:8081/index  不需登录，有一个去登录页的按钮
- http://localhost:8081/hello  需要登录访问，未登录会跳转到登录页
- http://localhost:8081/login  登录页，用户名密码user/password

## 02-security-thymeleaf
功能描述: 基于thymeleaf视图模版的简单的用户登录

## 03-security-ip-login
功能描述: 使用spring-security自定义登录

## 04-security-jwt
功能描述: 完整的使用spring-security+jwt实现的登录授权

## 05-oauth2-simple
功能描述: 简单的oauth2的密码模式和客户端模式实现，基于内存用户

## 06-oauth2-diy
功能描述: 完整的oauth2的密码模式和客户端模式实现，基于DB用户+自定义授权方式

示例演示流程：

#### 密码模式
- 请求： http://localhost:8081/oauth/token?grant_type=sms&client_id=123456&client_secret=testClientSecret&mobile=17538142004&code=123456
- 响应： {"access_token":"950a7cc9-5a8a-42c9-a693-40e817b1a4b0","token_type":"bearer","refresh_token":"773a0fcd-6023-45f8-8848-e141296cb3cb","expires_in":27036,"scope":"select"}

- 刷新token请求：http://localhost:8081/oauth/token?grant_type=refresh_token&client_id=123456&client_secret=testClientSecret&refresh_token=fbe54060-5ea5-4f83-b114-182f075b8172
- 响应同上

#### 客户端模式
- 请求：http://localhost:8081/oauth/token?grant_type=client_credentials&client_id=123456&client_secret=testClientSecret
- 响应：{"access_token":"6aeac7fb-e47b-440e-ba63-1181e7cab5af","token_type":"bearer","expires_in":431999,"scope":"all"}
- 不能刷新token
 

## 07-oauth2-code
功能描述：oauth2的授权码模式应用场景，爱奇艺客户端应用使用qq方式登录，auth-qq 持有用户qq账号信息，有认证服务器和资源服务器，client-aiqiyi 是 qq的一个客户端应用，要想成为qq的客户端应用，需先向qq申请client_id和秘钥等信息。

授权码完整流程：

1. 用户访问客户端，后者将前者导向认证服务器。
2. 用户选择是否给予客户端授权。
3. 假设用户给予授权，认证服务器将用户导向客户端事先指定的”重定向URI”（redirection URI），同时附上一个授权码。
4. 客户端收到授权码，附上早先的”重定向URI”，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
5. 认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。

示例演示流程：

- 获取token

1. 尝试获取授权码： http://localhost:7000/oauth/authorize?client_id=aiqiyi&response_type=code&redirect_uri=http://localhost:7007/aiqiyi/qq/redirect
2. 提示输入qq用户名和密码
3. 登录校验通过后，qq会请求步骤1中redirect_uri的重定向url同时附带授权码code，url像这样 localhost:7007/aiqiyi/qq/redirect?code=FWmFx2
4. 在aiqiyi的回调端点中，使用 restTemplate 通过步骤3中重定向url中的code值， 向 qq 发送 token 的申请，之后qq响应token，格式如下

```
{
	"access_token":"9f54d26f-5545-4eba-a124-54e6355dbe69",
	"token_type":"bearer",
	"refresh_token":"f7c176a6-e949-41fa-906d-0dedb0f0c1f7",
	"expires_in":42221,
	"scope":"get_user_info get_fanslist"
}
```

- 通过token请求获取qq资源

```
http://localhost:7000/qq/info/250577914?access_token=9f54d26f-5545-4eba-a124-54e6355dbe69 
```

## 08-oauth2-sso
功能描述：使用spring security oauth2实现单点登录，oauth-server作为认证中心，oauth-client和oauth-client2分别为两个客户端，在其中一个客户端登录后，如果未登录会重定向至oauth-server进行登录，此时如果访问另一个客户端时则不需再次登录。

示例演示流程：

1. 启动认证服务端和客户端，前者端口为8881，后者分别为8882，8883
2. 访问http://localhost:8882/，点击 login，跳转到 securedPage 页面，页面调用资源服务器的受保护接口 /user ，会跳转到认证服务器的登录界面，要求进行登录认证。
3. 输入用户名密码，默认是后台配置的用户信息，用户名：admin， 密码：123 ，点击登录，之后返回客户端securedPage页面
4. 再访问 http://localhost:8883/ ，点击登录，结果不需要再进行登录，直接跳转到了 8883 的安全保护页面，因为在访问 8882 的时候已经登录过了。
5. 同理，假如先访问 8883 资源进行登录之后，访问 8882 也无需重复登录，到此，单点登录的案例实现就完成了。
