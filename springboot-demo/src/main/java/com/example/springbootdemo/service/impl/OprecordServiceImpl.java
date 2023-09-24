package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.Oprecord;
import com.example.springbootdemo.mapper.OprecordMapper;
import com.example.springbootdemo.service.IOprecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 银行-操作流水表-2 服务实现类
 * </p>
 *
 * @author tl
 * @since 2023-09-22
 */
@Service
public class OprecordServiceImpl extends ServiceImpl<OprecordMapper, Oprecord> implements IOprecordService {

}
