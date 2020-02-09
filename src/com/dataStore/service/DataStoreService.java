package com.dataStore.service;

import java.util.ArrayList;

import com.dataStore.exception.IllegalPathException;
import com.dataStore.model.Node;

public interface DataStoreService
{

	public void createNode(String path, String data) throws IllegalPathException;

	public void updateNode(String path, String data) throws IllegalPathException;

	public void deleteNode(String path) throws IllegalPathException;

	public ArrayList<Node> listChildren(String path) throws IllegalPathException;

	public String getNodeData(String path) throws IllegalPathException;
}
