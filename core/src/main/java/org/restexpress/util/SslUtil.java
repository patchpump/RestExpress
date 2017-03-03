package org.restexpress.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

public class SslUtil
{
	public static SSLContext loadContext(String keyStore,
			char[] filePassword, char[] keyPassword) throws Exception
	{
		FileInputStream fin = null;

		try
		{
			KeyStore ks = KeyStore.getInstance("JKS");
			fin = new FileInputStream(keyStore);
			ks.load(fin, filePassword);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, keyPassword);

			SSLContext context = SSLContext.getInstance("TLS");
			context.init(kmf.getKeyManagers(), null, null);
			return context;
		}
		finally
		{
			if (null != fin)
			{
				try
				{
					fin.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}

}
