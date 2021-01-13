<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto ASR Final</title>
</head>
<body>
<h1>Práctica final de Arquitectura de Servicios de Red</h1>
<hr />
<h2><i>Ignacio Ampuero</i></h2>
<h2><i>Alvaro del Aguila</i></h2>
<ul>
		<li><a href="listar">Listar</a></li>
		<li>
		<form action="insertar" method="GET">
			Almacenar en la base de datos.<br>
			Se almacenara la traduccion del texto introducido.
			<input type="text" name="palabra" style="overflow: auto hidden;">
			<input type="submit" value="Guardar en Cloudant">
		</form>
		</li>
		
		<li>
		<form action="hablar" method="GET">
			Traductor(ES-EN)<br>
			<input type="text" name="palabra" style="overflow: auto hidden;">
			Escuchar pronunciacion<input type="checkbox" name="pr" value="true">
			<input type="submit" value="Traducir">
		</form>
		</li>

		<li>
		<form action="tono" method="GET">
			Obtener tono del texto(EN)<br>
			<input type="text" name="palabra" style="overflow: auto hidden;">
			<input type="submit" value="Obtener tono">
		</form>
		</li>

</ul>
</body>
</html>