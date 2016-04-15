package com.urahero.git;

public class Commit {

	Integer id;
	String message;
	String change;
	Commit parent;

	public Commit(Integer id, String message, Commit parent) {
		this.id = id;
		this.message = message;// commit message
		// this.change = actual change made by this commit
		this.parent = parent;
	}

	public static void main(String[] args) {

	}
}
