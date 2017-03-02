package XO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Действие комбобокса "выбор соперника".
 */
public class SelectPlayerComboBoxListener implements ActionListener{
    public void actionPerformed(ActionEvent event){

        if(XO.gameWindow.selectPlayerComboBox.getSelectedItem().equals("Robot")) XO.gamePlay.player2.setRobot(true);
            else XO.gamePlay.player2.setRobot(false);

    }

}
