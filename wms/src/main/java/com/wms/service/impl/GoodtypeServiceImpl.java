package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Goodtype;
import com.wms.entity.Storage;
import com.wms.mapper.GoodtypeMapper;
import com.wms.mapper.StorageMapper;
import com.wms.service.GoodtypeService;
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
public class GoodtypeServiceImpl extends ServiceImpl<GoodtypeMapper, Goodtype> implements GoodtypeService {
    @Resource
    private GoodtypeMapper goodtypeMapper;
    @Override
    public IPage pageCC(IPage<Goodtype> page, Wrapper wrapper) {
        return goodtypeMapper.pageCC(page,wrapper);
    }
}
