package com.ms.client;

import com.ms.exception.MsException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Ovi
 * Date: 6/11/13
 * Time: 11:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MsClientFactory {

    public MsClient getClient(String name) throws MsException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MsClient msClient = null;

        if (name.equals("")) {
            msClient = context.getBean(NexmoClient.class);
        } else if (name.equals("")) {
            //todo: get another bean
        } else {
            throw new MsException();
        }

        return msClient;
    }
}
