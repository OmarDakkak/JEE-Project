<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Banque</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div id="formBanque">
		<form action="controller.do" method="post">
			<table>
				<tr>
					<td>Code 1 :</td>
					<td><input type="text" name="code1"/></td>
				</tr>
				<tr>
					<td>Code 2 :</td>
					<td><input type="text" name="code2"/></td>
				</tr>
				<tr>
					<td>Montant :</td>
					<td><input type="text" name="montant"/></td>
				</tr>
				<tr>
					<td>Nom du nouveau budget :</td>
					<td><input type="text" name="categorie"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" name="action" value="verser"/>
						<input type="submit" name="action" value="retirer"/>
						<input type="submit" name="action" value="virement"/>
						<input type="submit" name="action" value="ajouter"/>
						<input type="submit" name="action" value="update"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<table class="table_show">
			<tr>
				<th>Code</th><th>Nom du budget</th><th>Solde</th><th>Date de creation</th><th>Active</th>
			</tr>
			<c:forEach items="${comptes}" var="cp">
				<tr>
					<td>${ cp.code }</td>
					<td>${ cp.categorie }</td>
					<td>${ cp.solde }</td>
					<td>${ cp.dateCreation }</td>
					<td>${ cp.active }</td>
					<td><a href="controller.do?action=delete&code=${ cp.code }">Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>