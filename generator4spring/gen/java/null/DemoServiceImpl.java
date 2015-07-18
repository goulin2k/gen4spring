/**
 * 
 */
package <!-- FREEMARKER ERROR MESSAGE STARTS HERE --><!-- ]]> --><script language=javascript>//"></script><script language=javascript>//'></script><script language=javascript>//"></script><script language=javascript>//'></script></title></xmp></script></noscript></style></object></head></pre></table></form></table></table></table></a></u></i></b><div align='left' style='background-color:#FFFF7C; display:block; border-top:double; padding:4px; margin:0; font-family:Arial,sans-serif; color:#A80000; font-size:12px; font-style:normal; font-variant:normal; font-weight:normal; text-decoration:none; text-transform: none'><b style='font-size:12px; font-style:normal; font-weight:bold; text-decoration:none; text-transform: none;'>FreeMarker template error</b><pre style='display:block; background: none; border: 0; margin:0; padding: 0;font-family:monospace; color:#A80000; font-size:12px; font-style:normal; font-variant:normal; font-weight:normal; text-decoration:none; text-transform: none; white-space: pre-wrap; white-space: -moz-pre-wrap; white-space: -pre-wrap; white-space: -o-pre-wrap; word-wrap: break-word;'>

The following has evaluated to null or missing:
==> model.packageName  [in template "serviceImpl.ftl" at line 4, column 11]

Tip: If the failing expression is known to be legally null/missing, either specify a default value with myOptionalVar!myDefault, or use &lt;#if myOptionalVar??>when-present&lt;#else>when-missing&lt;/#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthessis: (myOptionVar.foo)!myDefault, (myOptionVar.foo)??

The failing instruction (FTL stack trace):
----------
==> ${model.packageName}  [in template "serviceImpl.ftl" at line 4, column 9]
----------

Java stack trace (for programmers):
----------
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:98)
	at freemarker.core.EvalUtil.coerceModelToString(EvalUtil.java:382)
	at freemarker.core.Expression.evalAndCoerceToString(Expression.java:115)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:76)
	at freemarker.core.Environment.visit(Environment.java:265)
	at freemarker.core.MixedContent.accept(MixedContent.java:93)
	at freemarker.core.Environment.visit(Environment.java:265)
	at freemarker.core.Environment.process(Environment.java:243)
	at freemarker.template.Template.process(Template.java:277)
	at com.tianyi.codegen.BaseCodeGenerator.generateCodeFile(BaseCodeGenerator.java:150)
	at com.tianyi.codegen.BaseCodeGenerator.generatorFiles(BaseCodeGenerator.java:99)
	at com.tianyi.codegen.BaseCodeGenerator.generate(BaseCodeGenerator.java:86)
	at com.tianyi.codegen.demo.TestGenerator.main(TestGenerator.java:44)

</pre></div></html>
