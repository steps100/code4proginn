package com.metaship.edu.openapi.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.metaship.edu.base.context.CommonContext;
import com.metaship.edu.common.YESORNO;
import com.metaship.edu.consts.log.LogHelper;
import com.metaship.edu.exception.DataException;
import com.metaship.edu.openapi.exception.CustomException;
import com.metaship.edu.openapi.order.dto.OrderDTO;
import com.metaship.edu.res.resource.model.ProductOrderModel;
import com.metaship.edu.res.resource.service.IProductOrderService;
import com.metaship.edu.user.userinfo.model.UserModel;
import com.metaship.edu.user.userinfo.service.IUserQueryService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by None on 2018/7/28 21:10.
 * Wangyn All Rights Reserved.
 */
@Service
public class OrderService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Reference(version = "1.0", loadbalance = "random", cluster = "failfast")
    private IProductOrderService productOrderService;
    @Reference(version = "1.0", loadbalance = "random", cluster = "failover", retries = 2)
    private IUserQueryService userQueryService;

    /**
     * @param orderDTO
     * @return
     */
    public boolean addOrder(OrderDTO orderDTO) {
        String accountId = orderDTO.getAccountId();
        UserModel userModel;
        try {
            userModel = userQueryService.queryUserModelByAccountId(accountId);
        } catch (DataException e) {
            throw new CustomException(e.getCode(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ProductOrderModel productOrderModel = new ProductOrderModel();
        productOrderModel.setUserId(userModel.getId());
        //保存商品订单并且添加用户可用资源
        if (Objects.equals(orderDTO.getPurchaseAllResource(), YESORNO.YES.getCode())) {
            insertProductOrder(orderDTO, productOrderModel, userModel.getId());
        }
        return true;
    }

    private void insertProductOrder(OrderDTO orderDTO, ProductOrderModel productOrderModel, String userId)  {
        productOrderModel.setUserId(userId);
        productOrderModel.setOrderNO(orderDTO.getOrderNo());
        productOrderModel.setPurchaseTime(orderDTO.getPurchaseTime());
        productOrderModel.setInvalidTime(orderDTO.getInvalidTime());
        productOrderModel.setPrice(orderDTO.getPrice());
        productOrderModel.setStatus(orderDTO.getStatus().getCode());
        productOrderModel.setCreatedTime(orderDTO.getCreatedTime());
        productOrderModel.setModifiedTime(orderDTO.getCreatedTime());
        try {
            productOrderService.insertProductOrderAndStudentUsableResource(productOrderModel, userId);
        } catch (DataException e) {
            LOGGER.error(LogHelper.trace(e.getMessage()));
            throw new CustomException(e.getCode(), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
