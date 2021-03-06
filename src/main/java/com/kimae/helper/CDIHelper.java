package com.kimae.helper;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CDIHelper {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static  <T> void programmaticInjection(Class clazz, T injectionObject) throws NamingException {
      InitialContext initialContext = new InitialContext();
      Object lookup = initialContext.lookup("java:comp/BeanManager");
      BeanManager beanManager = (BeanManager) lookup;
      AnnotatedType annotatedType = beanManager.createAnnotatedType(clazz);
      InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotatedType);
      CreationalContext creationalContext = beanManager.createCreationalContext(null);
      injectionTarget.inject(injectionObject, creationalContext);
      creationalContext.release();
    }
}
