package com.gff.util;

import com.gff.model.entity.Configuracion;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduling {

    private Configuracion config;
    private Scheduler scheduler;
    private JobDetail job;
    private Trigger trigger;
    private final String JOB_NAME = "backUp";
    private String cron;

    public void startScheduling() throws SchedulerException {
        if (scheduler != null && scheduler.isStarted()) {
            this.stopScheduling();
        }
        this.buildJob();

        // Crear instancia de Trigger
        this.buildTrigger();

        // Crea una instancia de Scheduler
        this.buildScheduler();
    }

    public void stopScheduling() throws SchedulerException {
        if (scheduler.isStarted()) {
            scheduler.interrupt(job.getKey());
            scheduler.deleteJob(job.getKey());
//            scheduler.shutdown();
        }
    }

    private void buildJob() {
        this.job = JobBuilder.newJob(BackUpJob.class)
                .withIdentity(JOB_NAME)
                .build();
        //SE ENVIA OBJETO AL A LA IMPLEMENTACION DEL LA INTERFACE JOB
        this.job.getJobDataMap().put("config", this.config);

    }

    // Crear instancia de Trigger
    private void buildTrigger() {
        this.trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("backUpTrigger", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                //                .withSchedule(
                //                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }

    // Crea una instancia de Scheduler
    private void buildScheduler() {
        try {
            this.scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);// enlazar JobDetail y Trigger
        } catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }

    private void buidCron() {
        
    
        if (this.config.getScheduling().equals("one")) {
            
        }
        if (this.config.getScheduling().equals("daily")) {

        }
        if (this.config.getScheduling().equals("montly")) {

        }
    }

}
