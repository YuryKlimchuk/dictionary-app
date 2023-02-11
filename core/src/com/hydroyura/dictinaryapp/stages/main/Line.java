package com.hydroyura.dictinaryapp.stages.main;

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
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(batch.isDrawing()) batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.setColor(getColor());
            shapeRenderer.line(
                    0f, getY(), getParent().getWidth(), getY()
            );
        shapeRenderer.end();

        if(!batch.isDrawing()) batch.begin();
    }

}
