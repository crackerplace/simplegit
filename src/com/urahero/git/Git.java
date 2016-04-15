package com.urahero.git;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Simple Git Prototype without much of the functionality just as a practice to
 * dig internals of Git and build a java git client in the next step.
 * 
 * A collection of refs together with an object database containing all objects
 * which are reachable from the refs, possibly accompanied by meta data from one
 * or more porcelains. A repository can share an object database with other
 * repositories via alternates mechanism.
 * 
 * Source : http://kushagragour.in/blog/2014/01/build-git-learn-git/
 * 
 * http://maryrosecook.com/blog/post/git-in-six-hundred-words
 * 
 * https://codewords.recurse.com/issues/three/unpacking-git-packfiles
 * 
 * http://ftp.newartisans.com/pub/git.from.bottom.up.pdf
 * 
 * @author kiran
 * 
 */
public class Git {

	String repo;
	Integer lastCommitId = -1;
	Branch HEAD;
	Map<String, Branch> branches = new HashMap<>();

	// LinkedList<Commit> commits;

	public Git(String repo) {
		this.repo = repo;
		this.lastCommitId = -1;
		Branch master = new Branch("MASTER", null);
		this.HEAD = master;
		branches.put(master.name(), master);
	}

	public static void main(String[] args) {
		Git repo = new Git("myrepo");// git init
		Commit commit1 = repo.commit("First COmmit");// git commit -m
														// "Make commit work"
		Commit commit2 = repo.commit("Second COmmit");
		/*
		 * for (Commit commit : repo.log()) {
		 * System.out.println(commit.message); }
		 */
		repo.checkout("testbranch");
		repo.commit("first commit on new branch");
		for (Commit commit : repo.log()) {
			System.out.println(commit.message);
		}
	}

	public Commit commit(String message) {

		Commit commit = new Commit(++lastCommitId, message, this.HEAD.commit);
		this.HEAD.commit = commit;
		// commits.addFirst(commit);
		return commit;
	}

	public List<Commit> log() {
		List<Commit> log = new LinkedList<Commit>();
		Commit commit = this.HEAD.commit;
		while (commit != null) {
			log.add(commit);
			commit = commit.parent;
		}
		return log;
	}

	public Git checkout(String name) {
		if (branches.containsKey(name)) {
			this.HEAD = branches.get(name);
		} else {
			Branch branch = new Branch(name, this.HEAD.commit);
			this.HEAD = branch;
			branches.put(branch.name(), branch);
		}
		return this;
	}
}
