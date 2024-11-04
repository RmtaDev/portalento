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
}
 
function mostrarHabilidadesLike(listadoHabilidadesLike) {
	console.log ("INFO = " + listadoHabilidadesLike);
	let patronBusqueda = document.getElementById("input-buscador").value;
	let divSugerencias = document.getElementById("sugerencias");
	let ulSugerencias = divSugerencias.querySelector("ul");
	let divGrid = document.getElementById("grid");

	// Si no hay un <ul>, lo creo
	if (!ulSugerencias) {
		ulSugerencias = document.createElement("ul");
    	divSugerencias.appendChild(ulSugerencias);
    // y si lo hay lo vacío	
	} else {
		ulSugerencias.innerHTML = "";
		divSugerencias.classList.add("d-none");
		divGrid.innerHTML = "";		
	}	

	// lo relleno con las habilidades 	
	if (listadoHabilidadesLike.length != 0){
		listadoHabilidadesLike.forEach(habilidad => {
			let liSugerencia = document.createElement("li");
			liSugerencia.textContent = habilidad.nombre;
			ulSugerencias.appendChild(liSugerencia);
		
			liSugerencia.id = habilidad.nombre;
			liSugerencia.addEventListener("click", (evento)=>{
				console.log("HA TOCADO UNA HABLILIDAD " + evento.target.id);
				//sera el li
				let inputHabilidad = document.getElementById("input-buscador");
				inputHabilidad.value = liSugerencia.textContent;
				divSugerencias.classList.add("d-none");
			
				fetch("ObtenerUsuariosPorHabilidad?habilidad=" + evento.target.id)
				.then (respuesta => {
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

				.then(listadoUsuariosPorHabilidad => mostrarUsuariosPorHabilidad(listadoUsuariosPorHabilidad));	
				})
		})
	} else { // si no hay habilidades lo relleno con una indicación al respecto
		liSugerencia = document.createElement("li");
		liSugerencia.textContent = "No existen habilidades coincidentes con el patrón de busqueda introducido";
		ulSugerencias.appendChild(liSugerencia);
	}

	// lo muestro si el patrón de búsqueda no está vacío 
	if (patronBusqueda != "") {
		divSugerencias.classList.remove("d-none");
	}
}

function mostrarUsuariosPorHabilidad(listadoUsuariosPorHabilidad) {
	let divGrid = document.getElementById("grid");
	divGrid.innerHTML = "";

	listadoUsuariosPorHabilidad.forEach(usuario => {
		let divCard = document.createElement("div");
		divCard.classList.add("card");

		let imgCard = document.createElement("img");
		if (usuario.rutaFoto != null){
			imgCard.src = "ObtenerFoto?idfoto=" + usuario.rutaFoto.substring(usuario.rutaFoto.lastIndexOf('\\') + 1);
		} else {
			imgCard.src = "img/avatar.png"
		}
		
		divCard.appendChild(imgCard);

		let h2Card = document.createElement("h2");
		h2Card.innerText = usuario.usuario;
		divCard.appendChild(h2Card);

		let divEdadGenero =  document.createElement("div");
		divEdadGenero.classList.add("contenedor-edad-genero");

		let divEdad =  document.createElement("div");
		divEdad.classList.add("contenedor-edad");
		let labelEdad = document.createElement("label");
		labelEdad.innerText = "Edad";
		let pEdad = document.createElement("p");
		pEdad.innerText = usuario.edad;
		divEdad.appendChild(labelEdad)
		divEdad.appendChild(pEdad);

		let divGenero =  document.createElement("div");
		divGenero.classList.add("contenedor-genero");
		let labelGenero = document.createElement("label");
		labelGenero.innerText = "Genero";
		let pGenero = document.createElement("p");
		pGenero.innerText = usuario.genero;
		divGenero.appendChild(labelGenero);
		divGenero.appendChild(pGenero);

		divEdadGenero.appendChild(divEdad);
		divEdadGenero.appendChild(divGenero);
		divCard.appendChild(divEdadGenero);
		
		let divBoton = document.createElement("div");
		divBoton.classList.add("contenedor-boton");
		let boton = document.createElement("button"); 		
		boton.classList.add("btn", "btn-secondary");
		boton.innerText = "Contactar";
		divBoton.appendChild(boton);
		divCard.appendChild(divBoton);
		
		divGrid.appendChild(divCard);
	});
}
