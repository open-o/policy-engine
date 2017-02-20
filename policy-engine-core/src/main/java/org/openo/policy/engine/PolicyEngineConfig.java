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


import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class PolicyEngineConfig extends Configuration {
    @NotEmpty
    @JsonProperty
    private String template;
    
    @NotEmpty
    @JsonProperty
    private String defaultName = "Stranger";
    
	@NotEmpty
	@JsonProperty
    private String msbServerAddr;
    
    @Valid
    @JsonProperty
    private String serviceIp;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String name) {
        this.defaultName = name;
    }
    
    public String getMsbServerAddr() {
		return msbServerAddr;
	}

	public void setMsbServerAddr(String msbServerAddr) {
		this.msbServerAddr = msbServerAddr;
	}

	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}
}
