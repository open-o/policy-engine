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
package org.openo.policy.engine.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;
import org.openo.policy.engine.cpc.PolicyControllerService;
import org.openo.policy.engine.cpc.PolicyControllerServiceImpl;
import org.openo.policy.engine.model.PolicyDescription;

import io.dropwizard.testing.junit.ResourceTestRule;

public class PolicyRuleResourceTest {
	
	private static final PolicyControllerService controllerService = mock(PolicyControllerService.class);

	private static PolicyDescription policyDescription;

	static {
		policyDescription = new PolicyDescription();
		policyDescription.setFileUri("http://ip:port/engin.drl");
		policyDescription.setNslId("10001");
	}

	@ClassRule
	public static ResourceTestRule resources = ResourceTestRule.builder().addResource(new PolicyRuleResource(controllerService)).build();

	@Test
	public void test() {
        Response response = postToMessage(policyDescription);
        assertThat(response.getStatus()).isEqualTo(200);
	}

	private Response postToMessage(Object message) {
		return resources.client().target("/policyinfo/").request().post(Entity.json(message));
	}

}
