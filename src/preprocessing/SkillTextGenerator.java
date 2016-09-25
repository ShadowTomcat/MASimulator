/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.FileUtils;
import static model.Constants.*;
import model.Enums;
import model.Enums.EnumAttribute;
import model.Enums.EnumPhysicsMagic;
import model.Enums.EnumType;

/**
 *
 * @author Shadow
 */
public class SkillTextGenerator {

    static HashMap<String, List<String[]>> skillMap;
    static HashMap<String, List<String[]>> skillRoleMap;
    static List<String[]> cardList;

    static HashMap<String, String> classMap;
    static HashMap<String, String> typeMap;
    static HashMap<String, String> categoryMap;
    static HashMap<String, String> functionMap;
    static HashMap<String, String> abbrFunctionMap;
    static HashMap<String, Integer> functionOrderMap;
    static HashMap<String, String> attackTypeMap;
    static HashMap<String, String> targetMap;
    static HashMap<String, String> propertyMap;
    static HashMap<String, String> abbrPropertyMap;
    static List<String[]> sphereList;

    static Comparator cmp = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            if (t == null && t1 == null) {
                return 0;
            } else if (t == null) {
                return 1;
            } else if (t1 == null) {
                return -1;
            } else {
                return ((String[]) t)[SKILL_ROLE_FUNCTION_COL].compareTo(((String[]) t1)[SKILL_ROLE_FUNCTION_COL]);
            }
        }
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        createMappings();
        loadData();
        loadExcel();
    }

    private static void loadData() {
        skillMap = FileUtils.loadListMap("skill_player.csv", null);
        skillRoleMap = FileUtils.loadListMap("skill_role_player.csv", null);
        cardList = FileUtils.loadList("card.csv");
        sphereList = FileUtils.loadList("sphr.csv");
    }

    private static void loadExcel() {
        try {
            File f = new File(".\\organized.xlsx");
            File fnew = new File(".\\organized_new.xlsx");
            FileOutputStream out;
            try (FileInputStream is = new FileInputStream(f)) {
                XSSFWorkbook book = new XSSFWorkbook(is);
                writeToSheet(book);
                createCSV(book);
                createSphereCSV();
                out = new FileOutputStream(fnew);
                book.write(out);
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeToSheet(Workbook book) {
        Sheet sheet = book.getSheetAt(0);
        Row row, styleRow;
        Cell cell, styleCell;
        styleRow = sheet.getRow(1);
        styleCell = styleRow.getCell(0);

        StringBuilder sb;
        String skillId, cellValue, rareLevel = "";
        String[] skillArray, skillArrayBase;
        List<String[]> skillRoleArray;
        int rowCount = 0, colCount;
        for (int n = 0; n < cardList.size(); n++) {
            String[] card = cardList.get(n);
            if (!card[0].matches("[\\d]+[\\d]+")
                    || !(card[7].equals("ULTRARARE") || card[7].equals("MILLIONRARE"))) {
                continue;
            }
            if (cardList.size() > n + 1 && card[5].equals(cardList.get(n + 1)[5])
                    && card[7].equals("ULTRARARE") && cardList.get(n + 1)[7].equals("MILLIONRARE")) {
                continue;
            }
            rowCount++;
            colCount = 0;
            row = getNotNullRow(sheet, rowCount);

            // Id, Same Card Id, Normal Skill Id, Bonus Skill Id, Title, Name
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[0]);

            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[SAME_CARD_ID]);

            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[CARD_NORMAL_SKILL]);

            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[CARD_BOOST_SKILL]);

            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[4]);

            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[5]);

            //Rarity, Class, Type, Cost, HP, ATK, INT, MND, Category, Functions
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            if (card[7].equals("ULTRARARE")) {
                rareLevel = "UR";
            } else if (card[7].equals("MILLIONRARE")) {
                rareLevel = "MR";
            }
            cell.setCellValue(rareLevel);

            if (!card[CARD_BOOST_SKILL].isEmpty() && !card[CARD_BOOST_SKILL].equals("0")) {
                skillId = card[CARD_BOOST_SKILL];
            } else {
                skillId = card[CARD_NORMAL_SKILL];
            }

            skillArrayBase = skillMap.get(skillId).get(0);
            skillArray = skillMap.get(skillId).get(skillMap.get(skillId).size() - 1);
            skillRoleArray = skillRoleMap.get(skillArray[SKILL_SKILLROLE_COL]);
            Collections.sort(skillRoleArray, cmp);
            // Class
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(classMap.get(skillArrayBase[11]));
            // Type
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cellValue = getType(skillArrayBase[10]);
            cell.setCellValue(cellValue);
            // Cost
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            if (skillArrayBase[13].matches("[\\d]+")) {
                cell.setCellValue(Integer.parseInt(skillArrayBase[13]));
            } else {
                cell.setCellValue(0);
            }
            // HP
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(Integer.parseInt(card[11]));
            // ATK
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(Integer.parseInt(card[14]));
            // INT
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(Integer.parseInt(card[17]));
            // MND
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(Integer.parseInt(card[20]));
            // Category
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(categoryMap.get(skillArrayBase[9]));
            // Functions
            sb = new StringBuilder();
            for (int j = 0; j < skillRoleArray.size(); j++) {
                sb.append(getFunctionType(skillRoleArray, j)).append("/");
                //sb.append(functionMap.get(skillRoleArray.get(j)[6])).append("/");
            }
            sb.delete(sb.length() - 1, sb.length());
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(sb.toString());
            //System.out.print(colCount);
            // Normal Skill
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.getCellStyle().setAlignment(CellStyle.ALIGN_LEFT);
            cell.setCellValue(getFunctionText(card[CARD_NORMAL_SKILL], rareLevel));
            //System.out.print(";" + colCount);
            // Bonus skill
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.getCellStyle().setAlignment(CellStyle.ALIGN_LEFT);
            cell.setCellValue(sb.toString());
            //System.out.print(";" + colCount);
            // System.out.println(";" + (colCount - 1));
            if (!card[25].isEmpty() && !card[CARD_BOOST_SKILL].equals("0")) {
                cell.setCellValue(getFunctionText(card[CARD_BOOST_SKILL], rareLevel));
            } else {
                cell.setCellValue(row.getCell(colCount - 2).getStringCellValue());
            }

            // Attack Dialogue
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            if (card.length > 84) {
                cell.setCellValue(card[84]);
            } else {
                cell.setCellValue("");
            }
            // Support Dialogue
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            if (card.length > 85) {
                cell.setCellValue(card[85]);
            } else {
                cell.setCellValue("");
            }
            // Image ID
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.setCellValue(card[33]);

            // Description
            cell = getNotNullCell(row, colCount++);
            cell.setCellStyle(styleCell.getCellStyle());
            cell.getCellStyle().setAlignment(CellStyle.ALIGN_LEFT);
            cell.setCellValue(skillArrayBase[3].replaceAll("<br>", "，").replaceAll("，\\{", "\\{"));
        }

    }

    private static String getFunctionText(String skillId, String rareLevel) {
        String boostCondition, triggerCondition;
        String[] skillArray, skillArrayBase;
        List<String[]> skillRoleArray, skillRoleArrayBase;

        //System.out.println(skillId);
        if (!skillMap.containsKey(skillId)) {
            return "";
        }
        skillArrayBase = skillMap.get(skillId).get(0);
        skillArray = skillMap.get(skillId).get(skillMap.get(skillId).size() - 1);
        skillRoleArrayBase = skillRoleMap.get(skillArrayBase[SKILL_SKILLROLE_COL]);
        skillRoleArray = skillRoleMap.get(skillArray[SKILL_SKILLROLE_COL]);
        Collections.sort(skillRoleArrayBase, cmp);
        Collections.sort(skillRoleArray, cmp);

        // Boost Condition
        boostCondition = getSkillBoostCondition(skillArray, false);
        triggerCondition = getSkillBoostCondition(skillArray, true);
        StringBuilder sb, sb_diffTarget, sbBoost, sbBoost_diffTarget;
        TreeMap<Integer, String> normalFunctions, boostFunctions;
        // Detail functions and target.
        normalFunctions = new TreeMap<>();
        normalFunctions.put(0, targetMap.get(skillArrayBase[SKILL_TARGET]));
        for (int j = 0; j < skillRoleArrayBase.size(); j++) {
            System.out.println(skillRoleArrayBase.get(j)[SKILL_ROLE_FUNCTION_COL]);
            int key = functionOrderMap.get(skillRoleArrayBase.get(j)[SKILL_ROLE_FUNCTION_COL]);
            while (normalFunctions.containsKey(key)) {
                key++;
            }
            normalFunctions.put(key, getFunctionValue(skillArrayBase, skillRoleArrayBase, j, rareLevel));
        }
        if (skillArrayBase[SKILL_CHAIN_BOOST].equals("30")) {
            normalFunctions.put(400, "提升chain威力");
        }

        boostFunctions = new TreeMap<>();
        boostFunctions.put(0, targetMap.get(skillArray[SKILL_TARGET]));
        for (int j = 0; j < skillRoleArray.size(); j++) {
            int key = functionOrderMap.get(skillRoleArray.get(j)[SKILL_ROLE_FUNCTION_COL]);
            while (boostFunctions.containsKey(key)) {
                key++;
            }
            boostFunctions.put(key, getFunctionValue(skillArray, skillRoleArray, j, rareLevel));
        }
        if (skillArray[SKILL_CHAIN_BOOST].equals("30")) {
            boostFunctions.put(400, "提升chain威力");
        }

        sb = new StringBuilder();
        sb_diffTarget = new StringBuilder();
        sbBoost = new StringBuilder();
        sbBoost_diffTarget = new StringBuilder();
        // Merge function lists into string.
        String normalStr, boostStr;
        // Trigger
        if (triggerCondition != null && !triggerCondition.isEmpty()) {
            sb.append(triggerCondition);
        }
        while (normalFunctions.size() > 0) {
            Map.Entry<Integer, String> pollFirstEntry = normalFunctions.pollFirstEntry();
            normalStr = pollFirstEntry.getValue();
            if (boostFunctions.containsKey(pollFirstEntry.getKey())) {
                boostStr = boostFunctions.get(pollFirstEntry.getKey());
                boostFunctions.remove(pollFirstEntry.getKey());
            } else {
                boostStr = "";
            }
            if (pollFirstEntry.getKey() == 0) {
                sb.append(normalStr).append("/");
            } else if (normalStr.startsWith("[TC]")) {
                sb_diffTarget.append(normalStr.substring(4)).append("，");
            } else {
                sb.append(normalStr).append("，");
            }
            if (boostStr != null && !boostStr.isEmpty() && !boostStr.equals(normalStr)) {
                if (pollFirstEntry.getKey() == 0) {
                    sbBoost.append(boostStr).append("/");
                } else if (boostStr.startsWith("[TC]" + sbBoost.toString())) {
                    sbBoost = new StringBuilder();
                    sbBoost.append(boostStr.substring(4)).append("，");
                } else if (boostStr.startsWith("[TC]")) {
                    sbBoost_diffTarget.append(boostStr.substring(4)).append("，");
                } else {
                    sbBoost.append(boostStr).append("，");
                }
            }
        }
        while (boostFunctions.size() > 0) {
            Map.Entry<Integer, String> pollFirstEntry = boostFunctions.pollFirstEntry();
            boostStr = pollFirstEntry.getValue();
            if (boostStr.startsWith("[TC]")) {
                sbBoost_diffTarget.append(boostStr.substring(4)).append("，");
            } else {
                sbBoost.append(boostStr).append("，");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb_diffTarget.length() > 0) {
            sb_diffTarget.deleteCharAt(sb_diffTarget.length() - 1);
            sb.append("，").append(sb_diffTarget);
        }

        if (boostCondition != null && !boostCondition.isEmpty()) {
            sb.append("，").append(boostCondition);
        }
        if (sbBoost.length() > 0) {
            sbBoost.deleteCharAt(sbBoost.length() - 1);
            sb.append(sbBoost);
        }
        if (sbBoost_diffTarget.length() > 0) {
            sbBoost_diffTarget.deleteCharAt(sbBoost_diffTarget.length() - 1);
            if (sbBoost.length() > 0) {
                sb.append("，");
            }
            sb.append(sbBoost_diffTarget);
        }
        return sb.toString();
    }

    private static String getType(String oriTypeText) {
        if (oriTypeText.contains("_")) {
            return typeMap.get(oriTypeText.substring(0, oriTypeText.indexOf("_"))) + typeMap.get(oriTypeText.substring(oriTypeText.indexOf("_") + 1));
        } else {
            return typeMap.get(oriTypeText);
        }
    }

    private static String getNotNullStringCellValue(Cell cell) {
        DecimalFormat format = new DecimalFormat("#");
        if (cell == null) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return format.format(cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }

    private static Row getNotNullRow(Sheet sheet, int i) {
        if (sheet.getRow(i) == null) {
            return sheet.createRow(i);
        } else {
            return sheet.getRow(i);
        }
    }

    private static Cell getNotNullCell(Row row, int i) {
        if (row.getCell(i) == null) {
            return row.createCell(i);
        } else {
            return row.getCell(i);
        }
    }

    private static void createMappings() {
        classMap = new HashMap<>();
        classMap.put("MERCENARY", "佣兵");
        classMap.put("MILLIONAIRE", "富豪");
        classMap.put("THIEF", "盗贼");
        classMap.put("SINGER", "歌姬");
        classMap.put("NULL", "通用");

        typeMap = new HashMap<>();
        typeMap.put("DARK", "暗");
        typeMap.put("LIGHT", "光");
        typeMap.put("FIRE", "火");
        typeMap.put("WIND", "风");
        typeMap.put("ICE", "冰");

        categoryMap = new HashMap<>();
        categoryMap.put("ATTACK", "物理");
        categoryMap.put("SORCERY", "魔法");
        categoryMap.put("SUPPORT", "支援");
        categoryMap.put("JAMMING", "弱化");
        categoryMap.put("RECOVERY", "治疗");
        categoryMap.put("DEFENSE", "防御");

        functionMap = new HashMap<>();
        functionMap.put("ATTACK_AA", "攻击");
        functionMap.put("ATK_OP_DRAIN", "吸血");
        functionMap.put("ATK_OP_PIERCING", "破防");
        functionMap.put("ATK_UP_FIXED", "攻击提升");
        functionMap.put("ATK_OP_REVENGE", "反伤");
        functionMap.put("DEAL_BONUS", "抽卡");
        functionMap.put("DEF_UP_FIXED", "防御提升");
        functionMap.put("ATTR_DEF_UP", "属性抗性提升");
        functionMap.put("ATTR_DEF_DOWN", "属性抗性降低");
        functionMap.put("DEBUFF_RELEASE_ONE", "解除异常");
        functionMap.put("GUARD_BREAK_FIXED", "防御弱化");
        functionMap.put("ATK_BREAK_FIXED", "攻击弱化");
        functionMap.put("ATK_BREAK_BY_SELF_PARAM", "自身数值弱攻");
        functionMap.put("ATK_OP_DAMAGE_INCREASE", "自身数值加伤");
        functionMap.put("FREEZE", "冻结");
        functionMap.put("BLEED", "裂风");
        functionMap.put("POISON", "中毒");
        functionMap.put("ELECTRIC", "感电");
        functionMap.put("BURN", "点燃");
        functionMap.put("WEAKNESS", "标记");
        functionMap.put("CRITICAL_UP", "暴击率提升");
        functionMap.put("CARD_SEAL_REGIST", "封印抗性");
        functionMap.put("DARKNESS_REGIST", "黑暗抗性");
        functionMap.put("COVERING", "嘲讽");
        functionMap.put("REFLECTION", "伤害反弹");
        functionMap.put("HEAL_FIXED", "治疗");
        functionMap.put("REGENERATE_FIXED", "延迟治疗");
        functionMap.put("ATK_UP_BY_SELF_PARAM", "自身数值加攻");
        functionMap.put("DEF_UP_BY_SELF_PARAM", "自身数值加防");
        functionMap.put("STAN", "打断行动");
        functionMap.put("ATK_OP_ATTR_RATE_DOWN_INVALID", "属性不利无效");
        functionMap.put("ATTR_SEE", "揭露隐藏属性");
        functionMap.put("HEAL_BY_SELF_PARAM", "自身数值治疗");
        functionMap.put("CARD_TRAP_DAMAGE", "陷阱");
        functionMap.put("HP_CUT", "比例扣血");
        functionMap.put("DARKNESS", "黑暗");
        functionMap.put("CARD_SEAL", "封印");
        functionMap.put("ENCHANT", "追加伤害");
        functionMap.put("DOT_VALUE_UP", "DOT增强");
        functionMap.put("COST_BLOCK", "COST封印");
        functionMap.put("HEAL", "HP恢复");  // Actually mixed function and buff.

        abbrFunctionMap = new HashMap<>();
        abbrFunctionMap.put("ATTACK_AA", "攻击");
        abbrFunctionMap.put("ATK_OP_DRAIN", "吸血");
        abbrFunctionMap.put("ATK_OP_PIERCING", "破防");
        abbrFunctionMap.put("ATK_UP_FIXED", "攻击提升");
        abbrFunctionMap.put("ATK_OP_REVENGE", "反伤");
        abbrFunctionMap.put("DEAL_BONUS", "抽卡");
        abbrFunctionMap.put("DEF_UP_FIXED", "防御提升");
        abbrFunctionMap.put("ATTR_DEF_UP", "属抗↑");
        abbrFunctionMap.put("ATTR_DEF_DOWN", "属抗↓");
        abbrFunctionMap.put("DEBUFF_RELEASE_ONE", "解异常");
        abbrFunctionMap.put("GUARD_BREAK_FIXED", "防御弱化");
        abbrFunctionMap.put("ATK_BREAK_FIXED", "攻击弱化");
        abbrFunctionMap.put("ATK_BREAK_BY_SELF_PARAM", "自身数值弱攻");
        abbrFunctionMap.put("ATK_OP_DAMAGE_INCREASE", "自身数值加伤");
        abbrFunctionMap.put("FREEZE", "冻结");
        abbrFunctionMap.put("BLEED", "裂风");
        abbrFunctionMap.put("POISON", "中毒");
        abbrFunctionMap.put("ELECTRIC", "感电");
        abbrFunctionMap.put("BURN", "点燃");
        abbrFunctionMap.put("WEAKNESS", "标记");
        abbrFunctionMap.put("CRITICAL_UP", "暴击↑");
        abbrFunctionMap.put("CARD_SEAL_REGIST", "抗封印");
        abbrFunctionMap.put("DARKNESS_REGIST", "抗黑暗");
        abbrFunctionMap.put("COVERING", "嘲讽");
        abbrFunctionMap.put("REFLECTION", "伤害反弹");
        abbrFunctionMap.put("HEAL_FIXED", "治疗");
        abbrFunctionMap.put("REGENERATE_FIXED", "延迟奶");
        abbrFunctionMap.put("ATK_UP_BY_SELF_PARAM", "自身数值加攻");
        abbrFunctionMap.put("DEF_UP_BY_SELF_PARAM", "自身数值加防");
        abbrFunctionMap.put("STAN", "打断");
        abbrFunctionMap.put("ATK_OP_ATTR_RATE_DOWN_INVALID", "属性不利无效");
        abbrFunctionMap.put("ATTR_SEE", "揭露隐藏属性");
        abbrFunctionMap.put("HEAL_BY_SELF_PARAM", "治疗");
        abbrFunctionMap.put("HP_CUT", "比例扣血");
        abbrFunctionMap.put("CARD_TRAP_DAMAGE", "陷阱");
        abbrFunctionMap.put("DARKNESS", "黑暗");
        abbrFunctionMap.put("CARD_SEAL", "封印");
        abbrFunctionMap.put("ENCHANT", "追伤");
        abbrFunctionMap.put("DOT_VALUE_UP", "DOT↑");
        abbrFunctionMap.put("COST_BLOCK", "COST封印");

        int i = 1;
        functionOrderMap = new HashMap<>();
        functionOrderMap.put("ATTACK_AA", i += 10);
        functionOrderMap.put("ATK_OP_DRAIN", i += 10);
        functionOrderMap.put("ATK_OP_PIERCING", i += 10);
        functionOrderMap.put("ATK_UP_FIXED", i += 10);
        functionOrderMap.put("DEF_UP_FIXED", i += 10);
        functionOrderMap.put("ATTR_DEF_UP", i += 10);
        functionOrderMap.put("ATTR_DEF_DOWN", i += 10);
        functionOrderMap.put("HEAL_FIXED", i += 10);
        functionOrderMap.put("GUARD_BREAK_FIXED", i += 10);
        functionOrderMap.put("ATK_BREAK_FIXED", i += 10);
        functionOrderMap.put("ATK_BREAK_BY_SELF_PARAM", i += 10);
        functionOrderMap.put("REGENERATE_FIXED", i += 10);
        functionOrderMap.put("REFLECTION", i += 10);
        functionOrderMap.put("ATK_UP_BY_SELF_PARAM", i += 10);
        functionOrderMap.put("DEF_UP_BY_SELF_PARAM", i += 10);
        functionOrderMap.put("HEAL_BY_SELF_PARAM", i += 10);
        functionOrderMap.put("FREEZE", i += 10);
        functionOrderMap.put("BLEED", i += 10);
        functionOrderMap.put("POISON", i += 10);
        functionOrderMap.put("ELECTRIC", i += 10);
        functionOrderMap.put("BURN", i += 10);
        functionOrderMap.put("ATK_OP_DAMAGE_INCREASE", i += 10);
        functionOrderMap.put("WEAKNESS", i += 10);
        functionOrderMap.put("CRITICAL_UP", i += 10);
        functionOrderMap.put("DEBUFF_RELEASE_ONE", i += 10);
        functionOrderMap.put("CARD_SEAL_REGIST", i += 10);
        functionOrderMap.put("DARKNESS_REGIST", i += 10);
        functionOrderMap.put("COVERING", i += 10);
        functionOrderMap.put("STAN", i += 10);
        functionOrderMap.put("ATK_OP_REVENGE", i += 10);
        functionOrderMap.put("ATK_OP_ATTR_RATE_DOWN_INVALID", i += 10);
        functionOrderMap.put("ATTR_SEE", i += 10);
        functionOrderMap.put("ENCHANT", i += 10);
        functionOrderMap.put("HP_CUT", i += 10);
        functionOrderMap.put("CARD_TRAP_DAMAGE", i += 10);
        functionOrderMap.put("DOT_VALUE_UP", i += 10);
        functionOrderMap.put("DEAL_BONUS", i += 10);
        functionOrderMap.put("COST_BLOCK", i += 10);

        attackTypeMap = new HashMap<>();
        attackTypeMap.put("PHYSICS", "物理");
        attackTypeMap.put("MAGIC", "魔法");

        targetMap = new HashMap<>();
        targetMap.put("ENEMY_ONE", "敌单体");
        targetMap.put("ENEMY_ALL", "敌全体");
        targetMap.put("USER_ONE", "己方1人");
        targetMap.put("USER_ALL", "己方全体");
        targetMap.put("FRIEND_ALL", "己方全体");
        targetMap.put("SELF", "自身");

        propertyMap = new HashMap<>();
        propertyMap.put("HP", "HP");
        propertyMap.put("MAX_HP", "最大HP");
        propertyMap.put("ATK", "物理攻击力");
        propertyMap.put("INT", "魔法攻击力");
        propertyMap.put("DEF", "物理防御");
        propertyMap.put("MDEF", "魔法防御");
        propertyMap.put("MND", "治疗量");

        abbrPropertyMap = new HashMap<>();
        abbrPropertyMap.put("HP", "HP");
        abbrPropertyMap.put("MAX_HP", "最大HP");
        abbrPropertyMap.put("ATK", "物攻");
        abbrPropertyMap.put("INT", "魔攻");
        abbrPropertyMap.put("DEF", "物防");
        abbrPropertyMap.put("MDEF", "魔防");
        abbrPropertyMap.put("MND", "治疗");
    }

    private static String getFunctionValue(String[] skillArray, List<String[]> skillRoleArray, int j, String rareLevel) {
        String[] skillRole = skillRoleArray.get(j);
        Long maxLevel;
        if (rareLevel.equals("UR")) {
            maxLevel = 50L;
        } else {
            maxLevel = 60L;
        }
        return getSingleFunctionString(skillArray, skillRole, maxLevel);
    }

    private static String getSingleFunctionString(String[] skill, String[] skillRole, Long maxLevel) {
        if (skillRole == null) {
            return null;
        }

        String function = skillRole[SKILL_ROLE_FUNCTION_COL];
        StringBuilder sb = new StringBuilder();
        Long val;
        if (!skillRole[SKILL_ROLE_TARGET].equals("SELECT")) {
            sb.append("[TC]").append(targetMap.get(skillRole[SKILL_ROLE_TARGET])).append("/");  // Target Changed
        }
        switch (function) {
            case "ATTACK_AA":
                sb.append(attackTypeMap.get(skillRole[SKILL_ROLE_PARAM9])).append("/");
                sb.append(Long.parseLong(skillRole[SKILL_ROLE_PARAM1]) + Long.parseLong(skillRole[SKILL_ROLE_PARAM2]) * maxLevel / 1000).append("点");
                if (!skillRole[SKILL_ROLE_PARAM3].equals("1000")) {
                    sb.append("+").append(attackTypeMap.get(skillRole[SKILL_ROLE_PARAM9])).append("攻击力").append(Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) / 10).append("%的");
                }
                sb.append(getType(skillRole[SKILL_ROLE_PARAM8])).append("属性伤害");
                if (!skillRole[SKILL_ROLE_PARAM5].equals("1")) {
                    sb.append("，").append(skillRole[SKILL_ROLE_PARAM5]).append("次攻击");
                }
                if (!skillRole[SKILL_ROLE_PARAM7].equals("15") && !skillRole[SKILL_ROLE_PARAM7].isEmpty()) {
                    sb.append("，暴击率").append(skillRole[SKILL_ROLE_PARAM7]).append("%");
                }
                break;
            case "ATK_OP_DRAIN":
                sb.append("HP恢复伤害量的").append(skillRole[SKILL_ROLE_PARAM1]).append("%");
                break;
            case "ATK_OP_PIERCING":
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM1]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM2]) * maxLevel;
                sb.append("无视").append(val.toString()).append("%的防御");
                break;
            case "ATK_UP_FIXED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/提升");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                val = (Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel) / 1000;
                sb.append(val);
                break;
            case "ATK_OP_REVENGE":
                sb.append("提升累计损血").append(skillRole[SKILL_ROLE_PARAM1]).append("%的威力");
                break;
            case "DEAL_BONUS":
                sb.append("抽卡+").append(skillRole[SKILL_ROLE_PARAM1]);
                break;
            case "DEF_UP_FIXED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/提升");
                val = (Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel) / 1000;
                sb.append(val).append("点");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                break;
            case "ATTR_DEF_UP":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/提升");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("点");
                sb.append(EnumType.getNameById(skillRole[SKILL_ROLE_PARAM6])).append("属性抗性");
                break;
            case "ATTR_DEF_DOWN":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/降低");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("点");
                sb.append(EnumType.getNameById(skillRole[SKILL_ROLE_PARAM6])).append("属性抗性");
                break;
            case "HEAL_FIXED":
                boolean hasBase = false;
                sb.append("恢复");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM1]) + (Integer.parseInt(skillRole[SKILL_ROLE_PARAM2]) * maxLevel) / 1000;
                if (val > 0) {
                    sb.append(val);
                    hasBase = true;
                }
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM3]) / 10;
                if (val != 100 || !skillRole[SKILL_ROLE_PARAM5].equals("MND")) {
                    if (hasBase) {
                        sb.append("+");
                    }
                    sb.append(propertyMap.get(skillRole[SKILL_ROLE_PARAM5])).append("的").append(val).append("%");
                }
                sb.append("点HP");
                break;
            case "REGENERATE_FIXED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合恢复HP");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM2]) + (Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) * maxLevel) / 1000;
                sb.append(val);
                if (!skillRole[SKILL_ROLE_PARAM4].equals("1000")) {
                    sb.append("+").append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM6])).append("的").append(Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) / 10).append("%");
                }
                break;
            case "DEBUFF_RELEASE_ONE":
                sb.append("解除").append(functionMap.get(skillRole[SKILL_ROLE_PARAM3]));
                if (!skillRole[SKILL_ROLE_PARAM4].equals("NULL")) {
                    sb.append("＆").append(functionMap.get(skillRole[SKILL_ROLE_PARAM4]));
                }
                sb.append("状态");
                break;
            case "GUARD_BREAK_FIXED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/降低");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) * (Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel) / 1000;
                sb.append(val).append("点");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                break;
            case "ATK_BREAK_FIXED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/降低");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) * (Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel) / 1000;
                sb.append(val).append("点");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                break;
            case "ATK_OP_DAMAGE_INCREASE":
                sb.append("提升当前");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM5]));
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM3]) / 10;
                sb.append(val.toString()).append("%的威力");
                break;
            case "FREEZE":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("+");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) / 10;
                sb.append(val).append("%");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM8]));
                sb.append("的冰冻伤害");
                break;
            case "BLEED":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("+");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) / 10;
                sb.append(val).append("%");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM8]));
                sb.append("的裂风伤害");
                break;
            case "POISON":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("+");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) / 10;
                sb.append(val).append("%");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM8]));
                sb.append("的中毒伤害");
                break;
            case "ELECTRIC":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("+");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) / 10;
                sb.append(val).append("%");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM8]));
                sb.append("的感电伤害");
                break;
            case "BURN":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/每回合");
                val = Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM4]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM5]) * maxLevel / 1000;
                sb.append(val).append("+");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) / 10;
                sb.append(val).append("%");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM8]));
                sb.append("的燃烧伤害");
