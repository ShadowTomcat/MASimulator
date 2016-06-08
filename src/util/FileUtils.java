/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import javafx.SynchronousJFXFileChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Shadow
 */
public class FileUtils {

    private static String currentPath = ".";
    private static SynchronousJFXFileChooser chooser;

    public static String getCurrentPath() {
        return currentPath;
    }

    public static void setCurrentPath(String currentPath) {
        FileUtils.currentPath = currentPath;
    }

    public synchronized static String changeDuplicateFileName(String fileName) {
        if (fileName.lastIndexOf("(") == -1) {
            return addPostSerial(fileName, "(1)");
        } else {
            String serial = fileName.substring(fileName.lastIndexOf("("), fileName.lastIndexOf(")") + 1);
            Pattern p = Pattern.compile("[(][\\d]+[)]");
            if (p.matcher(serial).matches()) {
                String newSerial = "(" + (Integer.parseInt(serial.substring(1, serial.length() - 1)) + 1) + ")";
                return fileName.substring(0, fileName.lastIndexOf("(")) + newSerial + fileName.substring(fileName.lastIndexOf(")") + 1);
            } else {
                return addPostSerial(fileName, "(1)");
            }
        }
    }

    public synchronized static String addPostSerial(String fileName, String postFix) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return postFix;
        }
        if (fileName.contains(".")) {
            return fileName.substring(0, fileName.lastIndexOf(".")) + postFix + fileName.substring(fileName.lastIndexOf("."));
        }
        return fileName;
    }

    public synchronized static String changePostFix(String fileName, String newPostFix) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return "";
        }
        if (fileName.contains(".")) {
            return fileName.substring(0, fileName.lastIndexOf(".")) + newPostFix;
        }
        return fileName + "." + newPostFix;
    }

    public synchronized static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static HashMap<String, List<String[]>> loadListMap(String filePath) {
        try {
//            File f = new File(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
            InputStream is = FileUtils.class.getResourceAsStream("/resources/" + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            HashMap<String, List<String[]>> map = new HashMap<>();
            //Pattern p = Pattern.compile("[\\d]+");
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(",");
                if (lineArray.length > 0 && lineArray[0] != null && lineArray[0].matches("[\\d]+")) {
                    if (!map.containsKey(lineArray[0])) {
                        map.put(lineArray[0], new ArrayList<>());
                    }
                    map.get(lineArray[0]).add(lineArray);
                    //System.out.println(line);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String[]> loadMap(String filePath) {
        try {
            //File f = new File(filePath);
            InputStream is = FileUtils.class.getResourceAsStream("/resources/" + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            HashMap<String, String[]> map = new HashMap<>();
            //Pattern p = Pattern.compile("[\\d]+");
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(",");
                if (lineArray.length > 0 && lineArray[0] != null && lineArray[0].matches("[\\d]+")) {
                    map.put(lineArray[0], lineArray);
                    //System.out.println(line);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String[]> loadList(String filePath) {
        try {
            //File f = new File(filePath);
            InputStream is = FileUtils.class.getResourceAsStream("/resources/" + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            List<String[]> list = new ArrayList<>();
            //Pattern p = Pattern.compile("[\\d]+");
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(",");
                if (lineArray.length > 0 && lineArray[0] != null && lineArray[0].matches("[\\d]+")) {
                    list.add(lineArray);
                    //System.out.println(line);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initDummy() {
        javafx.embed.swing.JFXPanel dummy = new javafx.embed.swing.JFXPanel();
        chooser = new SynchronousJFXFileChooser(() -> {
            FileChooser ch = new FileChooser();
            if (FileUtils.getCurrentPath() != null) {
                ch.setInitialDirectory(new File(FileUtils.getCurrentPath()));
            }
            ch.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("MA Deck File", "*.MAdeck"),
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );
            return ch;
        });        // TODO add your handling code here:
    }

    public static SynchronousJFXFileChooser getChooser() {
        return chooser;
    }

}
