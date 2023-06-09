import javax.swing.*;

//Implement this in order to add component to scene's component list.
//It will be added in Load() method in Scene class.
interface ICompAddable {
    default void Subscribe(Scene scene, JComponent self) {
        scene.components.add(self);
    }
}

