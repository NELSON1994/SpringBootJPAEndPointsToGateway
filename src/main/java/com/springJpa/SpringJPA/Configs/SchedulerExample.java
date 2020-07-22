package com.springJpa.SpringJPA.Configs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerExample {
    // print something every SECOND
  //  @Scheduled(fixedRate = 1000)
    public void printOutPut(){
        System.out.println("==================WONDERFUL TASK DONE===============================================");

    }

//  "0 0 * * * *" = the top of every hour of every day.
//  "*/10 * * * * *" = every ten seconds.
//  "0 * * * * ?"  ====every min
//  "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//  "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
// "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
// "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//  "0 0 0 25 12 ?" = every Christmas Day at midnight

    // execute job every 3min   from 4pm-5pm
    @Scheduled(cron="0 0/3 16-17 *  * * ")
    public void cronJobSch(){
        System.out.println("ppppppppppppppppppp======================ppppppppppppppppppppp");
    }
}
