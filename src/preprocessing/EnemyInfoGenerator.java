/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocessing;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import model.EnemyInfo;
import model.EnemySkill;
import model.Enums.EnumType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import simulator.BattlePrepare;
import util.UIUtil;

/**
 *
 * @author Shadow
 */
public class EnemyInfoGenerator {

    private static XSSFCellStyle headerStyle, contentStyle, moneyStyle, sumContent;
    private static List<EnemyInfo> enemyList;
    private static Integer LAST_COL_NUM = 10;
    private final static Integer ROW_HEIGHT = 15 * 72 * 20 / 96;
    private final static Integer[] COL_WIDTH
            = new Integer[]{pixel2PoiWidth(50), pixel2PoiWidth(200), pixel2PoiWidth(80), pixel2PoiWidth(80), pixel2PoiWidth(80),
                pixel2PoiWidth(80), pixel2PoiWidth(80), pixel2PoiWidth(80), pixel2PoiWidth(80), pixel2PoiWidth(225), pixel2PoiWidth(90),
                pixel2PoiWidth(60), pixel2PoiWidth(60), pixel2PoiWidth(72), pixel2PoiWidth(66), pixel2PoiWidth(60),
                pixel2PoiWidth(60), pixel2PoiWidth(72), pixel2PoiWidth(60), pixel2PoiWidth(60), pixel2PoiWidth(175),
                pixel2PoiWidth(72), pixel2PoiWidth(50), pixel2PoiWidth(50), pixel2PoiWidth(50), pixel2PoiWidth(50),
                pixel2PoiWidth(50), pixel2PoiWidth(50), pixel2PoiWidth(50), pixel2PoiWidth(50), pixel2PoiWidth(50),
                pixel2PoiWidth(50), pixel2PoiWidth(100), pixel2PoiWidth(100), pixel2PoiWidth(100), pixel2PoiWidth(100)
            };
    private final static double CHAR_WIDTH = 8;
    private final static double SPACING = 0;
    private final static Integer[] skillArrayIndex = new Integer[]{0, 1, 12, 18, 20, 21, 22, 26, 27};
    private final static String[] skillArrayTitle = new String[]{"技能ID", "技能名称", "物理/魔法", "目标", "发动条件", "参数1", "参数2", "优先度", "效果ID"};
    private final static Integer[] skillRoleArrayIndex = new Integer[]{0, 7, 8, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    private final static String[] skillRoleArrayTitle = new String[]{"效果ID", "效果类别", "效果目标", "参数1", "参数2", "参数3", "参数4", "参数5", "参数6", "参数7", "参数8", "参数9", "参数10", "倍率使用", "上限"};
    private final static Integer[] enemyAiOrderIndex = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 27, 28, 29, 30};
    private final static String[] enemyAiOrderTitle = new String[]{"AI条件ID", "Part", "HP下限", "HP上限", "本体HP下限", "本体HP上限", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "触发条件", "参数1", "参数2", "参数3"};
    private final static String[] roundName = new String[]{"1(3c)", "2(4c)", "3(5c)", "4(6c)", "5(7c)", "6(8c)", "7(9c)", "8(10c)", "9", "10", "双破以后"};

