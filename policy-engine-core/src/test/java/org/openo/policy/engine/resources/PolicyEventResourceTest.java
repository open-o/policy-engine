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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.Test;
import org.openo.policy.engine.model.PolicyEvent;

import io.dropwizard.testing.junit.ResourceTestRule;

public class PolicyEventResourceTest {

	private static PolicyEvent PolicyEvent;

	static {
		PolicyEvent = new PolicyEvent();
		PolicyEvent.setObjectName("mysql-11");
		PolicyEvent.setObjecttype("db");
		PolicyEvent.setMetricName("cpu");
		PolicyEvent.setMetricValue(90);
	}

	@ClassRule
	public static ResourceTestRule resources = ResourceTestRule.builder().addResource(new PolicyEventResource()).build();

	@Test
	public void test() {
        Response response = postToMessage(PolicyEvent);
        assertThat(response.getStatus()).isEqualTo(200);
	}

	private Response postToMessage(Object message) {
		return resources.client().target("/policyevent").request().post(Entity.json(message));
	}

}
