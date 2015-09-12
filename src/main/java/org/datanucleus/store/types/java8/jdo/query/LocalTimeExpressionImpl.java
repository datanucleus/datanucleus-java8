/**********************************************************************
Copyright (c) 2015 Andy Jefferson and others. All rights reserved.
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
package org.datanucleus.store.types.java8.jdo.query;

import java.time.LocalTime;

import javax.jdo.query.NumericExpression;
import javax.jdo.query.PersistableExpression;

import org.datanucleus.api.jdo.query.ComparableExpressionImpl;
import org.datanucleus.api.jdo.query.ExpressionType;
import org.datanucleus.api.jdo.query.NumericExpressionImpl;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;

/**
 * Implementation of a Time expression.
 */
public class LocalTimeExpressionImpl extends ComparableExpressionImpl<LocalTime> implements LocalTimeExpression
{
    public LocalTimeExpressionImpl(PersistableExpression parent, String name)
    {
        super(parent, name);
    }

    public LocalTimeExpressionImpl(Class<LocalTime> cls, String name, ExpressionType type)
    {
        super(cls, name, type);
    }

    public LocalTimeExpressionImpl(Expression queryExpr)
    {
        super(queryExpr);
    }

    /* (non-Javadoc)
     * @see org.datanucleus.store.types.jdo.query.TimeExpression#getHour()
     */
    public NumericExpression<Integer> getHour()
    {
        org.datanucleus.query.expression.Expression invokeExpr = new InvokeExpression(queryExpr, "getHour", null);
        return new NumericExpressionImpl<Integer>(invokeExpr);
    }

    /* (non-Javadoc)
     * @see org.datanucleus.store.types.jdo.query.TimeExpression#getMinute()
     */
    public NumericExpression<Integer> getMinute()
    {
        org.datanucleus.query.expression.Expression invokeExpr = new InvokeExpression(queryExpr, "getMinute", null);
        return new NumericExpressionImpl<Integer>(invokeExpr);
    }

    /* (non-Javadoc)
     * @see org.datanucleus.store.types.jdo.query.TimeExpression#getSecond()
     */
    public NumericExpression<Integer> getSecond()
    {
        org.datanucleus.query.expression.Expression invokeExpr = new InvokeExpression(queryExpr, "getSecond", null);
        return new NumericExpressionImpl<Integer>(invokeExpr);
    }
}