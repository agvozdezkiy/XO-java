package XO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Нажатие кнопки старт: блокируются кнопка старт и выбор противника,
 * статус игры меняется на "началась", выводится сообщение, что игра началась
 */
public class StartButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event){
        XO.gamePlay.setGameStatus(GameStatus.STARTED);
        XO.gameWindow.startButton.setEnabled(false);
        XO.gameWindow.selectPlayerComboBox.setEnabled(false);
        XO.gameWindow.logPane.setText("<html>Game started!<br><html>");
        XO.gamePlay.start();
    }
}
