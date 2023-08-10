package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    private static final Map<Class<?>, String> BEEN_BY_LEVEL = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(Inspect.class)) {
            return bean;
        }

        BEEN_BY_LEVEL.put(bean.getClass(), bean.getClass().getAnnotation(Inspect.class).level());

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BEEN_BY_LEVEL.forEach((key, value) -> Proxy.newProxyInstance(
                key.getClassLoader(),
                key.getInterfaces(),
                ((proxy, method, args) -> {
                    String message = "Was called method: "
                            + method.getName() + " with arguments: " + Arrays.toString(args);
                    if (value.equals("info")) {
                        LOGGER.info(message);
                    } else {
                        LOGGER.debug(message);
                    }
                    return proxy;
                })
        ));

        return bean;
    }
}
// END
