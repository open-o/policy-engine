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

import org.kie.api.io.Resource;

public class ResourceWrapper {

	private Resource resource;

	private String targetResourceName;
	
	public ResourceWrapper(){
		
	}

	public ResourceWrapper(Resource resource, String targetResourceName) {
		this.resource = resource;
		this.targetResourceName = targetResourceName;
	}

	public Resource getResource() {
		return resource;
	}

	public String getTargetResourceName() {
		return targetResourceName;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setTargetResourceName(String targetResourceName) {
		this.targetResourceName = targetResourceName;
	}

}
