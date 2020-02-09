package com.dataStore.model;

import java.util.ArrayList;

import com.dataStore.exception.IllegalPathException;
import com.dataStore.service.DataStoreService;

public class Store extends EventHandler implements DataStoreService
{
	private Node root;
	private String basePath = "/";

	public Store()
	{
	}

	public Store(Node root, String basePath)
	{
		super();
		this.root = root;
		this.basePath = basePath;
	}

	public void createNode(String path, String data) throws IllegalPathException
	{
		path = path.trim();
		if (!path.startsWith(basePath))
		{
			throw new IllegalPathException();
		}
		path = path.substring(1);
		String[] heirarchy = path.split("/");
		int pathLen = heirarchy.length;
		if (pathLen == 0)
		{
			throw new IllegalPathException();
		}

		synchronized (this)
		{
			Node node = getLastNodeInPath(heirarchy, pathLen - 1);
			ArrayList<Node> children = attachNewChild(heirarchy[pathLen - 1], node, data);
			node.setChildren(children);
		}
		for (Node node : createEventListners)
		{
			if (path.startsWith(node.getPath()))
				listen(EventType.CREATE);
		}
	}

	public void updateNode(String path, String value) throws IllegalPathException
	{
		path = path.trim();
		if (!path.startsWith(basePath))
		{
			throw new IllegalPathException();
		}
		path = path.substring(1);
		String[] heirarchy = path.split("/");
		int pathLen = heirarchy.length;
		if (pathLen == 0)
		{
			throw new IllegalPathException();
		}
		synchronized (this)
		{
			Node node = getLastNodeInPath(heirarchy, pathLen);
			node.setValue(value);
		}
		for (Node node : updateEventListners)
		{
			if (path.startsWith(node.getPath()))
				listen(EventType.UPDATE);
		}
	}

	public void deleteNode(String path) throws IllegalPathException
	{
		path = path.trim();
		if (!path.startsWith(basePath))
		{
			throw new IllegalPathException();
		}
		path = path.substring(1);
		String[] heirarchy = path.split("/");
		int pathLen = heirarchy.length;
		if (pathLen == 0)
		{
			throw new IllegalPathException();
		}

		synchronized (this)
		{
			Boolean ChildFound = false;
			Node node = getLastNodeInPath(heirarchy, pathLen - 1);
			ArrayList<Node> children = node.getChildren();
			for (Node child : children)
			{
				if (child.getPath().equals(heirarchy[pathLen - 1]))
				{
					children.remove(child);
					ChildFound = true;
					break;
				}
			}
			if (ChildFound)
				node.setChildren(children);
			else
			{
				throw new IllegalPathException();
			}
		}
		for (Node node : deleteEventListners)
		{
			if (path.startsWith(node.getPath()))
				listen(EventType.DELETE);
		}

	}

	public String getNodeData(String path) throws IllegalPathException
	{
		path = path.trim();
		if (!path.startsWith(basePath))
		{
			throw new IllegalPathException();
		}
		path = path.substring(1);
		String[] heirarchy = path.split("/");
		int pathLen = heirarchy.length;
		if (pathLen == 0)
		{
			throw new IllegalPathException();
		}
		synchronized (this)
		{
			Node node = getLastNodeInPath(heirarchy, pathLen);
			return node.getValue();
		}
	}

	public ArrayList<Node> listChildren(String path) throws IllegalPathException
	{
		path = path.trim();
		if (!path.startsWith(basePath))
		{
			throw new IllegalPathException();
		}
		path = path.substring(1);
		String[] heirarchy = path.split("/");
		int pathLen = heirarchy.length;
		if (pathLen == 0)
		{
			throw new IllegalPathException();
		}
		synchronized (this)
		{
			Node node = getLastNodeInPath(heirarchy, pathLen);
			return node.getChildren();
		}

	}

	private Node getLastNodeInPath(String[] heirarchy, int pathLen) throws IllegalPathException
	{
		Node node;
		int index = 0;
		if (heirarchy[index].equals(root.getPath()))
		{
			node = root;
		} else
		{
			throw new IllegalPathException();
		}
		while (true)
		{
			index++;
			if (pathLen > index)
			{
				ArrayList<Node> children = node.getChildren();
				if (children == null)
				{
					throw new IllegalPathException();
				}
				Boolean childFound = false;
				for (Node child : children)
				{
					if (child.getPath().equals(heirarchy[index]))
					{
						node = child;
						childFound = true;
						break;
					}
				}
				if (!childFound)
				{
					throw new IllegalPathException();
				}
			} else
			{
				break;
			}
		}
		return node;
	}

	private ArrayList<Node> attachNewChild(String path, Node node, String data)
	{

		ArrayList<Node> children;
		if (node.getChildren() != null)
		{
			children = node.getChildren();
		} else
		{
			children = new ArrayList<Node>();
		}
		Node child = new Node(path, data, null);
		children.add(child);
		return children;
	}

	public Node getRoot()
	{
		return root;
	}

	public void setRoot(Node root)
	{
		this.root = root;
	}

	public String getBasePath()
	{
		return basePath;
	}

	public void setBasePath(String basePath)
	{
		this.basePath = basePath;
	}

}
