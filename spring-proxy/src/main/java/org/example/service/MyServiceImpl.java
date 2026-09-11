package org.example.service;

import org.example.annotation.Annotation1;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

  @Annotation1
  public void method1() {
    System.out.println("method1");
    method2();
  }

  @Annotation1
  public void method2() {
    System.out.println("method2");
  }
}




/**
 * 1. Aspect1 before
 *    method1
 *    Aspect1 before
 *    method2
 *    Aspect1 after
 *    Aspect1 after
 *
 * 2. Aspect1 before
 *    method1
 *    method2
 *    Aspect1 after
 *    Aspect1 after
 *
 * 3. Aspect1 before
 *    method1
 *    method2
 *    Aspect1 after
 *
 * 4. ничего из перечисленного
 *
 *
 *
 *
 *
 */