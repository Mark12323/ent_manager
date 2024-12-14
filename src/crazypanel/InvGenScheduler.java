
package crazypanel;

import javax.swing.JOptionPane;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class InvGenScheduler implements Runnable {
    @Override
    public void run() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            if (!scheduler.getJobKeys(GroupMatcher.anyJobGroup()).isEmpty()) {
                // Iterate through the keys of all the jobs and delete each job
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyJobGroup())) {
                    scheduler.deleteJob(jobKey);
                    System.out.println("Deleted job: " + jobKey);
                }
            }
            

            JobDetail jobw = JobBuilder.newJob(InvGen.class)
                .withIdentity("group1.pdfGenerationJob1", "group1")
                .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("mailGenerationJob", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 11 L * ?")) // Fires at 11:00 PM on the last day of each month
                    .build();
            
            JobDetail job1 = JobBuilder.newJob(MailGen.class)
                .withIdentity("mailGenerationTrigger", "group1")
                .build();

            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("pdfGenerationTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 11 L * ?")) // Fires at 11:00 PM on the last day of each month
                    .build();
            
            
            scheduler.scheduleJob(jobw, trigger1);
            scheduler.scheduleJob(job1, trigger);
            Thread.sleep(5 * 60 * 1000);

            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "A Fatal error is preventing Auto Invoice genereation and sending, ensure server is connected to internet");
        }
    }
}
