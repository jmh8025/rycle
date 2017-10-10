<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>LYCLEs</title>

    <!-- font -->
    <link rel='stylesheet' href='https://cdn.rawgit.com/openhiun/hangul/14c0f6faa2941116bb53001d6a7dcd5e82300c3f/nanumbarungothic.css'>
    
    <!-- css -->j
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/SpringTiles/css/reset1.css">
    <link rel="stylesheet" href="/SpringTiles/css/fontello.css"> 
    <link rel="stylesheet" href="/SpringTiles/css/style1.css">
    
    <!-- js -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/SpringTiles/js/memberreg.js"></script>
    <script src="/SpringTiles/js/common170908.js"></script>
    <script src="/SpringTiles/js/board.js"></script>


</head>
<body>
    <!-- header -->
    <tiles:insertAttribute name="header" />
    
    <section id="main">
    	<!-- sidebar -->
    	<tiles:insertAttribute name="sidebar" />
       
        <section id="content">
			<tiles:insertAttribute name="content" />            
        </section>
    </section>

	<!-- footer -->
	
	<tiles:insertAttribute name="footer" />
    
</body>

</html>