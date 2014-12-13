package sandbox9.thunderbolt.event.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sandbox9.thunderbolt.event.process.activity.EventProcessActivity;

/**
 * Created by chanwook on 2014. 12. 12..
 */
@Service
public class SimpleEventProcessor implements EventProcessor {
    //TODO FW 기능으로 변경
    @Autowired
    @Qualifier("event.activity.price")
    private EventProcessActivity pricingProcessor;

    @Autowired
    @Qualifier("event.activity.stock")
    private EventProcessActivity sotckProcessor;

    @Override
    public void process(Object eventSeed) {
        //TODO FW 기능으로 변경
        pricingProcessor.handleActivity(eventSeed);
        sotckProcessor.handleActivity(eventSeed);
    }
}
