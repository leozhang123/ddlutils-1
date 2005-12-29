package org.apache.ddlutils.io.postgresql;

/*
 * Copyright 1999-2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.ddlutils.io.DatatypesTestBase;
import org.apache.ddlutils.platform.postgresql.PostgreSqlPlatform;

/**
 * Performs the roundtrip datatype tests against a PostgreSql database.
 * 
 * @author Thomas Dudziak
 * @version $Revision: 289996 $
 */
public class TestPostgreSqlDatatypes extends DatatypesTestBase
{
    /**
     * {@inheritDoc}
     */
    protected String getPlatformName()
    {
        return PostgreSqlPlatform.DATABASENAME;
    }
}