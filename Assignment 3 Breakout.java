* File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
/** The velocity of the ball */
	private double vx, vy;
/** the time of delay of moving ball */
	private static final int DELAY=10;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/*
		 * there three chance to play the game.
		 * if player cannot bit all of the bricks,
		 * and the turns is not used out, remove all element and start again.
		 * if player bit all bricks, then show win!
		 * After three turns, if not win then show game over.
		 */
		for(int i=0; i<NTURNS; i++) {
			setup();
			playit();
			if(brickCounter>0){
				removeAll();
			}else if(brickCounter==0){
				removeAll();
				printWin();
				break;
			}
		}
		if(brickCounter>0){
			removeAll();
			printGameOver();
		}
	}
	/*
	 * draw bricks and paddle on canvas
	 */
	private void setup() {
		//setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		creatBricks();
		creatPaddle();
	}
	/*
	 * add bricks in 10 rows and 10 column,
	 * make sure these bricks in the center of window,
	 * The color of the bricks remain constant for two rows,
	 * and run in the following rainbow-like sequence: 
	 * RED, ORANGE, YELLOW, GREEN, CYAN
	 */
	private void creatBricks() {
		int bricks_x=(getWidth()-WIDTH)/2;
		// if the set window is not the appliction height and width,
		// make sure the game in the center of the window
		int bricks_y=(getHeight()-HEIGHT)/2+BRICK_Y_OFFSET;
		for(int row=0; row<NBRICK_ROWS;row++) {
			for(int column=0; column<NBRICKS_PER_ROW; column++) {
				int bx=bricks_x+column*(BRICK_WIDTH+BRICK_SEP);
				int by=bricks_y+row*(BRICK_HEIGHT+BRICK_SEP);
				brick=new GRect(bx,by,BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);
				switch(row){
					case 0:brick.setColor(Color.red);break;
					case 1:brick.setColor(Color.red);break;
					case 2:brick.setColor(Color.orange);break;
					case 3:brick.setColor(Color.orange);break;
					case 4:brick.setColor(Color.yellow);break;
					case 5:brick.setColor(Color.yellow);break;
					case 6:brick.setColor(Color.green);break;
					case 7:brick.setColor(Color.green);break;
					case 8:brick.setColor(Color.cyan);break;
					case 9:brick.setColor(Color.cyan);break;
				}
				add(brick);
			}
		}
	}
	/*
	 * add paddle on canvas.
	 * make sure the paddle at x coordinate in the center and y have setted
	 * paddle can be moved by mouse
	 */
	private void creatPaddle() {
		int paddle_x=(getWidth()-PADDLE_WIDTH)/2;
		// if the set window is not the appliction height and width,
		// make sure the game in the center of the window
		int paddle_y=getHeight()/2+HEIGHT/2-PADDLE_Y_OFFSET-PADDLE_HEIGHT;
		paddle=new GRect(paddle_x,paddle_y,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
	}
	/* Use mouse to move the paddle,
	 * if judgment guarantee the paddle move inside the window.
	 * only x coordinate changes, y does not change.
	 */
	public void mouseMoved(MouseEvent e) {
		if((e.getX()>0) && ((e.getX()+PADDLE_WIDTH)<getWidth())) {
			paddle.setLocation(e.getX(),getHeight()/2+HEIGHT/2-PADDLE_Y_OFFSET-PADDLE_HEIGHT);
			//paddle.move((e.getX()-(getWidth()-PADDLE_WIDTH)/2),0);
		}
	}
	/*
	 * create a new ball and wait mouse click,
	 * the vx is random and vy is 3,
	 * if paddle does not touch ball and the ball get out the window,
	 * the game over and jump the loop
	 * if all bricks bit jump the loop 
	 */
	private void playit() {
		createBall();
		waitForClick();
		getBallVelocity();
		while(true) {
			moveBall();
			if(ball.getY()>=getHeight()) {
				break;
			}
			if(brickCounter==0) {
				break;
			}
		}
	}
	/*
	 * The ball is created in the center of window
	 */
	private void createBall() {
		int ball_x=(getWidth()-BALL_RADIUS)/2;
		int ball_y=(getHeight()-BALL_RADIUS)/2;
		ball=new GOval(ball_x,ball_y,BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		addMouseListeners();
	}
	// Set the velocity of ball
	private void getBallVelocity() {
		vy=3.0;
		vx=rgen.nextDouble(1.0,3.0);
		if(rgen.nextBoolean(0.5)) vx=-vx;
	}
	/*
	 * if ball touch right or left wall,
	 * change vx to -vx
	 * if ball touch the top wall, then change vy to -vy
	 * check if have a collision
	 * if the collision on paddle,then bounce
	 * if the collision on brick, then the brick remove
	 */
	private void moveBall() {
		ball.move(vx,vy);
		pause(DELAY);
		if((ball.getX()-vx<=0 && vx<0) || (ball.getX()>=getWidth()-BALL_RADIUS*2 && vx>0)) {
			vx=-vx;
		}
		if(ball.getY()-vy<=0 && vy<0) {
			vy=-vy;
		}
		GObject collider = getCollidingObject();
		if(collider==paddle) {
			if(ball.getY()>=(getHeight()/2+HEIGHT/2-PADDLE_Y_OFFSET-PADDLE_HEIGHT-BALL_RADIUS*2)) {
				vy=-vy;
			}
		}else if(collider!=null) {
			remove(collider);
			vy=-vy;
			brickCounter--;
		}
	}
	/*
	 * judge if ball collide with paddle or bricks.
	 * use four corners of the square of the ball.
	 * if none of them collide anything then return null
	 */
	private GObject getCollidingObject() {
		if(getElementAt(ball.getX(),ball.getY())!=null) {
			return(getElementAt(ball.getX(),ball.getY()));
		}else if(getElementAt(ball.getX()+BALL_RADIUS*2,ball.getY())!=null) {
			return(getElementAt(ball.getX()+BALL_RADIUS*2,ball.getY()));
		}else if(getElementAt(ball.getX(),ball.getY()+BALL_RADIUS*2)!=null) {
			return(getElementAt(ball.getX(),ball.getY()+BALL_RADIUS*2));
		}else if(getElementAt(ball.getX()+BALL_RADIUS*2,ball.getY()+BALL_RADIUS*2)!=null) {
			return(getElementAt(ball.getX()+BALL_RADIUS*2,ball.getY()+BALL_RADIUS*2));
		}else{
			return null;
		}
	}
	private void printGameOver() {
		GLabel gameover=new GLabel("Game Over!");
		gameover.setFont("The New Man -38");
		gameover.setColor(Color.red);
		double gameover_x=gameover.getWidth();
		double gameover_y=gameover.getHeight();
		gameover.setLocation(getWidth()/2-gameover_x/2,getHeight()/2-gameover_y/2);
		add(gameover);
	}
	private void printWin() {
		GLabel win=new GLabel("Awsome!You Win!");
		win.setFont("The New Man -38");
		win.setColor(Color.red);
		double win_x=win.getWidth();
		double win_y=win.getHeight();
		win.setLocation(getWidth()/2-win_x/2,getHeight()/2-win_y/2);
		add(win);
	}
	/* private instance variables */
	private GRect brick;
	private GRect paddle;
	private GOval ball;
	private int brickCounter=100;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
