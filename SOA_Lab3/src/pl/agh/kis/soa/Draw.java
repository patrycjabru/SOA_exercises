package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "draw")
@RequestScoped
public class Draw {

    public String check() {
        if (Math.random() < 0.2)
            return "OK";
        else
            return "NOT_OK";
    }
}
