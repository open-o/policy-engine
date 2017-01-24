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
package org.openo.policy.resources;


import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.openo.policy.api.Rule;

import java.util.concurrent.atomic.AtomicLong;


@Path("/policy")
@Produces(MediaType.APPLICATION_JSON)   //application/json
public class PolicyResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    public PolicyResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }
    @GET
    @Timed  //记录调用频率
    public Rule sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));//"Hello, xxx!"
        return new Rule(counter.incrementAndGet(), value);
    }
}
