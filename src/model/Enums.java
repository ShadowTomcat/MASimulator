/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.EnumSet;
import java.util.HashMap;

/**
 *
 * @author Shadow
 */
public class Enums {

//    public static final String[] CLASS_NAME = new String[]{"佣兵亚瑟", "富豪亚瑟", "盗贼亚瑟", "歌姬亚瑟"};
//    public static final String[] CLASS_NAME_S = new String[]{"佣兵", "富豪", "盗贼", "歌姬"};
//    public static final String[] CLASS_NAME_EN = new String[]{"MERCENARY", "MILLIONARE", "THIEF", "SINGER"};
    public static final String[] CATEGORY_S = new String[]{"物理", "魔法", "弱化", "支援", "防御", "治疗"};

    public enum EnumArthur {

        A0("MERCENARY", "佣兵亚瑟", "佣兵", 0, new Color(206, 119, 90)), A1("MILLIONARE", "富豪亚瑟", "富豪", 1, new Color(255, 212, 112)),
        A2("THIEF", "盗贼亚瑟", "盗贼", 2, new Color(112, 255, 159)), A3("SINGER", "歌姬亚瑟", "歌姬", 3, new Color(255, 111, 216));

        private final String id;
        private final String name;
        private final String nameS;
        private final Integer index;
        private final Color bgColor;

        private EnumArthur(String id, String name, String nameS, Integer index, Color bgColor) {
            this.id = id;
            this.name = name;
            this.nameS = nameS;
            this.index = index;
            this.bgColor = bgColor;
        }

        public static String getNameSByIndex(Integer index) {
            for (EnumArthur c : EnumArthur.values()) {
                if (c.index.equals(index)) {
                    return c.nameS;
                }
            }
            return null;
        }

        public static String getNameByIndex(Integer index) {
            for (EnumArthur c : EnumArthur.values()) {
                if (c.index.equals(index)) {
                    return c.name;
                }
            }
            return null;
        }

        public static Color getColorByIndex(Integer index) {
            for (EnumArthur c : EnumArthur.values()) {
                if (c.index.equals(index)) {
                    return c.bgColor;
                }
            }
            return null;
        }

        public static Integer getIndexByName(String name) {
            for (EnumArthur c : EnumArthur.values()) {
                if (c.name.equals(name)) {
                    return c.index;
                }
            }
            return -1;
        }

        public static Integer getIndexById(String id) {
            for (EnumArthur c : EnumArthur.values()) {
                if (c.id.equals(id)) {
                    return c.index;
                }
            }
            return -1;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNameS() {
            return nameS;
        }

        public Integer getIndex() {
            return index;
        }
    }

    public enum EnumPhysicsMagic {

        PM0("PHYSICS", "物理", 0, "DEF"), PM1("MAGIC", "魔法", 1, "MDEF");
        private final String id;
        private final String name;
        private final Integer index;
        private final String defId;

        private EnumPhysicsMagic(String id, String name, Integer index, String defId) {
            this.id = id;
            this.name = name;
            this.index = index;
            this.defId = defId;
        }

        public static Integer getIndexById(String id) {
            for (EnumPhysicsMagic c : EnumPhysicsMagic.values()) {
                if (c.id.equals(id)) {
                    return c.index;
                }
            }
            return -1;
        }

        public static String getDefIdById(String id) {
            for (EnumPhysicsMagic c : EnumPhysicsMagic.values()) {
                if (c.id.equals(id)) {
                    return c.defId;
                }
            }
            return null;
        }

        public static String getNameById(String id) {
            for (EnumPhysicsMagic c : EnumPhysicsMagic.values()) {
                if (c.id.equals(id)) {
                    return c.name;
                }
            }
            return null;
        }

        public static String getNameByIndex(Integer index) {
            for (EnumPhysicsMagic c : EnumPhysicsMagic.values()) {
                if (c.index.equals(index)) {
                    return c.name;
                }
            }
            return null;
        }
    }

    public enum EnumBuff {

