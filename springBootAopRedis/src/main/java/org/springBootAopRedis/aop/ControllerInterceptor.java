package org.springBootAopRedis.aop;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springBootAopRedis.entity.PassPortInfo;
import org.springBootAopRedis.error.GlobalErrorInfoEnum;
import org.springBootAopRedis.error.ResultBody;
import org.springBootAopRedis.util.DesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/** 
 * 拦截器：记录用户操作日志，检查用户是否登录
 */ 
@Aspect
@Component
public class ControllerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	
	/***
	 * 拦截器规则：拦截springBootAopRedis包下的所有文件 同时带@RequestMapping注解的服务
	 */
	@Pointcut("execution(* org.springBootAopRedis..*(..))  and annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	@Pointcut("execution(* org.springBootAopRedis.rest..*(..))")
	public void controllerMethodPointcut(){};
	
	
	
//    @Before(value = "test.PointCuts.aopDemo()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("[Aspect1] before advise");
//        joinPoint.p
//    }
    
    
	/***
	 * 制定拦截器规则
	 */
	@Around("controllerMethodPointcut()")
	public ResultBody interceptor(ProceedingJoinPoint point){
		
		RequestAttributes requestAttributes =  RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest  request = servletRequestAttributes.getRequest();
		LOGGER.info("请求地址名称 ：" +  request.getRequestURI());
		
		ResultBody result = null;
		//判断当前访问地址是否需要拦截
		if(checkUrlRequired(request)){
			
			//获取Token信息
			String token = request.getParameter("token");
			
			if(StringUtils.isEmpty(token)){
				result = new ResultBody(GlobalErrorInfoEnum.NOT_PERMIS);
				LOGGER.info("请求地址中无 token");
			}else{
				
				ValueOperations<String, PassPortInfo>  operations = redisTemplate.opsForValue();
				//判断缓存是否存在
				Boolean hasKey =  redisTemplate.hasKey(token);
				LOGGER.info("查询缓存中的token" + token +" 是否存在 " + hasKey);
				if(hasKey){
					try {
						result = (ResultBody)point.proceed();
					} catch (Throwable e) {
						LOGGER.info("exception: ", e);
						result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
					}
				}else{
					LOGGER.info("token 失效...");
					result = new ResultBody(GlobalErrorInfoEnum.TIME_OUT);
				}
			}
			
		}else{
			try {
				// 一切正常的情况下，继续执行被拦截的方法
				point.proceed();
			} catch (Throwable e) {
				LOGGER.info("exception: ", e);
				result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
			}
		}
		
		return result;
		
	}
	
	/**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    protected String getToken(String passPortGid) {
        StringBuilder sb = new StringBuilder(passPortGid);
        

        return sb.toString();
    }
	
    
    /***
     * 请求地址过滤
     * @param request
     * @return
     */
	public boolean checkUrlRequired(HttpServletRequest request){
		String url = request.getRequestURI();
		
		if(!url.endsWith("/login")){
			return true;
		}else{
			return false;
		}
	}
}
