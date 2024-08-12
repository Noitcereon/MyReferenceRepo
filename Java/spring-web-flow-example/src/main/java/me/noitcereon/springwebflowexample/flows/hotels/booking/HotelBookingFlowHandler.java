package me.noitcereon.springwebflowexample.flows.hotels.booking;

import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

import java.io.Serializable;

public class HotelBookingFlowHandler extends AbstractFlowHandler implements Serializable {

    private String name;
    public void customInitMethod(){
        System.out.println("Hello from customInitMethod :>");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