        B0("NULL", "无", "无", 0, null), B1("ATK_UP_BY_ATK", "物攻上升", "物攻↑", 1, true), B2("ATK_UP_BY_INT", "魔攻上升", "魔攻↑", 2, true),
        B3("ATK_BREAK_BY_ATK", "物攻下降", "物攻↓", 3, false), B4("ATK_BREAK_BY_INT", "魔攻下降", "魔攻↓", 4, false), B5("DEF_UP_BY_DEF", "物防上升", "物防↑", 5, true),
        B6("DEF_UP_BY_MDEF", "魔防上升", "魔防↑", 6, true), B7("GUARD_BREAK_BY_DEF", "物防下降", "物防↓", 7, false), B8("GUARD_BREAK_BY_MDEF", "魔防下降", "魔防↓", 8, false),
        B9("ATTR_SEE", "属性显示", "属性显示", 8, false), B10("WEAKNESS", "标记", "标记", 10, false), B11("DARKNESS", "黑暗", "黑暗", 11, false),
        B12("REGENERATE_FIXED", "持续恢复", "恢复", 12, true), B13("CARD_SEAL", "卡牌封印", "封印", 13, false), B14("CARD_TRAP_DAMAGE", "卡牌陷阱", "陷阱", 14, false),
        B15("BURN", "燃烧", "燃烧", 15, false), B16("FREEZE", "冰冻", "冰冻", 16, false), B17("BLEED", "裂风", "裂风", 17, false),
        B18("ELECTRIC", "感电", "感电", 18, false), B19("POISON", "中毒", "中毒", 19, false), B20("HEAL_REVERSE", "治疗反转", "治疗反转", 20, false),
        B21("CARD_SEAL_REGIST", "封印抗性", "抗封", 21, true), B22("DARKNESS_REGIST", "黑暗抗性", "抗暗", 22, true), B23("COVERING", "嘲讽", "嘲讽", 23, true),
        B24("STAN", "打断", "打断", 24, false), B25("ATTR_HIDE", "属性隐藏", "属性隐藏", 25, true), B26("REWRITE", "属性变更", "属性变更", 26, true),
        B27("DEAL_BONUS", "抽卡奖励", "抽卡", 27, true), B28("DEAL_PENALTY", "抽卡惩罚", "减抽", 28, false), B29("ATTACK_BARRIER", "吸收盾", "护盾", 29, true),
        B30("ATK_UP_BY_MND", "治疗量提升", "治疗↑", 30, true), B31("ATK_BREAK_BY_MND", "治疗量下降", "治疗↓", 30, true), B32("CRITICAL_UP", "暴击率提升", "暴击↑", 32, true),
        B33("BURN_RESIST", "燃烧抗性", "燃抗", 33, true), B34("FREEZE_RESIST", "冰冻抗性", "冻抗", 34, true), B35("BLEED_RESIST", "裂风抗性", "裂抗", 35, true),
        B36("ELECTRIC_RESIST", "感电抗性", "电抗", 36, true), B37("POISON_RESIST", "中毒抗性", "毒抗", 37, true), B38("COST_BLOCK", "COST锁定", "COST↓", 38, false),
        B39("ENCHANT", "追加伤害", "追伤", 39, true), B40("ATK_UP_BY_MAX_HP", "最大HP上升", "HP↑", 39, true);
        private final String id;
        private final String name;
        private final String nameS;
        private final Integer index;
        private final Boolean isBuff;
        private final static HashMap<String, String> idToName = new HashMap<>();
        private final static HashMap<String, String> idToNameS = new HashMap<>();
        private final static HashMap<String, Integer> idToIndex = new HashMap<>();
        private final static HashMap<Integer, String> indexToName = new HashMap<>();
        private final static HashMap<Integer, String> indexToId = new HashMap<>();
        private final static String[] DOT_NAMES = new String[]{"BURN", "FREEZE", "BLEED", "ELECTRIC", "POISON"};

        static {
            for (EnumBuff eA : EnumSet.allOf(EnumBuff.class)) {
                idToName.put(eA.getId(), eA.getName());
                idToNameS.put(eA.getId(), eA.getNameS());
                idToIndex.put(eA.getId(), eA.getIndex());
                indexToName.put(eA.getIndex(), eA.getName());
                indexToId.put(eA.getIndex(), eA.getId());
            }
        }

        private EnumBuff(String id, String name, String nameS, Integer index, Boolean isBuff) {
            this.id = id;
            this.name = name;
            this.nameS = nameS;
            this.index = index;
            this.isBuff = isBuff;
        }

        public static String[] getDotNames() {
            return DOT_NAMES;
        }

        public static String getNameById(String id) {
            return idToName.get(id);
        }

        public static String getNameSById(String id) {
            return idToNameS.get(id);
        }

        public static Integer getIndexById(String id) {
            return idToIndex.get(id);
        }

        public static String getNameByIndex(Integer index) {
            return indexToName.get(index);
        }

        public static String getIdByIndex(Integer index) {
            return indexToId.get(index);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNameS() {
            return nameS;
        }

        public Integer getIndex() {
            return index;
        }

        public Boolean getIsBuff() {
            return isBuff;
        }

    }

    public enum EnumLevel {

        L1("初级", "1"), L2("中级", "2"), L3("上级", "3"), L4("特级", "4"), L5("超级", "5"), L6("超弩级", "6"), L7("挑战级", "7");
        private final String name;
        private final String index;

        private EnumLevel(String name, String index) {
            this.name = name;
            this.index = index;
        }

        public static String getName(String index) {
            for (EnumLevel c : EnumLevel.values()) {
                if (c.getIndex().equals(index)) {
                    return c.name;
                }
            }
            return null;
        }

