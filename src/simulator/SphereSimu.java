/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import model.SphereBasicInfo;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import util.FileUtils;
import util.TextUtil;

/**
 *
 * @author Shadow
 */
public class SphereSimu extends javax.swing.JPanel {

    /**
     * Creates new form SphereSimu
     */
    public SphereSimu() {
        initComponents();
        initComponentsByCode();
        initData();
        initListener();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;

    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private JPanel mainPanel;
    private JScrollPane sphrListPanel;
    private List<JPanel> classPanels;
    //private List<JScrollPane> shpereScroPane;
    private JList sphereJList;
    private List<JList> equipList;
    private List<JToggleButton> changeBtnList;
    private List<GroupLayout> layoutList;
    private JButton saveButton;
    private JButton resetButton;
    private ButtonGroup changeBtnGroup;
    private final String[] className = new String[]{"佣兵亚瑟", "富豪亚瑟", "盗贼亚瑟", "歌姬亚瑟"};

    private void initComponentsByCode() {
        mainPanel = new javax.swing.JPanel();
        sphrListPanel = new JScrollPane();
        sphrListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("可选Sphere一览（双击装备）"));
        sphereJList = new JList();
        sphereJList.setBackground(new java.awt.Color(240, 240, 240));
        sphereJList.setModel(new DefaultListModel());
        sphereJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sphrListPanel.setViewportView(sphereJList);

        saveButton = new JButton();
        resetButton = new JButton();
        saveButton.setText("保存");
        resetButton.setText("重置");

        classPanels = new ArrayList<>();

        equipList = new ArrayList<>();
        layoutList = new ArrayList<>();
        changeBtnList = new ArrayList<>();
        changeBtnGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            final int j = i;
            classPanels.add(new JPanel());
            sphereJList.add(new JList());
            equipList.add(new JList());
            changeBtnList.add(new JToggleButton());
            changeBtnGroup.add(changeBtnList.get(i));

            jPanel2 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            jList1 = new javax.swing.JList<>();
            jList2 = new javax.swing.JList<>();

            classPanels.get(i).setBorder(javax.swing.BorderFactory.createTitledBorder(className[i]));

            equipList.get(i).setBackground(new java.awt.Color(240, 240, 240));
            equipList.get(i).setModel(new DefaultListModel());
            equipList.get(i).setBorder(javax.swing.BorderFactory.createTitledBorder("当前装备Sphere（双击移除）"));
            equipList.get(i).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ListCellRenderer lcr = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {
                    list.setToolTipText(value + " | " + TextUtil.getSphereDescription(sphereMap.get(classSphereList[j].get(index))));
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            };
            equipList.get(i).setCellRenderer(lcr);
            changeBtnList.get(i).setText("显示可选Sphere");

            layoutList.add(new javax.swing.GroupLayout(classPanels.get(i)));
            classPanels.get(i).setLayout(layoutList.get(i));
            layoutList.get(i).setHorizontalGroup(
                    layoutList.get(i).createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutList.get(i).createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layoutList.get(i).createParallelGroup()
                                    //.addComponent(shpereScroPane.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(equipList.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(changeBtnList.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                            .addContainerGap())
            );
            layoutList.get(i).setVerticalGroup(
                    layoutList.get(i).createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layoutList.get(i).createSequentialGroup()
                            //.addComponent(shpereScroPane.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addGap(5, 5, 5)
                            .addComponent(equipList.get(i), javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeBtnList.get(i), javax.swing.GroupLayout.DEFAULT_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
            );

        }

        GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(classPanels.get(0), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(1), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(2), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(3), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
        );
        mainPanelLayout.setVerticalGroup(mainPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(mainPanelLayout.createParallelGroup()
                        .addComponent(classPanels.get(0), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(1), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(2), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(classPanels.get(3), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sphrListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 509, Short.MAX_VALUE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(sphrListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
        );
    }

    public static void main(String[] args) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                SphereSimu bp = new SphereSimu();
                frame.add(bp);
                frame.setVisible(true);

                FileUtils.initDummy();
            }
        });

    }

    private HashMap<String, String[]> sphereMap;
    private List<String[]> sphereList;
    private List<String>[] classSphereList;

    private void initData() {
        sphereList = FileUtils.loadList("sphere_data.csv");
        sphereMap = FileUtils.loadMap("sphere_data.csv");
        classSphereList = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            classSphereList[i] = new ArrayList<>();
        }
        loadSphere();
    }

    private void initListener() {
        for (int i = 0; i < 4; i++) {
            final int j = i;
            changeBtnList.get(i).addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    refreshSphereList(j);
                }
            });

            equipList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() % 2 == 0) {
                        unequipSphere(j);
                    }
                }
            });
        }

        sphereJList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() % 2 == 0) {
                    equipSphere();
                }
            }
        });

        saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSphere();
            }
        });

        resetButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSphere();
            }
        });

        changeBtnList.get(0).setSelected(true);
        refreshSphereList(0);
    }

    private void loadSphere() {
        File file = new File("Records.MASphere");
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            for (int i = 0; i < 4; i++) {
                List<String> spheres = classSphereList[i];
                for (String str : br.readLine().split(",")) {
                    if (str.isEmpty()) {
                        continue;
                    }
                    spheres.add(str);
                    ((DefaultListModel) equipList.get(i).getModel()).addElement(sphereMap.get(str)[1]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveSphere() {
        File file = new File("Records.MASphere");
        if (file.exists() && file.isDirectory()) {
            file.delete();
        }
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            StringBuilder sb;
            for (List<String> spheres : classSphereList) {
                sb = new StringBuilder();
                for (String str : spheres) {
                    sb.append(str).append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                br.write(sb.toString(), 0, sb.length());
                br.newLine();
            }
            br.flush();
            br.close();
            JOptionPane.showMessageDialog(this, "Sphere信息已经保存。", "完成", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetSphere() {
        for (int i = 0; i < 4; i++) {
            ((DefaultListModel) equipList.get(i).getModel()).removeAllElements();
            classSphereList[i].clear();
        }
    }

    private void equipSphere() {
        SphereBasicInfo info = (SphereBasicInfo) sphereJList.getSelectedValue();
        if (classSphereList[info.getClassIndex()].size() >= 3) {
            return;
        }
        if (classSphereList[info.getClassIndex()].contains(info.getId())) {
            return;
        }
        classSphereList[info.getClassIndex()].add(info.getId());
        ((DefaultListModel) equipList.get(info.getClassIndex()).getModel()).addElement(info.getName());
    }

    private void unequipSphere(int classIndex) {
        if (equipList.get(classIndex).getSelectedIndex() == -1) {
            return;
        }
        classSphereList[classIndex].remove(equipList.get(classIndex).getSelectedIndex());
        ((DefaultListModel) equipList.get(classIndex).getModel()).removeElementAt(equipList.get(classIndex).getSelectedIndex());
    }

    private void refreshSphereList(Integer classIndex) {
        //System.out.println(classIndex);
        ((DefaultListModel) sphereJList.getModel()).clear();
        for (String[] array : sphereList) {
            SphereBasicInfo baseInfo = new SphereBasicInfo(array[0], array[1], classIndex, TextUtil.getSphereDescription(array));
            if (array[classIndex + 3].equals("1")) {
                ((DefaultListModel) sphereJList.getModel()).addElement(baseInfo);
            }
        }
    }


}
