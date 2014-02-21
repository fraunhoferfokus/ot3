
   
   proc Trafo_CompoundExpression($CompoundExpression -> Tp[], EXPR)
      
      rule Trafo_CompoundExpression(X -> Tps, RES)
         switch X
         {
            case CompoundExpression_A1(X1)
            {
               switch X1
               {
                  case FieldExpressionList_A1(First, Others)
                  {
                     coxTrafo_FieldExpressionSpec(First -> TpHd, Hd)
                     coxTrafo_FieldExpressionSpec_LIST(Others -> TpTl, Tl)
                     RES <- expr_compound(FIELDSPEC[Hd::Tl])
                     Tps <- Tp[TpHd::TpTl]
                  }
               }
            }
            case CompoundExpression_A2(Arr)
            {
               coxTrafo_ArrayExpression(Arr -> Tps, RES)
            }
         }
   
   proc coxTrafo_ArrayExpression($ArrayExpression -> Tp[], EXPR)
      
      rule coxTrafo_ArrayExpression(Arr -> Tps, RES)
         switch Arr
         {
            case ArrayExpression_A1(A)
            {
               {
                  A -> $ArrayExpression_A1_M2? (A2)
                  switch A2
                  {
                     case ArrayExpression_A1_M2_A1(A3)
                     {
                        switch A3
                        {
                           case ArrayElementExpressionList_A1(First, Rest)
                           {
                              coxTrafo_NotUsedOrExpression(First -> FirstTp, FirstRes)
                              coxTrafo_ArrayElementExpressionList_A1_M2_SEQ(Rest -> RestTp, RestRes)
                              RES <- expr_array(EXPR[FirstRes::RestRes])
                              Tps <- Tp[FirstTp::RestTp]
                           }
                        }
                     }
                  }
               |
                  A -> $ArrayExpression_A1_M2?()
                  RES <- expr_array(EXPR[])
                  Tps <- Tp[]
               }
            }
         }
   
   proc coxTrafo_ArrayElementExpressionList_A1_M2_SEQ($ArrayElementExpressionList_A1_M2[] -> Tp[], EXPR[])
      
      rule coxTrafo_ArrayElementExpressionList_A1_M2_SEQ($ArrayElementExpressionList_A1_M2[H::T] -> Tp[TpH::TpT], EXPR[ResH::ResT])
         coxTrafo_ArrayElementExpressionList_A1_M2(H -> TpH, ResH)
         coxTrafo_ArrayElementExpressionList_A1_M2_SEQ(T -> TpT, ResT)
      
      rule coxTrafo_ArrayElementExpressionList_A1_M2_SEQ($ArrayElementExpressionList_A1_M2[] -> Tp[], EXPR[])
   
   proc coxTrafo_ArrayElementExpressionList_A1_M2($ArrayElementExpressionList_A1_M2 -> Tp, EXPR)
      
      rule coxTrafo_ArrayElementExpressionList_A1_M2(H -> TpH, ResH)
         switch H
         {
            case ArrayElementExpressionList_A1_M2_A1(X)
            {
               coxTrafo_NotUsedOrExpression(X -> TpH, ResH)
            }
         }
   
   proc coxTrafo_NotUsedOrExpression($NotUsedOrExpression -> Tp, EXPR)
      
      rule coxTrafo_NotUsedOrExpression(NotUsedOrExpression -> Tp, Res)
         switch NotUsedOrExpression
         {
            case NotUsedOrExpression_A1(Expr)
            {
               Trafo_Expression(Expr -> Tp, Res)
            }
            case NotUsedOrExpression_A2()
            {
               NotCovered("NotUsed value", NotUsedOrExpression)
               Tp <- tp_skip()
               Res <- expr_skip()
            }
         }
   
   proc coxTrafo_FieldExpressionSpec_ELEM($FieldExpressionList_A1_M3 -> Tp, FIELDSPEC)
      
      rule coxTrafo_FieldExpressionSpec_ELEM(X -> Tp, FIELDSPEC)
         switch X
         {
            case FieldExpressionList_A1_M3_A1(X1)
            {
               coxTrafo_FieldExpressionSpec(X1 -> Tp, FIELDSPEC)
            }
         }
   
   proc coxTrafo_FieldExpressionSpec_LIST($FieldExpressionList_A1_M3[] -> Tp[], FIELDSPEC[])
      
      rule coxTrafo_FieldExpressionSpec_LIST($FieldExpressionList_A1_M3[H::T] -> Tp[TpH::TpT], FIELDSPEC[RH::RT])
         coxTrafo_FieldExpressionSpec_ELEM(H -> TpH, RH)
         coxTrafo_FieldExpressionSpec_LIST(T -> TpT, RT)
      
      rule coxTrafo_FieldExpressionSpec_LIST($FieldExpressionList_A1_M3[] -> Tp[], FIELDSPEC[])
   
   proc coxTrafo_FieldExpressionSpec($FieldExpressionSpec -> Tp, FIELDSPEC)
      
      rule coxTrafo_FieldExpressionSpec(X -> Tp, Res)
         switch X
         {
            case FieldExpressionSpec_A1(Ref, NotUsedOrExpr)
            {
               switch Ref
               {
                  case FieldReference_A1(StructField)
                  {
                     StructField -> StructFieldRef_A1(Name)
                     switch NotUsedOrExpr
                     {
                        case NotUsedOrExpression_A1(Expr)
                        {
                           Trafo_Expression(Expr -> Tp, Val)
                        }
                        case NotUsedOrExpression_A2()
                        {
                           NotCovered("NotUsed value", NotUsedOrExpr)
                           Val <- expr_skip()
                           Tp <- tp_skip()
                        }
                     }
                     Res <- spec_namedfield(Name, Val)
                  }
                  case FieldReference_A2(_)
                  {
                     NotCovered("field reference 2", Ref)
                     Tp <- tp_skip()
                     Res <- spec_namedfield("-", expr_skip())
                  }
               }
            }
         }

