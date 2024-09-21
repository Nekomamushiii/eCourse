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
public class TrackHomeController {
    private static final Logger logger = LoggerFactory.getLogger(TrackHomeController.class);

    @Pointcut("execution(* mn.mlc.elearining.controllers.HomeController.*(..))")
    public void trackHomeController(){}

    @Before("trackHomeController()")
    public void beforeTrack(JoinPoint joinPoint){
        logger.info("Before home controller track: {}",joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "trackHomeController()",returning = "result")
    public void afterTrackReturn(JoinPoint joinPoint,Object result){
        logger.info("After home controller track return: {}",joinPoint.getSignature().getName());
        logger.info("Result: {}",result);
    }
}
