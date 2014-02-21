
// PACKAGE GENTLY  -  GENTLE RUNTIME LIBRARY
// COPYRIGHT (C) 2000-2013 COMPILERTOOLS.NET
// ALL RIGHTS RESERVED.   MAY BE DISTRIBUTED
// AS PART OF SOFTWARE GENERATED WITH GENTLE

package Gently;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;

public class Llana
{
   public static void AnalizeGrammar()
   {
      ComputeTransparent();
      ComputeFirst();
      ComputeFollow();
      ComputeDir();

      // Report1();
      // Report2(); // xxx and compute 'danger' ! // overlapping dir sets (?)

      // HandleLeftRecursion();
      // ComputeReachable();

   }

   private static ArrayList<Nonterm> Nonterms = new ArrayList<Nonterm>();

   public static void Register(Nonterm n)
   {
      Nonterms.add(n);
   }

   //
   // TRANSPARENT
   //
   // Trans(N) : N can produce the empty string
   //

   private static void ComputeTransparent()
   {
      for (Nonterm n : Nonterms) {
         SetTrans(n, false);
      }

      boolean CHANGED = true;
      while (CHANGED) {

         CHANGED = false;
         for (Nonterm n : Nonterms) {
            if (!Trans(n)) {
               for (Rule r : n.Rules) {
                  boolean stilltrans = true;
                  for (Symbol m : r.Rhs) {
                     if (m instanceof Nonterm) {
                        if (!Trans((Nonterm) m))
                           stilltrans = false;
                     } else {
                        stilltrans = false;
                     }
                  }
                  if (stilltrans) {
                     SetTrans(n, true);
                     CHANGED = true;
                  }
               }
            }
         }
      }
   }

   private static Hashtable<Nonterm, Boolean> TRANS = new Hashtable<Nonterm, Boolean>();

   private static boolean Trans(Nonterm s)
   {
      return TRANS.get(s);
   }

   private static void SetTrans(Nonterm s, boolean b)
   {
      TRANS.put(s, b);
   }

   /*
    * {***** // DIRECT RECURSIVE private static void HandleLeftRecursion() { for
    * (Nonterm n : Nonterms) { SetRecursive(n , false); for (Nonterm k :
    * Nonterms) { SetReachable(k , false); } HandleLeftRecursionN(n); } }
    * private static void HandleLeftRecursionN(Nonterm n) { boolean leftrec =
    * false; for (Rule r : n.Rules) { if (r.Rhs.length > 0) { Symbol s =
    * r.Rhs[0]; if (s.Name.equals(n.Name)) leftrec = true; } } if (!leftrec)
    * return;
    * 
    * for (Rule r : n.Rules) { if (r.Rhs.length > 0 &&
    * r.Rhs[0].Name.equals(n.Name)) { // n : n alpha // becomes // n$ : alpha n$
    * // n$ : empty } }
    * 
    * for (Rule r : n.Rules) { if (r.Rhs.length == 0 || !
    * r.Rhs[0].Name.equals(n.Name)) { // n : beta // becomes // n : beta n$ } }
    * }****}
    */

   static boolean RecursionDetected;

   public static void TestForRecursion()
   {
      RecursionDetected = false;
      ComputeReachable();
      if (RecursionDetected) {
         System.out.println("+++ recursion detected");
         JavaLib.RuntimeError("constraint not handled");
      }
   }

   // REACHABLE

   private static void ComputeReachable()
   {
      for (Nonterm n : Nonterms) {
         SetRecursive(n, false);
         for (Nonterm k : Nonterms) {
            SetReachable(k, false);
         }
         ComputeReachableN(n, n);
      }
   }

   private static void ComputeReachableN(Nonterm n, Nonterm k)
   {
      for (Rule r : n.Rules) {
         for (Symbol m : r.Rhs) {
            // still transparent
            if (m instanceof Nonterm) {
               if (m == k) {
                  System.out.print("RECURSIVE : ");
                  System.out.println(m.Name);
                  SetRecursive(m, true);
                  RecursionDetected = true;
                  return;
               }
               if (Reachable(m)) {
                  return;
               }
               SetReachable(m, true);
               ComputeReachableN((Nonterm) m, k);

               if (Trans((Nonterm) m)) {
                  // continue with rule
               } else {
                  // break rule
                  break;
               }
            } else { // token
               break;
            }
         }
      }
   }

   private static Hashtable<Symbol, Boolean> REACHABLE = new Hashtable<Symbol, Boolean>();

   private static boolean Reachable(Symbol n)
   {
      return REACHABLE.get(n);
   }

   private static void SetReachable(Symbol n, boolean b)
   {
      REACHABLE.put(n, b);
   }

   private static Hashtable<Symbol, Boolean> RECURSIVE = new Hashtable<Symbol, Boolean>();

   public static boolean Recursive(Symbol n)
   {
      return RECURSIVE.get(n);
   }

   private static void SetRecursive(Symbol n, boolean b)
   {
      RECURSIVE.put(n, b);
   }

   //
   // FIRST
   //
   // First(N) = { t | N can derive a string t... }
   //

   private static void ComputeFirst()
   {
      for (Nonterm n : Nonterms) {
         SetFirst(n, EmptySet());
      }
      boolean CHANGED = true;
      while (CHANGED) {
         CHANGED = false;
         for (Nonterm n : Nonterms) {
            for (Rule r : n.Rules) {
               for (Symbol m : r.Rhs) {
                  // still transparent
                  if (m instanceof Nonterm) {
                     if (!Subset(First(n), First(m))) {
                        SetFirst(n, Union(First(n), First(m)));
                        CHANGED = true;
                     }
                     if (Trans((Nonterm) m)) {
                        // continue with rule
                     } else {
                        // break rule
                        break;
                     }
                  } else { // token
                     if (!Subset(First(n), Singleton(m))) {
                        SetFirst(n, Union(First(n), Singleton(m)));
                        CHANGED = true;
                     }
                     // break rule
                     break;
                  }
               }
            }
         }
      }
   }

