
   
   proc Stmt_AltConstruct($AltConstruct -> STMT)
      
      rule Stmt_AltConstruct(AltConstruct -> Code)
         switch AltConstruct
         {
            case AltConstruct_A1(X)
            {
               switch X
               {
                  case AltGuardList_A1(LIST)
                  {
                     BuildAltGuardList(LIST -> ALTGUARDLIST)
                     Code <- stmt_altn(ALTGUARDLIST)
                  }
               }
            }
         }
   
   proc BuildAltGuardList($AltGuardList_A1_M1[] -> ALTGUARD[])
      
      rule BuildAltGuardList($AltGuardList_A1_M1[H::T] -> ALTGUARD[CH::CT])
         HandleAltGuard(H -> CH)
         BuildAltGuardList(T -> CT)
      
      rule BuildAltGuardList($AltGuardList_A1_M1[] -> ALTGUARD[])
   
   proc HandleAltGuard($AltGuardList_A1_M1 -> ALTGUARD)
      
      rule HandleAltGuard(M1 -> Code)
         switch M1
         {
            case AltGuardList_A1_M1_A1(X)
            {
               switch X
               {
                  case GuardStatement_A1(ALTGUARDCHAR, GUARDSTMT)
                  {
                  }
               }
            }
            case AltGuardList_A1_M1_A2(_, _)
            {
               NotCovered("alt guard list", M1)
               M1 -> AltGuardList_A1_M1_A1(GuardStatement_A1(ALTGUARDCHAR, GUARDSTMT))
            }
         }
         switch ALTGUARDCHAR
         {
            case AltGuardChar_A1(XX)
            {
               {
                  XX -> $AltGuardChar_A1_M2?()
               |
                  XX -> $AltGuardChar_A1_M2? (_)
                  NotCovered("alt guard char", XX)
               }
            }
         }
         switch GUARDSTMT
         {
            case GuardStatement_A1_M2_A1(_, _)
            {
               NotCovered("guard statement a1 m2 a1", GUARDSTMT)
               Code <- altguard(receive_timeout("dummy"), stmt_null())
            }
            case GuardStatement_A1_M2_A2(GUARDOP, BLOCK)
            {
               switch GUARDOP
               {
                  case GuardOp_A1(TS)
                  {
                     switch TS
                     {
                        case TimeoutStatement_A1(TS1)
                        {
                           switch TS1
                           {
                              case TimerRefOrAny_A1(TS2)
                              {
                                 switch TS2
                                 {
                                    case ArrayIdentifierRef_A1(Id, TS3)
                                    {
                                       {
                                          TS3 -> $ArrayIdentifierRef_A1_M2[]
                                          Trafo_StatementBlock(BLOCK -> ResBlock)
                                          Code <- altguard(receive_timeout(Id), ResBlock)
                                       |
                                          TS3 -> $ArrayIdentifierRef_A1_M2[_]
                                          NotCovered("array", TS3)
                                          Code <- altguard(receive_timeout("dummy"), stmt_null())
                                       }
                                    }
                                 }
                              }
                              case TimerRefOrAny_A2(_)
                              {
                                 NotCovered("timer ref or any 2", TS1)
                                 Code <- altguard(receive_timeout("dummy"), stmt_null())
                              }
                           }
                        }
                     }
                  }
                  case GuardOp_A2(RECEIVE)
                  {
                     HandleReceive(RECEIVE -> CodeRECEIVE)
                     Trafo_StatementBlock(BLOCK -> ResBlock)
                     Code <- altguard(CodeRECEIVE, ResBlock)
                  }
                  case GuardOp_A3(_)
                  {
                     NotCovered("guard op 3", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A4(_)
                  {
                     NotCovered("guard op 4", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A5(_)
                  {
                     NotCovered("guard op 5", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A6(_)
                  {
                     NotCovered("guard op 6", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A7(_)
                  {
                     NotCovered("guard op 7", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A8(_)
                  {
                     NotCovered("guard op 8", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
                  case GuardOp_A9(_)
                  {
                     NotCovered("guard op 9", GUARDOP)
                     Code <- altguard(receive_timeout("dummy"), stmt_null())
                  }
               }
            }
         }

