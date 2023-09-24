package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 银行-账号表-2000000000
 * </p>
 *
 * @author tl
 * @since 2023-09-22
 */
@TableName("bank_account")
@ApiModel(value = "Account对象", description = "银行-账号表-2000000000")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("银行卡号")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("账户余额")
      private BigDecimal balance;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public BigDecimal getBalance() {
        return balance;
    }

      public void setBalance(BigDecimal balance) {
          this.balance = balance;
      }

    @Override
    public String toString() {
        return "Account{" +
              "id=" + id +
                  ", password=" + password +
                  ", balance=" + balance +
              "}";
    }
}
