## 前言
- 场景： 爱奇艺客户端应用使用qq方式登录
- 授权方式： 授权码
- 授权码流程： 

1. 用户访问客户端，后者将前者导向认证服务器。
2. 用户选择是否给予客户端授权。
3. 假设用户给予授权，认证服务器将用户导向客户端事先指定的”重定向URI”（redirection URI），同时附上一个授权码。
4. 客户端收到授权码，附上早先的”重定向URI”，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
5. 认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。

## auth-qq
持有用户qq账号信息

有认证服务器和资源服务器

## client-aiqiyi
qq的一个客户端应用，需先向qq申请client_id和秘钥等信息

## 获取token
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

## 请求获取qq资源
```
http://localhost:7000/qq/info/250577914?access_token=9f54d26f-5545-4eba-a124-54e6355dbe69 
```


