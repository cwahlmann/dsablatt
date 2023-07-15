package de.dreierschach.dsablatt;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsaBlattApp implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(DsaBlattApp.class,args);
    }
}
