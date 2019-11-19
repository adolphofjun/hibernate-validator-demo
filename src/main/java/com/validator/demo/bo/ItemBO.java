package com.validator.demo.bo;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
@Data
public class ItemBO {

    @NotEmpty(message = "商品名称不可为空！")
    private String goodsName;
    private BigDecimal quantity;
}
