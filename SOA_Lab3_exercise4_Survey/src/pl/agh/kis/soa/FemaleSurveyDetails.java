package pl.agh.kis.soa;

public class FemaleSurveyDetails implements GenderSurveyDetails{
    int bustCircuit;
    int cupSize;
    int waistCircuit;
    int hipsCircuit;

    public int getBustCircuit() {
        return bustCircuit;
    }

    public void setBustCircuit(int bustCircuit) {
        this.bustCircuit = bustCircuit;
    }

    public int getCupSize() {
        return cupSize;
    }

    public void setCupSize(int cupSize) {
        this.cupSize = cupSize;
    }

    public int getWaistCircuit() {
        return waistCircuit;
    }

    public void setWaistCircuit(int waistCircuit) {
        this.waistCircuit = waistCircuit;
    }

    public int getHipsCircuit() {
        return hipsCircuit;
    }

    public void setHipsCircuit(int hipsCircuit) {
        this.hipsCircuit = hipsCircuit;
    }
}
