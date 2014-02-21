
   
   proc Stmt_ReceiveStatement($ReceiveStatement -> STMT)
      
      rule Stmt_ReceiveStatement(RECEIVE -> Code)
         switch RECEIVE
         {
            case ReceiveStatement_A1(Arg1, Arg2)
            {
               HandleReceive(RECEIVE -> CodeRECEIVE)
               ResBlock <- stmt_null()
               Cd <- altguard(CodeRECEIVE, ResBlock)
               Code <- stmt_standalone(Cd)
            }
         }
   
   proc HandleReceive($ReceiveStatement -> RECEIVE)
      
      rule HandleReceive(R -> CodeR)
         switch R
         {
            case ReceiveStatement_A1(Port, X2)
            {
               switch Port
               {
                  case PortOrAny_A1(Port1)
                  {
                     switch Port1
                     {
                        case ArrayIdentifierRef_A1(PortId, Array)
                        {
                           {
                              Array -> $ArrayIdentifierRef_A1_M2[]
                           |
                              Array -> $ArrayIdentifierRef_A1_M2[_::_]
                              NotCovered("port array", Array)
                           }
                        }
                     }
                  }
                  case PortOrAny_A2()
                  {
                     NotCovered("any port", Port)
                     PortId <- "unused"
                  }
               }
               switch X2
               {
                  case PortReceiveOp_A1(M2, M3, Umlenk)
                  {
                     {
                        M2 -> $PortReceiveOp_A1_M2? (M2X)
                        M2X -> PortReceiveOp_A1_M2_A1(TEMPLATE)
                        {
                           M3 -> $PortReceiveOp_A1_M3?()
                        |
                           M3 -> $PortReceiveOp_A1_M3? (_)
                           NotCovered("from sender", M2)
                        }
                        Redirection(Umlenk -> Redir)
                        InLineTemplateInReceive(TEMPLATE, PortId, Redir -> CodeR)
                     |
                        M2 -> $PortReceiveOp_A1_M2?()
                        NotCovered("missing template", M2)
                        CodeR <- receive_unused()
                     }
                  }
               }
            }
         }
   
   proc Redirection($PortReceiveOp_A1_M4? -> string)
      
      rule Redirection(Umlenk -> Redir)
         {
            Umlenk -> $PortReceiveOp_A1_M4?()
            Redir <- "/noredirect/"
         |
            Umlenk -> $PortReceiveOp_A1_M4? (X)
            switch X
            {
               case PortReceiveOp_A1_M4_A1(X2)
               {
                  switch X2
                  {
                     case PortRedirect_A1(X3)
                     {
                        switch X3
                        {
                           case PortRedirect_A1_M2_A3(_)
                           {
                              NotCovered("redirect timestamp", X3)
                              Redir <- ""
                           }
                           case PortRedirect_A1_M2_A2(_, _)
                           {
                              NotCovered("redirect sender", X2)
                              Redir <- ""
                           }
                           case PortRedirect_A1_M2_A1(X4a, X4b, X4c)
                           {
                              switch X4a
                              {
                                 case ValueSpec_A1(X4aa)
                                 {
                                    switch X4aa
                                    {
                                       case ValueSpec_A1_M2_A1(X4aaa)
                                       {
                                          switch X4aaa
                                          {
                                             case VariableRef_A1(MSG, Rest)
                                             {
                                                {
                                                   Rest -> $VariableRef_A1_M2?()
                                                |
                                                   Rest -> $VariableRef_A1_M2? (_)
                                                   NotCovered("variable suffix", Rest)
                                                }
                                             }
                                          }
                                       }
                                       case ValueSpec_A1_M2_A2(_)
                                       {
                                          error "cover"
                                          MSG <- ""
                                       }
                                    }
                                 }
                              }
                              {
                                 X4b -> $PortRedirect_A1_M2_A1_M2?()
                              |
                                 X4b -> $PortRedirect_A1_M2_A1_M2? (_)
                                 NotCovered("redirect sender", X4b)
                              }
                              {
                                 X4c -> $PortRedirect_A1_M2_A1_M3?()
                              |
                                 X4c -> $PortRedirect_A1_M2_A1_M3? (_)
                                 NotCovered("redirect irgendwas", X4c)
                              }
                              Redir <- MSG
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc InLineTemplateInReceive($InLineTemplate, string, string -> RECEIVE)
      
      rule InLineTemplateInReceive(TEMPLATE, PortId, Redir -> CodeR2)
         primaTrafo_InLineTemplate(TEMPLATE -> Tp, EXPR)
         CodeR2 <- receive_value(EXPR, PortId, Redir)

