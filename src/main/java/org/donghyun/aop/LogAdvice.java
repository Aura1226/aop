package org.donghyun.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Component //Bean으로 등록하기위해?
@Log4j
public class LogAdvice {

	@Before(value = "execution(* org.donghyun..*.*Service*.*(..))") //이번에만 손으로 쳐라...다음엔 복붙  
	public void logBefore() { //doA만 걸어주고 싶다면 마지막 *부분에 doA(메서드)를... 오전 11시 5분
		log.info("----------------------");
	}
	
	@Before(value = "execution(* org.donghyun..*.*Controller*.*(..))") //Controller에선 Around쓰면 안된다. 리스크가 크다.
	public void logBeforeController(JoinPoint jp) { 
		log.info("----------------------");
		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		log.info(methodName);
		log.info(Arrays.toString(args));
	}
	
	//@Around(value = "execution(* org.donghyun..*.*Service*.*(..))")//Service에선 Around를 써도 된다
	public Object logTime(ProceedingJoinPoint pjp) throws Throwable{//ProceedingJoinPoint @Around에서만 쓸 수 있다.
		
		String methodName = pjp.getSignature().getName();
		
//		log.info(pjp.getSignature());
//		log.info(Arrays.toString(pjp.getArgs()));
//		log.info(pjp.getThis());
		
		log.info(pjp.getSignature());
		log.info(methodName + ":::::::" + Arrays.toString(pjp.getArgs()));
		log.info(pjp.getThis());//여기서 getThis는 controller? 
		
		long start = System.currentTimeMillis();
		
		Object result = null;
		Object error = null;
		try {
			result = pjp.proceed();
		}catch (Throwable t) {
			log.error(t.getMessage());
			error = t;
		}
		log.info("end....................");
		
		long end = System.currentTimeMillis();
		log.info(methodName + "time: " + (end - start));
		
		if(error != null) {
			throw (Throwable)error;
		}
		return result;
	}
}
