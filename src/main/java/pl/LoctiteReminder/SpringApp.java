package pl.LoctiteReminder;

import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class SpringApp {
    public static void main(String[] args) {
       ConfigurableApplicationContext ctx= SpringApplication.run(SpringApp.class,args);


    }
}
