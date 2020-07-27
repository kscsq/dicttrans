package ru.rgs.dicttrans.component;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryTransformationProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(DictionaryTransformationProducer.class);
    private DictionaryTransformationEndpoint endpoint;

//    private TransformationService service;

    public DictionaryTransformationProducer(DictionaryTransformationEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
//        service = (TransformationService)exchange.getContext().getRegistry().lookupByName("transformationService");
//        Registry registry = exchange.getContext().getRegistry();
//        if (service != null) {
//            service.requestShit();
//        } else {
            System.out.println("TransformationService is null!");
            System.out.println("I am the Producer " + exchange.getIn().getBody());

//        }
    }

}
