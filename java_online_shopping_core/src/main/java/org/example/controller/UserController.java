package org.example.controller;

import org.example.po.RespCodePo;
import org.example.po.UserPo;
import org.example.req.UserReq;
import org.example.services.UserService;
import org.example.utils.JwtUtils;
import org.example.utils.Md5Utils;
import org.example.utils.ThreadUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/register")
    public RespCodePo register(@RequestBody UserReq userReq) {

        if(userReq.getUsername() == null || userReq.getPassword() == null) {
            return RespCodePo.error("The username and password cannot be empty");
        }

        if(userReq.getUsername().length() < 6 || userReq.getUsername().length() > 10) {
            return RespCodePo.error("The username must be 6-10 characters");
        }

        if(userReq.getPassword().length() < 8) {
            return RespCodePo.error("The password has to be 8 or more characters");
        }

        UserPo u = userService.findByUserName(userReq.getUsername());
        if(u==null){
            userService.register(userReq);
            return RespCodePo.success();
        } else {
            return RespCodePo.error("The username already exist");
        }
    }

    @RequestMapping("/login")
    public RespCodePo login(@RequestBody UserReq userReq){

        String username = userReq.getUsername();
        String password = userReq.getPassword();
        UserPo loginUser = userService.findByUserName(username);

        if(loginUser==null){
            return RespCodePo.error("Wrong Username");
        }

        if(Md5Utils.string2MD5(password).equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtils.generateToken(claims);
            stringRedisTemplate.opsForValue().set(token, token, 1, TimeUnit.DAYS);
            return RespCodePo.success(token);
        } else {
            return RespCodePo.error("Wrong Password");
        }
    }

    @PatchMapping("/updatePwd")
    public RespCodePo<UserPo> updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return RespCodePo.error("password cannot be empty");
        }

//        Map<String, Object> map = JwtUtils.parseToken(token);
        Map<String, Object> map = ThreadUtils.get();
        String username = (String) map.get("username");
        UserPo user = userService.findByUserName(username);

        if (!Md5Utils.passwordIsTrue(oldPwd, user.getPassword())) {
            return RespCodePo.error("Wrong password");
        }

        if(!rePwd.equals(newPwd)){
            return RespCodePo.error("Re-enter password need to be the same as the new password");
        }

        userService.updatePwd(newPwd);
        stringRedisTemplate.opsForValue().getOperations().delete(token);
        return RespCodePo.success(user);
    }

    @PatchMapping("/updateAddr")
    public RespCodePo<UserPo> updateAddr(@RequestBody UserPo userPo) {

        userService.updateAddr(userPo);

        return RespCodePo.success(userPo);
    }

}