//                System.out.println(Arrays.toString(skillRole));
//                System.out.println(sb.toString());
                break;
            case "CARD_TRAP_DAMAGE":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]);
                sb.append(val);
                if (Long.parseLong(skillRole[SKILL_ROLE_PARAM3]) > val) {
                    sb.append("～").append(Long.parseLong(skillRole[SKILL_ROLE_PARAM3]));
                }
                sb.append("枚变为陷阱状态(").append(Long.parseLong(skillRole[SKILL_ROLE_PARAM4])).append(")");
                break;
            case "WEAKNESS":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/标记，伤害上升");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]) / 10;
                sb.append(val).append("%");
                break;
            case "CRITICAL_UP":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/暴击率提升");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]);
                sb.append(val).append("%");
                break;
            case "CARD_SEAL_REGIST":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]);
                sb.append(val).append("%封印抗性");
                break;
            case "DARKNESS_REGIST":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]);
                sb.append(val).append("%黑暗抗性");
                break;
            case "COVERING":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/使攻击向自身集中，减免");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]) / 10;
                sb.append(val).append("%伤害");
                break;
            case "REFLECTION":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/反射所受");
                sb.append(EnumPhysicsMagic.getNameById(skillRole[SKILL_ROLE_PARAM4])).append("伤害的");
                val = (Integer.parseInt(skillRole[SKILL_ROLE_PARAM2]) + Integer.parseInt(skillRole[SKILL_ROLE_PARAM3]) * maxLevel) / 100;
                sb.append(val).append("%");
                break;
            case "ATK_UP_BY_SELF_PARAM":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                sb.append("提升").append(Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) * maxLevel).append("+");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM3]));
                sb.append("的");
                sb.append(new BigDecimal(skillRole[SKILL_ROLE_PARAM4]).divide(BigDecimal.TEN)).append("%");
                break;
            case "ATK_BREAK_BY_SELF_PARAM":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                sb.append("下降").append(Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) * maxLevel).append("+");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM3]));
                sb.append("的");
                sb.append(new BigDecimal(skillRole[SKILL_ROLE_PARAM4]).divide(BigDecimal.TEN)).append("%");
                break;
            case "DEF_UP_BY_SELF_PARAM":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM2]));
                sb.append("提升").append(Long.parseLong(skillRole[SKILL_ROLE_PARAM6]) * maxLevel).append("+");
                sb.append(EnumAttribute.getNameById(skillRole[SKILL_ROLE_PARAM3]));
                sb.append("的");
                sb.append(new BigDecimal(skillRole[SKILL_ROLE_PARAM4]).divide(BigDecimal.TEN)).append("%");
