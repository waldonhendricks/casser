/*
 *      Copyright (C) 2015 The Casser Authors
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
package com.noorq.casser.config;

import java.lang.reflect.Method;
import java.util.function.Function;

public enum GetterMethodDetector implements Function<Method, Boolean> {

	INSTANCE;
	
	@Override
	public Boolean apply(Method method) {
		
		if (method == null) {
			throw new IllegalArgumentException("empty parameter");
		}
		
		if (method.getParameterCount() != 0 || method.getReturnType() == void.class) {
			return false;
		}
		
		return true;
	    
	}

}
