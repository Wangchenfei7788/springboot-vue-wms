package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Good;
import com.wms.service.GoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2023-09-07
 */
@RestController
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodService goodService;
    @PostMapping("/save")
    public Result save(@RequestBody Good good){
        return goodService.save(good)?Result.suc():Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Good good){
        return goodService.updateById(good)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return goodService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")//分页
    public Result listPage(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        String goodType = (String)param.get("goodType");
        String storage = (String)param.get("storage");


        System.out.println("name==="+(String) param.get("name"));


        Page<Good> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Good> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Good::getName,name);
        }
        if(StringUtils.isNotBlank(goodType) && !"null".equals(goodType)){
            lambdaQueryWrapper.eq(Good::getGoodType,goodType);
        }
        if(StringUtils.isNotBlank(storage) && !"null".equals(storage)){
            lambdaQueryWrapper.eq(Good::getStorage,storage);
        }


        //IPage result = userService.pageC(page);
        IPage result = goodService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(), result.getTotal());
    }
}
