<!--#include file="inc/conn.asp"-->
<%
Call QuanXian(Products_B)
ID=Request("ID")
ClassName=Server.HTMLEncode(Request("ClassName"))
Sel=SafeRequest("Sel",2)

if ClassName<>Empty then
	ClassName_key=replace(Request("ClassName_key"),"'","''")
	ClassName_En=replace(Request("ClassName_En"),"'","''")
	order=SafeRequest("order",2)
	'取排序的值+++++++++++++++++++++++++++++++++++++++++++++++++++
	Set Rs=Conn.execute("select Max(Xu) as PageID from P_Class")
		PageID=Rs("PageID")
		IF PageID<>Empty Then
			PageID=PageID
		Else
			PageID=1
		End IF
	Call twClose()
	'结束+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	'大类
	if ClassName="Big" then
		if order<>Empty Then
			'打开ID取出当前的ID数++++++++++++++++++++++++++++++++++
			Set B_Order=Conn.execute("select * from P_Class Where ID="&ID&"")
			IF B_Order.Eof then
				twScript("操作失败,产品类型中没有此ID号")
			End IF
			B_b=B_Order("Xu")
			IF Order="up" Then
				'打开ID上前一列数的值++++++++++++++++++++++++++++++
				Set B1_Order=Conn.execute("select top 1 * from P_Class Where Xu<"&B_b&" and ParentClassID=0 Order by Xu Desc")
				IF B1_Order.Eof Then
					twScript("不能操作")
				End IF
				conn.execute ("update P_Class set Xu="&B1_Order("Xu")&" where ID="&ID&"")
				conn.execute ("update P_Class set Xu="&B_b&" where ID="&B1_Order("ID")&"")
				response.Redirect(request.ServerVariables("HTTP_REFERER"))
				'结束上一个ID++++++++++++++++++++++++++++++++++++++++
			Elseif Order="dowm" Then
				'打开ID上前一列数的值++++++++++++++++++++++++++++++
				Set B1_Order=Conn.execute("select Top 1 * from P_Class Where Xu>"&B_b&" and ParentClassID=0 Order by Xu asc")
				IF B1_Order.Eof Then
					twScript("不能操作")
				End IF
				conn.execute ("update P_Class set Xu="&B1_Order("Xu")&" where ID="&ID&"")
				conn.execute ("update P_Class set Xu="&B_b&" where ID="&B1_Order("ID")&"")
				response.Redirect(request.ServerVariables("HTTP_REFERER"))
				B1_Order.Close
				Set B1_Order=NotHing
				'结束上一个ID++++++++++++++++++++++++++++++++++++++++
			End if
			B_Order.Close
			Set B_Order=Nothing
		Else
			'添加大类+++++++++++++++++++++++++++++++++++++++++++++++++
			if Sel="Add" then
				Conn.execute("insert into P_Class(ClassName,ParentClassID,E_ClassName,Xu)values('"&ClassName_key&"',0,'"&ClassName_En&"',"&PageID+1&")")
				Response.Write("<Script>alert('产品大类添加成功');window.location='p_class.asp'</Script>")
				Response.End()
			'修改大类+++++++++++++++++++++++++++++++++++++++++++++++++
			Elseif Sel="Modify1" then
				Conn.execute("Update P_Class Set ClassName='"&ClassName_key&"',E_ClassName='"&ClassName_En&"' Where ID="&ID&" ")
				Response.Write("<Script>alert('产品大类修改成功');window.location='p_class.asp'</Script>")
				Response.End()
			'删除大类和此类产品一起删除+++++++++++++++++++++++++++++++
			Elseif Sel="Delete" then
				'删除此类产品+++++++++++++++++++++++++++++++++++++++++
				Conn.execute("delete from Products Where Big_Class="&ID&"")
				'结束+++++++++++++++++++++++++++++++++++++++++++++++++
				Conn.execute("delete from P_Class Where ParentClassID="&ID&"")
				Conn.execute("delete from P_Class Where ID="&ID&"")
				Response.Write("<Script>alert('产品大类和此类产品成功!!!');window.location='p_class.asp'</Script>")
				Response.End()
			End IF
			'结束大类添加,修改,删除+++++++++++++++++++++++++++++++++++
		End if
	'小类
	Elseif ClassName="Small" then
		if order<>Empty Then
			'打开ID取出当前的ID数++++++++++++++++++++++++++++++++++
			Set B_Order=Conn.execute("select * from P_Class Where ID="&ID&"")
			IF B_Order.Eof then
				twScript("操作失败,产品类型中没有此ID号")
			End IF
			B_b=B_Order("Xu")
			B_b_P=B_Order("ParentClassID")
			IF Order="up" Then
				'打开ID上前一列数的值++++++++++++++++++++++++++++++
				Set B1_Order=Conn.execute("select top 1 * from P_Class Where Xu<"&B_b&" and ParentClassID<>0 Order by Xu Desc")
				IF B1_Order.Eof Then
					twScript("不能操作")
				End IF
				conn.execute ("update P_Class set Xu="&B1_Order("Xu")&" where ID="&ID&"")
				conn.execute ("update P_Class set Xu="&B_b&" where ID="&B1_Order("ID")&"")
				response.Redirect(request.ServerVariables("HTTP_REFERER"))
				'结束上一个ID++++++++++++++++++++++++++++++++++++++++
			Elseif Order="dowm" Then
				'打开ID上前一列数的值++++++++++++++++++++++++++++++
				Set B1_Order=Conn.execute("select Top 1 * from P_Class Where Xu>"&B_b&" and ParentClassID<>0 Order by Xu asc")
				IF B1_Order.Eof Then
					twScript("不能操作")
				End IF
				conn.execute ("update P_Class set Xu="&B1_Order("Xu")&" where ID="&ID&"")
				conn.execute ("update P_Class set Xu="&B_b&" where ID="&B1_Order("ID")&"")
				response.Redirect(request.ServerVariables("HTTP_REFERER"))
				B1_Order.Close
				Set B1_Order=NotHing
				'结束上一个ID++++++++++++++++++++++++++++++++++++++++
			End if
			B_Order.Close
			Set B_Order=Nothing
		Else
			BigID=Request.Form("BigID")
			'添加小类+++++++++++++++++++++++++++++++++++++++++++++++++
			if Sel="Add" then
				Conn.execute("insert into P_Class(ClassName,ParentClassID,E_ClassName,pic,content,Xu)values('"&ClassName_key&"',"&BigID&",'"&ClassName_En&"','"&request("smpic")&"','"&request("content")&"',"&PageID+1&")")
				Response.Write("<Script>alert('产品小类添加成功');window.location='p_class.asp'</Script>")
				Response.End()
			'修改小类+++++++++++++++++++++++++++++++++++++++++++++++++
			ElseIF Sel="Modify1" then
				Conn.execute("Update P_Class Set ClassName='"&ClassName_key&"',E_ClassName='"&ClassName_En&"',ParentClassID="&BigID&",pic='"&request("smpic")&"',content='"&request("content")&"' Where ID="&ID&" ")
				Response.Write("<Script>alert('产品小类修改成功');window.location='p_class.asp'</Script>")
				Response.End()
			'删除小类+++++++++++++++++++++++++++++++++++++++++++++++++
			ElseIF Sel="Delete" then
				'删除此类产品+++++++++++++++++++++++++++++++++++++++++
				Conn.execute("delete from Products Where Small_Class="&ID&"")
				'结束+++++++++++++++++++++++++++++++++++++++++++++++++
				Conn.execute("delete from P_Class Where ID="&ID&"")
				Response.Write("<Script>alert('产品小类和此类产品成功!!!');window.location='p_class.asp'</Script>")
				Response.End()
			End if
			'结束小类添加,修改,删除+++++++++++++++++++++++++++++++++++
		End if
	End if
	'类结束
