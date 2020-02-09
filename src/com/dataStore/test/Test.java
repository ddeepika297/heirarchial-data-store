package com.dataStore.test;

import java.util.ArrayList;

import com.dataStore.client.Event.EventType;
import com.dataStore.model.Node;
import com.dataStore.model.Store;;
public class Test
{

	public static void main(String[] args) throws Exception
	{

		Store store = new Store();

		System.out.println("Add a node - root with a string data “nothing” ");
		Node root = new Node("root", "nothing", null);
		store.setRoot(root);

		System.out.println("Attach a listener which prints all events to root");
		store.register(root, EventType.CREATE);
		store.register(root, EventType.UPDATE);
		store.register(root, EventType.DELETE);

		System.out.println("Add two child nodes to root child1 with a string data “childdata 1” & child2 with a string data “childdata 2”");
		store.createNode("/root/child1", "childData 1");
		store.createNode("/root/child2", "childData 2");

		System.out.println("Add one child node to child1 - subchild1 with a string data “subchild1”");
		store.createNode("/root/child1/subchild1", "subchild 1");
		//store.register(root, EventType.DELETE);
		System.out.println("Get and print the data for all the nodes.");
		String path = "/root";
		System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
		path = "/root/child1";
		System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
		path = "/root/child2";
		System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));
		path = "/root/child1/subchild1";
		System.out.println("\tPath : " + path + " Data : " + store.getNodeData(path));

		System.out.println("List all the child nodes for root.");
		ArrayList<Node> children = store.listChildren("/root");
		for (Node child : children)
		{
			System.out.println("\tPath : " + child.getPath() + " Value : " + child.getValue());
		}

		System.out.println("Delete the node child2.");
		store.deleteNode("/root/child2");

	}

}
