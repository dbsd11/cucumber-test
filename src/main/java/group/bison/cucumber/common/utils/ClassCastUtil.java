package group.bison.cucumber.common.utils;

import java.lang.reflect.Constructor;

import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassCastUtil {

    public static <T, R> R cast(T fromObj, Class<R> toClass) {
        R toObj = null;
        try {
            Constructor[] toConstructors = toClass.getDeclaredConstructors();
            for (Constructor toConstructor : toConstructors) {
                toConstructor.setAccessible(true);
                if (toConstructor.getParameterTypes() != null && toConstructor.getParameterTypes().length > 0
                        && fromObj.getClass().isAssignableFrom(toConstructor.getParameterTypes()[0])) {
                    toObj = (R) toConstructor.newInstance(fromObj);
                }
            }

            if (toObj == null) {
                toObj = toClass.newInstance();
                BeanUtils.copyProperties(fromObj, toObj);
            }
        } catch (Exception e) {
            log.error("class cast failed", e);
        }
        return toObj;
    }
}
