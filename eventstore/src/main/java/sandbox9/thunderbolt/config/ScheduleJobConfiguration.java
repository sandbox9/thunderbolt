package sandbox9.thunderbolt.config;

import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import sandbox9.thunderbolt.event.sync.ProductUpdateJob;

/**
 * Created by chanwook on 2014. 12. 15..
 */
@Configuration
public class ScheduleJobConfiguration {

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
        trigger.setStartDelay(10000L); //10초 후 시작
        trigger.setRepeatInterval(30000L); //30초마다 실행
        trigger.setJobDetail(productEventJobDetail().getObject());
        return trigger;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean productEventJobDetail() {
        MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean =
                new MethodInvokingJobDetailFactoryBean();
        methodInvokingJobDetailFactoryBean.setTargetObject(productEventSynchronizer());
        methodInvokingJobDetailFactoryBean.setConcurrent(false);
        methodInvokingJobDetailFactoryBean.setTargetMethod("updateProduct");
        return methodInvokingJobDetailFactoryBean;
    }

    @Bean
    public ProductUpdateJob productEventSynchronizer() {
        ProductUpdateJob synchronizer = new ProductUpdateJob();
        return synchronizer;
    }

}
