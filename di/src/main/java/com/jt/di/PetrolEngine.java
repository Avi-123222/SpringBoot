package com.jt.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary

public class PetrolEngine implements Engine{
    @Override
    public void startEngine() {
        System.out.println("Petrol Engine started...");
    }

    @Override
    public void stopEngine() {
        System.out.println("Petrol Engine stopped...");
    }
    
}
