package group.bison.cucumber.domain;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import group.bison.cucumber.common.utils.ClassCastUtil;
import group.bison.cucumber.domain.model.vo.BaseVO;

public interface Entity2DOCaster<R> {
    Logger log = LoggerFactory.getLogger(Entity2DOCaster.class);
    
    default R tryCast2VO() {
        R castObj = null;
        try {
            Class<R> objClass = null;
            Type[] interfaces = this.getClass().getGenericInterfaces();
            for(Type interfaceI : interfaces) {
                if(!(interfaceI instanceof ParameterizedType)) {
                    continue;
                }

                if(!((Class)((ParameterizedType)interfaceI).getRawType()).isAssignableFrom(Entity2DOCaster.class)) {
                    continue;
                }

                objClass = (Class<R>)((ParameterizedType)interfaceI).getActualTypeArguments()[0];
            }
            
            if(BaseVO.class.isAssignableFrom(objClass)) {
                BaseVO baseVo = (BaseVO)objClass.newInstance();
                Field[] entityFields = this.getClass().getDeclaredFields();
                for(Field field : entityFields) {
                    field.setAccessible(true);
                    baseVo.getFieldMap().put(baseVo.generateOrGetFieldKey(field.getName()), field.get(this));
                }
                castObj = (R)baseVo;
            } else {
                castObj = ClassCastUtil.cast(this, objClass);
            }
        } catch (Exception e) {
            log.error("try cast2vo failed", e);
        }
        return castObj;
    }
}
