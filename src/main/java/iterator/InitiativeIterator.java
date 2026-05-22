package iterator;

import model.Initiative;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InitiativeIterator implements Iterator<Initiative> {
    private final List<Initiative> initiatives;
    private int index = 0;

    public InitiativeIterator(List<Initiative> initiatives) {
        this.initiatives = initiatives;
    }

    @Override
    public boolean hasNext() {
        return index < initiatives.size();
    }

    @Override
    public Initiative next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return initiatives.get(index++);
    }

    public double totalSavings() {
        return initiatives.stream().mapToDouble(Initiative::getCarbonSavings).sum();
    }
}
