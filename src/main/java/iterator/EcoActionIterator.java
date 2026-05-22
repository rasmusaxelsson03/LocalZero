package iterator;

import model.EcoAction;
import java.util.Iterator;
import java.util.List;

public class EcoActionIterator implements Iterator<EcoActionIterator> {
    List<EcoAction> ecoActions;
    int index;

    public EcoActionIterator(List<EcoAction> ecoActions) {
        this.ecoActions = ecoActions;
    }

    @Override
    public boolean hasNext() {
        if (index < ecoActions.size()) {
            return true;
        }
        return false;
    }

    @Override
    public EcoAction next() {
        if (!hasNext()) {
            return null;
        }
        return ecoActions.get(index);
    }
}
