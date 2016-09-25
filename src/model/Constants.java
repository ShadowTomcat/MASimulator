/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Shadow
 */
public class Constants {

    public static final HashMap<String, Integer> FUNC_ORDER_MAP = new HashMap<>();
    public static final HashMap<String, String> CATEGORY_MAP = new HashMap<>();
    public static final Long SLEEP_SHORT = 1000L;
    
    
    public static final int SAME_CARD_ID = 3;
    public static final int CARD_NORMAL_SKILL = 25;
    public static final int CARD_BOOST_SKILL = 26;
    
    public static final int SKILL_ROLE_FUNCTION_COL = 7;
    public static final int SKILL_ROLE_TARGET = 8;
    public static final int SKILL_ROLE_PARAM1 = 19;
    public static final int SKILL_ROLE_PARAM2 = 20;
    public static final int SKILL_ROLE_PARAM3 = 21;
    public static final int SKILL_ROLE_PARAM4 = 22;
    public static final int SKILL_ROLE_PARAM5 = 23;
    public static final int SKILL_ROLE_PARAM6 = 24;
    public static final int SKILL_ROLE_PARAM7 = 25;
    public static final int SKILL_ROLE_PARAM8 = 26;
    public static final int SKILL_ROLE_PARAM9 = 27;
    public static final int SKILL_ROLE_PARAM10 = 28;
    
    public static final int SKILL_ID = 0;
    public static final int SKILL_CATEGORY = 9;
    public static final int SKILL_TYPES = 10;
    public static final int SKILL_PVE_PRIORITY = 14;
    public static final int SKILL_TARGET = 18;
    public static final int SKILL_SKILLROLE_COL = 35;
    public static final int SKILL_CHAIN_BOOST = 27;
    public static final int SKILL_BOOST_CONDITION = 28;
    public static final int SKILL_BOOST_PARAM1 = 29;
    public static final int SKILL_BOOST_PARAM2 = 30;
    public static final int SKILL_BOOST_PARAM3 = 31;
    public static final int SKILL_PRIORITY = 34;
    
    public static final int SKILL_TRIGGER_TIME = 19;
    public static final int SKILL_TRIGGER_LAST = 20;
    public static final int SKILL_TRIGGER_CONDITION = 21;
    public static final int SKILL_TRIGGER_PARAM1 = 22;
    public static final int SKILL_TRIGGER_PARAM2 = 23;
    public static final int SKILL_TRIGGER_PARAM3 = 24;
    
    public static final int ENEMY_AI_CONDITION = 1;
    public static final int ENEMY_AI_LOW_HP = 2;
    public static final int ENEMY_AI_HIGH_HP = 3;;
    public static final int ENEMY_AI_MAIN_LOW_HP = 4;
    public static final int ENEMY_AI_MAIN_HIGH_HP = 5;
    public static final int ENEMY_AI_TRIGGER_TYPE = 28;
    public static final int ENEMY_AI_TRIGGER_PARAM1 = 29;
    public static final int ENEMY_AI_TRIGGER_PARAM2 = 30;
    public static final int ENEMY_AI_TRIGGER_PARAM3 = 31;
    public static final int ENEMY_AI_TURN_SHIFT = 6;
    
    public static final Comparator HAND_CARD_CMP = new Comparator<HandCardInfo>() {
        @Override
        public int compare(HandCardInfo o1, HandCardInfo o2) {
            return o1.getCardSkillPriority().compareTo(o2.getCardSkillPriority());
//            if (!o1.getCardSkillPriority().equals(o2.getCardSkillPriority())) {
//                return o1.getCardSkillPriority().compareTo(o2.getCardSkillPriority());
//            } else {
//                if (o1.getSkill().getArthurIndex().equals(o2.getSkill().getArthurIndex())) {
//                    return o1.getPlayedOrder().compareTo(o2.getPlayedOrder());
//                } else {
//                    return 0;
//                }
//            }
        }
    };

