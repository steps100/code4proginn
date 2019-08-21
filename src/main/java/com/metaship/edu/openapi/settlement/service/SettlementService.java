package com.metaship.edu.openapi.settlement.service;

import com.metaship.edu.openapi.settlement.dto.SettlementDTO;
import com.metaship.edu.openapi.settlement.dto.SettlementQueryDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by None on 2018/7/29 11:11.
 * Wangyn All Rights Reserved.
 */
@Service
public class SettlementService {
    public Integer getSettlements(LinkedList<SettlementDTO> list, String channel, SettlementQueryDTO settlementQueryDTO, Integer page, Integer count) {
        for (int i = 0; i < count; i++) {
            list.add(new SettlementDTO());
        }
        return 100;
    }
}
