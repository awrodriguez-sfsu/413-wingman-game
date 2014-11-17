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

public class ResourceClassBuilder {

	public static ArrayList<String> a = new ArrayList<>();

	public static void main(String[] args) {
		String resoucePath = "C:\\Users\\arodr101\\Desktop\\Resources";
		File folder = new File(resoucePath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith("png"))
					a.add(listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {}
		}

		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src//wingman//Resources.java"), "utf-8"));
			writer.write("package wingman;" + "\n");
			writer.write("\n");
			writer.write("import javax.imageio.ImageIO;" + "\n");
			writer.write("import java.awt.Image;" + "\n");
			writer.write("import java.io.File;" + "\n");
			writer.write("import java.io.IOException;" + "\n");
			writer.write("\n");
			writer.write("public class Resources {" + "\n");
			writer.write("private static Resources instance;" + "\n");
			writer.write("public Image ");

			String preFixed = "";
			for (int i = 0; i < a.size(); i++) {
				String prefix = a.get(i);
				prefix = prefix.substring(0, prefix.length() - 4);
				preFixed += prefix + ",";
			}
			writer.write(preFixed.substring(0, preFixed.length() - 1));
			writer.write(";" + "\n");
			writer.write("private Resources() { try { " + "\n");

			// add refs here
			for (int i = 0; i < a.size(); i++) {
				// sea = ImageIO.read(new File("Resources/water.png"));
				// explosion2_4 = ImageIO.read(new
				// File("Resources/explosion2_4.png"));
				String prefix = a.get(i);// get prefix filename
				prefix = prefix.substring(0, prefix.length() - 4);// get prefix
																	// filename
				// get = stuff
				writer.write(" " + prefix + " = ImageIO.read(new File(\"../Resources/" + a.get(i) + "\"));");
			}
			// add prefix hereF
			writer.write(" } catch (IOException e) { e.printStackTrace(); } }  public static Resources getInstance() { if (instance == null) { instance = new Resources();} return instance; } } ");
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {}
		}

	}
}
