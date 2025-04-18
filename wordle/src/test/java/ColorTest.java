import org.junit.jupiter.api.Test;

import wordle.Color;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    
    @Test
    void testClearColor(){
        Color couleur = new Color();
        assertEquals("\033[0m", couleur.ClearColor());
    }

    @Test
    void testgetGreen(){
        Color couleur = new Color();
        assertEquals("\033[32m", couleur.getGreen());
    }

    @Test
    void testgetRed(){
        Color couleur = new Color();
        assertEquals("\033[31m", couleur.getRed());
    }
    
    @Test
    void testgetGray(){
        Color couleur = new Color();
        assertEquals("\033[90m", couleur.getGray());
    }

    @Test
    void testgetYellow(){
        Color couleur = new Color();
        assertEquals("\033[33m", couleur.getYellow());
    }

}
