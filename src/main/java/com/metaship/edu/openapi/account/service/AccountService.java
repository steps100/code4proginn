package com.metaship.edu.openapi.account.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.metaship.edu.consts.log.LogHelper;
import com.metaship.edu.exception.DataException;
import com.metaship.edu.openapi.account.dto.AccountDTO;
import com.metaship.edu.openapi.exception.CustomException;
import com.metaship.edu.openapi.security.jwt.JwtUtils;
import com.metaship.edu.user.relate.model.AccountModel;
import com.metaship.edu.user.relate.service.IAccountService;
import com.metaship.edu.user.userinfo.enums.UserSourceEnum;
import com.metaship.edu.user.userinfo.model.UserModel;
import com.metaship.edu.user.userinfo.service.IUserQueryService;
import com.metaship.edu.user.userinfo.service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created by None on 2018/7/28 11:35.
 * Wangyn All Rights Reserved.
 */
@Service
public class AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Reference(version = "1.0", loadbalance = "random", cluster = "failover", retries = 2)
    private IUserQueryService userQueryService;
    @Reference(version = "1.0", loadbalance = "random", cluster = "failfast")
    private IUserService userService;
    @Reference(version = "1.0", loadbalance = "random", cluster = "failfast")
    private IAccountService accountService;
    @Autowired
    private ModelMapper modelMapper;


    public void create(String channel, AccountDTO accountDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(channel, JwtUtils.getSecretKey(channel)));
        LOGGER.info("创建用户并授权： {}", accountDTO);
        String accountId = accountDTO.getAccountId();
        //查询用户
        UserModel userModel;
        try {
            userModel = userQueryService.queryUserModelByAccountId(accountId);
        } catch (DataException e) {
            LOGGER.error(LogHelper.trace(e.getMessage()));
            throw new CustomException(e.getCode(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userModel != null) {
            return;
        }
        //创建用户
        LOGGER.info("创建新用户");
        userModel = new UserModel();
        insertUserModel(channel, accountDTO, userModel, accountId);
        //创建账户信息
        LOGGER.info("创建用户关联账户信息");
        insertAccountModel(accountDTO);
    }

    private void insertAccountModel(AccountDTO accountDTO) {
        AccountModel accountModel = new AccountModel();
        modelMapper.map(accountDTO, accountModel);
        accountService.insert(accountModel);
    }

    private void insertUserModel(String channel, AccountDTO accountDTO, UserModel userModel, String accountId) {
        userModel.setUserSource(UserSourceEnum.T.getCode());
        userModel.setChannel(channel);
        userModel.setNickname(accountDTO.getNickname());
        userModel.setAvatarUrl(accountDTO.getAvatarUrl());
        userModel.setGender(accountDTO.getGender());
        userModel.setRegion(accountDTO.getRegion());
        userModel.setBindPhone(accountDTO.getCellPhoneNumber());
        userModel.setMobile(accountDTO.getCellPhoneNumber());
        userModel.setGrade(accountDTO.getGrade());
        userModel.setCreateTime(accountDTO.getCreatedTime());
        userModel.setModifyTime(accountDTO.getModifiedTime());
        userModel.setOpenAccountId(accountId);
        userService.insert(userModel);
    }
}
