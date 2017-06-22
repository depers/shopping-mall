package com.fmall.dao;

import com.fmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String username);

    int checkUserEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password")String password);

    String selectQuestionByUsername(String username);

}