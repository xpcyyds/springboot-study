package com.cdcompany.service.impl;

import com.cdcompany.dao.GoodsDao;
import com.cdcompany.domain.Goods;
import com.cdcompany.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public boolean saveGoods(Goods goods) {

        goodsDao.insertGoods(goods);
        return true;
    }

    @Override
    public boolean deleteGoods(Goods goods) {
        goodsDao.deleteGoods(goods);
        return true;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
        return true;
    }

    @Override
    public List<Goods> getByName(String goodsname) {
        return goodsDao.getByName(goodsname);
    }

    @Override
    public List<Goods> getAll() {
        return goodsDao.getAll();
    }

    @Override
    public int selectCount(){
        return goodsDao.count();
    }

    @Override
    public void updateID(Integer id,Integer ID){
        goodsDao.updateID(id,ID);
    }

    @Override
    public boolean findGoods(Goods goods) {
        boolean bl = goodsDao.selectUser(goods);
        return bl;
    }
}
