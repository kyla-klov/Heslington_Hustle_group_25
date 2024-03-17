package com.main.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CollisionHandler {
    private final TiledMap tiledMap;
    private final int tileWidth, tileHeight;

    private final float objWidth, objHeight;
    private final float offSetX, offSetY;
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

    public Vector2 getSideHit(float x, float y, Rectangle obj, int dir){
        Vector2 bottomLeft = new Vector2 (Math.floorDiv((int) x, tileWidth), Math.floorDiv((int) y, tileHeight));
        Vector2 topRight = new Vector2 (Math.floorDiv((int) (x + objWidth), tileWidth), Math.floorDiv((int) (y + objHeight), tileHeight));
        Vector2 firstSide = null;

        for (int i = (int) bottomLeft.x; i <= topRight.x; i++) {
            for (int j = (int) bottomLeft.y; j <= topRight.y; j++) {
                for (TiledMapTileLayer layer : collisionLayers) {
                    TiledMapTileLayer.Cell cell = layer.getCell(i, j);
                    if (cell != null) {
                        Vector2 side = collidingSide(obj, tileToRect(i, j), dir);
                        if (side != null && (firstSide == null || side.x < firstSide.x) && side.x >= 0) {
                            firstSide = side;
                        }
                        break;
                    }
                }
            }
        }

        return firstSide;
    }

    public Rectangle tileToRect(int tileX, int tileY){
        return new Rectangle(tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    public int getDirection(float startX, float startY, float targX, float targY){
        int dir = 0;
        if (startY > targY){
            dir += 1;
        }
        else if (startY < targY){
            dir += 2;
        }
        if (startX > targX){
            dir += 3;
        }
        else if (startX < targX) {
            dir += 6;
        }
        return dir;
    }

    public Vector2 collidingSide(Rectangle movingObj, Rectangle other, int dir){
        Vector2 coll1;
        Vector2 coll2;
        Vector2 coll3;
        Vector2 coll4;

        switch (dir){
            case 1:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                return coll1;
            case 2:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                return coll2;
            case 3:
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll3;
            case 4:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll1.x > coll3.x ? coll1 : coll3;
            case 5:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                coll3 = new Vector2(movingObj.x - (other.x + other.width), 2);
                return coll2.x > coll3.x ? coll2 : coll3;
            case 6:
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll4;
            case 7:
                coll1 = new Vector2(movingObj.y - (other.y + other.height), 0);
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll1.x > coll4.x ? coll1 : coll4;
            case 8:
                coll2 = new Vector2(other.y - (movingObj.y + movingObj.height), 1);
                coll4 = new Vector2(other.x - (movingObj.x + movingObj.width), 3);
                return coll2.x > coll4.x ? coll2 : coll4;
            default:
                return null;
        }
    }

    public Vector2 adjustPosStep(float startX, float startY, float targX, float targY){
        Rectangle obj = new Rectangle(startX, startY, objWidth, objHeight);
        int dir = getDirection(startX, startY, targX, targY);
        Vector2 side = getSideHit(targX, targY, obj, dir);
        if (side == null) {return null;}
        int sideNum = (int) side.y;
        switch(sideNum) {
            case 0:
                return new Vector2(targX, startY - side.x + 1);
            case 1:
                return new Vector2(targX, startY + side.x - 1);
            case 2:
                return new Vector2(startX - side.x + 1, targY);
            case 3:
                return new Vector2(startX + side.x - 1, targY);
            default:
                return null;
        }
    }

    public Vector2 adjustPos(float startX, float startY, float targX, float targY){
        startX += offSetX; startY += offSetY; targX += offSetX; targY += offSetY;
        Vector2 nextPos = adjustPosStep(startX, startY, targX, targY);
        Vector2 newPos = null;
        while (nextPos != null){
            newPos = nextPos;
            nextPos = adjustPosStep(startX, startY, nextPos.x, nextPos.y);
        }
        if (newPos != null){
            newPos.x -= offSetX; newPos.y -= offSetY;
        }
        return newPos;
    }
}
