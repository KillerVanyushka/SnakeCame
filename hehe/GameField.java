package hehe;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GameField extends JPanel implements ActionListener {


    private final int Size=400;
    private final int bodySize=16;
    private final int AllDots=400;
    private Image body;
    private Image apple;
    private int appleX;
    private int appleY;
    private int x[]=new int[AllDots];
    private int y[]=new int[AllDots];
    private int partBody;
    private Timer timer;
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down =false;
    private boolean inGame=true;
    private int points;

    private static Image arbuz;
    private static Image border;

    private static Image restart;
    private static int count;


    public GameField(){
        setBackground(Color.black);
        loadImages();
        createBody();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void createBody(){

        partBody=3;
        points=-1;
        count=0;

        for(int i=0;i<partBody;i++){
            x[i]=48-i*bodySize;
            y[i]=48;


        }
        timer=new Timer(250,this);
        timer.start();
        createApple();

    }
    public void createApple(){

        appleX=new Random().nextInt(25)*bodySize;
        appleY=new Random().nextInt(25)*bodySize;
        if(count>0 && ((count % 5) == 0)){
            points+=10;
        }
        else{points++;}
        count++;

    }

    public void loadImages(){
        ImageIcon zmeika=new ImageIcon("body46.png");
        body =zmeika.getImage();
        ImageIcon yabloko=new ImageIcon("apple22.png");
        apple=yabloko.getImage();
        ImageIcon restartt=new ImageIcon("restart.png");
        restart=restartt.getImage();
        ImageIcon borderr=new ImageIcon("border1.png");
        border=borderr.getImage();
        ImageIcon arbuzz=new ImageIcon("arbuz.png");
        arbuz=arbuzz.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String str3="SUPER APPLE";
        g.setColor(Color.green);
        g.drawImage(border,0,416,null);
        String str1="Points:";
        g.setColor(Color.green);
        g.drawString(str1,180,500);
        String str2=Integer.toString(points);
        g.setColor(Color.GREEN);
        g.drawString(str2,225,500);
        if(count%5==0){
            g.drawString(str3,250,500);
        }
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < partBody; i++) {
                g.drawImage(body,x[i],y[i],this);
            }
        }
        else{
            String str="GAME OVER";
            g.setColor(Color.RED);
            g.drawString(str, 172,Size/2);
            g.drawImage(restart,20,500,null);
        }
    }

    public void move(){
        for (int i = partBody; i >0 ; i--) {
            x[i]=x[i-1];
            y[i]=y[i-1];

        }
        if(left){
            x[0]-=bodySize;
        }
        if(right){
            x[0]+=bodySize;
        }
        if(up){
            y[0]-=bodySize;
        }
        if(down){
            y[0]+=bodySize;
        }
    }
    public void bodyApple(){
        if(x[0]==appleX&&y[0]==appleY){
            partBody++;
            createApple();

        }
    }

    public void checkCollisions() {
        for (int i = partBody; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }

        }
        if (x[0] > Size) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > Size) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            bodyApple();
            checkCollisions();
            move();

        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_A && !right){
                left=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_D && !left){
                right=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_W && !down){
                right=false;
                up=true;
                left=false;
            }
            if(key==KeyEvent.VK_S && !up){
                left=false;
                right=false;
                down=true;

            }
        }
    }

}
