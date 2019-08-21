package com.metaship.edu.openapi.resource.service;

import com.metaship.edu.openapi.resource.dto.ResourceDTO;
import com.metaship.edu.openapi.resource.dto.ResourceQueryDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by None on 2018/7/28 21:34.
 * Wangyn All Rights Reserved.
 */
@Service
public class ResourceService {

    public Integer getResources(LinkedList<ResourceDTO> lists, String channel, ResourceQueryDTO resourceQueryDTO, Integer page, Integer count) {
        for (int i = 0; i < count; i++) {
            ResourceDTO resourceDTO = new ResourceDTO();
            resourceDTO.setId(String.valueOf(i));
            lists.add(resourceDTO);
        }
        return 100;
    }

    public ResourceDTO getResourceById(String id) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(id);
        return resourceDTO;
    }
}
