package net.cserny.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;

    private final PeteGame game;

    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private Pete pete;

    public GameScreen(PeteGame game) {
        this.game = game;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();

        pete = new Pete(game.getAssetManager().get("pete.png", Texture.class));

        tiledMap = game.getAssetManager().get("pete.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        clearScreen();
        draw();
//        drawDebug();
    }

    private void drawDebug() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        pete.drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    private void draw() {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        tiledMapRenderer.render();

        batch.begin();
        pete.draw(batch);
        batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.SKY.r, Color.SKY.g, Color.SKY.b, Color.SKY.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void update(float delta) {
        pete.update(delta);
        stopPeteLeavingScreen();
    }

    private void stopPeteLeavingScreen() {
        if (pete.getY() < 0) {
            pete.setPosition(pete.getX(), 0);
            pete.landed();
        }

        if (pete.getX() < 0) {
            pete.setPosition(0, pete.getY());
        }

        if (pete.getX() + Pete.WIDTH > WORLD_WIDTH) {
            pete.setPosition(WORLD_WIDTH - Pete.WIDTH, pete.getY());
        }
    }
}
