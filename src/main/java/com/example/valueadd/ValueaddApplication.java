package com.example.valueadd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@SpringBootApplication
public class ValueaddApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValueaddApplication.class, args);
    }

}

@RestController
class WordCounterRestController{
    private static final WordCounter wordCounter = new WordCounter();

    @RequestMapping(value="/analyze", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String Analyze(@RequestBody String text){
        return wordCounter.Proceed(text).toString();
    }
}
