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
public class SetCard implements Cloneable{

    private String skillId;
    private Integer delayedTurnLeft;
    private HandCardInfo cardInfo;

    public SetCard(String skillId, Integer delayedTurnLeft, HandCardInfo cardInfo) {
        this.skillId = skillId;
        this.delayedTurnLeft = delayedTurnLeft;
        this.cardInfo = cardInfo;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public Integer getDelayedTurnLeft() {
        return delayedTurnLeft;
    }

    public void setDelayedTurnLeft(Integer delayedTurnLeft) {
        this.delayedTurnLeft = delayedTurnLeft;
    }

    public HandCardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(HandCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    @Override
    protected SetCard clone() throws CloneNotSupportedException {
        SetCard clone = (SetCard) super.clone();
        clone.setCardInfo(cardInfo.clone());
        return clone;
    }


}
