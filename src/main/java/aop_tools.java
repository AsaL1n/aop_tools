import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.aop.Advice;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.aspectj.AspectJAfterReturningAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

public class aop_tools {

    public static Object aop_tostring(Object object,String methods_name) throws Exception {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(1);

        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor0 = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor0.setPattern(".*toString");
        regexpMethodPointcutAdvisor0.setAdvice((Advice) Reflections.getInstance("org.springframework.aop.interceptor.ExposeInvocationInterceptor"));
        advisedSupport.addAdvisor(regexpMethodPointcutAdvisor0);

        RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
        regexpMethodPointcutAdvisor.setPattern(".*toString");
        Method method = object.getClass().getMethod(methods_name);
        regexpMethodPointcutAdvisor.setAdvice(new AfterReturningAdviceInterceptor(
                new AspectJAfterReturningAdvice(
                method,
                new AspectJExpressionPointcut(),
                new SingletonAspectInstanceFactory(object)
        )));
        advisedSupport.addAdvisor(regexpMethodPointcutAdvisor);

        Object jdkDynamicAopProxy = Reflections.getInstance("org.springframework.aop.framework.JdkDynamicAopProxy", advisedSupport);
        Comparable proxy = (Comparable) Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                new Class[]{Comparable.class},
                (InvocationHandler) jdkDynamicAopProxy
        );

        return proxy;
    }
    public  static  Object aop_addInterface(Object object,Class[] clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(object);
        Constructor constructor = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy").getConstructor(AdvisedSupport.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(advisedSupport);
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), clazz, handler);
        return  proxy;
    }

    public static Object aop_getter(Object object, String methodName, String paramName, Object paramValue) throws Exception {

        HashMap<String, Object> map = new HashMap<>();
        map.put(paramName, paramValue);

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTarget(map);


        RegexpMethodPointcutAdvisor advisor0 = new RegexpMethodPointcutAdvisor();
        advisor0.setPattern(".*get");

        advisor0.setAdvice((Advice) Reflections.getInstance("org.springframework.aop.interceptor.ExposeInvocationInterceptor"));
        advisedSupport.addAdvisor(advisor0);

        RegexpMethodPointcutAdvisor advisor1 = new RegexpMethodPointcutAdvisor();
        advisor1.setPattern(".*get");

        Method method = object.getClass().getMethod(methodName,paramValue.getClass());

        AspectJAfterReturningAdvice aspectJAfterReturningAdvice = new AspectJAfterReturningAdvice(
                method,
                new AspectJExpressionPointcut(),
                new SingletonAspectInstanceFactory(object)
        );
        aspectJAfterReturningAdvice.setReturningName(paramName);

        advisor1.setAdvice(new AfterReturningAdviceInterceptor(aspectJAfterReturningAdvice));
        advisedSupport.addAdvisor(advisor1);
        Object jdkDynamicAopProxy = Reflections.getInstance("org.springframework.aop.framework.JdkDynamicAopProxy", advisedSupport);

        Object proxy = Proxy.newProxyInstance(
                map.getClass().getClassLoader(),
                new Class[]{Map.class},
                (InvocationHandler) jdkDynamicAopProxy
        );
        return proxy;
    }

}
