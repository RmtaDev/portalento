function habilidadesLike() {
	let valorConsulta = document.getElementById("input-buscador").value;
	let url = "BuscadorHabServlet?consulta=" + valorConsulta;

	fetch(url, {
		method: 'GET'
	})
	.then(respuesta => {
		switch (respuesta.status) {
			case 200:
				return respuesta.json();
			case 400:
				alert("No se encontró el recurso");
                break;
			case 500:
            	alert("Error en el servidor");
				break;
		}
	})
	
	.then(listadoHabilidadesLike => mostrarHabilidadesLike(listadoHabilidadesLike));

 
function mostrarHabilidadesLike(listadoHabilidadesLike) {
	console.log ("INFO = " + listadoHabilidadesLike);
	let divSugerencias = document.getElementById("sugerencias");
	let ulSugerencias = document.createElement("ul");
	
	//borrar todos los lis del ul del div
	listadoHabilidadesLike.forEach((hablidad, indice) => {
		let liSugerencia = document.createElement("li");
		liSugerencia.innerHTML = hablidad.nombre;
		ulSugerencias.appendChild(liSugerencia);
	})
	divSugerencias.appendChild(ulSugerencias);
	divSugerencias.classList.remove("d-none");


	
	/* getElementByIdSugerencias, Crearul, crearli, 
	document.getElementById ("nombre").innerHTML = infoHabilidadesLike.nombre
	document.getElementById ("fotoPerfil").src = "ObtenerFoto?idfoto="+infoHabilidadesLike.rutaFoto.substring(infoHabilidadesLike.rutaFoto.lastIndexOf('\\') + 1);
	//fotosperfil\foto1728286525650 */
}

}