    public static void createEnemyInfoExcel(String level, List<EnemyInfo> enemyList, HashMap<String, List<String[]>> skillMap,
            HashMap<String, List<String[]>> skillRoleMap, HashMap<String, String[]> enemyAiOrderMap, HashMap<String, String[]> enemyLvUpMap) {
        try {
            File f = new File("./EnemyInfo_" + enemyList.get(0).getName() + " " + level + ".xlsx");
            FileOutputStream out;
            XSSFWorkbook book = new XSSFWorkbook();
            writeToSheet(book, enemyList, skillMap, skillRoleMap, enemyAiOrderMap, enemyLvUpMap);
            out = new FileOutputStream(f);
            book.write(out);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeToSheet(XSSFWorkbook book, List<EnemyInfo> enemyList, HashMap<String, List<String[]>> skillMap,
            HashMap<String, List<String[]>> skillRoleMap, HashMap<String, String[]> enemyAiOrderMap, HashMap<String, String[]> enemyLvUpMap) {
        // Write data into sheet.
        Sheet sheet = book.createSheet();
        CellRangeAddress cra;
        Row row;
        Cell cell;
        int rowNum, colNum;
        createCellStyles(book);

        for (int i = 0; i < COL_WIDTH.length; i++) {
            sheet.setColumnWidth(i, COL_WIDTH[i]);
        }

        row = getNotNullRow(sheet, 0);
        cell = getNotNullCell(row, 0);
        cell.setCellValue("概要");
        cell.setCellStyle(headerStyle);
        for (int j = 1; j < 11; j++) {
            cell = getNotNullCell(row, 0);
            cell.setCellStyle(headerStyle);
        }
        cra = new CellRangeAddress(0, 0, 0, LAST_COL_NUM);
        sheet.addMergedRegion(cra);

        colNum = 0;
        row = getNotNullRow(sheet, 1);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("基本属性");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("名称");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("属性");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("HP");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("ATK");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("INT");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("MND");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("DEF");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("MDF");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("属性有效率");
        cell.setCellStyle(headerStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("每轮行动力");
        cell.setCellStyle(headerStyle);

        rowNum = 2;
        for (int i = 0; i < enemyList.size(); i++) {
            colNum = 0;
            row = getNotNullRow(sheet, rowNum);
            cell = getNotNullCell(row, colNum++);
            cell.setCellStyle(contentStyle);
            cell = getNotNullCell(row, colNum++);
            cell.setCellValue(enemyList.get(i).getName());
            cell.setCellStyle(contentStyle);
            cell = getNotNullCell(row, colNum++);
            cell.setCellValue(EnumType.getNameById(enemyList.get(i).getType()));
            cell.setCellStyle(contentStyle);
            for (int j = 0; j < 6; j++) {
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(enemyList.get(i).getCurrentAttr(j));
                cell.setCellStyle(contentStyle);
            }
            String[] enemyPartLvUpArray = enemyLvUpMap.get(enemyList.get(i).getId());
            String findTypeRate = ((BattlePrepare) UIUtil.getBattlePrepare()).findTypeRate(enemyPartLvUpArray, enemyList.get(i).getType());
            cell = getNotNullCell(row, colNum++);
            cell.setCellValue(findTypeRate);
            cell.setCellStyle(contentStyle);
            cell = getNotNullCell(row, colNum++);
            cell.setCellValue(enemyList.get(i).getActionPoint());
            cell.setCellStyle(contentStyle);
            rowNum++;
        }
        cra = new CellRangeAddress(1, rowNum - 1, 0, 0);
        sheet.addMergedRegion(cra);

        row = getNotNullRow(sheet, rowNum++);
        cell = getNotNullCell(row, 0);
        cell.setCellValue("备注");
        cell.setCellStyle(headerStyle);
        cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 1, LAST_COL_NUM);
        sheet.addMergedRegion(cra);

        row = getNotNullRow(sheet, rowNum++);
        cell = getNotNullCell(row, 0);
        cell.setCellValue("行动规则表");
        cell.setCellStyle(headerStyle);
        cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 0, LAST_COL_NUM);
        sheet.addMergedRegion(cra);

        colNum = 11;
        row = getNotNullRow(sheet, rowNum);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("技能ID");
        cell.setCellStyle(contentStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("AI条件ID");
        cell.setCellStyle(contentStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("目标类别");
        cell.setCellStyle(contentStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("最大次数");
        cell.setCellStyle(contentStyle);
        cell = getNotNullCell(row, colNum++);
        cell.setCellValue("发动率");
        cell.setCellStyle(contentStyle);

        for (int p = 0; p < enemyAiOrderTitle.length; p++) {
            cell = getNotNullCell(row, colNum++);
            cell.setCellStyle(contentStyle);
            cell.setCellValue(enemyAiOrderTitle[p]);
        }

        for (int n = 0; n < enemyList.size(); n++) {
            Integer oriRowNum = rowNum;
            row = getNotNullRow(sheet, rowNum++);
            cell = getNotNullCell(row, 0);
            cell.setCellValue(enemyList.get(n).getName());
            cell.setCellStyle(headerStyle);
            cell = getNotNullCell(row, 1);
            cell.setCellValue("技能描述");
            cell.setCellStyle(headerStyle);
            cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 1, 4);
            sheet.addMergedRegion(cra);
            cell = getNotNullCell(row, 5);
            cell.setCellValue("触发条件");
            cell.setCellStyle(headerStyle);
            cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 5, 8);
            sheet.addMergedRegion(cra);
            cell = getNotNullCell(row, 9);
            cell.setCellValue("花费行动力");
            cell.setCellStyle(headerStyle);
            cell = getNotNullCell(row, 10);
            cell.setCellValue("优先度");
            cell.setCellStyle(headerStyle);

            if (!enemyList.get(n).getPassiveSkill().isEmpty()) {
                row = getNotNullRow(sheet, rowNum++);
                cell = getNotNullCell(row, 9);
                cell.setCellValue("被动技能");
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, 10);
                cell.setCellValue("-");
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, 11);
                cell.setCellValue(enemyList.get(n).getPassiveSkill());
                cell.setCellStyle(contentStyle);
            }
            for (int i = 0; i < enemyList.get(n).getSkills().size(); i++) {
                EnemySkill skill = enemyList.get(n).getSkills().get(i);
                row = getNotNullRow(sheet, rowNum++);
                cell = getNotNullCell(row, 1);
                cell.setCellValue("");
                cell.setCellStyle(contentStyle);
                cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 1, 4);
                sheet.addMergedRegion(cra);
                cell = getNotNullCell(row, 5);
                cell.setCellValue("");
                cell.setCellStyle(contentStyle);
                cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 5, 8);
                sheet.addMergedRegion(cra);
                cell = getNotNullCell(row, 9);
                cell.setCellValue(skill.getCost());
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, 10);
                cell.setCellValue(skill.getPriority());
                cell.setCellStyle(contentStyle);

