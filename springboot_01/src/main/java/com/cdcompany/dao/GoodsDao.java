package com.cdcompany.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdcompany.domain.Goods;
import com.cdcompany.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface GoodsDao extends BaseMapper<Goods> {
    //1.添加商品操作insert into goods values(null,#{goodsname},#{goodsclass},#{goodsprice})
    @Insert("insert into goods (goodsId,goods_Name,goods_Class,goods_Price) values(#{goodsId},#{goodsName},#{goodsClass},#{goodsPrice})")
    public void insertGoods(Goods goods);

    //2.删除商品操作
    @Delete("delete from goods where goodsId=#{goodsId}")
    public void deleteGoods(Goods goods);

    //3.修改商品操作
    @Update("update goods set goods_Name=#{goodsName} , goods_Class=#{goodsClass} , goods_Price=#{goodsPrice} where goodsId=#{goodsId}")
    public void updateGoods(Goods goods);

    //4.查询单个商品
    @Select("select * from goods where goods_Name like concat('%',#{goodsName},'%')")
    public List<Goods> getByName(String goodsName);

    //5.查询全部商品
    @Select("select * from goods")
    public List<Goods> getAll();

    //6.查询商品总数
    @Select("select count(*) from goods")
    public int count();

    //7.更改商品ID
    @Update("update goods set goodsId=#{ID} where goodsId=#{id}")
    public void updateID(Integer id,Integer ID);

    //8.查看商品ID是否存在
    @Select("select count(goodsId) from goods where goodsId=#{goodsId}")
    public boolean selectUser(Goods goods);
}
