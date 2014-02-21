
   
   proc Trafo_Primary($Primary -> Tp, EXPR)
      
      rule Trafo_Primary(PRIMARY -> Tp, RES)
         switch PRIMARY
         {
            case Primary_A1(CALL)
            {
               switch CALL
               {
                  case OpCall_A1(X)
                  {
                     switch X
                     {
                        case ConfigurationOps_A1(CreateOp)
                        {
                           switch CreateOp
                           {
                              case CreateOp_A1(XX1, XX2, XX3)
                              {
                                 switch XX1
                                 {
                                    case ComponentType_A1(XXX1, Id, XXX3, XXX4)
                                    {
                                       {
                                          XXX1 -> $ComponentType_A1_M1?()
                                       |
                                          XXX1 -> $ComponentType_A1_M1? (_)
                                          NotCovered("xxx1", XXX1)
                                       }
                                       {
                                          XXX3 -> $ComponentType_A1_M3?()
                                       |
                                          XXX3 -> $ComponentType_A1_M3? (_)
                                          NotCovered("xxx3", XXX3)
                                       }
                                       {
                                          XXX4 -> $ComponentType_A1_M4?()
                                       |
                                          XXX4 -> $ComponentType_A1_M4? (_)
                                          NotCovered("xxx4", XXX4)
                                       }
                                    }
                                 }
                                 {
                                    XX2 -> $CreateOp_A1_M4?()
                                 |
                                    XX2 -> $CreateOp_A1_M4? (_)
                                    NotCovered("xx2", XX2)
                                 }
                                 {
                                    XX3 -> $CreateOp_A1_M5?()
                                 |
                                    XX3 -> $CreateOp_A1_M5? (_)
                                    NotCovered("xx3", XX3)
                                 }
                                 sourcepos Id -> Pos
                                 GloballyDefined(Id, Pos -> Def)
                                 {
                                    Def -> componenttype(_, _)
                                 |
                                    Error("component type expected", Pos)
                                 }
                                 Tp <- tp_referenced(Def)
                                 RES <- createcomponent(Id)
                              }
                           }
                        }
                        case ConfigurationOps_A2()
                        {
                           NotCovered("configuration op 2", X)
                           Tp <- tp_none()
                           RES <- nullexpr()
                        }
                        case ConfigurationOps_A3()
                        {
                           NotCovered("configuration op 3", X)
                           Tp <- tp_none()
                           RES <- nullexpr()
                        }
                        case ConfigurationOps_A4()
                        {
                           NotCovered("configuration op 4", X)
                           Tp <- tp_none()
                           RES <- nullexpr()
                        }
                        case ConfigurationOps_A5(_)
                        {
                           NotCovered("configuration op 5", X)
                           Tp <- tp_none()
                           RES <- nullexpr()
                        }
                        case ConfigurationOps_A6(_)
                        {
                           NotCovered("configuration op 6", X)
                           Tp <- tp_none()
                           RES <- nullexpr()
                        }
                     }
                  }
                  case OpCall_A2()
                  {
                     NotCovered("OpCall_A2", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A3(_)
                  {
                     NotCovered("OpCall_A3", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A4(_)
                  {
                     NotCovered("OpCall_A4", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A5(_, _)
                  {
                     NotCovered("OpCall_A5", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A6(X61, X62)
                  {
                     switch X61
                     {
                        case TemplateOps_A1(X611)
                        {
                           switch X611
                           {
                              case MatchOp_A1(X6111, X6112)
                              {
                                 Trafo_Expression(X6111 -> Tp1, Value)
                                 primaTrafo_InLineTemplate(X6112 -> Tp2, Pattern)
                                 {
                                    Equal(Tp1,Tp2)
                                 |
                                    Tp2 -> tp_nimmdas(Tps)
                                    Tp1 -> tp_referenced(Def)
                                    Def -> recordtype(_, _, Ref)
                                    valof Ref -> Val
                                    Val -> normalizedfields(Fields)
                                    sourcepos X611 -> Pos
                                    CheckCompoundFields(Fields, Tps, Pos)
                                 |
                                    sourcepos X611 -> Pos
                                    Error("Types are not compatible", Pos)
                                 }
                                 RES <- expr_match(Value, Pattern)
                                 Tp <- tp_boolean()
                              }
                           }
                        }
                        case TemplateOps_A2(_)
                        {
                           NotCovered("this variant of 'match'", CALL)
                           RES <- nullexpr()
                           Tp <- tp_primary()
                        }
                     }
                  }
                  case OpCall_A7(_)
                  {
                     NotCovered("OpCall_A7", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A8(_)
                  {
                     NotCovered("OpCall_A8", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A9()
                  {
                     NotCovered("OpCall_A9", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A10(_)
                  {
                     NotCovered("OpCall_A10", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A11(_)
                  {
                     NotCovered("OpCall_A11", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
                  case OpCall_A12(_)
                  {
                     NotCovered("OpCall_A12", CALL)
                     RES <- nullexpr()
                     Tp <- tp_primary()
                  }
               }
            }
            case Primary_A2(VALUE)
            {
               prima_Trafo_Value(VALUE -> Tp, RES)
            }
            case Primary_A3(SINGLE)
            {
               Trafo_SingleExpression(SINGLE -> Tp, RES)
            }
         }
   
   proc primaTrafo_InLineTemplate($InLineTemplate -> Tp, EXPR)
      
      rule primaTrafo_InLineTemplate(Templ -> Tp, RES)
         switch Templ
         {
            case InLineTemplate_A1(Prefix, Modifies, TemplateBody)
            {
               {
                  Modifies -> $InLineTemplate_A1_M2?()
               |
                  Modifies -> $InLineTemplate_A1_M2? (_)
                  NotCovered("modifies", Modifies)
               }
               Trafo_TemplateBody1(TemplateBody, Prefix -> Tp, RES)
            }
         }
   
   proc prima_Trafo_Value($Value -> Tp, EXPR)
      
      rule prima_Trafo_Value(VALUE -> Tp, RES)
         switch VALUE
         {
            case Value_A1(PREDEF)
            {
               prima_Trafo_PredefinedValue(PREDEF -> Tp, RES)
            }
            case Value_A2(REFERENCE)
            {
               prima_Trafo_ReferencedValue(REFERENCE -> Tp, RES)
            }
         }
   
   proc prima_Trafo_ReferencedValue($ReferencedValue -> Tp, EXPR)
      
      rule prima_Trafo_ReferencedValue(REFERENCE -> FinalTp, FinalRES)
         switch REFERENCE
         {
            case ReferencedValue_A1(X1, SUB)
            {
               LaLa(X1 -> Tp, RES)
               LaSub(SUB, Tp, RES -> FinalTp, FinalRES)
            }
         }
   
   proc LaSub($ReferencedValue_A1_M2?, Tp, EXPR -> Tp, EXPR)
      
      rule LaSub(SUB, Tp0, Code0 -> FinalTp, FinalRES)
         {
            SUB -> $ReferencedValue_A1_M2?()
            FinalTp <- Tp0
            FinalRES <- Code0
         |
            SUB -> $ReferencedValue_A1_M2? (X)
            switch X
            {
               case ReferencedValue_A1_M2_A1(XX)
               {
                  HandleExtendedFieldReference(XX, Tp0, Code0 -> FinalTp, FinalRES)
               }
            }
         }
   
   proc prima_Trafo_PredefinedValue($PredefinedValue -> Tp, EXPR)
      
      rule prima_Trafo_PredefinedValue(PREDEF -> Tp, RES)
         switch PREDEF
         {
            case PredefinedValue_A1(BSTRING)
            {
               BSTRING -> Bstring_A1(str)
               RES <- expr_bstring(str)
               Tp <- tp_none()
            }
            case PredefinedValue_A2(BV)
            {
               switch BV
               {
                  case BooleanValue_A1()
                  {
                     RES <- expr_true()
                     Tp <- tp_boolean()
                  }
                  case BooleanValue_A2()
                  {
                     RES <- expr_false()
                     Tp <- tp_boolean()
                  }
               }
            }
            case PredefinedValue_A3(CSTRING)
            {
               switch CSTRING
               {
                  case CharStringValue_A1(str00)
                  {
                     str00 -> Cstring_A1(str)
                     RES <- expr_cstring(str)
                     Tp <- tp_charstring()
                  }
                  case CharStringValue_A2(str0)
                  {
                     str0 -> Quadruple_A1(n1, n2, n3, n4)
                     n1 -> Number_A1(m1)
                     n2 -> Number_A1(m2)
                     n3 -> Number_A1(m3)
                     n4 -> Number_A1(m4)
                     RES <- expr_quadrupel(m1, m2, m3, m4)
                     Tp <- tp_charstring()
                  }
               }
            }
            case PredefinedValue_A4(N)
            {
               N -> Number_A1(NUMBER)
               RES <- intexpr(NUMBER)
               Tp <- tp_integer()
            }
            case PredefinedValue_A5(OSTRING)
            {
               OSTRING -> Ostring_A1(str)
               RES <- expr_ostring(str)
               Tp <- tp_none()
            }
            case PredefinedValue_A6(HSTRING)
            {
               HSTRING -> Hstring_A1(str)
               RES <- expr_hstring(str)
               Tp <- tp_none()
            }
            case PredefinedValue_A7(VERDICT)
            {
               {
                  VERDICT -> VerdictTypeValue_A1()
                  RES <- expr_verdict("pass")
               |
                  VERDICT -> VerdictTypeValue_A2()
                  RES <- expr_verdict("fail")
               |
                  VERDICT -> VerdictTypeValue_A3()
                  RES <- expr_verdict("inconc")
               |
                  VERDICT -> VerdictTypeValue_A4()
                  RES <- expr_verdict("none")
               |
                  VERDICT -> VerdictTypeValue_A5()
                  RES <- expr_verdict("error")
               }
               Tp <- tp_verdict()
            }
            case PredefinedValue_A8(F)
            {
               F -> FloatValue_A1(str)
               RES <- expr_float(str)
               Tp <- tp_float()
            }
            case PredefinedValue_A9()
            {
               RES <- expr_omit()
               Tp <- tp_none()
            }
            case PredefinedValue_A10(BEHAVIOUR)
            {
               {
                  BEHAVIOUR -> BehaviourValue_A1(I1, I2)
                  NotCovered("Behaviour", BEHAVIOUR)
               |
                  BEHAVIOUR -> BehaviourValue_A2()
                  NotCovered("Behaviour", BEHAVIOUR)
               }
               RES <- expr_omit()
               Tp <- tp_none()
            }
         }

