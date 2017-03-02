package XO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Действие кнопки "Начать игру заново". Возвращает все свойства и состояние игры в исходные.
 */
public class RestartButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
        XO.gameWindow.clear();
        XO.gameField.clear();
        XO.gamePlay.clear();
        XO.gameWindow.restartButton.setEnabled(false);
        XO.gameWindow.startButton.setEnabled(true);
        XO.gameWindow.selectPlayerComboBox.setEnabled(true);
    }
}
