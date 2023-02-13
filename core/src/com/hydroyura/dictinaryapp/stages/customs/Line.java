package com.hydroyura.dictinaryapp.stages.customs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;


// FIXME: add builder
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

        Gdx.app.log("LINE", "X = " + getX() + "; Y = " + getY());
        Gdx.app.log("LINE_PARRENT", "X = " + getParent().getX() + "; Y = " + getParent().getY());


        if(!batch.isDrawing()) batch.begin();
    }

}
