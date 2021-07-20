package com.github.fodorlab.hello_world;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import biolockj.Config;
import biolockj.Log;
import biolockj.exception.ConfigFormatException;

public class Step5 extends Step4 {

	/**
	 * Test anything that might cause your module to fail; especially user input.
	 * In this case, the only problem we foresee is the integer format for one of the properties, which we already
	 * check, via super.checkDependencies(); in {@link Step3#checkDependencies()}
	 * 
	 * Many modules just check properties for unacceptable values.
	 * Some modules may also check than an environment variable is set, or that an executable is reachable, or that some file exists...
	 * This check must be reliable and speedy; otherwise it is very open-ended.
	 */
	@Override
	public void checkDependencies() throws Exception {
		super.checkDependencies();
		Log.debug( this.getClass(), "Use this many exclamation points: " + Config.getPositiveInteger( this, EXCITE_PROP ) );
	}

	@Override
	public List<List<String>> buildScript( List<File> files ) throws Exception {
		// Elements in the outer list equate to samples.  Otherwise, there is only one element.
		List<List<String>> list = new ArrayList<>();
		// Elements in the inner list represent lines of code needed to process an individual sample.
		List<String> lines = new ArrayList<>();
		lines.add( "echo " + makeMessage() );
		list.add( lines );
		return list;
	}
	
	/**
	 * Create a message based on the modules properties.
	 * @return
	 * @throws ConfigFormatException
	 */
	protected String makeMessage() throws ConfigFormatException {
		String name = Config.getString( this, NAME_PROP );
		String msg = "Hello, " + name + howExciting();
		Log.info( this.getClass(), msg );
		return msg;
	}
	
	/**
	 * Accesses the {@value com.github.fodorlab.hello_world.Step3#EXCITE_PROP} and creates a string with that many !s.
	 * 
	 * Notice that this method access the value using the same method that was used in the {@link biolockj.module.BioModuleImpl#isValidProp(String)}
	 * @return
	 * @throws ConfigFormatException
	 */
	protected String howExciting() throws ConfigFormatException {
		Integer val = Config.getPositiveInteger( this, EXCITE_PROP );
		if (val==null) return ".";
		return new String(new char[val]).replace("\0", "!");
	}
	
	// The ubuntu docker image is lightweight and can handle the script we are making.
	// If you are developing a module to use some particular software, you'll need find / make an image that includes that software.
	// Even then; starting with the ubuntu image is a good way to get some initial components in place.
	@Override
	public String getDockerImageName() {
		return "ubuntu";
	}
	
}
