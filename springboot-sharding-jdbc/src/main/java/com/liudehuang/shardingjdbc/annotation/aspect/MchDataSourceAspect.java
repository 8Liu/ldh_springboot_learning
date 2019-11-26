package com.liudehuang.shardingjdbc.annotation.aspect;

import com.liudehuang.shardingjdbc.annotation.MchDataSource;
import com.liudehuang.shardingjdbc.config.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-26
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-26
 * @UpdateRemark:
 * @Version: 1.0
 */
@Aspect
@Slf4j
@Component
public class MchDataSourceAspect {

    @Pointcut("@annotation(com.liudehuang.shardingjdbc.annotation.MchDataSource)")
    public void annotationPointCut(){

    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long merchantId = getTargetFieldValue(joinPoint,"merchantId");
        if(null==merchantId){
            throw new Exception("商户号错误");
        }
        DataSourceHolder.set(merchantId);
    }


    @After(("annotationPointCut()"))
    public void after(JoinPoint joinPoint){
        DataSourceHolder.clear();
    }


    /**
     * 获取方法参数据目标字段属性值
     *
     * @param joinPoint       方法参数
     * @param targetFieldName 目标字段属性名
     * @return
     * @throws Throwable
     */
    private Long getTargetFieldValue(JoinPoint joinPoint, String targetFieldName) throws Throwable {
        Long merchantId = null;
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            throw new Exception("参数不能为空！");
        }


        /*// 1.获取到所有的参数值的数组
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 2.获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        // 3.通过你需要获取的参数名称的下标获取到对应的值
        int targetFieldIndex = ArrayUtils.indexOf(parameterNames, targetFieldName);*/

        if (args[0] instanceof Long) {
            // 存在对应的参数名, 则取出参数值
            merchantId = (Long) args[0];
        } else {
            // 获取方法参数对象的商户编号字段属性值
            PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(args[0]);
            Object mchIdObj = null;
            if (null != propertyAccessor && propertyAccessor.isWritableProperty(targetFieldName)) {
                mchIdObj = propertyAccessor.getPropertyValue(targetFieldName);
            }
            if (null != mchIdObj) {
                merchantId = (Long) mchIdObj;
            }
        }
        return merchantId;
    }
}
