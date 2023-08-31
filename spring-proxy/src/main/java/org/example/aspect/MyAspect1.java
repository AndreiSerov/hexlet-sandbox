package org.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect1 {

  @Pointcut("@annotation(org.example.annotation.Annotation1)")
  public void annotated() {}

  @Before("annotated()")
  public void before() {
    System.out.println("Aspect1 before");
  }

  @After("annotated()")
  public void after() {
    System.out.println("Aspect1 after");
  }
}