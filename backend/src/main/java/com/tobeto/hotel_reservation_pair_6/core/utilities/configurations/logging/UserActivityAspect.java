package com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

@Aspect
@Component
public class UserActivityAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserActivityAspect.class);

    @After("execution(* com.tobeto.hotel_reservation_pair_6..*(..))")
    public void logUserActivity(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        // Burada kullanıcı hakkında daha fazla bilgi çıkarabilirsiniz, örneğin kullanıcı adı veya ID
        String username = getCurrentUsername();

        logger.info("Kullanıcı [{}] yöntemi [{}] ile [{}] argümanları ile erişti", username, methodName, methodArgs);
    }

    @Before("@annotation(com.tobeto.hotel_reservation_pair_6.core.utilities.configurations.logging.LogActivity)")
    public void logAnnotatedUserActivity(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogActivity logActivity = method.getAnnotation(LogActivity.class);
        String activityDescription = logActivity.value();

        String username = getCurrentUsername();
        logger.info("Kullanıcı [{}] aktivite gerçekleştirdi [{}]", username, activityDescription);
    }

    private String getCurrentUsername() {
        // Bu yöntemi kendi kullanıcınızı elde etme yöntemiyle değiştirin
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonim";
    }
}