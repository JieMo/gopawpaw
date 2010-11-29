package com.jet.apps.databrowser.action;

import java.awt.Point;

import javax.swing.*;
import java.awt.event.*;

import com.jet.apps.databrowser.ui.DataBrowser;
import com.jet.apps.databrowser.model.*;
import com.jet.apps.databrowser.database.*;
import com.jet.apps.databrowser.sqllog.*;

/*
 * $Log: ConnectToSQLLog.java,v $
 * Revision 1.1  2007/02/18 18:02:27  bemocvs
 * Support for Java Web Start and SQL Log
 *
 * Revision 1.1.1.1  2002/12/05 00:08:00  bemocvs
 * initial checkin
 *
 * Revision 1.1  2002/04/21 00:55:29  bemocvs
 * db3 initial checkin
 *
 */

/**
 * Connects to the SQLLog database.  If the current window is
 * not connected it connects. If the current window is connected
 * it creates a new window, then connects it. 
 *
 * @author Paul Bemowski
 */
public class ConnectToSQLLog extends DefaultAction
{
   /** */
   public ConnectToSQLLog(DBSession session, JFrame parent) {
      super("Connect To SQLLog Database", session, parent);

      SQLLog sqllog=SQLLog.getInstance();
      if (sqllog == null)
         this.setEnabled(false);
   }

   /** */
   public void actionPerformed(ActionEvent ae) {
      log.writeDebug(4, "ConnectToSQLLog...");

      DBSession theSession=null;
      if (session.isConnected()) {
         DataBrowser newDB=new DataBrowser();
         DBSession newSession=newDB.getSession();

         Point p=parent.getLocation();
         newDB.setLocation(p.x+20, p.y+20);

         newDB.show();
         theSession=newSession;
      } else {
         theSession=session;
      }

      ConnectionInfo conInfo=SQLLog.getInstance().getConnectionInfo();

      theSession.setConnection(conInfo);
   }
}
