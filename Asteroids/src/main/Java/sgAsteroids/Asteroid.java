package sgAsteroids;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class Asteroid extends Polygon {

    Asteroid() {
        // randomize the factor
        Random rn = new Random();
        double scale = rn.nextDouble();

        // set color of asteroid
        this.setStroke(Color.GRAY);
        // this.setFill(Color.GRAY);

        // points of an hexagon
        Double[] pointList = {
                40.0, 10.0,
                80.0, 10.0,
                90.0, 30.0,
                80.0, 50.0,
                40.0, 50.0,
                30.0, 30.0
        };

//        for(int i = 0; i < pointList.length; i++) {
//            pointList[i] = morph(pointList[i], scale);
//        }
        this.getPoints().addAll(pointList);
    }

    private double morph(double pointList, double scale) {
        double variance = new Random().nextInt(20);

        return pointList * scale + variance;
    }
}
