package com.company;

import java.util.ArrayList;
import java.util.List;

interface Notifier {
    void addObserver(Observer obs);
    void notifyObserver();
}

class Public1 implements Notifier {
    private int subsCounter;
    private List observers;

    public Public1() {
        observers = new ArrayList();
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer obs = (Observer) observers.get(i);
            obs.update(subsCounter);
        }
    }

    public void changeData(int subsCounter) {
        this.subsCounter = subsCounter;
        notifyObserver();
    }
}

interface Observer {
    void update(int subsCounter);
}

class Subscriber implements Observer {
    private Notifier[] notifiers;
    private int subsCounter;

    public Subscriber(Notifier... notifiers) {
        this.notifiers = notifiers;
        for (Notifier notif : notifiers) {
            notif.addObserver(this);
        }
    }

    public void update(int subsCounter) {
        this.subsCounter = subsCounter;
        show();
    }

    public void show() {
        System.out.println("Количество подписчиков изменилось: " + subsCounter);
    }
}

public class Main {
    public static void main(String[] args) {
        Public1 news = new Public1();
        Public1 games = new Public1();
        Public1 people = new Public1();

        Subscriber mySubscriber1 = new Subscriber(news, games, people);
        Subscriber mySubscriber2 = new Subscriber(news, people);
        Subscriber mySubscriber3 = new Subscriber(news, games);

        System.out.println("Разосланы оповещения об изменения в games");
        games.changeData(4553);

        System.out.println("\nРазосланы оповещения об изменения в news");
        news.changeData(132024);

        System.out.println("\nРазосланы оповещения об изменения в people");
        people.changeData(3244);

    }
}