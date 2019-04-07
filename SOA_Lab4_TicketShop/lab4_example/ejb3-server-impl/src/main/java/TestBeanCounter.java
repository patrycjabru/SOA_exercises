package pl.agh.kis.soa.ejb3.server.impl;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Singleton
@Local
public class TestBeanCounter {
    private long counter = 0;

    public void increment() {
        counter++;
    }

    public long getNumber() {
        return counter;
    }
}
