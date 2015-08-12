package org.datanucleus.store.types.java8.containers;

import java.util.List;
import java.util.Optional;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.store.types.ElementContainerAdapter;
import org.datanucleus.store.types.TypeManager;
import org.datanucleus.store.types.containers.CollectionHandler;

public class OptionalHandler extends CollectionHandler<Optional>
{

    @Override
    public Optional newContainer()
    {
        return Optional.empty();
    }

    @Override
    public ElementContainerAdapter<Optional> getAdapter(Optional container)
    {
        return new OptionalAdapter(container);
    }

    @Override
    public Optional newContainer(Object... values)
    {
        return Optional.ofNullable(values[0]);
    }

    @Override
    public void populateMetaData(MetaDataManager mmgr, AbstractMemberMetaData mmd)
    {
        super.populateMetaData(mmgr, mmd);
        mmd.getCollection().setSingleElement(true);

        // Get columns defined metadata - not visible
        List<ColumnMetaData> columns = new AbstractMemberMetaData(mmd.getParent(), mmd)
        {
            private static final long serialVersionUID = 1L;

            public List<ColumnMetaData> getColumns()
            {
                return columns;
            }
        }.getColumns();

        if (columns == null || columns.size() == 0)
        {
            // Optional should allow nullable by default
            ColumnMetaData colmd = new ColumnMetaData();
            colmd.setAllowsNull(Boolean.TRUE);
            mmd.addColumn(colmd);
        }
    }

    /**
     * Default fetch group is defined by the type of the element.
     */
    @Override
    public boolean isDefaultFetchGroup(ClassLoaderResolver clr, MetaDataManager mmgr, AbstractMemberMetaData mmd)
    {
        String elementTypeName = mmd.getCollection().getElementType();
        Class elementClass = clr.classForName(elementTypeName);
        TypeManager typeMgr = mmgr.getNucleusContext().getTypeManager();

        return typeMgr.isDefaultFetchGroup(elementClass);
    }
}
