import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PictureToXMLConverter {
    public static void main(String[] args) {

        System.out.println("Usage: java PictureToXMLConverter <DungeonBuilder Installation Directory> <Image Scale> <Artist> <Artist Website> <Folder Name of Assets> <Category>");

        // Get the directory path from command line arguments
        if (args.length != 6) {
            System.out.println("Missing Arguments!");
            System.exit(1);
        }


        int scale = Integer.parseInt(args[1]);
        String artist = args[2];
        String artistWebsite = args[3];
        String folderName = args[4];
        String category = args[5];

        String directoryPath = args[0] + "\\Custom\\Art\\Build\\" + folderName + "\\" + category;
        String writeTo = args[0] + "\\Custom\\Art Definitions\\Build\\" + folderName + "\\" + category;


        // Get all files in the directory
        File directory = new File(directoryPath);
        File writeToDirectory = new File(writeTo);

            if(!writeToDirectory.exists()){
                if(writeToDirectory.mkdirs()){
                    System.out.println("A new directory for the XML files was created!");
                }
                else {
                    System.out.println("This directory already exists! Make sure you intend to overwrite its contents and delete it before creating new files!");
                    System.exit(1);
                }
            }

        File[] files = directory.listFiles();

        if (files == null) {
            System.err.println("Directory does not exist or is empty");
            System.exit(1);
        }

        // Iterate through the files
        for (File file : files) {
            if (file.isFile() && isPictureFile(file)) {
                String fileName = file.getName();

                // Replace the first letter of the file name with its uppercase version
                fileName = Character.toUpperCase(fileName.charAt(0)) + fileName.substring(1);
                String xmlContent = generateXMLContent(fileName, folderName, category, artist, artistWebsite, scale);
                fileName = Character.toLowerCase(fileName.charAt(0)) + fileName.substring(1);


                // Create a new .xml file with the same name as the picture file
                String xmlFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".xml";
                File xmlFile = new File(writeToDirectory, xmlFileName);
                try (FileWriter writer = new FileWriter(xmlFile)) {
                    writer.write(xmlContent);
                } catch (IOException e) {
                    System.err.println("Failed to create .xml file for " + fileName);
                    e.printStackTrace();
                }
            }
        }

        for (File file : files) {
            if (file.isFile()) {
                String oldName = file.getName();
                String newName = oldName.toLowerCase();
                if (!oldName.equals(newName)) {
                    File newFile = new File(directory, newName);
                    if (!file.renameTo(newFile)) {
                        System.err.println("Failed to rename " + oldName + " to " + newName);
                    }
                }
            }
        }

        System.out.println("XML files created successfully!");
        System.out.println("All files have been renamed to lowercase letters.");
        System.out.println("\nPicture Directory: " + directoryPath);
        System.out.println("General Foldername: " + folderName);
        System.out.println("Category Name: " + category);
        System.out.println("Image Scale: " + scale);
        System.out.println("Artist Name: " + artist);
        System.out.println("Artist Website: " + artistWebsite);
        System.out.println("This will be the directory the XML file points to for the asset image: " + "Art/Build/"+ folderName + "/" + category + "/example_image.png");
    }

    // Check if a file is a picture file (with .png, .jpg, or .jpeg extension)
    private static boolean isPictureFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".png") ||
                fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".jpeg");
    }

    // Generate the XML content with the picture file name
    private static String generateXMLContent(String pictureFileName, String folderName, String category, String artist, String artistWebsite, int scale) {
        String xmlContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<Settings>\n" +
                "\n" +
                "<SharedSettings>\n" +
                "<Setting Key=\"Artist\" Value=\"" + artist + "\" />\n" +
                "<Setting Key=\"ArtistURL\" Value=\"" +artistWebsite + "\" />\n" +
                "<Setting Key=\"ConceptCreator\" Value=\"" + artist + "\" />\n" +
                "</SharedSettings>\n" +
                "\n" +
                "<Placeable Name=\"" + pictureFileName.substring(0, pictureFileName.lastIndexOf('.')) + "\">\n" +
                "<Setting Key=\"ImagePath\" Value=\"Art/Build/" + folderName + "/" + category + "/" + pictureFileName + "\" />\n" +
                "\n" +
                "<Setting Key=\"Category\" Value=\"" + category + "\" />\n" +
                "<Setting Key=\"SnapType\" Value=\"None\" />\n" +
                "<Setting Key=\"PlacementType\" Value=\"Single\" />\n" +
                "\n" +
                "<Setting Key=\"DrawingOffsetX\" Value=\"0\" />\n" +
                "<Setting Key=\"DrawingOffsetY\" Value=\"0\" />\n" +
                "<Setting Key=\"DrawingOffsetZ\" Value=\"0\" />\n" +
                "<Setting Key=\"ImageScale\" Value=\""+ scale + "\" />\n" +
                "\n" +
                "<Setting Key=\"CornerOffsetX\" Value=\"0\" />\n" +
                "\n" +
                "<Setting Key=\"CornerOffsetY\" Value=\"90\" />\n" +
                "<Setting Key=\"StacksWithRelativeIsoPositions\" Value=\"2,1;1,1\" />\n" +
                "\n" +
                "</Placeable>\n" +
                "</Settings>\n";

        return xmlContent;
    }
}