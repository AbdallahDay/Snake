package abdallah;

import javax.swing.*;
import java.awt.event.*;
import java.text.BreakIterator;

public class dlgSettings extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox gameSpeedComboBox;
    private JComboBox squareSizeComboBox;
    private JCheckBox mazeCheckBox;
    private JCheckBox warpWallsCheckBox;

    private final int SLOW = 0;
    private final int MEDIUM = 1;
    private final int FAST = 2;

    private final int SMALL = 0;
    private final int LARGE = 1;

    public dlgSettings() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        //Get current game settings and set combo box options

        //game speed combo box
        gameSpeedComboBox.addItem("Rosy Boa    (slow)");
        gameSpeedComboBox.addItem("King Cobra  (medium)");
        gameSpeedComboBox.addItem("Black Mamba (fast)");

        int currentSpeed = SnakeGame.getGameSpeed();

        switch (currentSpeed) {
            case SnakeGame.ROSY_BOA: {
                gameSpeedComboBox.setSelectedIndex(SLOW);
                break;
            }
            case SnakeGame.KING_COBRA: {
                gameSpeedComboBox.setSelectedIndex(MEDIUM);
                break;
            }
            case SnakeGame.BLACK_MAMBA: {
                gameSpeedComboBox.setSelectedIndex(FAST);
                break;
            }
        }

        //Square size combo box
        squareSizeComboBox.addItem("Small");
        squareSizeComboBox.addItem("Large");

        int currentSquareSize = SnakeGame.squareSize;

        switch (currentSquareSize) {
            case SnakeGame.SMALL_SQUARES: {
                squareSizeComboBox.setSelectedIndex(SMALL);
            }
            case SnakeGame.LARGE_SQUARES: {
                squareSizeComboBox.setSelectedIndex(LARGE);
            }
        }

        //check boxes
        mazeCheckBox.setSelected(SnakeGame.mazeOn);
        warpWallsCheckBox.setSelected(SnakeGame.warpWallsOn);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        //Change game settings
        int gameSpeed = gameSpeedComboBox.getSelectedIndex();
        int squareSize = squareSizeComboBox.getSelectedIndex();

        switch (gameSpeed) {
            case SLOW: {
                SnakeGame.setGameSpeed(SnakeGame.ROSY_BOA);
                break;
            }
            case MEDIUM: {
                SnakeGame.setGameSpeed(SnakeGame.KING_COBRA);
                break;
            }
            case FAST: {
                SnakeGame.setGameSpeed(SnakeGame.BLACK_MAMBA);
                break;
            }
        }

        switch (squareSize) {
            case SMALL: {
                SnakeGame.squareSize = SnakeGame.SMALL_SQUARES;
                break;
            }
            case LARGE: {
                SnakeGame.squareSize = SnakeGame.LARGE_SQUARES;
                break;
            }
        }

        SnakeGame.mazeOn = mazeCheckBox.isSelected();
        SnakeGame.warpWallsOn = warpWallsCheckBox.isSelected();

        SnakeGame.restart();
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        dlgSettings settings = new dlgSettings();
        settings.pack();
        settings.setVisible(true);
        System.exit(0);
    }
}
