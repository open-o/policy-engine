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
package org.openo.policy;

import org.openo.policy.resources.PolicyResource;
import org.openo.policy.resources.EventResource;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.google.common.base.MoreObjects;

/**
 * Created by 10184056 on 2016/8/19.
 */
public class PolicyApplication extends Application<PolicyEngineConfiguration> {
    public static void main(String[] args) throws Exception {
        new PolicyApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<PolicyEngineConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(PolicyEngineConfiguration configuration,  Environment environment) {

        environment.jersey().register(new PolicyResource(configuration.getTemplate(), configuration.getDefaultName()));
        environment.jersey().register(new EventResource());
    }
}
