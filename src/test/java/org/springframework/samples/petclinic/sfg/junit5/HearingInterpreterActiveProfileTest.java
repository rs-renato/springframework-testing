package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.WordProducer;
import org.springframework.samples.petclinic.sfg.YannyWordProducer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("yanny")
@SpringJUnitConfig(classes= HearingInterpreterActiveProfileTest.TestConfig.class)
class HearingInterpreterActiveProfileTest {

    @Configuration
    static class TestConfig{

      @Bean
      HearingInterpreter hearingInterpreter(WordProducer wordProducer){
          return new HearingInterpreter(wordProducer);
      }

      @Bean
      @Profile("yanny")
      WordProducer wordProducer(){
        return new YannyWordProducer();
      }

    }

    @Autowired
    HearingInterpreter interpreter;

    @Test
    void whatIheard(){
        String word = interpreter.whatIheard();
        assertEquals("yanny", word);
    }
}