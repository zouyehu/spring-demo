<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>
<script
	src="<c:url value='/commons/js/jquery.1.10.2.min.js'/>"
	type="text/javascript"></script>
<script>
	var as = '';
	$(function() {

		$("#apiTestButton").click(function() {
			var params = {};
			var transCode = $("#transCode").val();
			var requestInfo = $("#requestInfo").val();
			var requestBodyJson = $("#requestBodyJson").val();
			var url = $("#requestUrl").val();
			var heard = $("#requestHeard").val();
			$.ajax({
				url : url,
				type : "post",
				cache : false,
				dataType : "json",
				data : {
					transCode : transCode,
					requestInfo : requestInfo,
					requestBodyJson : requestBodyJson,
					requestHeard : heard
				},
				beforeSend : function(request) {
					request.setRequestHeader("platformType", "2");
					request.setRequestHeader("APPTOKEN", heard);
				},
				success : function(data) {
					$("#resultShow").val(JSON.stringify(data));
				},
				error : function(data) {
					$("#resultShow").val(JSON.stringify(data));
				}
			});

		});
	})

</script>
</head>
<body>

	<table border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td><input type="hidden" name="appkey" id="appkey"
					value="12129701"> <input type="hidden" name="api_soure"
					id="api_soure" value="1">
					<table class="parameters" width="500" border="0" cellpadding="4"
						cellspacing="0">
						<tbody>
							<tr>
								<td width="160" align="right">请求地址：</td>
								<td width="340"><input type="text" id="requestUrl"
									value="<c:url value='/sysAccess/sysSubmit.do'/>" /></td>
							</tr>
							<tr>
								<td align="right">transCode名称：</td>
								<td><span id="SipApinameDiv">
										<input name="sip_apiname"
										id="transCode" style="width: 195px;">
								</span> &nbsp;</td>
							</tr>

							<tr>
								<td align="right">requestInfo名称：</td>
								<td><span id="SipRequestInfoDiv">
										<input name="sip_requestInfo"
										id="requestInfo" style="width: 195px;">
								</span> &nbsp;</td>
							</tr>
							<tr>
								<td align="right">requestHeard值：</td>
								<td><span id="SipRequestInfoDiv"> 
										<input name="sip_requestInfo"
										id="requestHeard" style="width: 195px;">
								</span> &nbsp;</td>
							</tr>
						</tbody>
					</table></td>
			</tr>

			<tr>
				<td>
					<table width="500" border="0" cellpadding="4" cellspacing="0">
						<tbody>
							<tr>
								<td width="160">&nbsp;</td>
								<td width="340" align="left"><input id="apiTestButton"
									type="button" value="提交测试"
									style="width: 60px; height: 24px; *padding-top: 3px; border: #666666 1px solid; cursor: pointer">
								</td>
							</tr>
						</tbody>
					</table>
				</td>

				<td valign="top">API请求参数： <br> <textarea name="param"
						id="requestBodyJson" cols="120" rows="10"></textarea> <br> <br>
					API返回结果： <br> <textarea name="resultShow" id="resultShow"
						cols="120" rows="10"></textarea> <br> <br>
				</td>
			</tr>

		</tbody>
	</table>

</body>
</html>