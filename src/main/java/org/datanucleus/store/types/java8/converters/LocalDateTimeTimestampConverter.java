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
package org.datanucleus.store.types.java8.converters;

import java.sql.Timestamp;
import java.util.Calendar;
import java.time.LocalDateTime;

import org.datanucleus.store.types.converters.TypeConverter;

/**
 * Class to handle the conversion between java.time.LocalDateTime and java.sql.Timestamp
 */
public class LocalDateTimeTimestampConverter implements TypeConverter<LocalDateTime, Timestamp>
{
    private static final long serialVersionUID = -4426520457647540543L;

    public LocalDateTime toMemberType(Timestamp ts)
    {
        if (ts == null)
        {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        return LocalDateTime.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH),
            cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)*1000000);
    }

    public Timestamp toDatastoreType(LocalDateTime datetime)
    {
        if (datetime == null)
        {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(datetime.getYear(), datetime.getMonth().ordinal(), datetime.getDayOfMonth(),
            datetime.getHour(), datetime.getMinute(), datetime.getSecond());
        return new Timestamp(cal.getTimeInMillis());
    }
}
