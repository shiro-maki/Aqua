package com.example.springbootdemo.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;

import java.sql.Types;
import java.util.Collections;

public class Coder {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost/mybank", "root", "root")
                .globalConfig(builder -> {
                    builder.author("tl") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\YC\\bank"); // 指定输出目录
                })
//                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//                    if (typeCode == Types.SMALLINT) {
//                        // 自定义类型转换
//                        return DbColumnType.INTEGER;
//                    }
//                    return typeRegistry.getColumnType(metaInfo);
//
//                }))
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("springbootdemo")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\YC\\bank")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("bank_"); // 设置过滤表前缀
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
