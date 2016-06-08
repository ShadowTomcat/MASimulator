/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import util.UIUtil;

/**
 *
 * @author Shadow
 */
public class CardPanelMouseAdapter extends MouseAdapter {

    private final Integer cardPanelIndex;

    public CardPanelMouseAdapter(Integer cardPanelIndex) {
        this.cardPanelIndex = cardPanelIndex;
    }

    @Override
    public void mousePressed(MouseEvent event) { //点击鼠标
        triggerEvent(event); //调用triggerEvent方法处理事件
    }

    @Override
    public void mouseReleased(MouseEvent event) { //释放鼠标
        triggerEvent(event);
    }

    private void triggerEvent(MouseEvent event) { //处理事件
        if (event.isPopupTrigger()) //如果是弹出菜单事件(根据平台不同可能不同)
        {
            UIUtil.getBattleSimu().tryCardSelection(event, cardPanelIndex);
        }
    }
}
