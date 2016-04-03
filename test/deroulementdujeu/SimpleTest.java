package deroulementdujeu;
import deroulementdujeu.Piece;

import org.junit.Assert;
import org.junit.Test;

public class SimpleTest {

	@Test
	public void testrotatePiece(){
		Piece tetrominos = new Piece();
		Piece result = tetrominos.rotate();
		Assert.assertNotSame("La pièce est différente puisqu'elle à tourné",result);
	}
	@Test
	public void testPiecegodown(){
		Piece tetrominos = new Piece();
		Piece result = tetrominos.goDown();
		Assert.assertNotSame("L'emplacement de la pièce doit avoir changé",result);
	}
	/*@Test
	public void testrotatePiece(){
		Piece tetrominos = new Piece();
		Piece result = tetrominos.rotate();
		Assert.assertNotSame("La pièce est différente puisqu'elle à tourné",result);
	}*/
}
