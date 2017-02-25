/*
 * Copyright 2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.policy.engine.timer.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jvnet.hk2.annotations.Service;
import org.openo.policy.engine.timer.entity.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Singleton
public class MqService {

    private static final Logger logger = LoggerFactory.getLogger(MqService.class);

    private Session session;

    @Inject
    public MqService(MqConfig mqConfig) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(mqConfig.getBrokerURL());
        try {
            Connection connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            logger.error("create mq topic and queue session failed.", e);
        }
    }

    public Optional<MessageProducer> getQueueProducer(String queueName) {
        try {
            MessageProducer producer = session.createProducer(session.createQueue(queueName));
            logger.info("create mq queue producer [{}] success.", queueName);
            return Optional.of(producer);
        } catch (JMSException e) {
            logger.error("create mq queue producer [{}] failed.", queueName);
            logger.error("", e);
        }
        return Optional.empty();
    }

    public void subscribeMQTopic(String topicName, MessageListener mqJsonListener) {
        try {
            session.createConsumer(session.createTopic(topicName)).setMessageListener(mqJsonListener);
            logger.info("subscribe topic [" + topicName + "] success.");
        } catch (Exception e) {
            logger.error("subscribe topic [" + topicName + "] failed.", e);
        }
    }
}
