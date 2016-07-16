/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Shadow
 */
public class ArthurInfo extends PartInfo implements Cloneable {

    private String id; // Mercenary, Millionare, Thief, Singer
    private String name; // 佣兵亚瑟，富豪亚瑟，盗贼亚瑟，歌姬亚瑟
    private Integer partIndex;
    private Boolean dead = false;
    private Integer deadCount = 0;
    private String type = "NONE";              // 100% for all damages.
    private final String originalType = "NONE";
    private HashMap<String, ArthurSkill> deck; // Key is SKILL id, as we consider a card as solely a skill in the battle.
    private HashMap<String, ArthurSkill> spheres;         // A sphere is a 'special' card (or skill)
    private Integer costUsed = 0;
    private Integer costBlocked = 0;
    private Integer damageTaken = 0;
    private Integer damageTakenNowTurn = 0;
    private Integer pDamageTakenNowTurn = 0;    // Physical
    private Integer mDamageTakenNowTurn = 0;    // Magic
    private final Integer[] eDamageTakenNowTurn = new Integer[]{0, 0, 0, 0, 0};    // Enchant Damage
    private final Integer[] damageNumNowTurn = new Integer[]{0, 0, 0};        // Physics, Magic, Enchant
    private Integer healTakenNowTurn = 0;
    private final Integer parent = -1;

