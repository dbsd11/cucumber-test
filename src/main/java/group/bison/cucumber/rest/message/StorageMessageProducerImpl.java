package group.bison.cucumber.rest.message;

import group.bison.cucumber.domain.message_layer.StorageMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StorageMessageProducerImpl implements StorageMessageProducer {

    private KafkaTemplate kafkaTemplate;

    @Override
    public boolean sendSuccess(Integer sourceId, Integer targetId, String storageInfo) {
        boolean sendSuccess = false;
        try {
            kafkaTemplate.send("storage-message", System.currentTimeMillis(), storageInfo).get();
            sendSuccess = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sendSuccess;
    }

    @Override
    public boolean sendFailMsg(Integer sourceId, Integer targetId, String failReason) {
        boolean sendSuccess = false;
        try {
            kafkaTemplate.send("storage-message", System.currentTimeMillis(), failReason).get();
            sendSuccess = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return sendSuccess;
    }
}
