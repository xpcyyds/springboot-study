package com.cdcompany.service;

import com.cdcompany.domain.Goods;
import com.cdcompany.domain.User;

import java.util.List;

public interface GoodsService {
    //添加商品
    public boolean saveGoods(Goods goods);

    //删除商品
    public boolean deleteGoods(Goods goods);

    //更改商品信息
    public boolean updateGoods(Goods goods);

    //查看单个商品信息
    public List<Goods> getByName(String goodsname);

    //查看所有商品信息
    public List<Goods> getAll();

    //查询商品总数
    public int selectCount();

    //更改商品ID
    public void updateID(Integer id,Integer ID);

    //查看商品是否存在
    public boolean findGoods(Goods goods);
}
