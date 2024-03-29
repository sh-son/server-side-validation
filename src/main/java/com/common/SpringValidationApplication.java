package com.common;

import com.common.exception.ValidCustomException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@SpringBootApplication
public class SpringValidationApplication {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
                Throwable error = getError(webRequest);

                if (error instanceof ValidCustomException) {
                    errorAttributes.put("errors", ((ValidCustomException)error).getErrors());
                }

                return errorAttributes;
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringValidationApplication.class, args);
    }

}
