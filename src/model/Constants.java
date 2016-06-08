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
