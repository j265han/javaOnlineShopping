package org.example.services;

import org.example.po.UserPo;
import org.example.req.UserReq;

public interface UserService {

    UserPo findByUserName(String username);

    void register(UserReq userReq);

    void updatePwd(String newPwd);

    void updateAddr(UserPo userPo);
}
