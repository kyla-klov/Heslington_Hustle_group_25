package com.main.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CollisionHandler {
    private final TiledMap tiledMap;
    private final int tileWidth;
    private final int tileHeight;

    private final float objWidth;
    private final float objHeight;
    private final float offSetX;
    private final float offSetY;
    private final ArrayList<TiledMapTileLayer> collisionLayers;

    public CollisionHandler(TiledMap tiledMap, int tileWidth, int tileHeight, float objWidth, float objHeight, float scaleX, float scaleY){
        this.tiledMap = tiledMap;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.objWidth = objWidth * scaleX;
        this.objHeight = objHeight * scaleY;
        this.offSetX = (objWidth - this.objWidth) / 2;
        this.offSetY = (objHeight - this.objHeight) / 2;
        this.collisionLayers = new ArrayList<>();
    }

    public void addCollisionLayers(String... args){
        for (String layer : args) {
            collisionLayers.add((TiledMapTileLayer) tiledMap.getLayers().get(layer));
        }
    }

    public ArrayList<Vector2> getTilePos(float x, float y){
        Vector2 bottomLeft = new Vector2 (Math.floorDiv((int) x, tileWidth), Math.floorDiv((int) y, tileHeight));
        Vector2 topRight = new Vector2 (Math.floorDiv((int) (x + objWidth), tileWidth), Math.floorDiv((int) (y + objHeight), tileHeight));

        ArrayList<Vector2> collisionTiles = new ArrayList<>();
        for (int i = (int) bottomLeft.x; i <= topRight.x; i++) {
            for (int j = (int) bottomLeft.y; j <= topRight.y; j++) {
                for (TiledMapTileLayer layer : collisionLayers) {
                    TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                    if (cell != null) {
                        collisionTiles.add(new Vector2(i, j));
                        break;
                    }
                }
            }
        }

        return collisionTiles;
    }

    public Rectangle tileToRect(int tileX, int tileY){
        return new Rectangle(tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    public Vector2 collidingSide(Rectangle movingObj, Rectangle other, int hor, int vert){
        Vector2 coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
        Vector2 coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
        Vector2 coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
        Vector2 coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);

        if (hor == 0 && vert == 1){
            return coll1;
        }
        else if (hor == 0 && vert == 2){
            return coll2;
        }
        else if (hor == 1 && vert == 0){
            return coll3;
        }
        else if (hor == 2 && vert == 0){
            return coll4;
        }
        else if (hor == 1 && vert == 1){
            if (coll1.x > coll3.x){
                return coll1;
            }
            else{
                return coll3;
            }
        }
        else if (hor == 1 && vert == 2){
            if (coll2.x > coll3.x){
                return coll2;
            }
            else{
                return coll3;
            }
        }
        else if (hor == 2 && vert == 1){
            if (coll1.x > coll4.x){
                return coll1;
            }
            else{
                return coll4;
            }
        }
        else if (hor == 2 && vert == 2){
            if (coll2.x > coll4.x){
                return coll2;
            }
            else{
                return coll4;
            }
        }

        return null;
    }

    public Vector2 adjustPosStep(float startX, float startY, float targX, float targY){
        ArrayList<Vector2> tiles = getTilePos(targX, targY);
        if (tiles.isEmpty()){
            return null;
        }
        //System.out.println();
        int vert = 0;
        int hor = 0;
        if (startY > targY){
            vert = 1;
        }
        else if (startY < targY){
            vert = 2;
        }
        if (startX > targX){
            hor = 1;
        }
        else if (startX < targX){
            hor = 2;
        }

        Rectangle obj = new Rectangle(startX, startY, objWidth, objHeight);
        Vector2 firstSide = null;
        for (Vector2 tile : tiles){
            Rectangle rect = tileToRect((int) tile.x, (int) tile.y);
            Vector2 side = collidingSide(obj, rect, hor, vert);
            if (side == null){
                continue;
            }
            if ((firstSide == null || side.x < firstSide.x) && side.x >= 0) {
                firstSide = side;
            }
        }

        if (firstSide == null){
            return null;
        }

        if ((int) firstSide.y == 0){
            return new Vector2(targX, startY - firstSide.x + 1);
        }
        else if ((int) firstSide.y == 1){
            return new Vector2(targX, startY + firstSide.x - 1);
        }
        else if ((int) firstSide.y == 2){
            return new Vector2(startX - firstSide.x + 1, targY);
        }
        else if ((int) firstSide.y == 3){
            return new Vector2(startX + firstSide.x - 1, targY);
        }
        return null;
    }

    public Vector2 adjustPos(float startX, float startY, float targX, float targY){
        startX += offSetX;
        startY += offSetY;
        targX += offSetX;
        targY += offSetY;
        Vector2 nextPos = adjustPosStep(startX, startY, targX, targY);
        Vector2 newPos = null;
        while (nextPos != null){
            newPos = nextPos;
            nextPos = adjustPosStep(startX, startY, nextPos.x, nextPos.y);
        }
        if (newPos != null){
            newPos.x -= offSetX;
            newPos.y -= offSetY;
        }
        return newPos;
    }
}
