
   
   proc Stmt_MapStatement_A1($MapStatement -> STMT)
      
      rule Stmt_MapStatement_A1(MapStatement -> Code)
         switch MapStatement
         {
            case MapStatement_A1(X1, X2, X3)
            {
               SingleConnection(X1 -> PORTCODE1, PORTCODE2)
               sourcepos MapStatement -> Pos
               {
                  PORTCODE1 -> expr_compportref(_, _)
                  PORTCODE2 -> expr_systemc_port(_)
                  Code <- stmt_map(PORTCODE1, PORTCODE2)
               |
                  PORTCODE1 -> expr_systemc_port(_)
                  PORTCODE2 -> expr_compportref(_, _)
                  Code <- stmt_map(PORTCODE2, PORTCODE1)
               |
                  PORTCODE1 -> expr_systemc_port(_)
                  PORTCODE2 -> expr_systemc_port(_)
                  Error("Cannot map system port onto system port", Pos)
                  Code <- stmt_null()
               |
                  PORTCODE1 -> expr_compportref(_, _)
                  PORTCODE2 -> expr_compportref(_, _)
                  Error("Cannot map component port onto component port", Pos)
                  Code <- stmt_null()
               |
                  Error("One of the ports must a system port", Pos)
                  Code <- stmt_null()
               }
               {
                  X2 -> $MapStatement_A1_M3?()
               |
                  X2 -> $MapStatement_A1_M3? (_)
                  NotCovered("this form of map", X2)
               }
               {
                  X3 -> $MapStatement_A1_M4?()
               |
                  X3 -> $MapStatement_A1_M4? (_)
                  NotCovered("this form of map", X3)
               }
            }
         }
   
   proc Stmt_UnmapStatement($UnmapStatement -> STMT)
      
      rule Stmt_UnmapStatement(UnmapStatement -> Code)
         switch UnmapStatement
         {
            case UnmapStatement_A1(X)
            {
               {
                  X -> $UnmapStatement_A1_M2? (X1)
                  switch X1
                  {
                     case UnmapStatement_A1_M2_A1(X11, X12)
                     {
                        SingleConnection(X11 -> PORTCODE1, PORTCODE2)
                        sourcepos UnmapStatement -> Pos
                        {
                           PORTCODE1 -> expr_compportref(_, _)
                           PORTCODE2 -> expr_systemc_port(_)
                           Code <- stmt_unmap(PORTCODE1, PORTCODE2)
                        |
                           PORTCODE1 -> expr_systemc_port(_)
                           PORTCODE2 -> expr_compportref(_, _)
                           Code <- stmt_unmap(PORTCODE2, PORTCODE1)
                        |
                           PORTCODE1 -> expr_systemc_port(_)
                           PORTCODE2 -> expr_systemc_port(_)
                           Error("Cannot unmap system port from system port", Pos)
                           Code <- stmt_null()
                        |
                           PORTCODE1 -> expr_compportref(_, _)
                           PORTCODE2 -> expr_compportref(_, _)
                           Error("Cannot unmap component port from component port", Pos)
                           Code <- stmt_null()
                        |
                           Error("One of the ports must a system port", Pos)
                           Code <- stmt_null()
                        }
                        {
                           X12 -> $UnmapStatement_A1_M2_A1_M2?()
                        |
                           X12 -> $UnmapStatement_A1_M2_A1_M2? (_)
                           NotCovered("this form of unmap", X12)
                        }
                     }
                     case UnmapStatement_A1_M2_A2(_, _)
                     {
                        NotCovered("this form of unmap", X1)
                        Code <- stmt_error()
                     }
                     case UnmapStatement_A1_M2_A3(_)
                     {
                        NotCovered("this form of unmap", X1)
                        Code <- stmt_error()
                     }
                     case UnmapStatement_A1_M2_A4(_)
                     {
                        NotCovered("this form of unmap", X1)
                        Code <- stmt_error()
                     }
                  }
               |
                  X -> $UnmapStatement_A1_M2?()
                  NotCovered("this form of unmap", X)
                  Code <- stmt_error()
               }
            }
         }
   
   proc Stmt_ConnectStatement_A1($ConnectStatement -> STMT)
      
      rule Stmt_ConnectStatement_A1(ConnectStatement -> Code)
         switch ConnectStatement
         {
            case ConnectStatement_A1(X, CST)
            {
               SingleConnection(X -> PORTCODE1, PORTCODE2)
               {
                  CST -> $ConnectStatement_A1_M3?()
               |
                  CST -> $ConnectStatement_A1_M3? (_)
                  NotCovered("this form of connect", CST)
               }
               Code <- stmt_connect(PORTCODE1, PORTCODE2)
            }
         }
   
   proc SingleConnection($SingleConnectionSpec -> EXPR, EXPR)
      
      rule SingleConnection(X -> PORTCODE1, PORTCODE2)
         switch X
         {
            case SingleConnectionSpec_A1(PORT1, PORT2)
            {
               PortReference(PORT1 -> PORTCODE1)
               PortReference(PORT2 -> PORTCODE2)
            }
         }
   
   proc PortReference($PortRef -> EXPR)
      
      rule PortReference(PORT -> Code)
         switch PORT
         {
            case PortRef_A1(X1, X2)
            {
               switch X2
               {
                  case ArrayIdentifierRef_A1(PortId, X22)
                  {
                  }
               }
               {
                  X22 -> $ArrayIdentifierRef_A1_M2[]
               |
                  X22 -> $ArrayIdentifierRef_A1_M2[_::_]
                  NotCovered("this form of port specification", X22)
               }
               switch X1
               {
                  case ComponentRef_A1(X11)
                  {
                     {
                        switch X11
                        {
                           case ComponentOrDefaultReference_A1(XX)
                           {
                              switch XX
                              {
                                 case VariableRef_A1(ComponentId, XXX)
                                 {
                                    sourcepos ComponentId -> Pos
                                    LocallyDefined(ComponentId, Pos -> Def)
                                    {
                                       Def -> localvar(Tp)
                                       Tp -> tp_referenced(C)
                                       C -> componenttype(_, _)
                                    |
                                       ErrorI("'", ComponentId, "' not declared as component", Pos)
                                    }
                                    {
                                       XXX -> $VariableRef_A1_M2?()
                                    |
                                       XXX -> $VariableRef_A1_M2? (_)
                                       NotCovered("this form of port spec", XXX)
                                    }
                                    Code <- expr_compportref(idexpr(ComponentId), PortId)
                                 }
                              }
                           }
                           case ComponentOrDefaultReference_A2(_)
                           {
                              NotCovered("this port spec", X11)
                              Code <- expr_systemc_port("-")
                           }
                        }
                     |
                        NotCovered("this port spec", X1)
                        Code <- expr_systemc_port("-")
                     }
                  }
                  case ComponentRef_A2()
                  {
                     Code <- expr_systemc_port(PortId)
                  }
                  case ComponentRef_A3()
                  {
                     NotCovered("self port", X1)
                     Code <- expr_systemc_port("-")
                  }
                  case ComponentRef_A4()
                  {
                     NotCovered("mtc port", X1)
                     Code <- expr_systemc_port("-")
                  }
               }
            }
         }

