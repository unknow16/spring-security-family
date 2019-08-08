-- auto-generated definition
create table jr_user_details
(
  id bigint not null comment '用户id'
    primary key,
  username varchar(50) null comment '用户名',
  password varchar(200) null comment '密码',
  mobile varchar(20) not null comment '手机号',
  email varchar(50) null comment '电子邮箱',
  type varchar(20) not null comment '用户类型',
  insert_timestamp timestamp default CURRENT_TIMESTAMP not null comment '插入时间',
  update_timestamp timestamp default CURRENT_TIMESTAMP not null comment '最近更新时间'
)
;

-- auto-generated definition
create table jr_client_details
(
  client_id bigint not null comment '第三方id'
    primary key,
  client_secret varchar(100) not null comment '第三方秘钥',
  resource_ids varchar(100) null comment '资源ids',
  scope varchar(100) null comment '权限范围',
  authorized_grant_types varchar(100) null comment '授权类型',
  web_server_redirect_uri varchar(200) null comment '回调域名',
  authorities varchar(100) null comment '权限集合',
  access_token_validity int not null comment 'access token有效期(秒)',
  refresh_token_validity int not null comment 'refresh token有效期(秒)',
  additional_information varchar(100) null comment '附加信息',
  autoapprove varchar(100) null comment '自动授权',
  insert_timestamp timestamp default CURRENT_TIMESTAMP not null comment '插入时间',
  update_timestamp timestamp default CURRENT_TIMESTAMP not null comment '最近更新时间'
)
;

INSERT INTO jr_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, insert_timestamp, update_timestamp) VALUES (123456, 'testClientSecret', null, 'all', 'refresh_token,client_credentials,password,userid,sms,image', null, null, 43200, 2592000, null, null, '2018-11-09 12:25:52', '2018-11-12 11:19:15');
INSERT INTO jr_user_details (id, username, password, mobile, email, type, insert_timestamp, update_timestamp) VALUES (1062638682487242754, 'aaaa', '$2a$10$3LJTbMe35XfuNjcjLdtfmu6RfUYeJUsMyrzlfG1uiSDa6sJK1/Gbe', '13322222222', '222@163.com', 'CUSTOMER', '2018-11-14 17:29:39', '2018-11-14 17:29:39');