//                val = Long.parseLong(skillRole[21]) / 10;
//                sb.append(val).append("%");
                break;
            case "HEAL_BY_SELF_PARAM":
                sb.append("恢复").append(propertyMap.get(skillRole[SKILL_ROLE_PARAM1]));
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]) / 10;
                sb.append("的").append(val).append("%");
                break;
            case "STAN":
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]);
                sb.append(val).append("%几率打断敌人行动");
                break;
            case "ATK_OP_ATTR_RATE_DOWN_INVALID":
                sb.append("因属性不利的伤害减少无效化");
                break;
            case "ATTR_SEE":
                sb.append("看破隐藏起来的属性");
                break;
            case "ENCHANT":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/赋予");
                val = Long.parseLong(skillRole[SKILL_ROLE_PARAM2]) + Long.parseLong(skillRole[SKILL_ROLE_PARAM5]) * maxLevel;
                sb.append(val).append(getType(skillRole[SKILL_ROLE_PARAM6])).append("追加伤害");
                break;
            case "HP_CUT":
                sb.append("HP扣减最大值的").append(skillRole[SKILL_ROLE_PARAM1]).append("%");
                break;
            case "DOT_VALUE_UP":
                sb.append(functionMap.get(skillRole[SKILL_ROLE_PARAM6])).append("的效果回合+").append(skillRole[SKILL_ROLE_PARAM1]).
                        append("，伤害+").append(skillRole[SKILL_ROLE_PARAM2]).append("%");
                break;
            case "COST_BLOCK":
                sb.append(skillRole[SKILL_ROLE_PARAM1]).append("回合/COST封印").append(skillRole[SKILL_ROLE_PARAM2]).append("点");
                break;
            default:
                System.out.println("Unrecognized function: " + function);
                break;
        }
        return sb.toString();
    }

    private static String getSkillBoostCondition(String[] skillArray, Boolean isTrigger) {
        Integer cond, param1, param2, param3, trigger, last;
        if (isTrigger) {
            trigger = SKILL_TRIGGER_TIME;
            last = SKILL_TRIGGER_LAST;
            cond = SKILL_TRIGGER_CONDITION;
            param1 = SKILL_TRIGGER_PARAM1;
            param2 = SKILL_TRIGGER_PARAM2;
            param3 = SKILL_TRIGGER_PARAM3;
        } else {
            trigger = -1;
            last = -1;
            cond = SKILL_BOOST_CONDITION;
            param1 = SKILL_BOOST_PARAM1;
            param2 = SKILL_BOOST_PARAM2;
            param3 = SKILL_BOOST_PARAM3;
        }
        String condition = skillArray[cond];
        if (condition.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("【");
        switch (condition) {
            case "SELF_OTHER_PLAY_SKILL_KIND":
                sb.append("自身/").append(categoryMap.get(skillArray[param1])).append("/额外").append(skillArray[param2]).append("枚以上");
                break;
            case "TURN":
                if (skillArray[param1].equals("0")) {
                    sb.append(skillArray[param2]).append("回合前");
                } else {
                    sb.append(skillArray[param1]).append("回合后");
                }
                break;
            case "SELF_HP_PER":
                sb.append("自身HP");
                if (skillArray[param1].equals("0")) {
                    sb.append(skillArray[param2]).append("%以下");
                } else {
                    sb.append(skillArray[param1]).append("%");
                    if (!skillArray[param1].equals("100")) {
                        sb.append("以上");
                    }
                }
                break;
            case "DECK_COMBO_COUNT":
                sb.append(skillArray[param1]).append("Chain以上");
                break;
            case "TARGET_DEBUFF":
                sb.append("目标").append(functionMap.get(skillArray[param1]));
                break;
            case "TARGET_ATTR":
                sb.append("目标为").append(typeMap.get(skillArray[param1])).append("属性");
                break;
            case "SELF_OTHER_PLAY_ATTR":
                sb.append("自身/").append(typeMap.get(skillArray[param1])).append("/额外").append(skillArray[param2]).append("枚以上");
                break;
            case "SELF_OTHER_PLAY_RARITY":
                sb.append("自身/额外").append(skillArray[param2]).append("枚以上");
                break;
            case "SELF_BUFF":
                sb.append("自身为").append(functionMap.get(skillArray[param1]));
                if (skillArray.length > param2 && !skillArray[param2].isEmpty()) {
                    sb.append("·").append(functionMap.get(skillArray[param2]));
                }
                sb.append("状态");
                break;
            case "ENEMY_SIDE_DEBUFF":
                sb.append("敌方为").append(functionMap.get(skillArray[param1]));
                if (skillArray.length > param2 && !skillArray[param2].isEmpty()) {
                    sb.append("·").append(functionMap.get(skillArray[param2]));
                }
                sb.append("状态");
                break;
            case "TARGET_BUFF":
                sb.append("目标为").append(functionMap.get(skillArray[param1]));
                if (skillArray.length > param2 && !skillArray[param2].isEmpty()) {
                    sb.append("·").append(functionMap.get(skillArray[param2]));
                }
                sb.append("状态");
                break;
            case "USER_SIDE_DEBUFF":
                sb.append("我方为").append(functionMap.get(skillArray[param1]));
                if (skillArray.length > param2 && !skillArray[param2].isEmpty()) {
                    sb.append("·").append(functionMap.get(skillArray[param2]));
                }
                sb.append("状态");
                break;
            default:
                System.out.println("Unrecognized condition: " + condition);
                break;
        }
        if (isTrigger) {
            switch (skillArray[trigger]) {
                case "ENEMY_ACTION_END":
                    sb.append("/敌方行动后发动/保持").append(skillArray[last]).append("回合");
                    break;
            }
        }
        sb.append("】");
        return sb.toString();
    }

    private static String getFunctionType(List<String[]> skillRoleArray, int index) {
        String str = abbrFunctionMap.get(skillRoleArray.get(index)[SKILL_ROLE_FUNCTION_COL]);
        if (str == null) {
            System.out.println("Unrecognized function: " + skillRoleArray.get(index)[SKILL_ROLE_FUNCTION_COL]);
            return "";
        }
        if (str.equals("攻击提升") || str.equals("防御提升") || str.equals("自身数值加攻") || str.equals("自身数值加防")) {
            str = abbrPropertyMap.get(skillRoleArray.get(index)[SKILL_ROLE_PARAM2]) + "↑";
        }
        if (str.equals("攻击弱化") || str.equals("防御弱化") || str.equals("自身数值弱攻") || str.equals("自身数值弱防")) {
            str = abbrPropertyMap.get(skillRoleArray.get(index)[SKILL_ROLE_PARAM2]) + "↓";
        }
        return str;
    }

    private static void createCSV(XSSFWorkbook book) {
        Sheet sheet = book.getSheetAt(0);
        Row row;
        Cell cell;
        List<String> csv = new ArrayList<>();
        StringBuilder sb;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            sb = new StringBuilder();
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            for (int j = 0; j < 21; j++) {
                sb.append(getStringCellValue(getNotNullCell(row, j))).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            csv.add(sb.toString());
        }
        try {
            File f = new File(".\\card_data.csv");
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            for (String str : csv) {
                br.write(str, 0, str.length());
                br.newLine();
            }
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getStringCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return new BigDecimal(cell.getNumericCellValue()).toString();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
            default:
                System.out.println("Uncontrolled type: " + cell.getCellType());
                return null;
        }
    }

    private static void createSphereCSV() {
        try {
            File f = new File(".\\sphere_data.csv");
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            String[] skill;
            List<String[]> skillRoles;
            StringBuilder sb;
            for (String[] array : sphereList) {
                sb = new StringBuilder();
                for (String str : array) {
                    sb.append(str).append(",");
                }

                skill = skillMap.get(array[0]).get(0);
                //System.out.println(array[0]);
                skillRoles = skillRoleMap.get(skill[SKILL_SKILLROLE_COL]);
                sb.append(skill[13]).append(",");   // Cost
                sb.append(targetMap.get(skill[18])).append("/");
                for (String[] skillRole : skillRoles) {
                    String description = getSingleFunctionString(skill, skillRole, 1L).replaceAll("\\[TC\\]", "");
                    sb.append(description).append("，");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(",");
                // Brief Description
                sb.append(classMap.get(skill[11])).append("/").append(getType(skill[10])).append("/")
                        .append(Integer.parseInt(skill[13])).append("C/").append(categoryMap.get(skill[9]));
                br.write(sb.toString(), 0, sb.length());
                br.newLine();
            }
            br.flush();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
