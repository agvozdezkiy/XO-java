package XO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Реакция на нахатия кнопок игрового поля.
 * Расшифровываем ActionCommand и передаем координаты нажатой кнопки классу игрового поля
 */
public class GameButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event){
        String buttonID = event.getActionCommand();

        int i = Integer.parseInt(buttonID) / 3;
        int j = Integer.parseInt(buttonID) - i*3;

        XO.gamePlay.setIJ(i, j);
        XO.gamePlay.start();
    }
}
