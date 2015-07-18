<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>你好 ${user}</h1>
<h2>${latestProduct["url"]}</h2>
<h2>${latestProduct.name}</h2>
<h2>${latestProduct.date?string("yyyy-MM-dd")}</h2>
<h2>${latestProduct.isUsed?string("yes","no")}</h2>

嵌入自定义方法
￼<#assign x = "something"> 
${indexOf("met", x)} 
${indexOf("foo", x)}


嵌入一个自定义指令@upper
<@upper>
  bar
  <#-- All kind of FTL is allowed here -->
  <#list ["red", "green", "blue"] as color>
    ${color}
  </#list>
  baaz
</@upper>

嵌入一个自定义指令	@repeat
<#assign x = 1>
<@repeat count=4>
  Test ${x}
  <#assign x = x + 1>
</@repeat>

<@repeat count=3 hr=true>
  Test
</@repeat>

<@repeat count=3; cnt>
  ${cnt}. Test
</@repeat>   
</body>
</html>