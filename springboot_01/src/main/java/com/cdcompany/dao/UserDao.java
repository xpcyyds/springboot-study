package com.cdcompany.dao;

import com.cdcompany.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//TODO 添加@Mapper
@Mapper
public interface UserDao {
    @Select("select count(id) from studentregister where sname=#{username} and spassword=#{password}")
    public boolean selectUser(User user);

    @Select("select count(id) from studentregister where sname=#{username}")
    public boolean selectName(User user);

    //注册用户
    @Insert("insert into studentregister (sname,spassword) values(#{username},#{password})")
    public boolean insertUser(User user);

    //注销用户
    @Delete("delete from studentregister where sname=#{username}")
    public boolean deleteUser(User user);
}
