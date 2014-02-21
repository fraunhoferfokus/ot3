
   
   proc NotCovered(string, any)
      
      rule NotCovered(Msg, X)
         sourcepos X -> P
         Concat("Not yet covered in this version: ", Msg -> Str)
         Error(Str, P)
   
   proc ErrorI(string, string, string, string)
      
      rule ErrorI(string1, string2, string3, Pos)
         Concat(string1, string2 -> t1)
         Concat(t1, string3 -> t2)
         Error(t2, Pos)
   
   proc CompilerError(string)
      
      rule CompilerError(str)
         error str

