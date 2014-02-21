
   
   proc Trafo_TTCN3Module($TTCN3Module)
      
      rule Trafo_TTCN3Module(X)
         switch X
         {
            case TTCN3Module_A1(X1, X2, X3, With, Semicolon)
            {
               switch X1
               {
                  case ModuleId_A1(Id, LanguageSpec)
                  {
                     {
                        LanguageSpec -> $ModuleId_A1_M2?()
                     |
                        LanguageSpec -> $ModuleId_A1_M2? (L)
                        NotCovered("language specification", L)
                     }
                  }
               }
               global NameOfModule <- Id
               Trafo_yyDeclsOpt(X2)
               DeclareControlPart(X3)
               {
                  With -> $TTCN3Module_A1_M7?()
               |
                  With -> $TTCN3Module_A1_M7? (W)
                  NotCovered("'with' for modules", W)
               }
               {
                  Semicolon -> $TTCN3Module_A1_M8?()
               |
                  Semicolon -> $TTCN3Module_A1_M8? (Sem)
               }
            }
         }
   
   proc Trafo_yyDeclsOpt($yyDecls?)
      
      rule Trafo_yyDeclsOpt(X)
         {
            X -> $yyDecls?()
         |
            X -> $yyDecls? (X1)
            Trafo_yyDecls(X1)
         }
   
   proc Trafo_yyDecls($yyDecls)
      
      rule Trafo_yyDecls(X)
         switch X
         {
            case yyDecls_A1(X1)
            {
               switch X1
               {
                  case ModuleDefinitionsList_A1(LIST)
                  {
                     Trafo_ModuleDefinitionsList_A1_M1_LIST(LIST)
                  }
               }
            }
         }
   
   proc Trafo_ModuleDefinitionsList_A1_M1_LIST($ModuleDefinitionsList_A1_M1[])
      
      rule Trafo_ModuleDefinitionsList_A1_M1_LIST(LIST)
         {
            LIST -> $ModuleDefinitionsList_A1_M1[HEAD::TAIL]
            Trafo_ModuleDefinitionsList_A1_M1(HEAD)
            Trafo_ModuleDefinitionsList_A1_M1_LIST(TAIL)
         |
            LIST -> $ModuleDefinitionsList_A1_M1[]
         }
   
   proc Trafo_ModuleDefinitionsList_A1_M1($ModuleDefinitionsList_A1_M1)
      
      rule Trafo_ModuleDefinitionsList_A1_M1(HEAD)
         switch HEAD
         {
            case ModuleDefinitionsList_A1_M1_A1(Def, SemicolonOpt)
            {
               Trafo_ModuleDefinition(Def)
            }
         }
   
   proc Trafo_ModuleDefinition($ModuleDefinition)
      
      rule Trafo_ModuleDefinition(D)
         switch D
         {
            case ModuleDefinition_A1(Def, With)
            {
               Trafo_ModuleDefinition_A1_M1(Def)
               {
                  With -> $ModuleDefinition_A1_M2?()
               |
                  With -> $ModuleDefinition_A1_M2? (W)
                  NotCovered("'with' for module definitions", W)
               }
            }
         }
   
   proc Trafo_ModuleDefinition_A1_M1($ModuleDefinition_A1_M1)
      
      rule Trafo_ModuleDefinition_A1_M1(Arg1)
         switch Arg1
         {
            case ModuleDefinition_A1_M1_A1(X1)
            {
               switch X1
               {
                  case ModuleDefinition_A1_M1_A1_M1_A1(Visibility, Item)
                  {
                     {
                        Visibility -> $ModuleDefinition_A1_M1_A1_M1_A1_M1?()
                     |
                        Visibility -> $ModuleDefinition_A1_M1_A1_M1_A1_M1? (V)
                        NotCovered("visibility specification", V)
                     }
                     Trafo_Item(Item)
                  }
               }
            }
            case ModuleDefinition_A1_M1_A2(GroupDef)
            {
               NotCovered("groups", GroupDef)
            }
            case ModuleDefinition_A1_M1_A3(FriendDef)
            {
               NotCovered("'friend' definition", FriendDef)
            }
         }
   
   proc Trafo_Item($ModuleDefinition_A1_M1_A1_M1_A1_M2)
      
      rule Trafo_Item(Item)
         switch Item
         {
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A1(D1)
            {
               Trafo_TypeDef(D1)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A2(D2)
            {
               Trafo_ConstDef(D2)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A3(D3)
            {
               Trafo_TemplateDef(D3)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A4(D4)
            {
               Trafo_ModuleParDef(D4)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A5(D5)
            {
               Trafo_FunctionDef(D5)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A6(D6)
            {
               Trafo_SignatureDef(D6)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A7(D7)
            {
               Trafo_TestcaseDef(D7)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A8(D8)
            {
               Trafo_AltstepDef(D8)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A9(D9)
            {
               Trafo_ImportDef(D9)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A10(D10)
            {
               Trafo_ExtFunctionDef(D10)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A11(D11)
            {
               Trafo_ExtConstDef(D11)
            }
            case ModuleDefinition_A1_M1_A1_M1_A1_M2_A12(D12)
            {
               Trafo_ConfigurationDef(D12)
            }
         }
   
   proc Trafo_TypeDef($TypeDef)
      
      rule Trafo_TypeDef(TYPEDEF)
         switch TYPEDEF
         {
            case TypeDef_A1(B)
            {
               switch B
               {
                  case TypeDefBody_A1(StructuredTypeDef)
                  {
                     switch StructuredTypeDef
                     {
                        case StructuredTypeDef_A1(RecordDef)
                        {
                           switch RecordDef
                           {
                              case RecordDef_A1(StrucDefBody)
                              {
                                 switch StrucDefBody
                                 {
                                    case StructDefBody_A1(X1, X2, X3, X4)
                                    {
                                       switch X1
                                       {
                                          case StructDefBody_A1_M1_A1(Name)
                                          {
                                             HandleRecordType(Name, X2, X3, X4)
                                          }
                                          case StructDefBody_A1_M1_A2()
                                          {
                                             NotCovered("address in record type definitions", X1)
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                        case StructuredTypeDef_A2(UnionDef)
                        {
                           NotCovered("union type definitions", UnionDef)
                        }
                        case StructuredTypeDef_A3(SetDef)
                        {
                           NotCovered("set type definitions", SetDef)
                        }
                        case StructuredTypeDef_A4(RecordOfDef)
                        {
                           NotCovered("record-of type definitions, nur geduld", RecordOfDef)
                        }
                        case StructuredTypeDef_A5(SetOfDef)
                        {
                           NotCovered("set-of type definitions", SetOfDef)
                        }
                        case StructuredTypeDef_A6(EnumDef)
                        {
                           NotCovered("enum type definitions", EnumDef)
                        }
                        case StructuredTypeDef_A7(PortDef)
                        {
                           switch PortDef
                           {
                              case PortDef_A1(PP)
                              {
                                 switch PP
                                 {
                                    case PortDefBody_A1(NAME, FormalTypePar, StructDefFormalTypePar, PortDefAttribs)
                                    {
                                       HandlePortType(NAME, FormalTypePar, StructDefFormalTypePar, PortDefAttribs)
                                    }
                                 }
                              }
                           }
                        }
                        case StructuredTypeDef_A8(ComponentDef)
                        {
                           switch ComponentDef
                           {
                              case ComponentDef_A1(NAME, _, _, _, _)
                              {
                                 sourcepos ComponentDef -> Pos
                                 DeclareComponentType(NAME, ComponentDef, Pos)
                              }
                           }
                        }
                        case StructuredTypeDef_A9(BehaviorDef)
                        {
                           NotCovered("behavior definitions", BehaviorDef)
                        }
                     }
                  }
                  case TypeDefBody_A2(SubTypeDef)
                  {
                     NotCovered("subtype definitions", SubTypeDef)
                  }
               }
            }
         }
   
   proc HandlePortType(string, $PortDefBody_A1_M2?, $PortDefBody_A1_M3?, $PortDefAttribs)
      
      rule HandlePortType(NAME, FormalTypePar, StructDefFormalTypePar, PortDefAttribs)
         sourcepos NAME -> Pos
         DeclarePortType(NAME, PortDefAttribs, Pos)
   
   proc HandleRecordType(string, $StructDefBody_A1_M2?, $StructDefBody_A1_M3?, $StructDefBody_A1_M5?)
      
      rule HandleRecordType(Name, X2, X3, X4)
         {
            X2 -> $StructDefBody_A1_M2?()
         |
            X2 -> $StructDefBody_A1_M2? (F)
            NotCovered("formal type parameters for record types", F)
         }
         {
            X3 -> $StructDefBody_A1_M3?()
         |
            X3 -> $StructDefBody_A1_M3? (FP)
            NotCovered("formal parameters for recod types", FP)
         }
         sourcepos Name -> PosX4
         DeclareRecordType(Name, X4, PosX4)
   
   proc Trafo_ConstDef($ConstDef)
      
      rule Trafo_ConstDef(D2)
         NotCovered("constant definitions", D2)
   
   proc Trafo_TemplateDef($TemplateDef)
      
      rule Trafo_TemplateDef(D3)
         NotCovered("template definitions", D3)
   
   proc Trafo_ModuleParDef($ModuleParDef)
      
      rule Trafo_ModuleParDef(D4)
         NotCovered("modulepar definitions", D4)
   
   proc Trafo_FunctionDef($FunctionDef)
      
      rule Trafo_FunctionDef(FDEF)
         switch FDEF
         {
            case FunctionDef_A1(NAME, _, _, _, _, _)
            {
               sourcepos NAME -> Pos
               DeclareFunction(NAME, FDEF, Pos)
            }
         }
   
   proc Trafo_SignatureDef($SignatureDef)
      
      rule Trafo_SignatureDef(D6)
         NotCovered("signature definitions", D6)
   
   proc Trafo_TestcaseDef($TestcaseDef)
      
      rule Trafo_TestcaseDef(D7)
         switch D7
         {
            case TestcaseDef_A1(NAME, _, _, _, _)
            {
               sourcepos NAME -> Pos
               DeclareTestcase(NAME, D7, Pos)
            }
         }
   
   proc Trafo_AltstepDef($AltstepDef)
      
      rule Trafo_AltstepDef(D8)
         NotCovered("altstep definitions", D8)
   
   proc Trafo_ImportDef($ImportDef)
      
      rule Trafo_ImportDef(D9)
         D9 -> ImportDef_A1(ImportFromSpec_A1(ModuleId_A1(Id, _), _), _)
         RegisterImport(Id)
      
      rule Trafo_ImportDef(D9)
         NotCovered("import definitions", D9)
   
   proc Trafo_ExtFunctionDef($ExtFunctionDef)
      
      rule Trafo_ExtFunctionDef(D10)
         NotCovered("external function definitions", D10)
   
   proc Trafo_ExtConstDef($ExtConstDef)
      
      rule Trafo_ExtConstDef(D11)
         NotCovered("external constant definitions", D11)
   
   proc Trafo_ConfigurationDef($ConfigurationDef)
      
      rule Trafo_ConfigurationDef(D12)
         NotCovered("configuration definitions", D12)
   
   var Imported: string[]
   
   proc RegisterImport(string)
      
      rule RegisterImport(Id)
         global Imported -> L
         global Imported <- string[Id::L]
   
   proc InitImports()
      
      rule InitImports()
         global Imported <- string[]
   
   proc DoImports()
      
      rule DoImports()
         global Imported -> Imp
         first L <- Imp
         {
            L -> string[H::T]
            DoImport(H)
            next L <- T
         }*
   
   proc DoImport(string)
      
      rule DoImport(Id)

