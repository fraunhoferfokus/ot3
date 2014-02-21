
   
   proc Stmt_StartTCStatement($StartTCStatement -> STMT)
      
      rule Stmt_StartTCStatement(StartTCStatement -> Code)
         switch StartTCStatement
         {
            case StartTCStatement_A1(Comp, Fun)
            {
               switch Comp
               {
                  case ComponentOrDefaultReference_A1(Var)
                  {
                     Var -> VariableRef_A1(Id1, Z1)
                     Z1 -> $VariableRef_A1_M2?()
                     sourcepos Id1 -> Pos1
                     LocallyDefined(Id1, Pos1 -> Def1)
                     {
                        Def1 -> localvar(Tp)
                        Tp -> tp_referenced(Ref)
                        Ref -> componenttype(_, _)
                     |
                        ErrorI("'", Id1, "' is not defined as component", Pos1)
                     }
                  }
                  case ComponentOrDefaultReference_A2(_)
                  {
                     NotCovered("comp ref", Comp)
                     Id1 <- "-"
                  }
               }
               switch Fun
               {
                  case FunctionInstance_A1(FunRef, X1, X2)
                  {
                     FunRef -> FunctionRef_A1(Z2, Id2)
                     {
                        Z2 -> $FunctionRef_A1_M1?()
                     |
                        Z2 -> $FunctionRef_A1_M1? (_)
                        NotCovered("function ref 1", Z2)
                     }
                     {
                        X1 -> $FunctionInstance_A1_M2?()
                     |
                        X1 -> $FunctionInstance_A1_M2? (_)
                        NotCovered("function ref 2", X1)
                     }
                     {
                        X2 -> $FunctionInstance_A1_M4?()
                     |
                        X2 -> $FunctionInstance_A1_M4? (_)
                        NotCovered("function ref 3", X2)
                     }
                     sourcepos Id2 -> Pos2
                     GloballyDefined(Id2, Pos2 -> Def2)
                     {
                        Def2 -> functiondefinition(_, _)
                     |
                        ErrorI("'", Id2, "' is not defined as function", Pos2)
                     }
                  }
                  case FunctionInstance_A2(_, _)
                  {
                     NotCovered("function instance", Fun)
                     Id2 <- "-"
                  }
               }
               Code <- stmt_startcomponent(Id1, Id2)
            }
         }
   
   proc Stmt_StartTimerStatement($StartTimerStatement -> STMT)
      
      rule Stmt_StartTimerStatement(StartTimerStatement -> Code)
         switch StartTimerStatement
         {
            case StartTimerStatement_A1(X1, X2)
            {
               switch X1
               {
                  case ArrayIdentifierRef_A1(Id, A)
                  {
                     sourcepos Id -> PosId
                     LocallyDefined(Id, PosId -> Def)
                     {
                        Def -> localtimer()
                     |
                        ErrorI("'", Id, "' is not defined as timer", PosId)
                     }
                     {
                        A -> $ArrayIdentifierRef_A1_M2[]
                     |
                        A -> $ArrayIdentifierRef_A1_M2[_::_]
                        NotCovered("start timer array", A)
                     }
                  }
               }
               {
                  X2 -> $StartTimerStatement_A1_M4? (X21)
                  switch X21
                  {
                     case StartTimerStatement_A1_M4_A1(X211)
                     {
                        Trafo_Expression(X211 -> Tp, X)
                        Code <- stmt_starttimerexpr(Id, X)
                        {
                           Tp -> tp_float()
                        |
                           sourcepos X211 -> Pos
                           Error("float value required", Pos)
                        }
                     }
                  }
               |
                  X2 -> $StartTimerStatement_A1_M4?()
                  NotCovered("start timer a1 m4", X2)
                  Code <- stmt_null()
               }
            }
         }
   
   proc Stmt_StopTimerStatement($StopTimerStatement -> STMT)
      
      rule Stmt_StopTimerStatement(StopTimerStatement -> Code)
         switch StopTimerStatement
         {
            case StopTimerStatement_A1(Timer)
            {
               switch Timer
               {
                  case TimerRefOrAll_A1(X)
                  {
                     switch X
                     {
                        case ArrayIdentifierRef_A1(Str, Z)
                        {
                           {
                              Z -> $ArrayIdentifierRef_A1_M2[]
                           |
                              Z -> $ArrayIdentifierRef_A1_M2[_::_]
                              NotCovered("timer ref 1", Z)
                           }
                        }
                     }
                  }
                  case TimerRefOrAll_A2()
                  {
                     NotCovered("timer ref 2", Timer)
                     Str <- "-"
                  }
               }
            }
         }
         sourcepos Str -> Pos
         LocallyDefined(Str, Pos -> Def)
         {
            Def -> localtimer()
            NotCovered("stop timer", Str)
            Code <- stmt_stopcomponent(Str)
         |
            Def -> localvar(Tp)
            {
               Tp -> tp_referenced(Ref)
               Ref -> componenttype(_, _)
            |
               ErrorI("'", Str, "' is not defined as component", Pos)
            }
            Code <- stmt_stopcomponent(Str)
         |
            ErrorI("'", Str, "': timer or component expected", Pos)
            Code <- stmt_stopcomponent(Str)
         }
   
   proc Trafo_SendStatement($SendStatement -> STMT)
      
      rule Trafo_SendStatement(SendStmt -> Code)
         switch SendStmt
         {
            case SendStatement_A1(Arg1, Arg2)
            {
               switch Arg1
               {
                  case ArrayIdentifierRef_A1(PortId, X)
                  {
                     {
                        X -> $ArrayIdentifierRef_A1_M2[]
                     |
                        X -> $ArrayIdentifierRef_A1_M2[_::_]
                        NotCovered("port indices", X)
                     }
                  }
               }
               switch Arg2
               {
                  case PortSendOp_A1(X1, X2)
                  {
                     switch X1
                     {
                        case InLineTemplate_A1(X11, X12, Body)
                        {
                           {
                              X11 -> $InLineTemplate_A1_M1?()
                           |
                              X11 -> $InLineTemplate_A1_M1? (_)
                              NotCovered("xxx", X11)
                           }
                           {
                              X12 -> $InLineTemplate_A1_M2?()
                           |
                              X12 -> $InLineTemplate_A1_M2? (_)
                              NotCovered("xxx", X12)
                           }
                        }
                     }
                     {
                        X2 -> $PortSendOp_A1_M5?()
                     |
                        X2 -> $PortSendOp_A1_M5? (_)
                        NotCovered("xxx", X2)
                     }
                  }
               }
               sourcepos PortId -> Pos
               LocallyDefined(PortId, Pos -> Def)
               {
                  Def -> componentmember_port(_)
               |
                  ErrorI("'", PortId, "' not declared as port", Pos)
               }
               Prefix <- ZInLineTemplate_A1_M1?()
               Trafo_TemplateBody(Body, Prefix -> Tp, Res)
               Port2 <- expr_runsonc_port(PortId)
               Code <- stmt_send(Port2, Res)
            }
         }
   
   proc Stmt_DoneStatement($DoneStatement -> STMT)
      
      rule Stmt_DoneStatement(DoneStatement -> Code)
         switch DoneStatement
         {
            case DoneStatement_A1(Comp)
            {
               switch Comp
               {
                  case ComponentId_A1(X)
                  {
                     switch X
                     {
                        case ComponentOrDefaultReference_A1(X1)
                        {
                           switch X1
                           {
                              case VariableRef_A1(Id, Ref)
                              {
                                 {
                                    Ref -> $VariableRef_A1_M2?()
                                 |
                                    Ref -> $VariableRef_A1_M2? (_)
                                    NotCovered("component id a1 m2", Ref)
                                 }
                                 Code <- stmt_done(Id)
                              }
                           }
                        }
                        case ComponentOrDefaultReference_A2(_)
                        {
                           NotCovered("component or default reference", X)
                           Code <- stmt_null()
                        }
                     }
                  }
                  case ComponentId_A2(_)
                  {
                     NotCovered("component id a2", Comp)
                     Code <- stmt_null()
                  }
               }
            }
         }
   
   proc Trafo_SetLocalVerdict($SetLocalVerdict -> STMT)
      
      rule Trafo_SetLocalVerdict(S6 -> Code)
         switch S6
         {
            case SetLocalVerdict_A1(EXPR, X)
            {
               {
                  X -> $SetLocalVerdict_A1_M4[]
               |
                  X -> $SetLocalVerdict_A1_M4[_::_]
               }
               Trafo_SingleExpression(EXPR -> Tp, Res)
               {
                  Tp -> tp_verdict()
               |
                  sourcepos EXPR -> Pos
                  Error("verdict value required", Pos)
               }
               Code <- stmt_verdict(Res)
            }
         }
   
   proc Trafo_SUTStatements($SUTStatements -> STMT)
      
      rule Trafo_SUTStatements(S7 -> Code)
         switch S7
         {
            case SUTStatements_A1(AT, QQQ)
            {
               switch AT
               {
                  case ActionText_A1(FT)
                  {
                     switch FT
                     {
                        case FreeText_A1(FT2)
                        {
                           switch FT2
                           {
                              case FREETEXT_A1(Str)
                              {
                                 Code <- stmt_action(Str)
                              }
                           }
                        }
                     }
                  }
                  case ActionText_A2(_)
                  {
                     NotCovered("action text a2", AT)
                     Code <- stmt_null()
                  }
               }
               {
                  QQQ -> $SUTStatements_A1_M4[]
               |
                  QQQ -> $SUTStatements_A1_M4[_::_]
                  NotCovered("action text extended", QQQ)
               }
            }
         }
   
   proc Trafo_LogStatement($LogStatement -> STMT)
      
      rule Trafo_LogStatement(X -> stmt_log(E))
         switch X
         {
            case LogStatement_A1(X1, X2)
            {
               Trafo_LogItem(X1 -> E)
               {
                  X2 -> $LogStatement_A1_M4[]
               |
                  NotCovered("multiple items in log", X2)
               }
            }
         }
   
   proc Trafo_LogItem($LogItem -> EXPR)
      
      rule Trafo_LogItem(X -> RES)
         switch X
         {
            case LogItem_A1(Text)
            {
               switch Text
               {
                  case FreeText_A1(FT)
                  {
                     switch FT
                     {
                        case FREETEXT_A1(Str)
                        {
                           RES <- expr_cstring(Str)
                        }
                     }
                  }
               }
            }
            case LogItem_A2(Templ)
            {
               primaTrafo_InLineTemplate(Templ -> Tp, RES)
            }
         }
   
   proc Stmt_Loop($LoopConstruct -> STMT)
      
      rule Stmt_Loop(Loop -> Code)
         switch Loop
         {
            case LoopConstruct_A1(For)
            {
               NotCovered("for statement", For)
               Code <- stmt_error()
            }
            case LoopConstruct_A2(While)
            {
               While -> WhileStatement_A1(X1, X2)
               X1 -> BooleanExpression_A1(Expr)
               Trafo_SingleExpression(Expr -> Tp, Res)
               {
                  Tp -> tp_boolean()
               |
                  sourcepos Expr -> Pos
                  Error("boolean expression required", Pos)
               }
               Trafo_StatementBlock(X2 -> ResBlock)
               Code <- stmt_while(Res, ResBlock)
            }
            case LoopConstruct_A3(DoWhile)
            {
               NotCovered("do while statement", DoWhile)
               Code <- stmt_error()
            }
         }
   
   proc Stmt_Conditional($ConditionalConstruct -> STMT)
      
      rule Stmt_Conditional(Conditional -> Code)
         switch Conditional
         {
            case ConditionalConstruct_A1(Condition, ThenPart, Elsifs, ElsePart)
            {
               switch Condition
               {
                  case BooleanExpression_A1(Expr)
                  {
                     Trafo_SingleExpression(Expr -> Tp, CodeCondition)
                     sourcepos Expr -> Pos
                     CheckBool(Tp, Pos)
                  }
               }
               Trafo_StatementBlock(ThenPart -> CodeThenPart)
               Trafo_Elsifs(Elsifs -> CodeElsifs)
               {
                  ElsePart -> $ConditionalConstruct_A1_M7? (Else)
                  switch Else
                  {
                     case ConditionalConstruct_A1_M7_A1(Else2)
                     {
                        switch Else2
                        {
                           case ElseClause_A1(Block)
                           {
                              Trafo_StatementBlock(Block -> CodeElsePart)
                           }
                        }
                     }
                  }
               |
                  ElsePart -> $ConditionalConstruct_A1_M7?()
                  CodeElsePart <- stmt_null()
               }
               Code <- stmt_if(CodeCondition, CodeThenPart, CodeElsifs, CodeElsePart)
            }
         }
   
   type ELSEIF
      elseif(EXPR, STMT)
   
   proc Trafo_Elsifs($ConditionalConstruct_A1_M6[] -> ELSEIF[])
      
      rule Trafo_Elsifs($ConditionalConstruct_A1_M6[Hd::Tl] -> ELSEIF[CodeHd::CodeTl])
         switch Hd
         {
            case ConditionalConstruct_A1_M6_A1(ElsifCl)
            {
               switch ElsifCl
               {
                  case ElseIfClause_A1(Cond, Block)
                  {
                     switch Cond
                     {
                        case BooleanExpression_A1(Expr)
                        {
                           Trafo_SingleExpression(Expr -> Tp, CodeCondition)
                           sourcepos Expr -> Pos
                           CheckBool(Tp, Pos)
                           Trafo_StatementBlock(Block -> ThenPartCode)
                           CodeHd <- elseif(CodeCondition, ThenPartCode)
                        }
                     }
                  }
               }
            }
         }
         Trafo_Elsifs(Tl -> CodeTl)
      
      rule Trafo_Elsifs($ConditionalConstruct_A1_M6[] -> ELSEIF[])
   
   proc CheckBool(Tp, string)
      
      rule CheckBool(Tp, Pos)
         {
            Tp -> tp_boolean()
         |
            Error("boolean expected", Pos)
         }
   
   proc Stmt_TestcaseInstance($TestcaseInstance -> STMT)
      
      rule Stmt_TestcaseInstance(ZZ11 -> Code)
         switch ZZ11
         {
            case TestcaseInstance_A1(ZZ111, ZZ112)
            {
               switch ZZ111
               {
                  case TestcaseInstance_A1_M3_A1(X1, X2, X3)
                  {
                     switch X1
                     {
                        case ExtendedIdentifier_A1(X11, Id)
                        {
                        }
                     }
                     {
                        X11 -> $ExtendedIdentifier_A1_M1?()
                     |
                        X11 -> $ExtendedIdentifier_A1_M1? (_)
                        NotCovered("TestCase extended identifier a1 m1", X11)
                     }
                     {
                        X2 -> $TestcaseInstance_A1_M3_A1_M2?()
                     |
                        X2 -> $TestcaseInstance_A1_M3_A1_M2? (_)
                        NotCovered("TestCase extended identifier a1 m3 a1 m2", X11)
                     }
                     {
                        X3 -> $TestcaseInstance_A1_M3_A1_M4?()
                     |
                        X3 -> $TestcaseInstance_A1_M3_A1_M4? (_)
                        NotCovered("TestCase extended identifier a1 m3 a1 m4", X11)
                     }
                     Code <- stmt_testcaseinst(Id)
                  }
                  case TestcaseInstance_A1_M3_A2(_, _)
                  {
                     NotCovered("TestCase extended identifier a1 m3 a2", ZZ111)
                     Code <- stmt_testcaseinst("")
                  }
               }
               {
                  ZZ112 -> $TestcaseInstance_A1_M4?()
               |
                  ZZ112 -> $TestcaseInstance_A1_M4? (_)
                  NotCovered("TestCase extended identifier a1 m4", ZZ112)
               }
            }
         }
   
   proc Stmt_FunctionInstance($FunctionInstance -> STMT)
      
      rule Stmt_FunctionInstance(X11 -> Code)
         switch X11
         {
            case FunctionInstance_A1(FR, X112, X113)
            {
               switch FR
               {
                  case FunctionRef_A1(X111, Fun)
                  {
                  }
               }
               {
                  X111 -> $FunctionRef_A1_M1?()
               |
                  X111 -> $FunctionRef_A1_M1? (_)
                  NotCovered("Functionref a1 m1", X111)
               }
               {
                  X112 -> $FunctionInstance_A1_M2?()
               |
                  X112 -> $FunctionInstance_A1_M2? (_)
                  NotCovered("Functionref a1 m2", X112)
               }
               {
                  X113 -> $FunctionInstance_A1_M4?()
               |
                  X113 -> $FunctionInstance_A1_M4? (_)
                  NotCovered("Functionref a1 m4", X113)
               }
               sourcepos Fun -> Pos
               GloballyDefined(Fun, Pos -> Def)
               {
                  Def -> functiondefinition(D, RefNormalizedFunction)
                  valof RefNormalizedFunction -> Val
                  Val -> normalizedfunction(RunsOnC, Block)
                  global ActiveRunsOnComponent -> CallerRunsOnC
                  {
                     RunsOnC -> "(none)"
                  |
                     Equal(CallerRunsOnC,RunsOnC)
                  |
                     ErrorI("caller must run on '", RunsOnC, "'", Pos)
                  }
               |
                  ErrorI("'", Fun, "' not defined as function", Pos)
               }
               Code <- stmt_call(Fun)
            }
            case FunctionInstance_A2(_, _)
            {
               NotCovered("/call 2/", X11)
               Code <- nyi()
            }
         }

