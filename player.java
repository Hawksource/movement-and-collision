import java.awt.*;

public class player {
    Color color;
    int size;
    double speedMultiplier;
    int X;
    int Y;
    String Direction;
    public player(Color Color,int Size, double SpeedMultiplier, int x, int y,String direction) {
        color = Color;
        size = Size;
        speedMultiplier = SpeedMultiplier;
        X = x;
        Y = y;
        Direction = direction;
    }
}
