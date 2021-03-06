package de.elnarion.ddlutils.alteration;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.ArrayList;
import java.util.List;

import de.elnarion.ddlutils.model.Database;
import de.elnarion.ddlutils.model.ForeignKey;
import de.elnarion.ddlutils.model.Reference;
import de.elnarion.ddlutils.model.Table;

/**
 * The base class for changes affecting foreign keys.
 * 
 * @version $Revision: $
 */
public abstract class ForeignKeyChangeImplBase extends    TableChangeImplBase
                                               implements ForeignKeyChange
{
    /** List of pairs of local and corresponding foreign column names that make up the foreign key. */
    private List<Pair> _referenceColumnNames = new ArrayList<>();

    /**
     * Creates a new change object.
     * 
     * @param tableName  The name of the table that owns the foreign key
     * @param foreignKey The foreign key; note that this change object will not maintain a reference
     *                   to the foreign key object
     */
    public ForeignKeyChangeImplBase(String tableName, ForeignKey foreignKey)
    {
        super(tableName);
        for (int refIdx = 0; refIdx < foreignKey.getReferenceCount(); refIdx++)
        {
            Reference ref = foreignKey.getReference(refIdx);

            _referenceColumnNames.add(new Pair(ref.getLocalColumnName(), ref.getForeignColumnName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public ForeignKey findChangedForeignKey(Database model, boolean caseSensitive)
    {
    	Table table = findChangedTable(model, caseSensitive);

    	if (table != null)
    	{
            for (int fkIdx = 0; fkIdx < table.getForeignKeyCount(); fkIdx++)
            {
                ForeignKey curFk = table.getForeignKey(fkIdx);

                if (curFk.getReferenceCount() == _referenceColumnNames.size())
                {
                    for (int refIdx = 0; refIdx < curFk.getReferenceCount(); refIdx++)
                    {
                        Reference ref      = curFk.getReference(refIdx);
                        Pair      colNames = (Pair)_referenceColumnNames.get(refIdx);

                        if (caseSensitive)
                        {
                            if (ref.getLocalColumnName().equals((String)colNames.getFirst()) &&
                                ref.getForeignColumnName().equals((String)colNames.getSecond()))
                            {
                                return curFk;
                            }
                        }
                        else
                        {
                            if (ref.getLocalColumnName().equalsIgnoreCase((String)colNames.getFirst()) &&
                                ref.getForeignColumnName().equalsIgnoreCase((String)colNames.getSecond()))
                            {
                                return curFk;
                            }
                        }
                    }
                }
            }
    	}
        return null;
    }
}
