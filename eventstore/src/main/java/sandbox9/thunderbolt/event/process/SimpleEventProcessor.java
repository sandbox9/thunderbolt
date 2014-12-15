package sandbox9.thunderbolt.event.process;

import org.springframework.beans.factory.BeanNameAware;
import sandbox9.thunderbolt.event.process.activity.EventProcessActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2014. 12. 12..
 */
public class SimpleEventProcessor implements EventProcessor, BeanNameAware {

    private List<EventProcessActivity> activities = new ArrayList<EventProcessActivity>();

    private String beanName;

    @Override
    public void process(Object eventSeed) {

        if (activities == null || activities.size() == 0) {
            throw new RuntimeException(beanName + " Processor에 EventProcessActivity가 설정되어 있지 않습니다!");
        }

        for (EventProcessActivity activity : this.activities) {
            activity.handleActivity(eventSeed);
        }
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void addActivity(EventProcessActivity activity) {
        this.activities.add(activity);
    }
}
