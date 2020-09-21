package pl.LoctiteReminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringApp {
    public static void main(String[] args) {
       ConfigurableApplicationContext ctx= SpringApplication.run(SpringApp.class,args);


    }
}
