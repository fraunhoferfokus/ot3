
   
   proc Start()
      TTCN3Module(-> X)
      Semantics(X)
   
   var NameOfModule: string
   
   proc v_2013_07_23()
      
      rule v_2013_07_23()
         log "Version 2013/07/23"
   
   proc Semantics($TTCN3Module)
      
      rule Semantics(X)
         InitScopeHandling()
         InitImports()
         InitEmit()
         BeginGlobalScope()
         Trafo_TTCN3Module(X)
         DoImports()
         ProcessGlobals()
         EndGlobalScope()

