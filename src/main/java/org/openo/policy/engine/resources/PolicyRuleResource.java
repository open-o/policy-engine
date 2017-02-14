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


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jvnet.hk2.annotations.Service;
import org.openo.policy.engine.model.PolicyDescription;


@Path("/openoapi/polengine/v1")
@Produces({MediaType.APPLICATION_JSON})

@Service
public class PolicyRuleResource {


    @POST
    @Path("policyinfo")
    public Response policyInfo(PolicyDescription policyDescription) {
       
        return response(new String("OK"), Response.Status.OK.getStatusCode());
    }

  
    private Response response(Object entity, int status) {
        return Response.status(status).entity(entity)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE, HEAD")
                .header("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept")
                .build();
    }

}