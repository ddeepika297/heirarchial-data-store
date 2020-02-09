package com.dataStore.model;

import java.util.ArrayList;
import java.util.List;

public class Node
{
	public Node(String path, String value, ArrayList<Node> children)
	{

		this.setPath(path);
		this.value = value;
		this.children = children;
	}

	private String path;
	private String value;
	private List<Node> children = null;

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public List<Node> getChildren()
	{
		return children;
	}

	public void setChildren(List<Node> children)
	{
		this.children = children;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

}
