/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2024 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.altibase.model;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.ext.generic.model.GenericStructContainer;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCUtils;
import org.jkiss.dbeaver.model.meta.Property;
import org.jkiss.utils.ByteNumberFormat;

public class AltibaseMemoryModule extends AltibaseGlobalObject {

    private String name;
    private long allocSizeInBytes;
    private long allocCount;

    AltibaseMemoryModule(GenericStructContainer parent, JDBCResultSet dbResult) {
        super((AltibaseDataSource) parent.getDataSource(), true);

        this.name = JDBCUtils.safeGetString(dbResult, "NAME");
        this.allocSizeInBytes = JDBCUtils.safeGetLong(dbResult, "ALLOC_SIZE");
        this.allocCount = JDBCUtils.safeGetLong(dbResult, "ALLOC_COUNT");
    }

    @NotNull
    @Property(viewable = true, order = 1)
    public String getName() {
        return name;
    }

    @Property(viewable = true, order = 2, formatter = ByteNumberFormat.class)
    public long getAllocSize() {
        return allocSizeInBytes;
    }

    @Property(viewable = true, order = 3)
    public long getAllocCount() {
        return allocCount;
    }
}