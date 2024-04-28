package com.mysystem.annotation.aspect;


import com.alibaba.fastjson.JSON;
import com.mysystem.dao.po.RequestRecordPO;
import com.mysystem.model.vo.RequestRecordVO;
import com.mysystem.mq.LogRecordProducer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class LogRecordAspect {

    @Autowired
    private LogRecordProducer logRecordProducer;

    @Around("@annotation(com.mysystem.annotation.LogRecord)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String urlPath = request.getServletPath().toString();
        String method = request.getMethod();
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Object> objects = Arrays.asList(joinPoint.getArgs());
        String requestParam = String.format("[param]:%s,[body]:%s", JSON.toJSONString(parameterMap), objects);

        Object o = joinPoint.proceed();

        // 写入mq
        RequestRecordPO vo = RequestRecordPO.builder()
                .method(method).url(urlPath).requestParam(requestParam)
                .requestId(String.valueOf(System.currentTimeMillis()))
                .requestTime(new Date()).build();
        System.out.println("当前请求参数为：" + vo);
        logRecordProducer.sendMsg(vo);

        return o;
    }
}
