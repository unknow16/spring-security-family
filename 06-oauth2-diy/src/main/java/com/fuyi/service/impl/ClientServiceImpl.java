package com.fuyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fuyi.entity.ClientDetailsImpl;
import com.fuyi.mapper.ClientMapper;
import com.fuyi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2019/8/6 17:21
 * @Created by minfy
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDetailsImpl> allClients() {
        QueryWrapper<ClientDetailsImpl> clientDetailsQueryWrapper = Condition.create();
        return clientMapper.selectList(clientDetailsQueryWrapper);
    }
}
