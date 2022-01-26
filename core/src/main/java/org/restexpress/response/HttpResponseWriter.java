/*
 * Copyright 2010, eCollege, Inc.  All rights reserved.
 */
package org.restexpress.response;

import org.restexpress.Request;
import org.restexpress.Response;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author toddf
 * @since Aug 26, 2010
 */
public interface HttpResponseWriter
{
	public void write(ChannelHandlerContext ctx, Request request, Response response);
}
