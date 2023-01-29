package com.hydroyura.dictinaryapp.stages.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {

    private ShapeRenderer shapeRenderer;

    public Background() {
        this(Color.BLUE);
    }

    public Background(Color color) {
        setColor(color);
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(batch.isDrawing()) batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.setColor(getColor());
            shapeRenderer.rect(
                    getParent().getX(), getParent().getY(),
                    getParent().getWidth(), getParent().getHeight());
        shapeRenderer.end();
        if(!batch.isDrawing()) batch.begin();
    }
}