package wingman;

/*
 * Name: Anderson Tao
 * Description: Singleton Resource Class Builder
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class ResourceClassBuilder {

	private static String newline = "\n";
	private static String tab = "\t";

	public static ArrayList<String> images = new ArrayList<>();

	public static void main(String[] args) {
		String resourcePath = "/home/arod/Desktop/Resources";
		// String resourcePath = "C:\\Users\\arodr101\\Desktop\\Resources";
		// String resourcePath = "Resources";
		File folder = new File(resourcePath);
		System.out.println("Folder Exists: " + folder.exists());
		File[] listOfFiles = folder.listFiles();

		Arrays.sort(listOfFiles);

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith("png"))
					images.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				// Multiple levels of directories
			}
		}

		File directory = new File("resources");

		FileUtilities.copyDirectory(resourcePath, directory.getAbsolutePath());

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src//wingman//Resources.java"), "utf-8"));
			writer.write("package wingman;" + newline);
			writer.write(newline);
			writer.write("import javax.imageio.ImageIO;" + newline);
			writer.write("import java.awt.Image;" + newline);
			writer.write("import java.io.File;" + newline);
			writer.write("import java.io.IOException;" + newline);
			writer.write(newline);
			writer.write("public class Resources {" + newline);
			writer.write(newline);
			writer.write(tab + "private static Resources instance;" + newline);
			writer.write(tab + "public Image ");

			String preFixed = "";
			for (int i = 0; i < images.size(); i++) {
				String prefix = images.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				preFixed += prefix + ",";

				if (i % 5 == 0 && i > 0) {
					preFixed += newline;
					preFixed += tab + tab + tab + tab;
				}
			}
			writer.write(preFixed.substring(0, preFixed.length() - 1));
			writer.write(";" + newline);
			writer.write(newline);
			writer.write(tab + "private Resources() {" + newline);
			writer.write(tab + tab + "try {" + newline);

			for (int i = 0; i < images.size(); i++) {
				String prefix = images.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				writer.write(tab + tab + tab + prefix + " = ImageIO.read(new File(\"../resources/" + images.get(i) + "\"));" + newline);
			}

			writer.write(newline);

			writer.write(tab + tab + "} catch (IOException ioException) {" + newline);
			writer.write(tab + tab + tab + "ioException.printStackTrace();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + "public static Resources getInstance() {" + newline);
			writer.write(tab + tab + "if (instance == null) {" + newline);
			writer.write(tab + tab + tab + "instance = new Resources();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + "return instance;" + newline);
			writer.write(tab + "}" + newline);
			writer.write("}");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

	}
}
