package com.backend.integrador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // or use "*" to allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }*/

    /*ESTE METODO DE ACA ES EL QUE PERMITE EL USO DEL FRONT*/
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login");
                        //.allowedOrigins("http://localhost:4200")
                        //.allowedMethods("*")
                        //.exposedHeaders("*");
                //registry.addMapping("/find/**");
               // registry.addMapping("/test/**")
                //        .allowedOrigins("http://localhost:4200")
                //        .allowedMethods("*");
            }
        };
    }
}