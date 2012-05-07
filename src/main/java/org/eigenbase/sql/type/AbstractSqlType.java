/*
// Licensed to DynamoBI Corporation (DynamoBI) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  DynamoBI licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at

//   http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
*/
package org.eigenbase.sql.type;

import java.io.*;

import org.eigenbase.reltype.*;


/**
 * Abstract base class for SQL implementations of {@link RelDataType}.
 *
 * @author jhyde
 * @version $Id$
 */
public abstract class AbstractSqlType
    extends RelDataTypeImpl
    implements Cloneable,
        Serializable
{
    //~ Instance fields --------------------------------------------------------

    protected final SqlTypeName typeName;
    protected boolean isNullable;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates an AbstractSqlType.
     *
     * @param typeName Type name
     * @param isNullable Whether nullable
     * @param fields Fields of type, or null if not a record type
     */
    protected AbstractSqlType(
        SqlTypeName typeName,
        boolean isNullable,
        RelDataTypeField [] fields)
    {
        super(fields);
        this.typeName = typeName;
        this.isNullable = isNullable || (typeName == SqlTypeName.NULL);
    }

    //~ Methods ----------------------------------------------------------------

    // implement RelDataType
    public SqlTypeName getSqlTypeName()
    {
        return typeName;
    }

    // implement RelDataType
    public boolean isNullable()
    {
        return isNullable;
    }

    // implement RelDataType
    public RelDataTypeFamily getFamily()
    {
        return SqlTypeFamily.getFamilyForSqlType(typeName);
    }

    // implement RelDataType
    public RelDataTypePrecedenceList getPrecedenceList()
    {
        RelDataTypePrecedenceList list =
            SqlTypeExplicitPrecedenceList.getListForType(this);
        if (list != null) {
            return list;
        }
        return super.getPrecedenceList();
    }
}

// End AbstractSqlType.java