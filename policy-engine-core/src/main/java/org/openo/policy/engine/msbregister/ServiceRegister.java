/**
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

package org.openo.policy.engine.msbregister;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eclipsesource.jaxrs.consumer.ConsumerFactory;

public class ServiceRegister implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceRegister.class);

	private ServiceRegisterEntity engineEntity;

	private String msbServerAddr;

	private String serviceIp;

	public ServiceRegister(String msbServerAddr, String serviceIp) {
		this.msbServerAddr = msbServerAddr;
		this.serviceIp = serviceIp;
		initServiceEntity();
	}

	@Override
	public void run() {
		LOG.info("start extsys microservice register");
		boolean flag = false;
		int retry = 0;
		while (!flag && retry < 1000) {
			LOG.info("extsys microservice register.retry:" + retry);
			retry++;
			flag = registerService();
			if (flag == false) {
				LOG.warn("microservice register failed, sleep 30S and try again.");
				threadSleep(30000);
			} else {
				LOG.info("microservice register success!");
				break;
			}
		}
		LOG.info("extsys microservice register end.");
	}

	/**
	 * @param entity
	 *            service entity
	 * @return register service to msb success return true, else return false.
	 */
	private boolean registerService() {
		ClientConfig config = new ClientConfig();
		try {
			MicroserviceBusRest resourceserviceproxy = ConsumerFactory.createConsumer(this.msbServerAddr, config,
					MicroserviceBusRest.class);
			resourceserviceproxy.registerServce("false", engineEntity);
		} catch (Exception error) {
			LOG.error("microservice register failed!" + error.getMessage());
			return false;
		}
		return true;
	}

	private void threadSleep(int second) {
		LOG.info("start sleep ....");
		try {
			Thread.sleep(second);
		} catch (InterruptedException error) {
			LOG.error("thread sleep error.errorMsg:" + error.getMessage());
		}
		LOG.info("sleep end .");
	}

	private void initServiceEntity() {
		this.engineEntity = new ServiceRegisterEntity();
		engineEntity.setServiceName("policy-engine");
		engineEntity.setProtocol("REST");
		engineEntity.setVersion("v1");
		engineEntity.setUrl("/openoapi/polengine/v1");
		engineEntity.setSingleNode(this.serviceIp, "8902", 0);
		engineEntity.setVisualRange("1");
	}
}
