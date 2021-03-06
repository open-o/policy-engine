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

package org.openo.policy.engine.timer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.openo.policy.engine.timer.entity.MqConfig;
import org.openo.policy.engine.timer.entity.ServerConfig;

public class TimerConfiguration extends Configuration {

    @JsonProperty("taskManagerServer")
    private ServerConfig taskManagerServer;

    @JsonProperty("taskEnforcementServer")
    private ServerConfig taskEnforcementServer;

    @JsonProperty("mqConfig")
    private MqConfig mqConfig;
}
