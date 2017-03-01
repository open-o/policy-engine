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

package org.openo.policy.engine.timer.receiver;

import java.util.Map;
import java.util.Optional;
import org.openo.policy.engine.timer.entity.task.Task;
import org.openo.policy.engine.timer.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskInstanceBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TaskInstanceBuilder.class);

    private TaskInstanceBuilder() {
    }

    public static Optional<Task> build(Map taskMap) {
        String taskStr = JsonUtil.toJson(taskMap);
        logger.info("receive task message: {}", taskStr);
        try {
            Class type = Class.forName("org.openo.policy.engine.timer.entity.task." + taskMap.get("type"));
            return Optional.ofNullable((Task) JsonUtil.fromJson(taskStr, type));
        } catch (ClassNotFoundException e) {
            logger.error("not found class from " + taskStr, e);
        }
        return Optional.empty();
    }

}
