# HelloModules

## Before you begin

This project is an example of a [BioLockJ](https://github.com/BioLockJ-Dev-Team/BioLockJ) module. 

To build it, or even just to use it, you must have successfully installed and tested [BioLockJ](https://github.com/BioLockJ-Dev-Team/BioLockJ).
```
biolockj --version
biolockj ${BLJ}/templates/myFirstPipeline/myFirstPipeline.properties
```

For more information about how to create BioLockJ modules and for other examples, see [the BioLockJ external modules resource repository](https://github.com/BioLockJ-Dev-Team/blj_ext_modules)

### Use this module

See the [userguide pages for the modules in this project](mkdocs/docs/index.md).

Download the jar file to your external modules folder (`mods`), which you point to with the `--external-modules` argument.  In your config file, reference the Hello_World module in your module run order using the `#BioModule` keyword.

Minimalist example:
```
PROJ=HelloModules
URL=https://github.com/BioLockJ-Dev-Team/HelloModules/releases/latest/download
CONFIG=sayHello.config

mkdir ${PROJ}_Example
cd ${PROJ}_Example
wget $URL/${PROJ}.jar -P $PWD/mods
wget $URL/demo.zip
unzip demo.zip && rm demo.zip 
biolockj --external-modules $PWD/mods ./demo/$CONFIG
```
The example above will create a minimalist pipeline demonstrating the use of the Hello_World module from the HelloModules project.  

Add the `#BioModule` line for the Hello_World module to any other pipeline.

### Build this module

The build file references the BioLockJ project by assuming it is a peer folder.  If you don't want to use this relative-path-dependency, you can edit the build.xml file to reference the `$BLJ` variable instead, see commented lines in the build.xml. 

Download the project as a peer to the BioLockJ folder.
```
cd $BLJ
cd ..
wget https://github.com/BioLockJ-Dev-Team/HelloModules/archive/refs/heads/main.zip 
unzip main.zip && rm main.zip && mv HelloModules-main HelloModules
```

Alternatively, use git:
```
cd $BLJ
cd ..
git clone https://github.com/BioLockJ-Dev-Team/HelloModules.git
```

Build with ant:
```
cd HelloModules
ant
```

_If you encounter build difficulties, try using the docker build process below._

Test that BioLockJ recognizes the module.
```
biolockj-api listModules --external-modules $PWD/dist
```
The output list should include "com.github.fodorlab.hello_world.Hello_World".

Run a demo pipeline.
```
biolockj --external-modules $PWD/dist ./demo/sayHello.config
```

See more in the demo folder.

### Build the project and its documentation using docker
Confirm docker is running:
```
docker run --rm hello-world
```

Note: the code block below references this project directory as `$PWD`.
```
cd HelloModules
```

This is the standard build process for BioLockJ modules.
```
docker run --rm \
  -v $PWD:/project \
  -v $BLJ:/BioLockJ \
  -e BLJ=/BioLockJ \
  -w /project \
  biolockjdevteam/build_and_deploy \
  ant userguide
```

This process produces the jar file and the standardized [userguide pages](mkdocs/docs/index.md) for the modules in this project.
