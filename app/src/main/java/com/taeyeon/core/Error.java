package com.taeyeon.core;

import android.util.*;
import com.taeyeon.aide_base.*;
import java.io.*;
import java.time.*;

public final class Error {
	public static final String LOG_TAG = "ERROR_LOG";
	
	public static final void log(Exception exception) {
		Log.e(LOG_TAG, new Error(exception).full);
	}
	
	public static final void toast(Exception exception) {
		log(exception);
		Utils.toast(Core.getContext().getResources().getString(R.string.error_happen, exception.toString())); //todo
	}
	
	
	private final Exception exception;
	private final LocalDateTime occurTime;
	
	private final String toString;
	private final StackTraceElement[] stackTraceElements;
	private final StackTraceElement stackTraceElement;
	private final String className;
	private final String fileName;
	private final int lineNumber;
	private final String methodName;
	private final boolean isNativeMethod;
	private final String message;
	private final String full;
	
	public Error(Exception exception) {
		this(exception, LocalDateTime.now());
	}
	
	public Error(Exception exception, LocalDateTime occurTime) {
		this.exception = exception;
		this.occurTime = occurTime;
		
		this.toString = exception.toString();
		this.stackTraceElements = exception.getStackTrace();
		this.stackTraceElement = stackTraceElements[0];
		this.className = stackTraceElement.getClassName();
		this.fileName = stackTraceElement.getFileName();
        this.lineNumber = stackTraceElement.getLineNumber();
        this.methodName = stackTraceElement.getMethodName();
        this.isNativeMethod = stackTraceElement.isNativeMethod();
		this.message = exception.getMessage() == null ? "" : exception.getMessage();
		
		final StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		this.full = stringWriter.toString();
	}
	
	public final Exception getException() {
		return this.exception;
	}

	public final LocalDateTime getOccurTime() {
		return this.occurTime;
	}

	public final String getToString() {
		return this.toString;
	}

	public final StackTraceElement[] getStackTraceElements() {
		return this.stackTraceElements;
	}
	
	public final StackTraceElement getStackTraceElement() {
		return this.stackTraceElement;
	}

	public final String getClassName() {
		return this.className;
	}

	public final String getFileName() {
		return this.fileName;
	}

	public final int getLineNumber() {
		return this.lineNumber;
	}

	public final String getMethodName() {
		return this.methodName;
	}

	public final boolean isNativeMethod() {
		return this.isNativeMethod;
	}

	public final String getMessage() {
		return this.message;
	}

	public final String getFull() {
		return this.full;
	}
	
}
