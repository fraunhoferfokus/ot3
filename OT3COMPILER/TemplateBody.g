
   
   proc Trafo_TemplateBody0($TemplateBody -> Tp, EXPR)
      
      rule Trafo_TemplateBody0(TEMPL -> Tp, RES)
         Trafo_TemplateBody(TEMPL, ZInLineTemplate_A1_M1?() -> Tp, RES)
   
   proc Trafo_TemplateBody1($TemplateBody, $InLineTemplate_A1_M1? -> Tp, EXPR)
      
      rule Trafo_TemplateBody1(TEMPL, Prefix -> Tp, RES)
         PrefixToZPrefix(Prefix -> ZPrefix)
         Trafo_TemplateBody(TEMPL, ZPrefix -> Tp, RES)
   
   type ZInLineTemplate_A1_M1
      zauberprefix($InLineTemplate_A1_M1)
   
   proc PrefixToZPrefix($InLineTemplate_A1_M1? -> ZInLineTemplate_A1_M1?)
      
      rule PrefixToZPrefix(Prefix -> ZPrefix)
         {
            Prefix -> $InLineTemplate_A1_M1?()
            ZPrefix <- ZInLineTemplate_A1_M1?()
         |
            Prefix -> $InLineTemplate_A1_M1? (Q)
            ZPrefix <- ZInLineTemplate_A1_M1? (zauberprefix(Q))
         }
   
   proc Trafo_TemplateBody($TemplateBody, ZInLineTemplate_A1_M1? -> Tp, EXPR)
      
      rule Trafo_TemplateBody(TEMPL, Prefix -> Tp, RES)
         switch TEMPL
         {
            case TemplateBody_A1(M1, M2)
            {
               switch M1
               {
                  case TemplateBody_A1_M1_A1(Simple)
                  {
                     Trafo_SimpleSpec(Simple, Prefix -> Tp, Res)
                     RES <- expr_templsimple(Res)
                  }
                  case TemplateBody_A1_M1_A2(List)
                  {
                     Trafo_FieldSpecList(List, Prefix -> TpList, ResList)
                     RES <- expr_templ(ResList)
                     NotCovered("template body of this form (field names)", M1)
                     Tp <- tp_undef()
                  }
                  case TemplateBody_A1_M1_A3(V)
                  {
                     switch V
                     {
                        case ArrayValueOrAttrib_A1(X)
                        {
                           switch X
                           {
                              case ArrayElementSpecList_A1(X1, X2)
                              {
                                 NimmDas(X1 -> HdTp, HdEx)
                                 NimmDasList(X2 -> TlTp, TlEx)
                              }
                           }
                        }
                     }
                     Tp <- tp_nimmdas(Tp[HdTp::TlTp])
                     RES <- expr_nimmdas(EXPR[HdEx::TlEx])
                     CheckPrefix(Prefix, Tp)
                  }
               }
               {
                  M2 -> $TemplateBody_A1_M2?()
               |
                  M2 -> $TemplateBody_A1_M2? (Attr)
                  switch Attr
                  {
                     case TemplateBody_A1_M2_A1(Extra)
                     {
                        switch Extra
                        {
                           case ExtraMatchingAttributes_A1(StrLength)
                           {
                              NotCovered("extra matching attributes 1", Extra)
                           }
                           case ExtraMatchingAttributes_A2()
                           {
                              NotCovered("extra matching attributes 2", Extra)
                           }
                           case ExtraMatchingAttributes_A3(StrLength)
                           {
                              NotCovered("extra matching attributes 3", Extra)
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc CheckPrefix(ZInLineTemplate_A1_M1?, Tp)
      
      rule CheckPrefix(Prefix, Tp)
         {
            Prefix -> ZInLineTemplate_A1_M1? (X1)
            X1 -> zauberprefix(X2)
            X2 -> InLineTemplate_A1_M1_A1(X3)
            X3 -> InLineTemplate_A1_M1_A1_M1_A2(X4)
            X4 -> Signature_A1(S1, Id, S3)
            S1 -> $Signature_A1_M1?()
            S3 -> $Signature_A1_M3?()
            sourcepos Id -> Pos
            GloballyDefined(Id, Pos -> Def)
            {
               Def -> recordtype(_, _, RefNormalizedFieldes)
               valof RefNormalizedFieldes -> Val
               Val -> normalizedfields(Fields)
               Tp -> tp_nimmdas(Tps)
               CheckCompoundFields(Fields, Tps, Pos)
            |
               ErrorI("'", Id, "' not a compound type", Pos)
            }
         |
         }
   
   proc NimmDas($ArrayElementSpec -> Tp, EXPR)
      
      rule NimmDas(ArrayElementSpec -> Tp, Code)
         switch ArrayElementSpec
         {
            case ArrayElementSpec_A1()
            {
               NotCovered("array element spec 1", ArrayElementSpec)
               Tp <- tp_undef()
               Code <- nullexpr()
            }
            case ArrayElementSpec_A2(_)
            {
               NotCovered("array element spec 2", ArrayElementSpec)
               Tp <- tp_undef()
               Code <- nullexpr()
            }
            case ArrayElementSpec_A3(T)
            {
               Trafo_TemplateBody0(T -> Tp, Code)
            }
         }
   
   proc NimmDasList($ArrayElementSpecList_A1_M2[] -> Tp[], EXPR[])
      
      rule NimmDasList($ArrayElementSpecList_A1_M2[HdX::TlX] -> Tp[HdTp::TlTp], EXPR[HdEx::TlEx])
         HdX -> ArrayElementSpecList_A1_M2_A1(Hd)
         NimmDas(Hd -> HdTp, HdEx)
         NimmDasList(TlX -> TlTp, TlEx)
      
      rule NimmDasList($ArrayElementSpecList_A1_M2[] -> Tp[], EXPR[])
   
   proc Trafo_SimpleSpec($SimpleSpec, ZInLineTemplate_A1_M1? -> Tp, FIELDSPEC)
      
      rule Trafo_SimpleSpec(X, Prefix -> Tp, SpecAndMore)
         switch X
         {
            case SimpleSpec_A1(X1)
            {
               switch X1
               {
                  case SimpleSpec_A1_M1_A1(Single2, X1M2)
                  {
                     {
                        X1M2 -> $SimpleSpec_A1_M1_A1_M2?()
                     |
                        X1M2 -> $SimpleSpec_A1_M1_A1_M2? (_)
                        NotCovered("& SimpleTemplateSpec", X1M2)
                     }
                  }
               }
               Trafo_SingleExpression(Single2 -> Tp, Expr)
               SpecAndMore <- spec_unnamedfield(Expr)
            }
            case SimpleSpec_A2(X2)
            {
               switch X2
               {
                  case SimpleTemplateSpec_A1(X2X1)
                  {
                     NotCovered("template spec 2", X2X1)
                     SpecAndMore <- spec_unfertig()
                     Tp <- tp_undef()
                  }
                  case SimpleTemplateSpec_A2(X2X2)
                  {
                     switch X2X2
                     {
                        case SimpleTemplateSpec_A2_M1_A1(X2X2X1a, X2X2X1b)
                        {
                           Trafo_SingleTemplateExpression(X2X2X1a, Prefix -> Tp, E)
                           SpecAndMore <- spec_unnamedfield(E)
                           {
                              X2X2X1b -> $SimpleTemplateSpec_A2_M1_A1_M2?()
                           |
                              X2X2X1b -> $SimpleTemplateSpec_A2_M1_A1_M2? (_)
                              NotCovered("template spec 7", X2X2X1b)
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc Trafo_SingleTemplateExpression($SingleTemplateExpression, ZInLineTemplate_A1_M1? -> Tp, EXPR)
      
      rule Trafo_SingleTemplateExpression(X2X2X1a, Prefix -> FinalErgTp, Ergebnis)
         switch X2X2X1a
         {
            case SingleTemplateExpression_A1(MatchingSymbol)
            {
               switch MatchingSymbol
               {
                  case MatchingSymbol_A1(_)
                  {
                     NotCovered("complement", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     ErgTp <- tp_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A2(M)
                  {
                     switch M
                     {
                        case MatchingSymbol_A2_M1_A1(MM)
                        {
                           {
                              MM -> $MatchingSymbol_A2_M1_A1_M2?()
                              JokerCode(Prefix -> FinalErgTp, Ergebnis)
                           |
                              MM -> $MatchingSymbol_A2_M1_A1_M2? (L)
                              NotCovered("length match", L)
                              Ergebnis <- expr_undef()
                              FinalErgTp <- tp_undef()
                           }
                        }
                     }
                  }
                  case MatchingSymbol_A3(_)
                  {
                     NotCovered("Matching 3", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A4(_)
                  {
                     NotCovered("Matching 4", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A5(_)
                  {
                     NotCovered("Matching 5", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A6(_)
                  {
                     NotCovered("Matching 6", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A7(_)
                  {
                     NotCovered("Matching 7", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A8(_)
                  {
                     NotCovered("Matching 8", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A9(_)
                  {
                     NotCovered("Matching 9", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A10(_)
                  {
                     NotCovered("Matching 10", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
                  case MatchingSymbol_A11(_)
                  {
                     NotCovered("Matching 11", MatchingSymbol)
                     Ergebnis <- expr_undef()
                     FinalErgTp <- tp_undef()
                  }
               }
            }
            case SingleTemplateExpression_A2(K)
            {
               switch K
               {
                  case SingleTemplateExpression_A2_M1_A1(K2a, K2b)
                  {
                     switch K2a
                     {
                        case TemplateRefWithParList_A1(La, Lb)
                        {
                           LaLa(La -> ErgTp, Ausdruck)
                           K2aCode <- Ausdruck
                           {
                              Lb -> $TemplateRefWithParList_A1_M2?()
                           |
                              Lb -> $TemplateRefWithParList_A1_M2? (_)
                              NotCovered("template spec 5", Lb)
                           }
                        }
                     }
                     {
                        K2b -> $SingleTemplateExpression_A2_M1_A1_M2?()
                        Ergebnis <- Ausdruck
                        FinalErgTp <- ErgTp
                     |
                        K2b -> $SingleTemplateExpression_A2_M1_A1_M2? (More)
                        switch More
                        {
                           case SingleTemplateExpression_A2_M1_A1_M2_A1(M)
                           {
                              HandleExtendedFieldReference(M, ErgTp, K2aCode -> ResTp, ResCode)
                           }
                        }
                        Ergebnis <- ResCode
                        FinalErgTp <- ResTp
                     }
                  }
               }
            }
         }
   
   proc LaLa($ExtendedIdentifier -> Tp, EXPR)
      
      rule LaLa(La -> ErgTp, Ausdruck)
         switch La
         {
            case ExtendedIdentifier_A1(J, Name)
            {
               {
                  J -> $ExtendedIdentifier_A1_M1? (JJ)
                  switch JJ
                  {
                     case ExtendedIdentifier_A1_M1_A1(V)
                     {
                        Record <- idexpr(V)
                        Field <- Name
                        sourcepos V -> Pos
                        LocallyDefined(V, Pos -> VDef)
                        {
                           VDef -> localvar(VTp)
                           VTp -> tp_referenced(_)
                           K2aVTp <- VTp
                        |
                           ErrorI("'", V, "' not a local variable", Pos)
                           K2aVTp <- tp_undef()
                        }
                        sourcepos Field -> PosF
                        LookupField(Field, K2aVTp, PosF -> ErgTp)
                        Ausdruck <- fieldselection(Record, Field)
                        K2aCode <- Ausdruck
                     }
                  }
               |
                  J -> $ExtendedIdentifier_A1_M1?()
                  sourcepos Name -> PosName
                  LocallyDefined(Name, PosName -> DefName)
                  {
                     DefName -> localvar(VarType)
                     Ausdruck <- idexpr(Name)
                  |
                     DefName -> componentmember_var(VarType)
                     Ausdruck <- compidexpr(Name)
                  |
                     sourcepos Name -> Pos
                     ErrorI("'", Name, "' unexpected in expression", Pos)
                     Ausdruck <- compidexpr("-")
                     VarType <- tp_integer()
                  }
                  ErgTp <- VarType
               }
            }
         }
   
   proc JokerCode(ZInLineTemplate_A1_M1? -> Tp, EXPR)
      
      rule JokerCode(Prefix -> Tp, Ergebnis)
         {
            Prefix -> ZInLineTemplate_A1_M1?()
            Ergebnis <- fragezeichen()
            Tp <- tp_joker()
         |
            Prefix -> ZInLineTemplate_A1_M1? (Zau)
            Zau -> zauberprefix(Q)
            switch Q
            {
               case InLineTemplate_A1_M1_A1(R)
               {
                  switch R
                  {
                     case InLineTemplate_A1_M1_A1_M1_A1(T)
                     {
                        switch T
                        {
                           case Type_A1(X1)
                           {
                              switch X1
                              {
                                 case PredefinedType_A1()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A2()
                                 {
                                    Ergebnis <- fragezeichen_tp(tp_boolean())
                                    Tp <- tp_boolean()
                                 }
                                 case PredefinedType_A3()
                                 {
                                    Ergebnis <- fragezeichen_tp(tp_charstring())
                                    Tp <- tp_charstring()
                                 }
                                 case PredefinedType_A4(_)
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A5()
                                 {
                                    Ergebnis <- fragezeichen_tp(tp_integer())
                                    Tp <- tp_integer()
                                 }
                                 case PredefinedType_A6()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A7()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A8()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A9()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A10()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A11()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A12()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                                 case PredefinedType_A13()
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                              }
                           }
                           case Type_A2(X2)
                           {
                              switch X2
                              {
                                 case ReferencedType_A1(Y1, Y2, Y3, Y4, Y5)
                                 {
                                    NotCovered("this match", R)
                                    Ergebnis <- expr_undef()
                                    Tp <- tp_undef()
                                 }
                              }
                           }
                        }
                     }
                     case InLineTemplate_A1_M1_A1_M1_A2(Sig)
                     {
                        switch Sig
                        {
                           case Signature_A1(M1, M2, M3)
                           {
                              {
                                 M1 -> $Signature_A1_M1?()
                              |
                                 M1 -> $Signature_A1_M1? (I)
                                 NotCovered("id.id", I)
                              }
                              Name <- M2
                              sourcepos Name -> Pos
                              GloballyDefined(Name, Pos -> Def)
                              {
                                 Def -> recordtype(_, _, _)
                                 Ergebnis <- fragezeichen_record(Name)
                                 Tp <- tp_referenced(Def)
                              |
                                 NotCovered("this kind of prefix", Name)
                                 Ergebnis <- expr_undef()
                                 Tp <- tp_undef()
                              }
                              {
                                 M3 -> $Signature_A1_M3?()
                              |
                                 M3 -> $Signature_A1_M3? (P)
                                 NotCovered("parameters", Name)
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc Trafo_FieldSpecList($FieldSpecList, ZInLineTemplate_A1_M1? -> Tp[], FIELDSPEC[])
      
      rule Trafo_FieldSpecList(List, Prefix -> TpList, ResList)
         switch List
         {
            case FieldSpecList_A1(M2)
            {
               {
                  M2 -> $FieldSpecList_A1_M2?()
                  NotCovered("xxx", M2)
                  ResList <- FIELDSPEC[]
                  TpList <- Tp[]
               |
                  M2 -> $FieldSpecList_A1_M2? (FirstRest)
                  FirstRest -> FieldSpecList_A1_M2_A1(First, Rest)
                  Trafo_FieldSpec(First, Prefix -> Tp, Spec)
                  {
                     Rest -> $FieldSpecList_A1_M2_A1_M2[Hd::Tl]
                     Trafo_FieldSpecList_A1_M2_A1_M2(Hd, Prefix -> Tp2, Spec2)
                     ResList <- FIELDSPEC[Spec2]
                     TpList <- Tp[Tp2]
                  |
                     Rest -> $FieldSpecList_A1_M2_A1_M2[]
                     ResList <- FIELDSPEC[]
                     TpList <- Tp[]
                  }
               }
            }
         }
   
   proc Trafo_FieldSpecList_A1_M2_A1_M2($FieldSpecList_A1_M2_A1_M2, ZInLineTemplate_A1_M1? -> Tp, FIELDSPEC)
      
      rule Trafo_FieldSpecList_A1_M2_A1_M2(X, Prefix -> Tp, Sp)
         switch X
         {
            case FieldSpecList_A1_M2_A1_M2_A1(X1)
            {
               Trafo_FieldSpec(X1, Prefix -> Tp, Sp)
            }
         }
   
   proc Trafo_FieldSpec($FieldSpec, ZInLineTemplate_A1_M1? -> Tp, FIELDSPEC)
      
      rule Trafo_FieldSpec(X, Prefix -> Tp, Sp)
         switch X
         {
            case FieldSpec_A1(Ref, Val)
            {
               switch Ref
               {
                  case FieldReference_A1(StructField)
                  {
                     switch StructField
                     {
                        case StructFieldRef_A1(Name)
                        {
                           Trafo_TemplateBody(Val, Prefix -> Tp, ValRes)
                           Sp <- spec_namedfield(Name, ValRes)
                        }
                        case StructFieldRef_A2(_)
                        {
                           NotCovered("struct field ref 2", StructField)
                           Tp <- tp_undef()
                           Sp <- spec_namedfield("-", nullexpr())
                        }
                        case StructFieldRef_A3(_)
                        {
                           NotCovered("struct field ref 3", StructField)
                           Tp <- tp_undef()
                           Sp <- spec_namedfield("-", nullexpr())
                        }
                     }
                  }
                  case FieldReference_A2(_)
                  {
                     NotCovered("field ref 2", Ref)
                     Tp <- tp_undef()
                     Sp <- spec_namedfield("-", nullexpr())
                  }
               }
            }
         }

