package com.fh.shoplongin;

import com.fh.exeception.AuthorizationExeception;
import com.fh.jwt.JwtUtis;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Order(3)
@Component
@Aspect
public class LoginAuthorization {



            @Around(value = "execution(* com.fh.controller.*.*(..))&&@annotation(loginAnnotation)")
            public Object loginAuthorization(ProceedingJoinPoint joinPoint,LoginAnnotation loginAnnotation){

                HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

                String tohen = request.getHeader("tohen");

                System.out.println(tohen);

                boolean blank = StringUtils.isBlank(tohen);

                if(StringUtils.isBlank(tohen)){

                    throw  new AuthorizationExeception(ServerEnum.TOKEN_TIMEOUT);
                }

                ResponseServer responseServer = JwtUtis.analysisTohen(tohen);

                if(responseServer.getCode() != 200){
                    throw  new AuthorizationExeception(ServerEnum.TOKEN_TIMEOUT);
                }

                Claims claims = (Claims) responseServer.getData();

                String  phone = (String) claims.get("phone");

                request.setAttribute("phone",phone);
                Object obj = null;

                try {
                    obj = joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return obj;
            }


}
