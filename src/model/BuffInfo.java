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
public class BuffInfo implements Cloneable {

    /**
     * Type : value (parameters) meaning.
     *
     * @ATTR_CHANGE - 0: value, 1: attribute index.
     * @REGENERATE - 0: value
     * @DOT - 0：value (without rate)，1：dot type index. NOTICE: actual damage
     * should re-calculate type rate.
     * @WEAKNESS - 0: Damage increase rate (%).
     * @HEAL_REVERSE - No parameter.
     * @REGIST - 0: Resist rate (%).
     * @DEAL - 0: card deal number. (minus is penalty)
     * @COVERING - 0：Damage Reduction (%).
     * @STAN - 0: Stun rate(%).
     * @ATTACK_BARRIER - 0: Damage absorb limit, 1: Damage absorb times, 2:
     * Physics/magic index.
     * @DARKNESS - 0-5: card position, 0 = not dark, 1 = dark.
     * @CARD_TRAP_DAMAGE - 0-5: card position, 6: trap damage.
     * @CARD_SEAL - 0-5: card position.
     * @ENCHANT - 0: damage value, 1: type index.
     * @DOT_VALUE_UP - 0：damage increase rate (%), 1: dot type index.
     */
    private Integer turnLeft;
    private final Integer turnSet;
    private final String type;            //Atk/Int/Mnd/Def/Mdef_up/down, regeneration, stun, card seal, dark, deal bonus/penalty, poison(s), trap, etc.
    private final Integer[] value;
    private final String buffName;        // Used in boost condition check or ai_order check.
    private final boolean isBuff;         //Buff or debuff.


    public BuffInfo(Integer turnLeft, Integer turnSet, String type, Integer[] value, String buffName, boolean isBuff) {
        this.turnLeft = turnLeft;
        this.turnSet = turnSet;
        this.type = type;
        this.value = value;
        this.buffName = buffName;
        this.isBuff = isBuff;
    }

    public Integer getTurnLeft() {
        return turnLeft;
    }
    
    public void extendTurn(Integer turns) {
        turnLeft += turns;
    }
    
    public Integer getTurnSet() {
        return turnSet;
    }

    public String getType() {
        return type;
    }

    public Integer[] getValue() {
        return value;
    }

    public String getBuffName() {
        return buffName;
    }

    public boolean isBuff() {
        return isBuff;
    }

    @Override
    protected BuffInfo clone() throws CloneNotSupportedException {
        return (BuffInfo) super.clone(); 
    }

    @Override
    public String toString() {
        return "BuffInfo{" + "turnLeft=" + turnLeft + ", turnSet=" + turnSet + ", type=" + type + ", value=" + Arrays.toString(value) + ", buffName=" + buffName + ", isBuff=" + isBuff + '}';
    }

}
