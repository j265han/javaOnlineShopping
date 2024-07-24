package org.example.services.impl;

import org.example.mapper.UserMapper;
import org.example.po.UserPo;
import org.example.req.UserReq;
import org.example.services.UserService;
import org.example.utils.Md5Utils;
import org.example.utils.ThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserPo findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(UserReq userReq) {

        String username = userReq.getUsername();

        String password = userReq.getPassword();

        String address = userReq.getAddress();

        String phone = userReq.getPhoneNo();

        String md5String = Md5Utils.string2MD5(password);

        userMapper.add(username, md5String, address, phone);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadUtils.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Utils.string2MD5(newPwd), id);
    }

    @Override
    public void updateAddr(UserPo userPo) {
        String address = userPo.getAddress();
        String phone = userPo.getPhoneNo();
        String username = userPo.getUsername();
        userMapper.updateAddr(address, phone, username);
    }
}
