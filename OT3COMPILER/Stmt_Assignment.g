
   
   proc Trafo_Assignment($Assignment -> STMT)
      
      rule Trafo_Assignment(ASSIGN -> Code)
         switch ASSIGN
         {
            case Assignment_A1(LHS, RHS)
            {
               Handle_Lhs(LHS -> LhsType, LhsCode)
               Handle_Rhs(RHS -> RhsType, RhsCode)
            }
         }
         {
            RhsType -> tp_compound(Tps)
            sourcepos RHS -> Pos
            CheckCompoundType(RhsType, LhsType, Pos)
            RhsCode -> expr_array(Exprs)
            Code <- stmt_assigncompound1(LhsCode, Exprs)
         |
            RhsType -> tp_compound(Tps)
            RhsCode -> expr_compound(Fields)
            NotCovered("record literals with field names", RHS)
            Code <- stmt_assigncompound2(LhsCode, Fields)
         |
            Equal(RhsType,LhsType)
            Code <- stmt_assign(LhsCode, RhsCode)
         |
            sourcepos LHS -> Pos
            Error("types are not compatible", Pos)
            Code <- stmt_assign(LhsCode, RhsCode)
         }
   
   proc Handle_Lhs($VariableRef -> Tp, EXPR)
      
      rule Handle_Lhs(LHS -> LhsType2, LhsCode2)
         switch LHS
         {
            case VariableRef_A1(Id, Access)
            {
               sourcepos Id -> Pos
               LocallyDefined(Id, Pos -> Meaning)
               switch Meaning
               {
                  case componentmember_port(_)
                  {
                     Error("Assigment to port not allowed", Pos)
                     LhsType <- tp_none()
                     LhsCode <- compidexpr(Id)
                  }
                  case componentmember_var(Tp)
                  {
                     LhsType <- Tp
                     LhsCode <- compidexpr(Id)
                  }
                  case localtimer()
                  {
                     LhsType <- tp_none()
                     LhsCode <- idexpr(Id)
                  }
                  case localvar(Tp)
                  {
                     LhsType <- Tp
                     LhsCode <- idexpr(Id)
                  }
                  case testcasedefinition(_, RefNormalizedTestcase)
                  {
                     ErrorI("Cannot assign to testcase '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
                  case functiondefinition(_, RefNormalizedFunction)
                  {
                     ErrorI("Cannot assign to function '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
                  case recordtype(N, X4, Zusatz)
                  {
                     ErrorI("Cannot assign to record type '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
                  case porttype(N, _, _)
                  {
                     ErrorI("Cannot assign to port type '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
                  case componenttype(_, _)
                  {
                     ErrorI("Cannot assign to component type '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
                  case nodefinition()
                  {
                     ErrorI("Cannot assign to '", Id, "'", Pos)
                     LhsType <- tp_none()
                     LhsCode <- idexpr("/error/")
                  }
               }
               {
                  Access -> $VariableRef_A1_M2?()
                  LhsType2 <- LhsType
                  LhsCode2 <- LhsCode
               |
                  Access -> $VariableRef_A1_M2? (X)
                  switch X
                  {
                     case VariableRef_A1_M2_A1(X1)
                     {
                        HandleExtendedFieldReference(X1, LhsType, LhsCode -> LhsType2, LhsCode2)
                     }
                  }
               }
            }
         }
   
   proc Handle_Rhs($Assignment_A1_M3 -> Tp, EXPR)
      
      rule Handle_Rhs(RHS -> Tp, RES)
         switch RHS
         {
            case Assignment_A1_M3_A1(X)
            {
               Trafo_Expression(X -> Tp, RES)
            }
            case Assignment_A1_M3_A2(TEMPL)
            {
               Trafo_TemplateBody0(TEMPL -> Tp, RES)
            }
         }
   
   proc CheckCompoundType(Tp, Tp, string)
      
      rule CheckCompoundType(RhsType, LhsType, Pos)
         RhsType -> tp_compound(Tps)
         LhsType -> tp_referenced(R)
         R -> recordtype(_, _, Ref)
         valof Ref -> Val
         Val -> normalizedfields(Fields)
         CheckCompoundFields(Fields, Tps, Pos)
      
      rule CheckCompoundType(RhsType, LhsType, Pos)
         Error("type of literal is not compatble", Pos)
   
   proc CheckCompoundFields(FIELD[], Tp[], string)
      
      rule CheckCompoundFields(FIELD[HFd::TFd], Tp[HTp::TTp], Pos)
         CheckCompoundField(HFd, HTp, Pos)
         CheckCompoundFields(TFd, TTp, Pos)
      
      rule CheckCompoundFields(FIELD[], Tp[], Pos)
      
      rule CheckCompoundFields(Tps, Fields, Pos)
         Error("types are not compatible (number of fields)", Pos)
   
   proc CheckCompoundField(FIELD, Tp, string)
      
      rule CheckCompoundField(FIELD, Tp, Pos)
         FIELD -> field(FieldName, FieldTp)
         {
            Equal(FieldTp,Tp)
         |
            ErrorI("types are not compatible (field '", FieldName, "')", Pos)
         }

