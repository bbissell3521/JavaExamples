package uwstout.cs145.projects.project1.drawing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This will store a SpriteManager and read
 *  from input and outputs including
 * files.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteIO {

	private SpriteManager man;

	/**
	 * This is a constructor that will copy
	 *  a SpriteManager into the class variable.
	 * 
	 * @param obj
	 *            This is the SpriteManager is what you want copied.
	 */
	public SpriteIO(SpriteManager obj) {
		if (obj == null) {
			man = new SpriteManager(5);
		} else {
			man = new SpriteManager(obj.capacity());
			for (int i = 0; i < obj.size(); i++) {
				man.addSprite(obj.getSprite(i));
			}
		}
	}

	/**
	 * This is the default constructor.
	 *  It will make a SpriteManager at size 5.
	 */
	public SpriteIO() {
		man = new SpriteManager(5);
	}

	/**
	 * This will get the SpriteManager object.
	 * 
	 * @return returns the SpriteManager object.
	 */
	public SpriteManager getSprites() {
		return man;
	}

	/**
	 * This is a private class for adding from files or strings.
	 * 
	 * @param str
	 *            This is the line to copy.
	 */
	private void assign(String str) {
		Scanner s = new Scanner(str);
		Sprite sprite;
		int xT;
		int yT;
		int widthT;
		int heightT;
		String idT;
		try {
			xT = s.nextInt();
			yT = s.nextInt();
			widthT = s.nextInt();
			heightT = s.nextInt();
			idT = s.nextLine();
			idT = idT.trim();

			sprite = new Sprite(idT, xT, yT, widthT, heightT);
			man.addSprite(sprite);
		} catch (Exception e) {
			// empty
		}
		s.close();

	}

	/**
	 * This will read from a scanner
	 * 
	 * @param s
	 *            This is the scanner to be read from.
	 * @return 
	 * This returns true if it could read the scanner.
	 *  This returns false if
	 *         the scanner was null.
	 */
	public boolean read(Scanner s) {
		if (s == null) {
			return false;
		}
		man.clear();
		while (s.hasNextLine()) {
			assign(s.nextLine());
		}

		return true;
	}

	/**
	 * This reads lines from a file to a Sprite IO.
	 * 
	 * @param fileName
	 *            This is the filename you want to look in.
	 * @return 
	 * Returns true if the method worked.
	 *  Returns false if it did not work.
	 */
	public boolean read(String fileName) {
		if (fileName == null) {
			return false;
		}
		man.clear();
		try {
			File file = new File(fileName);

			try {
				Scanner input = new Scanner(file);
				while (input.hasNext()) {
					assign(input.nextLine());
				}
				input.close();
			} catch (FileNotFoundException e) {
				return false;
			} catch (Exception e) {
				// dont do anything
			}

		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}

	/**
	 * This writes all data in man to a PrintWriter.
	 * 
	 * @param pw
	 *            This is the PrintWriter that you want the data written to.
	 * @return 
	 * This returns true if it worked.
	 *  This returns false if the PrintWriter
	 *         was null.
	 */
	public boolean write(PrintWriter pw) {
		if (pw == null) {
			return false;
		}
		Sprite sp;
		String s;
		String x;
		String y;
		String width;
		String height;
		String id;

		for (int i = 0; i < man.size(); i++) {
			sp = man.getSprite(i);
			x = "" + sp.getX();
			y = "" + sp.getY();
			width = "" + sp.getWidth();
			height = "" + sp.getHeight();
			id = sp.getId();
			s = " " + x + " " + y + " " + width + " " + height + " " + id;
			pw.println(s);
		}

		return true;
	}

	/**
	 * This writes to a file through a print writer.
	 * 
	 * @param fileName
	 *            This is the file name of the file you want written to.
	 * @return 
	 * This returns false if the file name is not found
	 *  or the action could not be performed.
	 *   This returns true if the action was performed.
	 */

	public boolean write(String fileName) {
		if (fileName == null) {
			return false;
		}

		try {
			File file = new File(fileName);
			PrintWriter p = new PrintWriter(file);
			write(p);
			p.close();
		} catch (FileNotFoundException e) {
			return false;
		}

		return true;
	}

}
