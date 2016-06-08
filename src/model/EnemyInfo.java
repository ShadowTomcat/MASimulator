/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Shadow
 */
public class EnemyInfo extends PartInfo implements Cloneable {

    @Override
    public EnemyInfo clone() throws CloneNotSupportedException {
        EnemyInfo clone = (EnemyInfo) super.clone();
        clone.setCurrentAttr(Arrays.copyOf(currentAttr, currentAttr.length));
        clone.setRealAttr(Arrays.copyOf(realAttr, realAttr.length));
        clone.setTypeRate(Arrays.copyOf(typeRate, typeRate.length));
        HashMap<String, List<BuffInfo>> cloneBuff = new HashMap<>();
        for (String key : buffs.keySet()) {
            cloneBuff.put(key, new ArrayList<>());
            if (!buffs.get(key).isEmpty()) {
                for (BuffInfo buff : buffs.get(key)) {
                    cloneBuff.get(key).add(buff.clone());
                }
            }
        }
        clone.setBuffs(buffs);
        List<EnemySkill> clonedSkills = new ArrayList<>();
        for (EnemySkill skill : skills) {
            clonedSkills.add(skill.clone());
        }
        clone.setSkills(clonedSkills);
        clone.setDarknessPos(Arrays.copyOf(darknessPos, darknessPos.length));
        return clone;
    }

    private String id;
    private String name;
    private Integer partIndex;
    private Boolean dead = false;
    private Integer deadCount = 0;
    private String originalType;
    private String type;    // May change
    private Integer actionPoint;
    private Integer currentActionPoint;
    private Integer parent; // the index of the parent, normally = 0 (1 in csv file) or -1; 
    private List<EnemyInfo> parts;

