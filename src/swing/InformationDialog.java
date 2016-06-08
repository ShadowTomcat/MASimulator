/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Shadow
 */
public class InformationDialog extends JDialog{

    private InformationDialogPanel infoDialogPanel;

    public InformationDialog(JFrame owner, String title, boolean mode) {
        super(owner, title, mode);
    }

    public InformationDialogPanel getInfoDialogPanel() {
        return infoDialogPanel;
    }

    public void setInfoDialogPanel(InformationDialogPanel infoDialogPanel) {
        this.infoDialogPanel = infoDialogPanel;
    }


}
