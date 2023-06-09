import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

abstract class Scene extends JPanel implements IScene {
    JFrame window;
    LinkedList<Component> components;

    Scene() {
        components = new LinkedList<>();
    }

    protected int CenterX() {
        return window.getBounds().getSize().width / 2;
    }

    protected int CenterY() {
        return window.getBounds().getSize().height / 2;
    }

    @Override
    //add every ICompAddable to scene, then add the scene to window
    public void Load() {
        for (Component c : components) {
            add(c);
            this.revalidate();
            this.repaint();
        }
        setVisible(true);
        window.add(this);

        window.revalidate();
        window.repaint();
    }

    @Override
    public void Hide() {
        for (Component c : components) {
            c.setVisible(false);
        }
        this.setVisible(false);
    }

    @Override
    public void Kill() {
        window.remove(this);
    }
}
