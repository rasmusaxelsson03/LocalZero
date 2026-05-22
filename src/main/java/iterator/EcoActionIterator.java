package iterator;

import model.EcoAction;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class EcoActionIterator implements Iterator<EcoAction> {
    private final List<EcoAction> ecoActions;
    private int index = 0;

    public EcoActionIterator(List<EcoAction> ecoActions) {
        this.ecoActions = ecoActions;
    }

    @Override
    public boolean hasNext() {
        return index < ecoActions.size();
    }

    @Override
    public EcoAction next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return ecoActions.get(index++);
    }

    public double totalSavings() {
        return ecoActions.stream().mapToDouble(EcoAction::getCarbonSaved).sum();
    }
}
