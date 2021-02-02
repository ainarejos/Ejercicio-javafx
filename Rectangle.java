package sample;/*
Proyect name:Javafx
Created by:alumnesmx
Data:2/2/21
Description: asedehe aha ahe asedehe si sarandonga asedehe
*/

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Rectangles {
    class Posicio {
        int posX;
        int posY;
        public Posicio(int x,int y) {
            this.posX=x;
            this.posY=y;
        }
    }
    Posicio posicio;
    int velocitat=3;
    Pane panell;
    Node rectangle;

    public Rectangles(Pane panell,int posX,int posY, Color color){
        posicio=new Posicio(posX,posY);
        this.panell=panell;
        this.rectangle=new Rectangle(posicio.posX, posicio.posY,color);
        posicio.posX=0;
        posicio.posY=0;
        this.rectangle.setLayoutX(posicio.posX);
        this.rectangle.setLayoutY(posicio.posY);
        this.panell.getChildren().add(this.rectangle);
    }

    public void mouAmunt() {
        posicio.posY=posicio.posY-this.velocitat;
        this.repinta();
        System.out.println("Amunt pitjat");
    }

    public void mouAbaix() {
        posicio.posY=posicio.posY+this.velocitat;
        this.repinta();
        System.out.println("Abaix pitjat");
    }

    private void repinta() {
        this.rectangle.setLayoutX(posicio.posX);
        this.rectangle.setLayoutY(posicio.posY);
    }
}
