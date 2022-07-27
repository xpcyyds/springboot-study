package com.cdcompany.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdcompany.dao.GoodsDao;
import com.cdcompany.domain.Goods;
import com.cdcompany.exception.SystemException;
import com.cdcompany.service.GoodsService;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDao goodsDao;

    /*
    1.添加商品操作@RequestParam("goodsId") String goodsId,
                            @RequestParam("goodsName") String goodsName,
                            @RequestParam("goodsClass") String goodsClass,
                            @RequestParam("goodsPrice") String goodsPrice
    */

    /**
     * 1.添加商品操作
     * @param goods
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result saveGoods(@RequestBody Goods goods) {
//        int id = goodsService.selectCount() + 1;
//        Goods goods = new Goods(goodsId,goodsName,goodsClass,goodsPrice);
        System.out.println("user add goodsName==="+goods.getGoodsName()+"   goodsClass==="+goods.getGoodsClass()+ " goodPrice==="+goods.getGoodsPrice());
        boolean flag1 = goodsService.findGoods(goods);
        boolean flag2 = goodsService.saveGoods(goods);
        String msg = flag1  ? "" : "数据添加失败，请重试！";
        return new Result(flag1 ? Code.ADD_OK : Code.ADD_ERR, msg);
    }

    /**
     * 2.删除商品操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result deleteGoods(@RequestParam("goodsId") Integer id) {
        Goods goods = new Goods(id);
        boolean flag = goodsService.deleteGoods(goods);
        Integer code = flag ? Code.DELETE_OK : Code.DELETE_ERR;
        String msg = flag  ? "" : "数据删除失败，请重试！";
        /*if(flag){
            int count = goodsService.selectCount(), ID;
            for(ID = id + 1; ID <= count + 1; ID++){
                goodsService.updateID(ID,ID-1);
            }
        }*/
//        goodsDao.deleteById(id);
        System.out.println("删除成功");
        return new Result(code, msg);
    }

    /*
    3.修改商品操作@RequestParam("goodsId") Integer id,
                              @RequestParam("goodsName") String goodsName,
                              @RequestParam("goodsClass") String goodsClass,
                              @RequestParam("goodsPrice") String goodsPrice
                              @RequestBody Goods goods
    */

    /**
     * 3.修改商品操作
     * @param goods
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Result updateGoods(@RequestBody Goods goods) {
//        Goods goods = new Goods(id,goodsName,goodsClass,goodsPrice);
        System.out.println("user add goodsName==="+goods.getGoodsName()+"   goodsClass==="+goods.getGoodsClass()+ " goodPrice==="+goods.getGoodsPrice());
        if(goods.getGoodsName().length() > 45){
            Integer code = 400;
            String msg = "输入字符过长，请重新输入";
            return new Result(code,msg);
        }
        boolean flag = goodsService.updateGoods(goods);
        /*boolean flag = true;
        goodsDao.updateById(goods);*/
        String msg = flag  ? "" : "数据更新失败，请重试！";
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, msg);
    }

    /*
    4.查询单个商品操作@RequestParam("query") String goodsName,@RequestParam("pageNum") Integer current, @RequestParam("pageSize") Integer size
    */

    /**
     * 4.查询商品操作
     * @param queryPage
     * @return
     */
    @RequestMapping(value = "/getGoods",method = RequestMethod.GET)
    public Result getByName(QueryPage queryPage) {
        System.out.println("query==="+queryPage.getQuery()+"pageNum==="+queryPage.getPageNum()+"pageSize==="+queryPage.getPageSize());
//        List<Goods> goodsList =  goodsService.getByName(goodsName);
        //1.进行模糊查询并利用wrapper容器进行
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("goods_Name",queryPage.getQuery());
        //2.接收wrapper容器中的数据进行分页操作
        IPage page = new Page(queryPage.getPageNum(),queryPage.getPageSize());
        goodsDao.selectPage(page,wrapper);
        //3.用goodList来接收分页操作后的数据
        List<Goods> goodsList =  page.getRecords();
        System.out.println(goodsList);

        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        //4.搜索进行模糊查询后的数据总数
        Long total = goodsDao.selectCount(wrapper);
        System.out.println(total);
        return new Result(code, goodsList, msg, total);
    }

    /*
    5.查询所有商品操作@RequestParam("username") String loginName@RequestParam("password") String loginPsw@RequestBody User user
    */
    /*@RequestMapping(value = "/getByName",method = RequestMethod.POST)
    @CrossOrigin
    public Result getAll(@RequestBody User user) {
        //User user = new User(loginName, loginPsw);
        System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
        System.out.println("查询所有商品成功");
        List<Goods> goodsList =  goodsService.getAll();
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        Integer total = goodsService.selectCount();
        return new Result(code,goodsList,msg,total);
    }*/

    /*
    6.分页查询
    */
    /*@RequestMapping(value = "/getAll",method = RequestMethod.POST)
    @CrossOrigin
    public Result getByPage(@RequestParam("pageNum") Integer current, @RequestParam("pageSize") Integer size) {
        //User user = new User(loginName, loginPsw);
//        System.out.println("user login success !!! username==="+user.getUsername()+"   password==="+user.getPassword());
        System.out.println("分页查询商品成功");
        IPage page = new Page(current,size);
        goodsDao.selectPage(page,null);
        List<Goods> goodsList =  page.getRecords();
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList != null ? "" : "数据查询失败，请重试！";
        Integer total = goodsService.selectCount();
        return new Result(code,goodsList,msg,total);
    }*/
}
