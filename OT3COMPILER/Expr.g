
   
   proc Trafo_Expression($Expression -> Tp, EXPR)
      
      rule Trafo_Expression(X -> Tp, RES)
         switch X
         {
            case Expression_A1(X1)
            {
               Trafo_SingleExpression(X1 -> Tp, RES)
            }
            case Expression_A2(X2)
            {
               Trafo_CompoundExpression(X2 -> Tps, RES)
               Tp <- tp_compound(Tps)
            }
         }
   
   proc HandleExtendedFieldReference($ExtendedFieldReference, Tp, EXPR -> Tp, EXPR)
      
      rule HandleExtendedFieldReference(X, Tp1, Code1 -> Tp2, Code2)
         switch X
         {
            case ExtendedFieldReference_A1(X1)
            {
               HandleExtendedFieldReferences(X1, Tp1, Code1 -> Tp2, Code2)
            }
         }
   
   proc HandleExtendedFieldReferences($ExtendedFieldReference_A1_M1[], Tp, EXPR -> Tp, EXPR)
      
      rule HandleExtendedFieldReferences($ExtendedFieldReference_A1_M1[H::T], Tp1, Code1 -> Tp3, Code3)
         switch H
         {
            case ExtendedFieldReference_A1_M1_A1(X)
            {
               switch X
               {
                  case ExtendedFieldReference_A1_M1_A1_M1_A1(X1)
                  {
                     switch X1
                     {
                        case ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1(X1X1)
                        {
                           switch X1X1
                           {
                              case ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A1(N)
                              {
                                 sourcepos X1X1 -> Pos
                                 LookupField(N, Tp1, Pos -> Tp2)
                                 Code2 <- fieldselection(Code1, N)
                              }
                              case ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A2(Q)
                              {
                                 NotCovered("primitive type in selection", Q)
                                 Tp2 <- tp_none()
                                 Code2 <- Code1
                              }
                              case ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3(P)
                              {
                                 NotCovered("type parameters in selection", P)
                                 Tp2 <- tp_none()
                                 Code2 <- Code1
                              }
                           }
                        }
                     }
                  }
                  case ExtendedFieldReference_A1_M1_A1_M1_A2(ArrayOrBitRef)
                  {
                     switch ArrayOrBitRef
                     {
                        case ArrayOrBitRef_A1(X2X1)
                        {
                           NotCovered("array or bit reference", X2X1)
                           Tp2 <- tp_none()
                           Code2 <- Code1
                        }
                     }
                  }
                  case ExtendedFieldReference_A1_M1_A1_M1_A3(Z)
                  {
                     NotCovered("'[ - ]'", Z)
                     Tp2 <- tp_none()
                     Code2 <- Code1
                  }
               }
            }
         }
         HandleExtendedFieldReferences(T, Tp2, Code2 -> Tp3, Code3)
      
      rule HandleExtendedFieldReferences($ExtendedFieldReference_A1_M1[], Tp, Code -> Tp, Code)
   
   proc LookupField(string, Tp, string -> Tp)
      
      rule LookupField(N, Tp1, Pos -> Tp2)
         {
            Tp1 -> tp_referenced(RecordType)
            RecordType -> recordtype(N2, X4, RefNormalizedFields)
            valof RefNormalizedFields -> QQ
            QQ -> normalizedfields(Flds)
            {
               LookupFields_R(N, Flds -> Tp2)
            |
               ErrorI("no field '", N, "'", Pos)
               Tp2 <- tp_none()
            }
         |
            Error("field selection for non-record type", Pos)
            Tp2 <- tp_none()
         }
   
   condition LookupFields_R(string, FIELD[] -> Tp)
      
      rule LookupFields_R(N, FIELD[H::T] -> Tp)
         {
            H -> field(N2, Tp)
            Equal(N,N2)
         |
            LookupFields_R(N, T -> Tp)
         }

