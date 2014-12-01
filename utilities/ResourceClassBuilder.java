package utilities;

/*
 * Name: Anderson Tao Description: Singleton Resource Class Builder
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

public class ResourceClassBuilder {

	private static String newline = "\n";
	private static String tab = "\t";

	private static ArrayList<String> images = new ArrayList<String>();
	private static ArrayList<String> solidSpecs = new ArrayList<String>();
	private static ArrayList<String> imageSpecs = new ArrayList<String>();
	private static ArrayList<String> sounds = new ArrayList<String>();
	public static ArrayList<String> topScores = new ArrayList<String>();

	public static void main(String[] args) {
		// String resourcePath = "/home/arod/Desktop//Wingman/Resources";
		// String solidResourcesText =
		// "/home/arod/Desktop/Wingman/solid_resources.txt";
		// String imageResourcesText =
		// "/home/arod/Desktop/Wingman/image_resources.txt";
		// String resourcesJSON = "/home/arod/Desktop/Wingman/resources.json";

		String resourcePath = "C:\\Users\\arodr101\\Desktop\\Wingman\\Resources";
		String solidResourcesText = "C:\\Users\\arodr101\\Desktop\\Wingman\\solid_resources.txt";
		String imageResourcesText = "C:\\Users\\arodr101\\Desktop\\Wingman\\image_resources.txt";
		String scoreboardText = "C:\\Users\\arodr101\\Desktop\\Wingman\\top_scores.txt";
		String resourcesJSON = "C:\\Users\\arodr101\\Desktop\\Wingman\\resources.json";

		String jarRun = "../"; // Applet Configuration
		// String jarRun = ""; // Jar Configuration

		System.out.println("Getting top scores");
		try (BufferedReader br = new BufferedReader(new FileReader(scoreboardText))) {
			for (String line; ( line = br.readLine() ) != null;) {
				topScores.add(line);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Getting solid_resources.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(solidResourcesText))) {
			for (String line; ( line = br.readLine() ) != null;) {
				solidSpecs.add(line);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Found " + solidSpecs.size() + " resources with specifications");

		System.out.println("Getting image_resources.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(imageResourcesText))) {
			for (String line; ( line = br.readLine() ) != null;) {
				imageSpecs.add(line);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		System.out.println("Found " + imageSpecs.size() + " resources with specifications");

		System.out.println("Getting resources");
		File folder = new File(resourcePath);
		File[] listOfFiles = folder.listFiles();

		Arrays.sort(listOfFiles);

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith("png")) {
					images.add(listOfFiles[i].getName());
				} else if (listOfFiles[i].getName().endsWith("wav")) {
					sounds.add(listOfFiles[i].getName());
				}
			} else if (listOfFiles[i].isDirectory()) {
				// Multiple levels of directories
			}
		}
		System.out.println("Found " + images.size() + " resources");

		System.out.println("Moving resources folder");
		File directory = new File("resources");

		FileUtilities.copyDirectory(resourcePath, directory.getAbsolutePath());

		FileUtilities.copyFile(new File(resourcesJSON), new File("resources.json"));

		FileUtilities.copyFile(new File(scoreboardText), new File("top_scores.txt"));

		System.out.println("Writing Resources.java");
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src//wingman//Resources.java"), "utf-8"));
			// Import statements
			writer.write("package wingman;" + newline);
			writer.write(newline);
			writer.write("import java.applet.AudioClip;" + newline);
			writer.write("import java.awt.Image;" + newline);
			writer.write("import java.io.BufferedReader;" + newline);
			writer.write("import java.io.BufferedWriter;" + newline);
			writer.write("import java.io.File;" + newline);
			writer.write("import java.io.FileOutputStream;" + newline);
			writer.write("import java.io.FileReader;" + newline);
			writer.write("import java.io.IOException;" + newline);
			writer.write("import java.io.OutputStreamWriter;" + newline);
			writer.write("import java.net.MalformedURLException;" + newline);
			writer.write("import java.util.ArrayList;" + newline);
			writer.write(newline);
			writer.write("import javax.imageio.ImageIO;" + newline);
			writer.write("import javax.swing.JApplet;" + newline);
			writer.write(newline);
			writer.write("import org.json.simple.JSONArray;" + newline);
			writer.write("import org.json.simple.JSONObject;" + newline);
			writer.write("import org.json.simple.parser.JSONParser;" + newline);
			writer.write(newline);

			// Resource class
			writer.write("public class Resources {" + newline);
			writer.write(newline);

			// Member variables
			writer.write(tab + "private static Resources instance;" + newline);

			// Images
			writer.write(tab + "public Image ");
			String imageVariables = "";
			for (int i = 0; i < images.size(); i++) {
				String prefix = images.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				imageVariables += prefix + ", ";
			}
			writer.write(imageVariables.substring(0, imageVariables.length() - 2));
			writer.write(";" + newline);
			writer.write(newline);

			// ImageSpecifications
			writer.write(tab + "public ImageSpecification ");
			String imageSpecVariables = "";
			for (int i = 0; i < imageSpecs.size(); i++) {
				String prefix = imageSpecs.get(i) + "_image_spec";
				imageSpecVariables += prefix + ", ";
			}
			writer.write(imageSpecVariables.substring(0, imageSpecVariables.length() - 2));
			writer.write(";" + newline);
			writer.write(newline);

			// SolidResourceSpecification
			writer.write(tab + "public static SolidResourceSpecification ");
			String specsVariable = "";
			for (int i = 0; i < solidSpecs.size(); i++) {
				String prefix = solidSpecs.get(i) + "_spec";
				specsVariable += prefix + ", ";
			}
			writer.write(specsVariable.substring(0, specsVariable.length() - 2));
			writer.write(";" + newline);
			writer.write(newline);

			// AudioClips
			String audioVariable = "";
			writer.write(tab + "public AudioClip ");
			for (int i = 0; i < sounds.size(); i++) {
				String prefix = sounds.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				audioVariable += prefix + ", ";

				if (i % 5 == 0 && i > 0) {
					audioVariable += newline;
					audioVariable += tab + tab + tab + tab;
				}
			}
			writer.write(audioVariable.substring(0, audioVariable.length() - 2));
			writer.write(";" + newline);
			writer.write(newline);

			// TopScores
			for (int i = 1; i <= 5; i++) {
				writer.write(tab + "public static int scoreNumber" + i + ";" + newline);
				writer.write(tab + "public static String score" + i + ";" + newline);
			}
			writer.write(newline);

			// Constructor
			writer.write(tab + "private Resources() {" + newline);
			writer.write(tab + tab + "try {" + newline);

			for (int i = 0; i < images.size(); i++) {
				String prefix = images.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				writer.write(tab + tab + tab + prefix + " = ImageIO.read(new File(\"" + jarRun
						+ "resources/" + images.get(i) + "\"));" + newline);
			}
			writer.write(newline);
			writer.write(tab + tab + "} catch (IOException ioException) {" + newline);
			writer.write(tab + tab + tab + "ioException.printStackTrace();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);

			// Sounds
			writer.write(tab + tab + "try {" + newline);
			for (int i = 0; i < sounds.size(); i++) {
				String prefix = sounds.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				writer.write(tab + tab + tab + prefix + " = JApplet.newAudioClip(( new File(\""
						+ jarRun + "resources/" + sounds.get(i) + "\").toURI().toURL() ));"
						+ newline);
			}
			writer.write(newline);
			writer.write(tab + tab + "} catch (MalformedURLException exception) {" + newline);
			writer.write(tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);

			// SolidResourcesSpecifications
			for (int i = 0; i < solidSpecs.size(); i++) {
				writer.write(tab + tab + solidSpecs.get(i) + "_spec"
						+ " = new SolidResourceSpecification(\"" + solidSpecs.get(i) + "\");"
						+ newline);
			}

			writer.write(newline);

			// ImageSpecifications
			for (int i = 0; i < imageSpecs.size(); i++) {
				writer.write(tab + tab + imageSpecs.get(i) + "_image_spec"
						+ " = new ImageSpecification(\"" + imageSpecs.get(i) + "\");" + newline);
			}

			writer.write(tab + "}" + newline);
			writer.write(newline);

			// getInstance() member method
			writer.write(tab + "public static Resources getInstance() {" + newline);
			writer.write(tab + tab + "if (instance == null) {" + newline);
			writer.write(tab + tab + tab + "instance = new Resources();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + "return instance;" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);

			// getTopScores()
			writer.write(tab + "private static void getTopScores() {" + newline);

			writer.write(tab + "}" + newline);
			writer.write(newline);

			// writeTopScores()
			writer.write(tab + "public static void writeTopScores() {" + newline);
			writer.write(tab + tab + "BufferedWriter writer = null;" + newline);
			writer.write(tab + tab + "try {" + newline);
			writer.write(tab + tab + tab
					+ "writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(\""
					+ jarRun + "top_scores.txt\"), \"utf-8\"));" + newline);
			for (int i = 1; i <= 5; i++) {
				writer.write(tab + tab + tab + "writer.write(score" + i + " + \"\\n\");" + newline);
			}
			for (int i = 1; i <= 5; i++) {
				writer.write(tab + tab + tab + "writer.write(\"\" + scoreNumber" + i + ");"
						+ newline);
				if (i < 5) {
					writer.write(tab + tab + tab + "writer.write(\"\\n\");" + newline);
				}
			}
			writer.write(tab + tab + "} catch (Exception exception) {" + newline);
			writer.write(tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + "} finally {" + newline);
			writer.write(tab + tab + tab + "try {" + newline);
			writer.write(tab + tab + tab + tab + "writer.close();" + newline);
			writer.write(tab + tab + tab + "} catch (Exception exception) {" + newline);
			writer.write(tab + tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);

			// getSolidSpec() member method
			writer.write(tab
					+ "public static SolidResourceSpecification getSolidSpec(String name) {"
					+ newline);
			writer.write(newline);
			writer.write(tab + tab + "if (instance == null) {" + newline);
			writer.write(tab + tab + tab + "instance = new Resources();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);

			// switch-case statement
			writer.write(tab + tab + "switch (name) {" + newline);
			for (int i = 0; i < solidSpecs.size(); i++) {
				writer.write(tab + tab + tab + "case \"" + solidSpecs.get(i) + "\":" + newline);
				writer.write(tab + tab + tab + tab + "return " + solidSpecs.get(i) + "_spec;"
						+ newline);
			}
			writer.write(tab + tab + tab + "default:" + newline);
			writer.write(tab + tab + tab + tab + "return null;" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);

			// SolidResourceSpecification class
			writer.write(tab + "public class SolidResourceSpecification {" + newline);
			writer.write(newline);

			// member variables
			writer.write(tab + tab + "// Bounds" + newline);
			writer.write(tab + tab
					+ "public double center_x, center_y, top, bottom, left, right, front;"
					+ newline);
			writer.write(tab + tab + "public long number_of_shapes;" + newline);
			writer.write(tab + tab + "public String name;" + newline);
			writer.write(newline);
			writer.write(tab + tab + "private JSONParser parser;" + newline);
			writer.write(newline);
			writer.write(tab + tab + "public ArrayList<Shape> shapes = new ArrayList<Shape>();"
					+ newline);
			writer.write(newline);

			// Constructor
			writer.write(tab + tab + "public SolidResourceSpecification(String name) {" + newline);
			writer.write(tab + tab + tab + "parser = new JSONParser();" + newline);
			writer.write(tab + tab + tab + "try {" + newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(\""
					+ jarRun + "resources.json\"));" + newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject bounds = (JSONObject) objectSpecifications.get(\"bounds\");"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "JSONArray center = (JSONArray) bounds.get(\"center\");" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab
					+ "this.center_x = (double) Double.parseDouble(center.get(0).toString());"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "this.center_y = (double) Double.parseDouble(center.get(1).toString());"
					+ newline);
			writer.write(newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.top = (double) Double.parseDouble(bounds.get(\"top_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.bottom = (double) Double.parseDouble(bounds.get(\"bottom_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.left = (double) Double.parseDouble(bounds.get(\"left_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.right = (double) Double.parseDouble(bounds.get(\"right_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.front = (double) Double.parseDouble(bounds.get(\"front_edge\").toString());"
					+ newline);
			writer.write(newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "JSONObject collision = (JSONObject) objectSpecifications.get(\"collision\");"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.number_of_shapes = (long) Long.parseLong(collision.get(\"number_of_shapes\").toString());"
					+ newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject JSONshapes = (JSONObject) collision.get(\"shapes\");" + newline);
			writer.write(tab + tab + tab + tab + "for (int i = 1; i <= number_of_shapes; i++) {"
					+ newline);
			writer.write(tab + tab + tab + tab + tab
					+ "JSONObject shape = (JSONObject) JSONshapes.get(\"shape\" + i);" + newline);
			writer.write(tab + tab + tab + tab + tab
					+ "JSONArray params = (JSONArray) shape.get(\"params\");" + newline);
			writer.write(tab + tab + tab + tab + tab
					+ "shapes.add(new Shape((String) shape.get(\"type\"), params));" + newline);
			writer.write(tab + tab + tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab + "this.name = name;" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "} catch (Exception exception) {" + newline);
			writer.write(tab + tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "parser = null;" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);

			// Shape class
			writer.write(tab + tab + "public class Shape {" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "public long x, y, width, height;" + newline);
			writer.write(tab + tab + tab + "public String type;" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "public Shape(String type, JSONArray array) {" + newline);
			writer.write(tab + tab + tab + tab + "this.type = type;" + newline);
			writer.write(tab + tab + tab + tab
					+ "this.x = (long) Long.parseLong(array.get(0).toString());" + newline);
			writer.write(tab + tab + tab + tab
					+ "this.y = (long) Long.parseLong(array.get(1).toString());" + newline);
			writer.write(tab + tab + tab + tab
					+ "this.width = (long) Long.parseLong(array.get(2).toString());" + newline);
			writer.write(tab + tab + tab + tab
					+ "this.height = (long) Long.parseLong(array.get(3).toString());" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);

			// writer.write(tab + tab + tab + tab + "" + newline);
			// ImageSpecification class
			writer.write(tab + "public class ImageSpecification {" + newline);
			writer.write(newline);

			// member variables
			writer.write(tab + tab + "public double center_x, center_y, top, bottom, left, right;"
					+ newline);
			writer.write(tab + tab + "public String name;" + newline);
			writer.write(newline);
			writer.write(tab + tab + "private JSONParser parser;" + newline);
			writer.write(newline);

			// Constructor
			writer.write(tab + tab + "public ImageSpecification(String name) {" + newline);
			writer.write(tab + tab + tab + "parser = new JSONParser();" + newline);
			writer.write(tab + tab + tab + "try {" + newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(\""
					+ jarRun + "resources.json\"));" + newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "JSONObject bounds = (JSONObject) objectSpecifications.get(\"bounds\");"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "JSONArray center = (JSONArray) bounds.get(\"center\");" + newline);
			writer.write(tab + tab + tab + tab
					+ "this.center_x = (double) Double.parseDouble(center.get(0).toString());"
					+ newline);
			writer.write(tab + tab + tab + tab
					+ "this.center_y = (double) Double.parseDouble(center.get(1).toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.top = (double) Double.parseDouble(bounds.get(\"top_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.bottom = (double) Double.parseDouble(bounds.get(\"bottom_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.left = (double) Double.parseDouble(bounds.get(\"left_edge\").toString());"
					+ newline);
			writer.write(tab
					+ tab
					+ tab
					+ tab
					+ "this.right = (double) Double.parseDouble(bounds.get(\"right_edge\").toString());"
					+ newline);
			writer.write(tab + tab + tab + tab + "this.name = name;" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "} catch (Exception exception) {" + newline);
			writer.write(tab + tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "parser = null;" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);
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

		System.out.println("Done writing Resources.java");
	}
}
