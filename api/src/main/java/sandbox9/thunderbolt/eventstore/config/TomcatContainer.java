package sandbox9.thunderbolt.eventstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by chanwook on 2014. 12. 16..
 */
@Component
public class TomcatContainer implements EmbeddedServletContainerCustomizer {
    @Value("${serverport.api}")
    private int serverPort;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(serverPort);
    }
}