End if

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<Script>
function Classname(formname)
{
	if(formname.ClassName_key.value=="")
	{
	alert("类别名称不能为空")
	formname.ClassName_key.focus()
	return false;
	}
}
function delbig()
{
if(!confirm('确定删除产品大类吗？删除后所属于此类产品一起删除！！！'))
{
return false;
}
}
function delsmall()
{
if(!confirm('确定删除产品小类吗？删除后所属于此类产品一起删除！！！'))
{
return false;
}
}
</Script>
<body>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<!--分类管理<开始>------------------------------------------------------------------>
  <tr>
    <td height="150"><form name="form1" method="post" action="?modify=modify">
      <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size">分类设置(<a href="p_class.asp#Add1" style="text-decoration:none;color:#ffffff">添加大类</a>)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2"><%
		  Set Rsp=Server.CreateObject("adodb.recordset")
		  StrSql="select * from P_Class Where ParentClassID=0 and ID<>71 Order by Xu Asc"
		  Rsp.open StrSql,conn,1,3
		  if Rsp.eof then
		  Response.Write("暂无分类")
		  else
		  Page=Rsp.recordcount
		  %>
              <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
                <tr align="center" class="table_bg_n">
                  <td width="10%" height="22"><B>排序</B></td>
                  <td colspan="2"><B>类别名称</B></td>
                  <td width="10%"><B>操作<STRONG>一</STRONG></B></td>
                  <td width="8%"><B>操作二</B></td>
                  <td width="8%"><B>操作三</B></td>
                </tr>
				<%
				Do while Not Rsp.Eof 
				Q=Q+1
				%>
                <tr class="table_bg_n">
                  <td height="22" align="center" style="color:#990000;"><%Response.Write(Q)%></td>
                  <td colspan="2" class="padding-left-10"><img src="images/tree_folder4.gif" width="15" height="15"><%IF banben_China=True Then Tw(Rsp("ClassName")) End IF%><%IF banben_En=True Then Tw("&nbsp;"&Rsp("E_ClassName")) End if%></td>
                  <td align="center"><a href="?ID=<%=Rsp("ID")%>&Sel=Add1&ClassName=Small#Add2">添加主题</A></td> 
                  <td align="center"><a href="?ID=<%=Rsp("ID")%>&Sel=Modify&ClassName=Big#Modify1">编辑</A></td>
                  <td align="center"><a href="?ID=<%=Rsp("ID")%>&Sel=Delete&ClassName=Big" onClick="return delbig()">删除</A></td>
                </tr>
				<%
				Set rsB=Server.CreateObject("adodb.recordset")
				StrSql="select * from P_Class Where ParentClassID="&rsp("ID")&" Order by Xu Asc"
				rsB.open StrSql,conn,1,3
				if Not rsB.eof then
				Do While Not rsB.eof
				Pagesmall=RsB.recordcount
				W=W+1
				%>
				<tr class="table_bg_n1">
                  <td height="22" align="center"><span class="huang">
                    <%Response.Write(W)%>
                  </span></td>
                  <td width="47%" class="padding-left-20 huang"><img src="images/tree_folder3.gif" width="15" height="15">
				  <%IF banben_China=True Then Tw(rsB("ClassName")) End IF%><%IF banben_En=True Then Tw("&nbsp;"&RsB("E_ClassName")) End if%></td>
                  <td colspan="2" align="center">　</td>
                  <td align="center"><a href="?ID=<%=rsB("ID")%>&P_ID=<%=Rsp("ID")%>&Sel=Modify&ClassName=Small#Modify2" class="hui">编辑</a></td>
                  <td align="center"><a href="?ID=<%=rsB("ID")%>&Sel=Delete&ClassName=Small" class="hui" onClick="return delsmall()">删除</a></td>
                </tr>
				<%
				rsB.MoveNext
				Loop
				End if
				W=0
				Rsp.MoveNext
				Loop
				%>
              </table>
              <%
		  End if
		  Rsp.Close
		  %>
          </td>
        </tr>
      </table>
    </form></td>
  </tr>
