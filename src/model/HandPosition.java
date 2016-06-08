/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Shadow
 */
public class HandPosition {
    private boolean isCard;
    private int position;

    public HandPosition(boolean isCard, int position) {
        this.isCard = isCard;
        this.position = position;
    }

    public boolean isIsCard() {
        return isCard;
    }

    public void setIsCard(boolean isCard) {
        this.isCard = isCard;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    
}
