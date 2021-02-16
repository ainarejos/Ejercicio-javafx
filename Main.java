import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Main extends Application {
    public static Circle cercle;
    public static Pane canvas;
    private Rectangle1 Rectangle1;
    private Rectangle2 Rectangle2;
    private Text text;
    private int jugador1=0;
    private int jugador2=0;
    private Text Game_over;
    private Text text2;

    @Override
    public void start(final Stage primaryStage) {

        canvas = new Pane();
        final Scene escena = new Scene(canvas, 1200, 800);

        primaryStage.setTitle("Bolla Rebotant");
        primaryStage.setScene(escena);
        primaryStage.show();

        int radi=15;
        cercle = new Circle(radi, Color.BLUE);
        cercle.relocate(600-radi, 400-radi);


        Rectangle1=new Rectangle1(canvas,10, 60, Color.BLUE);

        Rectangle2=new Rectangle2(canvas, 10, 60, Color.RED);

        text=new Text(jugador1 + "-" + jugador2);
        text.setFont(new Font(20));
        text.relocate(580, 20);
        text.setFill(Color.BLUE);

        text2=new Text("Pulsa espacio para comenzar");
        text2.setFont(new Font(20));
        text2.relocate(550, 100);
        text2.setFill(Color.BLUE);



        canvas.getChildren().addAll(cercle);
        canvas.getChildren().addAll(text);
        canvas.getChildren().addAll(text2);
        //canvas.getChildren().addAll(rectangle);
        //canvas.getChildren().addAll(rectangle2);



        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            // Formula en radians
            //double deltaX = 3*Math.cos(Math.PI/3);
            //double deltaY = 3*Math.sin(Math.PI/3);

            // Formula en graus
            double angle_en_radians =Math.toRadians(30);
            int velocitat=2;
            double deltaX = velocitat*Math.cos(angle_en_radians);
            double deltaY = velocitat*Math.sin(angle_en_radians);

            // Simulació gravitatòria
            double temps=0;
            final Bounds limits = canvas.getBoundsInLocal();

            @Override
            public void handle(final ActionEvent t) {
                if (jugador1<2&&jugador2<2){
                    //cercle.setLayoutX(cercle.getLayoutX() + deltaX/2);
                    cercle.setLayoutX(cercle.getLayoutX() + deltaX);
                    //cercle.setLayoutY(cercle.getLayoutY() + deltaY/3);
                    cercle.setLayoutY(cercle.getLayoutY() + deltaY);
                    //System.out.println(cercle.getLayoutX()+":"+cercle.getLayoutY());



                    final boolean alLimitDret = cercle.getLayoutX() >= (limits.getMaxX() - cercle.getRadius());
                    final boolean alLimitEsquerra = cercle.getLayoutX() <= (limits.getMinX() + cercle.getRadius());
                    final boolean alLimitInferior = cercle.getLayoutY() >= (limits.getMaxY() - cercle.getRadius());
                    final boolean alLimitSuperior = cercle.getLayoutY() <= (limits.getMinY() + cercle.getRadius());
                    final boolean alLititSuperiorRectangle1 = Rectangle1.rectangle.getLayoutY() <= (limits.getMinY());
                    final boolean alLititInferiorRectangle1 = Rectangle1.rectangle.getLayoutY() >= (limits.getMaxY()-60);
                    final boolean alLititSuperiorRectangle2 = Rectangle2.rectangle.getLayoutY() <= (limits.getMinY());
                    final boolean alLititInferiorRectangle2 = Rectangle2.rectangle.getLayoutY() >= (limits.getMaxY()-60);


                    if (alLititSuperiorRectangle1){
                        Rectangle1.posicio.posY=Rectangle1.posicio.posY+Rectangle1.velocitat;
                        //Rectangle1.rectangle.relocate(10, 0);
                        Rectangle1.rectangle.setLayoutY(Rectangle1.posicio.posY);
                    }
                    if (alLititInferiorRectangle1){
                        Rectangle1.posicio.posY=Rectangle1.posicio.posY-Rectangle1.velocitat;
                        //Rectangle1.rectangle.relocate(10, 740);
                        Rectangle1.rectangle.setLayoutY(Rectangle1.posicio.posY);
                    }
                    if (alLititSuperiorRectangle2){
                        Rectangle2.posicio.posY=Rectangle2.posicio.posY+Rectangle2.velocitat;
                        //Rectangle1.rectangle.relocate(10, 0);
                        Rectangle2.rectangle.setLayoutY(Rectangle2.posicio.posY);
                    }
                    if (alLititInferiorRectangle2){
                        Rectangle2.posicio.posY=Rectangle2.posicio.posY-Rectangle2.velocitat;
                        //Rectangle1.rectangle.relocate(10, 740);
                        Rectangle2.rectangle.setLayoutY(Rectangle2.posicio.posY);
                    }
                    if (alLimitInferior || alLimitSuperior) {
                        deltaY *= -1;
                    }
                    if(cercle.getBoundsInParent().intersects(Rectangle2.rectangle.getBoundsInParent())) {
                        //double angle_en_radians =Math.toRadians(30);
                        //int velocitat=3;
                        //double deltaX = velocitat*Math.cos(angle_en_radians);
                        //double deltaY = velocitat*Math.sin(angle_en_radians);
                        System.out.println("Impacte");
                    /*velocitat=velocitat+0.02;
                    deltaX = velocitat*Math.cos(angle_en_radians);*/
                        deltaX *= -1;
                    }
                    if(cercle.getBoundsInParent().intersects(Rectangle1.rectangle.getBoundsInParent())) {
                        //double angle_en_radians =Math.toRadians(30);
                        //int velocitat=3;
                        //double deltaX = velocitat*Math.cos(angle_en_radians);
                        //double deltaY = velocitat*Math.sin(angle_en_radians);
                        System.out.println("Impacte");
                    /*velocitat=velocitat+0.02;
                    deltaX = velocitat*Math.cos(angle_en_radians);*/
                        deltaX *= -1;
                    }
                    if (alLimitDret){
                        System.out.println("El jugador 1 ha marcado");
                        deltaX *= -1;
                        cercle.relocate(600-radi, 400-radi);
                        jugador1++;
                        text.setText(jugador1 + "-" + jugador2);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (alLimitEsquerra){
                        System.out.println("El jugador 2 ha marcado");
                        deltaX *= -1;
                        cercle.relocate(600-radi, 400-radi);
                        jugador2++;
                        text.setText(jugador1 + "-" + jugador2);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Game_over=new Text("Game Over");
                    Game_over.setFont(new Font(50));
                    Game_over.relocate(450, 100);
                    Game_over.setFill(Color.BLUE);
                    canvas.getChildren().add(Game_over);
                }
            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        //loop.play();
        canvas.requestFocus();canvas.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.SPACE)){
                loop.play();
                canvas.getChildren().remove(text2);
            }
            switch (e.getCode()) {
                case UP: Rectangle2.mouAmunt(); break;
                case DOWN: Rectangle2.mouAbaix(); break;
                case W: Rectangle1.mouAmunt(); break;
                case S: Rectangle1.mouAbaix(); break;
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}


