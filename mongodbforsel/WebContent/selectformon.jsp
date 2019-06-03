<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "java.util.*"%>
<%@page import="com.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询</title>

<script type="text/javascript">
function checkValidate()  {  
  //判断用户名是否为空  
  if(document.getElementById("search").value==""||document.getElementById("search").value==null)  {  
	  alert("要查询的内容不能为空！！！");    
	  return false;  
  }
  return true;
  
}  
</script>
</head>
<body>
<h3><font color="Green">WIKIDATA</font>查询功能</h3>
<h4>请输入要查询的内容：</h4>
<form action="MonHandler.do" method="get" id="form1" onsubmit="return checkValidate()" onkeydown="if(event.keyCode==13)return false;">

<input type="text" name="mongodbsearch" value="${what }" id="search">

<table border="0">
    <tr>
   		<td>请选择查询方式：</td>
        <td><button name ="style1" type="submit" value="name" >name——>entity</button></td>
        <td><button name ="style1" type="submit" value="id">id——>pre-catego</button></td>
        <td><button name ="style1" type="submit" value="entity">id——>all-entity</button></td>
        <td><button name ="style1" type="submit" value="ownpro">id——>ownpro</button></td>
        <td><button name ="style1" type="submit" value="qaq">question——>answer</button></td>
        <td><button name ="style1" type="submit" value="qaq1">question——>wikidata</button></td>
    </tr>
    <%-- 
    <tr>
    	<td>优化查询方式：</td>
        <td><button name ="style1" type="submit" value="name2">name——>entity</button></td>
        <td><button name ="style1" type="submit" value="id2">id——>pre-catego</button></td>
        <td><button name ="style1" type="submit" value="entity2">id——>all-entity</button></td>
        <td><button name ="style1" type="submit" value="ownpro2">id——>ownpro</button></td>
        <td><button name ="style1" type="submit" value="qaq2">question——>answer</button></td>
        <td><button name ="style1" type="submit" value="qaq2">question——>wikidata</button></td>
    </tr>
   --%>
</table>
</form>

<h4>优化前耗时(MS)：</h4>
<input type="text" name="hour" disabled="disabled" value="${time }">
<h4>优化后耗时(MS)：</h4>
<input type="text" name="hour1" disabled="disabled" value="${time1 }">
<h4>查询结果：</h4>
<div>
<span><font color="#FF0000">${des1 }</font></span>
<span><font color="#FF0000">${des2 }</font></span>
<span><font color="#FF0000">${des3 }</font></span>
<span><font color="#FF0000">${des4 }</font></span>
<span><font color="#FF0000">${des5 }</font></span>
<span><font color="#FF0000">${des6 }</font></span>
</div>

<%--第一个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table align="left" border="0">
<tr>

<td style="float: left;margin: 0px;padding: 0px; white-space: nowrap;">
<table>
<tr>
<th>${id }</th>
</tr>
<c:forEach var="idList" items="${results.id}">
<tr>
<td><span><a href="https://www.wikidata.org/wiki/${idList}" target="_blank">
<c:out value="${idList}"></c:out></a>
</span> </td>
</tr>
</c:forEach>
</table>
</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;">
<table>
<tr>
<th>${language }</th>
</tr>
<c:forEach var="languageList" items="${results.language}">
<tr>
<td><span>
<c:out value="${languageList}"></c:out>
</span></td>
</tr>
</c:forEach>
</table>
</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;">
<table>
<tr>
<th>${value }</th>
</tr>
<%-- 
<tr>
<td>
<c:if test="${empty results}"><font color="blue">抱歉！查无此相关内容。</font></c:if>
</td>
</tr>
--%>
<c:forEach var="valueList" items="${results.value}">
<tr>
<td><span>
<c:out value="${valueList}"></c:out>
</span></td>
</tr>
</c:forEach>
</table>
</td>

</tr>
<tr>
<td><font color="blue">${mess2}</font></td>
</tr>
</table>
<%--第一个功能查询e------------------------------------------------------------------------------------------------------  --%>

<%--第二个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table align="left" border="0">
<tr>
<td style="float: left;margin: 0px;padding: 0px; white-space: nowrap;">

<table>
<tr>
<th>${idp }</th>
</tr>
<c:forEach var="idList" items="${result.id}">
<tr>
<td><span><a href="https://www.wikidata.org/wiki/${idList}" target="_blank">
<c:out value="${idList}"></c:out></a>
</span> </td>
</tr>
</c:forEach>
</table>

