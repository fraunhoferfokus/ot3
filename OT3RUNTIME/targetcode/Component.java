package targetcode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

//import de.fraunhofer.fokus.ttcn.framework.Framework;
//import de.fraunhofer.fokus.ttcn.tci.*;
import de.fraunhofer.fokus.ttcn.tri.*;
import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

import targetcode.Trace;

public abstract class Component
   implements
   Value
   // Value : to make it assignable , Comp := CompType.create
   ,
   TriComponentId // ens: implementation of TriComponentId
   // TriComponentId : for Port.setComponent
{
   Thread mythread;

   //--- Value methods

   public Type
   getType()
   {
      return null;
   }
   public boolean
   notPresent()
   {
      return false;
   }
   public String
   getValueEncoding()
   {
      return "";
   }

   public String
   getValueEncodingVariant()
   {
      return "";
   }

   //--- data

   private Component ComponentData = null;

   public void setComponentData(Component C)
   {
      ComponentData = C;
   }

   public Component getComponentData()
   {
      if (ComponentData == null)
      {
         Trace.print("component has no value");
      }
      return ComponentData;
   }

   //--- Port members

   Hashtable<String, Port> PT = new Hashtable<String, Port>();

   protected void EnterPort(String id, Port val)
   {
      TestOutput();
      Trace.print("EnterPort : " + id);
      PT.put(id, val);
      val.setComponent(this);
      portlist.add(val);
   }

   public Port GetPort(String id)
   {
      Port res;
      res = PT.get(id);
      if (res == null) {
         TestOutput();
         Trace.print("GetPort returns null : " + id);
      }
      return res;
   }

   //--- Var members

   Hashtable<String, Value> HT = new Hashtable<String, Value>();
   private List<TriPortId> portlist = new ArrayList<TriPortId>();

   protected void EnterVar(String id, Value val)
   {
      X.Assert(val != null);
      HT.put(id, val);
   }

   public Value GetVar(String id)
   {
      Value V = HT.get(id);
      X.Assert(V != null);
      return V;
   }

   //--- Testoutput

   static int ComponentCount = 0;
   public String MyName = "nobody";
   public String MyType = "nothing";
   public int MyNumber = ++ComponentCount;

   public void TestOutput()
   {
      Trace.print("Ich bin component " + MyNumber + "/" + MyName + " : " + MyType);
   }
   public void XTestOutput()
   {
      X.P("Ich bin component " + MyNumber + "/" + MyName + " : " + MyType);
   }
   
   // activity start/end
   public int State = 0;
   // 0: ininitially
   // 1: activity started
   // 2: activity ended

   // ens: implementation of TriComponentId methods
   @Override
   public String getComponentId() {
       return MyName;
   }

   @Override
   public String getComponentName() {
       return MyName;
   }

   @Override
   public String getComponentTypeName() {
       return MyType;
   }

   @Override
   public TriPortIdList getPortList() {
       return new TriPortIdListImpl(portlist);
   }

   @Override
   public boolean equals(TriComponentId component) {
       return MyName.equals(component.getComponentId());
   }

   public void Start(Thread T)
   // called from foreign thread 
   {
      mythread = T;
      T.start();
   }

   @SuppressWarnings("deprecation")
   public void Stop()
   // called from foreign thread 
   {
      mythread.stop();
   }

}
