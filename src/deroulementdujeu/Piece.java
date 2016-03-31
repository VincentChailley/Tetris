package deroulementdujeu;

import java.util.Random;
import java.lang.Math;


public class Piece {

    enum Figure { NoPiece, ZPiece, SPiece, LinePiece, 
               TPiece, SquarePiece, LPiece, MirroredLPiece };

    private Figure piecePiece;
    private int coords[][];
    private int[][][] coordsTable;


    public Piece() {

        coords = new int[4][2];
        setPiece(Figure.NoPiece);

    }

    public void setPiece(Figure Piece) {

         coordsTable = new int[][][] {
            {{ 0, 0 },{ 0, 0 },{ 0, 0 },{ 0, 0}},
            {{ 0, -1 },{ 0, 0 },{ -1, 0 },{ -1, 1}},
            {{ 0, -1 },{ 0, 0 },{ 1, 0 },{ 1, 1}},
            {{ 0, -1 },{ 0, 0 },{ 0, 1 },{ 0, 2}},
            {{ -1, 0 },{ 0, 0 },{ 1, 0 },{ 0, 1}},
            {{ 0, 0 },{ 1, 0 },{ 0, 1 },{ 1, 1}},
            {{ -1, -1 },{ 0, -1 },{ 0, 0 },{ 0, 1}},
            {{ 1, -1 },{ 0, -1 },{ 0, 0 },{ 0, 1 }}
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[Piece.ordinal()][i][j];
            }
        }
        piecePiece = Piece;

    }

    private void setX(int index, int x){ 
    	coords[index][0] = x; 
    }
    private void setY(int index, int y){ 
    	coords[index][1] = y; 
    }
    public int x(int index) { 
    	return coords[index][0]; 
    }
    public int y(int index){
    	return coords[index][1]; 
    }
    public Figure getPiece(){ 
    	return piecePiece; 
    }

    public void setRandomPiece()
    {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Figure[] values = Figure.values(); 
        setPiece(values[x]);
    }

    public int minX()
    {
      int m = coords[0][0];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][0]);
      }
      return m;
    }


    public int minY() 
    {
      int m = coords[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][1]);
      }
      return m;
    }

    public Piece rotate() 
    {
        if (piecePiece == Figure.SquarePiece){
            return this;
        }

        Piece result = new Piece();
        result.piecePiece = piecePiece;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }

    public Piece goDown()
    {
        if (piecePiece == Figure.SquarePiece){
            return this;
        }

        Piece result = new Piece();
        result.piecePiece = piecePiece;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}