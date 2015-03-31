/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.noorq.casser.core.operation;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.noorq.casser.core.AbstractSessionOperations;
import com.noorq.casser.mapping.CasserMappingEntity;
import com.noorq.casser.mapping.CasserMappingProperty;
import com.noorq.casser.mapping.value.BeanColumnValueProvider;

public final class UpsertOperation extends AbstractEntityOperation<ResultSet, UpsertOperation> {

	private final Insert insert;
	
	public UpsertOperation(AbstractSessionOperations sessionOperations, CasserMappingEntity entity, Object pojo) {
		super(sessionOperations);
		
		this.insert = QueryBuilder.insertInto(entity.getName().toCql());
		
		for (CasserMappingProperty prop : entity.getMappingProperties()) {
			
			Object value = BeanColumnValueProvider.INSTANCE.getColumnValue(pojo, -1, prop);
			
			value = sessionOps.getValuePreparer().prepareColumnValue(value, prop);
			
			if (value != null) {
				insert.value(prop.getColumnName(), value);
			}
			
		}
		
		
	}

	@Override
	public BuiltStatement buildStatement() {
		return insert;
	}

	@Override
	public ResultSet transform(ResultSet resultSet) {
		return resultSet;
	}
	
	
	
}
