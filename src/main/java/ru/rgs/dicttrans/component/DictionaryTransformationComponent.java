package ru.rgs.dicttrans.component;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;

import java.util.Map;

@org.apache.camel.spi.annotations.Component("DictionaryTransformation")
public class DictionaryTransformationComponent extends DefaultComponent {
    
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new DictionaryTransformationEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
