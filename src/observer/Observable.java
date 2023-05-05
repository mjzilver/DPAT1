package observer;

import java.util.ArrayList;

public interface Observable {
    public final ArrayList<Observer> observers = new ArrayList<>();
    
    default void attach(Observer observer) {
        observers.add(observer);
    }

    default void detach(Observer observer) {
        observers.remove(observer);
    }

    default void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
