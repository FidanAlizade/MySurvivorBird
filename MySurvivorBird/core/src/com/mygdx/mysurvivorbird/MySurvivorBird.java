package com.mygdx.mysurvivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class MySurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture bird;
	Texture bee1;
	Texture bee2;
	Texture bee3;

	int gameState;

	float birdX;
	float birdY;

	int numberOfEnemySet = 4;
	float [] enemyX = new float[numberOfEnemySet];

	float velocity = 0;
	float enemyVelocity = 6f;
	float gravity = 0.2f;
	float distance = 0;

	Random random;
	float [] enemyOffSet1 = new float[numberOfEnemySet];
	float [] enemyOffSet2 = new float[numberOfEnemySet];
	float [] enemyOffSet3 = new float[numberOfEnemySet];

	ShapeRenderer shapeRenderer;

	Circle birdCircle;
	Circle [] enemyCircle1;
	Circle [] enemyCircle2;
	Circle [] enemyCircle3;

	int score = 0 ;
	int enemyScore = 0;

	BitmapFont font;
	BitmapFont font2;
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bird = new Texture("bird.png");

		birdX = Gdx.graphics.getWidth()/3;
		birdY = Gdx.graphics.getHeight()/3;

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(4);
		font2 = new BitmapFont();
		font2.setColor(Color.WHITE);
		font2.getData().setScale(6);

		distance = Gdx.graphics.getWidth()/2;
		random = new Random();

		bee1 = new Texture("bee.png");
		bee2 = new Texture("bee.png");
		bee3 = new Texture("bee.png");

		birdCircle = new Circle();
		enemyCircle1 = new Circle[numberOfEnemySet];
		enemyCircle2 = new Circle[numberOfEnemySet];
		enemyCircle3 = new Circle[numberOfEnemySet];


		for(int i = 0;i < numberOfEnemySet; i++){
			enemyX [i] =  Gdx.graphics.getWidth() - bee1.getWidth()/2  + (i * distance) ;

			enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
			enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

			enemyCircle1[i] = new Circle();
			enemyCircle2[i] = new Circle();
			enemyCircle3[i] = new Circle();

		}

		shapeRenderer = new ShapeRenderer();

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());



		if(gameState == 1){

			if(enemyX[enemyScore] < bird.getWidth()/2){
				score++;
				if(enemyScore < numberOfEnemySet-1){
					enemyScore++;
				}else {
					enemyScore = 0;
				}
			}

			if(Gdx.input.justTouched()){
				velocity= -7;
			}
			for (int i= 0; i<numberOfEnemySet;i++){

				if(enemyX[i] < 0){
					enemyX [i] = enemyX [i] + Gdx.graphics.getWidth()*2;
					enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

				}else{

					enemyX [i]= enemyX[i] - enemyVelocity;
				}
				enemyCircle1[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/38,Gdx.graphics.getHeight()/2+ enemyOffSet1[i]+Gdx.graphics.getHeight()/22,Gdx.graphics.getWidth()/38);
				enemyCircle2[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/38,Gdx.graphics.getHeight()/2+ enemyOffSet2[i]+Gdx.graphics.getHeight()/22,Gdx.graphics.getWidth()/38);
				enemyCircle3[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth()/38,Gdx.graphics.getHeight()/2+ enemyOffSet3[i]+Gdx.graphics.getHeight()/22,Gdx.graphics.getWidth()/38);


				batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2+ enemyOffSet1[i],Gdx.graphics.getWidth()/19,Gdx.graphics.getHeight()/11);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2+ enemyOffSet2[i],Gdx.graphics.getWidth()/19,Gdx.graphics.getHeight()/11);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2+ enemyOffSet3[i],Gdx.graphics.getWidth()/19,Gdx.graphics.getHeight()/11);


			}
			//yer cekimi
			if(birdY > 0){
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}else {
				gameState = 2;
			}

		}else if(gameState == 0){
			if(Gdx.input.justTouched()){
				gameState = 1;

			}
		} else if(gameState == 2){
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
			font2.draw(batch,"Game over! Tap to play again", 300,900);

			for(int i = 0;i < numberOfEnemySet; i++){
				enemyX [i] =  Gdx.graphics.getWidth() - bee1.getWidth()/2  + (i * distance) ;

				enemyOffSet1[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
				enemyOffSet2[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
				enemyOffSet3[i] = (random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

				enemyCircle1[i] = new Circle();
				enemyCircle2[i] = new Circle();
				enemyCircle3[i] = new Circle();

			}

			score = 0;
			velocity = 0;
			birdX = Gdx.graphics.getWidth()/3;
			birdY = Gdx.graphics.getHeight()/3;


		}

		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/19,Gdx.graphics.getHeight()/11);
		font.draw(batch,String.valueOf(score), 100, 200);

		batch.end();

		birdCircle.set(birdX+Gdx.graphics.getWidth()/38,birdY+Gdx.graphics.getHeight()/22,(Gdx.graphics.getWidth()/38));
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(Color.BLACK);
//		shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
 		   for (int i =0; i<numberOfEnemySet; i++){
//				shapeRenderer.circle(enemyCircle1[i].x,enemyCircle1[i].y,enemyCircle1[i].radius);
//				shapeRenderer.circle(enemyCircle2[i].x,enemyCircle2[i].y,enemyCircle2[i].radius);
//				shapeRenderer.circle(enemyCircle3[i].x,enemyCircle3[i].y,enemyCircle3[i].radius);
			   if(Intersector.overlaps(birdCircle,enemyCircle1[i])||Intersector.overlaps(birdCircle,enemyCircle2[i])|| Intersector.overlaps(birdCircle, enemyCircle3[i])){
				gameState =2;
			   }
 		   }

		shapeRenderer.end();

	}
	
	@Override
	public void dispose () {

	}
}
