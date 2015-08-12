package org.datanucleus.store.types.java8.containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import org.datanucleus.store.types.ElementContainerAdapter;

public class OptionalAdapter extends ElementContainerAdapter<Optional>
{
    public OptionalAdapter(Optional optional)
    {
        super(optional);
    }

    @Override
    public void clear()
    {
        setContainer(Optional.empty());
    }

    @Override
    public Iterator<Object> iterator()
    {
        return container.isPresent() ? new OptionalIterator(container.get()) : Collections.emptyIterator();
    }

    @Override
    public void add(Object newElement)
    {
        setContainer(Optional.ofNullable(newElement));
    }

    @Override
    public void remove(Object element)
    {
        setContainer(Optional.empty());
    }

    class OptionalIterator implements Iterator<Object>
    {
        private Object value;

        private boolean hasNext = true;

        public OptionalIterator(Object value)
        {
            super();
            this.value = value;
        }

        @Override
        public boolean hasNext()
        {
            return hasNext;
        }

        @Override
        public Object next()
        {
            if (hasNext)
            {
                hasNext = false;
                return value;
            }
            throw new java.util.NoSuchElementException();
        }
    }
}
