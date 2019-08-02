package com.fuyi.service.impl;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Date 2019/8/2 15:36
 * @Created by minfy
 */
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

    private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

    private static final String CLIENT_TABLE_NAME = "jr_client_details";

    private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
            + " from " + CLIENT_TABLE_NAME;

    private static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    private static final String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    private static final String DEFAULT_INSERT_STATEMENT = "insert into " + CLIENT_TABLE_NAME + " (" + CLIENT_FIELDS
            + ", client_id) values (?,?,?,?,?,?,?,?,?,?,?)";

    private static final String DEFAULT_UPDATE_STATEMENT = "update " + CLIENT_TABLE_NAME + " set "
            + CLIENT_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where client_id = ?";

    private static final String DEFAULT_UPDATE_SECRET_STATEMENT = "update " + CLIENT_TABLE_NAME
            + " set client_secret = ? where client_id = ?";

    private static final String DEFAULT_DELETE_STATEMENT = "delete from " + CLIENT_TABLE_NAME + " where client_id = ?";

    private String deleteClientDetailsSql = DEFAULT_DELETE_STATEMENT;

    private String findClientDetailsSql = DEFAULT_FIND_STATEMENT;

    private String updateClientDetailsSql = DEFAULT_UPDATE_STATEMENT;

    private String updateClientSecretSql = DEFAULT_UPDATE_SECRET_STATEMENT;

    private String insertClientDetailsSql = DEFAULT_INSERT_STATEMENT;

    private String selectClientDetailsSql = DEFAULT_SELECT_STATEMENT;

    public ClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);

        setDeleteClientDetailsSql(deleteClientDetailsSql);
        setFindClientDetailsSql(findClientDetailsSql);
        setInsertClientDetailsSql(insertClientDetailsSql);
        setSelectClientDetailsSql(selectClientDetailsSql);
        setUpdateClientDetailsSql(updateClientDetailsSql);
        setUpdateClientSecretSql(updateClientSecretSql);
    }
}
