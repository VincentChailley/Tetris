package deroulementdujeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import deroulementdujeu.Piece.Figure;

public class Terrain extends JPanel implements ActionListener {
	
    final int terrainWidth = 10;
    final int terrainHeight = 20;
    int attaque = 0;
    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    Piece curPiece;
    Figure[] terrain;



    public Terrain(Tetris parent) {

       setFocusable(true);
       curPiece = new Piece();
       timer = new Timer(400, this);
       timer.start(); 

       statusbar =  parent.getStatusBar();
       terrain = new Figure[terrainWidth * terrainHeight];
       addKeyListener(new TAdapter());
       clearterrain();  
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }


    int squareWidth() { return (int) getSize().getWidth() / terrainWidth; }
    int squareHeight() { return (int) getSize().getHeight() / terrainHeight; }
    Figure PieceAt(int x, int y) { return terrain[(y * terrainWidth) + x]; }


    public void start()
    {
        if (isPaused)
            return;

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearterrain();

        newPiece();
        timer.start();
    }

    void pause()
    {
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusbar.setText("Pause...");
        } else {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int terrainTop = (int) size.getHeight() - terrainHeight * squareHeight();


        for (int i = 0; i < terrainHeight; ++i) {
            for (int j = 0; j < terrainWidth; ++j) {
                Figure Piece = PieceAt(j, terrainHeight - i - 1);
                if (Piece != Figure.NoPiece)
                    drawSquare(g, 0 + j * squareWidth(),
                               terrainTop + i * squareHeight(), Piece);
            }
        }

        if (curPiece.getPiece() != Figure.NoPiece) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           terrainTop + (terrainHeight - y - 1) * squareHeight(),
                           curPiece.getPiece());
            }
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    private void clearterrain()
    {
        for (int i = 0; i < terrainHeight * terrainWidth; ++i)
            terrain[i] = Figure.NoPiece;
    }

    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            terrain[(y * terrainWidth) + x] = curPiece.getPiece();
        }

        removeFullLines();

        if (!isFallingFinished){
            newPiece();
            attaque = 1;
        }    
    }

    private void newPiece()
    {
        curPiece.setRandomPiece();
        curX = terrainWidth / 2 + 1;
        curY = terrainHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setPiece(Figure.NoPiece);
            timer.stop();
            isStarted = false;
            statusbar.setText("GAME OVER !");
        }
    }

    private boolean tryMove(Piece newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= terrainWidth || y < 0 || y >= terrainHeight)
                return false;
            if (PieceAt(x, y) != Figure.NoPiece)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = terrainHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < terrainWidth; ++j) {
                if (PieceAt(j, i) == Figure.NoPiece) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < terrainHeight - 1; ++k) {
                    for (int j = 0; j < terrainWidth; ++j)
                         terrain[(k * terrainWidth) + j] = PieceAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setPiece(Figure.NoPiece);
            repaint();
        }
     }

    private void drawSquare(Graphics g, int x, int y, Figure Piece)
    {
        Color colors[] = { new Color(51, 102, 255), new Color(111, 220, 111), 
            new Color(204, 102, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[Piece.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
    }

    class TAdapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {

             if (!isStarted || curPiece.getPiece() == Figure.NoPiece) {  
                 return;
             }

             int keycode = e.getKeyCode();

             if (keycode == 'p' || keycode == 'P') {
                 pause();
                 return;
             }

             if (isPaused)
                 return;

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 tryMove(curPiece, curX - 1, curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 tryMove(curPiece, curX + 1, curY);
                 break;
             case KeyEvent.VK_DOWN:
            	 tryMove(curPiece, curX, curY - 1);
                 break;
             case KeyEvent.VK_UP:
                 tryMove(curPiece.rotate(), curX, curY);
                 break;
             case KeyEvent.VK_SPACE:
                 dropDown();
                 break;
             }

         }
     }
}