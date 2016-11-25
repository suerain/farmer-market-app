package edu.mum.farmer.app.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.farmer.app.logging.ProductLogger;

@Aspect
@Component
public class ProductAdvice {
	@Autowired
	private ProductLogger logger;
	
	@After("execution(public * edu.mum.farmer.app.controller.ProductController.deleteProductRest(..))")
	public void traceDeleteMethod(JoinPoint joinpoint) {
		String method = joinpoint.getSignature().getName();
		System.out.println("**************************************");
		logger.log(method + " action has been performed for current order.");
	}

}
