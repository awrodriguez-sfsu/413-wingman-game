package wingman;

/*
 * Name: Anderson Tao
 * Description: Singleton Resource Class Builder
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
	private static ArrayList<String> withSpecs = new ArrayList<String>();

	public static void main(String[] args) {
		// String resourcePath = "/home/arod/Desktop/Resources";
		// String resourcePath = "Resources";
		String resourcePath = "C:\\Users\\arodr101\\Desktop\\Wingman\\Resources";
		String resourcesText = "C:\\Users\\arodr101\\Desktop\\Wingman\\resources.txt";

		System.out.println("Getting resources.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(resourcesText))) {
			for (String line; ( line = br.readLine() ) != null;) {
				withSpecs.add(line);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("Found " + withSpecs.size() + " resources with specifications");

		System.out.println("Getting resources");
		File folder = new File(resourcePath);
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
		System.out.println("Found " + images.size() + " resources");

		System.out.println("Moving resources folder");
		File directory = new File("resources");

		FileUtilities.copyDirectory(resourcePath, directory.getAbsolutePath());

		FileUtilities.copyFile(new File("C:\\Users\\arodr101\\Desktop\\Wingman\\resources.json"), new File("resources.json"));

		System.out.println("Writing Resources.java");
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src//wingman//Resources.java"), "utf-8"));
			writer.write("package wingman;" + newline);
			writer.write(newline);
			writer.write("import java.awt.Image;" + newline);
			writer.write("import java.io.File;" + newline);
			writer.write("import java.io.FileReader;" + newline);
			writer.write("import java.io.IOException;" + newline);
			writer.write("import java.util.ArrayList;" + newline);
			writer.write(newline);
			writer.write("import javax.imageio.ImageIO;" + newline);
			writer.write(newline);
			writer.write("import org.json.simple.JSONArray;" + newline);
			writer.write("import org.json.simple.JSONObject;" + newline);
			writer.write("import org.json.simple.parser.JSONParser;" + newline);
			writer.write(newline);
			writer.write("public class Resources {" + newline);
			writer.write(newline);
			writer.write(tab + "private static Resources instance;" + newline);

			writer.write(tab + "public Image ");
			String image_variables = "";
			for (int i = 0; i < images.size(); i++) {
				String prefix = images.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				image_variables += prefix + ", ";

				if (i % 5 == 0 && i > 0) {
					image_variables += newline;
					image_variables += tab + tab + tab + tab;
				}
			}
			writer.write(image_variables.substring(0, image_variables.length() - 2));
			writer.write(";" + newline);
			writer.write(newline);

			writer.write(tab + "public ResourceSpec ");
			String specs_variable = "";
			for (int i = 0; i < withSpecs.size(); i++) {
				String prefix = withSpecs.get(i) + "_spec";
				specs_variable += prefix + ", ";

				if (i % 5 == 0 && i > 0) {
					specs_variable += newline;
					specs_variable += tab + tab + tab + tab;
				}
			}
			writer.write(specs_variable.substring(0, specs_variable.length() - 2));
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
			writer.write(newline);
			for (int i = 0; i < withSpecs.size(); i++) {
				writer.write(tab + tab + withSpecs.get(i) + "_spec" + " = new ResourceSpec(\"" + withSpecs.get(i) + "\");" + newline);
			}
			writer.write(tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + "public static Resources getInstance() {" + newline);
			writer.write(tab + tab + "if (instance == null) {" + newline);
			writer.write(tab + tab + tab + "instance = new Resources();" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + "return instance;" + newline);
			writer.write(tab + "}" + newline);
			writer.write(newline);

			// writer.write(tab + tab + tab + tab + "" + newline);

			writer.write(tab + "public class ResourceSpec {" + newline);
			writer.write(newline);
			writer.write(tab + tab + "// Bounds" + newline);
			writer.write(tab + tab + "public double center_x, center_y, top, bottom, left, right;" + newline);
			writer.write(tab + tab + "public int number_of_shapes;" + newline);
			writer.write(newline);
			writer.write(tab + tab + "private JSONParser parser;" + newline);
			writer.write(newline);
			writer.write(tab + tab + "public ArrayList<Shape> shapes = new ArrayList<Shape>();" + newline);
			writer.write(newline);
			writer.write(tab + tab + "public ResourceSpec(String name) {" + newline);
			writer.write(tab + tab + tab + "parser = new JSONParser();" + newline);
			writer.write(tab + tab + tab + "try {" + newline);
			writer.write(tab + tab + tab + tab + "JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(\"../resources.json\"));" + newline);
			writer.write(tab + tab + tab + tab + "JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);" + newline);
			writer.write(tab + tab + tab + tab + "JSONObject bounds = (JSONObject) objectSpecifications.get(\"bounds\");" + newline);
			writer.write(tab + tab + tab + tab + "JSONArray center = (JSONArray) bounds.get(\"center\");" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab + "this.center_x = (double) center.get(0);" + newline);
			writer.write(tab + tab + tab + tab + "this.center_y = (double) center.get(1);" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab + "this.top = (double) bounds.get(\"top_edge\");" + newline);
			writer.write(tab + tab + tab + tab + "this.bottom = (double) bounds.get(\"bottom_edge\");" + newline);
			writer.write(tab + tab + tab + tab + "this.left = (double) bounds.get(\"left_edge\");" + newline);
			writer.write(tab + tab + tab + tab + "this.right = (double) bounds.get(\"right_edge\");" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab + "JSONObject collision = (JSONObject) objectSpecifications.get(\"collision\");" + newline);
			writer.write(tab + tab + tab + tab + "this.number_of_shapes = (int) collision.get(\"number_of_shapes\");" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + tab + "JSONObject JSONshapes = (JSONObject) collision.get(\"shapes\");" + newline);
			writer.write(tab + tab + tab + tab + "for (int i = 1; i <= number_of_shapes; i++) {" + newline);
			writer.write(tab + tab + tab + tab + tab + "JSONObject shape = (JSONObject) JSONshapes.get(\"shape\" + i);" + newline);
			writer.write(tab + tab + tab + tab + tab + "JSONArray params = (JSONArray) shape.get(\"params\");" + newline);
			writer.write(tab + tab + tab + tab + tab + "shapes.add(new Shape((String) shape.get(\"type\"), params));" + newline);
			writer.write(tab + tab + tab + tab + "}" + newline);
			writer.write(tab + tab + tab + "} catch (Exception exception) {" + newline);
			writer.write(tab + tab + tab + tab + "exception.printStackTrace();" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(tab + tab + "}" + newline);
			writer.write(newline);
			writer.write(tab + tab + "public class Shape {" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "public int x, y, width, height;" + newline);
			writer.write(tab + tab + tab + "public String type;" + newline);
			writer.write(newline);
			writer.write(tab + tab + tab + "public Shape(String type, JSONArray array) {" + newline);
			writer.write(tab + tab + tab + tab + "this.type = type;" + newline);
			writer.write(tab + tab + tab + tab + "this.x = (int) array.get(0);" + newline);
			writer.write(tab + tab + tab + tab + "this.y = (int) array.get(1);" + newline);
			writer.write(tab + tab + tab + tab + "this.width = (int) array.get(2);" + newline);
			writer.write(tab + tab + tab + tab + "this.height = (int) array.get(3);" + newline);
			writer.write(tab + tab + tab + "}" + newline);
			writer.write(tab + tab + "}" + newline);
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
