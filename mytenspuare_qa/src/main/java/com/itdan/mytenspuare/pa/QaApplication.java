package com.itdan.mytenspuare.pa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;


/**
 * 问答模块入口
 */
@SpringBootApplication
public class QaApplication {

    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }


        @Bean
        public IdWorker idWorker(){
            return  new IdWorker(1,1);
        }

}
