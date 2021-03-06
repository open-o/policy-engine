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
package org.openo.policy.engine.pdp;

import org.openo.policy.engine.model.PolicyId;
import org.openo.policy.engine.pep.PolicyAction;

public interface PolicyProcessor
{
	
	PolicyId getPolicyId();

    void insert(Object fact);
    
    void newSession(String url);

    int fire();
    
    void start();

    void reload();

    void destroy();
    
    void setPolicyAction(String identifier,PolicyAction action);

}
