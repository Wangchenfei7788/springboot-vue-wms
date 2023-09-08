package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Good;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-07
 */
public interface GoodService extends IService<Good> {
    IPage pageCC(IPage<Good>page, Wrapper wrapper);
}