                colNum = 11;
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(Long.parseLong(skill.getSkillId()));
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(Long.parseLong(skill.getAiOrderId()));
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(skill.getTarget());
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(skill.getMaxTimes());
                cell.setCellStyle(contentStyle);
                cell = getNotNullCell(row, colNum++);
                cell.setCellValue(skill.getSuccessRate());
                cell.setCellStyle(contentStyle);

                String[] aiOrder = enemyAiOrderMap.get(skill.getAiOrderId());
                for (int p = 0; p < enemyAiOrderIndex.length; p++) {
                    if (aiOrder.length > enemyAiOrderIndex[p]) {
                        cell = getNotNullCell(row, colNum++);
                        cell.setCellStyle(contentStyle);
                        if (aiOrder[enemyAiOrderIndex[p]].matches("[\\d]+")) {
                            cell.setCellValue(Integer.parseInt(aiOrder[enemyAiOrderIndex[p]]));
                        } else {
                            cell.setCellValue(aiOrder[enemyAiOrderIndex[p]]);
                        }
                    }
                }

            }
            cra = new CellRangeAddress(oriRowNum, rowNum - 1, 0, 0);
            sheet.addMergedRegion(cra);
        }
        Integer oriRowNum = rowNum;

        row = getNotNullRow(sheet, rowNum++);
        cell = getNotNullCell(row, 0);
        cell.setCellValue("分轮行动表");
        cell.setCellStyle(headerStyle);
        cra = new CellRangeAddress(rowNum - 1, rowNum - 1, 0, LAST_COL_NUM);
        sheet.addMergedRegion(cra);

        row = getNotNullRow(sheet, rowNum++);
        cell = getNotNullCell(row, 0);
        cell.setCellValue("轮数");
        cell.setCellStyle(headerStyle);

        for (int i = 0; i < 11; i++) {
            row = getNotNullRow(sheet, rowNum);
            cell = getNotNullCell(row, 0);
            cell.setCellStyle(headerStyle);
            if (roundName[i].matches("[\\d]+")) {
                cell.setCellValue(Integer.parseInt(roundName[i]));
            } else {
                cell.setCellValue(roundName[i]);
            }
            cra = new CellRangeAddress(rowNum, rowNum + 2, 0, 0);
            sheet.addMergedRegion(cra);
            rowNum += 3;
        }
        cra = new CellRangeAddress(rowNum - 3, rowNum - 1, 1, LAST_COL_NUM);
        sheet.addMergedRegion(cra);

        TreeSet<String> skillSet = new TreeSet<>();
        TreeSet<String> skillRoleSet = new TreeSet<>();
        for (int n = 0; n < enemyList.size(); n++) {
            if (!enemyList.get(n).getPassiveSkill().isEmpty()) {
                String skillId = enemyList.get(n).getPassiveSkill();
                skillSet.add(skillId);
            }
            for (int i = 0; i < enemyList.get(n).getSkills().size(); i++) {
                String skillId = enemyList.get(n).getSkills().get(i).getSkillId();
                skillSet.add(skillId);
            }
        }

        rowNum = oriRowNum;
        rowNum++;
        row = getNotNullRow(sheet, rowNum++);
        colNum = 11;
        for (int p = 0; p < skillArrayTitle.length; p++) {
            cell = getNotNullCell(row, colNum++);
            cell.setCellStyle(contentStyle);
            cell.setCellValue(skillArrayTitle[p]);
        }
        colNum = 19;
        for (int p = 0; p < skillRoleArrayTitle.length; p++) {
            cell = getNotNullCell(row, colNum++);
            cell.setCellStyle(contentStyle);
            cell.setCellValue(skillRoleArrayTitle[p]);
        }
        String skillId;
        while ((skillId = skillSet.pollFirst()) != null) {
            List<String[]> skills = skillMap.get(skillId);
            for (String[] skill : skills) {
                row = getNotNullRow(sheet, rowNum);
                skillRoleSet.add(skill[27]);
                List<String[]> skillRoles = skillRoleMap.get(skill[27]);
                colNum = 11;
                for (int p = 0; p < skillArrayIndex.length; p++) {
                    if (skill.length > skillArrayIndex[p]) {
                        cell = getNotNullCell(row, colNum++);
                        cell.setCellStyle(contentStyle);
                        if (skill[skillArrayIndex[p]].matches("[\\d]+")) {
                            cell.setCellValue(Integer.parseInt(skill[skillArrayIndex[p]]));
                        } else {
                            cell.setCellValue(skill[skillArrayIndex[p]]);
                        }
                    }
                }
                for (String[] skillRole : skillRoles) {
                    row = getNotNullRow(sheet, rowNum);
                    colNum = 19;
                    for (int p = 0; p < skillRoleArrayIndex.length; p++) {
                        if (skillRole.length > skillRoleArrayIndex[p]) {
                            cell = getNotNullCell(row, colNum++);
                            cell.setCellStyle(contentStyle);
                            if (skillRole[skillRoleArrayIndex[p]].matches("[\\d]+")) {
                                cell.setCellValue(Integer.parseInt(skillRole[skillRoleArrayIndex[p]]));
                            } else {
                                cell.setCellValue(skillRole[skillRoleArrayIndex[p]]);
                            }
                        }
                    }
                    rowNum++;
                }
            }
        }

        rowNum++;
