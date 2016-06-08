/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Shadow
 */
public class TurnInfoRecord {
    private Integer turn;
    private List<ArthurInfo> arthurInfo;
    private List<EnemyInfo> enemyInfo;
    private HashMap<String, Integer> enemyTriggerAiFlag;

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public List<ArthurInfo> getArthurInfo() {
        return arthurInfo;
    }

    public void setArthurInfo(List<ArthurInfo> arthurInfo) {
        this.arthurInfo = arthurInfo;
    }

    public List<EnemyInfo> getEnemyInfo() {
        return enemyInfo;
    }

    public void setEnemyInfo(List<EnemyInfo> enemyInfo) {
        this.enemyInfo = enemyInfo;
    }

    public HashMap<String, Integer> getEnemyTriggerAiFlag() {
        return enemyTriggerAiFlag;
    }

    public void setEnemyTriggerAiFlag(HashMap<String, Integer> enemyTriggerAiFlag) {
        this.enemyTriggerAiFlag = enemyTriggerAiFlag;
    }
    
    
}
