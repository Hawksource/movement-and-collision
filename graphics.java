
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.management.PlatformLoggingMXBean;

public class graphics extends JPanel implements KeyListener, ActionListener, Runnable {
    static boolean wR = true;
    static boolean aR = true;
    static boolean sR = true;
    static boolean dR = true;
    static obsticle[] ol = new obsticle[4];
    public static player player1 = new player(Color.BLUE,30,1.5, 600,670, "right");
    private Thread physicsThread = new Thread(new physics());
    Timer t = new Timer(10,this);
    public void paintComponent(Graphics g) {
        obsticle o = new obsticle(400,400,100,100);
        obsticle j = new obsticle(800,300,50,100);
        obsticle k = new obsticle(1100,350,100,50);
        obsticle ground = new obsticle(0,700,1200,50);
        ol[0] = o;
        ol[1] = j;
        ol[2] = k;
        ol[3] = ground;
        t.start();
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(new Color(30,150,30));
        g.fillRect(0,700,1200,50);
        g.fillRect(400,400,100,100);
        g.fillRect(800,300,50,100);
        g.fillRect(1100,350,100,50);
        g.setColor(player1.color);
        g.fillRect(player1.X,player1.Y,player1.size,player1.size);
        g.setColor(Color.RED);
        ///start of direction indicator
        if(player1.Direction=="up") {
            g.drawLine(player1.X,player1.Y-1,player1.X+player1.size-1,player1.Y-1);
        }if(player1.Direction=="left") {
            g.drawLine(player1.X-1,player1.Y,player1.X-1,player1.Y+player1.size);
        }if(player1.Direction=="down") {
            g.drawLine(player1.X,player1.Y+player1.size,player1.X+player1.size-1,player1.Y+player1.size);
        }if(player1.Direction=="right") {
            g.drawLine(player1.X+player1.size,player1.Y,player1.X+player1.size,player1.Y+player1.size);
        }
        ///end od direction indicator

    }
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W) {
            wR = false;
            player1.Direction = "up";
        }if(code==KeyEvent.VK_A) {
            aR = false;
            player1.Direction = "left";
        }if(code==KeyEvent.VK_S) {
            sR = false;
            player1.Direction = "down";
        }if(code==KeyEvent.VK_D) {
            dR = false;
            player1.Direction = "right";
        }if(code==KeyEvent.VK_R) {
            player1.X = 10;
            player1.Y = 660;
        }
    }
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W) {
            wR = true;
        }if(code==KeyEvent.VK_A) {
            aR = true;
        }if(code==KeyEvent.VK_S) {
            sR = true;
        }if(code==KeyEvent.VK_D) {
            dR = true;
        }
    }
    public void keyTyped(KeyEvent e) {

         }
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    public void run() {
        System.out.println("graphics run block reached");
        physicsThread.start();

    }
}
///Start of physics block
class physics implements Runnable {
    public void run() {
        System.out.println("physics thread reached");
        int[][] oldPosBuffer = {{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        while(true) {
            try{
                Thread.sleep(10);
                if(!graphics.wR) {
                    for(int u = -1;u<graphics.ol.length-1;u++) {
                        for (int i = u + 1; i <= graphics.ol.length + 1; i++) {
                            if ((graphics.ol[i].x + graphics.ol[i].width > graphics.player1.X && graphics.player1.X > graphics.ol[i].x - graphics.player1.size) && (graphics.ol[i].y + graphics.ol[i].height > graphics.player1.Y && graphics.ol[i].y < graphics.player1.Y)) {
                                graphics.wR = true;
                                graphics.player1.Y +=2;
                                oldPosBuffer[0][0] = graphics.player1.X;
                                oldPosBuffer[0][1] = graphics.player1.Y;
                            } else if(oldPosBuffer[0][0]!=graphics.player1.X&&oldPosBuffer[0][1]!=graphics.player1.Y){
                                graphics.player1.Y -= 1;
                                oldPosBuffer[0][0] = -1;
                                oldPosBuffer[0][1] = -1;
                                break;
                            }

                        }
                    }
                }if(!graphics.aR) {
                    for(int u = -1;u<graphics.ol.length-1;u++) {
                    for(int i = u+1;i <= graphics.ol.length;i++) {
                        if((graphics.ol[i].x+graphics.ol[i].width > graphics.player1.X && graphics.player1.X > graphics.ol[i].x-graphics.player1.size)&&(graphics.ol[i].y+graphics.ol[i].height > graphics.player1.Y &&graphics.ol[i].y-graphics.player1.size < graphics.player1.Y)) {
                            graphics.aR = true;
                            graphics.player1.X +=2;
                            oldPosBuffer[1][0] = graphics.player1.X;
                            oldPosBuffer[1][1] = graphics.player1.Y;
                        }else if(oldPosBuffer[1][0]!=graphics.player1.X&&oldPosBuffer[1][1]!=graphics.player1.Y){
                            graphics.player1.X -=1;
                            oldPosBuffer[1][0] = -1;
                            oldPosBuffer[1][1] = -1;
                            break;
                        }
                    }
                    }
                }if(!graphics.sR) {
                    for(int u = -1;u<graphics.ol.length-1;u++) {
                        for (int i = u + 1; i <= graphics.ol.length; i++) {
                            if ((graphics.ol[i].x + graphics.ol[i].width > graphics.player1.X && graphics.player1.X > graphics.ol[i].x - graphics.player1.size) && (graphics.ol[i].y + graphics.ol[i].height > graphics.player1.Y && graphics.ol[i].y - graphics.player1.size < graphics.player1.Y)) {
                                graphics.sR = true;
                                graphics.player1.Y-=2;
                                oldPosBuffer[2][0] = graphics.player1.X;
                                oldPosBuffer[2][1] = graphics.player1.Y;
                            } else if(oldPosBuffer[2][0]!=graphics.player1.X&&oldPosBuffer[2][1]!=graphics.player1.Y){
                                graphics.player1.Y += 1;
                                oldPosBuffer[2][0] = -1;
                                oldPosBuffer[2][1] = -1;
                                break;
                            }
                        }
                    }
                }if(!graphics.dR) {
                    for (int u = -1; u < graphics.ol.length - 1; u++) {
                        for (int i = u + 1; i <= graphics.ol.length; i++) {
                            if ((graphics.ol[i].x + graphics.ol[i].width > graphics.player1.X && graphics.player1.X > graphics.ol[i].x - graphics.player1.size) && (graphics.ol[i].y + graphics.ol[i].height > graphics.player1.Y && graphics.ol[i].y - graphics.player1.size < graphics.player1.Y)) {
                                graphics.dR = true;
                                graphics.player1.X-=2;
                                oldPosBuffer[3][0] = graphics.player1.X;
                                oldPosBuffer[3][1] = graphics.player1.Y;
                            } else if(oldPosBuffer[3][0]!=graphics.player1.X&&oldPosBuffer[3][1]!=graphics.player1.Y){
                                graphics.player1.X += 1;
                                oldPosBuffer[3][0] = -1;
                                oldPosBuffer[3][1] = -1;
                                break;
                            }
                        }
                    }
                }
            }catch(Exception e) {}
        }
    }
}