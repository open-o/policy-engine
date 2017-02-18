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
package org.openo.policy.engine.model;

import org.openo.policy.engine.model.PolicyDescription;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PolicyDescriptionTest {

	@Test
	public void testPolicyDescriptionGetAndSetMethod() {
		PolicyDescription pd = new PolicyDescription();
		pd.setNslId("1001");
		pd.setFileUri("ftp");
        assertThat(pd.getNslId()).isEqualTo("1001");
        assertThat(pd.getFileUri()).isEqualTo("ftp");
	}

}
