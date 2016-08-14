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
public class ArthurSkill {

    private String skillId;
    private String cardId;
    private String cardName;
    private String rarity;
    private Boolean isArBonus;
    private Boolean isSphere;
    private Integer turnAllowedAfter;
    private String dialogue;
    private String briefDescription;
    private String description;
    private Integer arthurIndex;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Boolean getIsArBonus() {
        return isArBonus;
    }

    public void setIsArBonus(Boolean isArBonus) {
        this.isArBonus = isArBonus;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public Boolean isSphere() {
        return isSphere;
    }

    public void setIsSphere(Boolean isSphere) {
        this.isSphere = isSphere;
    }

    public Integer getTurnAllowedAfter() {
        return turnAllowedAfter;
    }

    public void setTurnAllowedAfter(Integer turnAllowedAfter) {
        this.turnAllowedAfter = turnAllowedAfter;
    }

    public Integer getArthurIndex() {
        return arthurIndex;
    }

    public void setArthurIndex(Integer arthurIndex) {
        this.arthurIndex = arthurIndex;
    }

    @Override
    public String toString() {
        return cardName;
    }
    
}
