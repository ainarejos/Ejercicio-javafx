package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Main extends Application {
    public static Circle cercle;
    public static Pane canvas;
    public static Rectangle rectangle;
    public static Rectangle rectangle2;
    private Rectangles Rectangle1;
    private Rectangles Rectangle2;

    @Override
    public void start(final Stage primaryStage) {

        canvas = new Pane();
        final Scene escena = new Scene(canvas, 600, 400);

        primaryStage.setTitle("Bolla Rebotant");
        primaryStage.setScene(escena);
        primaryStage.show();

        int radi=15;
        cercle = new Circle(radi, Color.BLUE);
        cercle.relocate(200-radi, 200-radi);


        Rectangle1=new Rectangles(canvas,10, 60, Color.BLUE);
        Rectangle1.rectangle.relocate(5,200);

        Rectangle2=new Rectangles(canvas, 10, 60, Color.RED);
        Rectangle2.rectangle.relocate(585, 300);

        canvas.getChildren().addAll(cercle);
        //canvas.getChildren().addAll(rectangle);
        //canvas.getChildren().addAll(rectangle2);


        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {


            // Formula en radians
            //double deltaX = 3*Math.cos(Math.PI/3);
            //double deltaY = 3*Math.sin(Math.PI/3);

            // Formula en graus
            double angle_en_radians =Math.toRadians(30);
            int velocitat=3;
            double deltaX = velocitat*Math.cos(angle_en_radians);
            double deltaY = velocitat*Math.sin(angle_en_radians);

            // Simulació gravitatòria
            double temps=0;
            final Bounds limits = canvas.getBoundsInLocal();

            @Override
            public void handle(final ActionEvent t) {
                //cercle.setLayoutX(cercle.getLayoutX() + deltaX/2);

                cercle.setLayoutX(cercle.getLayoutX() + deltaX);
                //cercle.setLayoutY(cercle.getLayoutY() + deltaY/3);
                cercle.setLayoutY(cercle.getLayoutY() + deltaY);
                //System.out.println(cercle.getLayoutX()+":"+cercle.getLayoutY());



                final boolean alLimitDret = cercle.getLayoutX() >= (limits.getMaxX() - cercle.getRadius());
                final boolean alLimitEsquerra = cercle.getLayoutX() <= (limits.getMinX() + cercle.getRadius());
                final boolean alLimitInferior = cercle.getLayoutY() >= (limits.getMaxY() - cercle.getRadius());
                final boolean alLimitSuperior = cercle.getLayoutY() <= (limits.getMinY() + cercle.getRadius());



                if (alLimitInferior || alLimitSuperior) {
                    deltaY *= -1;
                }
                if(cercle.getBoundsInParent().intersects(Rectangle2.rectangle.getBoundsInParent())) {
                    //double angle_en_radians =Math.toRadians(30);
                    //int velocitat=3;
                    //double deltaX = velocitat*Math.cos(angle_en_radians);
                    //double deltaY = velocitat*Math.sin(angle_en_radians);
                    System.out.println("Impacte");
                    deltaX *= -1;
                }
                if(cercle.getBoundsInParent().intersects(Rectangle1.rectangle.getBoundsInParent())) {
                    //double angle_en_radians =Math.toRadians(30);
                    //int velocitat=3;
                    //double deltaX = velocitat*Math.cos(angle_en_radians);
                    //double deltaY = velocitat*Math.sin(angle_en_radians);
                    System.out.println("Impacte");
                    deltaX *= -1;
                }

            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
