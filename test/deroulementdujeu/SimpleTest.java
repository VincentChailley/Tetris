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
		//La pièce effectue-t-elle bien une rotation ?
	}
	@Test
	public void testPiecegodown(){
		Piece tetrominos = new Piece();
		Piece result = tetrominos.goDown();
		Assert.assertNotSame("L'emplacement de la pièce doit avoir changé",result);
		//La pièce est-elle bien descendu d'un cran ?
	}
	@Test
	public void testrandomPiece(){
		Piece tetromino1 = new Piece();
		Piece tetromino2 = new Piece();
		Assert.assertNotEquals(tetromino1, tetromino2);
		//Les pièces sortent-elle de façon aléatoire ?
	}
}
