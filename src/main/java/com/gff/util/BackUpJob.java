package com.gff.util;

import com.gff.model.entity.Configuracion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BackUpJob implements Job {

    private Configuracion config;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        config = (Configuracion) jec.getJobDetail().getJobDataMap().get("config");
        System.out.println(config.toString());
    }

}
