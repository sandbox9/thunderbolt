package sandbox9.thunderbolt.eventstore.config;

import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import sandbox9.thunderbolt.eventstore.product.ProductUpdateJob;

/**
 * Created by chanwook on 2014. 12. 15..
 */
@Configuration
public class ScheduleJobConfiguration {

    @Value("${event.updatejob.startdelay}")
    private long startDelay;

    @Value("${event.updatejob.repeatinterval}")
    private long repeatInterval;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger trigger = productEventTrigger().getObject();
        bean.setTriggers(trigger);
        return bean;
    }

    @Bean
    public SimpleTriggerFactoryBean productEventTrigger() {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setStartDelay(startDelay);
        trigger.setRepeatInterval(repeatInterval);
        trigger.setJobDetail(productEventJobDetail().getObject());
        return trigger;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean productEventJobDetail() {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean =
                new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(productUpdateJob());
        methodInvokingJobDetailFactoryBean.setConcurrent(false);
        methodInvokingJobDetailFactoryBean.setTargetMethod("updateProduct");
        return methodInvokingJobDetailFactoryBean;
    }

    @Bean
    public ProductUpdateJob productUpdateJob() {
        ProductUpdateJob synchronizer = new ProductUpdateJob();
        return synchronizer;
    }

}