</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;">
<table>
<tr>
<th>${property }</th>
</tr>

<c:forEach var="property" items="${result.property}">
<tr>
<td><span><a href="https://www.wikidata.org/wiki/Property:${property}" target="_blank">
<c:out value="${property}"></c:out></a>
</span></td>
</tr>
</c:forEach>
</table>
</td>
</tr>

<tr>
<td><font color="blue">${mess1}</font></td>
</tr>

</table>
<%--第二个功能查询e------------------------------------------------------------------------------------------------------  --%>

<%--第三个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table align="left" border="0">
<tr>
<th>${ide }</th>
</tr>
<c:forEach var="idList" items="${resultEntity.id}">
<tr>
<td><span><a href="https://www.wikidata.org/wiki/${idList}" target="_blank">
<c:out value="${idList}"></c:out></a>
</span> </td>
</tr> 
</c:forEach>

<tr>
<td><font color="blue">${mess}</font></td>
</tr>

</table>
<%--第三个功能查询e------------------------------------------------------------------------------------------------------  --%>

<%--第四个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table  align="left" border="0">
<tr>
<td   style="float: left;margin: 0px;padding: 0px; white-space: nowrap;">

<table border="0">
<tr>
<th>${dataproperty }</th>
</tr>
<c:forEach var="property" items="${resultOwnpro.property}">
<tr>
<td style="white-space:nowrap"><span><a href="https://www.wikidata.org/wiki/Property:${property}" target="_blank">
<c:out value="${property}"></c:out></a>
</span> </td>
</tr>
</c:forEach>
</table>

</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;" >

<table border="0">
<tr>
<th>${datavalue }</th>
</tr>

<c:forEach var="datavalue" items="${resOwnpro}">
<tr>
<td><span>
<c:out value="${datavalue}"></c:out>
</span></td>
</tr>
</c:forEach>
<c:if test="${resOwnpro=='[]'}">
<tr>
<td><font color="blue">抱歉！查无此相关内容。</font></td>
</tr>
</c:if>
</table>

</td>

</tr>
</table>
<%--第四个功能查询e------------------------------------------------------------------------------------------------------  --%>

<%--第五个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table  align="left" border="0">
<tr>
<td   style="float: left;margin: 0px;padding: 0px; white-space: nowrap;">

<table border="0">
<tr>
<th>${idQaq }</th>
</tr>
<c:forEach var="listID" items="${idQaqList}">
<tr>
<td style="white-space:nowrap"><span><a href="https://www.wikidata.org/wiki/${listID}" target="_blank">
<c:out value="${listID}"></c:out></a>
</span> </td>
</tr>
</c:forEach>
</table>

</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;" >

<table border="0">
<tr>
<th>${valueQaq }</th>
</tr>

<c:forEach var="valueList" items="${valueQaqList}">
<tr>
<td><span>
<c:out value="${valueList}"></c:out>
</span></td>
</tr>
</c:forEach>
 
<c:if test="${valueQaqList=='[]'}">
<tr>
<td><font color="blue">抱歉！查无此相关内容。</font></td>
</tr>
</c:if>

</table>

</td>

</tr>
</table>
<%--第五个功能查询e------------------------------------------------------------------------------------------------------ --%>

<%--第六个功能查询s------------------------------------------------------------------------------------------------------  --%>
<table  align="left" border="0">
<tr>
<td   style="float: left;margin: 0px;padding: 0px; white-space: nowrap;">

<table border="0">
<tr>
<th>${la }</th>
</tr>

<c:forEach var="listsl" items="${sl}">
<tr>
<td style="white-space:nowrap"><span>
<c:out value="${listsl}"></c:out>
</span> </td>
</tr>
</c:forEach>
</table>

</td>

<td style="float: right;margin: 0px;padding: 0px; white-space: nowrap;" >

<table border="0">
<tr>
<th>${link }</th>
</tr>
<%-- --%>
<c:forEach var="Listsl1" items="${sl1}">
<tr>
<td><span><a href="https://www.wikidata.org/wiki/Q${Listsl1}" target="_blank">
<c:out value="(Q${Listsl1})"></c:out></a>
</span></td>
</tr>
</c:forEach>
<c:if test="${sl1=='[]'}">
<tr>
<td><font color="blue">抱歉！查无此相关内容。</font></td>
</tr>
</c:if>
</table>

</td>

</tr>
</table>
<%--第六个功能查询e------------------------------------------------------------------------------------------------------  --%>
</body>
</html>