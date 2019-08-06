package com.fuyi.service;

import com.fuyi.entity.ClientDetailsImpl;

import java.util.List;

/**
 * 客户端相关服务
 */
public interface ClientService {

    List<ClientDetailsImpl> allClients();

}
