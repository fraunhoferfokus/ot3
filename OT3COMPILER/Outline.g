
   
   var OutlineObjects: OutlineEntry[]
   
   proc HEADER(any -> string)
      
      rule HEADER(X -> Id)
         X -> TTCN3Module_A1(X1, X2, X3, X4, X5)
         X1 -> ModuleId_A1(Id, X11)
   
   proc OUTLINE(any -> OutlineEntry[])
      
      rule OUTLINE(n -> Outline)
         global OutlineObjects <- OutlineEntry[]
         Sammle(n)
         global OutlineObjects -> L
         HEADER(n -> SUITE)
         Rt <- outlineentry(SUITE, "module", n, L)
         Outline <- OutlineEntry[Rt]
   
   type OutlineEntry
      outlineentry(name:string, bild:string, knoten:any, children:OutlineEntry[])
   
   sweep Sammle(any)
      
      rule Sammle(X~FunctionDef_A1(NAME0, _, _, _, _, _))
         H <- outlineentry(NAME0, "function", X, OutlineEntry[])
         global OutlineObjects -> L
         global OutlineObjects <- OutlineEntry[H::L]
      
      rule Sammle(X~TestcaseDef_A1(NAME0, _, _, _, _))
         H <- outlineentry(NAME0, "testcase", X, OutlineEntry[])
         global OutlineObjects -> L
         global OutlineObjects <- OutlineEntry[H::L]

