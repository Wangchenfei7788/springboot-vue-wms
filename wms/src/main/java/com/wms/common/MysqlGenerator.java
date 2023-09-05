package com.wms.common;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class MysqlGenerator {
    /**
     * 数据源配置（修改成MySQL）
     */
    protected static String URL = "jdbc:mysql://localhost:3306/wms?userSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
    protected static String USERNAME = "root";
    protected static String PASSWORD = "123456";
    //protected static String DRIVERNAME = "com.mysql.cj.jdbc.Driver";

    protected static DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD);

    // 处理 all 情况
    /**
     * 交互式的生成器（all）
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
//                全局配置
                .globalConfig((scanner,builder) ->
//                    builder就是globalConfig的构建器
                                builder.fileOverride() //覆盖已生成文件
                                        .outputDir(System.getProperty("user.dir")+"\\wms\\src\\main\\java")
                                        .author(scanner.apply("请输入你的用户名"))
                                        .enableSwagger()
                                        .commentDate("yyyy-MM-dd")
                                        .build() //构建器开始工作

                )
//                包配置
                .packageConfig(builder-> {
                    builder
                            .parent("com.wms")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "\\wms\\src\\main\\resources\\mapper"))
                            .build();
                })
//                注入配置
                .injectionConfig((builder) ->
                        builder.beforeOutputFile(
                                (a, b) -> log.warn("tableInfo: " + a.getEntityName())
                        )
                )
//                策略配置
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                .addTablePrefix("tb_")
                                .entityBuilder()
                                .enableChainModel()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .addTableFills(
                                        new Column("create_time", FieldFill.INSERT)
                                )
                                .controllerBuilder()
                                .enableRestStyle()
                                .enableHyphenStyle()
                                .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}


