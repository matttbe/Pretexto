package com.needsreal.social.socialnetwork;

public abstract class AbstractSocialNetwork implements SocialNetworkInterface
{
	protected String url;
	protected String login;
	protected String title;
	protected int resID;
	protected AbstractAuthSocialNetwork auth;

	public AbstractSocialNetwork (String url, String login, String title)
	{
		this.url = url;
		this.login = login;
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl ()
	{
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl (String url)
	{
		this.url = url;
	}

	/**
	 * @return the login
	 */
	public String getLogin ()
	{
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin (String login)
	{
		this.login = login;
	}

	/**
	 * @return the title
	 */
	public String getTitle ()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle (String title)
	{
		this.title = title;
	}

	/**
	 * @return the resID
	 */
	public int getResID ()
	{
		return resID;
	}

	/**
	 * @param resID the resID to set
	 */
	public void setResID (int resID)
	{
		this.resID = resID;
	}

	/**
	 * @return the auth
	 */
	public AuthSocialNetworkInterface getAuth ()
	{
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public void setAuth (AbstractAuthSocialNetwork auth)
	{
		this.auth = auth;
	}

}
