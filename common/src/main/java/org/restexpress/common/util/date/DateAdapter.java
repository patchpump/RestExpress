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

package org.restexpress.common.util.date;

import static org.restexpress.common.util.date.DateAdapterConstants.DATE_INPUT_FORMATS;
import static org.restexpress.common.util.date.DateAdapterConstants.DATE_OUTPUT_FORMAT;

import java.text.ParseException;
import java.util.Date;

import org.restexpress.common.util.AdapterCallback;
import org.restexpress.common.util.TextAdapter;


/**
 * Handles Date conversion from/to String via a DateFormatProcessor.  The default input and output formats
 * are discussed in DateAdapterConstants.
 * 
 * @author toddf
 * @since Nov 13, 2009
 * @see DateFormatProcessor
 * @see DateAdapterConstants
 */
public class DateAdapter
implements TextAdapter<Date>
{
	// SECTION: INSTANCE VARIABLES

	private DateFormatProcessor processor;
	private AdapterCallback<String> preParseCallback = null;
	private AdapterCallback<Date> postParseCallback = null;
	private AdapterCallback<Date> preFormatCallback = null;
	private AdapterCallback<String> postFormatCallback = null;

	
	// SECTION: CONSTRUCTOR

	public DateAdapter()
	{
		this(DATE_OUTPUT_FORMAT, DATE_INPUT_FORMATS);
	}
	
	public DateAdapter(String outputFormat, String... inputFormats)
	{
		this.processor = new DateFormatProcessor(outputFormat, inputFormats);
	}

	
	// SECTION: CALLBACK ACCESSING/MUTATING

	public void setPreParseCallback(AdapterCallback<String> preParseCallback)
	{
		this.preParseCallback = preParseCallback;
	}

	public void setPostParseCallback(AdapterCallback<Date> postParseCallback)
	{
		this.postParseCallback = postParseCallback;
	}

	public void setPreFormatCallback(AdapterCallback<Date> preFormatCallback)
	{
		this.preFormatCallback = preFormatCallback;
	}

	
	// SECTION: FORMATTING

	public void setPostFormatCallback(AdapterCallback<String> postFormatCallback) {
		this.postFormatCallback = postFormatCallback;
	}

	@Override
	public Date parse(String dateString)
	throws ParseException
	{
		String preprocessed = beforeParse(dateString);
		Date date = processor.parse(preprocessed);
		return afterParse(date);
	}
	
	@Override
	public String format(Date date)
	{
		Date preprocessed = beforeFormat(date);
		String string = processor.format(preprocessed);
		return afterFormat(string);
	}
	
	
	// SECTION: PRE-PROCESSING
	
	protected String beforeParse(String string)
	{
		if (preParseCallback == null)
		{
			return string;
		}
		
		return preParseCallback.process(string);
	}
	
	protected Date beforeFormat(Date date)
	{
		if (preFormatCallback == null)
		{
			return date;
		}
		
		return preFormatCallback.process(date);
	}
	
	
	// SECTION: POST-PROCESSING
	
	protected Date afterParse(Date date)
	{
		if (postParseCallback == null)
		{
			return date;
		}
		
		return postParseCallback.process(date);
	}
	
	protected String afterFormat(String string)
	{
		if (postFormatCallback == null)
		{
			return string;
		}
		
		return postFormatCallback.process(string);
	}
}
