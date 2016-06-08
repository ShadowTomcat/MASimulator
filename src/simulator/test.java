/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.ArthurInfo;
import model.ArthurSkill;
import model.Constants;
import model.Enums.EnumAttribute;
import model.HandCardInfo;
import util.FileUtils;

/**
 *
 * @author Shadow
 */
public class test {

    public static void main(String[] args) {
//        String a = "1,2,3,,,,,,,,8";
//        String b = "1,2,3,,,,,,,,";
//        String[] a1 = a.split(",");
//        String[] b1 = b.split(",");
//        System.out.println(a1.length);
//        System.out.println(b1.length);
//        Integer a = 100;
//        Integer b = a;
//        a = 200;
//        System.out.println(a);
//        System.out.println(b);

//        Integer[] arr = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int a = 3;
//        int b = 4;
//        for (int i = 0; i < 100000; i++) {
//            //double d = Math.random() * 10 - 0.5;
//            //Long l = Math.round(d);
//            //Long l = Math.round(Math.random() * (b - a + 1) - 0.5) + a;
//            Long l = (Math.round(Math.random() * 2- 0.5));
//            arr[l.intValue()]++;
//        }
//
//        System.out.println(Arrays.toString(arr));
//        Integer[] applyPos = new Integer[]{1, 2, 3, 4, 5};
//        Integer[] vals = new Integer[6];
//        System.arraycopy(applyPos, 0, vals, 0, applyPos.length);
//        vals[5] = 12345;
//        System.out.println(Arrays.toString(applyPos));
//        System.out.println(Arrays.toString(vals));
//        
//        HashMap<Long, Integer> map = new HashMap<>();
//        for (int i = 0; i < 100000; i++) {
//            Long l = Math.round(Math.random() * 3 - 1.5);
//            if (!map.containsKey(l)) {
//                map.put(l, 0);
//            }
//            map.put(l, map.get(l) + 1);
//        }
//        for(Long key : map.keySet()){
//            System.out.println(key + " : " + map.get(key));
//        }
//        Integer[] arr = new Integer[8];
//        System.out.println(arr[4]);
//        String a = "FIRE_ICE";
//        String b = "WIND";
//        System.out.println(Arrays.toString(a.split("_")));
//        System.out.println(Arrays.toString(b.split("_")));
//        HashSet<String> c = new HashSet<>();
//        Collections.addAll(c, a.split("_"));
//        Collections.addAll(c, b.split("_"));
//        for(String str : c){
//            System.out.println(str);
//        }
//        Integer[] attr = new Integer[]{23245, 232450};
//        double hpPer = (double) attr[0] / attr[1] * 100;
//        System.out.println(hpPer);
//        System.out.println(EnumAttribute.getIndex("MDEF"));
//        System.out.println(Integer.toString(0x2b));
//        
//        for(int i = 0; i < 4; i++){
//            switch(i){
//                case 0:
//                    System.out.println("000");
//                    break;
//                default:
//                    System.out.println("001");
//                    break;
//            }
//            System.out.println("123");
//        }
        // Determine action play order.
//        HashMap<String, List<String[]>> skillMap = FileUtils.loadListMap("skill.csv");
//        HashMap<String, Integer[]> order = new HashMap<>();
//
//        List<HandCardInfo> cardList = new ArrayList<>();
//        HandCardInfo info = new HandCardInfo();
//        info.setSkillId("11100112");
//        info.setPlayedOrder(2);
//        info.setBoostPriority(0);
//        info.setSkill(new ArthurSkill());
//        info.getSkill().setArthurIndex(0);
//        cardList.add(info);
//
//        info = new HandCardInfo();
//        info.setSkillId("11100212");
//        info.setPlayedOrder(3);
//        info.setBoostPriority(0);
//        info.setSkill(new ArthurSkill());
//        info.getSkill().setArthurIndex(0);
//        cardList.add(info);
//
//        info = new HandCardInfo();
//        info.setSkillId("11100292");
//        info.setPlayedOrder(1);
//        info.setBoostPriority(0);
//        info.setSkill(new ArthurSkill());
//        info.getSkill().setArthurIndex(0);
//        cardList.add(info);
//
//        info = new HandCardInfo();
//        info.setSkillId("11100272");
//        info.setPlayedOrder(4);
//        info.setBoostPriority(0);
//        info.setSkill(new ArthurSkill());
//        info.getSkill().setArthurIndex(0);
//        cardList.add(info);
//
//        info = new HandCardInfo();
//        info.setSkillId("11100241");
//        info.setPlayedOrder(1);
//        info.setBoostPriority(0);
//        info.setSkill(new ArthurSkill());
//        info.getSkill().setArthurIndex(1);
//        cardList.add(info);
//
//        for (HandCardInfo card : cardList) {
//            order.put(card.getSkillId(), new Integer[cardList.size()]);
//            Arrays.fill(order.get(card.getSkillId()), 0);
//        }
//
//        for (int q = 0; q < 100000; q++) {
//            List<HandCardInfo> actionList = new ArrayList<>();
//            for (HandCardInfo card : cardList) {
//                card.setCardSkillPriority(Integer.parseInt(skillMap.get(card.getSkillId()).get(card.getBoostPriority())[14]));
//                actionList.add(card);
//            }
//            // Shuffle the 'cards'.
//            long n = actionList.size();
//            HandCardInfo temp;
//            for (int i = 0; i < n; i++) {
//                Integer index = ((Long) Math.round(Math.random() * (n - i) - 0.5 + i)).intValue();
//                if (index != i) {
//                    temp = actionList.get(i);
//                    actionList.set(i, actionList.get(index));
//                    actionList.set(index, temp);
//                }
//            }
//            // Re-order them.
//            actionList.sort(Constants.HAND_CARD_CMP);
//            
//            for (int i = 0; i < actionList.size(); i++) {
//                //System.out.println(actionList.get(i).getCardSkillPriority());
//                order.get(actionList.get(i).getSkillId())[i] = order.get(actionList.get(i).getSkillId())[i] + 1;
//            }
//        }
//
//        for (String str : order.keySet()) {
//            System.out.println(str + " : " + Arrays.toString(order.get(str)));
//        }
//        String a = "CARD_SEAL_REGIST";
//        String b = "INT";
//        String releaseName = a.substring(0, a.lastIndexOf("SELF")) + b;
//        System.out.println(releaseName);
//        String name = a.substring(0, a.indexOf("_REGIST"));
////        System.out.println(name);
//        Long ll = 24L * 60 + 2770 * 15 / 100;
//        System.out.println("24".matches("[\\d]+"));
//
//        System.out.println(Integer.parseInt("ENEMY3".substring(5, 6)) - 1);
//        String a = "1";
//        String b = "1";
//        Integer minDeadCount = Integer.parseInt(a);
//        Integer maxDeadCount = 999;
//        if (b.matches("[\\d]+")
//                && Integer.parseInt(b) != 0 && Integer.parseInt(a) == 0) {     // MAY have bug
//            maxDeadCount = Integer.parseInt(b);
//        }
//        Integer deadCount = 0;
//        if (deadCount < minDeadCount || deadCount >= maxDeadCount) {
//            System.out.println(false);
//        } else {
//            System.out.println(true);
//        }
//        
//        deadCount = 1;
//        if (deadCount < minDeadCount || deadCount >= maxDeadCount) {
//            System.out.println(false);
//        } else {
//            System.out.println(true);
//        }
//        String targetText = "RANDOM_EXCEPT_MERCENARY";
//        System.out.println(targetText.matches("RANDOM_EXCEPT_.*"));
//
//        Pattern p = Pattern.compile("RANDOM_EXCEPT_.*");
//        Matcher m = p.matcher(targetText);
//        while (m.find()) {
//            System.out.println(m.group());
//        }
//        System.out.println(targetText.substring(14));
//        Integer[] test = new Integer[]{-1, -1};
//        System.out.println(Arrays.toString(test));
//        resetArr(test);
//        System.out.println(Arrays.toString(test));
//        List<String> a = new ArrayList<>();
//        List<Object> b = new ArrayList<>();
//        a.add("a");
//        a.add("c");
//        a.add("f");
//        a.add("u");
//        a.add("n");
//        b.addAll(a);
//        System.out.println(a);
//        System.out.println(b);
//        Integer[] a = new Integer[]{1, 2};
//        Integer[] b = a.clone();
//        a[1] = 222;
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));
//
//        String c = "aaccbb";
//        String d = c;
//        c = "ddffgg";
//        System.out.println(c);
//        System.out.println(d);
//        String[] a = new String[6];
//        a[2] = "@310";
//        for(String str : a){
//            System.out.println(str);
//        }
//        HashMap<String, Integer> a = new HashMap<>();
//        a.put("A", Integer.MIN_VALUE);
//        a.put("B", Integer.MIN_VALUE);
//        a.put("D", Integer.MIN_VALUE);
//        
//
//        List<String> buffs = new ArrayList<>(a.keySet());
//        System.out.println(buffs);
//        a.put("E", Integer.MIN_VALUE);
//        a.remove("A");
//        System.out.println(buffs);
//        
        List<ArthurInfo> a = new ArrayList<>();
        a.add(new ArthurInfo());
        a.add(new ArthurInfo());
        a.add(new ArthurInfo());
        
        a.get(0).setName("123333");
        List<ArthurInfo> b = new ArrayList<>();
        for(ArthurInfo ai : a){
            try {
                b.add(ai.clone());
            } catch (CloneNotSupportedException ex) {
                
            }
        }
        
        a.get(0).setName("123");
        a.remove(1);
        System.out.println(a);
        System.out.println(b);
    }

    private static void resetArr(Integer[] test) {
        test = new Integer[]{1, 1};
    }
}
