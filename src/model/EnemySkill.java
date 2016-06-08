/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Shadow
 */
public class EnemySkill implements Comparable, Cloneable {

    private String skillId;
    private String aiOrderId;
    private Integer priority;
    private String target;
    private Integer cost;
    private Integer maxTimes;
    private Integer successRate;
    private Integer enemyIndex;

    public EnemySkill(String skillId, String aiOrderId, Integer priority, String target, Integer cost, Integer maxTimes, Integer successRate) {
        this.skillId = skillId;
        this.aiOrderId = aiOrderId;
        this.priority = priority;
        this.target = target;
        this.cost = cost;
        this.maxTimes = maxTimes;
        this.successRate = successRate;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getAiOrderId() {
        return aiOrderId;
    }

    public void setAiOrderId(String aiOrderId) {
        this.aiOrderId = aiOrderId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(Integer maxTimes) {
        this.maxTimes = maxTimes;
    }

    public Integer getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Integer successRate) {
        this.successRate = successRate;
    }

    public Integer getEnemyIndex() {
        return enemyIndex;
    }

    public void setEnemyIndex(Integer enemyIndex) {
        this.enemyIndex = enemyIndex;
    }

    @Override
    public int compareTo(Object o) {
        if (this == null && o == null) {
            return -1;
        }
        if (this == null) {
            return -1;
        }
        if (o == null) {
            return 1;
        }
        return this.priority.compareTo(((EnemySkill)o).getPriority());
    }

    public static void main(String[] args){
        List<EnemySkill> aList = new ArrayList<>();
        aList.add(new EnemySkill("1", "1", 1, "", 0, 0, 0));
        aList.add(new EnemySkill("2", "1", 2, "", 0, 0, 0));
        aList.add(new EnemySkill("3", "1", 2, "", 0, 0, 0));
        aList.add(new EnemySkill("4", "1", 2, "", 0, 0, 0));
        aList.add(new EnemySkill("5", "1", 1, "", 0, 0, 0));
        aList.add(new EnemySkill("6", "1", 3, "", 0, 0, 0));
        aList.add(new EnemySkill("7", "1", 1, "", 0, 0, 0));
        Collections.sort(aList);
        for(EnemySkill a : aList){
            System.out.println(a.getSkillId() + " | " + a.getPriority());
        }
    }

    @Override
    protected EnemySkill clone() throws CloneNotSupportedException {
        return (EnemySkill) super.clone();
    }

    @Override
    public String toString() {
        return "EnemySkill{" + "skillId=" + skillId + ", aiOrderId=" + aiOrderId + ", priority=" + priority + ", target=" + target + ", cost=" + cost + ", maxTimes=" + maxTimes + ", successRate=" + successRate + '}';
    }
    
    
}
