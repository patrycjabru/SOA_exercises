package pl.agh.kis.soa.ejb3.server.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class TestAddBean {

    public int add(int a, int b) {
        return a + b;
    }
}