    private Integer[] currentAttr;  //HP, ATK, INT, MND, DEF, MDEF, DMG_REDUCTION, MAX_HP, CRIT
    private Integer[] realAttr;  //without limit
    private final Integer[] attrLowerLimit = new Integer[]{Integer.MIN_VALUE, 0, 0, 0,
        Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
    private final Integer[] attrUpperLimit = new Integer[]{Integer.MAX_VALUE, 99999, 99999, 99999,
        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    private Integer[] typeRate = new Integer[]{100, 100, 100, 100, 100};
    private HashMap<String, List<BuffInfo>> buffs;      // Map<BuffName, List<BuffInfo>>.

    private List<String> firstRoundDeck;
    private List<String> sphereList;                            // Records un-used sphere's skill id.
    private HandCardInfo[] handCards = new HandCardInfo[5];     // Size 5, can be null.
    private HandCardInfo[] handSpheres = new HandCardInfo[5];   // Size 5 (but max = 3), can be null.
    private List<String> deckCards = new LinkedList<>();        // Current cards still in deck.
    private Boolean[] darknessPos = new Boolean[]{false, false, false, false, false};   // True = darkness, false = not.

    private List<HandCardInfo> currentPlayedItem = new LinkedList<>();

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
    public void setDead(Boolean dead) {
        this.dead = dead;
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getOriginalType() {
        return originalType;
    }

    public HashMap<String, ArthurSkill> getDeck() {
        return deck;
    }

    public void setDeck(HashMap<String, ArthurSkill> deck) {
        this.deck = deck;
    }

    public HashMap<String, ArthurSkill> getSpheres() {
        return spheres;
    }

    public void setSpheres(HashMap<String, ArthurSkill> spheres) {
        this.spheres = spheres;
    }

    @Override
    public Integer getParent() {
        return parent;
    }

    public void setSphereList(List<String> sphereList) {
        this.sphereList = sphereList;
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

    public void setFirstRoundDeck(List<String> firstRoundDeck) {
        this.firstRoundDeck = firstRoundDeck;
    }

    public void setDeckCards(List<String> deckCards) {
        this.deckCards = deckCards;
    }

    @Override
    public String getCurrentAttrStr() {
        return Arrays.toString(currentAttr);
    }

    @Override
    public Integer[] getTypeRate() {
        return typeRate;
    }

    @Override
    public void setTypeRate(Integer[] typeRate) {
        this.typeRate = typeRate;
    }

    @Override
    public HashMap<String, List<BuffInfo>> getBuffs() {
        return buffs;
    }

    @Override
    public void setBuffs(HashMap<String, List<BuffInfo>> buffs) {
        this.buffs = buffs;
    }

    public Integer getCostUsed() {
        return costUsed;
    }

    public void setCostUsed(Integer costUsed) {
        this.costUsed = costUsed;
    }

    public Integer getCostBlocked() {
        return costBlocked;
    }

    public void setCostBlocked(Integer costBlocked) {
        this.costBlocked = costBlocked;
    }

    public List<String> getFirstRoundDeck() {
        return firstRoundDeck;
    }

    public List<String> getDeckCards() {
        return deckCards;
    }

    public HandCardInfo[] getHandCards() {
        return handCards;
    }

    public void setHandCards(HandCardInfo[] handCards) {
        this.handCards = handCards;
    }

    public HandCardInfo[] getHandSpheres() {
        return handSpheres;
    }

    public void setHandSpheres(HandCardInfo[] handSpheres) {
        this.handSpheres = handSpheres;
    }

    public List<HandCardInfo> getCurrentPlayedItem() {
        return currentPlayedItem;
    }

    public void setCurrentPlayedItem(List<HandCardInfo> currentPlayedSkill) {
        this.currentPlayedItem = currentPlayedSkill;
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
    public Integer geteDamageTakenNowTurn(Integer damageType) {
        if (damageType == null) {
            Integer damageSum = 0;
            for (Integer damage : eDamageTakenNowTurn) {
                damageSum += damage;
            }
            return damageSum;
        } else {
            return eDamageTakenNowTurn[damageType];
        }
    }

    @Override
    public void seteDamageTakenNowTurn(Integer eDamageTakenNowTurn, Integer damageType) {
        if (damageType == null) {
            for (int i = 0; i < this.eDamageTakenNowTurn.length; i++) {
                this.eDamageTakenNowTurn[i] = eDamageTakenNowTurn;
            }
        } else {
            this.eDamageTakenNowTurn[damageType] = eDamageTakenNowTurn;
        }
    }

    /**
     * @param damageType - Physics(0), Magic(1), Enchant(2), or ALL(3).
     * @return - Total damage numbers.
     */
    @Override
    public Integer getDamageNumNowTurn(Integer damageType) {
        if (damageType == 3) {                          // All damage nums
            Integer damageNumSum = 0;
            for (Integer damageNum : damageNumNowTurn) {
                damageNumSum += damageNum;
            }
            return damageNumSum;
        } else if (damageType >= 0 && damageType <= 1){ // Physics or magic includes enchant
            return damageNumNowTurn[damageType] + damageNumNowTurn[2];
        }else {
            return damageNumNowTurn[damageType];
        }
    }

    @Override
    public void setDamageNumNowTurn(Integer damageNumNowTurn, Integer damageType) {
        if (damageType == 3) {
            for (int i = 0; i < this.damageNumNowTurn.length; i++) {
                this.damageNumNowTurn[i] = damageNumNowTurn;
            }
        } else {
            this.damageNumNowTurn[damageType] = damageNumNowTurn;
        }
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
        return true;
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
        sb.append("\nArthurInfo{" + "name=").append(id).append(", chineseName=").append(name);
        sb.append("\ncards=").append(deck).append("\nspheres=").append(spheres).append("\ncurrentAttr=").append(Arrays.toString(currentAttr)).append("\nbuffs=").append(buffs).append('}');
        return sb.toString();
    }

    public void createFirstRoundDeck() {
        firstRoundDeck = new LinkedList<>();
        for (ArthurSkill card : deck.values()) {
            firstRoundDeck.add(card.getSkillId());
            deckCards.add(card.getSkillId());
        }

        long n = firstRoundDeck.size();
        String temp;
        for (int i = 0; i < n; i++) {
            Integer index = ((Long) Math.round(Math.random() * (n - i) - 0.5 + i)).intValue();
            if (index != i) {
                temp = firstRoundDeck.get(i);
                firstRoundDeck.set(i, firstRoundDeck.get(index));
                firstRoundDeck.set(index, temp);
            }
        }

        // Initialize spheres
        for (int i = 0; i < spheres.size(); i++) {
            handSpheres[i] = new HandCardInfo(sphereList.get(i), spheres.get(sphereList.get(i)), i, 0, false, 0, 0, 0);
        }
    }

    // Draw a card, return the skill id.  -1 if hand is already full.
    public String drawCard(Integer currentTurn) {
        String skillId;
        if (!firstRoundDeck.isEmpty()) {
            skillId = firstRoundDeck.get(0);
        } else {
            int index = ((Long) Math.round(Math.random() * deckCards.size() - 0.5)).intValue();
            skillId = deckCards.get(index);
        }

        // If hand is not full, draw card and remove from deck.
        for (int i = 0; i < 5; i++) {
            if (handCards[i] == null) {
                handCards[i] = new HandCardInfo(skillId, deck.get(skillId), i, 0, false, 0, 0, currentTurn);
                if (!firstRoundDeck.isEmpty()) {
                    firstRoundDeck.remove(0);
                    handCards[i].setFirstRoundCard(true);
                } else {
                    handCards[i].setFirstRoundCard(false);
                }
                deckCards.remove(skillId);
                return skillId;
            }
        }
        return null;
    }

    // Actually plays a card with given index (0-4), and put it back to deck.
    // Return the SKILL_ID (Not card id, since sphere has no card id).
    public void playCard(int index) {
        if (handCards[index] == null) {
            System.out.println("ArthurInfo: Error 247.");
            return;
        }
        String skillId = handCards[index].getSkillId();
        handCards[index] = null;
        deckCards.add(skillId);
    }

    public void playSphere(Integer index) {
        if (handSpheres[index] == null) {
            System.out.println("ArthurInfo: Error 257.");
            return;
        }
        handSpheres[index] = null;
    }

    public void decreaseSealedTurns() {
        for (HandCardInfo card : handCards) {
            if (card != null && card.getIsSealed() > 0) {
                card.setIsSealed(card.getIsSealed() - 1);
            }
        }
    }

    @Override
    public ArthurInfo clone() throws CloneNotSupportedException {
        ArthurInfo clone = (ArthurInfo) super.clone();
        clone.setCurrentAttr(Arrays.copyOf(currentAttr, currentAttr.length));
        clone.setRealAttr(Arrays.copyOf(realAttr, realAttr.length));
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
        clone.setFirstRoundDeck(new ArrayList<>(firstRoundDeck));
        HandCardInfo[] cloneHand = new HandCardInfo[5];
        for (int i = 0; i < 5; i++) {
            if (handCards[i] != null) {
                cloneHand[i] = handCards[i].clone();
            }
        }
        clone.setHandCards(cloneHand);
        cloneHand = new HandCardInfo[5];
        for (int i = 0; i < 5; i++) {
            if (handSpheres[i] != null) {
                cloneHand[i] = handSpheres[i].clone();
            }
        }
        clone.setHandSpheres(cloneHand);
        clone.setDeckCards(new ArrayList<>(deckCards));
        clone.setDarknessPos(Arrays.copyOf(darknessPos, darknessPos.length));
        List<HandCardInfo> clonedPlayedItem = new LinkedList<>();
        for (HandCardInfo card : currentPlayedItem) {
            clonedPlayedItem.add(card.clone());
        }
        clone.setCurrentPlayedItem(clonedPlayedItem);

        return clone;
    }
}
