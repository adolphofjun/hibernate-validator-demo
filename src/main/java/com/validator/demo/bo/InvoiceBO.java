package com.validator.demo.bo;

import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Data
public class InvoiceBO {

    @NotEmpty(message = "购方名称不可为空！")
    private String buyerName;
    @NotNull(message = "购方税号不可为空！")
    private String buyerTaxCode;
    //对象图:对象作为属性时需要校验时使用
    @Valid
    private ItemBO itemBO;
    @Valid
    private Goods goods;

    List<ItemBO> list;

}
