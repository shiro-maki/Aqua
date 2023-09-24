package com.example.springbootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 银行-操作流水表-2
 * </p>
 *
 * @author tl
 * @since 2023-09-22
 */
@TableName("bank_oprecord")
@ApiModel(value = "Oprecord对象", description = "银行-操作流水表-2")
public class Oprecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("流水号")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("卡号")
      private Integer aid;

      @ApiModelProperty("操作金额，正数存款，负数取款")
      private BigDecimal opmoney;

      @ApiModelProperty("交易费")
      private BigDecimal charge;

      @ApiModelProperty("操作时间, 默认系统当前时间")
      private LocalDateTime optime;

      @ApiModelProperty("备注: 存款,取款,转账,缴费")
      private String remark;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getAid() {
        return aid;
    }

      public void setAid(Integer aid) {
          this.aid = aid;
      }
    
    public BigDecimal getOpmoney() {
        return opmoney;
    }

      public void setOpmoney(BigDecimal opmoney) {
          this.opmoney = opmoney;
      }
    
    public BigDecimal getCharge() {
        return charge;
    }

      public void setCharge(BigDecimal charge) {
          this.charge = charge;
      }
    
    public LocalDateTime getOptime() {
        return optime;
    }

      public void setOptime(LocalDateTime optime) {
          this.optime = optime;
      }
    
    public String getRemark() {
        return remark;
    }

      public void setRemark(String remark) {
          this.remark = remark;
      }

    @Override
    public String toString() {
        return "Oprecord{" +
              "id=" + id +
                  ", aid=" + aid +
                  ", opmoney=" + opmoney +
                  ", charge=" + charge +
                  ", optime=" + optime +
                  ", remark=" + remark +
              "}";
    }
}
