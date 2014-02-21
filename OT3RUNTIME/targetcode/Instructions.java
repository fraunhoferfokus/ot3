package targetcode;

import de.fraunhofer.fokus.ttcn.framework.Framework;
import de.fraunhofer.fokus.ttcn.tci.*;

import org.etsi.ttcn.tci.*;
import org.etsi.ttcn.tri.*;

import targetcode.Trace;
import targetcode.SystemSetup;

import java.util.HashMap;

public class Instructions
{

   public static boolean TC_RUNNING = false;

   private static Printer TheLogger = new Printer();

   public static void SetLogger(Printer L)
   {
      TheLogger = L;
   }

   static HashMap<String, Type> TypeTable = new HashMap<String, Type>();

   public static void RegisterType(Type Tp)
   {
      String name = Tp.getName();
      TypeTable.put(name, Tp);
   }

   public static Type GetRegisteredType(String name)
   {
      Type tp = TypeTable.get(name);
      return tp;
   }

   public static Component RunsOnComponent;
   static Component SystemComponent;

   public static void TestcasePrelude(String Id)
   {
      Trace.print("========= Testcase " + Id + " =========");

      TriCommunicationSA Adapter = SystemSetup.Adapter;

      if (Adapter == null) // xxxxxx
      {
         // FATAL("FTL 001"); // xxx
      } else {
         TriStatus Status = Adapter.triExecuteTestCase(null, null);
      }

      // SystemSetup.UserTM.tciTestCaseStarted((TciTestCaseId) null,
      // (TciParameterList) null, (float) 0.0);
   }

   public static void TestcasePostlude(String Id)
   {
      TriCommunicationSA Adapter = SystemSetup.Adapter;

      if (Adapter == null) // xxxxxx
      {
         // FATAL("FTL 002"); // xxxx
      } else {
         TriStatus Status = Adapter.triEndTestCase();
      }

   }

   public static void SetRunsOnComponent(Value RC)
   {
      RunsOnComponent = (Component) RC;
   }

   public static void SetSystemComponent(Value SC)
   {
      SystemComponent = (Component) SC;
   }

   public static void FunctionPrelude(String Id, Value RC)
   {
      Trace.print("========= Function " + Id + " =========");
   }

   public static void Trace(String str)
   {
      Trace.print("=== " + str);
   }

   public static boolean ToBoolean(Value v)
   {
      return ((BooleanValue) v).getBoolean();
   }

   public static targetcode.Port RunsOnCPort(String P)
   {
      Trace.print("RunsOnCPort : " + P);
      RunsOnComponent.TestOutput();
      Port res = RunsOnComponent.GetPort(P);
      if (res == null) {
         Trace.print("RunsOnCPort returns null : " + P);
         FATAL("FTL 003");
      }
      return res;
   }

   public static targetcode.Port SystemCPort(String P)
   {
      Trace.print("SystemCPort : " + P);
      Port res = SystemComponent.GetPort(P);
      if (res == null) {
         Trace.print("SystemCPort returns null : " + P);
         FATAL("FTL 004");
      }
      return res;
   }

   public static targetcode.Port ComponentPort(Value K, String P)
   {
      // X.P("[[[++++ComponentPort");
      // X.P(P);
      // RunsOnComponent.XTestOutput();
      // X.P("=K=");
      // ((Component) K).XTestOutput();
      // X.P("===");
      try {
         Component Comp = (Component) K;
         Component CompD = Comp.getComponentData();
         targetcode.Port res = CompD.GetPort(P);
         // X.P("]]]");
         return res;
      } catch (Exception e) {
         FATAL("FTL 005");
         return null;
      }
   }

   public static void Action(String str)
   {
      Trace.print("Action in Instructions");

      TriCommunicationSA Adapter = SystemSetup.Adapter;
      TriStatus Status = Adapter.triSutActionInformal(str);
   }

   public static void Send(targetcode.Port v1, Value v2)
   {
      Trace.print("Send in Instructions");
      Trace.print("Send in Instructions calls Port.Transmit");
      v1.Transmit(v2);
   }

