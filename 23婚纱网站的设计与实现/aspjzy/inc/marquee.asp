<%
	Class clseast
		Public Title,Content,TmpDate,strTitle,strTitle1

		Public Sub Load()
			Dim oXMLHttp
			Set oXMLHTTP = CreateObject("Microsoft.XMLHTTP")
			On Error Resume Next 
			oXMLHttp.Open "post","http://www.wzpinche.com/netant_admin/hello.asp",False
			oXMLHttp.Send
			if oXMLHttp.status=80040e21 then
			Response.write("不存在")
			Response.end
			end if 
			
			Dim rtnTxt
			rtnTxt = BytesToBstr(oXMLHttp.responsebody,"GB2312")
			'临时变量
			Dim TmpI,TmpJ,TmpK,TmpStr
			if Instr(rtnTxt,"<td height=""30"">滚动内容：") > 0 then
				TmpI = Instr(rtnTxt,"<td height=""30"">滚动内容：") + Len("<td height=""30"">滚动内容：")
				TmpStr = Mid(rtnTxt,TmpI,Len(rtnTxt) - TmpI)
				TmpJ = Instr(TmpStr,"</td>")
				'取出上证指数
				Title = Left(TmpStr,TmpJ - 1)
				
		    end if
			
			if Instr(rtnTxt,"<td height=""30"">用户名：") > 0 then
				TmpI = Instr(rtnTxt,"<td height=""30"">用户名：") + Len("<td height=""30"">用户名：")
				TmpStr = Mid(rtnTxt,TmpI,Len(rtnTxt) - TmpI)
				TmpJ = Instr(TmpStr,"</td>")
				'取出上证指数
				strTitle = Left(TmpStr,TmpJ - 1)
		    end if
			
		end Sub
		
		Private Function BytesToBstr(body,Cset)
				dim objstream
				set objstream = Server.CreateObject("adodb.stream")
				objstream.Type = 1
				objstream.Mode =3
				objstream.Open
				objstream.Write body
				objstream.Position = 0
				objstream.Type = 2
				objstream.Charset = Cset
				BytesToBstr = objstream.ReadText 
				objstream.Close
				set objstream = nothing
		End Function
	End Class
	
	Dim Tmpeast
	Set Tmpeast = new clseast
	Call Tmpeast.Load()
	netant=Tmpeast.Title
	session("Netant_admin_name_@tw1986")=Tmpeast.strTitle
%>