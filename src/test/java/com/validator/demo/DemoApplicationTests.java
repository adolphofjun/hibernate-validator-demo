package com.validator.demo;

import com.alibaba.fastjson.JSON;
import com.validator.demo.bo.Goods;
import com.validator.demo.bo.InvoiceBO;
import com.validator.demo.bo.ItemBO;
import com.validator.demo.inter.GoodsChecks;
import com.validator.demo.inter.InvoioceChecks;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void contextLoads() {
        setUp();
        ItemBO itemBO = new ItemBO();
        InvoiceBO invoiceBO = new InvoiceBO();
        invoiceBO.setItemBO(itemBO);
        Goods goods = new Goods();
        invoiceBO.setGoods(goods);
        //1、使用validate()方法对一个给定的实体对象中定义的所有约束条件进行校验
        Set<ConstraintViolation<InvoiceBO>> constraintViolations =
                validator.validate(invoiceBO, Default.class);
       printInfo("v0",constraintViolations);

        //分组校验（GoodsChecks.class, InvoioceChecks.class需要校验的组）
        Set<ConstraintViolation<InvoiceBO>> constraintViolations1 =
                validator.validate(invoiceBO);
        printInfo("v1",constraintViolations1);


        //2、通过validateProperty()可以对一个给定实体对象的单个属性进行校验. 其中属性名称需要符合JavaBean规范中定义的属性名称.会忽略被验证属性上定义的@Valid.
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        InvoiceBO invoiceBO1 = new InvoiceBO();
//        Set<ConstraintViolation<InvoiceBO>> constraintViolations2 = validator.validateProperty(invoiceBO1, "itemBO");
//        printInfo("v2",constraintViolations2);
    }

    public void printInfo( String msgName,Set<ConstraintViolation<InvoiceBO>> constraintViolations){
        Iterator<ConstraintViolation<InvoiceBO>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()){
            ConstraintViolation<InvoiceBO> item = iterator.next();
            System.out.println(msgName+"==错误信息===" +item.getMessage());
            System.out.println(msgName+"==根实体对象===" +item.getRootBean());
            System.out.println(msgName+"==校验失败的值===" +item.getInvalidValue());
            System.out.println("========================================================");
        }
    }

}
