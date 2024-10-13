package com.lyz.dao;



import com.lyz.entities.UserItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    UserItem selectUserItemById(@Param("id") Long id);
}
