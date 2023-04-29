package observer;

import java.util.ArrayList;

public interface Observable {
    public ArrayList<Observer> observers = new ArrayList<Observer>();
    
    public default void attach(Observer observer) {
        observers.add(observer);
    }

    public default void detach(Observer observer) {
        observers.remove(observer);
    }

    public default void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
