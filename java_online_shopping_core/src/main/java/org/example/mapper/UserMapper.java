package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.po.UserPo;

@Mapper
public interface UserMapper extends BaseMapper<UserPo> {

    @Select("select * from user_info where username = #{username}")
    UserPo findByUserName(String username);

    @Insert("insert into user_info (username, password, created_time, updated_time, address, phone_no) VALUES"+
            " (#{username}, #{md5String}, now(), now(), #{address}, #{phoneNo})")
    void add(String username, String md5String, String address, String phoneNo);

    @Update("update user_info set password=#{s}, updated_time=now() where id=#{id}")
    void updatePwd(String s, Integer id);

    @Update("update user_info set address = #{address}, phone_no = #{phone} where username = #{username}")
    void updateAddr(String address, String phone, String username);

}
