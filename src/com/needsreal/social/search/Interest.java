package com.needsreal.social.search;

public class Interest
{
	private Category category;
	private String title;
	private String description;

	public Interest (Category category, String title, String description)
	{
		this.category = category;
		this.title = title;
		this.description = description;
	}

	/**
	 * @return the category
	 */
	public Category getCategory ()
	{
		return category;
	}

	/**
	 * @return the title
	 */
	public String getTitle ()
	{
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription ()
	{
		return description;
	}
}
