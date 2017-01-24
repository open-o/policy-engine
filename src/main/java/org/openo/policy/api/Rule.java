package org.openo.policy.api;

/**
 * Created by 10184056 on 2016/8/19.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Rule {
    private long id;
    @Length(max = 3)
    private String content;

    public Rule() {
        // Jackson deserialization
    }

    public Rule(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