    static {
        CATEGORY_MAP.put("ATTACK", "物理");
        CATEGORY_MAP.put("SORCERY", "魔法");
        CATEGORY_MAP.put("SUPPORT", "支援");
        CATEGORY_MAP.put("JAMMING", "弱化");
        CATEGORY_MAP.put("RECOVERY", "治疗");
        CATEGORY_MAP.put("DEFENSE", "防御");

        int i = 1;
        FUNC_ORDER_MAP.put("REVIVE", i += 10);
        FUNC_ORDER_MAP.put("ATTACK_AA", i += 10);
        FUNC_ORDER_MAP.put("HP_CUT", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_DRAIN", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_PIERCING", i += 10);
        FUNC_ORDER_MAP.put("ATK_UP_FIXED", i += 10);
        FUNC_ORDER_MAP.put("DEF_UP_FIXED", i += 10);
        FUNC_ORDER_MAP.put("HEAL_FIXED", i += 10);
        FUNC_ORDER_MAP.put("ATK_BREAK_FIXED", i += 10);
        FUNC_ORDER_MAP.put("GUARD_BREAK_FIXED", i += 10);
        FUNC_ORDER_MAP.put("REGENERATE_FIXED", i += 10);
        FUNC_ORDER_MAP.put("ATK_UP_BY_SELF_PARAM", i += 10);
        FUNC_ORDER_MAP.put("DEF_UP_BY_SELF_PARAM", i += 10);
        FUNC_ORDER_MAP.put("HEAL_BY_SELF_PARAM", i += 10);
        FUNC_ORDER_MAP.put("ATK_BREAK_BY_SELF_PARAM", i += 10);
        FUNC_ORDER_MAP.put("GUARD_BREAK_BY_SELF_PARAM", i += 10);
        FUNC_ORDER_MAP.put("CRITICAL_UP", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_DAMAGE_INCREASE", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_REVENGE", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_NOW_TURN_REVENGE", i += 10);
        FUNC_ORDER_MAP.put("HEAL_BY_TARGET_MAXHP", i += 10);
        FUNC_ORDER_MAP.put("FREEZE", i += 10);
        FUNC_ORDER_MAP.put("BLEED", i += 10);
        FUNC_ORDER_MAP.put("POISON", i += 10);
        FUNC_ORDER_MAP.put("ELECTRIC", i += 10);
        FUNC_ORDER_MAP.put("BURN", i += 10);
        FUNC_ORDER_MAP.put("WEAKNESS", i += 10);
        FUNC_ORDER_MAP.put("CARD_TRAP_DAMAGE", i += 10);
        FUNC_ORDER_MAP.put("CARD_SEAL", i += 10);
        FUNC_ORDER_MAP.put("DARKNESS_RANDOM", i += 10);
        FUNC_ORDER_MAP.put("DARKNESS_APPOINT", i += 10);
        FUNC_ORDER_MAP.put("HEAL_REVERSE", i += 10);
        FUNC_ORDER_MAP.put("BUFF_RELEASE", i += 10);
        FUNC_ORDER_MAP.put("BUFF_RELEASE_ONE", i += 10);
        FUNC_ORDER_MAP.put("DEBUFF_RELEASE", i += 10);
        FUNC_ORDER_MAP.put("DEBUFF_RELEASE_ONE", i += 10);
        FUNC_ORDER_MAP.put("CARD_SEAL_REGIST", i += 10);
        FUNC_ORDER_MAP.put("DARKNESS_REGIST", i += 10);
        FUNC_ORDER_MAP.put("COVERING", i += 10);
        FUNC_ORDER_MAP.put("STAN", i += 10);
        FUNC_ORDER_MAP.put("ATK_OP_ATTR_RATE_DOWN_INVALID", i += 10);
        FUNC_ORDER_MAP.put("ATTR_SEE", i += 10);
        FUNC_ORDER_MAP.put("ATTR_HIDE", i += 10);
        FUNC_ORDER_MAP.put("REWRITE", i += 10);
        FUNC_ORDER_MAP.put("DEAL_BONUS", i += 10);
        FUNC_ORDER_MAP.put("DEAL_PENALTY", i += 10);
        FUNC_ORDER_MAP.put("DEAL_PENALTY_TURN_APPOINT", i += 10);
        FUNC_ORDER_MAP.put("ENEMY_AI_TRIGGER_FLAG_SET", i += 10);
        FUNC_ORDER_MAP.put("ATTACK_BARRIER", i += 10);
        FUNC_ORDER_MAP.put("DESTRUCT", i += 10);

    }
}
