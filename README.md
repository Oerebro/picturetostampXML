# picturetostampXML
Automatically creates XML files for art definitions in Dungeon Builder.

## How to use:
The converter assumes that you already put the assets you want to make XML files for into your  Custom/Arts folder in your Dungeon Builder installation with the following hierarchy:

Dungeon Builder (where the main .exe is located)

Custom

Art

build

Placeholder Folder (the name of the artist for example)

Category Folder with your assets in it

### If you never used a java application before:
1. Open your command prompt (search for cmd.exe)
2. locate the directory you put the PictureToXMLConverter.class file into (example/directory/PictureToXMLConverter.class)
3. type 'cd example/directory' into your command prompt
4. type 'java PictureToXMLConverter <DungeonBuilder Installation Directory> <Image Scale> <Artist> <Artist Website> <Folder Name of Assets> <Category>
  (note that you need to put " before and after the Dungeonbuilder directory because of the spaces in the path)
  For Example: 
  java PictureToXMLConverter "example/directory/Dungeon Builder" 1 exampleArtist exampleWebsite.com PlaceholderFolder CategoryFolder
  
The programm will automatically create a directory with the XML files in your Art Definitions, unless you used symbols that don't work with Dungeon Builders directory paths all your stamps should show up the next time you open it.
