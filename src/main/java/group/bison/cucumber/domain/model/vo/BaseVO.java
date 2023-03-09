package group.bison.cucumber.domain.model.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

public abstract class BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Map<String, FieldKey> baseVoFieldKeyMap = new HashMap();

    private int version;

    private transient FieldMap fieldMap;

    public static FieldKey generateOrGetFieldKey(Object key) {
        return baseVoFieldKeyMap.compute(String.valueOf(key), (fieldName, fieldKey) -> fieldKey != null ? fieldKey : new FieldKey(key) );
    }

    public BaseVO() {
        this.fieldMap = new FieldMap(new HashMap<>());
    }

    public BaseVO(Map<FieldKey, Object> fieldMap) {
        this.fieldMap = new FieldMap(fieldMap);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public FieldMap getFieldMap() {
        return needSerialzeFieldMap() ? fieldMap : null;
    }

    protected boolean needSerialzeFieldMap() {
        return true;
    }

    protected void onUpdateField(FieldKey fieldKey, Object value) {
        this.version++;
    }

    public static class FieldKey {
        private String fieldName;
        private int hashCode;

        public FieldKey(Object key) {
            this.fieldName = String.valueOf(key);
            this.hashCode = computeHashCode(key);
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        int computeHashCode(Object key) {
            return key == null ? 0 : key.hashCode();
        }

        @Override
        public String toString() {
            return fieldName;
        }
    }

    public class FieldMap extends HashMap<FieldKey, Object> {

        FieldMap(Map<FieldKey, Object> map) {
            super(map);
        }

        @Override
        public Object put(FieldKey var1, Object var2) {
            Object obj = super.put(var1, var2);
            onUpdateField(var1, var2);
            return obj;
        }

        @Override
        public Object putIfAbsent(FieldKey var1, Object var2) {
            Object obj = super.putIfAbsent(var1, var2);
            onUpdateField(var1, var2);
            return obj;
        }

        @Override
        public void putAll(Map<? extends FieldKey, ? extends Object> var1) {
            if(CollectionUtils.isEmpty(var1)) {
                return;
            }

            var1.entrySet().forEach(entry -> {
                onUpdateField(entry.getKey(), entry.getValue());
            });

            super.putAll(var1);
        }
        
        @Override
        public Object remove(Object var1) {
            Object obj = super.remove(var1);
            onUpdateField(var1 instanceof FieldKey ? (FieldKey) var1 : new FieldKey(var1), null);
            return obj;
        }

        @Override
        public Object computeIfAbsent(FieldKey var1, Function<? super FieldKey, ? extends Object> var2) {
            Object obj = super.computeIfAbsent(var1, var2);
            onUpdateField(var1, obj);
            return obj;
        }

        @Override
        public Object computeIfPresent(FieldKey var1, BiFunction<? super FieldKey, ? super Object, ? extends Object> var2) {
            Object obj = super.computeIfPresent(var1, var2);
            onUpdateField(var1, obj);
            return obj;
        }

        @Override
        public void clear() {
            Set<FieldKey> keys = keySet();
            keys.forEach(key -> onUpdateField(key, null));
            super.clear();
        }
    }
}
