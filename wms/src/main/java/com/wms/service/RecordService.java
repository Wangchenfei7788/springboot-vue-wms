package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.wms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-08
 */
public interface RecordService extends IService<Record> {
    IPage pageCC(IPage<Record>page, Wrapper wrapper);
}
