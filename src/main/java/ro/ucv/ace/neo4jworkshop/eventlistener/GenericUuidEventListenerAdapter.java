package ro.ucv.ace.neo4jworkshop.eventlistener;

import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListenerAdapter;
import org.springframework.stereotype.Component;
import ro.ucv.ace.neo4jworkshop.model.GraphEntity;

import java.util.UUID;

@Component
public class GenericUuidEventListenerAdapter extends EventListenerAdapter {

    @Override
    public void onPreSave(Event event) {
        Object eventObject = event.getObject();
        if (!(eventObject instanceof GraphEntity)) {
            return;
        }

        GraphEntity entity = (GraphEntity) eventObject;
        if (entity.getUuid() == null) {
            entity.setUuid(UUID.randomUUID().toString());
        }
    }
}
