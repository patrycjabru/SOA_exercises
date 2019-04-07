package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "draw")
@RequestScoped
public class Draw {
    private static String randomNumber = getRandomNumber();

    private static int[] visitCounter = new int[] {0,0,0,0,0};

    public String check(String selectedNumber) {
        int selectedNumberAsInt = Integer.parseInt(selectedNumber);
        visitCounter[selectedNumberAsInt-1]++;
        if (selectedNumber.equals(randomNumber)) {
            randomNumber = getRandomNumber();
            return "win";
        }
        return selectedNumber;
    }

    private static String getRandomNumber() {
        String number = Integer.toString((int)(Math.random() * 5 + 1));
        return number;
    }

    public int getVisitCounter(int index) {
        return visitCounter[index-1];
    }
}
