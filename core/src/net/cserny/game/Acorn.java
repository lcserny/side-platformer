package net.cserny.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Acorn {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    private final Rectangle collision;
    private final Texture texture;
    private final float x, y;

    public Acorn(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.collision = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public Rectangle getCollision() {
        return collision;
    }
}
