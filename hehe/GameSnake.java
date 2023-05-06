package hehe;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import hehe.GameField.*;


public class GameSnake extends JFrame {

    public GameSnake() {
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(433,600);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
        setResizable(false);




    }
    public static void main(String []args){
        GameSnake gs=new GameSnake();





    }
}

