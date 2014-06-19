/**********************************************************************
Copyright (c) 2014 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package org.datanucleus.store.types.java8.wrappers.backed;

import java.util.Spliterator;
import java.util.stream.Stream;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;

/** 
 * A mutable second-class wrapper object. Extends the standard DataNucleus wrapper adding on Java8 methods.
 */
public class Collection extends org.datanucleus.store.types.wrappers.backed.Collection
{
    /**
     * Constructor.
     * @param op The ObjectProvider for this collection.
     * @param mmd Metadata for the member
     */
    public Collection(ObjectProvider op, AbstractMemberMetaData mmd)
    {
        super(op, mmd);
    }

    /**
     * Constructor used when creating a Collection for "Map.values" with specified backing store.
     * @param ownerOP ObjectProvider for the owning object
     * @param mmd Metadata for the member
     * @param allowNulls Whether nulls are allowed
     * @param backingStore The backing store
     */
    Collection(ObjectProvider ownerOP, AbstractMemberMetaData mmd, boolean allowNulls, CollectionStore backingStore)
    {
        super(ownerOP, mmd, allowNulls, backingStore);
    }

    @Override
    public Spliterator spliterator()
    {
        if (backingStore != null && useCache && !isCacheLoaded)
        {
            loadFromStore();
        }
        // TODO If using backing store yet not caching, then this will fail
        return delegate.spliterator();
    }

    @Override
    public Stream stream()
    {
        if (backingStore != null && useCache && !isCacheLoaded)
        {
            loadFromStore();
        }
        // TODO If using backing store yet not caching, then this will fail
        return delegate.stream();
    }

    @Override
    public Stream parallelStream()
    {
        if (backingStore != null && useCache && !isCacheLoaded)
        {
            loadFromStore();
        }
        // TODO If using backing store yet not caching, then this will fail
        return delegate.parallelStream();
    }
}