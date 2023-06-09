import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

//class for each cell of minesweeper game board
class MineCell extends JButton {
    Boolean flagged = false; //if this cell is flagged
    Boolean cracked = false; //if this cell is cracked(opened)
    int value = 0; // -1 if there's mine, else surrounding mine count
    MineBoard mb; //parent game board info

    //Constructor for Mine Cell
    MineCell(int size, int posX, int posY, MineBoard mb) {
        this.mb = mb;
        this.setOpaque(true);
        this.setBackground(Color.pink);
        this.setBounds(posX, posY, size, size);
        this.setBorder(new LineBorder(Color.BLACK, 1));
        this.setVisible(true);
        //add click event(Mouse) Listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                    case 1:
                        mb.LeftClick(getLocation().x/size, getLocation().y/size);
                        mb.CheckWin();
                        break;
                    case 3:
                        mb.RightClick(getLocation().x/size, getLocation().y/size);
                        mb.CheckWin();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
class MineBoard extends JPanel implements ICompAddable {
    MineCell[][] board;
    int mineNo;
    int cellCountX;
    int cellCountY;
    int mineSize;
    int mineFound = 0;
    public void RightClick(int x, int y){
        if (!board[x][y].flagged){
            board[x][y].setBackground(Color.red);
            board[x][y].flagged = true;
        } else {
            board[x][y].setBackground(Color.pink);
            board[x][y].flagged = false;
        }
        if (board[x][y].value == -1)
            mineFound += 1;
    }
    public void LeftClick(int x, int y) {
        if(board[x][y].value == -1) {
            GameOver();
            return;
        }
        board[x][y].setBackground(Color.green);
        board[x][y].cracked = true;


        if(board[x][y].value != 0)
            board[x][y].setText(Integer.toString(board[x][y].value));

        if(board[x][y].value == 0){
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++){
                    if((0 <= i && i < cellCountX) && ( 0 <= j && j < cellCountY)){
                        if(!board[i][j].cracked)
                            LeftClick(i, j);
                        repaint();
                    }
                }
            }
        }
    }
    public void CheckWin() {
        //check if every non-mine cell is exposed
        for (int i = 0; i < cellCountX; i++) {
            for (int j = 0; j < cellCountY; j++) {
                if (board[i][j].value == -1)
                    continue;
                else if (!board[i][j].cracked)
                    return;
            }
        }
        System.out.printf("WIN!!!\n");
        int opt = JOptionPane.showConfirmDialog(this, "You found all the bomb!\nPlay Again?", "YOU WIN!", JOptionPane.YES_NO_OPTION);
        if (opt == 1)
            System.exit(0);
        MineScene ms = (MineScene) getParent();
        ms.Kill();
        MenuScene mm = new MenuScene(ms.window);
        mm.Load();
    }

    private void setMines() {
        Random rand = new Random(100);
        for (int i = 0; i < mineNo; i++){
            int x = rand.nextInt(cellCountX);
            int y = rand.nextInt(cellCountY);
            while(board[x][y].value == -1){
                x = rand.nextInt(cellCountX);
                y = rand.nextInt(cellCountY);
            }
            board[x][y].value = -1;

            for (int j = x - 1; j <= x + 1; j++) {
                for (int k = y - 1; k <= y + 1; k++){
                    if(!((0 <= j && j < cellCountX) && ( 0 <= k && k < cellCountY)))
                        continue;
                    if(board[j][k].value != -1) {
                        board[j][k].value += 1;
                    }
                }
            }
        }
    }
    private void GameOver() {
        for (int i = 0; i < cellCountX; i++) {
            for (int j = 0; j < cellCountY; j++) {
                if(board[i][j].value == -1) {
                    board[i][j].setBackground(Color.red);
                    board[i][j].setText("X");
                    repaint();
                }
            }
        }
        System.out.println("LOOSE!!");
        int opt = JOptionPane.showConfirmDialog(this, "BOOM! You blew up the bomb.\nPlay Again?", "GAME OVER!", JOptionPane.YES_NO_OPTION);
        if (opt == 1)
            System.exit(0);
        MineScene ms = (MineScene) getParent();
        ms.Kill();
        MenuScene mm = new MenuScene(ms.window);
        mm.Load();
    }
    MineBoard(int cellCountX, int cellCountY, int mineNo, int mineSize, Scene scn){
        this.cellCountX = cellCountX;
        this.cellCountY = cellCountY;
        this.mineNo = mineNo;
        this.mineSize = mineSize;

        board = new MineCell[cellCountX][cellCountY];
        setLayout(null);

        setSize(mineSize * cellCountX, mineSize * cellCountY);
        setLocation(0, 0);

        for (int i = 0; i < cellCountX; i++) {
            for (int j = 0; j < cellCountY; j++) {
                add(board[i][j] = new MineCell(mineSize, i * mineSize, j * mineSize, this));
            }
        }
        setMines();
        setVisible(true);
        Subscribe(scn, this);
    }
}

