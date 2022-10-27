package com.example.demo.aspect;

import com.example.demo.dto.AuditDTO;
import com.example.demo.service.AuditServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LogManager.getLogger(LogAspect.class);

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
        List<? extends GrantedAuthority> authList = authentication.getAuthorities().stream().filter(e -> !e.getAuthority().equals("ROLE_ANONYMOUS"))
                .collect(Collectors.toList());
        String auth = null;

        if (authList.size() > 0) {
            auth = authList.get(0).getAuthority();
            auditService.insertAudit(new AuditDTO(authentication.getName(), auth));
        }

//        AuditDTO auditDTO = (AuditDTO) joinPoint.getArgs()[0];
//        logger.info(auditDTO.getId() + " " + auditDTO.getRole() + " " + auditDTO.getDate());
        return obj;
    }

}
