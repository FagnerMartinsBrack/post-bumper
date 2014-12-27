package com.fagnerbrack.postbumper.pages;

public interface LoginPage<T> {
	T loginAs( String username, String password ) throws ChainingException;
}
