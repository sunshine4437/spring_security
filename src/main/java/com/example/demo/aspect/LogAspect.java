package com.example.demo.aspect;

import com.example.demo.dto.Audit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LogManager.getLogger(LogAspect.class);
/*
    private final AuditServiceImpl auditService;

    @Autowired
    public LogAspect(AuditServiceImpl auditService) {
        this.auditService = auditService;
    }

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void controllerPointCut() {
    }

    @AfterReturning("controllerPointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " : afterReturning");
    }

    @AfterThrowing("controllerPointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " : afterThrowing");
    }

    @Around("controllerPointCut()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.getName().equals("anonymousUser")) {
            Audit dto = new Audit();
            dto.setUserId(authentication.getName());
            auditService.insertAudit(dto);
        }

//        AuditDTO auditDTO = (AuditDTO) joinPoint.getArgs()[0];
//        logger.info(auditDTO.getId() + " " + auditDTO.getRole() + " " + auditDTO.getDate());
        return obj;
    }*/

}
