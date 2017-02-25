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
package org.openo.policy.engine.enforcement.resources;

import org.jvnet.hk2.annotations.Service;
import org.openo.policy.engine.enforcement.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class TaskResource {
    private static final Logger logger = LoggerFactory.getLogger(TaskResource.class);

    @POST
    public Response enforce(@Valid Task task) {
        logger.info("enforce task [{}]", task.getId());
        return Response.ok("200", MediaType.APPLICATION_JSON_TYPE).build();
    }
}
