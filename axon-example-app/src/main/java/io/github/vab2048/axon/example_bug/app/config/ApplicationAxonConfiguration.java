package io.github.vab2048.axon.example_bug.app.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ApplicationAxonConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ApplicationAxonConfiguration.class);

    /* *************************************************************************************
     * Snapshotting...
     * *************************************************************************************/
    public static final String ACCOUNT_AGGREGATE_SNAPSHOT_TRIGGER_DEFINITION_BEAN_NAME = "accountAggregateSnapshotTrigger";
    public static final int ACCOUNT_AGGREGATE_EVENT_COUNT_SNAPSHOT_TRIGGER = 3;
    @Bean(ACCOUNT_AGGREGATE_SNAPSHOT_TRIGGER_DEFINITION_BEAN_NAME)
    public SnapshotTriggerDefinition accountAggregateSnapshotTrigger(Snapshotter snapshotter) {
        // After 3 events take a snapshot (this number is low just so we can see trigger a snapshot easily).
        return new EventCountSnapshotTriggerDefinition(snapshotter, ACCOUNT_AGGREGATE_EVENT_COUNT_SNAPSHOT_TRIGGER);
    }

}
