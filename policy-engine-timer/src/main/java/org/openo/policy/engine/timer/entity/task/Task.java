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

package org.openo.policy.engine.timer.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.openo.policy.engine.timer.scheduler.TaskScheduler;

import java.util.Map;

public abstract class Task {
    @JsonIgnore
    private TaskScheduler<Task> taskScheduler;

    @JsonProperty
    private String name;
    @JsonProperty
    private String id;
    @JsonProperty
    private String type;
    @JsonProperty
    private String scriptName;
    @JsonProperty
    private Map<String, Object> parameters;
    @JsonProperty
    private long startGMT;
    @JsonProperty
    private long endGMT;

    public Task() {
        taskScheduler = obtainScheduler();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public long getStartGMT() {
        return startGMT;
    }

    public long getEndGMT() {
        return endGMT;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void start() {
        taskScheduler.addTask(this);
    }

    public void stop() {
        taskScheduler.deleteTask(this);
    }

    public void update() {
        taskScheduler.updateTask(this);
    }

    protected abstract TaskScheduler<Task> obtainScheduler();
}
