package com.citi.training.entities;

public abstract class Strategy {

    enum Action{
        BUY,
        SELL,
        HOLD
    }



    String ticker;




    abstract Action getAction();







}
