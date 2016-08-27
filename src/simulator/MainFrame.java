/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.PropertyConfigurator;
import swing.InformationDialog;
import util.FileUtils;
import util.UIUtil;

/**
 *
 * @author Shadow
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        initComponentsByCode();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mnuDeck = new javax.swing.JMenuItem();
        mnuSphere = new javax.swing.JMenuItem();
        mnuBattle = new javax.swing.JMenuItem();
        chkMnuShowImage = new javax.swing.JCheckBoxMenuItem();
        mnuTest = new javax.swing.JMenu();
        mnuBack = new javax.swing.JMenuItem();
        mnuCardSelection = new javax.swing.JCheckBoxMenuItem();
        mnuExpendCost = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuHelp = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MA Simulator v0.96");

        jMenu2.setText("功能");

        mnuDeck.setText("卡组编辑");
        mnuDeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDeckActionPerformed(evt);
            }
        });
        jMenu2.add(mnuDeck);

        mnuSphere.setText("Sphere编辑");
        mnuSphere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSphereActionPerformed(evt);
            }
        });
        jMenu2.add(mnuSphere);

        mnuBattle.setText("战斗模拟");
        mnuBattle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBattleActionPerformed(evt);
            }
        });
        jMenu2.add(mnuBattle);

        chkMnuShowImage.setSelected(true);
        chkMnuShowImage.setText("显示卡牌头像");
        chkMnuShowImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMnuShowImageActionPerformed(evt);
            }
        });
        jMenu2.add(chkMnuShowImage);

        jMenuBar1.add(jMenu2);

        mnuTest.setText("测试");

        mnuBack.setText("时光之轮");
        mnuBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuBackActionPerformed(evt);
            }
        });
        mnuTest.add(mnuBack);

        mnuCardSelection.setText("上帝之手");
        mnuTest.add(mnuCardSelection);

        mnuExpendCost.setText("王之财宝");
        mnuExpendCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExpendCostActionPerformed(evt);
            }
        });
        mnuTest.add(mnuExpendCost);

        jMenuBar1.add(mnuTest);

        jMenu1.setText("版本");

        mnuHelp.setText("帮助文档");
        mnuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHelpActionPerformed(evt);
            }
        });
        jMenu1.add(mnuHelp);

        mnuAbout.setText("喵～ฅ( ̳• ·̫ • ̳)");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        jMenu1.add(mnuAbout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuDeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDeckActionPerformed
        switchPanel("deck");
    }//GEN-LAST:event_mnuDeckActionPerformed

    private void mnuBattleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBattleActionPerformed
        switchPanel("battle_prepare");
    }//GEN-LAST:event_mnuBattleActionPerformed

    private void mnuSphereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSphereActionPerformed
        switchPanel("sphere");
    }//GEN-LAST:event_mnuSphereActionPerformed

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
        JDialog dialog = new JDialog();
        JPanel about = new About();
        dialog.setTitle("关于本应用～");
        dialog.setSize(390, 310);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(about);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_mnuAboutActionPerformed

    private void mnuHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuHelpActionPerformed
        try {
            File resourceFile = new File("README.txt");
            if (resourceFile.exists() && resourceFile.isFile()) {
                Runtime.getRuntime().exec("cmd /c start notepad.exe " + resourceFile);
            }
        } catch (IOException ex) {
            log.error(ex);
        }
    }//GEN-LAST:event_mnuHelpActionPerformed

    private void mnuBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuBackActionPerformed
        UIUtil.getBattleSimu().revertTurnInfo();
    }//GEN-LAST:event_mnuBackActionPerformed

    private void mnuExpendCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExpendCostActionPerformed
        JDialog dialog = new JDialog(this, "初始COST设置", true);
        dialog.add(new SetInitCostPanel());
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_mnuExpendCostActionPerformed

    private void chkMnuShowImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMnuShowImageActionPerformed
        if (UIUtil.getDeckSimu() != null) {
            ((DeckSimu) UIUtil.getDeckSimu()).processShowImage(chkMnuShowImage.isSelected());
        }
    }//GEN-LAST:event_chkMnuShowImageActionPerformed

    public void switchPanel(String panelName) {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        mnuTest.setEnabled(false);
        switch (panelName) {
            case "deck":
                if (UIUtil.getDeckSimu() == null) {
                    currentPanel = new DeckSimu();
                    UIUtil.setDeckSimu(currentPanel);
                } else {
                    currentPanel = UIUtil.getDeckSimu();
                }
                this.add(currentPanel);
                break;
            case "battle_prepare":
                new Thread(() -> {
                    if (UIUtil.getBattlePrepare() == null) {
                        InformationDialog dialog = UIUtil.showInfoDialog(UIUtil.getMainFrame(), "资源载入中……");
                        currentPanel = new BattlePrepare();
                        UIUtil.setBattlePrepare(currentPanel);
                        UIUtil.hideInfoDialog(dialog);
                    } else {
                        currentPanel = UIUtil.getBattlePrepare();
                        ((BattlePrepare) currentPanel).loadSphere();
                    }
                    this.add(currentPanel);
                    this.validate();
                    this.repaint();
                }).start();
                break;
            case "sphere":
                if (UIUtil.getSphereSimu() == null) {
                    currentPanel = new SphereSimu();
                    UIUtil.setSphereSimu(currentPanel);
                } else {
                    currentPanel = UIUtil.getSphereSimu();
                }
                this.add(currentPanel);
                break;
            case "battle_simu":
                currentPanel = UIUtil.getBattleSimu();
                this.add(currentPanel);
                mnuTest.setEnabled(true);
            default:
                break;
        }
        this.validate();
        this.repaint();
    }

    public void enableTestFunc(boolean value) {
        mnuBack.setEnabled(value);
        mnuCardSelection.setEnabled(value);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InputStream is = FileUtils.class.getResourceAsStream("/resources/log4j.properties");
                    PropertyConfigurator.configure(is);
                    log.debug("Start Application.");
                    MainFrame frame = new MainFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                } catch (Exception e) {
                    log.error(e);
                    for (StackTraceElement element : e.getStackTrace()) {
                        log.error(element);
                    }
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem chkMnuShowImage;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenuItem mnuBack;
    private javax.swing.JMenuItem mnuBattle;
    private javax.swing.JCheckBoxMenuItem mnuCardSelection;
    private javax.swing.JMenuItem mnuDeck;
    private javax.swing.JMenuItem mnuExpendCost;
    private javax.swing.JMenuItem mnuHelp;
    private javax.swing.JMenuItem mnuSphere;
    private javax.swing.JMenu mnuTest;
    // End of variables declaration//GEN-END:variables
    private JPanel currentPanel;
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainFrame.class);

    private void initComponentsByCode() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screensize.getWidth();
        int height = (int) screensize.getHeight();
        if (width > 1200) {
            width = 1200;
        }
        if (height > 880) {
            height = 880;
        }
        height = height - 20;
        this.setSize(width, height);
        currentPanel = new DeckSimu();
        UIUtil.setDeckSimu(currentPanel);
        this.add(currentPanel);
        this.validate();
        this.repaint();

        mnuTest.setEnabled(false);
        
        File dir = new File("img");
        File f = new File("img/showImage_false");
        if (dir.exists() && !f.exists()) {
            this.chkMnuShowImage.setSelected(true);
        } else {
            this.chkMnuShowImage.setSelected(false);
            ((DeckSimu)currentPanel).processShowImage(false);
        }

        FileUtils.initDummy();
        UIUtil.setMainFrame(this);
    }

    public boolean cardSelectionEnabled() {
        return mnuCardSelection.isSelected();
    }

}