        public static String getIndex(String name) {
            for (EnumLevel c : EnumLevel.values()) {
                if (c.getName().equals(name)) {
                    return c.index;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public String getIndex() {
            return index;
        }
    }

    public enum EnumCardLevel {

        CL1("UR", 50), CL2("MR", 60), CL3("SPHRER", 1);
        private final String id;
        private final Integer level;

        private EnumCardLevel(String id, int level) {
            this.id = id;
            this.level = level;
        }

        public String getId() {
            return id;
        }

        public static Integer getLevel(String id) {
            for (EnumCardLevel c : EnumCardLevel.values()) {
                if (c.getId().equals(id)) {
                    return c.level;
                }
            }
            return -1;
        }
    }

    public enum EnumAttribute {

        HP("HP", "HP", 0), ATK("ATK", "物理攻击", 1), INT("INT", "魔法攻击", 2), MND("MND", "治疗量", 3),
        DEF("DEF", "物理防御", 4), MDEF("MDEF", "魔法防御", 5), DDUC("DDUC", "伤害减免", 6), MAX_HP("MAX_HP", "最大HP", 7),
        CRIT("CRIT", "暴击率", 8);

        private final String id;
        private final String name;
        private final int index;
        private final static HashMap<String, Integer> idToIndex = new HashMap<>();
        private final static HashMap<String, String> idToName = new HashMap<>();
        private final static HashMap<Integer, String> indexToName = new HashMap<>();

        private EnumAttribute(String id, String name, int index) {
            this.id = id;
            this.name = name;
            this.index = index;

        }

        static {
            for (EnumAttribute eA : EnumSet.allOf(EnumAttribute.class)) {
                idToIndex.put(eA.getId(), eA.getIndex());
                idToName.put(eA.getId(), eA.getName());
                indexToName.put(eA.getIndex(), eA.getName());
            }
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

        public static Integer getIndexById(String id) {
            return idToIndex.get(id);
        }

        public static String getNameById(String id) {
            return idToName.get(id);
        }

        public static String getNameByIndex(Integer index) {
            return indexToName.get(index);
        }
    }

    public enum EnumType {

        T0("无", "NONE", -1, "NONE", "无", new Color(230, 230, 230)), T1("火", "FIRE", 0, "BURN", "燃烧", new Color(255, 120, 120)),
        T2("冰", "ICE", 1, "FREEZE", "冰冻", new Color(140, 205, 255)), T3("风", "WIND", 2, "BLEED", "裂风", new Color(140, 255, 140)),
        T4("光", "LIGHT", 3, "ELECTRIC", "感电", new Color(240, 210, 150)), T5("暗", "DARK", 4, "POISON", "毒", new Color(200, 140, 180));
        private final String name;
        private final String id;
        private final Integer index;
        private final String dotId;
        private final String dotName;
        private final Color bgColor;

        private EnumType(String name, String id, int index, String dotId, String dotName, Color bgColor) {
            this.name = name;
            this.id = id;
            this.index = index;
            this.dotId = dotId;
            this.dotName = dotName;
            this.bgColor = bgColor;
        }

        public static Integer getNormalRate(String atkId, String defId) {
            if (defId.equals("NONE")) {
                return 100;
            }
            switch (atkId) {
                case "FIRE":
                    if (defId.equals("WIND")) {
                        return 200;
                    } else if (defId.equals("ICE")) {
                        return 50;
                    } else {
                        return 100;
                    }
                case "ICE":
                    if (defId.equals("FIRE")) {
                        return 200;
                    } else if (defId.equals("WIND")) {
                        return 50;
                    } else {
                        return 100;
                    }
                case "WIND":
                    if (defId.equals("ICE")) {
                        return 200;
                    } else if (defId.equals("FIRE")) {
                        return 50;
                    } else {
                        return 100;
                    }
                case "LIGHT":
                    if (defId.equals("DARK")) {
                        return 200;
                    } else {
                        return 100;
                    }
                case "DARK":
                    if (defId.equals("LIGHT")) {
                        return 200;
                    } else {
                        return 100;
                    }
            }

            return -1;
        }

        public static Integer getNormalRateByName(String atkName, String defId) {
            return getNormalRate(getIdByName(atkName), defId);
        }

        public static String getDotNameByIndex(Integer index) {
            for (EnumType c : EnumType.values()) {
                if (c.index.equals(index)) {
                    return c.dotName;
                }
            }
            return null;
        }

        public static String getNameByIndex(Integer index) {
            for (EnumType c : EnumType.values()) {
                if (c.index.equals(index)) {
                    return c.name;
                }
            }
            return null;
        }

        public static String getNameById(String id) {
            for (EnumType c : EnumType.values()) {
                if (c.getId().equals(id)) {
                    return c.name;
                }
            }
            return null;
        }

        public static Color getColorById(String id) {
            for (EnumType c : EnumType.values()) {
                if (c.getId().equals(id)) {
                    return c.bgColor;
                }
            }
            return null;
        }

        public static String getIdByName(String name) {
            for (EnumType c : EnumType.values()) {
                if (c.getName().equals(name)) {
                    return c.id;
                }
            }
            return null;
        }

        public static String getIdByDotId(String dotId) {
            for (EnumType c : EnumType.values()) {
                if (c.dotId.equals(dotId)) {
                    return c.id;
                }
            }
            return null;
        }

        public static Integer getIndexById(String id) {
            for (EnumType c : EnumType.values()) {
                if (c.getId().equals(id)) {
                    return c.index;
                }
            }
            return -1;
        }

        public static String getIdByIndex(Integer index) {
            for (EnumType c : EnumType.values()) {
                if (c.index.equals(index)) {
                    return c.id;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public int getIndex() {
            return index;
        }

    }
}
