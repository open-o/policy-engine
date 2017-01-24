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


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.openo.policy.api.Fact;

import java.io.IOException;


@Path("/rule")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource{

    @POST
    @Path("/policy/{userId}")
    public String responseToPerson(@PathParam("userId") long userId, @NotNull @Valid String requestJson) throws IOException {

        Fact person = new ObjectMapper().readValue(requestJson, Fact.class); //jackson解析
        long id = userId;
        return "id:" + id + " your data: {'name':" + person.getName() + ", 'gender':" + person.getGender()
                + ", 'age':" + person.getAge() + ", 'height':" + person.getHeight() + " }\n";
    }
}
