package com.fuyi.controller;

import com.fuyi.common.ApiResult;
import com.fuyi.entity.ClientDetailsImpl;
import com.fuyi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户端相关接口
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ApiResult<ClientDetailsImpl> allClients() {
        List<ClientDetailsImpl> clients = clientService.allClients();
        return ApiResult.success(clients);
    }
}
