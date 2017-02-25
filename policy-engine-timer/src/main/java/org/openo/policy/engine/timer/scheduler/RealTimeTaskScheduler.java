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

package org.openo.policy.engine.timer.scheduler;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;
import org.openo.policy.engine.timer.entity.task.RealTimeTask;
import org.openo.policy.engine.timer.executor.TaskExecutor;

@Service
@Singleton
@Named("realTimeTask")
public class RealTimeTaskScheduler implements TaskScheduler<RealTimeTask> {

    private TaskExecutor taskExecutor;

    @Inject
    public RealTimeTaskScheduler(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void addTask(RealTimeTask realTimeTask) {
        taskExecutor.execute(realTimeTask);
    }

    @Override
    public void deleteTask(RealTimeTask realTimeTask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateTask(RealTimeTask realTimeTask) {
        throw new UnsupportedOperationException();
    }
}
