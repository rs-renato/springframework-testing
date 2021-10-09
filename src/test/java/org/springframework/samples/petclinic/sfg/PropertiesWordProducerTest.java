package org.springframework.samples.petclinic.sfg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("classpath:word.properties")
@SpringJUnitConfig(classes= PropertiesWordProducerTest.TestConfig.class)
class PropertiesWordProducerTest {

    @Configuration
    static class TestConfig{

        @Bean
        HearingInterpreter hearingInterpreter(WordProducer wordProducer){
            return new HearingInterpreter(wordProducer);
        }

        @Bean
        @Primary
        WordProducer wordProducer(){
            return new PropertiesWordProducer();
        }
    }

    @Autowired
    HearingInterpreter interpreter;

    @Test
    void whatIheard(){
        String word = interpreter.whatIheard();
        assertEquals("externalWord", word);
    }
}