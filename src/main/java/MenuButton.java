import javax.swing.*;

class MenuButton extends JButton implements ICompAddable {
    MenuButton(String text, int x, int y, Scene scn) {
        Subscribe(scn, this);
        setText(text);
        setSize(70, 35);
        setLocation(x, y);
        setVisible(true);
    }
}
