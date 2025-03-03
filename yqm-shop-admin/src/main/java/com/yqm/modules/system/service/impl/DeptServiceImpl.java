/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yqmshop.cn
* 注意：
* 本软件为www.yqmshop.cn开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.yqm.modules.system.service.impl;

import com.yqm.common.service.impl.BaseServiceImpl;
import com.yqm.common.utils.QueryHelpPlus;
import com.yqm.dozer.service.IGenerator;
import com.yqm.exception.BadRequestException;
import com.yqm.modules.system.domain.Dept;
import com.yqm.modules.system.domain.Job;
import com.yqm.modules.system.domain.RolesDepts;
import com.yqm.modules.system.service.DeptService;
import com.yqm.modules.system.service.dto.DeptDto;
import com.yqm.modules.system.service.dto.DeptQueryCriteria;
import com.yqm.modules.system.service.mapper.DeptMapper;
import com.yqm.modules.system.service.mapper.JobMapper;
import com.yqm.modules.system.service.mapper.RolesDeptsMapper;
import com.yqm.utils.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author weiximei
* @date 2020-05-14
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "dept")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements DeptService {

    private final IGenerator generator;

    private final DeptMapper deptMapper;
    private final JobMapper jobMapper;
    private final RolesDeptsMapper rolesDeptsMapper;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(DeptQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Dept> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), DeptDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<Dept> queryAll(DeptQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Dept.class, criteria));
    }


    @Override
    public void download(List<DeptDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeptDto dept : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("名称", dept.getName());
            map.put("上级部门", dept.getPid());
            map.put("状态", dept.getEnabled());
            map.put("创建日期", dept.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 根据PID查询
     *
     * @param pid /
     * @return /
     */
    @Override
//    @Cacheable(key = "#p0")
    public List<Dept> findByPid(long pid) {
        DeptQueryCriteria criteria = new DeptQueryCriteria();
        criteria.setPid(pid);
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Dept.class, criteria));
    }

    /**
     * 构建树形数据
     *
     * @param deptDtos 原始数据
     * @return /
     */
    @Override
    public Object buildTree(List<DeptDto> deptDtos) {
        Set<DeptDto> trees = new LinkedHashSet<>();
        Set<DeptDto> depts= new LinkedHashSet<>();
        List<String> deptNames = deptDtos.stream().map(DeptDto::getName).collect(Collectors.toList());
        boolean isChild;
        DeptQueryCriteria criteria = new DeptQueryCriteria();
        List<Dept> deptList =  this.queryAll(criteria);
        for (DeptDto deptDto : deptDtos) {
            isChild = false;
            if ("0".equals(deptDto.getPid().toString())) {
                trees.add(deptDto);
            }
            for (DeptDto it : deptDtos) {
                if (it.getPid().equals(deptDto.getId())) {
                    isChild = true;
                    if (deptDto.getChildren() == null) {
                        deptDto.setChildren(new ArrayList<>());
                    }
                    deptDto.getChildren().add(it);
                }
            }
            if(isChild) {
                depts.add(deptDto);
                for (Dept dept : deptList) {
                    if(dept.getId().equals(deptDto.getPid()) && !deptNames.contains(dept.getName())){
                        depts.add(deptDto);
                    }
                }
            }
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDtos.size();

        Map<String,Object> map = new HashMap<>(2);
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)? deptDtos :trees);
        return map;
    }

    /**
     * 删除部门
     * @param deptIds
     */
    @Override
    public void delDepts(List<Long> deptIds){
        int jobCount = jobMapper.selectCount(Wrappers.<Job>lambdaQuery().in(Job::getDeptId,deptIds));
        int roleCount = rolesDeptsMapper.selectCount(Wrappers.<RolesDepts>lambdaQuery()
                .in(RolesDepts::getDeptId,deptIds));
        if(jobCount > 0) {
            throw new BadRequestException( "所选部门中存在与岗位关联，请取消关联后再试");
        }
        if(roleCount > 0) {
            throw new BadRequestException( "所选部门中存在与角色关联，请取消关联后再试");
        }
        this.removeByIds(deptIds);
    }

    /**
     * 获取待删除的部门
     *
     * @param deptList /
     * @param deptDtos /
     * @return /
     */
/*    @Override
    public Set<DeptDto> getDeleteDepts(List<Dept> deptList, Set<DeptDto> deptDtos) {

        for (Dept dept : deptList) {
            deptDtos.add((DeptDto)generator.convert(deptList,DeptDto.class));
            List<Dept> depts = Collections.singletonList(this.getOne(new LambdaQueryWrapper<Dept>().eq("id", dept.getId())));
            if(depts!=null && depts.size()!=0){
                getDeleteDepts(depts, deptDtos);
            }
        }
        return deptDtos;
    }*/

    /**
     * 根据角色ID查询
     *
     * @param id /
     * @return /
     */
    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptMapper.findDeptByRoleId(id);
    }
}
