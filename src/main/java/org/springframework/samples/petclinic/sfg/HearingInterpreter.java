package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    private final WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatIheard(){
        return this.wordProducer.getWord();
    }

}
