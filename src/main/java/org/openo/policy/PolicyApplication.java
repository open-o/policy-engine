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
