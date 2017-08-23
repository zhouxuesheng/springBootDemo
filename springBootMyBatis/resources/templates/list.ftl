<!DOCTYPE html>

<html lang="en">

<body>
<table>
	<#list list as testP>
		<tr>
			<td>${testP.pName}</td>
			<td>${testP.pSex!'未知'}</td>
		</tr>
	</#list>
</table>
</body>

</html>