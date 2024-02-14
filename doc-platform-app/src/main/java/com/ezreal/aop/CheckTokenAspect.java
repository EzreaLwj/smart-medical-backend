package com.ezreal.aop;

import cn.hutool.core.util.StrUtil;
import com.ezreal.types.common.CheckToken;
import com.ezreal.types.common.Constants;
import com.ezreal.types.exception.BusinessException;
import com.ezreal.types.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ezreal
 * @Date 2024/2/14
 */
@Slf4j
@Aspect
@Component
public class CheckTokenAspect {

    @Pointcut("@annotation(com.ezreal.types.common.CheckToken)")
    public void checkTokenPointCut() {
    }

    @Around(value = "checkTokenPointCut() && @annotation(checkToken)")
    public Object checkToken(ProceedingJoinPoint pjp, CheckToken checkToken) {

        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                String token = request.getHeader("Authorization");
                if (StrUtil.isBlank(token)) {
                    throw new BusinessException(Constants.ResponseCode.UN_LOGIN);
                }

                if (!JwtUtils.verify(token)) {
                    log.error("token 解析错误, token:{}", token);
                    throw new BusinessException(Constants.ResponseCode.UN_LOGIN);
                }
            }
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
