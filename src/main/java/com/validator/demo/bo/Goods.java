package com.validator.demo.bo;

import com.validator.demo.inter.GoodsChecks;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Goods {
    @NotNull(message = "子商品名称不可为空",groups = GoodsChecks.class)
    private String name;
    private BigDecimal price;
}
