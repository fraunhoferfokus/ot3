
   
   proc Trafo_FunctionStatement($FunctionStatement -> STMT)
      
      rule Trafo_FunctionStatement(FunctionSt -> Res)
         switch FunctionSt
         {
            case FunctionStatement_A1(S1)
            {
               Trafo_ConfigurationStatements(S1 -> Res)
            }
            case FunctionStatement_A2(S2)
            {
               Trafo_TimerStatements(S2 -> Res)
            }
            case FunctionStatement_A3(S3)
            {
               Trafo_CommunicationStatements(S3 -> Res)
            }
            case FunctionStatement_A4(S4)
            {
               Trafo_BasicStatements(S4 -> Res)
            }
            case FunctionStatement_A5(S5)
            {
               Trafo_BehaviourStatements(S5 -> Res)
            }
            case FunctionStatement_A6(S6)
            {
               Trafo_SetLocalVerdict(S6 -> Res)
            }
            case FunctionStatement_A7(S7)
            {
               Trafo_SUTStatements(S7 -> Res)
            }
            case FunctionStatement_A8(S8)
            {
               NotCovered("TestcaseOperation", FunctionSt)
               Res <- nyi()
            }
            case FunctionStatement_A9(S9)
            {
               NotCovered("RealtimeStatement", FunctionSt)
               Res <- nyi()
            }
            case FunctionStatement_A10(S10)
            {
               NotCovered("AssertStatement", FunctionSt)
               Res <- nyi()
            }
            case FunctionStatement_A11(S11)
            {
               NotCovered("WaitStatement", FunctionSt)
               Res <- nyi()
            }
            case FunctionStatement_A12(S12)
            {
               NotCovered("ModeSpecification", FunctionSt)
               Res <- nyi()
            }
         }
   
   proc Trafo_ControlStatement($ControlStatement -> STMT)
      
      rule Trafo_ControlStatement(ZWEIDREI -> Res)
         switch ZWEIDREI
         {
            case ControlStatement_A1(S1)
            {
               Trafo_TimerStatements(S1 -> Res)
            }
            case ControlStatement_A2(S2)
            {
               Trafo_BasicStatements(S2 -> Res)
            }
            case ControlStatement_A3(S3)
            {
               Trafo_BehaviourStatements(S3 -> Res)
            }
            case ControlStatement_A4(S4)
            {
               Trafo_SUTStatements(S4 -> Res)
            }
            case ControlStatement_A5()
            {
               NotCovered("StopStatement", ZWEIDREI)
               Res <- nyi()
            }
            case ControlStatement_A6(S6)
            {
               NotCovered("KillStatement", ZWEIDREI)
               Res <- nyi()
            }
         }
   
   proc Trafo_ConfigurationStatements($ConfigurationStatements -> STMT)
      
      rule Trafo_ConfigurationStatements(S -> Code)
         switch S
         {
            case ConfigurationStatements_A2(Map_Statement_A1)
            {
               Stmt_MapStatement_A1(Map_Statement_A1 -> Code)
            }
            case ConfigurationStatements_A1(ConnectStatement_A1)
            {
               Stmt_ConnectStatement_A1(ConnectStatement_A1 -> Code)
            }
            case ConfigurationStatements_A7(StartTCStatement)
            {
               Stmt_StartTCStatement(StartTCStatement -> Code)
            }
            case ConfigurationStatements_A5(DoneStatement)
            {
               Stmt_DoneStatement(DoneStatement -> Code)
            }
            case ConfigurationStatements_A4(UnmapStatement)
            {
               Stmt_UnmapStatement(UnmapStatement -> Code)
            }
            case ConfigurationStatements_A3(X3)
            {
               NotCovered("disconnect statement", X3)
               Code <- stmt_null()
            }
            case ConfigurationStatements_A6(X6)
            {
               NotCovered("killed statement", X6)
               Code <- stmt_null()
            }
            case ConfigurationStatements_A8(X8)
            {
               NotCovered("stop component", X8)
               Code <- stmt_null()
            }
            case ConfigurationStatements_A9(X9)
            {
               NotCovered("kill component", X9)
               Code <- stmt_null()
            }
         }
   
   proc Trafo_TimerStatements($TimerStatements -> STMT)
      
      rule Trafo_TimerStatements(S -> Code)
         switch S
         {
            case TimerStatements_A1(StartTimerStatement)
            {
               Stmt_StartTimerStatement(StartTimerStatement -> Code)
            }
            case TimerStatements_A3(_)
            {
               NotCovered("standalone timeout (use alt)", S)
               Code <- nyi()
            }
            case TimerStatements_A2(StopTimerStatement)
            {
               Stmt_StopTimerStatement(StopTimerStatement -> Code)
            }
         }
   
   proc Trafo_CommunicationStatements($CommunicationStatements -> STMT)
      
      rule Trafo_CommunicationStatements(S -> Code)
         switch S
         {
            case CommunicationStatements_A1(SendStmt)
            {
               Trafo_SendStatement(SendStmt -> Code)
            }
            case CommunicationStatements_A2(_)
            {
               NotCovered("communication statement 2", S)
               Code <- nyi()
            }
            case CommunicationStatements_A3(_)
            {
               NotCovered("communication statement 3", S)
               Code <- nyi()
            }
            case CommunicationStatements_A4(_)
            {
               NotCovered("communication statement 4", S)
               Code <- nyi()
            }
            case CommunicationStatements_A5(RECEIVE)
            {
               Stmt_ReceiveStatement(RECEIVE -> Code)
            }
            case CommunicationStatements_A6(_)
            {
               NotCovered("communication statement 6", S)
               Code <- nyi()
            }
            case CommunicationStatements_A7(_)
            {
               NotCovered("communication statement 7", S)
               Code <- nyi()
            }
            case CommunicationStatements_A8(_)
            {
               NotCovered("communication statement 8", S)
               Code <- nyi()
            }
            case CommunicationStatements_A9(_)
            {
               NotCovered("communication statement 9", S)
               Code <- nyi()
            }
            case CommunicationStatements_A10(_)
            {
               NotCovered("communication statement 10", S)
               Code <- nyi()
            }
            case CommunicationStatements_A11(_)
            {
               NotCovered("communication statement 11", S)
               Code <- nyi()
            }
            case CommunicationStatements_A12(_)
            {
               log S
               NotCovered("start statement", S)
               Code <- nyi()
            }
            case CommunicationStatements_A13(_)
            {
               NotCovered("communication statement 13", S)
               Code <- nyi()
            }
            case CommunicationStatements_A14(_)
            {
               NotCovered("communication statement 14", S)
               Code <- nyi()
            }
            case CommunicationStatements_A15(_)
            {
               NotCovered("communication statement 15", S)
               Code <- nyi()
            }
            case CommunicationStatements_A16(_)
            {
               NotCovered("communication statement 16", S)
               Code <- nyi()
            }
         }
   
   proc Trafo_BasicStatements($BasicStatements -> STMT)
      
      rule Trafo_BasicStatements(X -> Res)
         switch X
         {
            case BasicStatements_A1(ASSIGN)
            {
               Trafo_Assignment(ASSIGN -> Res)
            }
            case BasicStatements_A2(LOG)
            {
               Trafo_LogStatement(LOG -> Res)
            }
            case BasicStatements_A3(Loop)
            {
               Stmt_Loop(Loop -> Res)
            }
            case BasicStatements_A4(Conditional)
            {
               Stmt_Conditional(Conditional -> Res)
            }
            case BasicStatements_A5(_)
            {
               NotCovered("BasicStatement 5", X)
               Res <- nyi()
            }
            case BasicStatements_A6(_)
            {
               NotCovered("BasicStatement 6", X)
               Res <- nyi()
            }
         }
   
   proc Trafo_BehaviourStatements($BehaviourStatements -> STMT)
      
      rule Trafo_BehaviourStatements(X1 -> Code)
         switch X1
         {
            case BehaviourStatements_A1(ZZ11)
            {
               Stmt_TestcaseInstance(ZZ11 -> Code)
            }
            case BehaviourStatements_A2(X11)
            {
               Stmt_FunctionInstance(X11 -> Code)
            }
            case BehaviourStatements_A3(ReturnStatement)
            {
               NotCovered("return", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A4(AltConstruct)
            {
               Stmt_AltConstruct(AltConstruct -> Code)
            }
            case BehaviourStatements_A5(InterleaveStatement)
            {
               NotCovered("interleave", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A6(LabelStatement)
            {
               NotCovered("label", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A7(GotoStatement)
            {
               NotCovered("goto", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A8()
            {
               Code <- stmt_repeat()
            }
            case BehaviourStatements_A9(DeactivateStatement)
            {
               NotCovered("deactivate", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A10(AltstepInstance)
            {
               NotCovered("altstep", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A11(ActivateOp)
            {
               NotCovered("activate", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A12()
            {
               NotCovered("break", X1)
               Code <- nyi()
            }
            case BehaviourStatements_A13()
            {
               NotCovered("continue", X1)
               Code <- nyi()
            }
         }