    private Integer[] currentAttr;  //HP, ATK, INT, MND, DEF, MDEF, DMG_REDUCTION, MAX_HP, CRIT
    private Integer[] realAttr;  //without limit
    private final Integer[] attrLowerLimit = new Integer[]{Integer.MIN_VALUE, 0, 0, 0,
        Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
    private final Integer[] attrUpperLimit = new Integer[]{Integer.MAX_VALUE, 99999, 99999, 99999,
        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    private Integer[] typeRate;     //FIRE, ICE, WIND, LIGHT, DARK
    private String passiveSkill;
    private List<EnemySkill> skills;
    private HashMap<String, List<BuffInfo>> buffs;      // Map<BuffName, List<BuffInfo>>.

    private Integer damageTaken = 0;
    private Integer damageTakenNowTurn = 0;
    private Integer pDamageTakenNowTurn = 0;    // Physical
    private Integer mDamageTakenNowTurn = 0;    // Magic
    private Integer aDamageTakenNowTurn = 0;    // Additional Damage 
    private Integer healTakenNowTurn = 0;
    private Boolean[] darknessPos = new Boolean[]{false, false, false, false, false};   // True = darkness, false = not.

    public EnemyInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Integer getPartIndex() {
        return partIndex;
    }

    public void setPartIndex(Integer partIndex) {
        this.partIndex = partIndex;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean isDead() {
        return dead;
    }

    @Override
    public void setDead(Boolean isDead) {
        this.dead = isDead;
    }

    @Override
    public Integer getDeadCount() {
        return deadCount;
    }

    @Override
    public void setDeadCount(Integer deadCount) {
        this.deadCount = deadCount;
    }

    @Override
    public void initCurrentAttr(Integer[] currentAttr) {
        this.currentAttr = currentAttr;
        this.realAttr = Arrays.copyOf(currentAttr, currentAttr.length);
    }

    @Override
    public Integer getCurrentAttr(Integer index) {
        return currentAttr[index];
    }

    @Override
    public String getCurrentAttrStr() {
        return Arrays.toString(currentAttr);
    }

    @Override
    public void changeCurrentAttr(Integer valueChanged, Integer index) {
        this.realAttr[index] += valueChanged;
        this.currentAttr[index] = Math.min(Math.max(realAttr[index], attrLowerLimit[index]), attrUpperLimit[index]);
    }

    public void setCurrentAttr(Integer[] currentAttr) {
        this.currentAttr = currentAttr;
    }

    public void setRealAttr(Integer[] realAttr) {
        this.realAttr = realAttr;
    }

    @Override
    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public List<EnemySkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EnemySkill> skills) {
        this.skills = skills;
    }

    public Integer getActionPoint() {
        return actionPoint;
    }

    public void setActionPoint(Integer actionPoint) {
        this.actionPoint = actionPoint;
    }

    public Integer getCurrentActionPoint() {
        return currentActionPoint;
    }

    public void setCurrentActionPoint(Integer currentActionPoint) {
        this.currentActionPoint = currentActionPoint;
    }

    @Override
    public Integer[] getTypeRate() {
        return typeRate;
    }

    @Override
    public void setTypeRate(Integer[] typeRate) {
        this.typeRate = typeRate;
    }

    public String getPassiveSkill() {
        return passiveSkill;
    }

    public void setPassiveSkill(String passiveSkill) {
        this.passiveSkill = passiveSkill;
    }

    @Override
    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public List<EnemyInfo> getParts() {
        return parts;
    }

    public void setParts(List<EnemyInfo> parts) {
        this.parts = parts;
    }

    @Override
    public HashMap<String, List<BuffInfo>> getBuffs() {
        return buffs;
    }

    @Override
    public void setBuffs(HashMap<String, List<BuffInfo>> buffs) {
        this.buffs = buffs;
    }

    @Override
    public Integer getDamageTaken() {
        return damageTaken;
    }

    @Override
    public void setDamageTaken(Integer damageTaken) {
        this.damageTaken = damageTaken;
    }

    @Override
    public Integer getDamageTakenNowTurn() {
        return damageTakenNowTurn;
    }

    @Override
    public void setDamageTakenNowTurn(Integer damageTakenNowTurn) {
        this.damageTakenNowTurn = damageTakenNowTurn;
    }

    @Override
    public Integer getpDamageTakenNowTurn() {
        return pDamageTakenNowTurn;
    }

    @Override
    public void setpDamageTakenNowTurn(Integer pDamageTakenNowTurn) {
        this.pDamageTakenNowTurn = pDamageTakenNowTurn;
    }

    @Override
    public Integer getmDamageTakenNowTurn() {
        return mDamageTakenNowTurn;
    }

    @Override
    public void setmDamageTakenNowTurn(Integer mDamageTakenNowTurn) {
        this.mDamageTakenNowTurn = mDamageTakenNowTurn;
    }

    @Override
    public Integer getaDamageTakenNowTurn() {
        return aDamageTakenNowTurn;
    }

    @Override
    public void setaDamageTakenNowTurn(Integer aDamageTakenNowTurn) {
        this.aDamageTakenNowTurn = aDamageTakenNowTurn;
    }

    @Override
    public Integer getHealTakenNowTurn() {
        return healTakenNowTurn;
    }

    @Override
    public void setHealTakenNowTurn(Integer healTakenNowTurn) {
        this.healTakenNowTurn = healTakenNowTurn;
    }

    @Override
    public boolean isArthur() {
        return false;
    }

    @Override
    public Boolean[] getDarknessPos() {
        return darknessPos;
    }

    @Override
    public void setDarknessPos(Boolean[] darknessPos) {
        this.darknessPos = darknessPos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nEnemyInfo{" + "id=").append(id).append(", name=").append(name).append(", type=")
                .append(type).append(", actionPoint=").append(actionPoint).append(", parent=")
                .append(parent).append(", currentAttr=").append(Arrays.toString(currentAttr)).append(", typeRate=")
                .append(Arrays.toString(typeRate)).append(", passiveSkill=").append(passiveSkill).append("}");
        sb.append("\n");
        for (EnemySkill skill : skills) {
            sb.append(skill.toString()).append("\n");
        }
        return sb.toString();
    }

}