   private static Hashtable<Symbol, HashSet<Symbol>> FIRST = new Hashtable<Symbol, HashSet<Symbol>>();

   private static HashSet<Symbol> First(Symbol n)
   {
      return FIRST.get(n);
   }

   private static void SetFirst(Symbol n, HashSet<Symbol> s)
   {
      FIRST.put(n, s);
   }

   //
   // FOLLOW
   //
   // Follow(N) = { t | ... N t ... can be drived }
   //

   private static void ComputeFollow()
   {
      for (Nonterm n : Nonterms) {
         SetFollow(n, EmptySet());
      }

      Token Eof = TokenStream.EofToken();
      Nonterm Root = (Nonterm) ExhaustiveParser.UserRoot;
      SetFollow(Root, Singleton(Eof));

      boolean CHANGED = true;
      while (CHANGED) {
         CHANGED = false;
         for (Nonterm n : Nonterms) {
            for (Rule r : n.Rules) {
               HashSet<Symbol> RightContext;

               RightContext = Follow(n);

               int len = r.Rhs.length;
               for (int i = len - 1; i >= 0; i--) {

                  // rule backwards

                  Symbol m = r.Rhs[i];
                  if (m instanceof Nonterm) {
                     // m Nonterm
                     if (!Subset(Follow(m), RightContext)) {
                        SetFollow(m, Union(Follow(m), RightContext));
                        CHANGED = true;
                     }
                     if (Trans((Nonterm) m)) {
                        RightContext = Union(RightContext, First(m));
                     } else {
                        RightContext = First(m);
                     }
                  } else {
                     // m Token
                     RightContext = Singleton(m);
                  }
               }
            }
         }
      }
   }

   private static Hashtable<Symbol, HashSet<Symbol>> FOLLOW = new Hashtable<Symbol, HashSet<Symbol>>();

   private static HashSet<Symbol> Follow(Symbol n)
   {
      return FOLLOW.get(n);
   }

   private static void SetFollow(Symbol n, HashSet<Symbol> s)
   {
      FOLLOW.put(n, s);
   }

   //
   // DIR
   //
   // rule R : N ::= N1 ... Nn
   //
   // Dir(R) = Union First(Ni) , N1 ... Ni-1 transparent
   // Follow(N) , N1 ... Nn transparent
   //

   private static void ComputeDir()
   {
      for (Nonterm n : Nonterms) {
         for (Rule r : n.Rules) {
            boolean stilltransparent = true;
            HashSet<Symbol> DirSet = EmptySet();
            for (Symbol m : r.Rhs) {
               if (m instanceof Nonterm) {
                  DirSet = Union(DirSet, First(m));
                  if (!Trans((Nonterm) m)) {
                     stilltransparent = false;
                     break;
                  }
               } else {
                  // m token
                  DirSet = Union(DirSet, Singleton(m));
                  stilltransparent = false;
                  break;
               }
            }
            if (stilltransparent) {
               DirSet = Union(DirSet, Follow(n));
            }
            SetDir(r, DirSet);
         }
      }
   }

   private static Hashtable<Rule, HashSet<Symbol>> DIR = new Hashtable<Rule, HashSet<Symbol>>();

   public static HashSet<Symbol> Dir(Rule n)
   {
      return DIR.get(n);
   }

   private static void SetDir(Rule n, HashSet<Symbol> s)
   {
      DIR.put(n, s);
   }


   private static Hashtable<Nonterm, HashSet<Integer>> DANGER = new Hashtable<Nonterm, HashSet<Integer>>();

   private static Hashtable<Nonterm, Boolean> ACHTUNG = new Hashtable<Nonterm, Boolean>();

   public static boolean Achtung(Nonterm n)
   {
      return ACHTUNG.get(n);
   }


   // SET OPERATIONS

   private static HashSet<Symbol> EmptySet()
   {
      return new HashSet<Symbol>();
   }

   private static HashSet<Symbol> Union(HashSet<Symbol> s1, HashSet<Symbol> s2)
   {
      HashSet<Symbol> s = new HashSet<Symbol>();
      s.addAll(s1);
      s.addAll(s2);
      return s;
   }

   private static HashSet<Symbol> Singleton(Symbol x)
   {
      HashSet<Symbol> s = new HashSet<Symbol>();
      s.add(x);
      return s;
   }

   private static boolean Subset(HashSet<Symbol> s1, HashSet<Symbol> s2)
   {
      return s1.containsAll(s2);
   }

   private static void PrintSet(HashSet<Symbol> s)
   {
      System.out.print("{");
      for (Symbol k : s) {
         System.out.print(" " + k.Name);
      }
      System.out.println(" }");
   }

   private static boolean Disjoint(HashSet<Symbol> s1, HashSet<Symbol> s2)
   {
      for (Symbol m : s2) {
         if (s1.contains(m))
            return false;
      }
      return true;
   }

   private static HashSet<Symbol> Intersection(HashSet<Symbol> s1,
         HashSet<Symbol> s2)
   {
      HashSet<Symbol> res = new HashSet<Symbol>();
      for (Symbol m : s2) {
         if (s1.contains(m)) {
            res.add(m);
         }
      }
      return res;
   }

   static HashSet<Integer> Convert(HashSet<Symbol> s)
   {
      HashSet<Integer> res = new HashSet<Integer>();
      for (Symbol sym : s) {
         res.add(((Token) sym).Code);
      }
      return res;
   }
}
