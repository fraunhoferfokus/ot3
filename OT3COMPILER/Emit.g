
   
   type TESTCASE
      testcase(string, string, string, STMT)
   
   type FUNCTION
      function(string, STMT)
   
   type EXPR
      ichbinexpr()
      createcomponent(string)
      expr_compportref(EXPR, string)
      expr_runsonc_port(string)
      expr_systemc_port(string)
      binexpr(string, EXPR, EXPR)
      monexpr(string, EXPR)
      intexpr(string)
      expr_float(string)
      expr_omit()
      idexpr(string)
      fieldselection(EXPR, string)
      compidexpr(string)
      nullexpr()
      expr_bstring(string)
      expr_ostring(string)
      expr_hstring(string)
      expr_cstring(string)
      expr_quadrupel(string, string, string, string)
      expr_verdict(string)
      expr_true()
      expr_false()
      expr_compound(FIELDSPEC[])
      expr_templsimple(FIELDSPEC)
      expr_templ(FIELDSPEC[])
      expr_nimmdas(EXPR[])
      expr_array(EXPR[])
      expr_skip()
      expr_match(EXPR, EXPR)
      fragezeichen()
      fragezeichen_tp(Tp)
      fragezeichen_record(string)
      expr_undef()
   
   type FIELDSPEC
      spec_namedfield(string, EXPR)
      spec_unnamedfield(EXPR)
      spec_unfertig()
   
   type STMT
      nyi()
      stmt_error()
      stmt_altn(ALTGUARD[])
      stmt_standalone(ALTGUARD)
      stmt_testcaseinst(string)
      stmt_while(EXPR, STMT)
      stmt_if(EXPR, STMT, ELSEIF[], STMT)
      stmt_block(STMT, STMT)
      stmt_assign(EXPR, EXPR)
      stmt_assigncompound1(EXPR, EXPR[])
      stmt_assigncompound2(EXPR, FIELDSPEC[])
      stmt_log(EXPR)
      stmt_nolist()
      stmt_seq(STMT, STMT)
      stmt_null()
      stmt_declareboolean(string)
      stmt_declareinteger(string)
      stmt_declarecharstring(string)
      stmt_declarereferenced(string, string)
      stmt_declaretimer(string)
      stmt_send(EXPR, EXPR)
      stmt_call(string)
      stmt_starttimer(string)
      stmt_starttimerexpr(string, EXPR)
      stmt_stopcomponent(string)
      stmt_verdict(EXPR)
      stmt_map(EXPR, EXPR)
      stmt_connect(EXPR, EXPR)
      stmt_unmap(EXPR, EXPR)
      stmt_done(string)
      stmt_startcomponent(string, string)
      stmt_repeat()
      stmt_action(string)
      stmt_undef()
   
   type ALTGUARD
      altguard(RECEIVE, STMT)
      altguard_schrott()
   
   type RECEIVE
      receive_timeout(string)
      receive_value(EXPR, string, string)
      receive_unused()
   
   var HasControlPart: int
   
   proc Emit_ControlPart(STMT)
      
      rule Emit_ControlPart(STMT)
         global HasControlPart <- 1
         "@Override\n"
         "public void RunControlPart()\n"
         "{\n"
         "   DoControlPart();\n"
         "}\n"
         "public static void DoControlPart()\n"
         "{\n"
         Emit_Stmt(STMT)
         "}\n"
   
   proc Emit_Testcase(TESTCASE)
      
      rule Emit_Testcase(testcase(Id, RunsOnC, SystemC, Block))
         "public static void "
         emit Id
         " ()\n"
         "{\n"
         "Instructions.TestcasePrelude(\""
         emit Id
         "\");\n"
         "Instructions.SetSystemComponent(new "
         emit SystemC
         "(\"#SC#\"));\n"
         "Value MyRunsOnC = new "
         emit RunsOnC
         "(\"#RC#\")\n;"
         "Instructions.SetRunsOnComponent(MyRunsOnC);\n"
         "try {\n"
         Emit_Stmt(Block)
         "}\n"
         "catch (targetcode.TestCaseKilled X) {\n"
         "Instructions.Trace(\"CAUGHT TestCaseKilled\");\n"
         "System.out.println(\"TESTCASE KILLED\");\n"
         "System.out.println(\"EXCEPTION IN TESTCASE\");\n"
         "throw new targetcode.TtcnFatal();\n"
         "}\n"
         "catch (Exception X) {\n"
         "System.out.println(\"EXCEPTION IN TESTCASE\");\n"
         "throw new targetcode.TtcnFatal();\n"
         "}\n"
         "Instructions.TestcasePostlude(\""
         emit Id
         "\");\n"
         "}\n"
         CollectTestcase(Id)
   
   proc Emit_Function(FUNCTION)
      
      rule Emit_Function(function(Id, Block))
         "public static void "
         emit Id
         " (Value MyRunsOnC)\n"
         "{\n"
         "Instructions.FunctionPrelude(\""
         emit Id
         "\", MyRunsOnC);\n"
         "final Value FinalMyRunsOnC = MyRunsOnC;\n"
         Emit_Stmt(Block)
         "}\n"
   
   proc Emit_Imports()
      
      rule Emit_Imports()
         "import org.etsi.ttcn.tci.Value;\n"
         "import org.etsi.ttcn.tci.BooleanValue;\n"
         "\n"
         "import targetcode.Instructions;\n"
         "import targetcode.Lambda;\n"
         "import targetcode.Component;\n"
         "import targetcode.Port;\n"
         "\n"
   
   proc Emit_Prelude()
      
      rule Emit_Prelude()
         global NameOfModule -> PKG
         OpenJavaTargetFile(PKG, "MODULE")
         "// EMITFILE 0 module\n"
         "package "
         emit PKG
         ";\n"
         "import org.etsi.ttcn.tci.Value;\n"
         "import org.etsi.ttcn.tci.BooleanValue;\n"
         "import targetcode.Instructions;\n"
         "import targetcode.Lambda;\n"
         "import targetcode.Component;\n"
         "import targetcode.Port;\n"
         "\n"
         "public class MODULE\n"
         "implements targetcode.MetaModule\n"
         "{\n"
   
   proc Emit_Postlude()
      
      rule Emit_Postlude()
         {
            global HasControlPart -> Q
            Q -> 1
         |
            "@Override\n"
            "public void RunControlPart()\n"
            "{\n"
            "   System.out.println(\"[Module has no control part]\");\n"
            "}\n"
         }
         EmitTestcaseTable()
         "}\n"
         close
   
   proc Emit_Expression(EXPR)
      
      rule Emit_Expression(createcomponent(Type))
         "new "
         emit Type
         " (\"Rumpelstilzchen\")\n"
      
      rule Emit_Expression(expr_verdict("pass"))
         "Instructions.VerdictPass()\n"
      
      rule Emit_Expression(expr_verdict("fail"))
         "Instructions.VerdictFail()\n"
      
      rule Emit_Expression(expr_verdict("inconc"))
         "Instructions.VerdictInconc()\n"
      
      rule Emit_Expression(expr_verdict("none"))
         "Instructions.VerdictNone()\n"
      
      rule Emit_Expression(expr_verdict("error"))
         "Instructions.VerdictError()\n"
      
      rule Emit_Expression(expr_compportref(X, Id))
         "Instructions.ComponentPort(\n"
         Emit_Expression(X)
         ",\n"
         "\""
         emit Id
         "\"\n"
         ")\n"
      
      rule Emit_Expression(expr_runsonc_port(Id))
         "Instructions.ComponentPort(\n"
         "MyRunsOnC\n"
         ",\n"
         "\""
         emit Id
         "\"\n"
         ")\n"
      
      rule Emit_Expression(expr_systemc_port(Id))
         "Instructions.SystemCPort(\""
         emit Id
         "\")\n"
      
      rule Emit_Expression(monexpr("bitnot", X1))
         "XInstructions.Not4b(\n"
         Emit_Expression(X1)
         ")\n"
      
      rule Emit_Expression(monexpr("not", X1))
         "Instructions.Not(\n"
         Emit_Expression(X1)
         ")\n"
      
      rule Emit_Expression(monexpr("minus", X1))
         "Instructions.IntegerUnaryMinus(\n"
         Emit_Expression(X1)
         ")\n"
      
      rule Emit_Expression(fragezeichen())
         "new targetcode.Joker()\n"
      
      rule Emit_Expression(fragezeichen_record(Name))
         "new targetcode.RecordJoker((new Meta"
         emit Name
         "()).newInstance())\n"
      
      rule Emit_Expression(fragezeichen_tp(Tp))
         {
            Tp -> tp_boolean()
            "new targetcode.BooleanJoker()\n"
         |
            Tp -> tp_charstring()
            "new targetcode.CharstringJoker()\n"
         |
            Tp -> tp_integer()
            "new targetcode.IntegerJoker()\n"
         |
            "new targetcode.Joker()\n"
         }
      
      rule Emit_Expression(expr_match(X1, X2))
         "Instructions.Match(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("less", X1, X2))
         "Instructions.IntegerLess(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("greater", X1, X2))
         "Instructions.IntegerGreater(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("greaterequal", X1, X2))
         "Instructions.IntegerGreaterEqual(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("lessequal", X1, X2))
         "Instructions.IntegerLessEqual(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("shiftleft", X1, X2))
         "XInstructions.ShiftLeft(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("shiftright", X1, X2))
         "XInstructions.ShiftRight(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("rotateleft", X1, X2))
         "XInstructions.RotateLeft(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("rotateright", X1, X2))
         "XInstructions.RotateRight(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("notequal", X1, X2))
         "Instructions.IntegerUnequal(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("equal", X1, X2))
         "Instructions.IntegerEqual(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("or", X1, X2))
         "Instructions.Or(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("and", X1, X2))
         "Instructions.And(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("xor", X1, X2))
         "Instructions.Xor(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("bitand", X1, X2))
         "XInstructions.And4b(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("bitxor", X1, X2))
         "XInstructions.Xor4b(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("bitor", X1, X2))
         "XInstructions.Or4b(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("div", X1, X2))
         "Instructions.IntegerDiv(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("mul", X1, X2))
         "Instructions.IntegerMult(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("mod", X1, X2))
         "Instructions.IntegerMod(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("rem", X1, X2))
         "Instructions.IntegerRem(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("concat", X1, X2))
         "Instructions.CharstringConcat(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("add", X1, X2))
         "Instructions.IntegerPlus(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(binexpr("sub", X1, X2))
         "Instructions.IntegerMinus(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ")\n"
      
      rule Emit_Expression(compidexpr(str))
         "Instructions.ComponentVar(\n"
         "MyRunsOnC\n"
         ",\n"
         "\""
         emit str
         "\"\n"
         ")\n"
      
      rule Emit_Expression(fieldselection(X, str))
         "Instructions.FieldSelection(\n"
         Emit_Expression(X)
         ",\n"
         "\""
         emit str
         "\"\n"
         ")\n"
      
      rule Emit_Expression(idexpr(str))
         emit str
      
      rule Emit_Expression(expr_omit())
         "Instructions.IntegerConstant(444)\n"
      
      rule Emit_Expression(expr_float(n))
         "Instructions.FloatConstant("
         emit n
         ")\n"
      
      rule Emit_Expression(intexpr(n))
         "Instructions.IntegerConstant("
         emit n
         ")\n"
      
      rule Emit_Expression(expr_true())
         "Instructions.BooleanConstant(true)\n"
      
      rule Emit_Expression(expr_false())
         "Instructions.BooleanConstant(false)\n"
      
      rule Emit_Expression(expr_quadrupel(n1, n2, n3, n4))
         "Instructions.IntegerConstant(555)\n"
      
      rule Emit_Expression(expr_cstring(str))
         "Instructions.CharstringConstant("
         emit str
         ")\n"
      
      rule Emit_Expression(expr_bstring(str))
         "Instructions.IntegerConstant(666)\n"
      
      rule Emit_Expression(expr_hstring(str))
         "Instructions.IntegerConstant(666)\n"
      
      rule Emit_Expression(expr_ostring(str))
         "Instructions.IntegerConstant(666)\n"
      
      rule Emit_Expression(expr_templsimple(Spec))
         Emit_ObjectElement(Spec)
      
      rule Emit_Expression(expr_templ(Specs))
         error "template expr"
      
      rule Emit_Expression(expr_compound(Specs))
         error "compound expr"
      
      rule Emit_Expression(expr_array(Xs))
         error "array expr"
      
      rule Emit_Expression(expr_skip())
         error "skip expr"
      
      rule Emit_Expression(expr_nimmdas(Es))
         Emit_CompoundExpression1(Es)
      
      rule Emit_Expression(X)
         print X
         error "Emit_Expression incomplete"
   
   proc Emit_CompoundExpression1(EXPR[])
      
      rule Emit_CompoundExpression1(Exprs)
         "new targetcode.CompoundValue (\n"
         "new Value[] {\n"
         first L <- Exprs
         {
            L -> EXPR[H::T]
            Emit_Expression(H)
            {
               T -> EXPR[_::_]
               ",\n"
            |
            }
            next L <- T
         }*
         "}\n"
         ")\n"
   
   proc Emit_CompoundExpression2(FIELDSPEC[])
      
      rule Emit_CompoundExpression2(Fields)
         "new Value[] {\n"
         first L <- Fields
         {
            L -> FIELDSPEC[H::T]
            {
               H -> spec_namedfield(Name, Expr)
            |
               H -> spec_unnamedfield(Expr)
            }
            Emit_Expression(Expr)
            {
               T -> FIELDSPEC[_::_]
               ",\n"
            |
            }
            next L <- T
         }*
         "}\n"
   
   proc Emit_Stmt(STMT)
      
      rule Emit_Stmt(stmt_seq(A, B))
         Emit_Stmt(A)
         Emit_Stmt(B)
      
      rule Emit_Stmt(stmt_block(Defs, A))
         "{\n"
         Emit_Stmt(Defs)
         Emit_Stmt(A)
         "}\n"
      
      rule Emit_Stmt(stmt_while(EXPR, STMT))
         "while (\n"
         "Instructions.ToBoolean(\n"
         Emit_Expression(EXPR)
         ")\n"
         ") {\n"
         "Instructions.CheckKillFlag();\n"
         Emit_Stmt(STMT)
         "}\n"
      
      rule Emit_Stmt(stmt_if(Expr, Then, Elsifs, Else))
         "// IF\n"
         "if (\n"
         "Instructions.ToBoolean(\n"
         Emit_Expression(Expr)
         ")\n"
         ")\n"
         "{\n"
         Emit_Stmt(Then)
         "}\n"
         first L <- Elsifs
         {
            L -> ELSEIF[H::T]
            H -> elseif(C, S)
            "else if (\n"
            "Instructions.ToBoolean(\n"
            Emit_Expression(C)
            ")\n"
            ")\n"
            "{\n"
            Emit_Stmt(S)
            "}\n"
            next L <- T
         }*
         "else\n"
         "{\n"
         Emit_Stmt(Else)
         "}\n"
         "// ENDIF\n"
      
      rule Emit_Stmt(stmt_log(E))
         "Instructions.Log(\n"
         Emit_Expression(E)
         ");\n"
      
      rule Emit_Stmt(stmt_action(Str))
         "Instructions.Trace(\"ACTION\");\n"
         "Instructions.Action(\n"
         emit Str
         "\n"
         ");\n"
      
      rule Emit_Stmt(stmt_send(A, B))
         "Instructions.Trace(\"SEND\");\n"
         "Instructions.Send(\n"
         Emit_Expression(A)
         ",\n"
         Emit_Expression(B)
         ");\n"
      
      rule Emit_Stmt(stmt_assigncompound1(A, Bs))
         "Instructions.Trace(\"ASSIGN\");\n"
         "Instructions.AssignCompoundValue(\n"
         Emit_Expression(A)
         ",\n"
         Emit_CompoundExpression1(Bs)
         ");\n"
      
      rule Emit_Stmt(stmt_assigncompound2(A, Bs))
         "Instructions.Trace(\"ASSIGN\");\n"
         "Instructions.AssignCompoundValue(\n"
         Emit_Expression(A)
         ",\n"
         Emit_CompoundExpression2(Bs)
         ");\n"
      
      rule Emit_Stmt(stmt_assign(A, B))
         "Instructions.Trace(\"ASSIGN\");\n"
         "Instructions.AssignValue(\n"
         Emit_Expression(A)
         ",\n"
         Emit_Expression(B)
         ");\n"
      
      rule Emit_Stmt(stmt_null())
      
      rule Emit_Stmt(stmt_nolist())
      
      rule Emit_Stmt(stmt_declarecharstring(Id))
         "Instructions.Trace(\"DECLARE\");\n"
         "final Value "
         emit Id
         " = Instructions.CharstringConstant(\"abc\");\n"
      
      rule Emit_Stmt(stmt_declareboolean(Id))
         "Instructions.Trace(\"DECLARE\");\n"
         "final Value "
         emit Id
         " = Instructions.BooleanConstant(false);\n"
      
      rule Emit_Stmt(stmt_declareinteger(Id))
         "Instructions.Trace(\"DECLARE\");\n"
         "final Value "
         emit Id
         " = Instructions.IntegerConstant(999);\n"
      
      rule Emit_Stmt(stmt_declarereferenced(Id, TypeId))
         "Instructions.Trace(\"DECLARE\");\n"
         "final Value "
         emit Id
         " = new "
         emit TypeId
         " (\"Gantenbein\");\n"
      
      rule Emit_Stmt(stmt_declaretimer(Id))
         "Instructions.Trace(\"DECLARETIMER\");\n"
         "final Value "
         emit Id
         " = new targetcode.Timer();\n"
      
      rule Emit_Stmt(stmt_call(Fun))
         "Instructions.Trace(\"CALL\");\n"
         emit Fun
         "(Instructions.RunsOnComponent);\n"
      
      rule Emit_Stmt(stmt_standalone(G))
         "Instructions.Trace(\"ALT\");\n"
         NewState(-> K)
         "final targetcode.AltState State"
         emit K
         " = new targetcode.AltState();\n"
         "Instructions.AltConstruct(\n"
         "State"
         emit K
         ",\n"
         "new Lambda[] {\n"
         Emit_AltGuard(G, K)
         "}\n"
         ");\n"
      
      rule Emit_Stmt(stmt_altn(Gs))
         "Instructions.Trace(\"ALT\");\n"
         NewState(-> K)
         "final targetcode.AltState State"
         emit K
         " = new targetcode.AltState();\n"
         "Instructions.AltConstruct(\n"
         "State"
         emit K
         ",\n"
         "new Lambda[] {\n"
         Emit_AltGuardList(Gs, K)
         "}\n"
         ");\n"
      
      rule Emit_Stmt(stmt_testcaseinst(Id))
         "Instructions.Trace(\"TESTCASEINST\");\n"
         "Instructions.StartTestcase(\n"
         BeginLambda()
         emit Id
         "();\n"
         EndLambda()
         ");\n"
      
      rule Emit_Stmt(stmt_verdict(X))
         "Instructions.Trace(\"SETVERDICT\");\n"
         "Instructions.SetVerdict(\n"
         Emit_Expression(X)
         ");\n"
      
      rule Emit_Stmt(stmt_repeat())
         "Instructions.Trace(\"REPEAT\");\n"
         "Instructions.Repeat();\n"
      
      rule Emit_Stmt(stmt_done(Id))
         "Instructions.Trace(\"DONE\");\n"
         "Instructions.Done("
         emit Id
         ");\n"
      
      rule Emit_Stmt(stmt_map(X1, X2))
         "Instructions.Trace(\"MAP\");\n"
         "Instructions.Map(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ");\n"
      
      rule Emit_Stmt(stmt_connect(X1, X2))
         "Instructions.Trace(\"CONNECT\");\n"
         "Instructions.Connect(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ");\n"
      
      rule Emit_Stmt(stmt_unmap(X1, X2))
         "Instructions.Trace(\"UNMAP\");\n"
         "Instructions.Unmap(\n"
         Emit_Expression(X1)
         ",\n"
         Emit_Expression(X2)
         ");\n"
      
      rule Emit_Stmt(stmt_startcomponent(Id1, Id2))
         "Instructions.Trace(\"STARTCOMPONENT\");\n"
         "Instructions.StartComponent(\n"
         emit Id1
         ",\n"
         BeginLambda()
         "Instructions.ComponentActivityStarts("
         emit Id1
         ");\n"
         emit Id2
         "("
         emit Id1
         ");\n"
         "Instructions.ComponentActivityEnds("
         emit Id1
         ");\n"
         EndLambda()
         ");\n"
      
      rule Emit_Stmt(stmt_stopcomponent(Id))
         "Instructions.Trace(\"STOPCOMPONENT\");\n"
         "Instructions.StopComponent(\n"
         emit Id
         "\n"
         ");\n"
      
      rule Emit_Stmt(stmt_starttimer(Id))
         "Instructions.Trace(\"STARTTIMER\");\n"
         "Instructions.StartTimer("
         emit Id
         ");\n"
      
      rule Emit_Stmt(stmt_starttimerexpr(Id, Expr))
         "Instructions.Trace(\"STARTTIMER\");\n"
         "Instructions.StartTimerExpr("
         emit Id
         ",\n"
         "\n"
         Emit_Expression(Expr)
         ");\n"
      
      rule Emit_Stmt(Stmt)
         log Stmt
         error "emit stmt"
   
   proc Emit_AltGuardList(ALTGUARD[], int)
      
      rule Emit_AltGuardList(ALTGUARD[H::T], K)
         Emit_AltGuard(H, K)
         {
            T -> ALTGUARD[]
         |
            ",\n"
         }
         Emit_AltGuardList(T, K)
      
      rule Emit_AltGuardList(ALTGUARD[], K)
   
   proc BeginLambda()
      
      rule BeginLambda()
         "new Lambda() {\n"
         "public void Fun() { \n"
   
   proc EndLambda()
      
      rule EndLambda()
         "}\n"
         "}\n"
   
   proc Emit_AltGuard(ALTGUARD, int)
      
      rule Emit_AltGuard(altguard(Rcv, Stmt), N)
         "// GUARD\n"
         BeginLambda()
         Emit_Receive(Rcv, N)
         EndLambda()
         ",\n"
         "// => \n"
         BeginLambda()
         Emit_Stmt(Stmt)
         EndLambda()
         "// ENDGUARD\n"
   
   proc Emit_Receive(RECEIVE, int)
      
      rule Emit_Receive(receive_timeout(T), K)
         "// RECEIVETIMEOUT\n"
         "Instructions.Timeout(\n"
         emit T
         "\n"
         ",\n"
         "State"
         emit K
         "\n"
         ");\n"
      
      rule Emit_Receive(receive_value(Pattern, Port, Redir), K)
         {
            Redir -> "/noredirect/"
            "// RECEIVE\n"
            "Instructions.ReceiveValue(\n"
            "Instructions.ComponentPort(FinalMyRunsOnC, \""
            emit Port
            "\")\n"
            ",\n"
            "State"
            emit K
            ",\n"
            Emit_Expression(Pattern)
            ");\n"
         |
            "// RECEIVE\n"
            "Instructions.ReceiveValueRedirect(\n"
            "Instructions.ComponentPort(FinalMyRunsOnC, \""
            emit Port
            "\")\n"
            ",\n"
            "State"
            emit K
            ",\n"
            Emit_Expression(Pattern)
            ",\n"
            emit Redir
            "\n"
            ");\n"
         }
   
   var StateIndex: int
   
   proc NewState(-> int)
      
      rule NewState(-> K)
         global StateIndex -> K
         global StateIndex <- (K+1)
   
   proc InitEmit()
      
      rule InitEmit()
         global StateIndex <- 0
         global TestcaseTable <- string[]
         global HasControlPart <- 0
   
   var TestcaseTable: string[]
   
   proc CollectTestcase(tc: string)
      global TestcaseTable -> T
      global TestcaseTable <- string[tc::T]
   
   proc EmitTestcaseTable()
      "@Override\n"
      "public String[] GetTestCases()\n"
      "{\n"
      "   return new String[] {\n"
      global TestcaseTable -> L
      EmitTestcaseNames(L)
      "   };\n"
      "}\n"
   
   proc EmitTestcaseNames(string[])
      
      rule EmitTestcaseNames(string[H::T])
         "      \""
         emit H
         "\""
         {
            T -> string[]
         |
            ","
         }
         "\n"
         EmitTestcaseNames(T)
      
      rule EmitTestcaseNames(string[])
   
   proc Emit_ObjectElement(FIELDSPEC)
      
      rule Emit_ObjectElement(Hd)
         {
            Hd -> spec_unnamedfield(Val)
            Emit_Expression(Val)
         |
            Hd -> spec_namedfield(Name, Val)
            emit Name
            ":\n"
            Emit_Expression(Val)
         }

