package com.jmoyano.cassandra.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class IOUtils {
	
	public static List<String> getResourceContent(final String resourceName){
		BufferedReader reader = new BufferedReader(new InputStreamReader(IOUtils.class.getClassLoader().getResourceAsStream(resourceName)));
		String aux = null;
		List<String> text = new LinkedList<String>();
		try {
			while ((aux=reader.readLine())!=null) {
				text.add(aux);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
