package com.dataStore.test;

import java.util.List;

import com.dataStore.client.Event.EventType;
import com.dataStore.model.Node;
import com.dataStore.model.Store;;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		Store store = new Store();
		// Add a node - root with a string data “nothing”
		Node root = createRoot(store);
		if (root != null)
		{
			System.out.println("******* Root Creation : Successful *******");
		} else
		{
			System.out.println("******* Root Creation : Failed *******");
		}

		// Attach a listener which prints all events to root
		Boolean success = addListenerToRoot(store, root);
		if (success)
		{
			System.out.println("******* Add Listener : Successful *******");
		} else
		{
			System.out.println("******* Add Listener : Failed *******");
		}

		// add child nodes
		addChildren(store);

		// Get and print the data for all the nodes.
		success = getAllNodesData(store);
		if (success)
		{
			System.out.println("******* getNodeData : Successful *******");
		} else
		{
			System.out.println("******* getNodeData : Failed *******");
		}

		// List all the child nodes for root.
		success = listChildNodesOfRoot(store);
		if (success)
		{
			System.out.println("******* List ChildNode : Successful *******");
		} else
		{
			System.out.println("******* List ChildNode: Failed *******");
		}

		// update child1 data
		success = updateNode(store, "/root/child1", "child 1 New");
		if (success)
		{
			System.out.println("******* update child1 : Successful *******");
		} else
		{
			System.out.println("******* update child1: Failed *******");
		}

		// delete node
		success = deleteNode(store, "/root/child2");
		if (success)
		{
			System.out.println("******* delete child2 : Successful");
		} else
		{
			System.out.println("******* delete child2: Failed *******");
		}

	}

	private static Boolean updateNode(Store store, String path, String value)
	{
		System.out.println("update the node child1 data.");
		try
		{
			store.updateNode(path, value);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static Boolean deleteNode(Store store, String string)
	{
		System.out.println("Delete the node child2.");
		try
		{
			store.deleteNode("/root/child2");
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static Boolean listChildNodesOfRoot(Store store)
	{
		System.out.println("List all the child nodes for root.");
		try
		{
			List<Node> children = store.listChildren("/root");
			for (Node child : children)
			{
				System.out.println("\tPath : " + child.getPath() + " Value : " + child.getValue());
			}
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static boolean getAllNodesData(Store store)
	{
		try
		{
			System.out.println("Get and print the data for all the nodes.");
			String path = "/root";
			System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
			path = "/root/child1";
			System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
			path = "/root/child2";
			System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
			path = "/root/child1/subchild1";
			System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static void addChildren(Store store)
	{
		System.out.println("Add two child nodes to root child1 with a string data “childdata 1” & child2 with a string data “childdata 2”");
		Boolean addChild = addChildNode(store, "/root/child1", "childData 1");
		if (addChild)
			System.out.println("******* add child childData 1  : Successful *******");
		else
			System.out.println("******* add child childData 1  : Failed *******");

		addChild = addChildNode(store, "/root/child2", "childData 2");
		if (addChild)
			System.out.println("******* add child childData 2  : Successful *******");
		else
			System.out.println("******* add child childData 2  : Failed *******");

		System.out.println("Add one child node to child1 - subchild1 with a string data “subchild1”");

		addChild = addChildNode(store, "/root/child1/subchild1", "subchild 1");
		if (addChild)
		{
			System.out.println("******* add child subchild 1  : Successful *******");
		} else
		{
			System.out.println("******* add child subchild 1  : Failed");
		}
	}

	private static Boolean addChildNode(Store store, String path, String value)
	{
		try
		{
			store.createNode(path, value);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static Boolean addListenerToRoot(Store store, Node root)
	{
		System.out.println("Attach a listener which prints all events to root");
		try
		{
			store.register(root, EventType.CREATE);
			store.register(root, EventType.UPDATE);
			store.register(root, EventType.DELETE);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private static Node createRoot(Store store)
	{

		System.out.println("Add a node - root with a string data “nothing” ");
		Node root = new Node("root", "nothing", null);
		store.setRoot(root);
		return root;
	}

}
