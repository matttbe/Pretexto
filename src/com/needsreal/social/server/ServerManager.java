package com.needsreal.social.server;

public class ServerManager
{
	private ServerConnexion connexion;
	private ServerRequests requests;

	public ServerManager ()
	{
		connexion = new ServerConnexion ();
		requests = new ServerRequests ();
		connexion.test ();
	}

	public ServerRequests getServerRequests ()
	{
		return this.requests;
	}
}
