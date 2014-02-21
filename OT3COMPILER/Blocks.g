
   
   proc Trafo_StatementBlock($StatementBlock -> STMT)
      
      rule Trafo_StatementBlock(STBLOCK -> Code)
         switch STBLOCK
         {
            case StatementBlock_A1(DEFS, BODY)
            {
               BeginLocalScope()
               ZzTrafo_DEFS(DEFS -> Defs)
               ZzTrafo_BODY(BODY -> Body)
               EndLocalScope()
               Code <- stmt_block(Defs, Body)
            }
         }
   
   proc ZzTrafo_DEFS($StatementBlock_A1_M2? -> STMT)
      
      rule ZzTrafo_DEFS(DEFS -> Code)
         {
            DEFS -> $StatementBlock_A1_M2?()
            Code <- stmt_null()
         |
            DEFS -> $StatementBlock_A1_M2? (Block)
            switch Block
            {
               case StatementBlock_A1_M2_A1(L)
               {
                  switch L
                  {
                     case FunctionDefList_A1(SEQ)
                     {
                        ZzTrafo_DEFSEQ(SEQ -> Code)
                     }
                  }
               }
            }
         }
   
   proc ZzTrafo_DEFSEQ($FunctionDefList_A1_M1[] -> STMT)
      
      rule ZzTrafo_DEFSEQ(SEQ -> Code)
         {
            SEQ -> $FunctionDefList_A1_M1[HEAD::TAIL]
            ZzTrafo_DEFHEAD(HEAD -> Code1)
            ZzTrafo_DEFSEQ(TAIL -> Code2)
            Code <- stmt_seq(Code1, Code2)
         |
            SEQ -> $FunctionDefList_A1_M1[]
            Code <- stmt_null()
         }
   
   proc ZzTrafo_DEFHEAD($FunctionDefList_A1_M1 -> STMT)
      
      rule ZzTrafo_DEFHEAD(HEAD -> Code)
         switch HEAD
         {
            case FunctionDefList_A1_M1_A1(L, XX, Semi)
            {
               switch L
               {
                  case FunctionDefList_A1_M1_A1_M1_A1(_)
                  {
                     error "cover"
                     Code <- stmt_null()
                  }
                  case FunctionDefList_A1_M1_A1_M1_A2(X1)
                  {
                     LOCALDECL(X1 -> Code)
                     {
                        XX -> $FunctionDefList_A1_M1_A1_M2?()
                     |
                        XX -> $FunctionDefList_A1_M1_A1_M2? (_)
                        NotCovered("'with' for local declarations", XX)
                     }
                  }
               }
            }
         }
   
   proc LOCALDECL($FunctionLocalInst -> STMT)
      
      rule LOCALDECL(X1 -> Code)
         switch X1
         {
            case FunctionLocalInst_A1(XX1)
            {
               switch XX1
               {
                  case VarInstance_A1(XXX)
                  {
                     switch XXX
                     {
                        case VarInstance_A1_M2_A1(QQ5)
                        {
                           DeclareLocalVar(QQ5 -> Code)
                        }
                        case VarInstance_A1_M2_A2(_)
                        {
                           error "cover"
                           Code <- stmt_null()
                        }
                     }
                  }
               }
            }
            case FunctionLocalInst_A2(XXXX)
            {
               switch XXXX
               {
                  case TimerInstance_A1(XXXXX)
                  {
                     switch XXXXX
                     {
                        case VarList_A1(XXA, XXB)
                        {
                           switch XXA
                           {
                              case SingleVarInstance_A1(Id, XXA1, XXA2)
                              {
                                 {
                                    XXA1 -> $SingleVarInstance_A1_M2?()
                                 |
                                    XXA1 -> $SingleVarInstance_A1_M2? (ArrayDef)
                                    NotCovered("declaration of timer arrays", ArrayDef)
                                 }
                                 {
                                    XXA2 -> $SingleVarInstance_A1_M3?()
                                 |
                                    XXA2 -> $SingleVarInstance_A1_M3? (Init)
                                    NotCovered("initialization of timers", Init)
                                 }
                                 {
                                    XXB -> $VarList_A1_M2[]
                                 |
                                    XXB -> $VarList_A1_M2[H::T]
                                    NotCovered("multiple declaration of timers", H)
                                 }
                                 sourcepos Id -> Pos
                                 DefineLocalTimer(Id, Pos)
                                 Code <- stmt_declaretimer(Id)
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc DeclareLocalVar($VarInstance_A1_M2_A1_M1 -> STMT)
      
      rule DeclareLocalVar(QQ5 -> Code)
         switch QQ5
         {
            case VarInstance_A1_M2_A1_M1_A1(X1, X2)
            {
               switch X1
               {
                  case Type_A1(Y)
                  {
                     switch Y
                     {
                        case PredefinedType_A1()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A2()
                        {
                           ExtractId(X2 -> Id)
                           sourcepos Id -> Pos
                           DefineLocalVar(Id, tp_boolean(), Pos)
                           Code <- stmt_declareboolean(Id)
                        }
                        case PredefinedType_A3()
                        {
                           ExtractId(X2 -> Id)
                           sourcepos Id -> Pos
                           DefineLocalVar(Id, tp_charstring(), Pos)
                           Code <- stmt_declarecharstring(Id)
                        }
                        case PredefinedType_A4(_)
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A5()
                        {
                           ExtractId(X2 -> Id)
                           sourcepos Id -> Pos
                           DefineLocalVar(Id, tp_integer(), Pos)
                           Code <- stmt_declareinteger(Id)
                        }
                        case PredefinedType_A6()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A7()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A8()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A9()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A10()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A11()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A12()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                        case PredefinedType_A13()
                        {
                           NotCovered("var of this type", Y)
                           Code <- nyi()
                        }
                     }
                  }
                  case Type_A2(X)
                  {
                     switch X
                     {
                        case ReferencedType_A1(Z1, TypeId, Z3, Z4, Z5)
                        {
                           sourcepos TypeId -> Pos
                           {
                              Z1 -> $ReferencedType_A1_M1?()
                           |
                              Z1 -> $ReferencedType_A1_M1? (_)
                              NotCovered("qualified id", Z1)
                           }
                           {
                              Z3 -> $ReferencedType_A1_M3?()
                           |
                              Z3 -> $ReferencedType_A1_M3? (_)
                              NotCovered("param list", Z3)
                           }
                           {
                              Z4 -> $ReferencedType_A1_M4?()
                           |
                              Z4 -> $ReferencedType_A1_M4? (_)
                              NotCovered("type param list", Z4)
                           }
                           {
                              Z5 -> $ReferencedType_A1_M5?()
                           |
                              Z5 -> $ReferencedType_A1_M5? (_)
                              NotCovered("extended field ref", Z5)
                           }
                           GloballyDefined(TypeId, Pos -> Def)
                           {
                              Def -> recordtype(_, _, _)
                           |
                              Def -> componenttype(_, _)
                           |
                              ErrorI("'", TypeId, "' is not allowed as type", Pos)
                           }
                           ExtractId(X2 -> Id)
                           sourcepos Id -> Pos2
                           DefineLocalVar(Id, tp_referenced(Def), Pos2)
                           Code <- stmt_declarereferenced(Id, TypeId)
                        }
                     }
                  }
               }
            }
         }
   
   proc ExtractId($VarList -> string)
      
      rule ExtractId(X -> Id)
         switch X
         {
            case VarList_A1(X1, X2)
            {
               switch X1
               {
                  case SingleVarInstance_A1(Id, XX1, XX2)
                  {
                     {
                        XX1 -> $SingleVarInstance_A1_M2?()
                     |
                        XX1 -> $SingleVarInstance_A1_M2? (ArrayDef)
                        NotCovered("declaration of array variables", ArrayDef)
                     }
                     {
                        XX2 -> $SingleVarInstance_A1_M3?()
                     |
                        XX2 -> $SingleVarInstance_A1_M3? (Init)
                        NotCovered("var declaration with initialization", Init)
                     }
                  }
               }
               {
                  X2 -> $VarList_A1_M2[]
               |
                  X2 -> $VarList_A1_M2[H::T]
                  NotCovered("multiple var declaration", H)
               }
            }
         }
   
   proc ZzTrafo_BODY($StatementBlock_A1_M3? -> STMT)
      
      rule ZzTrafo_BODY(BODY -> Code)
         {
            BODY -> $StatementBlock_A1_M3? (X)
            switch X
            {
               case StatementBlock_A1_M3_A1(XX)
               {
                  switch XX
                  {
                     case FunctionStatementList_A1(LIST)
                     {
                        ZzTrafo_FunctionStatements(LIST -> Code)
                     }
                  }
               }
            }
         |
            BODY -> $StatementBlock_A1_M3?()
            Code <- stmt_null()
         }
   
   proc ZzTrafo_FunctionStatements($FunctionStatementList_A1_M1[] -> STMT)
      
      rule ZzTrafo_FunctionStatements(LIST -> Code)
         {
            LIST -> $FunctionStatementList_A1_M1[HEAD::TAIL]
            switch HEAD
            {
               case FunctionStatementList_A1_M1_A1(FunctionSt, Semi)
               {
                  Trafo_FunctionStatement(FunctionSt -> Res)
                  ZzTrafo_FunctionStatements(TAIL -> ResTail)
                  Code <- stmt_seq(Res, ResTail)
               }
            }
         |
            LIST -> $FunctionStatementList_A1_M1[]
            Code <- stmt_null()
         }

