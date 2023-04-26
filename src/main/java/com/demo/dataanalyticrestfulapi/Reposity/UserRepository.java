package com.demo.dataanalyticrestfulapi.Reposity;

import com.demo.dataanalyticrestfulapi.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface UserRepository {
    @Result(column = "id", property = "userId")
    @Select(("select * from users_tb"))
    List<User> allUsers();

    List<User> findUserByUsername(String username);

    @Insert("insert into users_tb(username, gender, address) \n" +
            "values (#{user.username},#{user.gender},#{user.address})")
    int createNewUser(@Param("user") User user);


    int updateUser(User user);

    @Result(property = "userId",column = "id")
    @Select("select * FROM users_tb where id = #{id}")
    User findUserByID(int id);

    int removeUser(int id);
}
