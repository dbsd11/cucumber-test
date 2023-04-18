package group.bison.cucumber.domain.infrastructure_layer;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import group.bison.cucumber.domain.abstract_layer.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import group.bison.cucumber.common.utils.ClassCastUtil;

public interface VO2EntityCaster<R> {
    Logger log = LoggerFactory.getLogger(VO2EntityCaster.class);
    
    default R tryCast2Entity() {
        R castObj = null;
        try {
            Class<R> objClass = null;
            Type[] interfaces = this.getClass().getGenericInterfaces();
            for(Type interfaceI : interfaces) {
                if(!(interfaceI instanceof ParameterizedType)) {
                    continue;
                }

                if(!((Class)((ParameterizedType)interfaceI).getRawType()).isAssignableFrom(VO2EntityCaster.class)) {
                    continue;
                }

                objClass = (Class<R>)((ParameterizedType)interfaceI).getActualTypeArguments()[0];
            }
            
            if(BaseEntity.class.isAssignableFrom(objClass)) {
                BaseEntity baseEntity = (BaseEntity)objClass.newInstance();
                Field[] entityFields = this.getClass().getDeclaredFields();
                for(Field field : entityFields) {
                    field.setAccessible(true);
                    baseEntity.getFieldMap().put(baseEntity.generateOrGetFieldKey(field.getName()), field.get(this));
                }
                castObj = (R) baseEntity;
            } else {
                castObj = ClassCastUtil.cast(this, objClass);
            }
        } catch (Exception e) {
            log.error("try cast2entity failed", e);
        }
        return castObj;
    }
}
