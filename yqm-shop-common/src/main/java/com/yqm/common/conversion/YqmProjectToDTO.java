/*
 * Copyright 2021 Wei xi mei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *
 * distributed under the License is distributed on an "AS IS" BASIS,
 *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 *
 * limitations under the License.
 */

package com.yqm.common.conversion;

import com.yqm.common.dto.YqmProjectDTO;
import com.yqm.common.entity.YqmProject;
import com.yqm.common.request.YqmProjectRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: weiximei
 * @Date: 2021/10/18 19:37
 * @微信: wxm907147608
 * @QQ: 907147608
 * @Email: 907147608@qq.com
 */
public class YqmProjectToDTO {

    public static YqmProjectDTO toYqmProjectDTO(YqmProject entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        YqmProjectDTO dto = new YqmProjectDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static YqmProject toYqmProject(YqmProjectDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        YqmProject entity = new YqmProject();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static YqmProjectRequest toYqmProjectRequest(YqmProject entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        YqmProjectRequest brandRequest = new YqmProjectRequest();
        BeanUtils.copyProperties(entity, brandRequest);
        return brandRequest;
    }

    public static YqmProject toYqmProject(YqmProjectRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }
        YqmProject entity = new YqmProject();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public static List<YqmProjectDTO> toYqmProjectDTOList(List<YqmProject> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<YqmProjectDTO> dtoList = entityList.stream().map(e -> toYqmProjectDTO(e)).collect(Collectors.toList());
        return dtoList;
    }

    public static List<YqmProject> toYqmProjectList(List<YqmProjectDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return null;
        }
        List<YqmProject> entityList = dtoList.stream().map(e -> toYqmProject(e)).collect(Collectors.toList());
        return entityList;
    }

}
