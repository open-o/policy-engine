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
package org.openo.policy.engine.cpc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openo.policy.engine.model.PolicyBaseDescr;
import org.openo.policy.engine.model.PolicyId;
import org.openo.policy.engine.pdp.PolicyProcessor;
import org.openo.policy.engine.pdp.drools.KiePolicyProcessor;
import org.openo.policy.engine.pep.PolicyActionFactory;

public class PolicyControllerServiceImpl implements PolicyControllerService{
	
	private Map<PolicyId,PolicyProcessor>  rumtimeMap = new ConcurrentHashMap<>();

	@Override
	public PolicyProcessor getProcessor(String name) {
	
		return null;
	}

	@Override
	public List<PolicyProcessor> getProcessorList() {
		return null;
	}

	

	@Override
	public void newProcessRuntime(PolicyBaseDescr baseDescr) {
		checkParameters(baseDescr);
		PolicyId policyId = createPolicyId(baseDescr);
		PolicyProcessor processor =  new KiePolicyProcessor(policyId);
		processor.newSession(baseDescr.getFileUrl());
		processor.setPolicyAction(baseDescr.getActionName(), PolicyActionFactory.newActionInstance(baseDescr.getActionName()));
		rumtimeMap.put(policyId, processor);
		
	}
	
	private PolicyId createPolicyId(PolicyBaseDescr baseDescr){
		PolicyId policyId = new PolicyId();
		policyId.setArtifactId(baseDescr.getArtifactId());
		policyId.setGroupId(baseDescr.getGroupId());
		policyId.setVersion(baseDescr.getVersion());
		policyId.setNsId(baseDescr.getNsId());
		return policyId;
	}
	
	
	
	private void checkParameters(PolicyBaseDescr baseDescr) {

		checkParameter(baseDescr.getGroupId(), "groupId can not be empty!");

		checkParameter(baseDescr.getArtifactId(), "artifactId can not be empty!");

		checkParameter(baseDescr.getVersion(), "version can not be empty!");
	}

	private void checkParameter(String content, String s) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException(s);
		}
	}
	
	

}
