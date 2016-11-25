package edu.mum.farmer.app.advice;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import edu.mum.farmer.app.logging.Logger;

@Aspect
@Component
public class TraceAdvice {
	@Autowired
	private Logger logger;

	@After("execution(public * edu.mum.farmer.app.controller.OrderController.*Cart(..)) || execution(public * edu.mum.farmer.app.controller.OrderController.*out(..))")
	public void traceAfterMethod(JoinPoint joinpoint) throws IOException {
		String method = joinpoint.getSignature().getName();
		System.out.println("**************************************");
		logger.log(method + " action has been performed for current order.");
	}

}
