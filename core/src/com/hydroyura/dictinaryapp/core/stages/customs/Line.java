package com.hydroyura.dictinaryapp.core.stages.customs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Line extends Actor {

    private ShapeRenderer shapeRenderer;

    public Line() {
        this(Color.BLACK);
    }

    public Line(Color color) {
        setColor(color);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(shapeRenderer == null) this.shapeRenderer = new ShapeRenderer();

        if(batch.isDrawing()) batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(getColor());
        shapeRenderer.line(
                0f, getY() + getParent().getY(), getParent().getWidth(), getY() + getParent().getY()
        );
        shapeRenderer.end();

        if(!batch.isDrawing()) batch.begin();
    }

}