package org.springframework.samples.petclinic.sfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesWordProducer implements WordProducer {

    private String word;

    @Override
    public String getWord() {
        return word;
    }

    @Value("${producer.word}")
    public void setWord(String word) {
        this.word = word;
    }
}
