package nextstep.support;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class ProxyUtils {

    public static <T> T create(Class<T> clazz, Long id) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getName().equals("getId")) {
                return id;
            }
            return proxy.invokeSuper(obj, args);
        });
        return (T) enhancer.create();
    }

    public static boolean isProxy(Object object) {
        return (object != null) && (object.getClass().getName().contains("$$"));
    }
}
