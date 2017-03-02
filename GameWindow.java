package XO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by antonio on 13/02/17.
 */
public class GameWindow extends JFrame{

    private JButton[][] gameButtons = new JButton[3][3];
    public JButton startButton, restartButton;
    public JComboBox selectPlayerComboBox;
    public JLabel logPane;


    public GameWindow(){

        // В конструкторе создаем основное окно с позицией, с размерами и заголовком
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setLocation(200, 200);
        setResizable(false);
        setTitle("X&0");

        //Создаем бокс панели управления игрой с элементами настройки и окном сообщени
        Box tweackBox = Box.createVerticalBox();

        //Блок выбора игрового партнера
        tweackBox.add(Box.createVerticalStrut(10));
        tweackBox.add(new JLabel("Select your partner"));
        tweackBox.add(Box.createVerticalStrut(10));

        String e[] = {"Man", "Robot"};
        selectPlayerComboBox = new JComboBox(e);
        selectPlayerComboBox.setAlignmentX(LEFT_ALIGNMENT);
        selectPlayerComboBox.setSelectedItem(e[1]);
        selectPlayerComboBox.addActionListener(new SelectPlayerComboBoxListener());
        tweackBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tweackBox.add(selectPlayerComboBox);
        tweackBox.add(Box.createVerticalStrut(10));

        //Кнопка начала игры
        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());
        tweackBox.add(startButton);
        tweackBox.add(Box.createVerticalStrut(5));
        tweackBox.add(new JSeparator());
        tweackBox.add(Box.createVerticalStrut(5));

        //Окно вывода сообщений
        logPane = new JLabel();
        logPane.setVerticalAlignment(JLabel.NORTH);
        tweackBox.add(logPane);
        Dimension dim = new Dimension(100, 600);
        logPane.setPreferredSize(dim);
        tweackBox.add(Box.createVerticalStrut(5));

        tweackBox.add(new JSeparator());
        tweackBox.add(Box.createVerticalStrut(5));

        //Кнопка новой игры неактивна
        restartButton = new JButton("Restart game");
        restartButton.addActionListener(new RestartButtonListener());
        restartButton.setEnabled(false);
        tweackBox.add(restartButton);

        //Цепляим блок управления игрой к основному окну
        getContentPane().add(tweackBox, BorderLayout.EAST);

        //Создаем игровые кнопки без заголовков с интивидуальным ActionCommand, меняем шрифт текста кнопки
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBounds(0, 0, 300, 300);
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameButtons[i][j] = new JButton("");
                gameButtons[i][j].addActionListener(new GameButtonListener());
                gameButtons[i][j].setActionCommand(Integer.toString(i*3+j));   //Устанавливаем ActionCommand для каждой кнопки

                Font font = new Font("Dialog", Font.PLAIN, 200);

                gameButtons[i][j].setFont(font);
                gamePanel.add(gameButtons[i][j]);
            }
        }

        //Отображаем окно
        setVisible(true);
    }



    //Рисует на заданной кнопке крест или ноль
    public void setTextGameButtons(int i, int j, FIELD_STATE state){
            if(state == FIELD_STATE.X)
                gameButtons[i][j].setText("X");
            if(state == FIELD_STATE.O)
                gameButtons[i][j].setText("0");
    }


    //очищает игровое поле от крстов и нолей
    public void clear(){
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameButtons[i][j].setText("");
            }
        }
        logPane.setText("");
    }

}