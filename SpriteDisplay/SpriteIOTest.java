package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;
/**
 * This is the test class for SpriteIO.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteIOTest {
		
	private SpriteManager createSprites() {
		SpriteManager obj1 = new SpriteManager(5);
		Sprite s1 = new Sprite("ABC100", 1, 2, 3, 4);
		Sprite s2 = new Sprite("DFG200", 4, 3, 2, 1);
		Sprite s3 = new Sprite("HIJ300", 2, 3, 4, 1);
		Sprite s4 = new Sprite("KLM400", 3, 4, 1, 2);
		obj1.addSprite(s1);
		obj1.addSprite(s2);
		obj1.addSprite(s3);
		obj1.addSprite(s4);
		return obj1;
	}

	/**
	 * This helps us test read.
	 * @param sp
	 * 	This is the sprite to be checked.
	 * @param id
	 * 	This is the id to check for.
	 * @param x
	 * 	This is the x to check for.
	 * @param y
	 * 	This is the y to check for.
	 * @param width
	 * 	This is the width to check for.
	 * @param height
	 * 	This is the height to check for.
	 */
	private void checkSprite(Sprite sp, String id, int x, int y,
				int width, int height) {
		assertEquals(x, sp.getX());
		assertEquals(y, sp.getY());
		assertEquals(width, sp.getWidth());
		assertEquals(height, sp.getHeight());
		assertEquals(id, sp.getId());
	}
	
	/**
	 * This tests the contrector that takes a SpriteManager.
	 */
	@Test
	public void testSpriteIOSpriteManager() {
		SpriteManager sm = createSprites();
		SpriteIO obj1 = new SpriteIO(sm);
		
		assertEquals(4, obj1.getSprites().size());
		assertEquals(sm.getSprite(0), obj1.getSprites().getSprite(0));
		assertEquals(sm.getSprite(1), obj1.getSprites().getSprite(1));
		assertEquals(sm.getSprite(2), obj1.getSprites().getSprite(2));
		assertEquals(sm.getSprite(3), obj1.getSprites().getSprite(3));
	}

	/**
	 * This tests our default constructor.
	 */
	@Test
	public void testSpriteIO() {
		SpriteIO obj1 = new SpriteIO();
		assertEquals(5, obj1.getSprites().capacity());
	}
	/**
	 * This tests for a null spritemanager given.
	 */
	@Test
	public void testSpriteIO2() {
		SpriteManager sm = null;
		SpriteIO obj1 = new SpriteIO(sm);
		assertEquals(5, obj1.getSprites().capacity());
	}

	/**
	 * This tests our read for scanners.
	 */
	@Test
	public void testReadScanner() {
		Scanner input = new Scanner("-93 51 95 83 SP1000\n"
				+ "-80 -101 43 92 SP 1001\n"
				+ "89 57 83 89 SP1002\n"
				+ "-13 17 90 SP1003\n"
				+ "28 -26 63 4 SP1004\n"
				+ "SP1005 29 120 49 100\n");
		SpriteIO sprite = new SpriteIO();
		assertTrue(sprite.read(input));
		SpriteManager sm = sprite.getSprites();
		assertEquals(4, sm.size());
		
		checkSprite(sm.getSprite(0), "SP1000", -93, 51, 95, 83);
		checkSprite(sm.getSprite(1), "SP 1001", -80, -101, 43, 92);
		checkSprite(sm.getSprite(2), "SP1002", 89, 57, 83, 89);
		checkSprite(sm.getSprite(3), "SP1004", 28, -26, 63, 4);
		
		input.close();
	}
	/**
	 * This tests for a null scanner.
	 */
	@Test
	public void testReadScanner2() {
		Scanner input = null;
		SpriteIO obj1 = new SpriteIO();
		assertFalse(obj1.read(input));
	}

	/**
	 * This tests our read from files
	 */
	@Test
	public void testReadString() {
		SpriteIO sprite = new SpriteIO();
		assertTrue(sprite.read("test1.txt"));
		SpriteManager sm = sprite.getSprites();
		assertEquals(4, sm.size()); 
		
		checkSprite(sm.getSprite(0), "SP1000", -93, 51, 95, 83);
		checkSprite(sm.getSprite(1), "SP 1001", -80, -101, 43, 92);
		checkSprite(sm.getSprite(2), "SP1002", 89, 57, 83, 89);
		checkSprite(sm.getSprite(3), "SP1004", 28, -26, 63, 4);
		
		
	}
	
	/**
	 * This tests for a null file name in read.
	 */
	@Test
	public void testReadString2() {
		SpriteIO sprite = new SpriteIO();
		String s = null;
		assertFalse(sprite.read(s));
	}
	/**
	 * This tests a null PrintWriter.
	 */
	@Test
	public void testWritePW() {
		SpriteIO obj1 = new SpriteIO();
		PrintWriter pw = null;
		assertFalse(obj1.write(pw));
	}
	/**
	 * This tests a null filename.
	 */
	@Test
	public void testWriteString2() {
		SpriteIO obj1 = new SpriteIO();
		String s = null;
		assertFalse(obj1.write(s));
	}

	/**
	 * This tests writing to a file. 
	 * and the printwriter write is used in it 
	 * so also tests that. 
	 */
	@Test
	public void testWriteString() {
		SpriteManager sManage = new SpriteManager(4);
		
		sManage.addSprite(new Sprite("SP1000", -93, 51, 95, 83));
		sManage.addSprite(new Sprite("SP 1001", -80, -101, 43, 92));
		sManage.addSprite(new Sprite("SP1002", 89, 57, 83, 89));
		sManage.addSprite(new Sprite("SP1004", 28, -26, 63, 4));
		
		SpriteIO spriteOut = new SpriteIO(sManage);
		
		spriteOut.write("testOut1.txt");
		
		File file = new File("testOut1.txt");
		try {
			Scanner in = new Scanner(file);
			String line;
			
			line = in.nextLine().trim();
			assertEquals("-93 51 95 83 SP1000", line);
			line = in.nextLine().trim();
			assertEquals("-80 -101 43 92 SP 1001", line);
			line = in.nextLine().trim();
			assertEquals("89 57 83 89 SP1002", line);
			line = in.nextLine().trim();
			assertEquals("28 -26 63 4 SP1004", line);
			
			in.close();
			
		} catch (FileNotFoundException e) {
			fail("Couldn't open file");
		}
		
		
		
	}

	

	
	
}
