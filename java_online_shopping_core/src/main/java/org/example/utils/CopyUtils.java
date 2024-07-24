package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.objenesis.ObjenesisStd;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class CopyUtils {

    /** Objenesis: Spring工具类，
     *  Objenesis使用多种方法尝试实例化对象，具体取决于对象的类型，JVM版本，JVM供应商和SecurityManager。 */
    private static ThreadLocal<ObjenesisStd> stdThreadLocal = ThreadLocal.withInitial(ObjenesisStd::new);

    private static final String beanCopierHashKey = "source:%s:target:%s:useConverter:%s";

    private static final ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>(512);

    public static <T> T copyAndObtain(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            throw new RuntimeException("source or targetClass is null.");
        }
        try {
            T target = stdThreadLocal.get().newInstance(targetClass);
            copy(source, target);
            return target;
        } catch (Exception e) {
            log.error("Copies failure!", e);
            throw new RuntimeException("Copies failure, source:" + source + " to ,targetClass:" + targetClass);
        }
    }

    public static <T> T copy (Object source, T target) {
        if (source == null || target == null) {
            throw new RuntimeException("source or target is null.");
        }

        try {
            String hashKey = generateBeanCopierKey(source.getClass(), target.getClass(), false);
            BeanCopier beanCopier;
            if ((beanCopier = beanCopierMap.get(hashKey)) == null) {
                beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                beanCopierMap.put(hashKey, beanCopier);
            }

            beanCopier.copy(source, target, null);

            return target;
        } catch (Exception e) {
            log.error("Copies failure!", e);
            throw new RuntimeException("Copies failure, source:" + source + " to ,target:" + target);
        }
    }

    private static String generateBeanCopierKey(Class source, Class target, boolean useConverter) {
        return String.format(beanCopierHashKey, source, target, useConverter);
    }
}