//        row = getNotNullRow(sheet, rowNum++);
//        colNum = 11;
//        for (int p = 0; p < skillRoleArrayTitle.length; p++) {
//            cell = getNotNullCell(row, colNum++);
//            cell.setCellStyle(contentStyle);
//            cell.setCellValue(skillRoleArrayTitle[p]);
//        }
//        String skillRoleId;
//        while ((skillRoleId = skillRoleSet.pollFirst()) != null) {
//            List<String[]> skillRoles = skillRoleMap.get(skillRoleId);
//            for (String[] skillRole : skillRoles) {
//                row = getNotNullRow(sheet, rowNum);
//                colNum = 11;
//                for (int p = 0; p < skillRoleArrayIndex.length; p++) {
//                    if (skillRole.length > skillRoleArrayIndex[p]) {
//                        cell = getNotNullCell(row, colNum++);
//                        cell.setCellStyle(contentStyle);
//                        if (skillRole[skillRoleArrayIndex[p]].matches("[\\d]+")) {
//                            cell.setCellValue(Integer.parseInt(skillRole[skillRoleArrayIndex[p]]));
//                        } else {
//                            cell.setCellValue(skillRole[skillRoleArrayIndex[p]]);
//                        }
//                    }
//                }
//                rowNum++;
//            }
//        }

        for (int i = 0; i < rowNum + 30; i++) {
            getNotNullRow(sheet, i).setHeight(ROW_HEIGHT.shortValue());
        }

    }

    private static Cell getNotNullCell(Row row, int colNum) {
        if (row.getCell(colNum) == null) {
            return row.createCell(colNum);
        }
        return row.getCell(colNum);
    }

    private static Row getNotNullRow(Sheet sheet, int i) {
        if (sheet.getRow(i) == null) {
            Row row = sheet.createRow(i);
            return row;
        } else {
            return sheet.getRow(i);
        }
    }

    private static void createCellStyles(XSSFWorkbook book) {
        XSSFDataFormat format = book.createDataFormat();
        XSSFFont font = book.createFont();
        font.setFontHeightInPoints((short) 9);
        font.setFontName("宋体");
        XSSFFont headerFont = book.createFont();
        headerFont.setFontHeightInPoints((short) 9);
        headerFont.setBold(true);
        headerFont.setColor(new XSSFColor(new Color(0, 176, 80)));
        headerFont.setFontName("宋体");

        headerStyle = book.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
//        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        headerStyle.setBorderTop(CellStyle.BORDER_THIN);
//        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setFont(headerFont);
        headerStyle.setWrapText(true);

        contentStyle = book.createCellStyle();
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        contentStyle.setBorderRight(CellStyle.BORDER_THIN);
//        contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        contentStyle.setBorderTop(CellStyle.BORDER_THIN);
//        contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
        contentStyle.setFont(font);

        moneyStyle = book.createCellStyle();
        moneyStyle.setAlignment(HorizontalAlignment.CENTER);
        moneyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        moneyStyle.setBorderRight(CellStyle.BORDER_THIN);
        moneyStyle.setBorderLeft(CellStyle.BORDER_THIN);
        moneyStyle.setBorderTop(CellStyle.BORDER_THIN);
        moneyStyle.setBorderBottom(CellStyle.BORDER_THIN);
        moneyStyle.setDataFormat(format.getFormat("$#,##0.00_);[Red]($#,##0.00)"));
        moneyStyle.setFont(font);

    }

    public static int pixel2PoiWidth(int pixel) {
        double numChars = pixel2Character(pixel);
        numChars *= CHAR_WIDTH;
        numChars += SPACING;
        numChars /= CHAR_WIDTH;
        numChars *= 256;
        return (int) numChars;
    }

    public static double poiWidth2Character(int poiWidth) {
        double numChars = poiWidth / 256.0 - (SPACING * 1.0 / CHAR_WIDTH);
        //2位小数  
        return new BigDecimal(numChars).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double pixel2Character(int pixel) {
        double numChars = (pixel - SPACING) * 1.0 / CHAR_WIDTH;
        return new BigDecimal(numChars).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(pixel2PoiWidth(72));
    }
}
