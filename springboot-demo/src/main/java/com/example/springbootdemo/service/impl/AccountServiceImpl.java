package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.Account;
import com.example.springbootdemo.mapper.AccountMapper;
import com.example.springbootdemo.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 银行-账号表-2000000000 服务实现类
 * </p>
 *
 * @author tl
 * @since 2023-09-22
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
