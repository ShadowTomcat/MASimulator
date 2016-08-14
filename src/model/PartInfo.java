/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.List;

/**
 * Abstract Class. Parent of ArthurInfo and EnemyInfo.
 *
 * @author Shadow
 */
public abstract class PartInfo {

    //public abstract Integer[] getCurrentAttr();
    public abstract void initCurrentAttr(Integer[] currentAttr);

    public abstract Integer getCurrentAttr(Integer index);

    public abstract String getCurrentAttrStr();

    public abstract void changeCurrentAttr(Integer valueChanged, Integer index);

    public abstract Integer[] getTypeDef();

    public abstract void setTypeDef(Integer[] typeDef);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Boolean isDead();

    public abstract void setDead(Boolean isDead);

    public abstract Integer getDamageTaken();

    public abstract void setDamageTaken(Integer damage);

    public abstract Integer getDamageTakenNowTurn();

    public abstract void setDamageTakenNowTurn(Integer damageTakenNowTurn);

    public abstract Integer getpDamageTakenNowTurn();

    public abstract void setpDamageTakenNowTurn(Integer pDamageTakenNowTurn);

    public abstract Integer getmDamageTakenNowTurn();

    public abstract void setmDamageTakenNowTurn(Integer mDamageTakenNowTurn);

    public abstract Integer geteDamageTakenNowTurn(Integer damageType);

    public abstract void seteDamageTakenNowTurn(Integer aDamageTakenNowTurn, Integer damageType);

    public abstract Integer getDamageNumNowTurn(Integer damageType);

    public abstract void setDamageNumNowTurn(Integer damageNum, Integer damageType);

    public abstract void addDamageNumNowTurn(Integer damageTimes, Integer damageType);

    public abstract Integer getSkillTypeTakenNowTurn(Integer skillTypeIndex);

    public abstract void setSkillTypeTakenNowTurn(Integer skillTypeIndex);

    public abstract HashMap<String, List<BuffInfo>> getBuffs();

    public abstract void setBuffs(HashMap<String, List<BuffInfo>> buffs);

    public abstract boolean isArthur();

    public abstract String getType();

    public abstract void setType(String type);

    public abstract Integer[] getTypeRate();

    public abstract void setTypeRate(Integer[] typeRate);

    public abstract Boolean[] getDarknessPos();

    public abstract void setDarknessPos(Boolean[] darknessPos);

    public abstract Integer getParent();

    public abstract String getOriginalType();

    public abstract Integer getHealTakenNowTurn();

    public abstract void setHealTakenNowTurn(Integer healTakenNowTurn);

    public abstract Integer getDeadCount();

    public abstract void setDeadCount(Integer deadCount);

    public abstract Integer getPartIndex();
}
