package iterator;

import model.Initiative;
import java.util.Iterator;
import java.util.List;

public class InitiativeIterator implements Iterator<Initiative> {
    List<Initiative> initiatives;
    int index;

    public InitiativeIterator(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    @Override
    public boolean hasNext() {
        if (index < initiatives.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Initiative next() {
        if (!hasNext()) {
            return null;
        }
        return initiatives.get(index++);
    }
}
