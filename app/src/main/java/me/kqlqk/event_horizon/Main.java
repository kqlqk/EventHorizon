/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package me.kqlqk.event_horizon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class)
                .build()
                .run(args);
    }
}
