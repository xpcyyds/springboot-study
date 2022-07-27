package com.cdcompany.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("goods")
public class Goods {

    @TableId("goodsId")
    private Integer goodsId;
    private String goodsName;
    private String goodsClass;
    private String goodsPrice;

    public Goods(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods(Integer goodsId, String goodsName, String goodsClass, String goodsPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsClass = goodsClass;
        this.goodsPrice = goodsPrice;
    }

    public Goods(String goodsName, String goodsClass, String goodsPrice) {
        this.goodsName = goodsName;
        this.goodsClass = goodsClass;
        this.goodsPrice = goodsPrice;
    }

    public Goods() {
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(String goodsClass) {
        this.goodsClass = goodsClass;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


}
