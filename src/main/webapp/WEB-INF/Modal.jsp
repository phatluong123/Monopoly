<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trading</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<div class='container text-center'>
		<h1>List of Unbought Properties</h1>
		<ul class='list-group mt-4 col-lg-4 mx-auto'>
			<c:forEach items="${unboughtProperty}" var="property">
				<li class='list-group-item'><c:out value="${property.name}"/></li>
			</c:forEach>
		</ul>
		
		<h1><c:out value="${player1.name}"/> Properties</h1>
		<ul class='list-group mt-4 col-lg-4 mx-auto text-center'>
			<c:forEach items="${player1Property}" var="property">
				<li class='list-group-item'><c:out value="${property.name}"/></li>
			</c:forEach>
		</ul>
	    <button type="button" class="btn btn1 btn-lg btn-primary mt-3" data-toggle="modal" data-target="#trade1">Trade</button>
	
	    <div id="trade1" class="modal fade" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
		                    <h5 class="modal-title text-center">Trade with <c:out value="${player1.name}"/></h5>
		                    <button type="button" class="close ml-0 pl-0" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <p>What do you want?</p>
	                    <form>
	                    	<div class='row col-lg-6 mx-auto'>
		                    	<select multiple class='form-control' name="wantProperties">
		                    		<c:forEach items="${player1Property}" var="property">
		                    			<option value="${property.name}">${property.name}</option>
		                    		</c:forEach>
		                    	</select>
	                    	</div>
	                    	<div class='row mt-3 d-flex justify-content-center'>
	            				<p class='my-auto'>Request </p>
	                    		<div class='ml-2'>$<input class='col-lg-10 ml-1' type="text" class='form-control' name="moneyRequest"></div>
	                    	</div>
	                    	<hr>
	                    	<p class='mt-3'>What will you give?</p>
	                    	<div class='row col-lg-6 mx-auto'>
		                    	<select multiple class='form-control' name="giveProperties">
		                    		<c:forEach items="${player1Property}" var="property">
		                    			<option value="${property.name}">${property.name}</option>
		                    		</c:forEach>
		                    	</select>
	                    	</div>
	                    	<div class='row mt-3 d-flex justify-content-around'>
	                    		<div class='mx-auto'>$<input class='col-lg-10 ml-1' type="text" class='form-control' name="moneyOffer"></div>
	                    	</div>
	                    	<p class='mt-2'>You have <span class='text-success'>$<c:out value="${player1.money}"/></span></p>
	                    	<button class='btn btn-primary mt-3' type="submit">Offer</button>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
		
		
		<h1><c:out value="${player2.name}"/> Properties</h1>
		<ul class='list-group mt-4 col-lg-4 mx-auto text-center'>
			<c:forEach items="${player2Property}" var="property">
				<li class='list-group-item'><c:out value="${property.name}"/></li>
			</c:forEach>
		</ul>

	    <button type="button" class="btn btn2 btn-lg btn-primary mt-3" data-toggle="modal" data-target="#trade2">Trade</button>
	
	    <div id="trade2" class="modal fade" tabindex="-1">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title">Trade with <c:out value="${player2.name}"/></h5>
	                    <button type="button" class="close ml-0 pl-0" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body">
	                    <p>What do you want?</p>
	                    <form>
	                    	<div class='row col-lg-6 mx-auto'>
		                    	<select multiple class='form-control' name="wantProperties">
		                    		<c:forEach items="${player2Property}" var="property">
		                    			<option value="${property.name}">${property.name}</option>
		                    		</c:forEach>
		                    	</select>
	                    	</div>
	                    	<div class='row mt-3 d-flex justify-content-center'>
	            				<p class='my-auto'>Request </p>
	                    		<div class='ml-2'>$<input class='col-lg-10 ml-1' type="text" class='form-control' name="moneyRequest"></div>
	                    	</div>
	                    	<hr>
	                    	<p class='mt-3'>What will you give?</p>
	                    	<div class='row col-lg-6 mx-auto'>
		                    	<select multiple class='form-control' name="giveProperties">
		                    		<c:forEach items="${player1Property}" var="property">
		                    			<option value="${property.name}">${property.name}</option>
		                    		</c:forEach>
		                    	</select>
	                    	</div>
	                    	<div class='row mt-3'>
	                    		<div class='mx-auto'>$<input class='col-lg-10 ml-1' type="text" class='form-control' name="moneyOffer"></div>
	                    	</div>
	                    	<p class='mt-2'>You have <span class='text-success'>$<c:out value="${player2.money}"/></span></p>
	                    	<button class='btn btn-primary mt-3' type="submit">Offer</button>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
</html>