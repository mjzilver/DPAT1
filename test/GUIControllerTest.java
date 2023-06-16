import engine.GUIController;
import view.FinalViewDecorator;
import view.HelperViewDecorator;

import org.junit.Test;
import static org.junit.Assert.*;

public class GUIControllerTest {
    private GUIController guiController;

    @Test
    public void testSwitchState() {
        guiController = new GUIController();
        guiController.switchState();
        assertTrue(guiController.getView() instanceof HelperViewDecorator);
        guiController.switchState();
        assertTrue(guiController.getView() instanceof FinalViewDecorator);
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
