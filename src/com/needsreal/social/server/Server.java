package com.needsreal.social.server;

public class Server
{
	private static ServerManager serverMgr = null;

	// not possible to instantiate it
	private Server () {}

	public static ServerManager getServerMgr ()
	{
		if (serverMgr == null)
			serverMgr = new ServerManager ();
		return serverMgr;
	}
}
