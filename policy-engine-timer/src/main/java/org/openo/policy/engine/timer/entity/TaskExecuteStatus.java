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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openo.policy.engine.timer.util.JsonUtil;

public class TaskExecuteStatus {

    @JsonProperty
    private String id;
    @JsonProperty
    private long status;
    @JsonProperty
    private long fireTime;
    @JsonProperty
    private long nextFireTime;

    public TaskExecuteStatus(String id, long status, long fireTime, long nextFireTime) {
        this.id = id;
        this.status = status;
        this.fireTime = fireTime;
        this.nextFireTime = nextFireTime;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }
}
