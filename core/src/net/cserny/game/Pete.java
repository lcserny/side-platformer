package net.cserny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Pete {

    private static final float MAX_X_SPEED = 2;
    private static final float MAX_Y_SPEED = 2;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    private final Rectangle collisionRectangle = new Rectangle(0, 0, WIDTH, HEIGHT);

    private float x = 0, y = 0, xSpeed = 0, ySpeed = 0;

    public void update() {
        Input input = Gdx.input;
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            xSpeed = MAX_X_SPEED;
        } else if (input.isKeyPressed(Input.Keys.LEFT)) {
            xSpeed = -MAX_X_SPEED;
        } else {
            xSpeed = 0;
        }

        x += xSpeed;
        y += ySpeed;
        updateCollisionRectangle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRectangle.x, collisionRectangle.y, collisionRectangle.width, collisionRectangle.height);
    }

    private void updateCollisionRectangle() {
        collisionRectangle.setPosition(x, y);
    }
}
