/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import simulator.BattleSimu;
import simulator.MainFrame;
import swing.InformationDialog;
import swing.InformationDialogPanel;

/**
 *
 * @author Shadow
 */
public class UIUtil {

    private static boolean autoDownload;
    private static InformationDialog infoDialog;
    //private static InformationDialogPanel infoPanelObjectSave;
    private static JPanel deckSimu;
    private static JPanel battlePrepare;
    private static JPanel battleSimu;
    private static JPanel sphereSimu;
    private static JFrame mainFrame;

    public static boolean isAutoDownload() {
        return autoDownload;
    }

    public static void setAutoDownload(boolean autoDownload) {
        UIUtil.autoDownload = autoDownload;
    }

    public static InformationDialog showInfoDialog(JFrame owner, String label) {
        infoDialog = new InformationDialog(owner, "Warning", false);
        infoDialog.setUndecorated(true);
        infoDialog.setPreferredSize(new Dimension(300, 75));
        infoDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        InformationDialogPanel infoPanel = new InformationDialogPanel();
        infoPanel.setLabelText(label);
        infoDialog.setInfoDialogPanel(infoPanel);
        infoDialog.getContentPane().add(infoPanel, BorderLayout.CENTER);
        infoDialog.pack();
        infoPanel.setContentText("");
        infoDialog.setLocationRelativeTo(owner);
        infoDialog.setVisible(true);// 显示进度对话框

        return infoDialog;
    }

    public static void setDialogLabel(String text) {
        if (infoDialog != null) {
            infoDialog.getInfoDialogPanel().setLabelText(text);
        }
    }

    public static void setDialogText(String text) {
        if (infoDialog != null) {
            infoDialog.getInfoDialogPanel().setContentText(text);
        }
    }

    public static void hideInfoDialog(InformationDialog dialog) {
        dialog.dispose();
    }

    public static JPanel getDeckSimu() {
        return deckSimu;
    }

    public static void setDeckSimu(JPanel deckSimu) {
        UIUtil.deckSimu = deckSimu;
    }

    public static JPanel getBattlePrepare() {
        return battlePrepare;
    }

    public static void setBattlePrepare(JPanel battlePrepare) {
        UIUtil.battlePrepare = battlePrepare;
    }

    public static BattleSimu getBattleSimu() {
        return (BattleSimu) battleSimu;
    }

    public static void setBattleSimu(JPanel battleSimu) {
        UIUtil.battleSimu = battleSimu;
    }

    public static JPanel getSphereSimu() {
        return sphereSimu;
    }

    public static void setSphereSimu(JPanel sphereSimu) {
        UIUtil.sphereSimu = sphereSimu;
    }

    public static void setMainFrame(MainFrame frame) {
        UIUtil.mainFrame = frame;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

}
