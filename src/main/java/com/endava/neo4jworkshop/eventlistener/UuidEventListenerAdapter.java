package com.endava.neo4jworkshop.eventlistener;

import com.endava.neo4jworkshop.model.GraphNode;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidEventListenerAdapter extends EventListenerAdapter {

    @Override
    public void onPreSave(Event event) {
        var eventObject = event.getObject();
        if (!(eventObject instanceof GraphNode)) {
            return;
        }

        var entity = (GraphNode) eventObject;
        if (StringUtils.isEmpty(entity.getUuid())) {
            entity.setUuid(UUID.randomUUID().toString());
        }
    }
}
