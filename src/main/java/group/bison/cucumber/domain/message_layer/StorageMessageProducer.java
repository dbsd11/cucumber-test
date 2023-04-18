package group.bison.cucumber.domain.message_layer;

public interface StorageMessageProducer {

    boolean sendSuccess(Integer sourceId, Integer targetId, String storageInfo);

    boolean sendFailMsg(Integer sourceId, Integer targetId, String failReason);
}
