package com.rockit.common.blackboxtester.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import com.rockit.common.blackboxtester.exceptions.GenericException;

public class FileUtils {
	
	public static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());
	
	public static Iterable<File> listFolders(File root) { 
		return Iterables.filter(
				Files.fileTreeTraverser().children( root ), new Predicate<File>() {
						@Override
						public boolean apply(File input) {
							return input.isDirectory();
						}
				});	
	}

	public static Iterable<File> listFiles(File root) { 
		return Iterables.filter(
				Files.fileTreeTraverser().children( root ), new Predicate<File>() {
						@Override
						public boolean apply(File input) {
							return input.isFile();
						}
				});
		
	}
			
	public static String getContents(File file)  {
		String content;
		try {
			
			content =  Files.toString(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error(e);
			throw new GenericException(e);
		} 
		return content;
		
	}

	public static void deleteDirectory(File file)  {
		try {
			org.apache.commons.io.FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			LOGGER.warn("cannot delete " + file.getAbsolutePath(), e);
		} 
	}

}

