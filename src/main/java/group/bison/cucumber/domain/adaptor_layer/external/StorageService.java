package group.bison.cucumber.domain.adaptor_layer.external;

public interface StorageService<T> {

    public String storage(Integer targetId, T data);
}
