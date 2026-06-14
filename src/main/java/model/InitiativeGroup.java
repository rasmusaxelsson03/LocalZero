package model;

import java.util.ArrayList;
import java.util.List;

public class InitiativeGroup {
    private String title;
    private List<Initiative> children = new java.util.concurrent.CopyOnWriteArrayList<>();

    public InitiativeGroup(String title){
        this.title = title;
    }

    public List<Initiative> getChildren() {
        return children;
    }

    public void add(Initiative initiative){
        children.add(initiative);
    }

    public void remove(Initiative initiative){
        children.remove(initiative);
    }

    public String getTitle() {
        return title;
    }

    public double getCarbonSavings(){
        return children.stream().mapToDouble(Initiative::getCarbonSavings).sum();
    }
}
