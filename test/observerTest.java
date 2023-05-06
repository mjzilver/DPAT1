import observer.Observable;
import observer.Observer;

import org.junit.Test;
import static org.junit.Assert.*;

public class observerTest {
    private class mockObservable implements Observable {}

    private class mockObserver implements Observer {
        public boolean updated = false;
        @Override
        public void update() {
            this.updated = true;
        }
    }
    
    // test the observer pattern
    @Test
    public void testObserver() {
        Observable observable = new mockObservable();
        mockObserver observer = new mockObserver();
        assertFalse(observer.updated);
        observable.attach(observer);
        observable.notifyObservers();
        assertTrue(observer.updated);
    }
}
