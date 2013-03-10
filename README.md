An FAE-compatible Scheme Interpreter
------------------------------------
-Building the Interpreter
You can build the interpreter using `ant`.

-Running the Interpreter
You can run the interpreter either through an ant task

    ant run

Or calling java directly

    java -cp bin interp.REPL

-Options
Default scoping is set to static. Enabling dynamic
scoping is possible with the following command

    java -cp bin -Ddynamic-scope interp.REPL

-Sample lisp file (Demo)
Some simple (FAE) lisp expressions to try

    java -cp bin interp.REPL < demo.lisp

