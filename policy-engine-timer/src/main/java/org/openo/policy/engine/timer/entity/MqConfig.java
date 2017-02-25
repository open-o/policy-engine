/*
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

package org.openo.policy.engine.timer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MqConfig {

    private MqConfig() {
    }

    @JsonProperty
    private String brokerIp;

    @JsonProperty
    private String brokerPort;

    public String getBrokerIp() {
        return brokerIp;
    }

    public String getBrokerPort() {
        return brokerPort;
    }

    @JsonIgnore
    public String getBrokerURL() {
        return String.format("%s:%s", brokerIp, brokerPort);
    }
}