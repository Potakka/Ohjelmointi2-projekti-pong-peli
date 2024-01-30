package harjoitustyo;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.input.KeyCode;

public class Peli extends Application
{  

   @Override
   public void start(Stage stage)
   {  

      /*
      Tässä luodaan paneeli
      */  
      Pane paneeli = new Pane();
      /*
      Tässä luodaan meidän pelipallo
      */
      Circle pallukka = new Circle(50,50,15);
      pallukka.setFill(Color.BLACK);
      pallukka.setStroke(Color.BLACK);
      /*
      Tässä luodaan meidän mailat ja asetetaan niiden sijainnit.
      */
      Rectangle maila1 = new Rectangle();
      maila1.setWidth(15);
      maila1.setHeight(100);
      Rectangle maila2 = new Rectangle();
      maila2.setWidth(15);
      maila2.setHeight(100);
      maila1.setX(10);
      maila2.setX(480);

      /*
      Tässä on tekstiä, jotka näytetään pelin alussa, mitkä ovat pelaajien mailat ja mistä aloitetaan peli.
      */
      Text teksti1 = new Text(" Pelaaja 1 ");
      teksti1.setX(100);
      teksti1.setY(15);
      teksti1.setFont(Font.font("Courier", 15));
      Text teksti2 = new Text(" Pelaaja 2 ");
      teksti2.setX(350);
      teksti2.setY(15);
      teksti2.setFont(Font.font("Courier", 15));
      Text teksti3 = new Text("Paina space aloittaaksesi");
      teksti3.setX(50);
      teksti3.setY(300);
      teksti3.setFont(Font.font("Courier", 20));


      paneeli.getChildren().addAll(maila1,maila2,pallukka, teksti1, teksti2, teksti3);
      Scene kohtaus = new Scene(paneeli, 500, 500);
      paneeli.requestFocus();
      alkuIkkuna.setTitle("Pong-peli");
      alkuIkkuna.setScene(kohtaus);
      alkuIkkuna.show();

      /*
tästä eteenpäin hyödynsin toista koodia apuna
      */

      /*
      Luodaan animaatio pallolle.
      */

Timeline animaatio = new Timeline(new KeyFrame(Duration.millis(15), new EventHandler<ActionEvent>() {

/*
Arvot joiden avulla pallot liikkuu
*/
 public int x = 7;
 public int y = 3; 
 

  @Override
  public void handle(ActionEvent c) {

 /*
näiden lauseiden avulla pallo liikkuu
 */
   pallukka.setLayoutX(pallukka.getLayoutX() + x);
   pallukka.setLayoutY(pallukka.getLayoutY() + y);

/*
--------------------------------------------------
tästä eteenpäin en hyödyntänyt toista koodia apuna
---------------------------------------------------
*/

/*
Jos pallo menee ohi "mailoista" niin silloin vastapuolen pelaaja saa pisteen ja pallo tulee takaisin kentälle.
se on hieman arvaamaton, mutta toimii.
*/
   if (pallukka.getLayoutX() > 550) {
   pelaaja1.setPisteet(pelaaja1.getPisteet()+1);
   Pelaaja.kirjoitus(pelaaja1, pelaaja2);
   Pelaaja.luku();
    pallukka.setLayoutX(200);
    pallukka.setLayoutY(200);
   }
/*
Sama tässä
*/
   if (pallukka.getLayoutX() < -50) {
      pelaaja2.setPisteet(pelaaja2.getPisteet()+1);
      Pelaaja.kirjoitus(pelaaja1, pelaaja2);
      Pelaaja.luku();
       pallukka.setLayoutX(200);
       pallukka.setLayoutY(200);
   }
/*
Jos pallo osuu ylä tai alareunaan niin se kimpoaa takaisin.
*/
   if (pallukka.getLayoutY() > 450) {
      y = -y;
   }
   if (pallukka.getLayoutY() < -50) {
     y = 3;
   }

/*
Ja mikäli pallo osuuu mailaan niin se kimpoaa myös.
*/
   if (pallukka.getBoundsInParent().intersects(maila2.getBoundsInParent())) {
      x = -x;
   }

   if (pallukka.getBoundsInParent().intersects(maila1.getBoundsInParent())) {
      x = 7;
   }
}
}));
/*
Animaatio laitetaan pyörimään toistaiseksi.
*/
animaatio.setCycleCount(Timeline.INDEFINITE);

/*
Täältä löytyy kaikki ohjaukset peliä varten ensimmäinen pelaaja liikkuu näppäimistä W JA S, toinen pelaaja nuolinäppäimistä ylös ja alas, 
Space näppäimestä käynnistettään peli, pausesta voi laittaa paussin, 
escape näppäimestä Sammutetaan peli, 
samalla se pysäyttää pallon lukee pelaajien tiedot, tallentaa ne.
*/

/*
kun peli aloitetaan, niin samalla piilotetaan tekstit.
*/
kohtaus.setOnKeyPressed(e -> {
   if (e.getCode() == KeyCode.W) {
      maila1.setY(maila1.getY()-100);
   }
   else if (e.getCode()== KeyCode.S) {
      maila1.setY(maila1.getY()+100);
   }
   else if (e.getCode() == KeyCode.UP) {
      maila2.setY(maila2.getY()-100);
   }
   else if (e.getCode() == KeyCode.DOWN) {
      maila2.setY(maila2.getY()+100);
   }
   else if (e.getCode() == KeyCode.SPACE) {
      teksti1.setOpacity(0);
      teksti2.setOpacity(0);
      teksti3.setOpacity(0);
      animaatio.play();
   }
   else if (e.getCode() == KeyCode.PAUSE) {
      animaatio.pause();
   }
   else if (e.getCode() == KeyCode.ESCAPE) {
   animaatio.stop();
    System.exit(0);
   }
}); 
}

/*
Pääohjelma
*/
    public static void main(String [] args)
   {
      
      Application.launch(args);
}   
}