- {[/oauth/authorize]}
- {[/oauth/authorize],methods=[POST]
- {[/oauth/token],methods=[GET]}
- {[/oauth/token],methods=[POST]}
- {[/oauth/check_token]}
- {[/oauth/error]}


## 密码模式
- 请求： http://localhost:8081/oauth/token?grant_type=sms&client_id=123456&client_secret=testClientSecret&mobile=17538142004&code=123456
- 响应： {"access_token":"950a7cc9-5a8a-42c9-a693-40e817b1a4b0","token_type":"bearer","refresh_token":"773a0fcd-6023-45f8-8848-e141296cb3cb","expires_in":27036,"scope":"select"}

- 刷新token请求：http://localhost:8081/oauth/token?grant_type=refresh_token&client_id=123456&client_secret=testClientSecret&refresh_token=fbe54060-5ea5-4f83-b114-182f075b8172
- 响应同上

## 客户端模式
- 请求：http://localhost:8081/oauth/token?grant_type=client_credentials&client_id=123456&client_secret=testClientSecret
- 响应：{"access_token":"6aeac7fb-e47b-440e-ba63-1181e7cab5af","token_type":"bearer","expires_in":431999,"scope":"all"}
- 不能刷新token

默认是通过Basic认证，即客户端id和秘钥，用：连接，然后经过Base64加码后，放到请求头 Authorization中

如需上面连接中，需对auth-server进行如下配置
```
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }
```