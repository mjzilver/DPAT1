import engine.GUIController;
import state.BaseState;
import state.FinalNumberState;
import state.HelperNumberState;
import view.FinalViewDecorator;
import view.HelperViewDecorator;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class GUIControllerTest {
    private GUIController guiController;

    @Test
    public void testSwitchState() {
        try {
            Field field = GUIController.class.getDeclaredField("state");
            field.setAccessible(true);

            guiController = new GUIController();


            guiController.switchState();
            assertTrue(guiController.getView() instanceof HelperViewDecorator);
            BaseState state = (BaseState) field.get(guiController);
            assertTrue(state instanceof HelperNumberState);

            guiController.switchState();
            assertTrue(guiController.getView() instanceof FinalViewDecorator);
            state = (BaseState) field.get(guiController);
            assertTrue(state instanceof FinalNumberState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBoardController() {
        guiController = new GUIController();
        assertNotNull(guiController.getBoardController());
    }

    @Test
    public void testGetView() {
        guiController = new GUIController();
        assertNotNull(guiController.getView());
    }
}
