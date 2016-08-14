/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author Shadow
 */
public class HandCardInfo implements Cloneable {

    private String skillId;
    //private String skillName;
    private ArthurSkill skill;
    private Integer handPosition;           // 0 - 4.
    private Integer value;                  // Value without chain bonus. (For UI only)
    private boolean isPlayed;
    private Integer[] target;               // [0]:0=user, 1=enemy, 2=dead enemy; [1]:, 0-3=single part, 4=all; null = no target.
    private Integer boostPriority;          // Only for view, since arthur condition may change during skill play (e.g. HP) - 0 = not boosted, 1+ = boost priority.
    private Integer currentChain;           // 0 = 1Chain, 3 = 4Chain.
    private Integer playedOrder = -1;
    private Integer cardSkillPriority;      // May differ due to bonus condition, so do not store in ArthurSkill (which should NOT change during battle).
    private Integer isSealed = 0;               // 0 = not sealed, 1+ = sealed turns.
    private Integer isTrapped = 0;              // 0 = mot trapped, 1+ = trap damage.
    private boolean isFirstRoundCard;       // Used in card manual selection.
    private Integer dealedTurn;             // Used in card manual selection.
    private Integer delayedTurn;             // Whether a card is set, and for how long.
    
    public HandCardInfo() {
    }

    public HandCardInfo(String skillId, ArthurSkill skill, Integer handPosition, Integer value, boolean isPlayed, 
            Integer boostPriority, Integer currentChain, Integer dealedTurn) {
        this.skillId = skillId;
        this.skill = skill;
        this.handPosition = handPosition;
        this.value = value;
        this.isPlayed = isPlayed;
        this.boostPriority = boostPriority;
        this.currentChain = currentChain;
        this.dealedTurn = dealedTurn;
    }

    @Override
    protected HandCardInfo clone() throws CloneNotSupportedException {
        HandCardInfo clone = (HandCardInfo) super.clone();
        if (target != null) {
            clone.setTarget(Arrays.copyOf(target, target.length));
        }
        return clone;
    }

    public HandCardInfo(String skillId) {
        this.skillId = skillId;
    }

    public Integer getDealedTurn() {
        return dealedTurn;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public ArthurSkill getSkill() {
        return skill;
    }

    public void setSkill(ArthurSkill skill) {
        this.skill = skill;
    }

    public Integer getHandPosition() {
        return handPosition;
    }

    public void setHandPosition(Integer handPosition) {
        this.handPosition = handPosition;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getBoostPriority() {
        return boostPriority;
    }

    public void setBoostPriority(Integer boostPriority) {
        this.boostPriority = boostPriority;
    }

    public Integer getCardSkillPriority() {
        return cardSkillPriority;
    }

    public void setCardSkillPriority(Integer cardSkillPriority) {
        this.cardSkillPriority = cardSkillPriority;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setIsPlayed(boolean isPlayed) {
        this.isPlayed = isPlayed;
    }

    public Integer getCurrentChain() {
        return currentChain;
    }

    public void setCurrentChain(Integer currentChain) {
        this.currentChain = currentChain;
    }

    public Integer[] getTarget() {
        return target;
    }

    public void setTarget(Integer[] target) {
        this.target = target;
    }

    public Integer getPlayedOrder() {
        return playedOrder;
    }

    public void setPlayedOrder(Integer playedOrder) {
        this.playedOrder = playedOrder;
    }

    public Integer getIsSealed() {
        return isSealed;
    }

    public void setIsSealed(Integer isSealed) {
        this.isSealed = isSealed;
    }

    public Integer getIsTrapped() {
        return isTrapped;
    }

    public void setIsTrapped(Integer isTrapped) {
        this.isTrapped = isTrapped;
    }

    public Integer getDelayedTurn() {
        return delayedTurn;
    }

    public void setDelayedTurn(Integer delayedTurn) {
        this.delayedTurn = delayedTurn;
    }

    public boolean isFirstRoundCard() {
        return isFirstRoundCard;
    }

    public void setFirstRoundCard(boolean isFirstRoundCard) {
        this.isFirstRoundCard = isFirstRoundCard;
    }

}
