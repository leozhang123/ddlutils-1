package org.apache.ddlutils.platform.db2;

/*
 * Copyright 2006 The Apache Software Foundation.
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

import org.apache.ddlutils.Platform;

/**
 * The SQL Builder for DB2 v8 and above.
 * 
 * @version $Revision: $
 */
public class Db2v8Builder extends Db2Builder
{
    /**
     * Creates a new builder instance.
     * 
     * @param platform The plaftform this builder belongs to
     */
    public Db2v8Builder(Platform platform)
    {
        super(platform);
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxConstraintNameLength()
    {
        // In non-delimited identifier mode we can only use 18 characters apparently
        return getPlatform().isDelimitedIdentifierModeOn() ? super.getMaxConstraintNameLength() : 18;
    }

}
