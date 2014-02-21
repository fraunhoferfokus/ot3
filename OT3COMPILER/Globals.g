
   
   type NormalizedFields
      normalizedfields(FIELD[])
   
   type FIELD
      field(string, Tp)
   
   type NormalizedPortType
      normalizedport(Dir, Tp)
      port_notyet()
   
   type Dir
      dir_in()
      dir_out()
      dir_inout()
   
   data ScopeStack( -> SYMTABENTRY[][])
   
   data GlobalScope( -> SYMTABENTRY[])
   
   proc InitScopeHandling()
      
      rule InitScopeHandling()
         Set-ScopeStack(SYMTABENTRY[][])
   
   proc GloballyDefined(string, string -> DEFINITION)
      
      rule GloballyDefined(Name, Pos -> Def)
         xDefined(Name, Pos -> Def)
   
   proc LocallyDefined(string, string -> DEFINITION)
      
      rule LocallyDefined(Name, Pos -> Def)
         xDefined(Name, Pos -> Def)
   
   proc xDefined(string, string -> DEFINITION)
      
      rule xDefined(Id, Pos -> M)
         Get-ScopeStack(-> S)
         IsInSymtabEntryListList(S, Id -> M)
      
      rule xDefined(Id, Pos -> nodefinition())
         ErrorI("'", Id, "' has not been declared", Pos)
   
   condition IsInSymtabEntryListList(SYMTABENTRY[][], string -> DEFINITION)
      
      rule IsInSymtabEntryListList(SYMTABENTRY[][Hd::Tl], Id -> M)
         {
            IsInSymtabEntryList(Hd, Id -> M)
         |
            IsInSymtabEntryListList(Tl, Id -> M)
         }
   
   condition IsInSymtabEntryList(SYMTABENTRY[], string -> DEFINITION)
      
      rule IsInSymtabEntryList(SYMTABENTRY[Hd::Tl], Id -> M)
         {
            Hd -> symtabentry(Id0, M)
            Equal(Id,Id0)
         |
            IsInSymtabEntryList(Tl, Id -> M)
         }
   
   proc BeginGlobalScope()
      
      rule BeginGlobalScope()
         xBeginScope()
   
   proc EndGlobalScope()
      
      rule EndGlobalScope()
         xEndScope()
   
   proc BeginLocalScope()
      
      rule BeginLocalScope()
         xBeginScope()
   
   proc EndLocalScope()
      
      rule EndLocalScope()
         xEndScope()
   
   proc xBeginScope()
      
      rule xBeginScope()
         Get-ScopeStack(-> Tl)
         Set-ScopeStack(SYMTABENTRY[][SYMTABENTRY[]::Tl])
   
   proc xEndScope()
      
      rule xEndScope()
         Get-ScopeStack(-> SYMTABENTRY[][Hd::Tl])
         Set-ScopeStack(Tl)
   
   proc DefineLocalTimer(string, string)
      
      rule DefineLocalTimer(Id, Pos)
         EnterIntoSymtab(Id, localtimer(), Pos)
   
   type Tp
      tp_charstring()
      tp_integer()
      tp_boolean()
      tp_float()
      tp_verdict()
      tp_referenced(DEFINITION)
      tp_none()
      tp_primary()
      tpxx(int)
      tp_compound(Tp[])
      tp_nimmdas(Tp[])
      tp_skip()
      tp_joker()
      tp_undef()
   
   proc DefineLocalVar(string, Tp, string)
      
      rule DefineLocalVar(Id, Tp, Pos)
         EnterIntoSymtab(Id, localvar(Tp), Pos)
   
   proc DefineComponentMember_Var(string, Tp, string)
      
      rule DefineComponentMember_Var(Id, Tp, Pos)
         EnterIntoSymtab(Id, componentmember_var(Tp), Pos)
   
   proc DefineComponentMember_Port(string, string)
      
      rule DefineComponentMember_Port(Id, Pos)
         EnterIntoSymtab(Id, componentmember_port(port_notyet()), Pos)
   
   proc DefineGlobal(string, DEFINITION, string)
      
      rule DefineGlobal(Id, Def, Pos)
         EnterIntoSymtab(Id, Def, Pos)
   
   proc EnterIntoSymtab(string, DEFINITION, string)
      
      rule EnterIntoSymtab(Id, Def, Pos)
         P <- symtabentry(Id, Def)
         Get-ScopeStack(-> Old)
         Old -> SYMTABENTRY[][Inner::Outer]
         {
            IsDef(Id, Inner)
            ErrorI("'", Id, "' already declared", Pos)
         |
         }
         NewInner <- SYMTABENTRY[P::Inner]
         New <- SYMTABENTRY[][NewInner::Outer]
         Set-ScopeStack(New)
   
   condition IsDef(string, SYMTABENTRY[])
      
      rule IsDef(Id, SYMTABENTRY[H::T])
         {
            H -> symtabentry(J, _)
            Equal(Id,J)
         |
            IsDef(Id, T)
         }
   
   type SYMTABENTRY
      symtabentry(string, DEFINITION)
   
   type DEFINITION
      componentmember_var(Tp)
      componentmember_port(NormalizedPortType)
      localtimer()
      localvar(Tp)
      functiondefinition($FunctionDef, ref NormalizedFunction)
      testcasedefinition($TestcaseDef, ref NormalizedTestcase)
      componenttype($ComponentDef, ref NormalizedComponentType)
      recordtype(string, $StructDefBody_A1_M5?, ref NormalizedFields)
      porttype(string, $PortDefAttribs, ref NormalizedPortType)
      nodefinition()
   
   proc DeclarePortType(string, $PortDefAttribs, string)
      
      rule DeclarePortType(Name, PortDefAttribs, Pos)
         DefineGlobal(Name, porttype(Name, PortDefAttribs, ref NormalizedPortType), Pos)
   
   proc DeclareRecordType(string, $StructDefBody_A1_M5?, string)
      
      rule DeclareRecordType(Name, X, Pos)
         DefineGlobal(Name, recordtype(Name, X, ref NormalizedFields), Pos)
   
   proc DeclareComponentType(string, $ComponentDef, string)
      
      rule DeclareComponentType(NAME, COMPDEF, Pos)
         DefineGlobal(NAME, componenttype(COMPDEF, ref NormalizedComponentType), Pos)
   
   proc DeclareTestcase(string, $TestcaseDef, string)
      
      rule DeclareTestcase(NAME, X, Pos)
         DefineGlobal(NAME, testcasedefinition(X, ref NormalizedTestcase), Pos)
   
   proc DeclareFunction(string, $FunctionDef, string)
      
      rule DeclareFunction(NAME, FDEF, Pos)
         DefineGlobal(NAME, functiondefinition(FDEF, ref NormalizedFunction), Pos)
   
   proc ProcessGlobals()
      
      rule ProcessGlobals()
         PhaseTwo()
         Emit_Prelude()
         PhaseThree()
         Get-ZzControlPart(-> X)
         ProcessControlPart(X)
         Emit_Postlude()
   
   proc PhaseTwo()
      
      rule PhaseTwo()
         Get-ScopeStack(-> SYMTABENTRY[][Globals])
         first GL <- Globals
         {
            GL -> SYMTABENTRY[H::T]
            H -> symtabentry(NAME, Def)
            {
               Def -> componenttype(ComponentDef, RefNormalizedComponentType)
               ActivateComponentType(NAME, ComponentDef, RefNormalizedComponentType)
            |
               Def -> porttype(N, PortDefAttribs, RefNormalizedPortType)
               ActivatePortType(N, PortDefAttribs, RefNormalizedPortType)
            |
               Def -> recordtype(N, X, RefNormalizedFields)
               ActivateRecordType(NAME, X, RefNormalizedFields)
            |
               Def -> functiondefinition(FunctionDef, RefNormalizedFunction)
               ActivateFunction(NAME, FunctionDef, RefNormalizedFunction)
            |
               Def -> testcasedefinition(TestcaseDef, RefNormalizedTestcase)
               ActivateTestcase(NAME, TestcaseDef, RefNormalizedTestcase)
            |
               CompilerError("unexpected global item")
            }
            next GL <- T
         }*
   
   proc PhaseThree()
      
      rule PhaseThree()
         Get-ScopeStack(-> SYMTABENTRY[][Globals])
         first GL <- Globals
         {
            GL -> SYMTABENTRY[H::T]
            H -> symtabentry(NAME, Def)
            {
               Def -> componenttype(ComponentDef, RefNormalizedComponentType)
            |
               Def -> porttype(N, PortDefAttribs, RefNormalizedPortType)
            |
               Def -> recordtype(N, X4, RefNormalizedFields)
            |
               Def -> functiondefinition(FunctionDef, RefNormalizedFunction)
               ActivateFunction2(NAME, RefNormalizedFunction)
            |
               Def -> testcasedefinition(TestcaseDef, RefNormalizedTestcase)
               ActivateTestcase2(NAME, RefNormalizedTestcase)
            |
               CompilerError("unexpected global item")
            }
            next GL <- T
         }*
   
   proc OpenFile(string, string)
      
      rule OpenFile(Pkg, Name)
         OpenJavaTargetFile(Pkg, Name)
   
   data ZzControlPart( -> $yyControlPart?)
   
   proc DeclareControlPart($yyControlPart?)
      
      rule DeclareControlPart(X)
         Set-ZzControlPart(X)
   
   proc ActivateFunction(string, $FunctionDef, ref NormalizedFunction)
      
      rule ActivateFunction(NAME, FDEF, RefNormalizedFunction)
         switch FDEF
         {
            case FunctionDef_A1(NAME0, FDEF13, FDEF15, M7, FDEF18, BLOCK)
            {
            }
         }
         {
            FDEF13 -> $FunctionDef_A1_M3?()
         |
            FDEF13 -> $FunctionDef_A1_M3? (_)
            NotCovered("fdef13", FDEF13)
         }
         {
            FDEF15 -> $FunctionDef_A1_M5?()
         |
            FDEF15 -> $FunctionDef_A1_M5? (_)
            NotCovered("fdef15", FDEF15)
         }
         {
            FDEF18 -> $FunctionDef_A1_M8?()
         |
            FDEF18 -> $FunctionDef_A1_M8? (_)
            NotCovered("fdef18", FDEF18)
         }
         {
            M7 -> $FunctionDef_A1_M7? (RO)
            switch RO
            {
               case FunctionDef_A1_M7_A1(RUNSON)
               {
               }
            }
            HandleRunsOn(RUNSON -> RunsOnC)
         |
            M7 -> $FunctionDef_A1_M7?()
            RunsOnC <- "(none)"
         }
         NormalizedFunction <- normalizedfunction(RunsOnC, BLOCK)
         valof RefNormalizedFunction <- NormalizedFunction
   
   proc HandleSystem($ConfigSpec_A1_M2? -> string)
      
      rule HandleSystem(SYSTEMOPT -> SystemC)
         {
            SYSTEMOPT -> $ConfigSpec_A1_M2? (ConfigSpec)
            switch ConfigSpec
            {
               case ConfigSpec_A1_M2_A1(SYSSPEC)
               {
               }
            }
            switch SYSSPEC
            {
               case SystemSpec_A1(KOMPTYPE)
               {
               }
            }
            switch KOMPTYPE
            {
               case ComponentType_A1(KOMPTYPE11, SystemC, KOMPTYPE13, KOMPTYPE14)
               {
                  {
                     KOMPTYPE11 -> $ComponentType_A1_M1?()
                  |
                     KOMPTYPE11 -> $ComponentType_A1_M1? (C11)
                     NotCovered("qualified component in 'system'", C11)
                  }
                  {
                     KOMPTYPE13 -> $ComponentType_A1_M3?()
                  |
                     KOMPTYPE13 -> $ComponentType_A1_M3? (C13)
                     NotCovered("generic component in 'system'", C13)
                  }
                  {
                     KOMPTYPE14 -> $ComponentType_A1_M4?()
                  |
                     KOMPTYPE14 -> $ComponentType_A1_M4? (C14)
                     NotCovered("paramerized component in 'system'", C14)
                  }
               }
            }
            sourcepos SystemC -> Pos
            GloballyDefined(SystemC, Pos -> Def)
            {
               Def -> componenttype(_, RefNormalizedComponentType)
            |
               ErrorI("'", SystemC, "' not defined as component", Pos)
            }
         |
            SYSTEMOPT -> $ConfigSpec_A1_M2?()
            NotCovered("no 'system' specified", SYSTEMOPT)
            SystemC <- ""
         }
   
   proc ActivateTestcase(string, $TestcaseDef, ref NormalizedTestcase)
      
      rule ActivateTestcase(NAME, TestcaseDef, RefNormalizedTestcase)
         switch TestcaseDef
         {
            case TestcaseDef_A1(NAME0, TT3, TT5, TT7, BLOCK)
            {
               {
                  TT3 -> $TestcaseDef_A1_M3?()
               |
                  TT3 -> $TestcaseDef_A1_M3? (_)
                  NotCovered("testcase tt3", TT3)
               }
               {
                  TT5 -> $TestcaseDef_A1_M5?()
               |
                  TT5 -> $TestcaseDef_A1_M5? (_)
                  NotCovered("testcase arguments", TT5)
               }
               switch TT7
               {
                  case TestcaseDef_A1_M7_A1(ConfigSpec2)
                  {
                     switch ConfigSpec2
                     {
                        case ConfigSpec_A1(RUNSON, SYSTEMOPT)
                        {
                        }
                     }
                     HandleRunsOn(RUNSON -> RunsOnC)
                     HandleSystem(SYSTEMOPT -> SystemC)
                  }
                  case TestcaseDef_A1_M7_A2(_)
                  {
                     RunsOnC <- "-"
                     SystemC <- "-"
                     error "cover"
                  }
               }
            }
         }
         NormalizedTestcase <- normalizedtestcase(RunsOnC, SystemC, BLOCK)
         valof RefNormalizedTestcase <- NormalizedTestcase
   
   proc ActivateRecordType(string, $StructDefBody_A1_M5?, ref NormalizedFields)
      
      rule ActivateRecordType(Name, X4, RefNormalizedFields)
         {
            X4 -> $StructDefBody_A1_M5?()
            NotCovered("empty field list in record types", X4)
         |
            X4 -> $StructDefBody_A1_M5? (XX)
            switch XX
            {
               case StructDefBody_A1_M5_A1(XX1, XX2)
               {
                  HandleStructFieldDef(XX1 -> Hd)
                  HandleStructFieldDefs(XX2 -> Tl)
                  Flds <- FIELD[Hd::Tl]
                  valof RefNormalizedFields <- normalizedfields(Flds)
                  global NameOfModule -> PKG
                  OpenJavaTargetFileMeta(PKG, Name)
                  "// EMITFILE 1 meta\n"
                  "package "
                  emit PKG
                  ";\n"
                  "import org.etsi.ttcn.tci.Type;\n"
                  "import org.etsi.ttcn.tci.Value;\n"
                  "import org.etsi.ttcn.tci.TciTypeClass;\n"
                  "import targetcode.Instructions;\n"
                  "public class Meta"
                  emit Name
                  " extends de.fraunhofer.fokus.ttcn.tci.TypeImpl\n"
                  "{\n"
                  "   public static Type Instance = new Meta"
                  emit Name
                  "();\n"
                  "   public Meta"
                  emit Name
                  "()\n"
                  "   {\n"
                  "      super(null, \""
                  emit Name
                  "\", TciTypeClass.RECORD, \"typeEnc\", \"typeEncVar\", null, null, null) ;\n"
                  "      Instructions.RegisterType(this);\n"
                  "   }\n"
                  "   @Override public Value newInstance()\n"
                  "   {\n"
                  "      return new "
                  emit Name
                  "(\"/NewInstance/\");\n"
                  "   }\n"
                  "}\n"
                  close
                  OpenFile(PKG, Name)
                  "// EMITFILE 2 record type\n"
                  "package "
                  emit PKG
                  ";\n"
                  Emit_Imports()
                  "import de.fraunhofer.fokus.ttcn.tci.IntegerValueImpl;\n"
                  "import de.fraunhofer.fokus.ttcn.tci.CharstringValueImpl;\n"
                  "import de.fraunhofer.fokus.ttcn.tci.BooleanValueImpl;\n"
                  "import org.etsi.ttcn.tci.Type;\n"
                  "public class "
                  emit Name
                  " extends de.fraunhofer.fokus.ttcn.tci.RecordValueImpl\n"
                  "{\n"
                  "   //public Type MetaType = Meta"
                  emit Name
                  ".Instance;\n"
                  "   public "
                  emit Name
                  "(String id) {\n"
                  "      super(Meta"
                  emit Name
                  ".Instance);\n"
                  "      fieldNames = new String[] {\n"
                  FieldNames(Flds)
                  "      };\n"
                  "      vals = new Value[] {\n"
                  FieldTypes(Flds)
                  "};\n"
                  "   }\n"
                  "}\n"
                  close
               }
            }
         }
   
   proc FieldNames(FIELD[])
      
      rule FieldNames(FIELD[H::T])
         H -> field(Name, Type)
         "\""
         emit Name
         "\""
         {
            T -> FIELD[]
            "\n"
         |
            ",\n"
            FieldNames(T)
         }
      
      rule FieldNames(FIELD[])
   
   proc FieldTypes(FIELD[])
      
      rule FieldTypes(FIELD[H::T])
         H -> field(Name, Type)
         {
            Type -> tp_integer()
            "new IntegerValueImpl()"
         |
            Type -> tp_boolean()
            "new BooleanValueImpl()"
         |
            Type -> tp_charstring()
            "new CharstringValueImpl()"
         |
            Type -> tp_referenced(D)
            D -> recordtype(Name2, _, _)
            "new "
            emit Name2
            "(\""
            emit Name2
            "\")"
         |
            NotCovered("target code record field of this type", Type)
         }
         {
            T -> FIELD[]
            "\n"
         |
            ",\n"
            FieldTypes(T)
         }
      
      rule FieldTypes(FIELD[])
   
   proc HandleStructFieldDef($StructFieldDef -> FIELD)
      
      rule HandleStructFieldDef(XX1 -> Fld)
         switch XX1
         {
            case StructFieldDef_A1(Type, Name, Array, Subtype, Optional)
            {
               switch Type
               {
                  case StructFieldDef_A1_M1_A1(RefType)
                  {
                     switch RefType
                     {
                        case Type_A1(Predef)
                        {
                           {
                              Predef -> PredefinedType_A3()
                              Tp <- tp_charstring()
                           |
                              Predef -> PredefinedType_A5()
                              Tp <- tp_integer()
                           |
                              Predef -> PredefinedType_A2()
                              Tp <- tp_boolean()
                           |
                              NotCovered("this predefined field type", Predef)
                              Tp <- tp_integer()
                           }
                        }
                        case Type_A2(R)
                        {
                           switch R
                           {
                              case ReferencedType_A1(X1, X2, X3, X4, X5)
                              {
                                 sourcepos X2 -> Pos
                                 GloballyDefined(X2, Pos -> D)
                                 Tp <- tp_referenced(D)
                              }
                           }
                        }
                     }
                  }
                  case StructFieldDef_A1_M1_A2(NestedType)
                  {
                     NotCovered("nested types", NestedType)
                     Tp <- tp_none()
                  }
               }
               {
                  Array -> $StructFieldDef_A1_M3? (A)
                  NotCovered("fields as arrays", A)
               |
                  Array -> $StructFieldDef_A1_M3?()
               }
               {
                  Subtype -> $StructFieldDef_A1_M4? (S)
                  NotCovered("subtypes for fields", S)
               |
                  Subtype -> $StructFieldDef_A1_M4?()
               }
               {
                  Optional -> $StructFieldDef_A1_M5? (O)
                  NotCovered("optional fields", O)
               |
                  Optional -> $StructFieldDef_A1_M5?()
               }
            }
         }
         Fld <- field(Name, Tp)
   
   proc HandleStructFieldDefs($StructDefBody_A1_M5_A1_M2[] -> FIELD[])
      
      rule HandleStructFieldDefs(XX2 -> Flds)
         {
            XX2 -> $StructDefBody_A1_M5_A1_M2[Head::Tail]
            {
               switch Head
               {
                  case StructDefBody_A1_M5_A1_M2_A1(YY)
                  {
                     HandleStructFieldDef(YY -> Hd)
                  }
               }
            }
            HandleStructFieldDefs(Tail -> Tl)
            Flds <- FIELD[Hd::Tl]
         |
            XX2 -> $StructDefBody_A1_M5_A1_M2[]
            Flds <- FIELD[]
         }
   
   proc ActivateComponentType(string, $ComponentDef, ref NormalizedComponentType)
      
      rule ActivateComponentType(NAME, ComponentDef, RefNormalizedComponentType)
         HandleComponent(ComponentDef -> QQ1, NormalizedComponentType)
         valof RefNormalizedComponentType <- NormalizedComponentType
         global NameOfModule -> PKG
         OpenFile(PKG, NAME)
         "// EMITFILE 3 component type\n"
         "package "
         emit PKG
         ";\n"
         Emit_Imports()
         "public class "
         emit NAME
         " extends Component\n"
         "{\n"
         "public "
         emit NAME
         "(String Id)\n"
         "{\n"
         "targetcode.Trace.print(\"Component constructed:\");\n"
         "MyName = Id;\n"
         "MyType = \""
         emit NAME
         "\";\n"
         HHHandleComponentMembers(NormalizedComponentType)
         "TestOutput();\n"
         "}\n"
         "}\n"
         close
   
   type NormalizedFunction
      normalizedfunction(string, $StatementBlock)
   
   type NormalizedTestcase
      normalizedtestcase(string, string, $StatementBlock)
   
   type NormalizedComponentType
      normalizedcomptype(CompItem[])
   
   type CompItem
      compitem_var(string, Tp)
      compitem_port(string, DEFINITION)
      compitem_null()
   
   proc HandleComponent($ComponentDef -> $ComponentDef_A1_M7?, NormalizedComponentType)
      
      rule HandleComponent(ComponentDef -> QQ, NormalizedComponentType)
         switch ComponentDef
         {
            case ComponentDef_A1(Id, AA, BB, CC, QQ)
            {
               {
                  AA -> $ComponentDef_A1_M3?()
               |
                  AA -> $ComponentDef_A1_M3? (_)
                  NotCovered("formal type parameters", AA)
               }
               {
                  BB -> $ComponentDef_A1_M4?()
               |
                  BB -> $ComponentDef_A1_M4? (_)
                  NotCovered("struct def formal type parameters", BB)
               }
               {
                  CC -> $ComponentDef_A1_M5?()
               |
                  CC -> $ComponentDef_A1_M5? (_)
                  NotCovered("extends", CC)
               }
               {
                  QQ -> $ComponentDef_A1_M7?()
                  LL2 <- CompItem[]
                  NormalizedComponentType <- normalizedcomptype(LL2)
               |
                  QQ -> $ComponentDef_A1_M7? (KK)
                  switch KK
                  {
                     case ComponentDef_A1_M7_A1(List)
                     {
                        switch List
                        {
                           case ComponentDefList_A1(LL)
                           {
                              HandleComponentDefListMembers(LL -> LL2)
                              NormalizedComponentType <- normalizedcomptype(LL2)
                           }
                        }
                     }
                  }
               }
            }
         }
   
   proc HandleComponentDefListMembers($ComponentDefList_A1_M1[] -> CompItem[])
      
      rule HandleComponentDefListMembers($ComponentDefList_A1_M1[H::T] -> CompItem[H2::T2])
         HandleComponentDefListMember(H -> H2)
         HandleComponentDefListMembers(T -> T2)
      
      rule HandleComponentDefListMembers($ComponentDefList_A1_M1[] -> CompItem[])
   
   proc HandleComponentDefListMember($ComponentDefList_A1_M1 -> CompItem)
      
      rule HandleComponentDefListMember(Member -> CompItem)
         switch Member
         {
            case ComponentDefList_A1_M1_A1(ComponentElementDef, _, _)
            {
               switch ComponentElementDef
               {
                  case ComponentElementDef_A1(X1)
                  {
                     {
                        X1 -> PortInstance_A1($PortInstance_A1_M2?(), PortType, $PortInstance_A1_M4?(), $PortInstance_A1_M5?(), PortElement_A1(PortName, $PortElement_A1_M2?(), $PortElement_A1_M3?()), $PortInstance_A1_M7[])
                     |
                        NotCovered("this kind of port instance", X1)
                        PortType <- "-"
                        PortName <- "-"
                     }
                     sourcepos PortType -> Pos
                     GloballyDefined(PortType, Pos -> Def)
                     {
                        Def -> porttype(_, _, RefNormalizedPortType)
                     |
                        ErrorI("'", PortType, "' not defined as port type", Pos)
                     }
                     CompItem <- compitem_port(PortName, Def)
                  }
                  case ComponentElementDef_A2(X2)
                  {
                     {
                        X2 -> VarInstance_A1(VarInstance_A1_M2_A1(VarInstance_A1_M2_A1_M1_A1(VARTYPE, VarList_A1(SingleVarInstance_A1(VarName, $SingleVarInstance_A1_M2?(), $SingleVarInstance_A1_M3?()), $VarList_A1_M2[]))))
                        HandleType(VARTYPE -> Tp)
                     |
                        NotCovered("this kind of var instance", X2)
                        Tp <- tp_none()
                        VarName <- "-"
                     }
                     CompItem <- compitem_var(VarName, Tp)
                  }
                  case ComponentElementDef_A3(X3)
                  {
                     NotCovered("TimerInstance in component defs", X3)
                     CompItem <- compitem_null()
                  }
                  case ComponentElementDef_A4(X4)
                  {
                     NotCovered("ConstInstance in component defs", X4)
                     CompItem <- compitem_null()
                  }
               }
            }
         }
   
   proc HHHandleComponentMembers(NormalizedComponentType)
      
      rule HHHandleComponentMembers(NormalizedComponentType)
         NormalizedComponentType -> normalizedcomptype(CompItemList)
         HHHandleComponentMembersList(CompItemList)
   
   proc HHHandleComponentMembersList(CompItem[])
      
      rule HHHandleComponentMembersList(CompItem[H::T])
         HHHandleComponentMember(H)
         HHHandleComponentMembersList(T)
      
      rule HHHandleComponentMembersList(CompItem[])
   
   proc HHHandleComponentMember(CompItem)
      
      rule HHHandleComponentMember(CompItem)
         switch CompItem
         {
            case compitem_var(VarName, Tp)
            {
               "EnterVar(\n"
               "\""
               emit VarName
               "\"\n"
               ",\n"
               "Instructions.IntegerConstant(7777)\n"
               ");\n"
            }
            case compitem_port(PortName, Def)
            {
               {
                  Def -> porttype(PortType, _, _)
               |
                  log Def
                  error "def falsch"
                  PortType <- "-"
               }
               "// "
               emit PortType
               " "
               emit PortName
               "\n"
               "EnterPort(\n"
               "\""
               emit PortName
               "\"\n"
               ",\n"
               "new "
               emit PortType
               " (\""
               emit PortName
               "\")\n"
               ");\n"
            }
            case compitem_null()
            {
            }
         }
   
   proc ActivatePortType(string, $PortDefAttribs, ref NormalizedPortType)
      
      rule ActivatePortType(NAME, PortDefAttribs, RefNormalizedPortType)
         HandlePortDefAttribs(PortDefAttribs -> Dir, Tp)
         P <- normalizedport(Dir, Tp)
         valof RefNormalizedPortType <- P
         global NameOfModule -> PKG
         OpenFile(PKG, NAME)
         "// EMITFILE 4 port type\n"
         "package "
         emit PKG
         ";\n"
         Emit_Imports()
         "public class "
         emit NAME
         " extends Port\n"
         "{\n"
         "public "
         emit NAME
         " (String Id)\n"
         "{\n"
         "MyName = Id;\n"
         "MyType = \""
         emit NAME
         "\";\n"
         "targetcode.Trace.print(\"Port constructed:\");\n"
         "TestOutput();"
         "}\n"
         "}\n"
         close
   
   proc HandlePortDefAttribs($PortDefAttribs -> Dir, Tp)
      
      rule HandlePortDefAttribs(PortDefAttribs -> Dir, Tp)
         switch PortDefAttribs
         {
            case PortDefAttribs_A1(Attr)
            {
               switch Attr
               {
                  case MessageAttribs_A1(M2, M4)
                  {
                     {
                        M2 -> $MessageAttribs_A1_M2?()
                     |
                        M2 -> $MessageAttribs_A1_M2? (_)
                        NotCovered("realtime", M2)
                     }
                     {
                        M4 -> $MessageAttribs_A1_M4[]
                        sourcepos M4 -> P
                        Error("empty attributes not expected", P)
                        Dir <- dir_in()
                        Tp <- tp_integer()
                     |
                        M4 -> $MessageAttribs_A1_M4[H::T]
                        switch H
                        {
                           case MessageAttribs_A1_M4_A1(X1, Semi)
                           {
                              switch X1
                              {
                                 case MessageAttribs_A1_M4_A1_M1_A1(XX1)
                                 {
                                    NotCovered("address decl", X1)
                                    Dir <- dir_in()
                                    Tp <- tp_integer()
                                 }
                                 case MessageAttribs_A1_M4_A1_M1_A2(XX2)
                                 {
                                    switch XX2
                                    {
                                       case MessageList_A1(Direction, AllOrTypeList)
                                       {
                                          switch Direction
                                          {
                                             case Direction_A1()
                                             {
                                                Dir <- dir_in()
                                             }
                                             case Direction_A2()
                                             {
                                                Dir <- dir_out()
                                             }
                                             case Direction_A3()
                                             {
                                                Dir <- dir_inout()
                                             }
                                          }
                                       }
                                    }
                                    switch AllOrTypeList
                                    {
                                       case AllOrTypeList_A1()
                                       {
                                          NotCovered("all", X1)
                                          Tp <- tp_integer()
                                       }
                                       case AllOrTypeList_A2(TypeList)
                                       {
                                          switch TypeList
                                          {
                                             case TypeList_A1(Head, Tail)
                                             {
                                                HandleType(Head -> Tp)
                                                {
                                                   Tail -> $TypeList_A1_M2[]
                                                |
                                                   Tail -> $TypeList_A1_M2[_::_]
                                                   NotCovered("type list", X1)
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                                 case MessageAttribs_A1_M4_A1_M1_A3(XX3)
                                 {
                                    NotCovered("config param", X1)
                                    Dir <- dir_in()
                                    Tp <- tp_integer()
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            case PortDefAttribs_A2(_)
            {
               NotCovered("procedure ports", PortDefAttribs)
               Dir <- dir_in()
               Tp <- tp_integer()
            }
            case PortDefAttribs_A3(_)
            {
               NotCovered("mixed ports", PortDefAttribs)
               Dir <- dir_in()
               Tp <- tp_integer()
            }
            case PortDefAttribs_A4(_)
            {
               NotCovered("stream ports", PortDefAttribs)
               Dir <- dir_in()
               Tp <- tp_integer()
            }
         }
   
   proc HandleType($Type -> Tp)
      
      rule HandleType(Type -> Tp)
         switch Type
         {
            case Type_A1(Predef)
            {
               {
                  Predef -> PredefinedType_A3()
                  Tp <- tp_charstring()
               |
                  Predef -> PredefinedType_A5()
                  Tp <- tp_integer()
               |
                  Predef -> PredefinedType_A2()
                  Tp <- tp_boolean()
               |
                  NotCovered("this predefined field type", Predef)
                  Tp <- tp_integer()
               }
            }
            case Type_A2(R)
            {
               switch R
               {
                  case ReferencedType_A1(X1, X2, X3, X4, X5)
                  {
                     sourcepos X2 -> Pos
                     GloballyDefined(X2, Pos -> D)
                     Tp <- tp_referenced(D)
                  }
               }
            }
         }
   
   var ActiveRunsOnComponent: string
   
   proc ActivateTestcase2(string, ref NormalizedTestcase)
      
      rule ActivateTestcase2(NAME, RefNormalizedTestcase)
         valof RefNormalizedTestcase -> NormalizedTestcase
         NormalizedTestcase -> normalizedtestcase(RunsOnC, SystemC, BLOCK)
         BeginLocalScope()
         global ActiveRunsOnComponent <- RunsOnC
         MakeComponentMembersVisible(RunsOnC)
         Trafo_StatementBlock(BLOCK -> ResBlock)
         EndLocalScope()
         Emit_Testcase(testcase(NAME, RunsOnC, SystemC, ResBlock))
   
   proc ActivateFunction2(string, ref NormalizedFunction)
      
      rule ActivateFunction2(NAME, RefNormalizedFunction)
         valof RefNormalizedFunction -> NormalizedFunction
         NormalizedFunction -> normalizedfunction(RunsOnC, BLOCK)
         BeginLocalScope()
         global ActiveRunsOnComponent <- RunsOnC
         MakeComponentMembersVisible(RunsOnC)
         Trafo_StatementBlock(BLOCK -> ResBlock)
         EndLocalScope()
         Emit_Function(function(NAME, ResBlock))
   
   proc HandleRunsOn($RunsOnSpec -> string)
      
      rule HandleRunsOn(RUNSON -> RunsOnC)
         switch RUNSON
         {
            case RunsOnSpec_A1(RUNSON13)
            {
               switch RUNSON13
               {
                  case RunsOnSpec_A1_M3_A1(COMPTYPE)
                  {
                     switch COMPTYPE
                     {
                        case ComponentType_A1(COMPTYPE11, RunsOnC, COMPTYPE13, COMPTYPE14)
                        {
                           {
                              COMPTYPE11 -> $ComponentType_A1_M1?()
                           |
                              COMPTYPE11 -> $ComponentType_A1_M1? (C11)
                              NotCovered("qualified component in 'runs on'", C11)
                           }
                           {
                              COMPTYPE13 -> $ComponentType_A1_M3?()
                           |
                              COMPTYPE13 -> $ComponentType_A1_M3? (C13)
                              NotCovered("generic component in 'runs on'", C13)
                           }
                           {
                              COMPTYPE14 -> $ComponentType_A1_M4?()
                           |
                              COMPTYPE14 -> $ComponentType_A1_M4? (C14)
                              NotCovered("paramerized component in 'runs on'", C14)
                           }
                        }
                     }
                  }
                  case RunsOnSpec_A1_M3_A2()
                  {
                     error "cover"
                     NotCovered("this runs on spec", RUNSON13)
                     RunsOnC <- ""
                  }
               }
            }
         }
   
   proc MakeComponentMembersVisible(string)
      
      rule MakeComponentMembersVisible("(none)")
      
      rule MakeComponentMembersVisible(Comp)
         sourcepos Comp -> Pos
         GloballyDefined(Comp, Pos -> M)
         {
            M -> componenttype(X, RefNormalizedComponentType)
            X -> ComponentDef_A1(_, _, _, _, DEFS)
            valof RefNormalizedComponentType -> NormalizedComponentType
            NormalizedComponentType -> normalizedcomptype(CompItems)
            CollectComponentMembers(CompItems)
         |
            ErrorI("'", Comp, "' not defined as component", Pos)
         }
   
   proc CollectComponentMembers(CompItem[])
      
      rule CollectComponentMembers(CompItem[H::T])
         CollectComponentMember(H)
         CollectComponentMembers(T)
      
      rule CollectComponentMembers(CompItem[])
   
   proc CollectComponentMember(CompItem)
      
      rule CollectComponentMember(CompItem)
         switch CompItem
         {
            case compitem_var(Id, Tp)
            {
               sourcepos Id -> Pos
               DefineComponentMember_Var(Id, Tp, Pos)
            }
            case compitem_port(Id, DEFINITION)
            {
               sourcepos Id -> Pos
               DefineComponentMember_Port(Id, Pos)
            }
            case compitem_null()
            {
            }
         }

