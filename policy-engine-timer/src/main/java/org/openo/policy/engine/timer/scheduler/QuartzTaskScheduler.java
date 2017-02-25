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

import java.util.Date;
import javax.inject.Named;
import javax.inject.Singleton;
import org.jvnet.hk2.annotations.Service;
import org.openo.policy.engine.timer.entity.task.CronTask;
import org.openo.policy.engine.timer.executor.QuartzTaskExecutor;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Singleton
@Named("quartzTask")
public class QuartzTaskScheduler implements TaskScheduler<CronTask> {

    private static final Logger logger = LoggerFactory.getLogger(QuartzTaskScheduler.class);
    private Scheduler scheduler;
    private Class<? extends Job> executorClass = QuartzTaskExecutor.class;

    public QuartzTaskScheduler() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("get scheduler error.", e);
            System.exit(1);
        }
    }

    @Override
    public void addTask(CronTask cronTask) {
        JobDetail jobDetail = getJobDetail(cronTask);
        CronTrigger cronTrigger = getCronTrigger(cronTask);
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
            logger.info("add cron task [{}] with cron expression [{}] success.", cronTask.getId(),
                    cronTask.getExpression());
        } catch (SchedulerException e) {
            logger.error("add cron task [{}] with cron expression [{}] error.", cronTask.getId(),
                    cronTask.getExpression());
            logger.error("", e);
        }
    }

    private JobDetail getJobDetail(CronTask cronTask) {
        JobDetail jobDetail = JobBuilder.newJob(executorClass)
                .withIdentity(cronTask.getId(), "cronTaskDetailGroup")
                .build();
        jobDetail.getJobDataMap().put("taskInstance", cronTask);
        return jobDetail;
    }

    private CronTrigger getCronTrigger(CronTask cronTask) {
        TriggerBuilder<CronTrigger> cronTaskTriggerGroup = TriggerBuilder.newTrigger()
                .withIdentity(cronTask.getId(), "cronTaskTriggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTask.getExpression()))
                .startNow();
        return buildTriggerValidDate(cronTaskTriggerGroup, cronTask);
    }

    private CronTrigger buildTriggerValidDate(TriggerBuilder<CronTrigger> cronTaskTriggerGroup, CronTask cronTask) {
        TriggerBuilder<CronTrigger> triggerBuilder = cronTaskTriggerGroup;
        if (cronTask.getStartGMT() > 0) {
            triggerBuilder = triggerBuilder.startAt(new Date(cronTask.getStartGMT()));
            logger.info("cronTask [{}] start at [{}]", cronTask.getId(), new Date(cronTask.getStartGMT()));
        }
        if (cronTask.getEndGMT() > 0) {
            triggerBuilder = triggerBuilder.endAt(new Date(cronTask.getEndGMT()));
            logger.info("cronTask [{}] end at [{}]", cronTask.getId(), new Date(cronTask.getEndGMT()));
        }
        return triggerBuilder.build();
    }

    @Override
    public void deleteTask(CronTask cronTask) {
        String cronTaskId = cronTask.getId();
        try {
            scheduler.unscheduleJob(new TriggerKey(cronTaskId, "cronTaskTriggerGroup"));
            logger.info("delete task [{}] success.", cronTaskId);
        } catch (SchedulerException e) {
            logger.error("delete task [{}] error.", cronTaskId, e);
        }
    }

    @Override
    public void updateTask(CronTask cronTask) {
        deleteTask(cronTask);
        addTask(cronTask);
        logger.info("update task [{}] success.", cronTask.getId());
    }

    public void shutdown() {
        try {
            scheduler.clear();
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error("shutdown quartzTaskScheduler error.");
            logger.error("", e);
        }
    }
}
