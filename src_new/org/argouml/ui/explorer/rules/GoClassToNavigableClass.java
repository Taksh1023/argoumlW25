// $Id$
// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.ui.explorer.rules;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.argouml.model.ModelFacade;


import org.argouml.ui.AbstractGoRule;

public class GoClassToNavigableClass implements PerspectiveRule {

    public String getRuleName() {
        return "Class->Navigable Class";
    }

    public Collection getChildren(Object parent) {
        if (!(org.argouml.model.ModelFacade.isAClass(parent)))
            return null;
        
        List childClasses = new ArrayList();
        
        Collection ends = ModelFacade.getAssociationEnds(parent);
        if (ends == null)
            return null;
        
        java.util.Iterator enum = ends.iterator();
        while (enum.hasNext()) {
            Object ae = /*(MAssociationEnd)*/ enum.next();
            Object asc = ModelFacade.getAssociation(ae);
            Collection allEnds = ModelFacade.getConnections(asc);
            
            Object otherEnd = null;
            Iterator endIt = allEnds.iterator();
            if (endIt.hasNext()) {
                otherEnd = /*(MAssociationEnd)*/ endIt.next();
                if (ae != otherEnd && endIt.hasNext()) {
                    otherEnd = /*(MAssociationEnd)*/ endIt.next();
                    if (ae != otherEnd)
                        otherEnd = null;
                }
            }
            
            if (otherEnd == null)
                continue;
            if (!ModelFacade.isNavigable(otherEnd))
                continue;
            if (childClasses.contains(ModelFacade.getType(otherEnd)))
                continue;
            childClasses.add(ModelFacade.getType(otherEnd));
            // TODO: handle n-way Associations
        }
        return childClasses;
    }

}