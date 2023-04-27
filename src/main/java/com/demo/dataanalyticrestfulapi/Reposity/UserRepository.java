package com.demo.dataanalyticrestfulapi.Reposity;

import com.demo.dataanalyticrestfulapi.model.Account;
import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface UserRepository {
    @Result(column = "id", property = "userId")
    @Select(("select * from users_tb"))
    List<User> allUsers();

    @Results({
            @Result(column = "id", property = "userId"),
            @Result(column = "id", property = "accounts", many = @Many(select = "findAccountsByUserId"))
    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccounts();

    @Results({
            @Result(property = "accountName",column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "phoneNumber",column = "phone_number"),
            @Result(property = "transferLimit",column = "transferLimit"),
            @Result(property = "accountType", column = "account_type",
                    one = @One(select = "com.demo.dataanalyticrestfulapi.Reposity.AccountRepository.getAccountTypeByID"))
    })
    @Select("select * from useraccount_tb\n" +
            "    inner join account_tb a on a.id = useraccount_tb.account_id\n" +
            "    WHERE user_id = #{id};")
    List<Account> findAccountsByUserId(int id);

    List<User> findUserByUsername(String username);

    @Insert("insert into users_tb(username, gender, address) \n" +
            "values (#{user.username},#{user.gender},#{user.address})")
    int createNewUser(@Param("user") User user);

    @Result(property = "userId",column = "id")
    @Select("select * FROM users_tb where id = #{id}")
    User findUserByID(int id);

    @Update("update users_tb\n" +
            "set username = #{user.username}, gender = #{user.gender}, address = #{user.address}\n" +
            "where id = #{id}")
    int updateUser(User user, int id);

    @Delete("delete from users_tb\n" +
            "where id = #{id}")
    int removeUser(int id);
}
