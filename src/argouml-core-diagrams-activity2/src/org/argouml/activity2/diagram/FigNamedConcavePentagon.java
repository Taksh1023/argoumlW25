/* $Id$
 *****************************************************************************
 * Copyright (c) 2010-2011 Contributors - see below
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Bob Tarling
 *****************************************************************************
 */

package org.argouml.activity2.diagram;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;

import org.argouml.uml.diagram.DiagramSettings;
import org.tigris.gef.presentation.FigPoly;

class FigNamedConcavePentagon extends FigBasePresentation
        implements StereotypeDisplayer, NameDisplayer {

    public FigNamedConcavePentagon(
            final Object owner, Rectangle rect, Color lineColor,
            Color fillColor, Object modelElement, DiagramSettings settings) {
        super(owner, rect, lineColor, fillColor, modelElement, settings);
        createBorder(rect, lineColor, fillColor);
    }

    protected DiagramElement createBorder(
            final Rectangle rect,
            final Color lineColor,
            final Color fillColor) {
        final Poly poly = new Poly();
        poly.setPolygon(PolygonUtils.createConcavePentagon(rect.x, rect.y, rect.width, rect.height));
        return poly;
    }
    
    
    private class Poly extends FigPoly implements DiagramElement {
        Poly() {
            super();
        }
    }
}
