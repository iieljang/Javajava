import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuScene extends Scene {
    MenuButton newMPButton;
    MenuButton newSPButton;

    MenuScene(JFrame pr) {
        window = pr;
        setLocation(0, 0);
        setSize(window.getSize());
        this.setOpaque(true);
        this.setBackground(Color.lightGray);

        newSPButton = new MenuButton("NEW SINGLEPLAYER", CenterX(), CenterY(), this);
        newMPButton = new MenuButton("NEW MULTIPLAYER", CenterX(), CenterY(), this);
    }

    @Override
    public void Load() {
        super.Load();

        newSPButton.addActionListener(e -> {
            Hide();
            Kill();
            MineScene ms = new MineScene(window);
            ms.Load();
        });
    }

    @Override
    public void Kill() {
        window.remove(this);
    }
}
