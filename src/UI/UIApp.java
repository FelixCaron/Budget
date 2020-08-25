package UI;

import Main.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UIApp {
    public static UIApp runningUI;
    GridBagConstraints gbc;
    JFrame parentFrame;
    JPanel parentPanel;
    

    public static void launch() {
        runningUI = new UIApp();
    }

    UIApp() {
        
        parentFrame = new JFrame();
        parentFrame.setTitle("Budget");
        parentFrame.setSize(800, 600);
        parentFrame.setLocationRelativeTo(null);
        parentFrame.setVisible(true);
        parentFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        parentFrame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if(askForExitConfirm()){
                    Main.exit();
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }
            
        });
        
        parentPanel = new JPanel(new GridBagLayout());
        parentFrame.setContentPane(parentPanel);
        addComponents();

        }
        

        private void addComponents() {
            //make the window here
        }

        protected boolean askForExitConfirm() {
        int b = JOptionPane.showConfirmDialog(this.parentFrame, "Save and Exit?", "Exit", JOptionPane.OK_CANCEL_OPTION);
        
        return (b==0);
    }
    
}