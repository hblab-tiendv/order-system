package com.ordering.system.messageconsumer;

import org.apache.avro.specific.SpecificRecordBase;

public interface RabbitMQConsumer<T extends SpecificRecordBase> {
    void receive(T messages);
}
