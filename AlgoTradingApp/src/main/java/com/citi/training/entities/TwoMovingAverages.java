package com.citi.training.entities;


import com.citi.training.misc.Action;

public class TwoMovingAverages extends Strategy {




    @Override
    public Action getAction() {
        return Action.HOLD;
    }



}