   public static void Receive(targetcode.Port P, AltState state)
   {
      // P.TestOutput();

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         state.fired = P.HasMessage();
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
      }
   }

   public static boolean IsValueOfType(Value V, Type tp)
   {

      // if (V instanceof RecordValue) {
      Type tpv = V.getType();
      String idv = tpv.getName();
      String id = tp.getName();
      return idv.equals(id);
      // }

      // return false;
   }

   public static void ReceiveRecord(targetcode.Port P, AltState state, Type Tp)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();
            if (IsValueOfType(V, Tp)) {
               /*
                * if (V instanceof RecordValue) { IntegerValue csv =
                * (IntegerValue) V; int s = csv.getInteger(); String ss = "" +
                * s;
                * 
                * if (Str.equals("/joker/")) { res = true; } else { res =
                * ss.equals(Str); }
                */
               res = true;
            } else {
               res = false;
            }
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
      }
   }

   // achtung, baby
   // hier

   public static void ReceiveValue(targetcode.Port P, AltState state,
         Value Pattern)
   {
      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();

            res = Matches(V, Pattern);
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
      }
   }

   private static boolean Matches(Value Val, Value Pattern)
   {
      Value V = Match(Val, Pattern);
      BooleanValue BV = (BooleanValue) V;
      boolean b = BV.getBoolean();
      return b;
   }

   public static void ReceiveInt(targetcode.Port P, AltState state, String Str)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();
            if (V instanceof IntegerValue) {
               IntegerValue csv = (IntegerValue) V;
               int s = csv.getInteger();
               String ss = "" + s;

               if (Str.equals("/joker/")) {
                  res = true;
               } else {
                  res = ss.equals(Str);
               }
            } else {
               res = false;
            }
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
      }
   }

   public static void ReceiveStr(targetcode.Port P, AltState state, String Str)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();
            if (V instanceof CharstringValue) {
               CharstringValue csv = (CharstringValue) V;
               String s = csv.getString();

               if (Str.equals("/joker/")) {
                  res = true;
               } else {
                  res = s.equals(Str);
               }
            } else {
               res = false;
            }
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
      }
   }

   public static void ReceiveStrRedirect(targetcode.Port P, AltState state,
         String Str, Value Redir)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();
            if (V instanceof CharstringValue) {
               CharstringValue csv = (CharstringValue) V;
               String s = csv.getString();
               if (Str.equals("/joker/")) { // /joker/ xxx
                  res = true;
               } else {
                  res = s.equals(Str);
               }
            } else {
               res = false;
            }
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
         AssignValue(Redir, V);
      }
   }

   // achtung, baby
   public static void ReceiveValueRedirect(targetcode.Port P, AltState state,
         Value Pattern, Value Redir)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();

            res = Matches(V, Pattern);
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
         AssignValue(Redir, V);
      }
   }

   public static void ReceiveIntRedirect(targetcode.Port P, AltState state,
         String Str, Value Redir)
   {

      boolean res;

      Trace.print("Receive in Instructions");
      if (state.phase == 1) {
         Trace.print("(Phase 1)");
         P.TestOutput();
         if (P.HasMessage()) {
            Value V = P.Peek();
            if (V instanceof IntegerValue) {
               IntegerValue csv = (IntegerValue) V;
               int s = csv.getInteger();
               String ss = "" + s;
               if (Str.equals("/joker/")) { // /joker/ xxx
                  res = true;
               } else {
                  res = ss.equals(Str);
               }
            } else {
               res = false;
            }
         } else {
            res = false;
         }
         state.fired = res;
      } else {
         Trace.print("(Phase 2)");
         Trace.print("Receive Phase 2");
         Value V;
         V = P.Dequeue();
         Trace.print("(Got)");
         AssignValue(Redir, V);
      }
   }

   public static void Timeout(Value v, AltState state)
   {
      if (state.phase == 1) {
         Timer t = (Timer) v;
         state.fired = !t.Running();
      } else {
      }
   }

   public static void Done(Value v)
   {
      Trace.print("Waiting for Done ...");
      Component Comp = (Component) v;
      while (Comp.State != 2) // activity ended
      {
         try {
            Thread.sleep(100);
         } catch (Exception E) {
         }
      }
      Trace.print("Done");
   }

   public static void Repeat()
   {
      throw new RepeatEscape();
   }

   public static void StartTestcase(Lambda L)
   {
      L.Fun();
   }

   private static boolean KillFlag = false;

   public static void CheckKillFlag()
   {
      if (KillFlag) {
         KillFlag = false;
         throw new TestCaseKilled();
      }
   }

   public static void KillTestcase()
   {
      KillFlag = true;
      // XciTMRequiredImpl.KillTestCase();
   }

   public static String LastVerdict = "NOVERDICT";

   public static void SetFirstVerdict()
   {
      LastVerdict = "NONE";
   }

   public static void SetErrorVerdict()
   {
      LastVerdict = "ERROR";
   }

   public static void SetVerdict(Value v)
   {
      VerdictValue vv = (VerdictValue) v;
      int i = vv.getVerdict();

      String s;
      if (i == VerdictValue.ERROR)
         s = "ERROR";
      else if (i == VerdictValue.FAIL)
         s = "FAIL";
      else if (i == VerdictValue.INCONC)
         s = "INCONC";
      else if (i == VerdictValue.NONE)
         s = "NONE";
      else if (i == VerdictValue.PASS)
         s = "PASS";
      else
         s = "???";

      LastVerdict = s;

   }

   public static Value ComponentVar(Value K, String id)
   {
      try {
         Component Comp = (Component) K;
         Value res = Comp.GetVar(id);
         return res;
      } catch (Exception e) {
         FATAL("FTL 006");
         return null;
      }
   }

   public static Value FieldSelection(Value v, String id)
   {
      RecordValue rv = (RecordValue) v;
      Value res = rv.getField(id);
      return res;
   }

   public static void AssignCompoundValue(Value lhs, Value rhs)
   {

      RecordValueImpl rv = (RecordValueImpl) lhs;
      CompoundValue cv = (CompoundValue) rhs;
      rv.vals = cv.vals;
   }

   public static void AssignValue(Value lhs, Value rhs)
   {
      if (lhs instanceof IntegerValue) {
         IntegerValueImpl x = (IntegerValueImpl) lhs;
         IntegerValueImpl y = (IntegerValueImpl) rhs;
         x.setInteger(y.getInteger());
      } else if (lhs instanceof CharstringValue) {
         CharstringValueImpl x = (CharstringValueImpl) lhs;
         CharstringValueImpl y = (CharstringValueImpl) rhs;
         x.setString(y.getString());
      } else if (lhs instanceof BooleanValue) {
         BooleanValueImpl x = (BooleanValueImpl) lhs;
         BooleanValueImpl y = (BooleanValueImpl) rhs;
         x.setBoolean(y.getBoolean());
      } else if (lhs instanceof Component) {
         ((Component) lhs).setComponentData((Component) rhs);
      } else if (lhs instanceof RecordValue) {
         RecordValueImpl lhs2 = (RecordValueImpl) lhs;
         RecordValueImpl rhs2 = (RecordValueImpl) rhs;
         lhs2.assignRecord(rhs2);
      } else {
         System.out.println("lhs=" + lhs);
         System.out.println("cannot assign value !");
      }
   }

   public static void AltConstruct(AltState state, Lambda[] Ls)
   {

      boolean again;
      do {
         again = false;

         state.phase = 1;
         state.fired = false;
         int L = -1;
         while (!state.fired) {
            for (int I = 0; I < Ls.length / 2; I++) {
               Ls[2 * I].Fun();
               if (state.fired) {
                  L = 2 * I;
                  break;
               }
            }
         }

         state.phase = 2;
         Ls[L].Fun();

         try {
            Ls[L + 1].Fun();
         } catch (RepeatEscape Exc) {
            again = true;
         }

      } while (again);
   }

   private static int NumberOfThreads = 0;

   public static void StartThread()
   {
      NumberOfThreads++;
   }

   public static void EndThread()
   {
      NumberOfThreads--;

   }

   public static void ReportThreads()
   {
      // System.out.println("Number of threads: "+NumberOfThreads);
   }

   public static void StartComponent(Value C, Lambda L)
   {
      Component K = (Component) C;

      final Lambda L0 = L;
      Thread T = new Thread()
      {
         public void run()
         {
            StartThread();
            try {

               Trace.print("start component: run()");
               L0.Fun();
            } catch (Exception E) {
               FATAL("exception in component");
            }
            EndThread();
         }
      };
      K.Start(T);
      // T.start();
   }

   public static void StopComponent(Value C)
   {
      Component K = (Component) C;
      K.Stop();
   }

   public static void ComponentActivityStarts(Value C)
   {
      Trace.print("component activity starts");
      Component Comp = (Component) C;
      Comp.State = 1; // activity started
   }

   public static void ComponentActivityEnds(Value C)
   {
      Trace.print("component activity ends");
      Component Comp = (Component) C;
      Comp.State = 2; // activity ended
   }

   public static void Connect(targetcode.Port p1, targetcode.Port p2)
   {
      Trace.print("Connect ...");
      if (p1 == null) {
         FATAL("FTL 007");
         Abort("connect: port 1 == null");
      }
      if (p2 == null) {
         FATAL("FTL 008");
         Abort("connect: port 2 == null");
      }
      p1.TestOutput();
      p2.TestOutput();
      p1.Connect(p2);
   }

   public static void Map(targetcode.Port p1, targetcode.Port p2)
   {
      Trace.print("Map ...");
      if (p1 == null) {
         FATAL("FTL 009");
         Abort("map: port 1 == null");
      }
      if (p2 == null) {
         FATAL("FTL 010");
         Abort("map: port 2 == null");
      }
      p1.TestOutput();
      p2.TestOutput();
      p1.Map(p2);
      // UserCode.Map(p1, p2);

      TriCommunicationSA Adapter = SystemSetup.Adapter;
      if (Adapter == null) {
         FATAL("FTL 011");
         Abort("Adapter == null");
      }
      Adapter.triMap(p1, p2);
   }

   public static void Unmap(targetcode.Port p1, targetcode.Port p2)
   {
      Trace.print("Unmap ...");
      p1.TestOutput();
      p2.TestOutput();

      TriCommunicationSA Adapter = SystemSetup.Adapter;
      Adapter.triUnmap(p1, p2);
   }

   public static void StartTimer(Value v)
   {
      Timer t = (Timer) v;
      t.StartTimer(5);
   }

   public static void StartTimerExpr(Value v, Value e)
   {
      FloatValue flv = (FloatValue) e;
      double d = flv.getFloat();
      Timer t = (Timer) v;
      t.StartTimer(d);
   }

   public static Value VerdictPass()
   {
      VerdictValue result = Framework.GetFactory().VerdictValue();
      result.setVerdict(VerdictValue.PASS);
      return result;
   }

   public static Value VerdictFail()
   {
      VerdictValue result = Framework.GetFactory().VerdictValue();
      result.setVerdict(VerdictValue.FAIL);
      return result;
   }

   public static Value VerdictInconc()
   {
      VerdictValue result = Framework.GetFactory().VerdictValue();
      result.setVerdict(VerdictValue.INCONC);
      return result;
   }

   public static Value VerdictNone()
   {
      VerdictValue result = Framework.GetFactory().VerdictValue();
      result.setVerdict(VerdictValue.NONE);
      return result;
   }

   public static Value VerdictError()
   {
      VerdictValue result = Framework.GetFactory().VerdictValue();
      result.setVerdict(VerdictValue.ERROR);
      return result;
   }

   public static Value IntegerConstant(int x)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      result.setInteger(x);
      return result;
   }

   public static Value FloatConstant(double x)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      result.setFloat((float) x);
      return result;
   }

   public static Value BooleanConstant(boolean x)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      result.setBoolean(x);
      return result;
   }

   public static Value BitstringConstant(String x)
   {
      BitstringValue result = Framework.GetFactory().BitstringValue();
      result.setString(x);
      return result;
   }

   public static Value OctetstringConstant(String x)
   {
      OctetstringValue result = Framework.GetFactory().OctetstringValue();
      result.setString(x);
      return result;
   }

   public static Value HexstringConstant(String x)
   {
      HexstringValue result = Framework.GetFactory().HexstringValue();
      result.setString(x);
      return result;
   }

   public static Value CharstringConstant(String x)
   {
      CharstringValue result = Framework.GetFactory().CharstringValue();
      result.setString(x);
      return result;
   }

   public static Value UniversalCharstringConstant(String x)
   {
      UniversalCharstringValue result = Framework.GetFactory()
            .UniversalCharstringValue();
      result.setString(x);
      return result;
   }

   public static Value Or(Value x, Value y)
   {

      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((BooleanValue) x).getBoolean()
               || ((BooleanValue) y).getBoolean());
      }
      return result;
   }

   public static Value Xor(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((BooleanValue) x).getBoolean()
               ^ ((BooleanValue) y).getBoolean());
      }
      return result;
   }

   public static Value And(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((BooleanValue) x).getBoolean()
               && ((BooleanValue) y).getBoolean());
      }
      return result;
   }

   public static Value Not(Value x)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, x)) {
         result.setBoolean(!((BooleanValue) x).getBoolean());
      }
      return result;
   }

   public static Value Not4b(Value x)
   {

      if (checkOperandsPresent(x, x)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i) ^ 1);
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i) ^ 15);
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i) ^ 255);
            }
            return result;
         }
      }
      return null;
   }

   public static Value And4b(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i)
                     & ((BitstringValue) y).getBit(i));
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i)
                     & ((HexstringValue) y).getHex(i));
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i)
                     & ((OctetstringValue) y).getOctet(i));
            }
            return result;
         }
      }
      return null;
   }

   public static Value Or4b(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i)
                     | ((BitstringValue) y).getBit(i));
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i)
                     | ((HexstringValue) y).getHex(i));
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i)
                     | ((OctetstringValue) y).getOctet(i));
            }
            return result;
         }
      }
      return null;
   }

   public static Value Xor4b(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i)
                     ^ ((BitstringValue) y).getBit(i));
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i)
                     ^ ((HexstringValue) y).getHex(i));
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            for (int i = 0; i < len; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i)
                     ^ ((OctetstringValue) y).getOctet(i));
            }
            return result;
         }
      }
      return null;
   }

   public static Value RotateLeft(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            int shift = ((IntegerValue) y).getInteger();
            result.setLength(len);
            for (int i = 0; i < len - shift; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i + shift - len));
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i + shift - len));
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setOctet(i,
                     ((OctetstringValue) x).getOctet(i + shift - len));
            }
            return result;
         } else if (x instanceof CharstringValue) {
            CharstringValue result = Framework.GetFactory().CharstringValue();
            int len = ((CharstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setChar(i, ((CharstringValue) x).getChar(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setChar(i, ((CharstringValue) x).getChar(i + shift - len));
            }
            return result;
         } else if (x instanceof UniversalCharstringValue) {
            UniversalCharstringValue result = Framework.GetFactory()
                  .UniversalCharstringValue();
            int len = ((UniversalCharstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setChar(i,
                     ((UniversalCharstringValue) x).getChar(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setChar(i,
                     ((UniversalCharstringValue) x).getChar(i + shift - len));
            }
            return result;
         }

         else if (x instanceof RecordOfValue) {
            RecordOfValue result = null;
            if (x instanceof Cloneable) {
               try {
                  result = (RecordOfValue) x.getClass().getMethod("clone")
                        .invoke(x);
               } catch (Exception e) {
                  FATAL("FTL 012");
               }
            }
            int len = ((RecordOfValue) x).getLength();
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setField(i, ((RecordOfValue) x).getField(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setField(i, ((RecordOfValue) x).getField(i + shift - len));
            }
            return result;
         }

      }
      return null;
   }

   public static Value ShiftRight(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            int shift = ((IntegerValue) y).getInteger();
            result.setLength(len);
            for (int i = len - 1; i > shift; i--) {
               result.setBit(i, ((BitstringValue) x).getBit(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setBit(i, 0);
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = len - 1; i > shift; i--) {
               result.setHex(i, ((HexstringValue) x).getHex(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setHex(i, 0);
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = len - 1; i > shift; i--) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setOctet(i, 0);
            }
            return result;
         }
      }
      return null;
   }

   public static Value ShiftLeft(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            int shift = ((IntegerValue) y).getInteger();
            result.setLength(len);
            for (int i = 0; i < len - shift; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setBit(i, 0);
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setHex(i, 0);
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = 0; i < len - shift; i++) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i + shift));
            }
            for (int i = len - shift; i < len; i++) {
               result.setOctet(i, 0);
            }
            return result;
         }
      }
      return null;
   }

   public static Value RotateRight(Value x, Value y)
   {

      if (checkOperandsPresent(x, y)) {
         if (x instanceof BitstringValue) {
            BitstringValue result = Framework.GetFactory().BitstringValue();
            int len = ((BitstringValue) x).getLength();
            int shift = ((IntegerValue) y).getInteger();
            result.setLength(len);
            for (int i = len - 1; i > shift; i--) {
               result.setBit(i, ((BitstringValue) x).getBit(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setBit(i, ((BitstringValue) x).getBit(i + len - shift));
            }
            return result;
         } else if (x instanceof HexstringValue) {
            HexstringValue result = Framework.GetFactory().HexstringValue();
            int len = ((HexstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = len - 1; i > shift; i--) {
               result.setHex(i, ((HexstringValue) x).getHex(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setHex(i, ((HexstringValue) x).getHex(i + len - shift));
            }
            return result;
         } else if (x instanceof OctetstringValue) {
            OctetstringValue result = Framework.GetFactory().OctetstringValue();
            int len = ((OctetstringValue) x).getLength();
            result.setLength(len);
            int shift = ((IntegerValue) y).getInteger();
            for (int i = len - 1; i > shift; i--) {
               result.setOctet(i, ((OctetstringValue) x).getOctet(i - shift));
            }
            for (int i = 0; i < shift; i++) {
               result.setOctet(i,
                     ((OctetstringValue) x).getOctet(i + len - shift));
            }
            return result;
         }
      }
      return null;
   }

   public static Value Match(Value x, Value y)
   {
      boolean b;
      if (y instanceof Joker) {
         b = true;
      } else if (y instanceof IntegerJoker) {
         b = (x instanceof IntegerValue);
      } else if (y instanceof BooleanJoker) {
         b = (x instanceof BooleanValue);
      } else if (y instanceof CharstringJoker) {
         b = (x instanceof CharstringValue);
      } else if (y instanceof RecordJoker) {
         Value v = ((RecordJoker) y).example;
         Class cx = x.getClass();
         Class cv = v.getClass();
         b = cv.isAssignableFrom(cx);
      } else if (y instanceof IntegerValue) {
         // hier match
         int i1 = ((IntegerValue) x).getInteger();
         int i2 = ((IntegerValue) y).getInteger();
         b = (i1 == i2);
      } else if (y instanceof CharstringValue) {
         String s1 = ((CharstringValue) x).getString();
         String s2 = ((CharstringValue) y).getString();
         b = (s1.equals(s2));
      } else if (y instanceof BooleanValue) {
         boolean b1 = ((BooleanValue) x).getBoolean();
         boolean b2 = ((BooleanValue) y).getBoolean();
         b = (b1 == b2);
      } else if (y instanceof CompoundValue) {
         b = CompareCompoundValue(x, y);
      } else {
         System.out.println("[Match: this case not yet covered]");
         b = false;
      }

      BooleanValue result = Framework.GetFactory().BooleanValue();
      result.setBoolean(b);
      return result;
   }

   private static boolean CompareCompoundValue(Value x, Value y)
   {
      RecordValueImpl rv = (RecordValueImpl) x;
      CompoundValue cv = (CompoundValue) y;
      Value[] vals1 = rv.vals;
      Value[] vals2 = cv.vals;
      int l1 = vals1.length;
      int l2 = vals2.length;
      if (l1 != l2)
         return false;
      for (int i = 0; i < l1; i++) {
         Value v = Match(vals1[i], vals2[i]);
         BooleanValue bv = (BooleanValue) v;
         boolean b = bv.getBoolean();
         if (!b)
            return false;
      }
      return true;
   }

   public static Value IntegerEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() == ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value IntegerUnequal(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() != ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value IntegerLess(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() < ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value IntegerGreater(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() > ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value IntegerGreaterEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() >= ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value IntegerLessEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((IntegerValue) x).getInteger() <= ((IntegerValue) y)
               .getInteger());
      }
      return result;
   }

   public static Value FloatEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() == ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value FloatUnequal(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() != ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value FloatLess(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() < ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value FloatGreater(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() > ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value FloatGreaterEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() >= ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value FloatLessEqual(Value x, Value y)
   {
      BooleanValue result = Framework.GetFactory().BooleanValue();
      if (checkOperandsPresent(x, y)) {
         result.setBoolean(((FloatValue) x).getFloat() <= ((FloatValue) y)
               .getFloat());
      }
      return result;
   }

   public static Value IntegerPlus(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         result.setInteger(((IntegerValue) x).getInteger()
               + ((IntegerValue) y).getInteger());
      }
      return result;
   }

   public static Value IntegerMinus(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         result.setInteger(((IntegerValue) x).getInteger()
               - ((IntegerValue) y).getInteger());
      }
      return result;
   }

   public static Value IntegerMult(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         result.setInteger(((IntegerValue) x).getInteger()
               * ((IntegerValue) y).getInteger());
      }
      return result;
   }

   public static void Failure(String str)
   {
      Log(str);
      throw new TtcnError();
   }

   public static Value IntegerDiv(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         int iy = ((IntegerValue) y).getInteger();
         if (iy == 0)
            Failure("Division by zero");
         else
            result.setInteger(((IntegerValue) x).getInteger() / iy);
      }
      return result;
   }

   public static Value IntegerRem(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         int iy = ((IntegerValue) y).getInteger();
         if (iy == 0)
            System.out.println("ERROR: division by zero");
         else
            result.setInteger(((IntegerValue) x).getInteger()
                  % ((IntegerValue) y).getInteger());
      }
      return result;
   }

   public static Value IntegerMod(Value x, Value y)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, y)) {
         int iy = ((IntegerValue) y).getInteger();
         if (iy == 0)
            System.out.println("ERROR: division by zero");
         else {
            int ix = ((IntegerValue) x).getInteger();
            if (iy < 0)
               iy = -iy;
            int iresult = ix % iy;
            if (iresult < 0)
               iresult += iy;
            result.setInteger(iresult);
         }
      }
      return result;
   }

   public static Value IntegerUnaryMinus(Value x)
   {
      IntegerValue result = Framework.GetFactory().IntegerValue();
      if (checkOperandsPresent(x, x)) {
         result.setInteger(-((IntegerValue) x).getInteger());
      }
      return result;
   }

   public static Value FloatPlus(Value x, Value y)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      if (checkOperandsPresent(x, y)) {
         result.setFloat(((FloatValue) x).getFloat()
               + ((FloatValue) y).getFloat());
      }
      return result;
   }

   public static Value FloatMinus(Value x, Value y)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      if (checkOperandsPresent(x, y)) {
         result.setFloat(((FloatValue) x).getFloat()
               - ((FloatValue) y).getFloat());
      }
      return result;
   }

   public static Value FloatMult(Value x, Value y)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      if (checkOperandsPresent(x, y)) {
         result.setFloat(((FloatValue) x).getFloat()
               * ((FloatValue) y).getFloat());
      }
      return result;
   }

   public static Value FloatDiv(Value x, Value y)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      if (checkOperandsPresent(x, y)) {
         float fy = ((FloatValue) y).getFloat();
         if (fy == 0.0)
            System.out.println("ERROR: division by zero");
         else
            result.setFloat(((FloatValue) x).getFloat() / fy);
      }
      return result;
   }

   public static Value FloatUnaryMinus(Value x, Value y)
   {
      FloatValue result = Framework.GetFactory().FloatValue();
      if (checkOperandsPresent(x, x)) {
         result.setFloat(-((FloatValue) x).getFloat());
      }
      return result;
   }

   public static void Log(String str)
   {
      TheLogger.Log("War wohl nichts: " + str);
   }

   public static void Log(Value/* Impl */v)
   {
      // System.out.println("--- Log ---");
      if (v == null || v.notPresent()) {
         Trace.print("log: value not present");
      } else if (v instanceof CharstringValue) {
         Trace.print("log instance of CharstringValue");
         String str = ((CharstringValue) v).getString();
         Trace.print("log: '" + str + "'");
         TheLogger.Log(str);

      } else if (v instanceof IntegerValue) {
         // System.out.println("log instance of IntegerValue");
         int i = ((IntegerValue) v).getInteger();
         // System.out.println("log: " + i );
         Trace.print("" + i);
         TheLogger.Log("" + i);

      } else if (v instanceof FloatValue) {
         Trace.print("log instance of FloatValue");
         float f = ((FloatValue) v).getFloat();
         Trace.print("log: " + f);
         TheLogger.Log("" + f);
      } else if (v instanceof BooleanValue) {
         // System.out.println("log instance of BooleanValue");
         boolean b = ((BooleanValue) v).getBoolean();
         Trace.print("" + b);
         TheLogger.Log("" + b);
      } else if (v instanceof VerdictValue) {
         Trace.print("log instance of VerdictValue");
         int ve = ((VerdictValue) v).getVerdict();
         Trace.print("log: " + ve);
         TheLogger.Log("" + ve);

      } else {
         TheLogger.Log("/Log/");
      }
   }

   public static Value/* Impl */
   CharacterConstant(String str)
   {
      CharstringValue v =
      // (de.fraunhofer.fokus.ttcn.tci.CharstringValue)
      de.fraunhofer.fokus.ttcn.framework.Framework.GetFactory()
            .CharstringValue();
      // de.fraunhofer.fokus.ttcn.tci.CharstringValueImpl v =
      // new de.fraunhofer.fokus.ttcn.tci.CharstringValueImpl();
      v.setString(str);
      return v;
   }

   private static boolean checkOperandsPresent(Value x, Value y)
   {
      if (x == null || y == null || x.notPresent() || y.notPresent()) {
         System.out.println("ERROR: value not present");
         return false;
      }
      return true;
   }

   public static Value CharstringConcat(Value x, Value y)
   {
      if (checkOperandsPresent(x, y) && x instanceof CharstringValue
            && y instanceof CharstringValue) {
         CharstringValue result = Framework.GetFactory().CharstringValue();
         /*
          * int len1 = ((CharstringValue) x).getLength(); int len2 =
          * ((CharstringValue) y).getLength(); result.setLength(len1 + len2);
          * for (int i=0; i<len1; i++) result.setChar(i, ((CharstringValue)
          * x).getChar(i)); for (int i=0; i<len2; i++) result.setChar(i+len1,
          * ((CharstringValue) y).getChar(i));
          */
         CharstringValue sv1 = (CharstringValue) x;
         CharstringValue sv2 = (CharstringValue) y;
         String str1 = sv1.getString();
         String str2 = sv2.getString();
         String str3 = str1 + str2;
         result.setString(str3);
         return result;
      }
      return null;
   }

   public static Value UniversalCharstringConcat(Value x, Value y)
   {
      if (checkOperandsPresent(x, y) && x instanceof UniversalCharstringValue
            && y instanceof UniversalCharstringValue) {
         UniversalCharstringValue result = Framework.GetFactory()
               .UniversalCharstringValue();
         int len1 = ((UniversalCharstringValue) x).getLength();
         int len2 = ((UniversalCharstringValue) y).getLength();
         result.setLength(len1 + len2);
         for (int i = 0; i < len1; i++)
            result.setChar(i, ((UniversalCharstringValue) x).getChar(i));
         for (int i = 0; i < len2; i++)
            result.setChar(i + len1, ((UniversalCharstringValue) y).getChar(i));
         return result;
      }
      return null;
   }

   public static Value BitstringConcat(Value x, Value y)
   {
      if (checkOperandsPresent(x, y) && x instanceof BitstringValue
            && y instanceof BitstringValue) {
         BitstringValue result = Framework.GetFactory().BitstringValue();
         int len1 = ((BitstringValue) x).getLength();
         int len2 = ((BitstringValue) y).getLength();
         result.setLength(len1 + len2);
         for (int i = 0; i < len1; i++)
            result.setBit(i, ((BitstringValue) x).getBit(i));
         for (int i = 0; i < len2; i++)
            result.setBit(i + len1, ((BitstringValue) y).getBit(i));
         return result;
      }
      return null;
   }

   public static Value OctetstringConcat(Value x, Value y)
   {
      if (checkOperandsPresent(x, y) && x instanceof OctetstringValue
            && y instanceof OctetstringValue) {
         OctetstringValue result = Framework.GetFactory().OctetstringValue();
         int len1 = ((OctetstringValue) x).getLength();
         int len2 = ((OctetstringValue) y).getLength();
         result.setLength(len1 + len2);
         for (int i = 0; i < len1; i++)
            result.setOctet(i, ((OctetstringValue) x).getOctet(i));
         for (int i = 0; i < len2; i++)
            result.setOctet(i + len1, ((OctetstringValue) y).getOctet(i));
         return result;
      }
      return null;
   }

   public static Value HexstringConcat(Value x, Value y)
   {
      if (checkOperandsPresent(x, y) && x instanceof HexstringValue
            && y instanceof HexstringValue) {
         HexstringValue result = Framework.GetFactory().HexstringValue();
         int len1 = ((HexstringValue) x).getLength();
         int len2 = ((HexstringValue) y).getLength();
         result.setLength(len1 + len2);
         for (int i = 0; i < len1; i++)
            result.setHex(i, ((HexstringValue) x).getHex(i));
         for (int i = 0; i < len2; i++)
            result.setHex(i + len1, ((HexstringValue) y).getHex(i));
         return result;
      }
      return null;
   }

   public static void Abort(String str)
   {
      System.out.println(str);
      System.out.println("Execution failed");
      FATAL("ABORTED");
   }

   public static void FATAL(String str)
   {
      // System.out.println("***** FATAL: " + str);
      // System.out.println("***** throw TtcnFatal");
      throw new targetcode.TtcnFatal();
   }

}
