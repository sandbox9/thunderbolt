package sandbox9.thunderbolt.event.process;

import sandbox9.thunderbolt.event.process.activity.EventProcessActivity;

/**
 * Created by chanwook on 2014. 12. 11..
 */
public interface EventProcessor {
    void process(Object eventSeed);

    void addActivity(EventProcessActivity activity);
}
