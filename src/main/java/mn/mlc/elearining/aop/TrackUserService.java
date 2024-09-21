package mn.mlc.elearining.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackUserService {
    private static final Logger logger = LoggerFactory.getLogger(TrackUserService.class);

    @Pointcut("execution(* mn.mlc.elearining.services.impl.UserServiceImpl.*(..))")
    public void userServiceTrack(){}

    @Before("userServiceTrack()")
    public void beforeTrack(JoinPoint joinPoint){
        logger.info("Before user service track: {}",joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "userServiceTrack()",returning = "result")
    public void afterTrackReturn(JoinPoint joinPoint,Object result){
        logger.info("After user service track return: {}",joinPoint.getSignature().getName());
        logger.info("Result: {}",result);
    }
}
