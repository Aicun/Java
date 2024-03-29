/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/*
 * $Id: Main.java,v 1.2 2009-11-11 14:17:30 pavel_bucek Exp $
 */

package jaxb.j2s.xmlType;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import jaxb.j2s.xmlType.address.USAddress;
import jaxb.j2s.xmlType.address.PurchaseOrderType;

public class Main {
    public static void main(String[] args) throws Exception {

        // Demonstates shipping and billing data printed in the property
        // order defined by the propOrder annotation element in class 
        // USAddress.        
        JAXBContext jc = JAXBContext.newInstance(PurchaseOrderType.class);
        Unmarshaller u = jc.createUnmarshaller();
        try {
            PurchaseOrderType poType = (PurchaseOrderType)u.unmarshal(new
            File("src/testData.xml"));
            System.out.println(poType.toString());
        } catch(javax.xml.bind.UnmarshalException e){
            System.out.println("Main: " + e);
        }    
    }
}

