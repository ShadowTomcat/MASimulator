/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Shadow
 */
public class TextUtil {

    public synchronized static String getSphereDescription(String[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(array[12]).append("C | ");
        sb.append(array[11]).append("回合后 | ");
        sb.append(array[13]);
        return sb.toString();
    }
}
