import javax.swing.*;

class GameWindow extends JFrame {
    GameWindow(int wSizeX,int wSizeY){
        setSize(wSizeX, wSizeY);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MenuScene mm = new MenuScene(this);
        mm.Load();
        setVisible(true);
    }
}

public class App {
    public static void main(String[] args) {
        GameWindow gm = new GameWindow(800, 800);
    }
}