<tr>
    <td height="150"><form name="form1" method="post" action="?modify=modify">
      <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size">分类设置(<a href="p_class.asp#Add1" style="text-decoration:none;color:#ffffff">排序</a>)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2">
              <table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
                <tr align="center" class="table_bg_n">
                  <td width="10%" height="22"><B>排序</B></td>
                  <td width="64%"><B>类别名称</B></td>
                  <td width="26%"><STRONG>操作</STRONG></td>
                </tr>
				<%
				Set rsB=Server.CreateObject("adodb.recordset")
				StrSql="select * from P_Class Where ParentClassID<>0 Order by Xu Asc"
				rsB.open StrSql,conn,1,3
				if Not rsB.eof then
				Do While Not rsB.eof
				Pagesmall=RsB.recordcount
				W=W+1
				Set rsB2=Server.CreateObject("adodb.recordset")
				StrSql2="select * from P_Class Where ID="&RsB("ParentClassID")&" Order by Xu Asc"
				rsB2.open StrSql2,conn,1,3
				Bigname=rsB2("ClassName")
				rsB2.close
				%>
				<tr class="table_bg_n1">
                  <td height="22" align="center"><span class="huang">
                    <%Response.Write(W)%>
                  </span></td>
                  <td class="padding-left-20 huang"><img src="images/tree_folder3.gif" width="15" height="15">
				  <%IF banben_China=True Then Tw(rsB("ClassName")) End IF%><%IF banben_En=True Then Tw("&nbsp;"&RsB("E_ClassName")) End if%>
				  (<%= Bigname %>)</td>
                  <td align="center"><%if W<>1 then%><a href="?ID=<%=rsB("ID")%>&order=up&ClassName=Small"><img src="images/arrow_up.gif" width="17" height="14" border="0"></a>
                    <%End if %><%if W<>Pagesmall then%><a href="?ID=<%=RsB("ID")%>&order=dowm&ClassName=Small"><img src="images/arrow_down.gif" width="17" height="14" border="0"></a><%End if%></td>
                </tr>
				<%
				rsB.MoveNext
				Loop
				End if
				rsB.close
				%>
              </table>
          </td>
        </tr>
      </table>
    </form></td>
  </tr>
  <!--分类管理<结束>------------------------------------------------------------------>
  <%
  Select case ClassName
  case "Big"
  if Sel="Modify" then
  '--------------------------------------------------------------------------------------------
  Set RsBigModify=Conn.execute("select * from P_Class Where ID="&ID&"")
  IF RsBigModify.eof then
	twScript("操作有误")
  End IF
  %>
  <!--一级分类修改管理<开始>------------------------------------------------------------------>
  <tr>
    <td height="100"><form action="?ClassName=Big&Sel=Modify1" method="post" name="Add" id="Add" onSubmit="return Classname(this)">
      <a name="Modify1"></a>
      <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size"> 修改分类(一级)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
            <%
			'是否有China版本
			IF banben_China=True Then
			%>
			<tr class="table_bg_n">
              <td height="25" align="center">分类名称(China)</td>
              <td class="padding-left-10"><input name="ClassName_key" type="text" id="ClassName_key" size="50" value="<%Response.Write(RsBigModify("ClassName"))%>"></td>
            </tr>
			<%
			End IF
			'是否有English版本
			IF banben_En=True Then
			%>
              <tr class="table_bg_n">
                <td width="30%" height="25" align="center">分类名称(En)</td>
                <td class="padding-left-10"><input name="ClassName_En" type="text" id="ClassName_En" size="50" value="<%Response.Write(RsBigModify("E_ClassName"))%>"></td>
              </tr>
			<%
			End IF
			'结束判断Enlgish
			%>
			
              <tr class="table_bg_n"><input name="ID" type="hidden" id="ID" size="50" value="<%Response.Write(RsBigModify("ID"))%>">
                <td height="22" colspan="2" align="center"><input type="submit" name="Submit" value="保存修改"></td>
              </tr>
          </table></td>
        </tr>
      </table>
    </form></td>
  </tr>
  <%RsBigModify.Close%>
  <!--一级分类修改管理<结束>------------------------------------------------------------------>
  <%
  End IF 
  case "Small"
  if Sel="Add1" then
  %>
  <!--二级分类添加管理<开始>------------------------------------------------------------------>
  <tr>
    <td height="130"><form action="?ClassName=Small&Sel=Add" method="post" name="myform" id="myform" onSubmit="return Classname(this)">
      <a name="Add2"></a>
      <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size"> 添加分类(二级)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
            <tr class="table_bg_n">
              <td height="25" align="center">大类名称</td>
              <td class="padding-left-10"><select name="BigID" id="BigID">
                <%
				Set rsbig=Conn.execute("select * from P_Class Where ParentClassID=0 Order by Xu Asc")
				If Not rsbig.eof then
				Do While Not rsbig.eof
				%>
				<option <%IF Rsbig("ID")=Cint(ID) then response.Write("selected") end if%> value="<%Response.Write(Rsbig("ID"))%>"><%Response.Write(Rsbig("ClassName"))%></option>
				<%
				Rsbig.MoveNext
				loop
				End IF
				Rsbig.Close
				%>
              </select></td>
            </tr>
			<%
			'是否有China版本
			IF banben_China=True Then
			%>
            <tr class="table_bg_n">
              <td height="25" align="center">分类名称(China)</td>
              <td class="padding-left-10"><input name="ClassName_key" type="text" id="ClassName_key" size="50"></td>
            </tr>
			<%
			End IF
			'是否有English版本
			IF banben_En=True Then
			%>
              <tr class="table_bg_n">
                <td width="30%" height="25" align="center">分类名称(En)</td>
                <td class="padding-left-10"><input name="ClassName_En" type="text" id="ClassName_En" size="50"></td>
              </tr>
			<%
			End IF
			'结束判断Enlgish
			%>
			<tr class="table_bg_n">
            <td height="25" align="center">小图</td>
            <td class="padding-left-10"><input name="smpic" type="text" id="smpic" size="41" maxlength="120"><input type="button" name="Submit2" value="上 传" onClick="window.open('upload_flash.asp?formname=myform&editname=smpic&uppath=../products_pic&filelx=jpg','','status=no,scrollbars=no,top=20,left=110,width=420,height=165')">
              <span class="style1">*</span></td>
          </tr>
		  <tr class="table_bg_n">
            <td height="80" align="center"> 主题介绍</td>
            <td class="padding-left-10">
			<input name="Content" type="hidden">
			<iframe id="eWebEditor1" src="../WebEditor/ewebeditor.asp?id=Content&style=s_mini1" frameborder="0" scrolling="no" width="380" height="250"></iframe>			</td>
          </tr>
              <tr class="table_bg_n">
                <td height="22" colspan="2" align="center"><input type="submit" name="Submit" value="确定添加"></td>
              </tr>
          </table></td>
        </tr>
      </table>
    </form></td>
  </tr>
  <!--二级分类添加管理<结束>------------------------------------------------------------------>
  <%
  End if
  if Sel="Modify" then
  %>
  <!--二级分类修改管理<开始>------------------------------------------------------------------>
  <tr>
    <td height="130">
	<form action="?ClassName=Small&Sel=Modify1" method="post" name="myform" id="myform" onSubmit="return Classname(this)">
      <%
	  Set RsSmallModify=Conn.execute("select * from P_Class Where ID="&ID&"")
	  IF RsSmallModify.eof then
	  	twScript("操作有误")
	  End IF
	  %>
	  <a name="Modify2"></a>
	  <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size"> 修改分类(二级)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
              <tr class="table_bg_n">
                <td height="25" align="center">大类名称</td>
                <td class="padding-left-10"><select name="BigID" id="BigID">
                    <%
				Set rsbig=Conn.execute("select * from P_Class Where ParentClassID=0 Order by Xu Asc")
				If Not rsbig.eof then
				Do While Not rsbig.eof
				%>
                    <option <%if Rsbig("ID")=Cint(Request("P_ID")) Then Response.Write("selected") End if%> value="<%Response.Write(Rsbig("ID"))%>"><%Response.Write(Rsbig("ClassName"))%></option>
                    <%
				Rsbig.MoveNext
				loop
				End IF
				Rsbig.Close
				%>
                </select></td>
              </tr>
			 <%
			'是否有China版本
			IF banben_China=True Then
			%>
              <tr class="table_bg_n">
                <td height="25" align="center">分类名称(China)</td>
                <td class="padding-left-10"><input name="ClassName_key" type="text" id="ClassName_key" size="50" value="<%Response.Write(RsSmallModify("ClassName"))%>"></td>
              </tr>
			<%
			End IF
			'是否有English版本
			IF banben_En=True Then
			%>
              <tr class="table_bg_n">
                <td width="30%" height="25" align="center">分类名称(En)</td>
                <td class="padding-left-10"><input name="ClassName_En" type="text" id="ClassName_En" size="50" value="<%Response.Write(RsSmallModify("E_ClassName"))%>"></td>
              </tr>
			<%
			End IF
			'结束判断Enlgish
			%>
			<tr class="table_bg_n">
            <td height="25" align="center">小图</td>
            <td class="padding-left-10"><input name="smpic" type="text" id="smpic" size="41" maxlength="120" value="<%Response.Write(RsSmallModify("pic"))%>"><input type="button" name="Submit2" value="上 传" onClick="window.open('upload_flash.asp?formname=myform&editname=smpic&uppath=../products_pic&filelx=jpg','','status=no,scrollbars=no,top=20,left=110,width=420,height=165')">
              <span class="style1">*</span></td>
          </tr>
		  <tr class="table_bg_n">
            <td height="80" align="center"> 主题介绍</td>
            <td class="padding-left-10">
			<input name="Content" type="hidden" value="<%Response.Write(RsSmallModify("content"))%>">
			<iframe id="eWebEditor1" src="../WebEditor/ewebeditor.asp?id=Content&style=s_mini1" frameborder="0" scrolling="no" width="380" height="250"></iframe>			</td>
          </tr>
              <tr class="table_bg_n"><input name="ID" type="hidden" id="ID" size="50" value="<%Response.Write(RsSmallModify("ID"))%>">
                <td height="22" colspan="2" align="center"><input type="submit" name="Submit" value="保存修改"></td>
              </tr>
          </table></td>
        </tr>
      </table>
	  <%
	  RsSmallModify.close
	  Set RsSmallModify=Nothing
	  %>
    </form></td>
  </tr>
  <!--二级分类修改管理<结束>------------------------------------------------------------------>
  <%
  End IF
  End Select
  %>
   <!--一级分类添加管理<开始>------------------------------------------------------------------>
  <tr>
    <td height="100"><form action="?ClassName=Big&Sel=Add" method="post" name="Add" id="Add" onSubmit="return Classname(this)">
      <a name="Add1"></a>
      <table width="85%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_border">
        <tr bgcolor="#DBE6FF">
          <td colspan="2" class="padding_1"><table width="100%"  border="0" cellpadding="0" cellspacing="0" class="table_bg">
              <tr>
                <td align="center" class="table_bg_size"> 添加分类(一级)</td>
              </tr>
          </table></td>
        </tr>
        <tr bgcolor="#DBE6FF">
          <td colspan="2"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
            <%
			'是否有China版本
			IF banben_China=True Then
			%>
			<tr class="table_bg_n">
              <td height="25" align="center">分类名称(China)</td>
              <td class="padding-left-10"><input name="ClassName_key" type="text" id="ClassName_key" size="50"></td>
            </tr>
			<%
			End IF
			'是否有English版本
			IF banben_En=True Then
			%>
              <tr class="table_bg_n">
                <td width="30%" height="25" align="center">分类名称(English)</td>
                <td class="padding-left-10"><input name="ClassName_En" type="text" id="ClassName_En" size="50"></td>
              </tr>
			<%
			End IF
			'结束判断Enlgish
			%>
              <tr class="table_bg_n">
                <td height="22" colspan="2" align="center"><input type="submit" name="Submit" value="确定添加"></td>
              </tr>
          </table></td>
        </tr>
      </table>
    </form></td>
  </tr>
  <!--一级分类添加管理<结束>------------------------------------------------------------------>
</table>
</body>
</html>
