package cn.kjcoder.controller;

import cn.kjcoder.domain.SysLog;
import cn.kjcoder.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author kjcoder源码
 * @date 2021/3/19 9:24
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service
@Aspect
public class LogAop {

    /*@Autowired
    private HttpServletRequest request;*/
    @Autowired
    private SysLogService sysLogService;



    @Pointcut("execution(* cn.kjcoder.controller.*.*(..))")
    public void pc() {
    }


    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法
    private long time;
    private String url = "";
    private String username;

    @Around("pc()")
    public Object aroundPrintLog(ProceedingJoinPoint jp) throws Exception {
        Object proceed = null;
        try {
            visitTime = new Date();//当前时间就是开始访问的时间
            clazz = jp.getTarget().getClass(); //具体要访问的类
            String methodName = jp.getSignature().getName(); //获取访问的方法的名称
            Object[] args = jp.getArgs();//获取访问的方法的参数

            //获取具体执行的方法的Method对象
            if (args == null || args.length == 0) {
                method = clazz.getMethod(methodName); //只能获取无参数的方法
            } else {
                Class[] classArgs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    classArgs[i] = args[i].getClass();
                }
                method = clazz.getMethod(methodName, classArgs);
            }

            proceed = jp.proceed(args);//切入点方法调用

            time = new Date().getTime() - visitTime.getTime(); //获取访问的时长


            //获取url
            if (clazz != null && method != null && clazz != LogAop.class) {
                //1.获取类上的@RequestMapping("/orders")
                RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                if (classAnnotation != null) {
                    String[] classValue = classAnnotation.value();
                    //2.获取方法上的@RequestMapping(xxx)
                    RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                    if (methodAnnotation != null) {
                        String[] methodValue = methodAnnotation.value();
                        url = classValue[0] + methodValue[0];
                    }
                }
            }
            //获取当前操作的用户
            SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
            //Spring security里的User类
            User user = (User) context.getAuthentication().getPrincipal();
            username = user.getUsername();
            return proceed;


        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        } finally {
            if(!(method.getName().equals("findAllSysLog"))){
                //将日志相关信息封装到SysLog对象
                SysLog sysLog = new SysLog();
                sysLog.setExecutionTime(time); //执行时长
                sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                sysLog.setUrl(url);
                sysLog.setUsername(username);
                sysLog.setVisitTime(visitTime);
                //调用Service完成操作
                sysLogService.save(sysLog);
            }


        }
    }

    /*//前置通知
    @Before("execution(* cn.kjcoder.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//访问的方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数
        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    //后置通知
    @After("execution(* cn.kjcoder.controller..*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//执行时长

        String url = "";

        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];


                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    if(!(method.getName().equals("findAll")) || !(method.getName().equals("deleteSysLogById"))){
                        //将日志相关信息封装到SysLog对象
                        SysLog sysLog = new SysLog();
                        sysLog.setExecutionTime(time); //执行时长
                        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setVisitTime(visitTime);

                        //调用Service完成操作
                        sysLogService.save(sysLog);
                    }

                }
            }
        }

    }*/
}