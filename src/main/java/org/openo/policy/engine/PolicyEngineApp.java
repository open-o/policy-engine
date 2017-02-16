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
package org.openo.policy.engine;

import org.openo.policy.engine.resources.PolicyEventResource;
import org.openo.policy.engine.resources.PolicyRuleResource;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.dropwizard.Application;
import io.dropwizard.server.SimpleServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import io.dropwizard.assets.AssetsBundle;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;


public class PolicyEngineApp extends Application<PolicyEngineConfig> {
    public static void main(String[] args) throws Exception {
        new PolicyEngineApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<PolicyEngineConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/api-doc", "/api-doc", "index.html", "api-doc"));
    }

    @Override
    public void run(PolicyEngineConfig configuration,  Environment environment) {

        environment.jersey().register(new PolicyEventResource());
        environment.jersey().register(new PolicyRuleResource());
        initSwaggerConfig(configuration, environment);
    }
    
    
    private void initSwaggerConfig(PolicyEngineConfig configuration, Environment environment) {
        environment.jersey().register(new ApiListingResource());
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        BeanConfig config = new BeanConfig();
        config.setTitle("Open-O POLICY-ENGINE API");
        config.setVersion("1.1.0");
        config.setResourcePackage("org.openo.policy");
        // set rest api base path in swagger
        SimpleServerFactory serverFactory =
            (SimpleServerFactory) configuration.getServerFactory();
        String basePath = serverFactory.getApplicationContextPath();
        String rootPath = serverFactory.getJerseyRootPath();
        rootPath = rootPath.substring(0, rootPath.indexOf("/*"));
        basePath =
            ("/").equals(basePath) ? rootPath : (new StringBuilder()).append(basePath).append(rootPath)
                .toString();
        config.setBasePath(basePath);
        config.setScan(true);
    }
}
