package pl.agh.kis.soa;

public class MaleSurveyDetails implements GenderSurveyDetails{
    int chestCircuit;
    int waitsCircuit;

    public int getChestCircuit() {
        return chestCircuit;
    }

    public void setChestCircuit(int chestCircuit) {
        this.chestCircuit = chestCircuit;
    }

    public int getWaitsCircuit() {
        return waitsCircuit;
    }

    public void setWaitsCircuit(int waitsCircuit) {
        this.waitsCircuit = waitsCircuit;
    }
}
