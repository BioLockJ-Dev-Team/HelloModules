package com.github.fodorlab.hello_world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import biolockj.api.ApiModule;
import biolockj.module.ScriptModuleImpl;
import biolockj.util.BioLockJUtil;

public class Hello_World extends ScriptModuleImpl implements ApiModule {
	
	@Override
	public List<List<String>> buildScript( List<File> files ) throws Exception {
		List<List<String>> list = new ArrayList<>();
		List<String> lines = new ArrayList<>();
		lines.add( FUNCTION_NAME );
		list.add( lines );
		return list;
	}
	

	@Override
	public List<String> getWorkerScriptFunctions() throws Exception {
		List<String> lines = new ArrayList<>();
		lines.add( "function " + FUNCTION_NAME + "(){" );
		lines.add( "echo 'The message is: Hello World!'" ); //captured by log file
		lines.add( "echo 'Hello World!' > ../output/hello.txt"); //captured by output file
		lines.add( "}" );
		return lines;
	}
	
	@Override
	public String getDockerImageName() {
		return "ubuntu";
	}

	@Override
	public String getDescription() {
		return "Print the classic phrase: Hello World!";
	}

	@Override
	public String getCitationString() {
		return "Module developed by Ivory Blakley." + System.lineSeparator() + "BioLockJ " + BioLockJUtil.getVersion();
	}
	
	private final String FUNCTION_NAME = "sayHello";

}
