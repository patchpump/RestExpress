/*
 * Copyright 2009, Strategic Gains, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
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

package org.restexpress.common.util;

import java.text.ParseException;

/**
 * Defines an interface to convert an object to a text string and back.
 * 
 * @author toddf
 * @since Nov 13, 2009
 */
public interface TextAdapter<T>
{
	public T parse(String value)
	throws ParseException;
	
	public String format(T value);
}