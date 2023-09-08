package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Good;

import com.wms.mapper.GoodMapper;

import com.wms.service.GoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2023-09-07
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Resource
    private GoodMapper goodMapper;
    @Override
    public IPage pageCC(IPage<Good> page, Wrapper wrapper) {
        return goodMapper.pageCC(page,wrapper);
    }
}
