package com.github.eng1c2g4.eng1c2g4.entity.npc_spawning;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Disposable;

public class MapMask implements Disposable {
    private final Pixmap mask;

    public MapMask() {
        mask = new Pixmap(Gdx.files.internal("map/mask.png"));
    }

    /**
     *
     * @param x, the X (Horizontal) Coordinate of the center of the sprite
     * @param y  the Y (Vertical) Coordinate of the sprites center
     * @param w  Float value for the width of the sprite
     * @param h  Float value for the height of the sprite
     * @return areaCondition record containing two boolean (OutOfBounds, Water)
     */
    public areaCondition checkArea(float x, float y, float w, float h) {

        //check in map bounds
        float left = x - w / 2.0F, bottom = y - h / 2.0F;
        if (left < 0.0F || bottom < 0.0F || left + w > (float)this.mask.getWidth() | bottom + h > (float)this.mask.getHeight())
            return new areaCondition(true, false);


        //find the co-ord of the bottom left corner of the sprite
        int leftInt = Math.round(left);
        int top = Math.round(mask.getHeight() - (bottom + h));
        int heightInt = Math.round(h), widthInt = Math.round(w);

        boolean OutOfBounds = false, water = false;
        //Check each pixel for red or blue pixels, returning if a red is found or all pixels have been checked
        for (int j = top; j < top + heightInt; j++) {
            for (int i = leftInt; i < leftInt + widthInt; i++) {
                int pixel = mask.getPixel(i, j);
                //binary right shift and mask to isolate red pixels (pixels are stored as RGBA)
                if (((pixel >>> 24) & 0xFF) == 128) OutOfBounds = true;
                else if (((pixel >>> 8) & 0xFF) == 128) water = true;
                if (OutOfBounds && water) return new areaCondition(true, true);
            }
        }

        return new areaCondition(OutOfBounds, water);

    }
    public void dispose() {
        mask.dispose();
    }
    public record areaCondition(boolean outOfBounds, boolean water){}
}

