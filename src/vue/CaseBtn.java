/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import con.SelectCase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mod.CaseGrille;
import mod.Configuration;

/**
 *
 * @author ueve
 */
public class CaseBtn extends Button {

    private int numero;
    private static double xd,yd;    
    
    public CaseBtn(int num) {
        super(" ");
        numero = num;
        setPrefSize(50.0, 50.0);
        final int n = num;
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(InitialisationVue.getNavirePaneVue().isSelected()){
                    Accueil.getPartie().getJoueur().getGrille().getVue().select(n);
                    SelectCase.selectioner(cord()[0], cord()[1]);
                    InitialisationVue.getNavirePaneVue().setSelected(false);
                }
            }
        }); 
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                xd = me.getSceneX();
                yd = me.getY();
            }
        });
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(InitialisationVue.getNavirePaneVue().isSelected())
                    Accueil.getPartie().getJoueur().getGrille().getVue().focusCases(n);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(InitialisationVue.getNavirePaneVue().isSelected())
                    Accueil.getPartie().getJoueur().getGrille().getVue().UnfocusCases(n);
            }
        });
    }
    
    public int nextLineV(Integer x){
        if(x < 10) return 10;
        String val = x.toString();
        int line =Integer.parseInt(val.substring(0, 1))*10;
        return line+10;
    }
    
    public int nextLineH(Integer x){
        if(x < 10) return x+90;
        String val = x.toString();
        int line =Integer.parseInt(val.substring(1, 2))+90;
        return line+10;
    }
    
    public void setFocus(){
        this.setStyle("-fx-base: #b6e7c9;");
    }
    
    public void setUnFocus(){
        this.setStyle("");  
    }
    
    public void setExplosion(){
        ImageView im = new ImageView(new Image(GrilleVue.class.getResourceAsStream("/ressources/icons/explosion.png")));
        im.setFitHeight(50.0);
        im.setFitWidth(50.0);
        
        this.setGraphic(im);
    }
    
    public void setEmpty(){
        if(Configuration.getTypePartie().equals("Bataille Navale")){
            ImageView im = new ImageView(new Image(GrilleVue.class.getResourceAsStream("/ressources/icons/empty.png")));
            im.setFitHeight(50.0);
            im.setFitWidth(50.0);

            this.setGraphic(im);
            this.setText("");
        }
        else{
            CaseGrille cs = new CaseGrille("Vide", cord()[0], cord()[1]);
            
            int nbr = Accueil.getPartie().getJoueur().getGrille().procheCible(cs);
            
            ImageView im = new ImageView(new Image(GrilleVue.class.getResourceAsStream("/ressources/icons/"+nbr+".png")));
            im.setFitHeight(50.0);
            im.setFitWidth(50.0);
            this.setGraphic(im);
            this.setText("");
        }
    }
    
    public static double getXd() {
        return xd;
    }

    public static void setXd(double x) {
        xd = x;
    }

    public static double getYd() {
        return yd;
    }

    public static void setYd(double y) {
        yd = y;
    }

    public int getNumero() {
        return numero;
    }
    
    public int[] cord(){
        int tab[] = new int[2];
        if (numero <10) {
            tab[0] = 0;
            tab[1] = numero;
        }
        else{
            String val = String.valueOf(numero);
            tab[0] = Integer.parseInt(val.substring(0, 1));
            tab[1] = Integer.parseInt(val.substring(1, 2));
        }
        return tab;
    }
}
