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
package org.openo.policy.engine.pdp.drools;

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.openo.policy.engine.model.PolicyId;
import org.openo.policy.engine.pdp.PolicyProcessor;
import org.openo.policy.engine.pep.PolicyAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KiePolicyProcessor implements PolicyProcessor,Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(KiePolicyProcessor.class.getName());

	private PolicyId policyId;
	
	private KieServices kieServices;

	private KieSession kieSession;
	
	private Thread workThread;


	public KiePolicyProcessor(PolicyId policyId) {
		this.policyId = policyId;
        this.kieServices = KieServices.Factory.get();

        
	}
	
	@Override
	public void newSession(String url){
		String groupId = policyId.getGroupId();
		String artifactId = policyId.getArtifactId();
		String version = policyId.getVersion();
		final ReleaseId releaseId = kieServices.newReleaseId(groupId, artifactId, version);  
		ResourceWrapper resourceWrapper = new ResourceWrapper();
		//ResourceFactory.newUrlResource("http://10.92.217.139:8080/test/my.drl")
		resourceWrapper.setResource(ResourceFactory.newUrlResource(url));
		resourceWrapper.setTargetResourceName(artifactId);
		// 创建初始化的kjar  
        InternalKieModule kJar = KieJarFactory.createKieJar(kieServices, releaseId, resourceWrapper);
        KieRepository repository = kieServices.getRepository();  
        repository.addKieModule(kJar);  
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);  
        this.kieSession = kieContainer.newKieSession(); 
        logger.info("new PDP instance is start.  groupId:" + groupId + " artifactId:" +artifactId+ " version" + version);
	}


	@Override
	public void insert(Object fact) {
		  this.kieSession.insert(fact);
	}


	@Override
	public int fire() {
		
         return this.kieSession.fireAllRules();
         
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		this.kieSession.dispose();

	}

	@Override
	public void setPolicyAction(String identifier,PolicyAction action) {
		if(action != null){
			this.kieSession.setGlobal(identifier, action);
//			this.kieSession.registerChannel(identifier, new TestChannel());
		}
	}

	@Override
	public PolicyId getPolicyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		
		if(kieSession != null){
			kieSession.fireUntilHalt();
		}
		
	}

	@Override
	public void start() {
		workThread = new Thread(this);
		workThread.start();
		
	}

	
}
