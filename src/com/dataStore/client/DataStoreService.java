package com.dataStore.client;

import java.util.ArrayList;

import com.dataStore.exception.IllegalPathException;
import com.dataStore.model.Node;

public interface DataStoreService
{

	public boolean createNode(String path, String data) throws IllegalPathException;

	public boolean updateNode(String path, String data) throws IllegalPathException;

	public boolean deleteNode(String path) throws IllegalPathException;

	public ArrayList<Node> listChildren(String path) throws IllegalPathException;

	public String getNodeData(String path) throws IllegalPathException;
}
