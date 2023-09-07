package com.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goodtype;

import com.wms.service.GoodtypeService;

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
@RequestMapping("/goodtype")
public class GoodtypeController {

    @Autowired
    private GoodtypeService goodtypeService;
    @PostMapping("/save")
    public Result save(@RequestBody Goodtype goodtype){
        return goodtypeService.save(goodtype)?Result.suc():Result.fail();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Goodtype goodtype){
        return goodtypeService.updateById(goodtype)?Result.suc():Result.fail();
    }

    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return goodtypeService.removeById(id)?Result.suc():Result.fail();
    }

    @PostMapping("/listPage")//分页
    public Result listPage(@RequestBody QueryPageParam query){

        System.out.println("num==="+query.getPageNum());
        System.out.println("size==="+query.getPageSize());

        HashMap param = query.getParam();
        String name = (String)param.get("name");

        System.out.println("name==="+(String) param.get("name"));


        Page<Goodtype> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goodtype> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Goodtype::getName,name);
        }


        //IPage result = userService.pageC(page);
        IPage result = goodtypeService.pageCC(page,lambdaQueryWrapper);

        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(), result.getTotal());
    }
}
