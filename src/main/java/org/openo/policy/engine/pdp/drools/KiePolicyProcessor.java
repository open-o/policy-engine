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

import org.kie.api.runtime.KieSession;
import org.openo.policy.engine.pdp.PolicyProcessor;
import org.openo.policy.engine.pep.PolicyAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KiePolicyProcessor implements PolicyProcessor {
	
	private final Logger logger = LoggerFactory.getLogger(KiePolicyProcessor.class.getName());

	private String name;

	private String groupId;

	private String artifactId;

	private String version;

	private KieSession kieSession;


	public KiePolicyProcessor(String name, String groupId, String artifactId, String version) {
		checkParameters(name, groupId, artifactId, version);

		this.name = name;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}

	private void checkParameters(String name, String groupId, String artifactId, String version) {
		checkParameter(name, "Engine name can not be empty!");

		checkParameter(groupId, "groupId can not be empty!");

		checkParameter(artifactId, "artifactId can not be empty!");

		checkParameter(version, "version can not be empty!");
	}

	private void checkParameter(String content, String s) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException(s);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Object fact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public int fire() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPolicyAction(PolicyAction action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterChannel() {
		// TODO Auto-generated method stub

	}

}
