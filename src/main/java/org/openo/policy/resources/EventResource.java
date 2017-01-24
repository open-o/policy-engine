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
