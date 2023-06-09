import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class MineScene extends Scene {
    int mineSize = 20;
    MineBoard mineboard;

    private int GetBoardSize() {
        String[] options = {"Large", "Medium", "Small"};
        String msg = "Small:  15 x 15\nMedium:  25 x 25\nLarge:  35 x 35";
        int opt = JOptionPane.showOptionDialog(this, msg, "Choose Board Size", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, 1);
        if (opt == JOptionPane.CLOSED_OPTION)
            opt = 2;
        switch (opt) {
            case 2:
                return 15;
            case 1:
                return 25;
            case 0:
                return 35;
            default:
                break;
        }
        return 15;
    }

    private float GetDifficulty() {
        String[] options = {"Hard", "Normal", "Easy"};
        int opt = JOptionPane.showOptionDialog(this, "", "Choose Difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        if (opt == JOptionPane.CLOSED_OPTION)
            opt = 2;
        switch (opt) {
            case 2:
                return 0.1f;
            case 1:
                return 0.25f;
            case 0:
                return 0.5f;
            default:
                break;
        }
        return 0.1f;
    }

    MineScene(JFrame frame) {
        window = frame;
        int boardSizeCount = GetBoardSize();
        float diff = GetDifficulty();
        int mineNo = (int)(boardSizeCount * boardSizeCount * diff);
        setLayout(null);
        mineboard = new MineBoard(boardSizeCount, boardSizeCount, mineNo, 20, this);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int wSizeX = window.getBounds().getSize().width;
                int wSizeY = window.getBounds().getSize().height;
                int boardSizeX = mineSize * boardSizeCount;
                int boardSizeY = mineSize * boardSizeCount;
                mineboard.setLocation((wSizeX - boardSizeX)/2, (wSizeY - boardSizeY)/ 2);
                super.componentResized(e);
            }
        });
    }
}
