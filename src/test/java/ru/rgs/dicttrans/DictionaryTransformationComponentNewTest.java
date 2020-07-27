package ru.rgs.dicttrans;

import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rgs.dicttrans.component.EventBusHelper;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
public class DictionaryTransformationComponentNewTest {

    @EndpointInject(uri = "mock:result")
    private MockEndpoint mock;

    private final EventBusHelper eventBusHelper = EventBusHelper.getInstance();
    private final RouteBuilder routeBuilder = createRouteBuilder();

    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("DictionaryTransformation://foo")
                        .to("DictionaryTransformation://bar")
                        .to("mock:result");
            }
        };
    }


    @Test
    public void testDictionaryTransformation() throws Exception {
//        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(5);

        // Trigger events to subscribers
        simulateEventTrigger();

        mock.await();
    }


    private void simulateEventTrigger() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                final Date now = new Date();
                // publish events to the event bus
                eventBusHelper.publish(now);
            }
        };

        new Timer().scheduleAtFixedRate(task, 1000L, 1000L);
    }
}
