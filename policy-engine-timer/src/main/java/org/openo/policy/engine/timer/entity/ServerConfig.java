/*
 * Copyright 2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.policy.engine.timer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

    @JsonProperty
    @NotEmpty
    private String ip;

    @JsonProperty
    @NotEmpty
    private String port;

    @JsonProperty
    @NotEmpty
    private String uri;

    @JsonProperty("isAutoDiscover")
    private boolean isAutoDiscover;

    @JsonProperty
    private String serviceName;

    @JsonProperty
    private String serviceVersion;

    @JsonIgnore
    public String getUrl() {
        String url = "http://" + ip + ":" + port + uri;
        logger.info("service {} url is: {}", serviceName, url);
        return url;
    }
}
