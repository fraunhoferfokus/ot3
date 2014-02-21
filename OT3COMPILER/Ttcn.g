
   
   phrase TTCN3Module
      #variant TTCN3Module_A1
      "module"
      ModuleId
      "{"
      #subphrase yyDecls
      {
         #variant yyDecls_A1
         ModuleDefinitionsList
      }?
      #subphrase yyControlPart
      {
         #variant yyControlPart_A1
         ModuleControlPart
      }?
      "}"
      #subphrase TTCN3Module_A1_M7
      {
         #variant TTCN3Module_A1_M7_A1
         WithStatement
      }?
      #subphrase TTCN3Module_A1_M8
      {
         #variant TTCN3Module_A1_M8_A1
         ";"
      }?
   
   phrase ModuleId
      #variant ModuleId_A1
      Identifier
      #subphrase ModuleId_A1_M2
      {
         #variant ModuleId_A1_M2_A1
         LanguageSpec
      }?
   
   phrase LanguageSpec
      #variant LanguageSpec_A1
      "language"
      FreeText
      #subphrase LanguageSpec_A1_M3
      {
         #variant LanguageSpec_A1_M3_A1
         ","
         FreeText
      }*
   
   phrase ModuleDefinitionsList
      #variant ModuleDefinitionsList_A1
      #subphrase ModuleDefinitionsList_A1_M1
      {
         #variant ModuleDefinitionsList_A1_M1_A1
         ModuleDefinition
         #subphrase ModuleDefinitionsList_A1_M1_A1_M2
         {
            #variant ModuleDefinitionsList_A1_M1_A1_M2_A1
            ";"
         }?
      }+
   
   phrase ModuleDefinition
      #variant ModuleDefinition_A1
      #subphrase ModuleDefinition_A1_M1
      {
         #variant ModuleDefinition_A1_M1_A1
         #subphrase ModuleDefinition_A1_M1_A1_M1
         {
            #variant ModuleDefinition_A1_M1_A1_M1_A1
            #subphrase ModuleDefinition_A1_M1_A1_M1_A1_M1
            {
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M1_A1
               Visibility
            }?
            #subphrase ModuleDefinition_A1_M1_A1_M1_A1_M2
            {
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A1
               TypeDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A2
               ConstDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A3
               TemplateDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A4
               ModuleParDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A5
               FunctionDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A6
               SignatureDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A7
               TestcaseDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A8
               AltstepDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A9
               ImportDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A10
               ExtFunctionDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A11
               ExtConstDef
            |
               #variant ModuleDefinition_A1_M1_A1_M1_A1_M2_A12
               ConfigurationDef
            }
         }
      |
         #variant ModuleDefinition_A1_M1_A2
         #subphrase ModuleDefinition_A1_M1_A2_M1
         {
            #variant ModuleDefinition_A1_M1_A2_M1_A1
            #subphrase ModuleDefinition_A1_M1_A2_M1_A1_M1
            {
               #variant ModuleDefinition_A1_M1_A2_M1_A1_M1_A1
               "public"
            }?
            GroupDef
         }
      |
         #variant ModuleDefinition_A1_M1_A3
         #subphrase ModuleDefinition_A1_M1_A3_M1
         {
            #variant ModuleDefinition_A1_M1_A3_M1_A1
            #subphrase ModuleDefinition_A1_M1_A3_M1_A1_M1
            {
               #variant ModuleDefinition_A1_M1_A3_M1_A1_M1_A1
               "private"
            }?
            FriendModuleDef
         }
      }
      #subphrase ModuleDefinition_A1_M2
      {
         #variant ModuleDefinition_A1_M2_A1
         WithStatement
      }?
   
   phrase Visibility
      #variant Visibility_A1
      "public"
   |
      #variant Visibility_A2
      "friend"
   |
      #variant Visibility_A3
      "private"
   
   phrase TypeDef
      #variant TypeDef_A1
      "type"
      TypeDefBody
   
   phrase TypeDefBody
      #variant TypeDefBody_A1
      StructuredTypeDef
   |
      #variant TypeDefBody_A2
      SubTypeDef
   
   phrase StructuredTypeDef
      #variant StructuredTypeDef_A1
      RecordDef
   |
      #variant StructuredTypeDef_A2
      UnionDef
   |
      #variant StructuredTypeDef_A3
      SetDef
   |
      #variant StructuredTypeDef_A4
      RecordOfDef
   |
      #variant StructuredTypeDef_A5
      SetOfDef
   |
      #variant StructuredTypeDef_A6
      EnumDef
   |
      #variant StructuredTypeDef_A7
      PortDef
   |
      #variant StructuredTypeDef_A8
      ComponentDef
   |
      #variant StructuredTypeDef_A9
      BehaviourDef
   
   phrase RecordDef
      #variant RecordDef_A1
      "record"
      StructDefBody
   
   phrase StructDefBody
      #variant StructDefBody_A1
      #subphrase StructDefBody_A1_M1
      {
         #variant StructDefBody_A1_M1_A1
         Identifier
      |
         #variant StructDefBody_A1_M1_A2
         "address"
      }
      #subphrase StructDefBody_A1_M2
      {
         #variant StructDefBody_A1_M2_A1
         FormalTypeParList
      }?
      #subphrase StructDefBody_A1_M3
      {
         #variant StructDefBody_A1_M3_A1
         StructDefFormalParList
      }?
      "{"
      #subphrase StructDefBody_A1_M5
      {
         #variant StructDefBody_A1_M5_A1
         StructFieldDef
         #subphrase StructDefBody_A1_M5_A1_M2
         {
            #variant StructDefBody_A1_M5_A1_M2_A1
            ","
            StructFieldDef
         }*
      }?
      "}"
   
   phrase StructFieldDef
      #variant StructFieldDef_A1
      #subphrase StructFieldDef_A1_M1
      {
         #variant StructFieldDef_A1_M1_A1
         Type
      |
         #variant StructFieldDef_A1_M1_A2
         NestedTypeDef
      }
      Identifier
      #subphrase StructFieldDef_A1_M3
      {
         #variant StructFieldDef_A1_M3_A1
         ArrayDef
      }?
      #subphrase StructFieldDef_A1_M4
      {
         #variant StructFieldDef_A1_M4_A1
         SubTypeSpec
      }?
      #subphrase StructFieldDef_A1_M5
      {
         #variant StructFieldDef_A1_M5_A1
         "optional"
      }?
   
   phrase NestedTypeDef
      #variant NestedTypeDef_A1
      NestedRecordDef
   |
      #variant NestedTypeDef_A2
      NestedUnionDef
   |
      #variant NestedTypeDef_A3
      NestedSetDef
   |
      #variant NestedTypeDef_A4
      NestedRecordOfDef
   |
      #variant NestedTypeDef_A5
      NestedSetOfDef
   |
      #variant NestedTypeDef_A6
      NestedEnumDef
   |
      #variant NestedTypeDef_A7
      NestedBehaviourDef
   
   phrase NestedRecordDef
      #variant NestedRecordDef_A1
      "record"
      "{"
      #subphrase NestedRecordDef_A1_M3
      {
         #variant NestedRecordDef_A1_M3_A1
         StructFieldDef
         #subphrase NestedRecordDef_A1_M3_A1_M2
         {
            #variant NestedRecordDef_A1_M3_A1_M2_A1
            ","
            StructFieldDef
         }*
      }?
      "}"
   
   phrase NestedUnionDef
      #variant NestedUnionDef_A1
      "union"
      "{"
      UnionFieldDef
      #subphrase NestedUnionDef_A1_M4
      {
         #variant NestedUnionDef_A1_M4_A1
         ","
         UnionFieldDef
      }*
      "}"
   
   phrase NestedSetDef
      #variant NestedSetDef_A1
      "set"
      "{"
      #subphrase NestedSetDef_A1_M3
      {
         #variant NestedSetDef_A1_M3_A1
         StructFieldDef
         #subphrase NestedSetDef_A1_M3_A1_M2
         {
            #variant NestedSetDef_A1_M3_A1_M2_A1
            ","
            StructFieldDef
         }*
      }?
      "}"
   
   phrase NestedRecordOfDef
      #variant NestedRecordOfDef_A1
      "record"
      #subphrase NestedRecordOfDef_A1_M2
      {
         #variant NestedRecordOfDef_A1_M2_A1
         StringLength
      }?
      "of"
      #subphrase NestedRecordOfDef_A1_M4
      {
         #variant NestedRecordOfDef_A1_M4_A1
         Type
      |
         #variant NestedRecordOfDef_A1_M4_A2
         NestedTypeDef
      }
   
   phrase NestedSetOfDef
      #variant NestedSetOfDef_A1
      "set"
      #subphrase NestedSetOfDef_A1_M2
      {
         #variant NestedSetOfDef_A1_M2_A1
         StringLength
      }?
      "of"
      #subphrase NestedSetOfDef_A1_M4
      {
         #variant NestedSetOfDef_A1_M4_A1
         Type
      |
         #variant NestedSetOfDef_A1_M4_A2
         NestedTypeDef
      }
   
   phrase NestedEnumDef
      #variant NestedEnumDef_A1
      "enumerated"
      "{"
      EnumerationList
      "}"
   
   phrase UnionDef
      #variant UnionDef_A1
      "union"
      UnionDefBody
   
   phrase UnionDefBody
      #variant UnionDefBody_A1
      #subphrase UnionDefBody_A1_M1
      {
         #variant UnionDefBody_A1_M1_A1
         Identifier
      |
         #variant UnionDefBody_A1_M1_A2
         "address"
      }
      #subphrase UnionDefBody_A1_M2
      {
         #variant UnionDefBody_A1_M2_A1
         FormalTypeParList
      }?
      #subphrase UnionDefBody_A1_M3
      {
         #variant UnionDefBody_A1_M3_A1
         StructDefFormalParList
      }?
      "{"
      UnionFieldDef
      #subphrase UnionDefBody_A1_M6
      {
         #variant UnionDefBody_A1_M6_A1
         ","
         UnionFieldDef
      }*
      "}"
   
   phrase UnionFieldDef
      #variant UnionFieldDef_A1
      #subphrase UnionFieldDef_A1_M1
      {
         #variant UnionFieldDef_A1_M1_A1
         Type
      |
         #variant UnionFieldDef_A1_M1_A2
         NestedTypeDef
      }
      Identifier
      #subphrase UnionFieldDef_A1_M3
      {
         #variant UnionFieldDef_A1_M3_A1
         ArrayDef
      }?
      #subphrase UnionFieldDef_A1_M4
      {
         #variant UnionFieldDef_A1_M4_A1
         SubTypeSpec
      }?
   
   phrase SetDef
      #variant SetDef_A1
      "set"
      StructDefBody
   
   phrase RecordOfDef
      #variant RecordOfDef_A1
      "record"
      #subphrase RecordOfDef_A1_M2
      {
         #variant RecordOfDef_A1_M2_A1
         StringLength
      }?
      "of"
      StructOfDefBody
   
   phrase StructOfDefBody
      #variant StructOfDefBody_A1
      #subphrase StructOfDefBody_A1_M1
      {
         #variant StructOfDefBody_A1_M1_A1
         Type
      |
         #variant StructOfDefBody_A1_M1_A2
         NestedTypeDef
      }
      #subphrase StructOfDefBody_A1_M2
      {
         #variant StructOfDefBody_A1_M2_A1
         Identifier
      |
         #variant StructOfDefBody_A1_M2_A2
         "address"
      }
      #subphrase StructOfDefBody_A1_M3
      {
         #variant StructOfDefBody_A1_M3_A1
         FormalTypeParList
      }?
      #subphrase StructOfDefBody_A1_M4
      {
         #variant StructOfDefBody_A1_M4_A1
         StructDefFormalParList
      }?
      #subphrase StructOfDefBody_A1_M5
      {
         #variant StructOfDefBody_A1_M5_A1
         SubTypeSpec
      }?
   
   phrase SetOfDef
      #variant SetOfDef_A1
      "set"
      #subphrase SetOfDef_A1_M2
      {
         #variant SetOfDef_A1_M2_A1
         StringLength
      }?
      "of"
      StructOfDefBody
   
   phrase EnumDef
      #variant EnumDef_A1
      "enumerated"
      #subphrase EnumDef_A1_M2
      {
         #variant EnumDef_A1_M2_A1
         Identifier
      |
         #variant EnumDef_A1_M2_A2
         "address"
      }
      #subphrase EnumDef_A1_M3
      {
         #variant EnumDef_A1_M3_A1
         StructDefFormalParList
      }?
      "{"
      EnumerationList
      "}"
   
   phrase EnumerationList
      #variant EnumerationList_A1
      Enumeration
      #subphrase EnumerationList_A1_M2
      {
         #variant EnumerationList_A1_M2_A1
         ","
         Enumeration
      }*
   
   phrase Enumeration
      #variant Enumeration_A1
      Identifier
      #subphrase Enumeration_A1_M2
      {
         #variant Enumeration_A1_M2_A1
         "("
         #subphrase Enumeration_A1_M2_A1_M2
         {
            #variant Enumeration_A1_M2_A1_M2_A1
            "-"
         }?
         Number
         ")"
      }?
   
   phrase SubTypeDef
      #variant SubTypeDef_A1
      Type
      #subphrase SubTypeDef_A1_M2
      {
         #variant SubTypeDef_A1_M2_A1
         Identifier
      |
         #variant SubTypeDef_A1_M2_A2
         "address"
      }
      #subphrase SubTypeDef_A1_M3
      {
         #variant SubTypeDef_A1_M3_A1
         FormalTypeParList
      }?
      #subphrase SubTypeDef_A1_M4
      {
         #variant SubTypeDef_A1_M4_A1
         StructDefFormalParList
      }?
      #subphrase SubTypeDef_A1_M5
      {
         #variant SubTypeDef_A1_M5_A1
         ArrayDef
      }?
      #subphrase SubTypeDef_A1_M6
      {
         #variant SubTypeDef_A1_M6_A1
         SubTypeSpec
      }?
   
   phrase SubTypeSpec
      #variant SubTypeSpec_A1
      AllowedValuesSpec
      #subphrase SubTypeSpec_A1_M2
      {
         #variant SubTypeSpec_A1_M2_A1
         StringLength
      }?
   |
      #variant SubTypeSpec_A2
      StringLength
   
   phrase AllowedValuesSpec
      #variant AllowedValuesSpec_A1
      "("
      #subphrase AllowedValuesSpec_A1_M2
      {
         #variant AllowedValuesSpec_A1_M2_A1
         #subphrase AllowedValuesSpec_A1_M2_A1_M1
         {
            #variant AllowedValuesSpec_A1_M2_A1_M1_A1
            TemplateOrRange
            #subphrase AllowedValuesSpec_A1_M2_A1_M1_A1_M2
            {
               #variant AllowedValuesSpec_A1_M2_A1_M1_A1_M2_A1
               ","
               TemplateOrRange
            }*
         }
      |
         #variant AllowedValuesSpec_A1_M2_A2
         CharStringMatch
      }
      ")"
   
   phrase TemplateOrRange
      #variant TemplateOrRange_A1
      RangeDef
   |
      #variant TemplateOrRange_A2
      TemplateBody
   |
      #variant TemplateOrRange_A3
      Type
   
   phrase RangeDef
      #variant RangeDef_A1
      Bound
      ".."
      Bound
   
   phrase StringLength
      #variant StringLength_A1
      "length"
      "("
      SingleExpression
      #subphrase StringLength_A1_M4
      {
         #variant StringLength_A1_M4_A1
         ".."
         Bound
      }?
      ")"
   
   phrase PortDef
      #variant PortDef_A1
      "port"
      PortDefBody
   
   phrase PortDefBody
      #variant PortDefBody_A1
      Identifier
      #subphrase PortDefBody_A1_M2
      {
         #variant PortDefBody_A1_M2_A1
         FormalTypeParList
      }?
      #subphrase PortDefBody_A1_M3
      {
         #variant PortDefBody_A1_M3_A1
         StructDefFormalParList
      }?
      PortDefAttribs
   
   phrase PortDefAttribs
      #variant PortDefAttribs_A1
      MessageAttribs
   |
      #variant PortDefAttribs_A2
      ProcedureAttribs
   |
      #variant PortDefAttribs_A3
      MixedAttribs
   |
      #variant PortDefAttribs_A4
      StreamAttribs
   
   phrase MessageAttribs
      #variant MessageAttribs_A1
      "message"
      #subphrase MessageAttribs_A1_M2
      {
         #variant MessageAttribs_A1_M2_A1
         "realtime"
      }?
      "{"
      #subphrase MessageAttribs_A1_M4
      {
         #variant MessageAttribs_A1_M4_A1
         #subphrase MessageAttribs_A1_M4_A1_M1
         {
            #variant MessageAttribs_A1_M4_A1_M1_A1
            AddressDecl
         |
            #variant MessageAttribs_A1_M4_A1_M1_A2
            MessageList
         |
            #variant MessageAttribs_A1_M4_A1_M1_A3
            ConfigParamDef
         }
         #subphrase MessageAttribs_A1_M4_A1_M2
         {
            #variant MessageAttribs_A1_M4_A1_M2_A1
            ";"
         }?
      }+
      "}"
   
   phrase ConfigParamDef
      #variant ConfigParamDef_A1
      MapParamDef
   |
      #variant ConfigParamDef_A2
      UnmapParamDef
   
   phrase MapParamDef
      #variant MapParamDef_A1
      "map"
      "param"
      "("
      FormalValuePar
      #subphrase MapParamDef_A1_M5
      {
         #variant MapParamDef_A1_M5_A1
         ","
         FormalValuePar
      }*
      ")"
   
   phrase UnmapParamDef
      #variant UnmapParamDef_A1
      "unmap"
      "param"
      "("
      FormalValuePar
      #subphrase UnmapParamDef_A1_M5
      {
         #variant UnmapParamDef_A1_M5_A1
         ","
         FormalValuePar
      }*
      ")"
   
   phrase AddressDecl
      #variant AddressDecl_A1
      "address"
      Type
   
   phrase MessageList
      #variant MessageList_A1
      Direction
      AllOrTypeList
   
   phrase Direction
      #variant Direction_A1
      "in"
   |
      #variant Direction_A2
      "out"
   |
      #variant Direction_A3
      "inout"
   
   phrase AllOrTypeList
      #variant AllOrTypeList_A1
      "all"
   |
      #variant AllOrTypeList_A2
      TypeList
   
   phrase TypeList
      #variant TypeList_A1
      Type
      #subphrase TypeList_A1_M2
      {
         #variant TypeList_A1_M2_A1
         ","
         Type
      }*
   
   phrase ProcedureAttribs
      #variant ProcedureAttribs_A1
      "procedure"
      #subphrase ProcedureAttribs_A1_M2
      {
         #variant ProcedureAttribs_A1_M2_A1
         "realtime"
      }?
      "{"
      #subphrase ProcedureAttribs_A1_M4
      {
         #variant ProcedureAttribs_A1_M4_A1
         #subphrase ProcedureAttribs_A1_M4_A1_M1
         {
            #variant ProcedureAttribs_A1_M4_A1_M1_A1
            AddressDecl
         |
            #variant ProcedureAttribs_A1_M4_A1_M1_A2
            ProcedureList
         |
            #variant ProcedureAttribs_A1_M4_A1_M1_A3
            ConfigParamDef
         }
         #subphrase ProcedureAttribs_A1_M4_A1_M2
         {
            #variant ProcedureAttribs_A1_M4_A1_M2_A1
            ";"
         }?
      }+
      "}"
   
   phrase ProcedureList
      #variant ProcedureList_A1
      Direction
      AllOrSignatureList
   
   phrase AllOrSignatureList
      #variant AllOrSignatureList_A1
      "all"
   |
      #variant AllOrSignatureList_A2
      SignatureList
   
   phrase SignatureList
      #variant SignatureList_A1
      Signature
      #subphrase SignatureList_A1_M2
      {
         #variant SignatureList_A1_M2_A1
         ","
         Signature
      }*
   
   phrase MixedAttribs
      #variant MixedAttribs_A1
      "mixed"
      #subphrase MixedAttribs_A1_M2
      {
         #variant MixedAttribs_A1_M2_A1
         "realtime"
      }?
      "{"
      #subphrase MixedAttribs_A1_M4
      {
         #variant MixedAttribs_A1_M4_A1
         #subphrase MixedAttribs_A1_M4_A1_M1
         {
            #variant MixedAttribs_A1_M4_A1_M1_A1
            AddressDecl
         |
            #variant MixedAttribs_A1_M4_A1_M1_A2
            MixedList
         |
            #variant MixedAttribs_A1_M4_A1_M1_A3
            ConfigParamDef
         }
         #subphrase MixedAttribs_A1_M4_A1_M2
         {
            #variant MixedAttribs_A1_M4_A1_M2_A1
            ";"
         }?
      }+
      "}"
   
   phrase MixedList
      #variant MixedList_A1
      Direction
      ProcOrTypeList
   
   phrase ProcOrTypeList
      #variant ProcOrTypeList_A1
      "all"
   |
      #variant ProcOrTypeList_A2
      #subphrase ProcOrTypeList_A2_M1
      {
         #variant ProcOrTypeList_A2_M1_A1
         ProcOrType
         #subphrase ProcOrTypeList_A2_M1_A1_M2
         {
            #variant ProcOrTypeList_A2_M1_A1_M2_A1
            ","
            ProcOrType
         }*
      }
   
   phrase ProcOrType
      #variant ProcOrType_A1
      Signature
   |
      #variant ProcOrType_A2
      Type
   
   phrase ComponentDef
      #variant ComponentDef_A1
      "component"
      Identifier
      #subphrase ComponentDef_A1_M3
      {
         #variant ComponentDef_A1_M3_A1
         FormalTypeParList
      }?
      #subphrase ComponentDef_A1_M4
      {
         #variant ComponentDef_A1_M4_A1
         StructDefFormalParList
      }?
      #subphrase ComponentDef_A1_M5
      {
         #variant ComponentDef_A1_M5_A1
         "extends"
         ComponentType
         #subphrase ComponentDef_A1_M5_A1_M3
         {
            #variant ComponentDef_A1_M5_A1_M3_A1
            ","
            ComponentType
         }*
      }?
      "{"
      #subphrase ComponentDef_A1_M7
      {
         #variant ComponentDef_A1_M7_A1
         ComponentDefList
      }?
      "}"
   
   phrase ComponentType
      #variant ComponentType_A1
      #subphrase ComponentType_A1_M1
      {
         #variant ComponentType_A1_M1_A1
         Identifier
         "."
      }?
      Identifier
      #subphrase ComponentType_A1_M3
      {
         #variant ComponentType_A1_M3_A1
         ActualTypeParList
      }?
      #subphrase ComponentType_A1_M4
      {
         #variant ComponentType_A1_M4_A1
         TypeActualParList
      }?
   
   phrase ComponentDefList
      #variant ComponentDefList_A1
      #subphrase ComponentDefList_A1_M1
      {
         #variant ComponentDefList_A1_M1_A1
         ComponentElementDef
         #subphrase ComponentDefList_A1_M1_A1_M2
         {
            #variant ComponentDefList_A1_M1_A1_M2_A1
            WithStatement
         }?
         #subphrase ComponentDefList_A1_M1_A1_M3
         {
            #variant ComponentDefList_A1_M1_A1_M3_A1
            ";"
         }?
      }*
   
   phrase ComponentElementDef
      #variant ComponentElementDef_A1
      PortInstance
   |
      #variant ComponentElementDef_A2
      VarInstance
   |
      #variant ComponentElementDef_A3
      TimerInstance
   |
      #variant ComponentElementDef_A4
      ConstDef
   
   phrase PortInstance
      #variant PortInstance_A1
      "port"
      #subphrase PortInstance_A1_M2
      {
         #variant PortInstance_A1_M2_A1
         Identifier
         "."
      }?
      Identifier
      #subphrase PortInstance_A1_M4
      {
         #variant PortInstance_A1_M4_A1
         ActualTypeParList
      }?
      #subphrase PortInstance_A1_M5
      {
         #variant PortInstance_A1_M5_A1
         TypeActualParList
      }?
      PortElement
      #subphrase PortInstance_A1_M7
      {
         #variant PortInstance_A1_M7_A1
         ","
         PortElement
      }*
   
   phrase PortElement
      #variant PortElement_A1
      Identifier
      #subphrase PortElement_A1_M2
      {
         #variant PortElement_A1_M2_A1
         ArrayDef
      }?
      #subphrase PortElement_A1_M3
      {
         #variant PortElement_A1_M3_A1
         ":="
         PortInitialValue
      }?
   
   phrase ConstDef
      #variant ConstDef_A1
      "const"
      Type
      ConstList
   
   phrase ConstList
      #variant ConstList_A1
      SingleConstDef
      #subphrase ConstList_A1_M2
      {
         #variant ConstList_A1_M2_A1
         ","
         SingleConstDef
      }*
   
   phrase SingleConstDef
      #variant SingleConstDef_A1
      Identifier
      #subphrase SingleConstDef_A1_M2
      {
         #variant SingleConstDef_A1_M2_A1
         ArrayDef
      }?
      ":="
      ConstantExpression
   
   phrase TemplateDef
      #variant TemplateDef_A1
      "template"
      #subphrase TemplateDef_A1_M2
      {
         #variant TemplateDef_A1_M2_A1
         TemplateRestriction
      }?
      BaseTemplate
      #subphrase TemplateDef_A1_M4
      {
         #variant TemplateDef_A1_M4_A1
         DerivedDef
      }?
      ":="
      TemplateBody
   
   phrase BaseTemplate
      #variant BaseTemplate_A1
      #subphrase BaseTemplate_A1_M1
      {
         #variant BaseTemplate_A1_M1_A1
         Type
      |
         #variant BaseTemplate_A1_M1_A2
         Signature
      }
      Identifier
      #subphrase BaseTemplate_A1_M3
      {
         #variant BaseTemplate_A1_M3_A1
         FormalTypeParList
      }?
      #subphrase BaseTemplate_A1_M4
      {
         #variant BaseTemplate_A1_M4_A1
         "("
         TemplateOrValueFormalParList
         ")"
      }?
   
   phrase DerivedDef
      #variant DerivedDef_A1
      "modifies"
      ExtendedIdentifier
   
   phrase TemplateOrValueFormalParList
      #variant TemplateOrValueFormalParList_A1
      TemplateOrValueFormalPar
      #subphrase TemplateOrValueFormalParList_A1_M2
      {
         #variant TemplateOrValueFormalParList_A1_M2_A1
         ","
         TemplateOrValueFormalPar
      }*
   
   phrase TemplateOrValueFormalPar
      #variant TemplateOrValueFormalPar_A1
      FormalValuePar
   |
      #variant TemplateOrValueFormalPar_A2
      FormalTemplatePar
   
   phrase TemplateBody
      #variant TemplateBody_A1
      #subphrase TemplateBody_A1_M1
      {
         #variant TemplateBody_A1_M1_A1
         SimpleSpec
      |
         #variant TemplateBody_A1_M1_A2
         FieldSpecList
      |
         #variant TemplateBody_A1_M1_A3
         ArrayValueOrAttrib
      }
      #subphrase TemplateBody_A1_M2
      {
         #variant TemplateBody_A1_M2_A1
         ExtraMatchingAttributes
      }?
   
   phrase SimpleSpec
      #variant SimpleSpec_A1
      #subphrase SimpleSpec_A1_M1
      {
         #variant SimpleSpec_A1_M1_A1
         SingleExpression
         #subphrase SimpleSpec_A1_M1_A1_M2
         {
            #variant SimpleSpec_A1_M1_A1_M2_A1
            "&"
            SimpleTemplateSpec
         }?
      }
   |
      #variant SimpleSpec_A2
      SimpleTemplateSpec
   
   phrase SimpleTemplateSpec
      #variant SimpleTemplateSpec_A1
      CharStringMatch
   |
      #variant SimpleTemplateSpec_A2
      #subphrase SimpleTemplateSpec_A2_M1
      {
         #variant SimpleTemplateSpec_A2_M1_A1
         SingleTemplateExpression
         #subphrase SimpleTemplateSpec_A2_M1_A1_M2
         {
            #variant SimpleTemplateSpec_A2_M1_A1_M2_A1
            "&"
            SimpleSpec
         }?
      }
   
   phrase SingleTemplateExpression
      #variant SingleTemplateExpression_A1
      MatchingSymbol
   |
      #variant SingleTemplateExpression_A2
      #subphrase SingleTemplateExpression_A2_M1
      {
         #variant SingleTemplateExpression_A2_M1_A1
         TemplateRefWithParList
         #subphrase SingleTemplateExpression_A2_M1_A1_M2
         {
            #variant SingleTemplateExpression_A2_M1_A1_M2_A1
            ExtendedFieldReference
         }?
      }
   
   phrase FieldSpecList
      #variant FieldSpecList_A1
      "{"
      #subphrase FieldSpecList_A1_M2
      {
         #variant FieldSpecList_A1_M2_A1
         FieldSpec
         #subphrase FieldSpecList_A1_M2_A1_M2
         {
            #variant FieldSpecList_A1_M2_A1_M2_A1
            ","
            FieldSpec
         }*
      }?
      "}"
   
   phrase FieldSpec
      #variant FieldSpec_A1
      FieldReference
      ":="
      TemplateBody
   
   phrase FieldReference
      #variant FieldReference_A1
      StructFieldRef
   |
      #variant FieldReference_A2
      ArrayOrBitRef
   
   phrase StructFieldRef
      #variant StructFieldRef_A1
      Identifier
   |
      #variant StructFieldRef_A2
      PredefinedType
   |
      #variant StructFieldRef_A3
      ReferencedType
   
   phrase ArrayOrBitRef
      #variant ArrayOrBitRef_A1
      "["
      FieldOrBitNumber
      "]"
   
   phrase FieldOrBitNumber
      #variant FieldOrBitNumber_A1
      SingleExpression
   
   phrase ArrayValueOrAttrib
      #variant ArrayValueOrAttrib_A1
      "{"
      ArrayElementSpecList
      "}"
   
   phrase ArrayElementSpecList
      #variant ArrayElementSpecList_A1
      ArrayElementSpec
      #subphrase ArrayElementSpecList_A1_M2
      {
         #variant ArrayElementSpecList_A1_M2_A1
         ","
         ArrayElementSpec
      }*
   
   phrase ArrayElementSpec
      #variant ArrayElementSpec_A1
      "-"
   |
      #variant ArrayElementSpec_A2
      PermutationMatch
   |
      #variant ArrayElementSpec_A3
      TemplateBody
   
   phrase MatchingSymbol
      #variant MatchingSymbol_A1
      Complement
   |
      #variant MatchingSymbol_A2
      #subphrase MatchingSymbol_A2_M1
      {
         #variant MatchingSymbol_A2_M1_A1
         "?"
         #subphrase MatchingSymbol_A2_M1_A1_M2
         {
            #variant MatchingSymbol_A2_M1_A1_M2_A1
            WildcardLengthMatch
         }?
      }
   |
      #variant MatchingSymbol_A3
      #subphrase MatchingSymbol_A3_M1
      {
         #variant MatchingSymbol_A3_M1_A1
         "*"
         #subphrase MatchingSymbol_A3_M1_A1_M2
         {
            #variant MatchingSymbol_A3_M1_A1_M2_A1
            WildcardLengthMatch
         }?
      }
   |
      #variant MatchingSymbol_A4
      TemplateList
   |
      #variant MatchingSymbol_A5
      Range
   |
      #variant MatchingSymbol_A6
      BitStringMatch
   |
      #variant MatchingSymbol_A7
      HexStringMatch
   |
      #variant MatchingSymbol_A8
      OctetStringMatch
   |
      #variant MatchingSymbol_A9
      CharStringMatch
   |
      #variant MatchingSymbol_A10
      SubsetMatch
   |
      #variant MatchingSymbol_A11
      SupersetMatch
   
   phrase ExtraMatchingAttributes
      #variant ExtraMatchingAttributes_A1
      StringLength
   |
      #variant ExtraMatchingAttributes_A2
      "ifpresent"
   |
      #variant ExtraMatchingAttributes_A3
      #subphrase ExtraMatchingAttributes_A3_M1
      {
         #variant ExtraMatchingAttributes_A3_M1_A1
         StringLength
         "ifpresent"
      }
   
   phrase BitStringMatch
      #variant BitStringMatch_A1
      BITSTRINGMATCH
   
   phrase HexStringMatch
      #variant HexStringMatch_A1
      HEXSTRINGMATCH
   
   phrase OctetStringMatch
      #variant OctetStringMatch_A1
      OCTETSTRINGMATCH
   
   phrase CharStringMatch
      #variant CharStringMatch_A1
      "pattern"
      PatternParticle
      #subphrase CharStringMatch_A1_M3
      {
         #variant CharStringMatch_A1_M3_A1
         "&"
         PatternParticle
      }*
   
   phrase PatternParticle
      #variant PatternParticle_A1
      Pattern
   |
      #variant PatternParticle_A2
      ReferencedValue
   
   phrase Pattern
      #variant Pattern_A1
      PATTERN
   
   phrase Complement
      #variant Complement_A1
      "complement"
      "("
      TemplateBody
      #subphrase Complement_A1_M4
      {
         #variant Complement_A1_M4_A1
         ","
         TemplateBody
      }*
      ")"
   
   phrase SubsetMatch
      #variant SubsetMatch_A1
      "subset"
      TemplateList
   
   phrase SupersetMatch
      #variant SupersetMatch_A1
      "superset"
      TemplateList
   
   phrase PermutationMatch
      #variant PermutationMatch_A1
      "permutation"
      PermutationList
   
   phrase PermutationList
      #variant PermutationList_A1
      "("
      TemplateBody
      #subphrase PermutationList_A1_M3
      {
         #variant PermutationList_A1_M3_A1
         ","
         TemplateBody
      }*
      ")"
   
   phrase TemplateList
      #variant TemplateList_A1
      "("
      TemplateBody
      #subphrase TemplateList_A1_M3
      {
         #variant TemplateList_A1_M3_A1
         ","
         TemplateBody
      }+
      ")"
   
   phrase WildcardLengthMatch
      #variant WildcardLengthMatch_A1
      "length"
      "("
      SingleExpression
      ")"
   
   phrase Range
      #variant Range_A1
      "("
      Bound
      ".."
      Bound
      ")"
   
   phrase Bound
      #variant Bound_A1
      #subphrase Bound_A1_M1
      {
         #variant Bound_A1_M1_A1
         #subphrase Bound_A1_M1_A1_M1
         {
            #variant Bound_A1_M1_A1_M1_A1
            "!"
         }?
         SingleExpression
      }
   |
      #variant Bound_A2
      #subphrase Bound_A2_M1
      {
         #variant Bound_A2_M1_A1
         #subphrase Bound_A2_M1_A1_M1
         {
            #variant Bound_A2_M1_A1_M1_A1
            "-"
         }?
         "infinity"
      }
   
   phrase TemplateInstanceAssignment
      #variant TemplateInstanceAssignment_A1
      Identifier
      ":="
      InLineTemplate
   
   phrase TemplateRefWithParList
      #variant TemplateRefWithParList_A1
      ExtendedIdentifier
      #subphrase TemplateRefWithParList_A1_M2
      {
         #variant TemplateRefWithParList_A1_M2_A1
         TemplateActualParList
      }?
   
   phrase InLineTemplate
      #variant InLineTemplate_A1
      #subphrase InLineTemplate_A1_M1
      {
         #variant InLineTemplate_A1_M1_A1
         #subphrase InLineTemplate_A1_M1_A1_M1
         {
            #variant InLineTemplate_A1_M1_A1_M1_A1
            Type
         |
            #variant InLineTemplate_A1_M1_A1_M1_A2
            Signature
         }
         ":"
      }?
      #subphrase InLineTemplate_A1_M2
      {
         #variant InLineTemplate_A1_M2_A1
         DerivedRefWithParList
         ":="
      }?
      TemplateBody
   
   phrase DerivedRefWithParList
      #variant DerivedRefWithParList_A1
      "modifies"
      TemplateRefWithParList
   
   phrase TemplateActualParList
      #variant TemplateActualParList_A1
      "("
      #subphrase TemplateActualParList_A1_M2
      {
         #variant TemplateActualParList_A1_M2_A1
         #subphrase TemplateActualParList_A1_M2_A1_M1
         {
            #variant TemplateActualParList_A1_M2_A1_M1_A1
            TemplateInstanceActualPar
            #subphrase TemplateActualParList_A1_M2_A1_M1_A1_M2
            {
               #variant TemplateActualParList_A1_M2_A1_M1_A1_M2_A1
               ","
               TemplateInstanceActualPar
            }*
         }
      |
         #variant TemplateActualParList_A1_M2_A2
         #subphrase TemplateActualParList_A1_M2_A2_M1
         {
            #variant TemplateActualParList_A1_M2_A2_M1_A1
            TemplateInstanceAssignment
            #subphrase TemplateActualParList_A1_M2_A2_M1_A1_M2
            {
               #variant TemplateActualParList_A1_M2_A2_M1_A1_M2_A1
               ","
               TemplateInstanceAssignment
            }*
         }
      }?
      ")"
   
   phrase TemplateInstanceActualPar
      #variant TemplateInstanceActualPar_A1
      InLineTemplate
   |
      #variant TemplateInstanceActualPar_A2
      "-"
   
   phrase TemplateOps
      #variant TemplateOps_A1
      MatchOp
   |
      #variant TemplateOps_A2
      ValueofOp
   
   phrase MatchOp
      #variant MatchOp_A1
      "match"
      "("
      Expression
      ","
      InLineTemplate
      ")"
   
   phrase ValueofOp
      #variant ValueofOp_A1
      "valueof"
      "("
      InLineTemplate
      ")"
   
   phrase FunctionDef
      #variant FunctionDef_A1
      "function"
      Identifier
      #subphrase FunctionDef_A1_M3
      {
         #variant FunctionDef_A1_M3_A1
         FormalTypeParList
      }?
      "("
      #subphrase FunctionDef_A1_M5
      {
         #variant FunctionDef_A1_M5_A1
         FunctionFormalParList
      }?
      ")"
      #subphrase FunctionDef_A1_M7
      {
         #variant FunctionDef_A1_M7_A1
         RunsOnSpec
      }?
      #subphrase FunctionDef_A1_M8
      {
         #variant FunctionDef_A1_M8_A1
         ReturnType
      }?
      StatementBlock
   
   phrase FunctionFormalParList
      #variant FunctionFormalParList_A1
      FunctionFormalPar
      #subphrase FunctionFormalParList_A1_M2
      {
         #variant FunctionFormalParList_A1_M2_A1
         ","
         FunctionFormalPar
      }*
   
   phrase FunctionFormalPar
      #variant FunctionFormalPar_A1
      FormalValuePar
   |
      #variant FunctionFormalPar_A2
      FormalTimerPar
   |
      #variant FunctionFormalPar_A3
      FormalTemplatePar
   |
      #variant FunctionFormalPar_A4
      FormalPortPar
   
   phrase ReturnType
      #variant ReturnType_A1
      "return"
      #subphrase ReturnType_A1_M2
      {
         #variant ReturnType_A1_M2_A1
         "template"
      |
         #variant ReturnType_A1_M2_A2
         RestrictedTemplate
      }?
      Type
   
   phrase RunsOnSpec
      #variant RunsOnSpec_A1
      "runs"
      "on"
      #subphrase RunsOnSpec_A1_M3
      {
         #variant RunsOnSpec_A1_M3_A1
         ComponentType
      |
         #variant RunsOnSpec_A1_M3_A2
         "self"
      }
   
   phrase StatementBlock
      #variant StatementBlock_A1
      "{"
      #subphrase StatementBlock_A1_M2
      {
         #variant StatementBlock_A1_M2_A1
         FunctionDefList
      }?
      #subphrase StatementBlock_A1_M3
      {
         #variant StatementBlock_A1_M3_A1
         FunctionStatementList
      }?
      "}"
   
   phrase FunctionDefList
      #variant FunctionDefList_A1
      #subphrase FunctionDefList_A1_M1
      {
         #variant FunctionDefList_A1_M1_A1
         #subphrase FunctionDefList_A1_M1_A1_M1
         {
            #variant FunctionDefList_A1_M1_A1_M1_A1
            FunctionLocalDef
         |
            #variant FunctionDefList_A1_M1_A1_M1_A2
            FunctionLocalInst
         }
         #subphrase FunctionDefList_A1_M1_A1_M2
         {
            #variant FunctionDefList_A1_M1_A1_M2_A1
            WithStatement
         }?
         #subphrase FunctionDefList_A1_M1_A1_M3
         {
            #variant FunctionDefList_A1_M1_A1_M3_A1
            ";"
         }?
      }+
   
   phrase FunctionStatementList
      #variant FunctionStatementList_A1
      #subphrase FunctionStatementList_A1_M1
      {
         #variant FunctionStatementList_A1_M1_A1
         FunctionStatement
         #subphrase FunctionStatementList_A1_M1_A1_M2
         {
            #variant FunctionStatementList_A1_M1_A1_M2_A1
            ";"
         }?
      }+
   
   phrase FunctionLocalInst
      #variant FunctionLocalInst_A1
      VarInstance
   |
      #variant FunctionLocalInst_A2
      TimerInstance
   
   phrase FunctionLocalDef
      #variant FunctionLocalDef_A1
      ConstDef
   |
      #variant FunctionLocalDef_A2
      TemplateDef
   
   phrase FunctionStatement
      #variant FunctionStatement_A1
      ConfigurationStatements
   |
      #variant FunctionStatement_A2
      TimerStatements
   |
      #variant FunctionStatement_A3
      CommunicationStatements
   |
      #variant FunctionStatement_A4
      BasicStatements
   |
      #variant FunctionStatement_A5
      BehaviourStatements
   |
      #variant FunctionStatement_A6
      SetLocalVerdict
   |
      #variant FunctionStatement_A7
      SUTStatements
   |
      #variant FunctionStatement_A8
      TestcaseOperation
   |
      #variant FunctionStatement_A9
      RealtimeStatement
   |
      #variant FunctionStatement_A10
      AssertStatement
   |
      #variant FunctionStatement_A11
      WaitStatement
   |
      #variant FunctionStatement_A12
      ModeSpecification
   
   phrase FunctionInstance
      #variant FunctionInstance_A1
      FunctionRef
      #subphrase FunctionInstance_A1_M2
      {
         #variant FunctionInstance_A1_M2_A1
         ActualTypeParList
      }?
      "("
      #subphrase FunctionInstance_A1_M4
      {
         #variant FunctionInstance_A1_M4_A1
         FunctionActualParList
      }?
      ")"
   |
      #variant FunctionInstance_A2
      "apply"
      "("
      Primary
      "("
      #subphrase FunctionInstance_A2_M5
      {
         #variant FunctionInstance_A2_M5_A1
         FunctionActualParList
      }?
      ")"
      ")"
   
   phrase FunctionRef
      #variant FunctionRef_A1
      #subphrase FunctionRef_A1_M1
      {
         #variant FunctionRef_A1_M1_A1
         Identifier
         "."
      }?
      Identifier
   
   phrase FunctionActualParList
      #variant FunctionActualParList_A1
      #subphrase FunctionActualParList_A1_M1
      {
         #variant FunctionActualParList_A1_M1_A1
         FunctionActualPar
         #subphrase FunctionActualParList_A1_M1_A1_M2
         {
            #variant FunctionActualParList_A1_M1_A1_M2_A1
            ","
            FunctionActualPar
         }*
      }
   |
      #variant FunctionActualParList_A2
      #subphrase FunctionActualParList_A2_M1
      {
         #variant FunctionActualParList_A2_M1_A1
         FunctionActualParAssignment
         #subphrase FunctionActualParList_A2_M1_A1_M2
         {
            #variant FunctionActualParList_A2_M1_A1_M2_A1
            ","
            FunctionActualParAssignment
         }*
      }
   
   phrase FunctionActualPar
      #variant FunctionActualPar_A1
      ArrayIdentifierRef
   |
      #variant FunctionActualPar_A2
      InLineTemplate
   |
      #variant FunctionActualPar_A3
      ComponentRef
   |
      #variant FunctionActualPar_A4
      "-"
   
   phrase FunctionActualParAssignment
      #variant FunctionActualParAssignment_A1
      TemplateInstanceAssignment
   |
      #variant FunctionActualParAssignment_A2
      ComponentRefAssignment
   |
      #variant FunctionActualParAssignment_A3
      ArrayIdentifierRefAssignment
   
   phrase ArrayIdentifierRefAssignment
      #variant ArrayIdentifierRefAssignment_A1
      Identifier
      ":="
      ArrayIdentifierRef
   
   phrase SignatureDef
      #variant SignatureDef_A1
      "signature"
      Identifier
      #subphrase SignatureDef_A1_M3
      {
         #variant SignatureDef_A1_M3_A1
         FormalTypeParList
      }?
      "("
      #subphrase SignatureDef_A1_M5
      {
         #variant SignatureDef_A1_M5_A1
         SignatureFormalParList
      }?
      ")"
      #subphrase SignatureDef_A1_M7
      {
         #variant SignatureDef_A1_M7_A1
         ReturnType
      |
         #variant SignatureDef_A1_M7_A2
         "noblock"
      }?
      #subphrase SignatureDef_A1_M8
      {
         #variant SignatureDef_A1_M8_A1
         ExceptionSpec
      }?
   
   phrase SignatureFormalParList
      #variant SignatureFormalParList_A1
      FormalValuePar
      #subphrase SignatureFormalParList_A1_M2
      {
         #variant SignatureFormalParList_A1_M2_A1
         ","
         FormalValuePar
      }*
   
   phrase ExceptionSpec
      #variant ExceptionSpec_A1
      "exception"
      "("
      TypeList
      ")"
   
   phrase Signature
      #variant Signature_A1
      #subphrase Signature_A1_M1
      {
         #variant Signature_A1_M1_A1
         Identifier
         "."
      }?
      Identifier
      #subphrase Signature_A1_M3
      {
         #variant Signature_A1_M3_A1
         ActualTypeParList
      }?
   
   phrase TestcaseDef
      #variant TestcaseDef_A1
      "testcase"
      Identifier
      #subphrase TestcaseDef_A1_M3
      {
         #variant TestcaseDef_A1_M3_A1
         FormalTypeParList
      }?
      "("
      #subphrase TestcaseDef_A1_M5
      {
         #variant TestcaseDef_A1_M5_A1
         TemplateOrValueFormalParList
      }?
      ")"
      #subphrase TestcaseDef_A1_M7
      {
         #variant TestcaseDef_A1_M7_A1
         ConfigSpec
      |
         #variant TestcaseDef_A1_M7_A2
         ExecuteOnSpec
      }
      StatementBlock
   
   phrase ConfigSpec
      #variant ConfigSpec_A1
      RunsOnSpec
      #subphrase ConfigSpec_A1_M2
      {
         #variant ConfigSpec_A1_M2_A1
         SystemSpec
      }?
   
   phrase SystemSpec
      #variant SystemSpec_A1
      "system"
      ComponentType
   
   phrase TestcaseInstance
      #variant TestcaseInstance_A1
      "execute"
      "("
      #subphrase TestcaseInstance_A1_M3
      {
         #variant TestcaseInstance_A1_M3_A1
         ExtendedIdentifier
         #subphrase TestcaseInstance_A1_M3_A1_M2
         {
            #variant TestcaseInstance_A1_M3_A1_M2_A1
            ActualTypeParList
         }?
         "("
         #subphrase TestcaseInstance_A1_M3_A1_M4
         {
            #variant TestcaseInstance_A1_M3_A1_M4_A1
            TestcaseActualParList
         }?
         ")"
      |
         #variant TestcaseInstance_A1_M3_A2
         "apply"
         "("
         Primary
         "("
         #subphrase TestcaseInstance_A1_M3_A2_M5
         {
            #variant TestcaseInstance_A1_M3_A2_M5_A1
            TestcaseActualParList
         }?
         ")"
         ")"
      }
      #subphrase TestcaseInstance_A1_M4
      {
         #variant TestcaseInstance_A1_M4_A1
         ","
         #subphrase TestcaseInstance_A1_M4_A1_M2
         {
            #variant TestcaseInstance_A1_M4_A1_M2_A1
            Expression
         |
            #variant TestcaseInstance_A1_M4_A1_M2_A2
            "-"
         }
         #subphrase TestcaseInstance_A1_M4_A1_M3
         {
            #variant TestcaseInstance_A1_M4_A1_M3_A1
            ","
            SingleExpression
         }?
      }?
      ")"
   
   phrase TestcaseActualParList
      #variant TestcaseActualParList_A1
      #subphrase TestcaseActualParList_A1_M1
      {
         #variant TestcaseActualParList_A1_M1_A1
         TemplateInstanceActualPar
         #subphrase TestcaseActualParList_A1_M1_A1_M2
         {
            #variant TestcaseActualParList_A1_M1_A1_M2_A1
            ","
            TemplateInstanceActualPar
         }*
      }
   |
      #variant TestcaseActualParList_A2
      #subphrase TestcaseActualParList_A2_M1
      {
         #variant TestcaseActualParList_A2_M1_A1
         TemplateInstanceAssignment
         #subphrase TestcaseActualParList_A2_M1_A1_M2
         {
            #variant TestcaseActualParList_A2_M1_A1_M2_A1
            ","
            TemplateInstanceAssignment
         }*
      }
   
   phrase AltstepDef
      #variant AltstepDef_A1
      "altstep"
      Identifier
      #subphrase AltstepDef_A1_M3
      {
         #variant AltstepDef_A1_M3_A1
         FormalTypeParList
      }?
      "("
      #subphrase AltstepDef_A1_M5
      {
         #variant AltstepDef_A1_M5_A1
         FunctionFormalParList
      }?
      ")"
      #subphrase AltstepDef_A1_M7
      {
         #variant AltstepDef_A1_M7_A1
         RunsOnSpec
      }?
      "{"
      AltstepLocalDefList
      AltGuardList
      "}"
   
   phrase AltstepLocalDefList
      #variant AltstepLocalDefList_A1
      #subphrase AltstepLocalDefList_A1_M1
      {
         #variant AltstepLocalDefList_A1_M1_A1
         AltstepLocalDef
         #subphrase AltstepLocalDefList_A1_M1_A1_M2
         {
            #variant AltstepLocalDefList_A1_M1_A1_M2_A1
            WithStatement
         }?
         #subphrase AltstepLocalDefList_A1_M1_A1_M3
         {
            #variant AltstepLocalDefList_A1_M1_A1_M3_A1
            ";"
         }?
      }*
   
   phrase AltstepLocalDef
      #variant AltstepLocalDef_A1
      VarInstance
   |
      #variant AltstepLocalDef_A2
      TimerInstance
   |
      #variant AltstepLocalDef_A3
      ConstDef
   |
      #variant AltstepLocalDef_A4
      TemplateDef
   
   phrase AltstepInstance
      #variant AltstepInstance_A1
      ExtendedIdentifier
      #subphrase AltstepInstance_A1_M2
      {
         #variant AltstepInstance_A1_M2_A1
         ActualTypeParList
      }?
      "("
      #subphrase AltstepInstance_A1_M4
      {
         #variant AltstepInstance_A1_M4_A1
         FunctionActualParList
      }?
      ")"
   
   phrase ImportDef
      #variant ImportDef_A1
      "import"
      ImportFromSpec
      #subphrase ImportDef_A1_M3
      {
         #variant ImportDef_A1_M3_A1
         AllWithExcepts
      |
         #variant ImportDef_A1_M3_A2
         #subphrase ImportDef_A1_M3_A2_M1
         {
            #variant ImportDef_A1_M3_A2_M1_A1
            "{"
            ImportSpec
            "}"
         }
      }
   
   phrase AllWithExcepts
      #variant AllWithExcepts_A1
      "all"
      #subphrase AllWithExcepts_A1_M2
      {
         #variant AllWithExcepts_A1_M2_A1
         ExceptsDef
      }?
   
   phrase ExceptsDef
      #variant ExceptsDef_A1
      "except"
      "{"
      ExceptSpec
      "}"
   
   phrase ExceptSpec
      #variant ExceptSpec_A1
      #subphrase ExceptSpec_A1_M1
      {
         #variant ExceptSpec_A1_M1_A1
         ExceptElement
         #subphrase ExceptSpec_A1_M1_A1_M2
         {
            #variant ExceptSpec_A1_M1_A1_M2_A1
            ";"
         }?
      }*
   
   phrase ExceptElement
      #variant ExceptElement_A1
      ExceptGroupSpec
   |
      #variant ExceptElement_A2
      ExceptTypeDefSpec
   |
      #variant ExceptElement_A3
      ExceptTemplateSpec
   |
      #variant ExceptElement_A4
      ExceptConstSpec
   |
      #variant ExceptElement_A5
      ExceptTestcaseSpec
   |
      #variant ExceptElement_A6
      ExceptAltstepSpec
   |
      #variant ExceptElement_A7
      ExceptFunctionSpec
   |
      #variant ExceptElement_A8
      ExceptSignatureSpec
   |
      #variant ExceptElement_A9
      ExceptModuleParSpec
   
   phrase ExceptGroupSpec
      #variant ExceptGroupSpec_A1
      "group"
      #subphrase ExceptGroupSpec_A1_M2
      {
         #variant ExceptGroupSpec_A1_M2_A1
         QualifiedIdentifierList
      |
         #variant ExceptGroupSpec_A1_M2_A2
         "all"
      }
   
   phrase IdentifierListOrAll
      #variant IdentifierListOrAll_A1
      IdentifierList
   |
      #variant IdentifierListOrAll_A2
      "all"
   
   phrase ExceptTypeDefSpec
      #variant ExceptTypeDefSpec_A1
      "type"
      IdentifierListOrAll
   
   phrase ExceptTemplateSpec
      #variant ExceptTemplateSpec_A1
      "template"
      IdentifierListOrAll
   
   phrase ExceptConstSpec
      #variant ExceptConstSpec_A1
      "const"
      IdentifierListOrAll
   
   phrase ExceptTestcaseSpec
      #variant ExceptTestcaseSpec_A1
      "testcase"
      IdentifierListOrAll
   
   phrase ExceptAltstepSpec
      #variant ExceptAltstepSpec_A1
      "altstep"
      IdentifierListOrAll
   
   phrase ExceptFunctionSpec
      #variant ExceptFunctionSpec_A1
      "function"
      IdentifierListOrAll
   
   phrase ExceptSignatureSpec
      #variant ExceptSignatureSpec_A1
      "signature"
      IdentifierListOrAll
   
   phrase ExceptModuleParSpec
      #variant ExceptModuleParSpec_A1
      "modulepar"
      IdentifierListOrAll
   
   phrase ImportSpec
      #variant ImportSpec_A1
      #subphrase ImportSpec_A1_M1
      {
         #variant ImportSpec_A1_M1_A1
         ImportElement
         #subphrase ImportSpec_A1_M1_A1_M2
         {
            #variant ImportSpec_A1_M1_A1_M2_A1
            ";"
         }?
      }*
   
   phrase ImportElement
      #variant ImportElement_A1
      ImportGroupSpec
   |
      #variant ImportElement_A2
      ImportTypeDefSpec
   |
      #variant ImportElement_A3
      ImportTemplateSpec
   |
      #variant ImportElement_A4
      ImportConstSpec
   |
      #variant ImportElement_A5
      ImportTestcaseSpec
   |
      #variant ImportElement_A6
      ImportAltstepSpec
   |
      #variant ImportElement_A7
      ImportFunctionSpec
   |
      #variant ImportElement_A8
      ImportSignatureSpec
   |
      #variant ImportElement_A9
      ImportModuleParSpec
   |
      #variant ImportElement_A10
      ImportImportSpec
   
   phrase ImportFromSpec
      #variant ImportFromSpec_A1
      "from"
      ModuleId
      #subphrase ImportFromSpec_A1_M3
      {
         #variant ImportFromSpec_A1_M3_A1
         "recursive"
      }?
   
   phrase ImportGroupSpec
      #variant ImportGroupSpec_A1
      "group"
      #subphrase ImportGroupSpec_A1_M2
      {
         #variant ImportGroupSpec_A1_M2_A1
         GroupRefListWithExcept
      |
         #variant ImportGroupSpec_A1_M2_A2
         AllGroupsWithExcept
      }
   
   phrase GroupRefListWithExcept
      #variant GroupRefListWithExcept_A1
      QualifiedIdentifierWithExcept
      #subphrase GroupRefListWithExcept_A1_M2
      {
         #variant GroupRefListWithExcept_A1_M2_A1
         ","
         QualifiedIdentifierWithExcept
      }*
   
   phrase AllGroupsWithExcept
      #variant AllGroupsWithExcept_A1
      "all"
      #subphrase AllGroupsWithExcept_A1_M2
      {
         #variant AllGroupsWithExcept_A1_M2_A1
         "except"
         QualifiedIdentifierList
      }?
   
   phrase QualifiedIdentifierWithExcept
      #variant QualifiedIdentifierWithExcept_A1
      QualifiedIdentifier
      #subphrase QualifiedIdentifierWithExcept_A1_M2
      {
         #variant QualifiedIdentifierWithExcept_A1_M2_A1
         ExceptsDef
      }?
   
   phrase IdentifierListOrAllWithExcept
      #variant IdentifierListOrAllWithExcept_A1
      IdentifierList
   |
      #variant IdentifierListOrAllWithExcept_A2
      AllWithExcept
   
   phrase ImportTypeDefSpec
      #variant ImportTypeDefSpec_A1
      "type"
      IdentifierListOrAllWithExcept
   
   phrase AllWithExcept
      #variant AllWithExcept_A1
      "all"
      #subphrase AllWithExcept_A1_M2
      {
         #variant AllWithExcept_A1_M2_A1
         "except"
         IdentifierList
      }?
   
   phrase ImportTemplateSpec
      #variant ImportTemplateSpec_A1
      "template"
      IdentifierListOrAllWithExcept
   
   phrase ImportConstSpec
      #variant ImportConstSpec_A1
      "const"
      IdentifierListOrAllWithExcept
   
   phrase ImportAltstepSpec
      #variant ImportAltstepSpec_A1
      "altstep"
      IdentifierListOrAllWithExcept
   
   phrase ImportTestcaseSpec
      #variant ImportTestcaseSpec_A1
      "testcase"
      IdentifierListOrAllWithExcept
   
   phrase ImportFunctionSpec
      #variant ImportFunctionSpec_A1
      "function"
      IdentifierListOrAllWithExcept
   
   phrase ImportSignatureSpec
      #variant ImportSignatureSpec_A1
      "signature"
      IdentifierListOrAllWithExcept
   
   phrase ImportModuleParSpec
      #variant ImportModuleParSpec_A1
      "modulepar"
      IdentifierListOrAllWithExcept
   
   phrase ImportImportSpec
      #variant ImportImportSpec_A1
      "import"
      "all"
   
   phrase GroupDef
      #variant GroupDef_A1
      "group"
      Identifier
      "{"
      #subphrase GroupDef_A1_M4
      {
         #variant GroupDef_A1_M4_A1
         ModuleDefinitionsList
      }?
      "}"
   
   phrase ExtFunctionDef
      #variant ExtFunctionDef_A1
      "external"
      "function"
      Identifier
      "("
      #subphrase ExtFunctionDef_A1_M5
      {
         #variant ExtFunctionDef_A1_M5_A1
         FunctionFormalParList
      }?
      ")"
      #subphrase ExtFunctionDef_A1_M7
      {
         #variant ExtFunctionDef_A1_M7_A1
         ReturnType
      }?
   
   phrase ExtConstDef
      #variant ExtConstDef_A1
      "external"
      "const"
      Type
      IdentifierList
   
   phrase ModuleParDef
      #variant ModuleParDef_A1
      "modulepar"
      #subphrase ModuleParDef_A1_M2
      {
         #variant ModuleParDef_A1_M2_A1
         ModulePar
      |
         #variant ModuleParDef_A1_M2_A2
         #subphrase ModuleParDef_A1_M2_A2_M1
         {
            #variant ModuleParDef_A1_M2_A2_M1_A1
            "{"
            MultitypedModuleParList
            "}"
         }
      }
   
   phrase MultitypedModuleParList
      #variant MultitypedModuleParList_A1
      #subphrase MultitypedModuleParList_A1_M1
      {
         #variant MultitypedModuleParList_A1_M1_A1
         ModulePar
         #subphrase MultitypedModuleParList_A1_M1_A1_M2
         {
            #variant MultitypedModuleParList_A1_M1_A1_M2_A1
            ";"
         }?
      }*
   
   phrase ModulePar
      #variant ModulePar_A1
      Type
      ModuleParList
   
   phrase ModuleParList
      #variant ModuleParList_A1
      Identifier
      #subphrase ModuleParList_A1_M2
      {
         #variant ModuleParList_A1_M2_A1
         ":="
         ConstantExpression
      }?
      #subphrase ModuleParList_A1_M3
      {
         #variant ModuleParList_A1_M3_A1
         ","
         Identifier
         #subphrase ModuleParList_A1_M3_A1_M3
         {
            #variant ModuleParList_A1_M3_A1_M3_A1
            ":="
            ConstantExpression
         }?
      }*
   
   phrase FriendModuleDef
      #variant FriendModuleDef_A1
      "friend"
      "module"
      IdentifierList
      #subphrase FriendModuleDef_A1_M4
      {
         #variant FriendModuleDef_A1_M4_A1
         ";"
      }?
   
   phrase ModuleControlPart
      #variant ModuleControlPart_A1
      "control"
      "{"
      ModuleControlBody
      "}"
      #subphrase ModuleControlPart_A1_M5
      {
         #variant ModuleControlPart_A1_M5_A1
         WithStatement
      }?
      #subphrase ModuleControlPart_A1_M6
      {
         #variant ModuleControlPart_A1_M6_A1
         ";"
      }?
   
   phrase ModuleControlBody
      #variant ModuleControlBody_A1
      #subphrase ModuleControlBody_A1_M1
      {
         #variant ModuleControlBody_A1_M1_A1
         ControlStatementOrDefList
      }?
   
   phrase ControlStatementOrDefList
      #variant ControlStatementOrDefList_A1
      #subphrase ControlStatementOrDefList_A1_M1
      {
         #variant ControlStatementOrDefList_A1_M1_A1
         ControlStatementOrDef
         #subphrase ControlStatementOrDefList_A1_M1_A1_M2
         {
            #variant ControlStatementOrDefList_A1_M1_A1_M2_A1
            ";"
         }?
      }+
   
   phrase ControlStatementOrDef
      #variant ControlStatementOrDef_A1
      #subphrase ControlStatementOrDef_A1_M1
      {
         #variant ControlStatementOrDef_A1_M1_A1
         FunctionLocalDef
      |
         #variant ControlStatementOrDef_A1_M1_A2
         FunctionLocalInst
      }
      #subphrase ControlStatementOrDef_A1_M2
      {
         #variant ControlStatementOrDef_A1_M2_A1
         WithStatement
      }?
   |
      #variant ControlStatementOrDef_A2
      ControlStatement
   
   phrase ControlStatement
      #variant ControlStatement_A1
      TimerStatements
   |
      #variant ControlStatement_A2
      BasicStatements
   |
      #variant ControlStatement_A3
      BehaviourStatements
   |
      #variant ControlStatement_A4
      SUTStatements
   |
      #variant ControlStatement_A5
      "stop"
   |
      #variant ControlStatement_A6
      KillConfigStatement
   
   phrase VarInstance
      #variant VarInstance_A1
      "var"
      #subphrase VarInstance_A1_M2
      {
         #variant VarInstance_A1_M2_A1
         #subphrase VarInstance_A1_M2_A1_M1
         {
            #variant VarInstance_A1_M2_A1_M1_A1
            Type
            VarList
         }
      |
         #variant VarInstance_A1_M2_A2
         #subphrase VarInstance_A1_M2_A2_M1
         {
            #variant VarInstance_A1_M2_A2_M1_A1
            #subphrase VarInstance_A1_M2_A2_M1_A1_M1
            {
               #variant VarInstance_A1_M2_A2_M1_A1_M1_A1
               "template"
            |
               #variant VarInstance_A1_M2_A2_M1_A1_M1_A2
               RestrictedTemplate
            }
            Type
            TempVarList
         }
      }
   
   phrase VarList
      #variant VarList_A1
      SingleVarInstance
      #subphrase VarList_A1_M2
      {
         #variant VarList_A1_M2_A1
         ","
         SingleVarInstance
      }*
   
   phrase SingleVarInstance
      #variant SingleVarInstance_A1
      Identifier
      #subphrase SingleVarInstance_A1_M2
      {
         #variant SingleVarInstance_A1_M2_A1
         ArrayDef
      }?
      #subphrase SingleVarInstance_A1_M3
      {
         #variant SingleVarInstance_A1_M3_A1
         ":="
         Expression
      }?
   
   phrase TempVarList
      #variant TempVarList_A1
      SingleTempVarInstance
      #subphrase TempVarList_A1_M2
      {
         #variant TempVarList_A1_M2_A1
         ","
         SingleTempVarInstance
      }*
   
   phrase SingleTempVarInstance
      #variant SingleTempVarInstance_A1
      Identifier
      #subphrase SingleTempVarInstance_A1_M2
      {
         #variant SingleTempVarInstance_A1_M2_A1
         ArrayDef
      }?
      #subphrase SingleTempVarInstance_A1_M3
      {
         #variant SingleTempVarInstance_A1_M3_A1
         ":="
         TemplateBody
      }?
   
   phrase VariableRef
      #variant VariableRef_A1
      Identifier
      #subphrase VariableRef_A1_M2
      {
         #variant VariableRef_A1_M2_A1
         ExtendedFieldReference
      }?
   
   phrase TimerInstance
      #variant TimerInstance_A1
      "timer"
      VarList
   
   phrase ArrayIdentifierRef
      #variant ArrayIdentifierRef_A1
      Identifier
      #subphrase ArrayIdentifierRef_A1_M2
      {
         #variant ArrayIdentifierRef_A1_M2_A1
         ArrayOrBitRef
      }*
   
   phrase ConfigurationStatements
      #variant ConfigurationStatements_A1
      ConnectStatement
   |
      #variant ConfigurationStatements_A2
      MapStatement
   |
      #variant ConfigurationStatements_A3
      DisconnectStatement
   |
      #variant ConfigurationStatements_A4
      UnmapStatement
   |
      #variant ConfigurationStatements_A5
      DoneStatement
   |
      #variant ConfigurationStatements_A6
      KilledStatement
   |
      #variant ConfigurationStatements_A7
      StartTCStatement
   |
      #variant ConfigurationStatements_A8
      StopTCStatement
   |
      #variant ConfigurationStatements_A9
      KillTCStatement
   
   phrase ConfigurationOps
      #variant ConfigurationOps_A1
      CreateOp
   |
      #variant ConfigurationOps_A2
      "self"
   |
      #variant ConfigurationOps_A3
      "system"
   |
      #variant ConfigurationOps_A4
      "mtc"
   |
      #variant ConfigurationOps_A5
      RunningOp
   |
      #variant ConfigurationOps_A6
      AliveOp
   
   phrase CreateOp
      #variant CreateOp_A1
      ComponentType
      "."
      "create"
      #subphrase CreateOp_A1_M4
      {
         #variant CreateOp_A1_M4_A1
         "("
         #subphrase CreateOp_A1_M4_A1_M2
         {
            #variant CreateOp_A1_M4_A1_M2_A1
            SingleExpression
         |
            #variant CreateOp_A1_M4_A1_M2_A2
            "-"
         }
         #subphrase CreateOp_A1_M4_A1_M3
         {
            #variant CreateOp_A1_M4_A1_M3_A1
            ","
            SingleExpression
         }?
         ")"
      }?
      #subphrase CreateOp_A1_M5
      {
         #variant CreateOp_A1_M5_A1
         "alive"
      |
         #variant CreateOp_A1_M5_A2
         "static"
      }?
   
   phrase DoneStatement
      #variant DoneStatement_A1
      ComponentId
      "."
      "done"
   
   phrase KilledStatement
      #variant KilledStatement_A1
      ComponentId
      "."
      "killed"
   
   phrase ComponentId
      #variant ComponentId_A1
      ComponentOrDefaultReference
   |
      #variant ComponentId_A2
      #subphrase ComponentId_A2_M1
      {
         #variant ComponentId_A2_M1_A1
         "any"
      |
         #variant ComponentId_A2_M1_A2
         "all"
      }
      "component"
   
   phrase RunningOp
      #variant RunningOp_A1
      ComponentId
      "."
      "running"
   
   phrase AliveOp
      #variant AliveOp_A1
      ComponentId
      "."
      "alive"
   
   phrase ConnectStatement
      #variant ConnectStatement_A1
      "connect"
      SingleConnectionSpec
      #subphrase ConnectStatement_A1_M3
      {
         #variant ConnectStatement_A1_M3_A1
         "static"
      }?
   
   phrase SingleConnectionSpec
      #variant SingleConnectionSpec_A1
      "("
      PortRef
      ","
      PortRef
      ")"
   
   phrase PortRef
      #variant PortRef_A1
      ComponentRef
      ":"
      ArrayIdentifierRef
   
   phrase ComponentRef
      #variant ComponentRef_A1
      ComponentOrDefaultReference
   |
      #variant ComponentRef_A2
      "system"
   |
      #variant ComponentRef_A3
      "self"
   |
      #variant ComponentRef_A4
      "mtc"
   
   phrase ComponentRefAssignment
      #variant ComponentRefAssignment_A1
      Identifier
      ":="
      ComponentRef
   
   phrase DisconnectStatement
      #variant DisconnectStatement_A1
      "disconnect"
      #subphrase DisconnectStatement_A1_M2
      {
         #variant DisconnectStatement_A1_M2_A1
         SingleConnectionSpec
      |
         #variant DisconnectStatement_A1_M2_A2
         AllConnectionsSpec
      |
         #variant DisconnectStatement_A1_M2_A3
         AllPortsSpec
      |
         #variant DisconnectStatement_A1_M2_A4
         AllCompsAllPortsSpec
      }?
   
   phrase AllConnectionsSpec
      #variant AllConnectionsSpec_A1
      "("
      PortRef
      ")"
   
   phrase AllPortsSpec
      #variant AllPortsSpec_A1
      "("
      ComponentRef
      ":"
      "all"
      "port"
      ")"
   
   phrase AllCompsAllPortsSpec
      #variant AllCompsAllPortsSpec_A1
      "("
      "all"
      "component"
      ":"
      "all"
      "port"
      ")"
   
   phrase MapStatement
      #variant MapStatement_A1
      "map"
      SingleConnectionSpec
      #subphrase MapStatement_A1_M3
      {
         #variant MapStatement_A1_M3_A1
         ParamClause
      }?
      #subphrase MapStatement_A1_M4
      {
         #variant MapStatement_A1_M4_A1
         "static"
      }?
   
   phrase ParamClause
      #variant ParamClause_A1
      "param"
      FunctionActualParList
   
   phrase UnmapStatement
      #variant UnmapStatement_A1
      "unmap"
      #subphrase UnmapStatement_A1_M2
      {
         #variant UnmapStatement_A1_M2_A1
         SingleConnectionSpec
         #subphrase UnmapStatement_A1_M2_A1_M2
         {
            #variant UnmapStatement_A1_M2_A1_M2_A1
            ParamClause
         }?
      |
         #variant UnmapStatement_A1_M2_A2
         AllConnectionsSpec
         #subphrase UnmapStatement_A1_M2_A2_M2
         {
            #variant UnmapStatement_A1_M2_A2_M2_A1
            ParamClause
         }?
      |
         #variant UnmapStatement_A1_M2_A3
         AllPortsSpec
      |
         #variant UnmapStatement_A1_M2_A4
         AllCompsAllPortsSpec
      }?
   
   phrase StartTCStatement
      #variant StartTCStatement_A1
      ComponentOrDefaultReference
      "."
      "start"
      "("
      FunctionInstance
      ")"
   
   phrase StopTCStatement
      #variant StopTCStatement_A1
      "stop"
   |
      #variant StopTCStatement_A2
      #subphrase StopTCStatement_A2_M1
      {
         #variant StopTCStatement_A2_M1_A1
         ComponentReferenceOrLiteral
      |
         #variant StopTCStatement_A2_M1_A2
         "all"
         "component"
      }
      "."
      "stop"
   
   phrase ComponentReferenceOrLiteral
      #variant ComponentReferenceOrLiteral_A1
      ComponentOrDefaultReference
   |
      #variant ComponentReferenceOrLiteral_A2
      "mtc"
   |
      #variant ComponentReferenceOrLiteral_A3
      "self"
   
   phrase KillTCStatement
      #variant KillTCStatement_A1
      "kill"
   |
      #variant KillTCStatement_A2
      #subphrase KillTCStatement_A2_M1
      {
         #variant KillTCStatement_A2_M1_A1
         #subphrase KillTCStatement_A2_M1_A1_M1
         {
            #variant KillTCStatement_A2_M1_A1_M1_A1
            ComponentReferenceOrLiteral
         |
            #variant KillTCStatement_A2_M1_A1_M1_A2
            "all"
            "component"
         }
         "."
         "kill"
      }
   
   phrase ComponentOrDefaultReference
      #variant ComponentOrDefaultReference_A1
      VariableRef
   |
      #variant ComponentOrDefaultReference_A2
      FunctionInstance
   
   phrase CommunicationStatements
      #variant CommunicationStatements_A1
      SendStatement
   |
      #variant CommunicationStatements_A2
      CallStatement
   |
      #variant CommunicationStatements_A3
      ReplyStatement
   |
      #variant CommunicationStatements_A4
      RaiseStatement
   |
      #variant CommunicationStatements_A5
      ReceiveStatement
   |
      #variant CommunicationStatements_A6
      TriggerStatement
   |
      #variant CommunicationStatements_A7
      GetCallStatement
   |
      #variant CommunicationStatements_A8
      GetReplyStatement
   |
      #variant CommunicationStatements_A9
      CatchStatement
   |
      #variant CommunicationStatements_A10
      CheckStatement
   |
      #variant CommunicationStatements_A11
      ClearStatement
   |
      #variant CommunicationStatements_A12
      StartStatement
   |
      #variant CommunicationStatements_A13
      StopStatement
   |
      #variant CommunicationStatements_A14
      HaltStatement
   |
      #variant CommunicationStatements_A15
      CheckStateStatement
   |
      #variant CommunicationStatements_A16
      StreamEvalStatements
   
   phrase SendStatement
      #variant SendStatement_A1
      ArrayIdentifierRef
      "."
      PortSendOp
   
   phrase PortSendOp
      #variant PortSendOp_A1
      "send"
      "("
      InLineTemplate
      ")"
      #subphrase PortSendOp_A1_M5
      {
         #variant PortSendOp_A1_M5_A1
         ToClause
      }?
   
   phrase ToClause
      #variant ToClause_A1
      "to"
      #subphrase ToClause_A1_M2
      {
         #variant ToClause_A1_M2_A1
         InLineTemplate
      |
         #variant ToClause_A1_M2_A2
         AddressRefList
      |
         #variant ToClause_A1_M2_A3
         "all"
         "component"
      }
   
   phrase AddressRefList
      #variant AddressRefList_A1
      "("
      InLineTemplate
      #subphrase AddressRefList_A1_M3
      {
         #variant AddressRefList_A1_M3_A1
         ","
         InLineTemplate
      }*
      ")"
   
   phrase CallStatement
      #variant CallStatement_A1
      ArrayIdentifierRef
      "."
      PortCallOp
      #subphrase CallStatement_A1_M4
      {
         #variant CallStatement_A1_M4_A1
         PortCallBody
      }?
   
   phrase PortCallOp
      #variant PortCallOp_A1
      "call"
      "("
      CallParameters
      ")"
      #subphrase PortCallOp_A1_M5
      {
         #variant PortCallOp_A1_M5_A1
         ToClause
      }?
   
   phrase CallParameters
      #variant CallParameters_A1
      InLineTemplate
      #subphrase CallParameters_A1_M2
      {
         #variant CallParameters_A1_M2_A1
         ","
         CallTimerValue
      }?
   
   phrase CallTimerValue
      #variant CallTimerValue_A1
      Expression
   |
      #variant CallTimerValue_A2
      "nowait"
   
   phrase PortCallBody
      #variant PortCallBody_A1
      "{"
      CallBodyStatementList
      "}"
   
   phrase CallBodyStatementList
      #variant CallBodyStatementList_A1
      #subphrase CallBodyStatementList_A1_M1
      {
         #variant CallBodyStatementList_A1_M1_A1
         CallBodyStatement
         #subphrase CallBodyStatementList_A1_M1_A1_M2
         {
            #variant CallBodyStatementList_A1_M1_A1_M2_A1
            ";"
         }?
      }+
   
   phrase CallBodyStatement
      #variant CallBodyStatement_A1
      CallBodyGuard
      StatementBlock
   
   phrase CallBodyGuard
      #variant CallBodyGuard_A1
      AltGuardChar
      CallBodyOps
   
   phrase CallBodyOps
      #variant CallBodyOps_A1
      GetReplyStatement
   |
      #variant CallBodyOps_A2
      CatchStatement
   
   phrase ReplyStatement
      #variant ReplyStatement_A1
      ArrayIdentifierRef
      "."
      PortReplyOp
   
   phrase PortReplyOp
      #variant PortReplyOp_A1
      "reply"
      "("
      InLineTemplate
      #subphrase PortReplyOp_A1_M4
      {
         #variant PortReplyOp_A1_M4_A1
         ReplyValue
      }?
      ")"
      #subphrase PortReplyOp_A1_M6
      {
         #variant PortReplyOp_A1_M6_A1
         ToClause
      }?
   
   phrase ReplyValue
      #variant ReplyValue_A1
      "value"
      Expression
   
   phrase RaiseStatement
      #variant RaiseStatement_A1
      ArrayIdentifierRef
      "."
      PortRaiseOp
   
   phrase PortRaiseOp
      #variant PortRaiseOp_A1
      "raise"
      "("
      Signature
      ","
      InLineTemplate
      ")"
      #subphrase PortRaiseOp_A1_M7
      {
         #variant PortRaiseOp_A1_M7_A1
         ToClause
      }?
   
   phrase ReceiveStatement
      #variant ReceiveStatement_A1
      PortOrAny
      "."
      PortReceiveOp
   
   phrase PortOrAny
      #variant PortOrAny_A1
      ArrayIdentifierRef
   |
      #variant PortOrAny_A2
      "any"
      "port"
   
   phrase PortReceiveOp
      #variant PortReceiveOp_A1
      "receive"
      #subphrase PortReceiveOp_A1_M2
      {
         #variant PortReceiveOp_A1_M2_A1
         "("
         InLineTemplate
         ")"
      }?
      #subphrase PortReceiveOp_A1_M3
      {
         #variant PortReceiveOp_A1_M3_A1
         FromClause
      }?
      #subphrase PortReceiveOp_A1_M4
      {
         #variant PortReceiveOp_A1_M4_A1
         PortRedirect
      }?
   
   phrase FromClause
      #variant FromClause_A1
      "from"
      #subphrase FromClause_A1_M2
      {
         #variant FromClause_A1_M2_A1
         InLineTemplate
      |
         #variant FromClause_A1_M2_A2
         AddressRefList
      |
         #variant FromClause_A1_M2_A3
         "any"
         "component"
      }
   
   phrase PortRedirect
      #variant PortRedirect_A1
      "->"
      #subphrase PortRedirect_A1_M2
      {
         #variant PortRedirect_A1_M2_A1
         ValueSpec
         #subphrase PortRedirect_A1_M2_A1_M2
         {
            #variant PortRedirect_A1_M2_A1_M2_A1
            SenderSpec
         }?
         #subphrase PortRedirect_A1_M2_A1_M3
         {
            #variant PortRedirect_A1_M2_A1_M3_A1
            TimestampSpec
         }?
      |
         #variant PortRedirect_A1_M2_A2
         SenderSpec
         #subphrase PortRedirect_A1_M2_A2_M2
         {
            #variant PortRedirect_A1_M2_A2_M2_A1
            TimestampSpec
         }?
      |
         #variant PortRedirect_A1_M2_A3
         TimestampSpec
      }
   
   phrase ValueSpec
      #variant ValueSpec_A1
      "value"
      #subphrase ValueSpec_A1_M2
      {
         #variant ValueSpec_A1_M2_A1
         VariableRef
      |
         #variant ValueSpec_A1_M2_A2
         #subphrase ValueSpec_A1_M2_A2_M1
         {
            #variant ValueSpec_A1_M2_A2_M1_A1
            "("
            SingleValueSpec
            #subphrase ValueSpec_A1_M2_A2_M1_A1_M3
            {
               #variant ValueSpec_A1_M2_A2_M1_A1_M3_A1
               ","
               SingleValueSpec
            }*
            ")"
         }
      }
   
   phrase SingleValueSpec
      #variant SingleValueSpec_A1
      VariableRef
      #subphrase SingleValueSpec_A1_M2
      {
         #variant SingleValueSpec_A1_M2_A1
         ":="
         FieldReference
         ExtendedFieldReference
      }?
   
   phrase SenderSpec
      #variant SenderSpec_A1
      "sender"
      VariableRef
   
   phrase TriggerStatement
      #variant TriggerStatement_A1
      PortOrAny
      "."
      PortTriggerOp
   
   phrase PortTriggerOp
      #variant PortTriggerOp_A1
      "trigger"
      #subphrase PortTriggerOp_A1_M2
      {
         #variant PortTriggerOp_A1_M2_A1
         "("
         InLineTemplate
         ")"
      }?
      #subphrase PortTriggerOp_A1_M3
      {
         #variant PortTriggerOp_A1_M3_A1
         FromClause
      }?
      #subphrase PortTriggerOp_A1_M4
      {
         #variant PortTriggerOp_A1_M4_A1
         PortRedirect
      }?
   
   phrase GetCallStatement
      #variant GetCallStatement_A1
      PortOrAny
      "."
      PortGetCallOp
   
   phrase PortGetCallOp
      #variant PortGetCallOp_A1
      "getcall"
      #subphrase PortGetCallOp_A1_M2
      {
         #variant PortGetCallOp_A1_M2_A1
         "("
         InLineTemplate
         ")"
      }?
      #subphrase PortGetCallOp_A1_M3
      {
         #variant PortGetCallOp_A1_M3_A1
         FromClause
      }?
      #subphrase PortGetCallOp_A1_M4
      {
         #variant PortGetCallOp_A1_M4_A1
         PortRedirectWithParam
      }?
   
   phrase PortRedirectWithParam
      #variant PortRedirectWithParam_A1
      "->"
      RedirectWithParamSpec
   
   phrase RedirectWithParamSpec
      #variant RedirectWithParamSpec_A1
      ParamSpec
      #subphrase RedirectWithParamSpec_A1_M2
      {
         #variant RedirectWithParamSpec_A1_M2_A1
         SenderSpec
      }?
   |
      #variant RedirectWithParamSpec_A2
      SenderSpec
   
   phrase ParamSpec
      #variant ParamSpec_A1
      "param"
      ParamAssignmentList
   
   phrase ParamAssignmentList
      #variant ParamAssignmentList_A1
      "("
      #subphrase ParamAssignmentList_A1_M2
      {
         #variant ParamAssignmentList_A1_M2_A1
         AssignmentList
      |
         #variant ParamAssignmentList_A1_M2_A2
         VariableList
      }
      ")"
   
   phrase AssignmentList
      #variant AssignmentList_A1
      VariableAssignment
      #subphrase AssignmentList_A1_M2
      {
         #variant AssignmentList_A1_M2_A1
         ","
         VariableAssignment
      }*
   
   phrase VariableAssignment
      #variant VariableAssignment_A1
      VariableRef
      ":="
      Identifier
   
   phrase VariableList
      #variant VariableList_A1
      VariableEntry
      #subphrase VariableList_A1_M2
      {
         #variant VariableList_A1_M2_A1
         ","
         VariableEntry
      }*
   
   phrase VariableEntry
      #variant VariableEntry_A1
      VariableRef
   |
      #variant VariableEntry_A2
      "-"
   
   phrase GetReplyStatement
      #variant GetReplyStatement_A1
      PortOrAny
      "."
      PortGetReplyOp
   
   phrase PortGetReplyOp
      #variant PortGetReplyOp_A1
      "getreply"
      #subphrase PortGetReplyOp_A1_M2
      {
         #variant PortGetReplyOp_A1_M2_A1
         "("
         InLineTemplate
         #subphrase PortGetReplyOp_A1_M2_A1_M3
         {
            #variant PortGetReplyOp_A1_M2_A1_M3_A1
            ValueMatchSpec
         }?
         ")"
      }?
      #subphrase PortGetReplyOp_A1_M3
      {
         #variant PortGetReplyOp_A1_M3_A1
         FromClause
      }?
      #subphrase PortGetReplyOp_A1_M4
      {
         #variant PortGetReplyOp_A1_M4_A1
         PortRedirectWithValueAndParam
      }?
   
   phrase PortRedirectWithValueAndParam
      #variant PortRedirectWithValueAndParam_A1
      "->"
      RedirectWithValueAndParamSpec
   
   phrase RedirectWithValueAndParamSpec
      #variant RedirectWithValueAndParamSpec_A1
      ValueSpec
      #subphrase RedirectWithValueAndParamSpec_A1_M2
      {
         #variant RedirectWithValueAndParamSpec_A1_M2_A1
         ParamSpec
      }?
      #subphrase RedirectWithValueAndParamSpec_A1_M3
      {
         #variant RedirectWithValueAndParamSpec_A1_M3_A1
         SenderSpec
      }?
   |
      #variant RedirectWithValueAndParamSpec_A2
      RedirectWithParamSpec
   
   phrase ValueMatchSpec
      #variant ValueMatchSpec_A1
      "value"
      InLineTemplate
   
   phrase CheckStatement
      #variant CheckStatement_A1
      PortOrAny
      "."
      PortCheckOp
   
   phrase PortCheckOp
      #variant PortCheckOp_A1
      "check"
      #subphrase PortCheckOp_A1_M2
      {
         #variant PortCheckOp_A1_M2_A1
         "("
         CheckParameter
         ")"
      }?
   
   phrase CheckParameter
      #variant CheckParameter_A1
      CheckPortOpsPresent
   |
      #variant CheckParameter_A2
      FromClausePresent
   |
      #variant CheckParameter_A3
      RedirectPresent
   
   phrase FromClausePresent
      #variant FromClausePresent_A1
      FromClause
      #subphrase FromClausePresent_A1_M2
      {
         #variant FromClausePresent_A1_M2_A1
         "->"
         SenderSpec
      }?
   
   phrase RedirectPresent
      #variant RedirectPresent_A1
      "->"
      SenderSpec
   
   phrase CheckPortOpsPresent
      #variant CheckPortOpsPresent_A1
      PortReceiveOp
   |
      #variant CheckPortOpsPresent_A2
      PortGetCallOp
   |
      #variant CheckPortOpsPresent_A3
      PortGetReplyOp
   |
      #variant CheckPortOpsPresent_A4
      PortCatchOp
   
   phrase CatchStatement
      #variant CatchStatement_A1
      PortOrAny
      "."
      PortCatchOp
   
   phrase PortCatchOp
      #variant PortCatchOp_A1
      "catch"
      #subphrase PortCatchOp_A1_M2
      {
         #variant PortCatchOp_A1_M2_A1
         "("
         CatchOpParameter
         ")"
      }?
      #subphrase PortCatchOp_A1_M3
      {
         #variant PortCatchOp_A1_M3_A1
         FromClause
      }?
      #subphrase PortCatchOp_A1_M4
      {
         #variant PortCatchOp_A1_M4_A1
         PortRedirect
      }?
   
   phrase CatchOpParameter
      #variant CatchOpParameter_A1
      Signature
      ","
      InLineTemplate
   |
      #variant CatchOpParameter_A2
      "timeout"
   
   phrase ClearStatement
      #variant ClearStatement_A1
      PortOrAll
      "."
      "clear"
   
   phrase PortOrAll
      #variant PortOrAll_A1
      ArrayIdentifierRef
   |
      #variant PortOrAll_A2
      "all"
      "port"
   
   phrase StartStatement
      #variant StartStatement_A1
      PortOrAll
      "."
      "start"
   
   phrase StopStatement
      #variant StopStatement_A1
      PortOrAll
      "."
      "stop"
   
   phrase HaltStatement
      #variant HaltStatement_A1
      PortOrAll
      "."
      "halt"
   
   phrase CheckStateStatement
      #variant CheckStateStatement_A1
      PortOrAllAny
      "."
      "checkstate"
      "("
      SingleExpression
      ")"
   
   phrase PortOrAllAny
      #variant PortOrAllAny_A1
      PortOrAll
   |
      #variant PortOrAllAny_A2
      "any"
      "port"
   
   phrase TimerStatements
      #variant TimerStatements_A1
      StartTimerStatement
   |
      #variant TimerStatements_A2
      StopTimerStatement
   |
      #variant TimerStatements_A3
      TimeoutStatement
   
   phrase TimerOps
      #variant TimerOps_A1
      ReadTimerOp
   |
      #variant TimerOps_A2
      RunningTimerOp
   
   phrase StartTimerStatement
      #variant StartTimerStatement_A1
      ArrayIdentifierRef
      "."
      "start"
      #subphrase StartTimerStatement_A1_M4
      {
         #variant StartTimerStatement_A1_M4_A1
         "("
         Expression
         ")"
      }?
   
   phrase StopTimerStatement
      #variant StopTimerStatement_A1
      TimerRefOrAll
      "."
      "stop"
   
   phrase TimerRefOrAll
      #variant TimerRefOrAll_A1
      ArrayIdentifierRef
   |
      #variant TimerRefOrAll_A2
      "all"
      "timer"
   
   phrase ReadTimerOp
      #variant ReadTimerOp_A1
      ArrayIdentifierRef
      "."
      "read"
   
   phrase RunningTimerOp
      #variant RunningTimerOp_A1
      TimerRefOrAny
      "."
      "running"
   
   phrase TimeoutStatement
      #variant TimeoutStatement_A1
      TimerRefOrAny
      "."
      "timeout"
   
   phrase TimerRefOrAny
      #variant TimerRefOrAny_A1
      ArrayIdentifierRef
   |
      #variant TimerRefOrAny_A2
      #subphrase TimerRefOrAny_A2_M1
      {
         #variant TimerRefOrAny_A2_M1_A1
         "any"
         "timer"
      }
   
   phrase TestcaseOperation
      #variant TestcaseOperation_A1
      "testcase"
      "."
      "stop"
      #subphrase TestcaseOperation_A1_M4
      {
         #variant TestcaseOperation_A1_M4_A1
         "("
         #subphrase TestcaseOperation_A1_M4_A1_M2
         {
            #variant TestcaseOperation_A1_M4_A1_M2_A1
            #subphrase TestcaseOperation_A1_M4_A1_M2_A1_M1
            {
               #variant TestcaseOperation_A1_M4_A1_M2_A1_M1_A1
               FreeText
            |
               #variant TestcaseOperation_A1_M4_A1_M2_A1_M1_A2
               InLineTemplate
            }
            #subphrase TestcaseOperation_A1_M4_A1_M2_A1_M2
            {
               #variant TestcaseOperation_A1_M4_A1_M2_A1_M2_A1
               ","
            }?
         }*
         ")"
      }?
   
   phrase Type
      #variant Type_A1
      PredefinedType
   |
      #variant Type_A2
      ReferencedType
   
   phrase PredefinedType
      #variant PredefinedType_A1
      "bitstring"
   |
      #variant PredefinedType_A2
      "boolean"
   |
      #variant PredefinedType_A3
      "charstring"
   |
      #variant PredefinedType_A4
      UniversalCharString
   |
      #variant PredefinedType_A5
      "integer"
   |
      #variant PredefinedType_A6
      "octetstring"
   |
      #variant PredefinedType_A7
      "hexstring"
   |
      #variant PredefinedType_A8
      "verdicttype"
   |
      #variant PredefinedType_A9
      "float"
   |
      #variant PredefinedType_A10
      "address"
   |
      #variant PredefinedType_A11
      "default"
   |
      #variant PredefinedType_A12
      "anytype"
   |
      #variant PredefinedType_A13
      "configuration"
   
   phrase UniversalCharString
      #variant UniversalCharString_A1
      "universal"
      "charstring"
   
   phrase ReferencedType
      #variant ReferencedType_A1
      #subphrase ReferencedType_A1_M1
      {
         #variant ReferencedType_A1_M1_A1
         Identifier
         "."
      }?
      Identifier
      #subphrase ReferencedType_A1_M3
      {
         #variant ReferencedType_A1_M3_A1
         ActualTypeParList
      }?
      #subphrase ReferencedType_A1_M4
      {
         #variant ReferencedType_A1_M4_A1
         TypeActualParList
      }?
      #subphrase ReferencedType_A1_M5
      {
         #variant ReferencedType_A1_M5_A1
         ExtendedFieldReference
      }?
   
   phrase ArrayDef
      #variant ArrayDef_A1
      #subphrase ArrayDef_A1_M1
      {
         #variant ArrayDef_A1_M1_A1
         "["
         SingleExpression
         #subphrase ArrayDef_A1_M1_A1_M3
         {
            #variant ArrayDef_A1_M1_A1_M3_A1
            ".."
            SingleExpression
         }?
         "]"
      }+
   
   phrase Value
      #variant Value_A1
      PredefinedValue
   |
      #variant Value_A2
      ReferencedValue
   
   phrase PredefinedValue
      #variant PredefinedValue_A1
      Bstring
   |
      #variant PredefinedValue_A2
      BooleanValue
   |
      #variant PredefinedValue_A3
      CharStringValue
   |
      #variant PredefinedValue_A4
      Number
   |
      #variant PredefinedValue_A5
      Ostring
   |
      #variant PredefinedValue_A6
      Hstring
   |
      #variant PredefinedValue_A7
      VerdictTypeValue
   |
      #variant PredefinedValue_A8
      FloatValue
   |
      #variant PredefinedValue_A9
      "omit"
   |
      #variant PredefinedValue_A10
      "_xxx_"
      BehaviourValue
   
   phrase BooleanValue
      #variant BooleanValue_A1
      "true"
   |
      #variant BooleanValue_A2
      "false"
   
   phrase VerdictTypeValue
      #variant VerdictTypeValue_A1
      "pass"
   |
      #variant VerdictTypeValue_A2
      "fail"
   |
      #variant VerdictTypeValue_A3
      "inconc"
   |
      #variant VerdictTypeValue_A4
      "none"
   |
      #variant VerdictTypeValue_A5
      "error"
   
   phrase CharStringValue
      #variant CharStringValue_A1
      Cstring
   |
      #variant CharStringValue_A2
      Quadruple
   
   phrase Quadruple
      #variant Quadruple_A1
      "char"
      "("
      Number
      ","
      Number
      ","
      Number
      ","
      Number
      ")"
   
   phrase FloatValue
      #variant FloatValue_A1
      FLOATVALUE
   
   phrase ReferencedValue
      #variant ReferencedValue_A1
      ExtendedIdentifier
      #subphrase ReferencedValue_A1_M2
      {
         #variant ReferencedValue_A1_M2_A1
         ExtendedFieldReference
      }?
   
   phrase Number
      #variant Number_A1
      NUMBER
   
   phrase Bstring
      #variant Bstring_A1
      BSTRING
   
   phrase Hstring
      #variant Hstring_A1
      HSTRING
   
   phrase Ostring
      #variant Ostring_A1
      OSTRING
   
   phrase Cstring
      #variant Cstring_A1
      CSTRING
   
   token Identifier( -> string)
      <<<[A-Za-z][A-Za-z0-9_]*>>>
   
   phrase FreeText
      #variant FreeText_A1
      FREETEXT
   
   phrase FormalValuePar
      #variant FormalValuePar_A1
      #subphrase FormalValuePar_A1_M1
      {
         #variant FormalValuePar_A1_M1_A1
         #subphrase FormalValuePar_A1_M1_A1_M1
         {
            #variant FormalValuePar_A1_M1_A1_M1_A1
            "in"
         |
            #variant FormalValuePar_A1_M1_A1_M1_A2
            "inout"
         |
            #variant FormalValuePar_A1_M1_A1_M1_A3
            "out"
         }
      }?
      Type
      Identifier
      #subphrase FormalValuePar_A1_M4
      {
         #variant FormalValuePar_A1_M4_A1
         ":="
         #subphrase FormalValuePar_A1_M4_A1_M2
         {
            #variant FormalValuePar_A1_M4_A1_M2_A1
            Expression
         |
            #variant FormalValuePar_A1_M4_A1_M2_A2
            "-"
         }
      }?
   
   phrase FormalPortPar
      #variant FormalPortPar_A1
      #subphrase FormalPortPar_A1_M1
      {
         #variant FormalPortPar_A1_M1_A1
         "inout"
      }?
      Identifier
      Identifier
   
   phrase FormalTimerPar
      #variant FormalTimerPar_A1
      #subphrase FormalTimerPar_A1_M1
      {
         #variant FormalTimerPar_A1_M1_A1
         "out"
      }?
      "timer"
      Identifier
   
   phrase FormalTemplatePar
      #variant FormalTemplatePar_A1
      #subphrase FormalTemplatePar_A1_M1
      {
         #variant FormalTemplatePar_A1_M1_A1
         #subphrase FormalTemplatePar_A1_M1_A1_M1
         {
            #variant FormalTemplatePar_A1_M1_A1_M1_A1
            "in"
         |
            #variant FormalTemplatePar_A1_M1_A1_M1_A2
            "out"
         |
            #variant FormalTemplatePar_A1_M1_A1_M1_A3
            "inout"
         }
      }?
      #subphrase FormalTemplatePar_A1_M2
      {
         #variant FormalTemplatePar_A1_M2_A1
         "template"
      |
         #variant FormalTemplatePar_A1_M2_A2
         RestrictedTemplate
      }
      Type
      Identifier
      #subphrase FormalTemplatePar_A1_M5
      {
         #variant FormalTemplatePar_A1_M5_A1
         ":="
         #subphrase FormalTemplatePar_A1_M5_A1_M2
         {
            #variant FormalTemplatePar_A1_M5_A1_M2_A1
            InLineTemplate
         |
            #variant FormalTemplatePar_A1_M5_A1_M2_A2
            "-"
         }
      }?
   
   phrase RestrictedTemplate
      #variant RestrictedTemplate_A1
      "omit"
   |
      #variant RestrictedTemplate_A2
      #subphrase RestrictedTemplate_A2_M1
      {
         #variant RestrictedTemplate_A2_M1_A1
         "template"
         TemplateRestriction
      }
   
   phrase TemplateRestriction
      #variant TemplateRestriction_A1
      "("
      #subphrase TemplateRestriction_A1_M2
      {
         #variant TemplateRestriction_A1_M2_A1
         "omit"
      |
         #variant TemplateRestriction_A1_M2_A2
         "value"
      |
         #variant TemplateRestriction_A1_M2_A3
         "present"
      }
      ")"
   
   phrase WithStatement
      #variant WithStatement_A1
      "with"
      WithAttribList
   
   phrase WithAttribList
      #variant WithAttribList_A1
      "{"
      MultiWithAttrib
      "}"
   
   phrase MultiWithAttrib
      #variant MultiWithAttrib_A1
      #subphrase MultiWithAttrib_A1_M1
      {
         #variant MultiWithAttrib_A1_M1_A1
         SingleWithAttrib
         #subphrase MultiWithAttrib_A1_M1_A1_M2
         {
            #variant MultiWithAttrib_A1_M1_A1_M2_A1
            ";"
         }?
      }*
   
   phrase SingleWithAttrib
      #variant SingleWithAttrib_A1
      AttribKeyword
      #subphrase SingleWithAttrib_A1_M2
      {
         #variant SingleWithAttrib_A1_M2_A1
         "override"
      }?
      #subphrase SingleWithAttrib_A1_M3
      {
         #variant SingleWithAttrib_A1_M3_A1
         AttribQualifier
      }?
      FreeText
   
   phrase AttribKeyword
      #variant AttribKeyword_A1
      "encode"
   |
      #variant AttribKeyword_A2
      "variant"
   |
      #variant AttribKeyword_A3
      "display"
   |
      #variant AttribKeyword_A4
      "extension"
   |
      #variant AttribKeyword_A5
      "optional"
   |
      #variant AttribKeyword_A6
      "stepsize"
   
   phrase AttribQualifier
      #variant AttribQualifier_A1
      "("
      DefOrFieldRefList
      ")"
   
   phrase DefOrFieldRefList
      #variant DefOrFieldRefList_A1
      DefOrFieldRef
      #subphrase DefOrFieldRefList_A1_M2
      {
         #variant DefOrFieldRefList_A1_M2_A1
         ","
         DefOrFieldRef
      }*
   
   phrase DefOrFieldRef
      #variant DefOrFieldRef_A1
      #subphrase DefOrFieldRef_A1_M1
      {
         #variant DefOrFieldRef_A1_M1_A1
         #subphrase DefOrFieldRef_A1_M1_A1_M1
         {
            #variant DefOrFieldRef_A1_M1_A1_M1_A1
            FieldReference
         |
            #variant DefOrFieldRef_A1_M1_A1_M1_A2
            "["
            "-"
            "]"
         }
         #subphrase DefOrFieldRef_A1_M1_A1_M2
         {
            #variant DefOrFieldRef_A1_M1_A1_M2_A1
            ExtendedFieldReference
         }?
      }
   |
      #variant DefOrFieldRef_A2
      AllRef
   
   phrase QualifiedIdentifier
      #variant QualifiedIdentifier_A1
      #subphrase QualifiedIdentifier_A1_M1
      {
         #variant QualifiedIdentifier_A1_M1_A1
         Identifier
         "."
      }*
      Identifier
   
   phrase AllRef
      #variant AllRef_A1
      #subphrase AllRef_A1_M1
      {
         #variant AllRef_A1_M1_A1
         "group"
         "all"
         #subphrase AllRef_A1_M1_A1_M3
         {
            #variant AllRef_A1_M1_A1_M3_A1
            "except"
            "{"
            QualifiedIdentifierList
            "}"
         }?
      }
   |
      #variant AllRef_A2
      #subphrase AllRef_A2_M1
      {
         #variant AllRef_A2_M1_A1
         #subphrase AllRef_A2_M1_A1_M1
         {
            #variant AllRef_A2_M1_A1_M1_A1
            "type"
         |
            #variant AllRef_A2_M1_A1_M1_A2
            "template"
         |
            #variant AllRef_A2_M1_A1_M1_A3
            "const"
         |
            #variant AllRef_A2_M1_A1_M1_A4
            "altstep"
         |
            #variant AllRef_A2_M1_A1_M1_A5
            "testcase"
         |
            #variant AllRef_A2_M1_A1_M1_A6
            "function"
         |
            #variant AllRef_A2_M1_A1_M1_A7
            "signature"
         |
            #variant AllRef_A2_M1_A1_M1_A8
            "modulepar"
         }
         "all"
         #subphrase AllRef_A2_M1_A1_M3
         {
            #variant AllRef_A2_M1_A1_M3_A1
            "except"
            "{"
            IdentifierList
            "}"
         }?
      }
   
   phrase BehaviourStatements
      #variant BehaviourStatements_A1
      TestcaseInstance
   |
      #variant BehaviourStatements_A2
      FunctionInstance
   |
      #variant BehaviourStatements_A3
      ReturnStatement
   |
      #variant BehaviourStatements_A4
      AltConstruct
   |
      #variant BehaviourStatements_A5
      InterleavedConstruct
   |
      #variant BehaviourStatements_A6
      LabelStatement
   |
      #variant BehaviourStatements_A7
      GotoStatement
   |
      #variant BehaviourStatements_A8
      "repeat"
   |
      #variant BehaviourStatements_A9
      DeactivateStatement
   |
      #variant BehaviourStatements_A10
      AltstepInstance
   |
      #variant BehaviourStatements_A11
      ActivateOp
   |
      #variant BehaviourStatements_A12
      "break"
   |
      #variant BehaviourStatements_A13
      "continue"
   
   phrase SetLocalVerdict
      #variant SetLocalVerdict_A1
      "setverdict"
      "("
      SingleExpression
      #subphrase SetLocalVerdict_A1_M4
      {
         #variant SetLocalVerdict_A1_M4_A1
         ","
         LogItem
      }*
      ")"
   
   phrase SUTStatements
      #variant SUTStatements_A1
      "action"
      "("
      ActionText
      #subphrase SUTStatements_A1_M4
      {
         #variant SUTStatements_A1_M4_A1
         StringOp
         ActionText
      }*
      ")"
   
   phrase ActionText
      #variant ActionText_A1
      FreeText
   |
      #variant ActionText_A2
      Expression
   
   phrase ReturnStatement
      #variant ReturnStatement_A1
      "return"
      #subphrase ReturnStatement_A1_M2
      {
         #variant ReturnStatement_A1_M2_A1
         Expression
      |
         #variant ReturnStatement_A1_M2_A2
         InLineTemplate
      }?
   
   phrase AltConstruct
      #variant AltConstruct_A1
      "alt"
      "{"
      AltGuardList
      "}"
   
   phrase AltGuardList
      #variant AltGuardList_A1
      #subphrase AltGuardList_A1_M1
      {
         #variant AltGuardList_A1_M1_A1
         GuardStatement
      |
         #variant AltGuardList_A1_M1_A2
         ElseStatement
         #subphrase AltGuardList_A1_M1_A2_M2
         {
            #variant AltGuardList_A1_M1_A2_M2_A1
            ";"
         }?
      }*
   
   phrase GuardStatement
      #variant GuardStatement_A1
      AltGuardChar
      #subphrase GuardStatement_A1_M2
      {
         #variant GuardStatement_A1_M2_A1
         AltstepInstance
         #subphrase GuardStatement_A1_M2_A1_M2
         {
            #variant GuardStatement_A1_M2_A1_M2_A1
            StatementBlock
         }?
      |
         #variant GuardStatement_A1_M2_A2
         GuardOp
         StatementBlock
      }
   
   phrase ElseStatement
      #variant ElseStatement_A1
      "["
      "else"
      "]"
      StatementBlock
   
   phrase AltGuardChar
      #variant AltGuardChar_A1
      "["
      #subphrase AltGuardChar_A1_M2
      {
         #variant AltGuardChar_A1_M2_A1
         BooleanExpression
      }?
      "]"
   
   phrase GuardOp
      #variant GuardOp_A1
      TimeoutStatement
   |
      #variant GuardOp_A2
      ReceiveStatement
   |
      #variant GuardOp_A3
      TriggerStatement
   |
      #variant GuardOp_A4
      GetCallStatement
   |
      #variant GuardOp_A5
      CatchStatement
   |
      #variant GuardOp_A6
      CheckStatement
   |
      #variant GuardOp_A7
      GetReplyStatement
   |
      #variant GuardOp_A8
      DoneStatement
   |
      #variant GuardOp_A9
      KilledStatement
   
   phrase InterleavedConstruct
      #variant InterleavedConstruct_A1
      "interleave"
      "{"
      InterleavedGuardList
      "}"
   
   phrase InterleavedGuardList
      #variant InterleavedGuardList_A1
      #subphrase InterleavedGuardList_A1_M1
      {
         #variant InterleavedGuardList_A1_M1_A1
         InterleavedGuardElement
         #subphrase InterleavedGuardList_A1_M1_A1_M2
         {
            #variant InterleavedGuardList_A1_M1_A1_M2_A1
            ";"
         }?
      }+
   
   phrase InterleavedGuardElement
      #variant InterleavedGuardElement_A1
      InterleavedGuard
      StatementBlock
   
   phrase InterleavedGuard
      #variant InterleavedGuard_A1
      "["
      "]"
      GuardOp
   
   phrase LabelStatement
      #variant LabelStatement_A1
      "label"
      Identifier
   
   phrase GotoStatement
      #variant GotoStatement_A1
      "goto"
      Identifier
   
   phrase ActivateOp
      #variant ActivateOp_A1
      "activate"
      "("
      AltstepInstance
      ")"
   
   phrase DeactivateStatement
      #variant DeactivateStatement_A1
      "deactivate"
      #subphrase DeactivateStatement_A1_M2
      {
         #variant DeactivateStatement_A1_M2_A1
         "("
         ComponentOrDefaultReference
         ")"
      }?
   
   phrase BasicStatements
      #variant BasicStatements_A1
      Assignment
   |
      #variant BasicStatements_A2
      LogStatement
   |
      #variant BasicStatements_A3
      LoopConstruct
   |
      #variant BasicStatements_A4
      ConditionalConstruct
   |
      #variant BasicStatements_A5
      SelectCaseConstruct
   |
      #variant BasicStatements_A6
      StatementBlock
   
   phrase Expression
      #variant Expression_A1
      SingleExpression
   |
      #variant Expression_A2
      CompoundExpression
   
   phrase CompoundExpression
      #variant CompoundExpression_A1
      FieldExpressionList
   |
      #variant CompoundExpression_A2
      ArrayExpression
   
   phrase FieldExpressionList
      #variant FieldExpressionList_A1
      "{"
      FieldExpressionSpec
      #subphrase FieldExpressionList_A1_M3
      {
         #variant FieldExpressionList_A1_M3_A1
         ","
         FieldExpressionSpec
      }*
      "}"
   
   phrase FieldExpressionSpec
      #variant FieldExpressionSpec_A1
      FieldReference
      ":="
      NotUsedOrExpression
   
   phrase ArrayExpression
      #variant ArrayExpression_A1
      "{"
      #subphrase ArrayExpression_A1_M2
      {
         #variant ArrayExpression_A1_M2_A1
         ArrayElementExpressionList
      }?
      "}"
   
   phrase ArrayElementExpressionList
      #variant ArrayElementExpressionList_A1
      NotUsedOrExpression
      #subphrase ArrayElementExpressionList_A1_M2
      {
         #variant ArrayElementExpressionList_A1_M2_A1
         ","
         NotUsedOrExpression
      }*
   
   phrase NotUsedOrExpression
      #variant NotUsedOrExpression_A1
      Expression
   |
      #variant NotUsedOrExpression_A2
      "-"
   
   phrase ConstantExpression
      #variant ConstantExpression_A1
      SingleExpression
   |
      #variant ConstantExpression_A2
      CompoundConstExpression
   
   phrase BooleanExpression
      #variant BooleanExpression_A1
      SingleExpression
   
   phrase CompoundConstExpression
      #variant CompoundConstExpression_A1
      FieldConstExpressionList
   |
      #variant CompoundConstExpression_A2
      ArrayConstExpression
   
   phrase FieldConstExpressionList
      #variant FieldConstExpressionList_A1
      "{"
      FieldConstExpressionSpec
      #subphrase FieldConstExpressionList_A1_M3
      {
         #variant FieldConstExpressionList_A1_M3_A1
         ","
         FieldConstExpressionSpec
      }*
      "}"
   
   phrase FieldConstExpressionSpec
      #variant FieldConstExpressionSpec_A1
      FieldReference
      ":="
      ConstantExpression
   
   phrase ArrayConstExpression
      #variant ArrayConstExpression_A1
      "{"
      #subphrase ArrayConstExpression_A1_M2
      {
         #variant ArrayConstExpression_A1_M2_A1
         ArrayElementConstExpressionList
      }?
      "}"
   
   phrase ArrayElementConstExpressionList
      #variant ArrayElementConstExpressionList_A1
      ConstantExpression
      #subphrase ArrayElementConstExpressionList_A1_M2
      {
         #variant ArrayElementConstExpressionList_A1_M2_A1
         ","
         ConstantExpression
      }*
   
   phrase Assignment
      #variant Assignment_A1
      VariableRef
      ":="
      #subphrase Assignment_A1_M3
      {
         #variant Assignment_A1_M3_A1
         Expression
      |
         #variant Assignment_A1_M3_A2
         TemplateBody
      }
   
   phrase SingleExpression
      #variant SingleExpression_A1
      XorExpression
      #subphrase SingleExpression_A1_M2
      {
         #variant SingleExpression_A1_M2_A1
         "or"
         XorExpression
      }*
   
   phrase XorExpression
      #variant XorExpression_A1
      AndExpression
      #subphrase XorExpression_A1_M2
      {
         #variant XorExpression_A1_M2_A1
         "xor"
         AndExpression
      }*
   
   phrase AndExpression
      #variant AndExpression_A1
      NotExpression
      #subphrase AndExpression_A1_M2
      {
         #variant AndExpression_A1_M2_A1
         "and"
         NotExpression
      }*
   
   phrase NotExpression
      #variant NotExpression_A1
      #subphrase NotExpression_A1_M1
      {
         #variant NotExpression_A1_M1_A1
         "not"
      }?
      EqualExpression
   
   phrase EqualExpression
      #variant EqualExpression_A1
      RelExpression
      #subphrase EqualExpression_A1_M2
      {
         #variant EqualExpression_A1_M2_A1
         EqualOp
         RelExpression
      }*
   
   phrase RelExpression
      #variant RelExpression_A1
      ShiftExpression
      #subphrase RelExpression_A1_M2
      {
         #variant RelExpression_A1_M2_A1
         RelOp
         ShiftExpression
      }?
   |
      #variant RelExpression_A2
      CompoundExpression
   
   phrase ShiftExpression
      #variant ShiftExpression_A1
      BitOrExpression
      #subphrase ShiftExpression_A1_M2
      {
         #variant ShiftExpression_A1_M2_A1
         ShiftOp
         BitOrExpression
      }*
   
   phrase BitOrExpression
      #variant BitOrExpression_A1
      BitXorExpression
      #subphrase BitOrExpression_A1_M2
      {
         #variant BitOrExpression_A1_M2_A1
         "or4b"
         BitXorExpression
      }*
   
   phrase BitXorExpression
      #variant BitXorExpression_A1
      BitAndExpression
      #subphrase BitXorExpression_A1_M2
      {
         #variant BitXorExpression_A1_M2_A1
         "xor4b"
         BitAndExpression
      }*
   
   phrase BitAndExpression
      #variant BitAndExpression_A1
      BitNotExpression
      #subphrase BitAndExpression_A1_M2
      {
         #variant BitAndExpression_A1_M2_A1
         "and4b"
         BitNotExpression
      }*
   
   phrase BitNotExpression
      #variant BitNotExpression_A1
      #subphrase BitNotExpression_A1_M1
      {
         #variant BitNotExpression_A1_M1_A1
         "not4b"
      }?
      AddExpression
   
   phrase AddExpression
      #variant AddExpression_A1
      MulExpression
      #subphrase AddExpression_A1_M2
      {
         #variant AddExpression_A1_M2_A1
         AddOp
         MulExpression
      }*
   
   phrase MulExpression
      #variant MulExpression_A1
      UnaryExpression
      #subphrase MulExpression_A1_M2
      {
         #variant MulExpression_A1_M2_A1
         MultiplyOp
         UnaryExpression
      }*
   |
      #variant MulExpression_A2
      CompoundExpression
   
   phrase UnaryExpression
      #variant UnaryExpression_A1
      #subphrase UnaryExpression_A1_M1
      {
         #variant UnaryExpression_A1_M1_A1
         UnaryOp
      }?
      Primary
   
   phrase Primary
      #variant Primary_A1
      OpCall
   |
      #variant Primary_A2
      Value
   |
      #variant Primary_A3
      "("
      SingleExpression
      ")"
   
   phrase ExtendedFieldReference
      #variant ExtendedFieldReference_A1
      #subphrase ExtendedFieldReference_A1_M1
      {
         #variant ExtendedFieldReference_A1_M1_A1
         #subphrase ExtendedFieldReference_A1_M1_A1_M1
         {
            #variant ExtendedFieldReference_A1_M1_A1_M1_A1
            #subphrase ExtendedFieldReference_A1_M1_A1_M1_A1_M1
            {
               #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1
               "."
               #subphrase ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2
               {
                  #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A1
                  Identifier
               |
                  #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A2
                  PredefinedType
               |
                  #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3
                  #subphrase ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1
                  {
                     #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1_A1
                     Identifier
                     #subphrase ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1_A1_M2
                     {
                        #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1_A1_M2_A1
                        ActualTypeParList
                     }?
                     #subphrase ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1_A1_M3
                     {
                        #variant ExtendedFieldReference_A1_M1_A1_M1_A1_M1_A1_M2_A3_M1_A1_M3_A1
                        TypeActualParList
                     }?
                  }
               }
            }
         |
            #variant ExtendedFieldReference_A1_M1_A1_M1_A2
            ArrayOrBitRef
         |
            #variant ExtendedFieldReference_A1_M1_A1_M1_A3
            #subphrase ExtendedFieldReference_A1_M1_A1_M1_A3_M1
            {
               #variant ExtendedFieldReference_A1_M1_A1_M1_A3_M1_A1
               "["
               "-"
               "]"
            }
         }
      }+
   
   phrase OpCall
      #variant OpCall_A1
      ConfigurationOps
   |
      #variant OpCall_A2
      "getverdict"
   |
      #variant OpCall_A3
      TimerOps
   |
      #variant OpCall_A4
      TestcaseInstance
   |
      #variant OpCall_A5
      FunctionInstance
      #subphrase OpCall_A5_M2
      {
         #variant OpCall_A5_M2_A1
         ExtendedFieldReference
      }?
   |
      #variant OpCall_A6
      TemplateOps
      #subphrase OpCall_A6_M2
      {
         #variant OpCall_A6_M2_A1
         ExtendedFieldReference
      }?
   |
      #variant OpCall_A7
      ActivateOp
   |
      #variant OpCall_A8
      ConfigurationInstance
   |
      #variant OpCall_A9
      "now"
   |
      #variant OpCall_A10
      StreamDataOps
   |
      #variant OpCall_A11
      StreamNavigationOps
   |
      #variant OpCall_A12
      ModeLocalOps
   
   phrase AddOp
      #variant AddOp_A1
      "+"
   |
      #variant AddOp_A2
      "-"
   |
      #variant AddOp_A3
      StringOp
   
   phrase MultiplyOp
      #variant MultiplyOp_A1
      "*"
   |
      #variant MultiplyOp_A2
      "/"
   |
      #variant MultiplyOp_A3
      "mod"
   |
      #variant MultiplyOp_A4
      "rem"
   
   phrase UnaryOp
      #variant UnaryOp_A1
      "+"
   |
      #variant UnaryOp_A2
      "-"
   
   phrase RelOp
      #variant RelOp_A1
      "<"
   |
      #variant RelOp_A2
      ">"
   |
      #variant RelOp_A3
      ">="
   |
      #variant RelOp_A4
      "<="
   
   phrase EqualOp
      #variant EqualOp_A1
      "=="
   |
      #variant EqualOp_A2
      "!="
   
   phrase StringOp
      #variant StringOp_A1
      "&"
   
   phrase ShiftOp
      #variant ShiftOp_A1
      "<<"
   |
      #variant ShiftOp_A2
      ">>"
   |
      #variant ShiftOp_A3
      "<@"
   |
      #variant ShiftOp_A4
      "@>"
   
   phrase LogStatement
      #variant LogStatement_A1
      "log"
      "("
      LogItem
      #subphrase LogStatement_A1_M4
      {
         #variant LogStatement_A1_M4_A1
         ","
         LogItem
      }*
      ")"
   
   phrase LogItem
      #variant LogItem_A1
      FreeText
   |
      #variant LogItem_A2
      InLineTemplate
   
   phrase LoopConstruct
      #variant LoopConstruct_A1
      ForStatement
   |
      #variant LoopConstruct_A2
      WhileStatement
   |
      #variant LoopConstruct_A3
      DoWhileStatement
   
   phrase ForStatement
      #variant ForStatement_A1
      "for"
      "("
      Initial
      ";"
      BooleanExpression
      ";"
      Assignment
      ")"
      StatementBlock
   
   phrase Initial
      #variant Initial_A1
      VarInstance
   |
      #variant Initial_A2
      Assignment
   
   phrase WhileStatement
      #variant WhileStatement_A1
      "while"
      "("
      BooleanExpression
      ")"
      StatementBlock
   
   phrase DoWhileStatement
      #variant DoWhileStatement_A1
      "do"
      StatementBlock
      "while"
      "("
      BooleanExpression
      ")"
   
   phrase ConditionalConstruct
      #variant ConditionalConstruct_A1
      "if"
      "("
      BooleanExpression
      ")"
      StatementBlock
      #subphrase ConditionalConstruct_A1_M6
      {
         #variant ConditionalConstruct_A1_M6_A1
         ElseIfClause
      }*
      #subphrase ConditionalConstruct_A1_M7
      {
         #variant ConditionalConstruct_A1_M7_A1
         ElseClause
      }?
   
   phrase ElseIfClause
      #variant ElseIfClause_A1
      "else"
      "if"
      "("
      BooleanExpression
      ")"
      StatementBlock
   
   phrase ElseClause
      #variant ElseClause_A1
      "else"
      StatementBlock
   
   phrase SelectCaseConstruct
      #variant SelectCaseConstruct_A1
      "select"
      "("
      SingleExpression
      ")"
      SelectCaseBody
   
   phrase SelectCaseBody
      #variant SelectCaseBody_A1
      "{"
      #subphrase SelectCaseBody_A1_M2
      {
         #variant SelectCaseBody_A1_M2_A1
         SelectCase
      }+
      "}"
   
   phrase SelectCase
      #variant SelectCase_A1
      "case"
      #subphrase SelectCase_A1_M2
      {
         #variant SelectCase_A1_M2_A1
         "("
         InLineTemplate
         #subphrase SelectCase_A1_M2_A1_M3
         {
            #variant SelectCase_A1_M2_A1_M3_A1
            ","
            InLineTemplate
         }*
         ")"
      |
         #variant SelectCase_A1_M2_A2
         "else"
      }
      StatementBlock
   
   phrase ExtendedIdentifier
      #variant ExtendedIdentifier_A1
      #subphrase ExtendedIdentifier_A1_M1
      {
         #variant ExtendedIdentifier_A1_M1_A1
         Identifier
         "."
      }?
      Identifier
   
   phrase IdentifierList
      #variant IdentifierList_A1
      Identifier
      #subphrase IdentifierList_A1_M2
      {
         #variant IdentifierList_A1_M2_A1
         ","
         Identifier
      }*
   
   phrase QualifiedIdentifierList
      #variant QualifiedIdentifierList_A1
      QualifiedIdentifier
      #subphrase QualifiedIdentifierList_A1_M2
      {
         #variant QualifiedIdentifierList_A1_M2_A1
         ","
         QualifiedIdentifier
      }*
   
   phrase TypeActualParList
      #variant TypeActualParList_A1
      #subphrase TypeActualParList_A1_M1
      {
         #variant TypeActualParList_A1_M1_A1
         "("
         TypeActualPar
         #subphrase TypeActualParList_A1_M1_A1_M3
         {
            #variant TypeActualParList_A1_M1_A1_M3_A1
            ","
            TypeActualPar
         }*
         ")"
      }
   |
      #variant TypeActualParList_A2
      #subphrase TypeActualParList_A2_M1
      {
         #variant TypeActualParList_A2_M1_A1
         "("
         TypeParAssignment
         #subphrase TypeActualParList_A2_M1_A1_M3
         {
            #variant TypeActualParList_A2_M1_A1_M3_A1
            ","
            TypeParAssignment
         }*
         ")"
      }
   
   phrase TypeActualPar
      #variant TypeActualPar_A1
      ConstantExpression
   |
      #variant TypeActualPar_A2
      TypeActualParIdentifier
      "{"
      UnionFieldDef
      #subphrase TypeActualPar_A2_M4
      {
         #variant TypeActualPar_A2_M4_A1
         ","
         UnionFieldDef
      }*
      "}"
      "{"
      EnumerationList
      "}"
   
   phrase FormalTypeParList
      #variant FormalTypeParList_A1
      "<"
      FormalTypePar
      #subphrase FormalTypeParList_A1_M3
      {
         #variant FormalTypeParList_A1_M3_A1
         ","
         FormalTypePar
      }*
      ">"
   
   phrase FormalTypePar
      #variant FormalTypePar_A1
      #subphrase FormalTypePar_A1_M1
      {
         #variant FormalTypePar_A1_M1_A1
         "in"
      }?
      #subphrase FormalTypePar_A1_M2
      {
         #variant FormalTypePar_A1_M2_A1
         Type
      |
         #variant FormalTypePar_A1_M2_A2
         "type"
      }?
      TypeParIdentifier
      #subphrase FormalTypePar_A1_M4
      {
         #variant FormalTypePar_A1_M4_A1
         ":="
         Type
      }?
   
   phrase TypeParIdentifier
      #variant TypeParIdentifier_A1
      Identifier
   
   phrase TypeActualParIdentifier
      #variant TypeActualParIdentifier_A1
      Identifier
   
   phrase TypeParAssignment
      #variant TypeParAssignment_A1
      TypeActualParIdentifier
      ":="
      TypeActualPar
   
   phrase ActualTypeParList
      #variant ActualTypeParList_A1
      #subphrase ActualTypeParList_A1_M1
      {
         #variant ActualTypeParList_A1_M1_A1
         "<"
         ActualTypePar
         #subphrase ActualTypeParList_A1_M1_A1_M3
         {
            #variant ActualTypeParList_A1_M1_A1_M3_A1
            ","
            ActualTypePar
         }*
         ">"
      }
   |
      #variant ActualTypeParList_A2
      #subphrase ActualTypeParList_A2_M1
      {
         #variant ActualTypeParList_A2_M1_A1
         "<"
         ActualTypeParAssignment
         #subphrase ActualTypeParList_A2_M1_A1_M3
         {
            #variant ActualTypeParList_A2_M1_A1_M3_A1
            ","
            ActualTypeParAssignment
         }*
         ">"
      }
   
   phrase ActualTypePar
      #variant ActualTypePar_A1
      Type
   |
      #variant ActualTypePar_A2
      "-"
   
   phrase ActualTypeParAssignment
      #variant ActualTypeParAssignment_A1
      TypeActualParIdentifier
      ":="
      ActualTypePar
   
   phrase StructDefFormalParList
      #variant StructDefFormalParList_A1
      "("
      StructDefFormalPar
      #subphrase StructDefFormalParList_A1_M3
      {
         #variant StructDefFormalParList_A1_M3_A1
         ","
         StructDefFormalPar
      }*
      ")"
   
   phrase StructDefFormalPar
      #variant StructDefFormalPar_A1
      FormalValuePar
   
   phrase BehaviourDef
      #variant BehaviourDef_A1
      #subphrase BehaviourDef_A1_M1
      {
         #variant BehaviourDef_A1_M1_A1
         "altstep"
         Identifier
         #subphrase BehaviourDef_A1_M1_A1_M3
         {
            #variant BehaviourDef_A1_M1_A1_M3_A1
            FormalTypeParList
         }?
         "("
         #subphrase BehaviourDef_A1_M1_A1_M5
         {
            #variant BehaviourDef_A1_M1_A1_M5_A1
            FunctionFormalParList
         }?
         ")"
         #subphrase BehaviourDef_A1_M1_A1_M7
         {
            #variant BehaviourDef_A1_M1_A1_M7_A1
            RunsOnSpec
         }?
      }
   |
      #variant BehaviourDef_A2
      #subphrase BehaviourDef_A2_M1
      {
         #variant BehaviourDef_A2_M1_A1
         "function"
         Identifier
         #subphrase BehaviourDef_A2_M1_A1_M3
         {
            #variant BehaviourDef_A2_M1_A1_M3_A1
            FormalTypeParList
         }?
         "("
         #subphrase BehaviourDef_A2_M1_A1_M5
         {
            #variant BehaviourDef_A2_M1_A1_M5_A1
            FunctionFormalParList
         }?
         ")"
         #subphrase BehaviourDef_A2_M1_A1_M7
         {
            #variant BehaviourDef_A2_M1_A1_M7_A1
            RunsOnSpec
         }?
         #subphrase BehaviourDef_A2_M1_A1_M8
         {
            #variant BehaviourDef_A2_M1_A1_M8_A1
            ReturnType
         }?
      }
   |
      #variant BehaviourDef_A3
      #subphrase BehaviourDef_A3_M1
      {
         #variant BehaviourDef_A3_M1_A1
         "testcase"
         Identifier
         #subphrase BehaviourDef_A3_M1_A1_M3
         {
            #variant BehaviourDef_A3_M1_A1_M3_A1
            FormalTypeParList
         }?
         "("
         #subphrase BehaviourDef_A3_M1_A1_M5
         {
            #variant BehaviourDef_A3_M1_A1_M5_A1
            TemplateOrValueFormalParList
         }?
         ")"
         ConfigSpec
      }
   
   phrase NestedBehaviourDef
      #variant NestedBehaviourDef_A1
      #subphrase NestedBehaviourDef_A1_M1
      {
         #variant NestedBehaviourDef_A1_M1_A1
         "altstep"
         "("
         #subphrase NestedBehaviourDef_A1_M1_A1_M3
         {
            #variant NestedBehaviourDef_A1_M1_A1_M3_A1
            FunctionFormalParList
         }?
         ")"
         #subphrase NestedBehaviourDef_A1_M1_A1_M5
         {
            #variant NestedBehaviourDef_A1_M1_A1_M5_A1
            RunsOnSpec
         }?
      }
   |
      #variant NestedBehaviourDef_A2
      #subphrase NestedBehaviourDef_A2_M1
      {
         #variant NestedBehaviourDef_A2_M1_A1
         "function"
         "("
         #subphrase NestedBehaviourDef_A2_M1_A1_M3
         {
            #variant NestedBehaviourDef_A2_M1_A1_M3_A1
            FunctionFormalParList
         }?
         ")"
         #subphrase NestedBehaviourDef_A2_M1_A1_M5
         {
            #variant NestedBehaviourDef_A2_M1_A1_M5_A1
            RunsOnSpec
         }?
         #subphrase NestedBehaviourDef_A2_M1_A1_M6
         {
            #variant NestedBehaviourDef_A2_M1_A1_M6_A1
            ReturnType
         }?
      }
   |
      #variant NestedBehaviourDef_A3
      #subphrase NestedBehaviourDef_A3_M1
      {
         #variant NestedBehaviourDef_A3_M1_A1
         "testcase"
         "("
         #subphrase NestedBehaviourDef_A3_M1_A1_M3
         {
            #variant NestedBehaviourDef_A3_M1_A1_M3_A1
            TemplateOrValueFormalParList
         }?
         ")"
         ConfigSpec
      }
   
   phrase BehaviourValue
      #variant BehaviourValue_A1
      #subphrase BehaviourValue_A1_M1
      {
         #variant BehaviourValue_A1_M1_A1
         Identifier
      }?
      "."
      Identifier
   |
      #variant BehaviourValue_A2
      "null"
   
   phrase ExecuteOnSpec
      #variant ExecuteOnSpec_A1
      "execute"
      "on"
      ConfigurationRef
   
   phrase ConfigurationDef
      #variant ConfigurationDef_A1
      "configuration"
      ConfigurationIdentifier
      "("
      #subphrase ConfigurationDef_A1_M4
      {
         #variant ConfigurationDef_A1_M4_A1
         TemplateOrValueFormalParList
      }?
      ")"
      ConfigSpec
      StatementBlock
   
   phrase ConfigurationIdentifier
      #variant ConfigurationIdentifier_A1
      Identifier
   
   phrase ConfigurationInstance
      #variant ConfigurationInstance_A1
      ConfigurationRef
      "("
      #subphrase ConfigurationInstance_A1_M3
      {
         #variant ConfigurationInstance_A1_M3_A1
         TestcaseActualParList
      }?
      ")"
   
   phrase ConfigurationRef
      #variant ConfigurationRef_A1
      #subphrase ConfigurationRef_A1_M1
      {
         #variant ConfigurationRef_A1_M1_A1
         Identifier
         "."
      }?
      ConfigurationIdentifier
   
   phrase KillConfigStatement
      #variant KillConfigStatement_A1
      ConfigurationReference
      "."
      "kill"
   
   phrase ConfigurationReference
      #variant ConfigurationReference_A1
      VariableRef
   |
      #variant ConfigurationReference_A2
      FunctionInstance
   
   phrase TimestampSpec
      #variant TimestampSpec_A1
      "timestamp"
      VariableRef
   
   phrase RealtimeStatement
      #variant RealtimeStatement_A1
      WaitStatement
   
   phrase WaitStatement
      #variant WaitStatement_A1
      "wait"
      #subphrase WaitStatement_A1_M2
      {
         #variant WaitStatement_A1_M2_A1
         SingleExpression
      }
   
   phrase StreamAttribs
      #variant StreamAttribs_A1
      "stream"
      "{"
      StreamDirection
      Type
      "}"
   
   phrase StreamDirection
      #variant StreamDirection_A1
      "in"
   |
      #variant StreamDirection_A2
      "out"
   
   phrase PortInitialValue
      #variant PortInitialValue_A1
      Expression
   
   phrase StreamDataOps
      #variant StreamDataOps_A1
      Identifier
      "."
      PortDataOp
   
   phrase PortDataOp
      #variant PortDataOp_A1
      "value"
   |
      #variant PortDataOp_A2
      "timestamp"
   |
      #variant PortDataOp_A3
      "delta"
   |
      #variant PortDataOp_A4
      PortHistoryOp
   |
      #variant PortDataOp_A5
      PortValuesOp
   
   phrase PortHistoryOp
      #variant PortHistoryOp_A1
      "history"
      #subphrase PortHistoryOp_A1_M2
      {
         #variant PortHistoryOp_A1_M2_A1
         "("
         StartValue
         #subphrase PortHistoryOp_A1_M2_A1_M3
         {
            #variant PortHistoryOp_A1_M2_A1_M3_A1
            ","
            EndValue
         }?
         ")"
      }?
   
   phrase StartValue
      #variant StartValue_A1
      Expression
   
   phrase EndValue
      #variant EndValue_A1
      Expression
   
   phrase PortValuesOp
      #variant PortValuesOp_A1
      "values"
      #subphrase PortValuesOp_A1_M2
      {
         #variant PortValuesOp_A1_M2_A1
         "("
         StartValue
         #subphrase PortValuesOp_A1_M2_A1_M3
         {
            #variant PortValuesOp_A1_M2_A1_M3_A1
            ","
            EndValue
         }?
         ")"
      }?
   
   phrase StreamNavigationOps
      #variant StreamNavigationOps_A1
      Identifier
      "."
      #subphrase StreamNavigationOps_A1_M3
      {
         #variant StreamNavigationOps_A1_M3_A1
         PortPrevOp
      |
         #variant StreamNavigationOps_A1_M3_A2
         PortAtOp
      }
      #subphrase StreamNavigationOps_A1_M4
      {
         #variant StreamNavigationOps_A1_M4_A1
         "."
         PortDataOp
      }?
   
   phrase PortPrevOp
      #variant PortPrevOp_A1
      "prev"
      #subphrase PortPrevOp_A1_M2
      {
         #variant PortPrevOp_A1_M2_A1
         "("
         IndexValue
         ")"
      }?
   
   phrase IndexValue
      #variant IndexValue_A1
      Expression
   
   phrase PortAtOp
      #variant PortAtOp_A1
      "at"
      #subphrase PortAtOp_A1_M2
      {
         #variant PortAtOp_A1_M2_A1
         "("
         TimeIndexValue
         ")"
      }?
   
   phrase TimeIndexValue
      #variant TimeIndexValue_A1
      Expression
   
   phrase ModeLocalOps
      #variant ModeLocalOps_A1
      "duration"
   |
      #variant ModeLocalOps_A2
      "finished"
   |
      #variant ModeLocalOps_A3
      "notinv"
   
   phrase StreamEvalStatements
      #variant StreamEvalStatements_A1
      Identifier
      "."
      #subphrase StreamEvalStatements_A1_M3
      {
         #variant StreamEvalStatements_A1_M3_A1
         PortApplyOp
      |
         #variant StreamEvalStatements_A1_M3_A2
         PortFindOp
      |
         #variant StreamEvalStatements_A1_M3_A3
         PortViolatesOp
      }
   
   phrase PortApplyOp
      #variant PortApplyOp_A1
      "apply"
      #subphrase PortApplyOp_A1_M2
      {
         #variant PortApplyOp_A1_M2_A1
         "("
         ApplyParameter
         ")"
      }?
   
   phrase ApplyParameter
      #variant ApplyParameter_A1
      TemplateInstance
   
   phrase PortFindOp
      #variant PortFindOp_A1
      "find"
      #subphrase PortFindOp_A1_M2
      {
         #variant PortFindOp_A1_M2_A1
         "("
         FindParameter
         ")"
      }?
   
   phrase FindParameter
      #variant FindParameter_A1
      TemplateInstance
   
   phrase PortViolatesOp
      #variant PortViolatesOp_A1
      "violates"
      #subphrase PortViolatesOp_A1_M2
      {
         #variant PortViolatesOp_A1_M2_A1
         "("
         ViolatesParameter
         ")"
      }?
   
   phrase ViolatesParameter
      #variant ViolatesParameter_A1
      TemplateInstance
   
   phrase AssertStatement
      #variant AssertStatement_A1
      "assert"
      #subphrase AssertStatement_A1_M2
      {
         #variant AssertStatement_A1_M2_A1
         "("
         AssertionList
         ")"
      }?
   
   phrase AssertionList
      #variant AssertionList_A1
      Expression
      #subphrase AssertionList_A1_M2
      {
         #variant AssertionList_A1_M2_A1
         ","
         Expression
      }*
   
   phrase ModeSpecification
      #variant ModeSpecification_A1
      #subphrase ModeSpecification_A1_M1
      {
         #variant ModeSpecification_A1_M1_A1
         BasicMode
      |
         #variant ModeSpecification_A1_M1_A2
         ComplexMode
      }
      #subphrase ModeSpecification_A1_M2
      {
         #variant ModeSpecification_A1_M2_A1
         UntilBlock
      }?
   
   phrase BasicMode
      #variant BasicMode_A1
      "cont"
      "{"
      #subphrase BasicMode_A1_M3
      {
         #variant BasicMode_A1_M3_A1
         VarInstance
      }*
      #subphrase BasicMode_A1_M4
      {
         #variant BasicMode_A1_M4_A1
         OnEntryBlock
      }?
      #subphrase BasicMode_A1_M5
      {
         #variant BasicMode_A1_M5_A1
         InvariantBlock
      }?
      #subphrase BasicMode_A1_M6
      {
         #variant BasicMode_A1_M6_A1
         BasicModeOp
      }*
      #subphrase BasicMode_A1_M7
      {
         #variant BasicMode_A1_M7_A1
         OnExitBlock
      }?
      "}"
   
   phrase OnEntryBlock
      #variant OnEntryBlock_A1
      "onentry"
      "{"
      StatementBlock
      "}"
   
   phrase InvariantBlock
      #variant InvariantBlock_A1
      "inv"
      "{"
      InvariantList
      "}"
   
   phrase InvariantList
      #variant InvariantList_A1
      #subphrase InvariantList_A1_M1
      {
         #variant InvariantList_A1_M1_A1
         BooleanExpression
         #subphrase InvariantList_A1_M1_A1_M2
         {
            #variant InvariantList_A1_M1_A1_M2_A1
            ","
            BooleanExpression
         }*
      }?
   
   phrase BasicModeOp
      #variant BasicModeOp_A1
      Assignment
   |
      #variant BasicModeOp_A2
      AssertStatement
   
   phrase OnExitBlock
      #variant OnExitBlock_A1
      "onexit"
      "{"
      StatementBlock
      "}"
   
   phrase ComplexMode
      #variant ComplexMode_A1
      #subphrase ComplexMode_A1_M1
      {
         #variant ComplexMode_A1_M1_A1
         "seq"
      |
         #variant ComplexMode_A1_M1_A2
         "par"
      }
      "{"
      #subphrase ComplexMode_A1_M3
      {
         #variant ComplexMode_A1_M3_A1
         VarInstance
      }*
      #subphrase ComplexMode_A1_M4
      {
         #variant ComplexMode_A1_M4_A1
         OnEntryBlock
      }?
      #subphrase ComplexMode_A1_M5
      {
         #variant ComplexMode_A1_M5_A1
         InvariantBlock
      }?
      ModeList
      #subphrase ComplexMode_A1_M7
      {
         #variant ComplexMode_A1_M7_A1
         OnExitBlock
      }?
      "}"
   
   phrase ModeList
      #variant ModeList_A1
      #subphrase ModeList_A1_M1
      {
         #variant ModeList_A1_M1_A1
         #subphrase ModeList_A1_M1_A1_M1
         {
            #variant ModeList_A1_M1_A1_M1_A1
            LabelStatement
         }?
         ModeSpecification
      }*
   
   phrase UntilBlock
      #variant UntilBlock_A1
      "until"
      "{"
      UntilGuardList
      "}"
   
   phrase UntilGuardList
      #variant UntilGuardList_A1
      #subphrase UntilGuardList_A1_M1
      {
         #variant UntilGuardList_A1_M1_A1
         UntilGuardStatement
      }*
   
   phrase UntilGuardStatement
      #variant UntilGuardStatement_A1
      "["
      #subphrase UntilGuardStatement_A1_M2
      {
         #variant UntilGuardStatement_A1_M2_A1
         BooleanExpression
      }?
      "]"
      GuardOp
      StatementBlock
      #subphrase UntilGuardStatement_A1_M6
      {
         #variant UntilGuardStatement_A1_M6_A1
         GotoStatement
      }?
      #subphrase UntilGuardStatement_A1_M7
      {
         #variant UntilGuardStatement_A1_M7_A1
         "("
         TemplateOrValueFormalParList
         ")"
      }?
      #subphrase UntilGuardStatement_A1_M8
      {
         #variant UntilGuardStatement_A1_M8_A1
         RunsOnSpec
      }?
      #subphrase UntilGuardStatement_A1_M9
      {
         #variant UntilGuardStatement_A1_M9_A1
         "("
         TemplateOrValueFormalParList
         ")"
      }?
      #subphrase UntilGuardStatement_A1_M10
      {
         #variant UntilGuardStatement_A1_M10_A1
         RunsOnSpec
      }?
      ModeSpecification
   
   phrase TemplateInstance
      #variant TemplateInstance_A1
      Identifier
   
   phrase BITSTRINGMATCH
      #variant BITSTRINGMATCH_A1
      BSTRING
   
   phrase HEXSTRINGMATCH
      #variant HEXSTRINGMATCH_A1
      HSTRING
   
   phrase OCTETSTRINGMATCH
      #variant OCTETSTRINGMATCH_A1
      OSTRING
   
   phrase PATTERN
      #variant PATTERN_A1
      CSTRING
   
   token FLOATVALUE( -> string)
      <<<[0-9]+"."[0-9]*("E"("+"|"-")?[0-9]+)?>>>
   
   token NUMBER( -> string)
      <<<[0-9]+>>>
   
   token BSTRING( -> string)
      <<<\'(0|1)*\'B>>>
   
   token HSTRING( -> string)
      <<<\'(0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f|A|B|C|D|E|F)*\'H>>>
   
   token OSTRING( -> string)
      <<<\'((0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f|A|B|C|D|E|F)(0|1|2|3|4|5|6|7|8|9|a|b|c|d|e|f|A|B|C|D|E|F))*\'O>>>
   
   token CSTRING( -> string)
      <<<\"[^\"]*\">>>
   
   phrase FREETEXT
      #variant FREETEXT_A1
      CSTRING

