
   
   proc ProcessControlPart($yyControlPart?)
      
      rule ProcessControlPart(X)
         {
            X -> $yyControlPart? (CP)
            switch CP
            {
               case yyControlPart_A1(MCP)
               {
                  switch MCP
                  {
                     case ModuleControlPart_A1(X1, With, Semicolon)
                     {
                        BeginLocalScope()
                        ZzPriCtl_Trafo_ModuleControlBody(X1 -> Code)
                        EndLocalScope()
                        Emit_ControlPart(Code)
                        {
                           With -> $ModuleControlPart_A1_M5?()
                        |
                           With -> $ModuleControlPart_A1_M5? (W)
                           NotCovered("'with' for control part", W)
                        }
                     }
                  }
               }
            }
         |
            X -> $yyControlPart?()
         }
   
   proc ZzPriCtl_Trafo_ModuleControlBody($ModuleControlBody -> STMT)
      
      rule ZzPriCtl_Trafo_ModuleControlBody(X -> Code)
         X -> ModuleControlBody_A1(X1)
         {
            X1 -> $ModuleControlBody_A1_M1? (B)
            switch B
            {
               case ModuleControlBody_A1_M1_A1(B2)
               {
                  switch B2
                  {
                     case ControlStatementOrDefList_A1(LIST)
                     {
                        ZzPriCtl_Trafo_LIST(LIST -> Code)
                     }
                  }
               }
            }
         |
            X1 -> $ModuleControlBody_A1_M1?()
            Code <- stmt_nolist()
         }
   
   proc ZzPriCtl_Trafo_LIST($ControlStatementOrDefList_A1_M1[] -> STMT)
      
      rule ZzPriCtl_Trafo_LIST(LIST -> Code)
         {
            LIST -> $ControlStatementOrDefList_A1_M1[HEAD::TAIL]
            ZzProcess_Head(HEAD -> CodeHead)
            ZzPriCtl_Trafo_LIST(TAIL -> CodeTail)
            Code <- stmt_seq(CodeHead, CodeTail)
         |
            LIST -> $ControlStatementOrDefList_A1_M1[]
            Code <- stmt_null()
         }
   
   proc ZzProcess_Head($ControlStatementOrDefList_A1_M1 -> STMT)
      
      rule ZzProcess_Head(HEAD -> CodeHead)
         switch HEAD
         {
            case ControlStatementOrDefList_A1_M1_A1(K, Semicolon)
            {
               switch K
               {
                  case ControlStatementOrDef_A1(S2, With)
                  {
                     switch S2
                     {
                        case ControlStatementOrDef_A1_M1_A2(S3)
                        {
                           LOCALDECL(S3 -> CodeHead)
                        }
                        case ControlStatementOrDef_A1_M1_A1(_)
                        {
                           error "cover"
                           CodeHead <- stmt_null()
                        }
                     }
                     {
                        With -> $ControlStatementOrDef_A1_M2?()
                     |
                        With -> $ControlStatementOrDef_A1_M2? (W)
                        NotCovered("'with' for declaration inside control part", W)
                     }
                  }
                  case ControlStatementOrDef_A2(S)
                  {
                     Trafo_ControlStatement(S -> CodeHead)
                  }
               }
            }
         }

