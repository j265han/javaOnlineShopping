package org.example.interceptors;

import org.example.utils.JwtUtils;
import org.example.utils.ThreadUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("Authorization");

        try {

            String redisToken = stringRedisTemplate.opsForValue().get(token);
            if (redisToken==null){
                throw new RuntimeException();
            }
            Map<String, Object> claims = JwtUtils.parseToken(token);
            ThreadUtils.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        ThreadUtils.remove();
    }


}
