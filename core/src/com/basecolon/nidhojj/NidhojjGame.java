package com.basecolon.nidhojj;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class NidhojjGame extends ApplicationAdapter {
    SpriteBatch batch;
    Sprite sprite;
    Texture image;
    World world;
    Body body;


    @Override
    public void create() {
        batch = new SpriteBatch();
        Box2D.init();

        batch = new SpriteBatch();
        image = new Texture(Gdx.files.internal("core/assets/head.gif"));
        sprite = new Sprite(image) {{
            setPosition(0, 0);
        }};

        // Create a world with normal gravity
        world = new World(new Vector2(0, -9.81F), true);

        // Create a debug-renderer for use when programming
        Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

        // Create a camera
        final OrthographicCamera camera = getCamera(100);

        // Set the debug renderer up
        debugRenderer.render(world, camera.combined);


        // Build the body
        BodyDef bodyDef = new BodyDef() {{
            type = BodyType.DynamicBody;
            position.set(sprite.getX(), sprite.getY());
        }};
        body = world.createBody(bodyDef);

        // Build the fixture
        final PolygonShape polygonShape= new PolygonShape() {{
            setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
        }};
        FixtureDef fixtureDef = new FixtureDef() {{
            shape = polygonShape;
            density = 0.5f;
            friction = 0.4f;
            restitution = 0.6f;
        }};
        Fixture fixture = body.createFixture(fixtureDef);

        polygonShape.dispose();
    }

    @Override
    public void render() {
        world.step(1/60f, 6, 2); // TODO: What do these parameters mean anyway?

        // Keep the sprite with the physics-body that it corresponds to
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        // You know the rest...
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        image.dispose();
        world.dispose();
    }

    public OrthographicCamera getCamera(float width) {
        return new OrthographicCamera(width, width * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
    }
}
