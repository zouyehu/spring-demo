package com.zyh.spring.utils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 获取上下文 bean
 * @author HU
 *
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static boolean hasTransactionThrowException = false;
    private static PlatformTransactionManager defaultTransactionManager;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static boolean hasTransactionThrowException() {
        return hasTransactionThrowException;
    }

    public void setHasTransactionThrowException(boolean hasTransactionThrowException) {
        ApplicationContextUtil.hasTransactionThrowException = hasTransactionThrowException;
    }

    public static PlatformTransactionManager getDefaultTransactionManager() {
        return defaultTransactionManager;
    }

    public void setDefaultTransactionManager(PlatformTransactionManager defaultTransactionManager) {
        ApplicationContextUtil.defaultTransactionManager = defaultTransactionManager;
    }

}
