package com.itdan.mytenspuare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;


/**
 * 文章模块入口
 */
@SpringBootApplication
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class,args);
    }


        @Bean
        public IdWorker idWorker(){
            return  new IdWorker(1,1);
        }

}
