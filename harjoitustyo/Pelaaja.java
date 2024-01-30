package harjoitustyo;
/*
Pelaaja-luokka
*/

import java.util.Scanner;
import java.io.*;

/*
Pelaajalla on kaksi ominaisuutta
nimi ja pisteet
*/

public class Pelaaja implements Serializable {
public String nimi;
public int pisteet;

/*
Alustaja
*/
public Pelaaja(String nimi, int pisteet) {
this.nimi = nimi;
this.pisteet = pisteet;
}
/*
parametriton alustaja
*/
public Pelaaja() {
}


/** 
 * @return String
 */
/*
Tostring metodi
*/
public String toString() {
    return "Pelaajan nimi on " + nimi + " ja pisteet ovat " + getPisteet();
}

/** 
 * @return int
 */
/*
palauttaa pisteet
*/
public int getPisteet() {
return pisteet;
}

/** 
 * @return String
 */
/*
palauttaa nimen
*/
public String getNimi() {
return nimi;
}

/** 
 * @param nimi
 */
/*
Asettaa nimen
*/
public void setNimi(String nimi) {
this.nimi = nimi;
}

/** 
 * @param pisteet
 */
/*
Asettaa pisteet
*/
public void setPisteet(int pisteet) {
this.pisteet = pisteet;
}

/** 
 * @param pelaaja1
 * @param pelaaja2
 */
/*
Kirjoittaa oliot tiedostoon, tässä hyödynsin kurssimateriaaleja.
*/
public static void kirjoitus (Pelaaja pelaaja1, Pelaaja pelaaja2) {
 
    Scanner input = new Scanner(System.in);
    Pelaaja[] pelaajat = new Pelaaja[2];
    pelaajat[0] = pelaaja1;
    pelaajat[1] = pelaaja2;

        try {
            FileOutputStream tiedosto = new FileOutputStream("pelaajat.dat");
            ObjectOutputStream oliotiedosto = new ObjectOutputStream(tiedosto);
             oliotiedosto.writeObject(pelaajat[0]);
             oliotiedosto.writeObject(pelaajat[1]);
            System.out.println("kirjoitettiin tiedostoon");
            tiedosto.close();
            input.close();


        } catch (Exception e) {
            System.out.println("kirjoittaminen epäonnistui");
        }
}
/*
Lukee oliot tiedostosta, tässä hyödynsin kurssimateriaaleja.
*/
public static void luku () {
    File testitiedosto = new File("pelaajat.dat");
    if (testitiedosto.exists()) {
        Pelaaja [] pelaajat2 = new Pelaaja[2];
        try {
            FileInputStream tiedosto2 = new FileInputStream("pelaajat.dat");
            ObjectInputStream oliotiedosto2 = new ObjectInputStream(tiedosto2);
             pelaajat2[0] = (Pelaaja) oliotiedosto2.readObject();
             pelaajat2[1] = (Pelaaja) oliotiedosto2.readObject();
             oliotiedosto2.close();

        } catch (Exception e){
            System.out.println("lukeminen epäonnistui");
            
        }

        System.out.println("Tässä pelaajat tiedostosta:");
        for (Pelaaja pelaaja: pelaajat2) {
            System.out.println(pelaaja);
        }

    } else
        System.out.println("tiedostoa ei ole olemassa.");
}
/*
//Jos lisää tämän pääohjelman tähän luokkaan niin se lukee viimeisimmät pelaajien tiedot.
public static void main(String [] args)
   {
      Pelaaja.luku();
    }
*/   
}