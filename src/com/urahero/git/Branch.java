package com.urahero.git;

/**
 * A "branch" is an active line of development. The most recent commit on a
 * branch is referred to as the tip of that branch. The tip of the branch is
 * referenced by a branch head, which moves forward as additional development is
 * done on the branch. A single git repository can track an arbitrary number of
 * branches
 * 
 * 
 * @author kiran
 * 
 */
public class Branch {

	private String name;
	public Commit commit;

	public Branch(String name, Commit commit) {
		this.name = name;
		this.commit = commit;
	}

	public String name() {
		return name;
	}

}
