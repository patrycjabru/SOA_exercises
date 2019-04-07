package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@ManagedBean(name = "commercials")
@ApplicationScoped
public class Commercials implements Serializable {
    private HashMap<String,Integer> commercialsCounters;
    private HashMap<String,String> commercialsLinks;
    private Map.Entry<String, Integer> randomCommercial;

    public Commercials(){
        if (commercialsCounters ==null)
            init();
    }

    private void init() {
        commercialsCounters = new HashMap<>();
        commercialsCounters.put("/images/pepsi.jpg",0);
        commercialsCounters.put("/images/fanta.jpg",0);
        commercialsCounters.put("/images/mountainDew.jpg",0);

        commercialsLinks = new HashMap<>();
        commercialsLinks.put("/images/pepsi.jpg","https://www.pepsi.pl/");
        commercialsLinks.put("/images/fanta.jpg","https://www.coca-cola.pl/fanta/pl/home/");
        commercialsLinks.put("/images/mountainDew.jpg","http://www.mountaindew.com/");

    }

    public String getRandomCommercial() {
        Random random = new Random();
        int randomId = Math.abs(random.nextInt(commercialsCounters.size()));
        int counter = 0;
        for (Map.Entry<String, Integer> entry : commercialsCounters.entrySet()) {
            if (counter == randomId) {
                randomCommercial = entry;
                break;
            }
            counter++;
        }
        return randomCommercial.getKey();
    }

    public void redirect(){
        String commercialPath = randomCommercial.getKey();
        Integer counter = commercialsCounters.get(commercialPath);
        counter++;
        commercialsCounters.put(commercialPath,counter);
        String link = commercialsLinks.get(commercialPath);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getCommercialsCounters() {
        return commercialsCounters;
    }

    public void setCommercialsCounters(HashMap<String, Integer> commercialsCounters) {
        this.commercialsCounters = commercialsCounters;
    }

    public HashMap<String, String> getCommercialsLinks() {
        return commercialsLinks;
    }

    public void setCommercialsLinks(HashMap<String, String> commercialsLinks) {
        this.commercialsLinks = commercialsLinks;
    }

    public void setRandomCommercial(Map.Entry<String, Integer> randomCommercial) {
        this.randomCommercial = randomCommercial;
    }

    public Integer getCounter() {
        return randomCommercial.getValue();
    }

    public void setCounter(Integer counter) {

    }
}
