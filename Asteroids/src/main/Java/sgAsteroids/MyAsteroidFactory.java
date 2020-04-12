package sgAsteroids;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.shape.Circle;

public class MyAsteroidFactory implements EntityFactory {

    @Spawns("asteroid")
    public Entity newAsteroid(SpawnData data) {
        return FXGL.entityBuilder()
                .from(data)
                .at(data.getX(), data.getY())
                .viewWithBBox(new Asteroid())
                .bbox(new HitBox("ASTEROID_BODY", BoundingShape.box(data.get("hitBoxX"), data.get("hitBoxY"))))
                .with(new CollidableComponent(true))
                .build();
    }

}
