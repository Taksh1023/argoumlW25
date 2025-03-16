package org.argouml.activity2.diagram;

import java.awt.Polygon;

public class PolygonUtils {

    /**
     * Generates a concave pentagon shape given x, y, width, and height.
     *
     * @param x The x-coordinate of the top-left corner.
     * @param y The y-coordinate of the top-left corner.
     * @param w The width of the shape.
     * @param h The height of the shape.
     * @return A Polygon object representing the shape.
     */
    public static Polygon createConcavePentagon(int x, int y, int w, int h) {
        final int[] xs = new int[6];
        final int[] ys = new int[6];

        xs[0] = x;         ys[0] = y;
        xs[1] = x + w;     ys[1] = y;
        xs[2] = x + w;     ys[2] = y + h;
        xs[3] = x;         ys[3] = y + h;
        xs[4] = x + h / 2; ys[4] = y + h / 2;
        xs[5] = x;         ys[5] = y;

        return new Polygon(xs, ys, 6);
    }
}
