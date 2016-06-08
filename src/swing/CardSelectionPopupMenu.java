/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import model.ArthurInfo;
import model.ArthurSkill;
import model.HandCardInfo;
import util.UIUtil;

/**
 *
 * @author Shadow
 */
public class CardSelectionPopupMenu extends JPopupMenu {

    public final Font defaultFont = new java.awt.Font("宋体", 0, 12);

    public CardSelectionPopupMenu() {
        super("替换卡牌");

    }

    public void initPopupMenu(ArthurInfo arthur, HandCardInfo cardToReplace, List<String> deckSting, HashMap<String, ArthurSkill> deck) {
        this.removeAll();

        JLabel label = new JLabel("  替换卡牌为：");
        label.setSize(80, 20);
        label.setFont(defaultFont);
        this.add(label);

        for (String str : deckSting) {
            JMenuItem selectCard = new JMenuItem();
            selectCard.setText(deck.get(str).getCardName());
            this.add(selectCard);
            selectCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UIUtil.getBattleSimu().doneCardSelection(arthur, cardToReplace, str);
                }
            });
        }
    }
}
