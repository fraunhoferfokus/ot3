
   
   proc Trafo_SingleExpression($SingleExpression -> Tp, EXPR)
      
      rule Trafo_SingleExpression(X -> Tp, RES)
         switch X
         {
            case SingleExpression_A1(XOR, Rest)
            {
               Trafo_XorExpression(XOR -> Tp0, RES0)
               Trafo_OrRest(Rest, Tp0, RES0 -> Tp, RES)
            }
         }
   
   proc Trafo_OrRest($SingleExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_OrRest($SingleExpression_A1_M2[], Tp0, EXPR0 -> Tp0, EXPR0)
      
      rule Trafo_OrRest($SingleExpression_A1_M2[Hd::Tl], Tp0, EXPR0 -> Tp3, RES3)
         switch Hd
         {
            case SingleExpression_A1_M2_A1(X)
            {
            }
         }
         Trafo_XorExpression(X -> Tp1, RES1)
         RES2 <- binexpr("or", EXPR0, RES1)
         CheckExpr("and", Hd, Tp0, Tp1 -> Tp2)
         Trafo_OrRest(Tl, Tp2, RES2 -> Tp3, RES3)
   
   proc Trafo_XorExpression($XorExpression -> Tp, EXPR)
      
      rule Trafo_XorExpression(XOR -> Tp, RES)
         switch XOR
         {
            case XorExpression_A1(AND, TAIL)
            {
               Trafo_AndExpression(AND -> Tp1, RES2)
               Trafo_XorSequence(TAIL, Tp1, RES2 -> Tp, RES)
            }
         }
   
   proc Trafo_XorSequence($XorExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_XorSequence(SEQ, Tp1, LEFT -> Tp1, LEFT)
         SEQ -> $XorExpression_A1_M2[]
      
      rule Trafo_XorSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $XorExpression_A1_M2[HEAD::TAIL]
         HEAD -> XorExpression_A1_M2_A1(AND)
         Trafo_AndExpression(AND -> Tp1, ARG2)
         RES2 <- binexpr("xor", LEFT, ARG2)
         CheckExpr("and", HEAD, Tp0, Tp1 -> Tp2)
         Trafo_XorSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_AndExpression($AndExpression -> Tp, EXPR)
      
      rule Trafo_AndExpression(AND -> Tp2, RES)
         AND -> AndExpression_A1(NOT, SEQ)
         Trafo_NotExpression(NOT -> Tp1, RES2)
         Trafo_AndSequence(SEQ, Tp1, RES2 -> Tp2, RES)
   
   proc Trafo_AndSequence($AndExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_AndSequence(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $AndExpression_A1_M2[]
      
      rule Trafo_AndSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $AndExpression_A1_M2[HEAD::TAIL]
         HEAD -> AndExpression_A1_M2_A1(NOT)
         Trafo_NotExpression(NOT -> Tp1, ARG2)
         RES2 <- binexpr("and", LEFT, ARG2)
         CheckExpr("and", HEAD, Tp0, Tp1 -> Tp2)
         Trafo_AndSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_NotExpression($NotExpression -> Tp, EXPR)
      
      rule Trafo_NotExpression(NOT -> Tp2, RES)
         NOT -> NotExpression_A1(OP, EQUAL)
         Trafo_EqualExpression(EQUAL -> Tp1, RES2)
         {
            OP -> $NotExpression_A1_M1?()
            RES <- RES2
            Tp2 <- Tp1
         |
            OP -> $NotExpression_A1_M1? (NotExpression_A1_M1_A1())
            RES <- monexpr("not", RES2)
            CheckExpr1("not", OP, Tp1 -> Tp2)
         }
   
   proc Trafo_EqualExpression($EqualExpression -> Tp, EXPR)
      
      rule Trafo_EqualExpression(EQUAL -> Tp2, RES)
         EQUAL -> EqualExpression_A1(REL, TAIL)
         Trafo_RelExpression(REL -> Tp1, RES2)
         Trafo_EqualSequence(TAIL, Tp1, RES2 -> Tp2, RES)
   
   proc Trafo_EqualSequence($EqualExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_EqualSequence(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $EqualExpression_A1_M2[]
      
      rule Trafo_EqualSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $EqualExpression_A1_M2[HEAD::TAIL]
         HEAD -> EqualExpression_A1_M2_A1(OP, REL)
         Trafo_RelExpression(REL -> Tp1, ARG2)
         {
            OP -> EqualOp_A1()
            RES2 <- binexpr("equal", LEFT, ARG2)
         |
            OP -> EqualOp_A2()
            RES2 <- binexpr("notequal", LEFT, ARG2)
         }
         CheckExpr("equal", OP, Tp0, Tp1 -> Tp2)
         Trafo_EqualSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_RelExpression($RelExpression -> Tp, EXPR)
      
      rule Trafo_RelExpression(REL -> Tp2, RES)
         {
            REL -> RelExpression_A1(SHIFT, TAIL)
            Trafo_ShiftExpression(SHIFT -> Tp1, RES2)
            Trafo_RelSequence(TAIL, Tp1, RES2 -> Tp2, RES)
         |
            REL -> RelExpression_A2(Compound)
            Trafo_CompoundExpression(Compound -> Tps, RES)
            Tp2 <- tp_compound(Tps)
         }
   
   proc Trafo_RelSequence($RelExpression_A1_M2?, Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_RelSequence(HEAD, Tp, LEFT -> Tp, LEFT)
         HEAD -> $RelExpression_A1_M2?()
      
      rule Trafo_RelSequence(HEAD, Tp0, LEFT -> Tp2, RES)
         HEAD -> $RelExpression_A1_M2? (RelExpression_A1_M2_A1(OP, REL))
         Trafo_ShiftExpression(REL -> Tp1, ARG2)
         {
            OP -> RelOp_A1()
            RES <- binexpr("less", LEFT, ARG2)
         |
            OP -> RelOp_A2()
            RES <- binexpr("greater", LEFT, ARG2)
         |
            OP -> RelOp_A3()
            RES <- binexpr("greaterequal", LEFT, ARG2)
         |
            OP -> RelOp_A4()
            RES <- binexpr("lessequal", LEFT, ARG2)
         }
         CheckExpr("less", OP, Tp0, Tp1 -> Tp2)
   
   proc Trafo_ShiftExpression($ShiftExpression -> Tp, EXPR)
      
      rule Trafo_ShiftExpression(SHIFT -> Tp2, RES)
         SHIFT -> ShiftExpression_A1(BITOR, TAIL)
         Trafo_BitOrExpression(BITOR -> Tp1, RES2)
         Trafo_ShiftSequence(TAIL, Tp1, RES2 -> Tp2, RES)
   
   proc Trafo_ShiftSequence($ShiftExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_ShiftSequence(X, Tp, LEFT -> Tp, LEFT)
         X -> $ShiftExpression_A1_M2[]
      
      rule Trafo_ShiftSequence(X, Tp0, LEFT -> Tp3, RES)
         X -> $ShiftExpression_A1_M2[HEAD::TAIL]
         HEAD -> ShiftExpression_A1_M2_A1(OP, SHIFT)
         Trafo_BitOrExpression(SHIFT -> Tp1, ARG2)
         {
            OP -> ShiftOp_A1()
            RES2 <- binexpr("shiftleft", LEFT, ARG2)
            NotCovered("shift left operator", OP)
         |
            OP -> ShiftOp_A2()
            NotCovered("shift right operator", OP)
            RES2 <- binexpr("shiftright", LEFT, ARG2)
         |
            OP -> ShiftOp_A3()
            NotCovered("rotate left operator", OP)
            RES2 <- binexpr("rotateleft", LEFT, ARG2)
         |
            OP -> ShiftOp_A4()
            NotCovered("rotate right operator", OP)
            RES2 <- binexpr("rotateright", LEFT, ARG2)
         }
         CheckExpr("rotate", OP, Tp0, Tp1 -> Tp2)
         Trafo_ShiftSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_BitOrExpression($BitOrExpression -> Tp, EXPR)
      
      rule Trafo_BitOrExpression(BITOR -> Tp2, RES)
         BITOR -> BitOrExpression_A1(BITXOR, TAIL)
         Trafo_BitXorExpression(BITXOR -> Tp1, RES2)
         Trafo_BitOrSequence(TAIL, Tp1, RES2 -> Tp2, RES)
   
   proc Trafo_BitOrSequence($BitOrExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_BitOrSequence(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $BitOrExpression_A1_M2[]
      
      rule Trafo_BitOrSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $BitOrExpression_A1_M2[HEAD::TAIL]
         HEAD -> BitOrExpression_A1_M2_A1(XOR)
         Trafo_BitXorExpression(XOR -> Tp1, ARG2)
         RES2 <- binexpr("bitor", LEFT, ARG2)
         CheckExpr("bitor", HEAD, Tp0, Tp1 -> Tp2)
         NotCovered("or4b operator", HEAD)
         Trafo_BitOrSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_BitXorExpression($BitXorExpression -> Tp, EXPR)
      
      rule Trafo_BitXorExpression(BITXOR -> Tp2, RES)
         BITXOR -> BitXorExpression_A1(BITAND, TAIL)
         Trafo_BitAndExpression(BITAND -> Tp1, RES2)
         Trafo_BitXorSequence(TAIL, Tp1, RES2 -> Tp2, RES)
   
   proc Trafo_BitXorSequence($BitXorExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_BitXorSequence(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $BitXorExpression_A1_M2[]
      
      rule Trafo_BitXorSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $BitXorExpression_A1_M2[HEAD::TAIL]
         HEAD -> BitXorExpression_A1_M2_A1(AND)
         Trafo_BitAndExpression(AND -> Tp1, ARG2)
         RES2 <- binexpr("bitxor", LEFT, ARG2)
         CheckExpr("bitxor", HEAD, Tp0, Tp1 -> Tp2)
         NotCovered("xor4b operator", HEAD)
         Trafo_BitXorSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_BitAndExpression($BitAndExpression -> Tp, EXPR)
      
      rule Trafo_BitAndExpression(BITAND -> Tp3, RES)
         BITAND -> BitAndExpression_A1(BITNOT, TAIL)
         Trafo_BitNotExpression(BITNOT -> Tp2, RES2)
         Trafo_BitAndSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_BitAndSequence($BitAndExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_BitAndSequence(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $BitAndExpression_A1_M2[]
      
      rule Trafo_BitAndSequence(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $BitAndExpression_A1_M2[HEAD::TAIL]
         HEAD -> BitAndExpression_A1_M2_A1(NOT)
         Trafo_BitNotExpression(NOT -> Tp1, ARG2)
         RES2 <- binexpr("bitand", LEFT, ARG2)
         NotCovered("and4b operator", HEAD)
         CheckExpr("bitand", HEAD, Tp0, Tp1 -> Tp2)
         Trafo_BitAndSequence(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_BitNotExpression($BitNotExpression -> Tp, EXPR)
      
      rule Trafo_BitNotExpression(BITNOT -> Tp2, RES)
         BITNOT -> BitNotExpression_A1(OP, ADD)
         Trafo_ADD(ADD -> Tp1, RES2)
         {
            OP -> $BitNotExpression_A1_M1?()
            RES <- RES2
            Tp2 <- Tp1
         |
            OP -> $BitNotExpression_A1_M1? (BitNotExpression_A1_M1_A1())
            RES <- monexpr("bitnot", RES2)
            NotCovered("bit4b operator", OP)
            CheckExpr1("bitnot", OP, Tp1 -> Tp2)
         }
   
   proc Trafo_ADD($AddExpression -> Tp, EXPR)
      
      rule Trafo_ADD(ADD -> Tp2, RES)
         ADD -> AddExpression_A1(MUL, SEQ)
         Trafo_MUL(MUL -> Tp1, RES1)
         Trafo_ADDSEQ(SEQ, Tp1, RES1 -> Tp2, RES)
   
   proc Trafo_ADDSEQ($AddExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_ADDSEQ(SEQ, Tp1, LEFT -> Tp1, LEFT)
         SEQ -> $AddExpression_A1_M2[]
      
      rule Trafo_ADDSEQ(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $AddExpression_A1_M2[HEAD::TAIL]
         HEAD -> AddExpression_A1_M2_A1(OP, MUL)
         Trafo_MUL(MUL -> Tp1, ARG1)
         sourcepos OP -> Pos
         switch OP
         {
            case AddOp_A1()
            {
               RES2 <- binexpr("add", LEFT, ARG1)
               CheckExpr("add", OP, Tp0, Tp1 -> Tp2)
            }
            case AddOp_A2()
            {
               RES2 <- binexpr("sub", LEFT, ARG1)
               CheckExpr("add", OP, Tp0, Tp1 -> Tp2)
            }
            case AddOp_A3(_)
            {
               RES2 <- binexpr("concat", LEFT, ARG1)
               CheckExpr("concat", OP, Tp0, Tp1 -> Tp2)
            }
         }
         Trafo_ADDSEQ(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc Trafo_UnaryExpression($UnaryExpression -> Tp, EXPR)
      
      rule Trafo_UnaryExpression(UNARY -> TpUnary, ResUnary)
         switch UNARY
         {
            case UnaryExpression_A1(X1, PRIMARY)
            {
               {
                  X1 -> $UnaryExpression_A1_M1?()
                  Trafo_Primary(PRIMARY -> TpUnary, ResUnary)
               |
                  X1 -> $UnaryExpression_A1_M1? (XX1)
                  switch XX1
                  {
                     case UnaryExpression_A1_M1_A1(XXX1)
                     {
                        switch XXX1
                        {
                           case UnaryOp_A1()
                           {
                              Trafo_Primary(PRIMARY -> TpUnary, ResUnary)
                           }
                           case UnaryOp_A2()
                           {
                              Trafo_Primary(PRIMARY -> TpUnary0, ResUnary0)
                              CheckExpr1("minus", XX1, TpUnary0 -> TpUnary)
                              ResUnary <- monexpr("minus", ResUnary0)
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc Trafo_MUL($MulExpression -> Tp, EXPR)
      
      rule Trafo_MUL(MUL -> Tp, RES)
         switch MUL
         {
            case MulExpression_A1(UNARY, TAIL)
            {
               Trafo_UnaryExpression(UNARY -> TpUnary, ResUnary)
               Trafo_MulSEQ(TAIL, TpUnary, ResUnary -> Tp, RES)
            }
            case MulExpression_A2(_)
            {
               NotCovered("///", MUL)
               Tp <- tp_undef()
               RES <- expr_undef()
            }
         }
   
   proc Trafo_MulSEQ($MulExpression_A1_M2[], Tp, EXPR -> Tp, EXPR)
      
      rule Trafo_MulSEQ(SEQ, Tp, LEFT -> Tp, LEFT)
         SEQ -> $MulExpression_A1_M2[]
      
      rule Trafo_MulSEQ(SEQ, Tp0, LEFT -> Tp3, RES)
         SEQ -> $MulExpression_A1_M2[HEAD::TAIL]
         switch HEAD
         {
            case MulExpression_A1_M2_A1(OP, UNARY)
            {
               Trafo_UnaryExpression(UNARY -> TpUnary, ResUnary)
            }
         }
         switch OP
         {
            case MultiplyOp_A1()
            {
               RES2 <- binexpr("mul", LEFT, ResUnary)
            }
            case MultiplyOp_A2()
            {
               RES2 <- binexpr("div", LEFT, ResUnary)
            }
            case MultiplyOp_A3()
            {
               RES2 <- binexpr("mod", LEFT, ResUnary)
            }
            case MultiplyOp_A4()
            {
               RES2 <- binexpr("rem", LEFT, ResUnary)
            }
         }
         CheckExpr("mul", OP, Tp0, TpUnary -> Tp2)
         Trafo_MulSEQ(TAIL, Tp2, RES2 -> Tp3, RES)
   
   proc CheckExpr1(string, any, Tp -> Tp)
      
      rule CheckExpr1("not", X, tp_boolean() -> tp_boolean())
      
      rule CheckExpr1("bitnot", X, tp_boolean() -> tp_boolean())
      
      rule CheckExpr1("minus", X, tp_integer() -> tp_integer())
      
      rule CheckExpr1(Operator, X, Tp -> tp_none())
         sourcepos X -> Pos
         Error("invalid type of argument", Pos)
   
   proc CheckExpr(string, any, Tp, Tp -> Tp)
      
      rule CheckExpr("and", X, tp_boolean(), tp_boolean() -> tp_boolean())
      
      rule CheckExpr("equal", X, tp_integer(), tp_integer() -> tp_boolean())
      
      rule CheckExpr("less", X, tp_integer(), tp_integer() -> tp_boolean())
      
      rule CheckExpr("rotate", X, Tp0, Tp1 -> tp_none())
      
      rule CheckExpr("bitor", X, Tp0, Tp1 -> tp_none())
      
      rule CheckExpr("bitxor", X, Tp0, Tp1 -> tp_none())
      
      rule CheckExpr("bitand", X, Tp0, Tp1 -> tp_none())
      
      rule CheckExpr("add", X, tp_integer(), tp_integer() -> tp_integer())
      
      rule CheckExpr("concat", X, tp_charstring(), tp_charstring() -> tp_charstring())
      
      rule CheckExpr("mul", X, tp_integer(), tp_integer() -> tp_integer())
      
      rule CheckExpr(Operator, X, Tp1, Tp2 -> tp_none())
         sourcepos X -> Pos
         Error("invalid type of argument", Pos)

