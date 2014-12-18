<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base_layout.css" />"> 
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.structure.min.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.theme.min.css" />">
<script type="text/javascript" src="<c:url value="https://www.google.com/jsapi" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:insertAttribute name="title" ignore="true" />  
</title>  
</head>  
<body>  
    <table cellspacing="2" align="center">  
        <tr>  
            <td id="header" height="60" colspan="2">
            	<tiles:insertAttribute name="header" />  
            </td>  
        </tr>  
        <tr>  
            <td id="body" height="450">
            	<tiles:insertAttribute name="body" />  
            </td>  
        </tr>  
        <tr>  
            <td id="footer" height="60" colspan="2">
            	<tiles:insertAttribute name="footer" />  
            </td>  
        </tr>  
    </table>  
</body>  
</html> 