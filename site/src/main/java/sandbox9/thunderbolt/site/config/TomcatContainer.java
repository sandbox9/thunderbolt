package sandbox9.thunderbolt.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by chanwook on 2014. 12. 16..
 */
@Component
public class TomcatContainer implements EmbeddedServletContainerCustomizer {
    @Value("${serverport.site}")
    private int serverPort;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(serverPort);
    }
}
