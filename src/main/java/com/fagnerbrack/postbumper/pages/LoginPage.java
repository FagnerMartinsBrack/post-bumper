package com.fagnerbrack.postbumper.pages;

public interface LoginPage<T> {
	T loginWith( AuthenticationData auth ) throws ChainingException;
}
