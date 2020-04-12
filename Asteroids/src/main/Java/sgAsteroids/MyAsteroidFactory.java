package sgAsteroids;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class MyAsteroidFactory implements EntityFactory {

    @Spawns("asteroid")
    public Entity newAsteroid(SpawnData data) {
        Asteroid a = new Asteroid();
        ObservableList<Double> p = a.getPoints();
        Point2D[] points = {
                new Point2D(p.get(0), p.get(1)),
                new Point2D(p.get(2), p.get(3)),
                new Point2D(p.get(4), p.get(5)),
                new Point2D(p.get(6), p.get(7)),
                new Point2D(p.get(8), p.get(9)),
                new Point2D(p.get(10), p.get(11))
        };
        return FXGL.entityBuilder()
                .type(EntityType.ASTEROID)
                .from(data)
                .at(data.getX(), data.getY())
                .viewWithBBox(new Asteroid())
                .bbox(new HitBox("ASTEROID_BODY", BoundingShape.chain(points)))
                .with(new CollidableComponent(true))
                .build();
    }

}
