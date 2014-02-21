package targetcode;

import de.fraunhofer.fokus.ttcn.framework.Framework;
import de.fraunhofer.fokus.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

import java.util.LinkedList;

import org.etsi.ttcn.tci.*;

import targetcode.Trace;
import targetcode.SystemSetup;

        public abstract class Port implements TriPortId // ens: implementation of TriPortId
        {

           private static int PortCount = 0;
           protected String MyName = "nobody";
           protected String MyType = "nothing";
           private int MyNumber = ++PortCount;
           private TriComponentId component;

           public void TestOutput()
           {
              Trace.print("Ich bin port " + MyNumber + "/" + MyName + " : " + MyType);
           }

           private Port ConnectedPort = null;
           private boolean IsMappedPort = false;

           public void Connect(Port P)
           {
              P.TestOutput();
              ConnectedPort = P;
              IsMappedPort = false;

              // this => P also connects P => this
              P.ConnectedPort = this;
              P.IsMappedPort = false;
           }

           public void Map(Port P)
           {
              P.TestOutput();
              ConnectedPort = P;
              IsMappedPort = true;

              // this => P also maps P => this
              // P.ConnectedPort = this;
              // P.IsMappedPort = true;
           }

           public void Transmit(Value V)
           {
              if (ConnectedPort == null) {
                 Trace.print("not connected/mapped");
              }
              if (IsMappedPort) {
                 Trace.print("Transmit in Port");

                 TriCommunicationSA Adapter = SystemSetup.Adapter;
                 TciCDProvided Codec = SystemSetup.Codec;

                 //UserCode.Send(V);
                 
                 TriComponentId XcomponentId = this.component; 

                 TriPortId XtsiPortId = this;
                 TriAddress Xaddress = null;

                 Trace.print("Transmit in Port calls Codec.encode");
                 TriMessage XsendMessage = Codec.encode(V);

                 TriStatus Status;

                 Trace.print("Transmit in Port calls Adapter.triSend");
                 Status = Adapter.triSend (
                    XcomponentId,
                    XtsiPortId,
                    Xaddress,
                    XsendMessage
                 );

              }
              else {
                 ConnectedPort.Enqueue(V);
              }
           }

           //Value StoredValue = null;
           private LinkedList<Value> Kju = new LinkedList<Value>();

           public void Enqueue(Value V)
           {
              Trace.print("Enqueue in Port " + PortCount);
              TestOutput();
              Trace.print("(Kju.offer(V)) " + PortCount);
              Kju.offer(V);
           }

           public boolean HasMessage()
           {
              Trace.print("HasMessage in Port " + PortCount);
              boolean B = ( Kju.peek() != null );
              return B;
           }

           public Value Peek()
           {
              Trace.print("Peek in Port " + PortCount);
              Value V = Kju.peek();
              return V;
           }

           public Value Dequeue()
           {
              Trace.print("Dequeue in Port");
              Trace.print("(V = Kju.poll())");
              Value V;
              V = Kju.poll();
              return V;
           }
           
           // ens: implementation of TriPortId methods:
           @Override
           public String getPortName() {
               return MyName;
           }

           @Override
           public String getPortTypeName() {
               return MyType;
           }

           public void setComponent( TriComponentId comp) {
        	   component = comp;
           }
           
           @Override
           public TriComponentId getComponent() {
               return component;
           }

           @Override
           public boolean isArray() {
               return false;
           }

           @Override
           public int getPortIndex() {
               return 0;
           }


        }
