# HelloModules
An example of a BioLockJ module. 

See the [documentation for the modules in this project](mkdocs/docs/index.md).

### Use this module

Download the jar file to your external modules folder (`mods`).

Minimalist example:
```
mkdir HelloModules_Example
cd HelloModules_Example
mkdir mods
mkdir demo
cd mods
wget https://github.com/BioLockJ-Dev-Team/HelloModules/releases/download/0.0.0/HelloModules.jar
cd ../demo
wget https://raw.githubusercontent.com/BioLockJ-Dev-Team/HelloModules/main/demo/sayHello.config
cd ..
biolockj --external-modules ./mods ./demo/sayHello.config
```
The example above will create a minimalist pipeline demonstrating the use of the HelloModule module from the HelloModules project.  

Add the `#BioModule` line for the HelloModules module to any other pipeline.

### Build this module

The build file references the BioLockJ project by assuming it is a peer folder.
```
cd $BLJ
cd ..
git clone https://github.com/BioLockJ-Dev-Team/HelloModules.git
cd HelloModules
ant
```

Test that BioLockJ recognizes the module.
```
biolockj-api listModules --external-modules ./dist
```
The output list should include "com.github.fodorlab.hello_world.HelloModules".

Run a demo pipeline.
```
biolockj --external-modules $PWD/dist ./demo/sayHello.config
```

See more in the demo folder.
