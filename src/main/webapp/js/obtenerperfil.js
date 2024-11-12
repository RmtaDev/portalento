
let botonHabilidadOf = "<button onclick=\"borrarHabilidadOfertada(this)\" type=\"submit\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x-lg\" viewBox=\"0 0 16 16\">											<path d=\"M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z\" />	</svg></button>";
let botonHabilidadDe = "<button onclick=\"borrarHabilidadDemandada(this)\" type=\"submit\"><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-x-lg\" viewBox=\"0 0 16 16\">											<path d=\"M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z\" />	</svg></button>";

let listaHabilidadesOfertadas = [];
let listaHabilidadesDemandadas = [];

document.addEventListener("DOMContentLoaded", function() {

	obtenerPerfilUsuario();
	obtenerCategorias();
	console.log("Cargando Info");
	obtenerHabilidadesOfertadas();
	obtenerHabilidadesDemandadas();
	obtenerNotificacionesPendientes();


});

function pintarCampana(notificaciones) {
    const campanaContenedor = document.querySelector('.contenedor-campana');
    if (!campanaContenedor) {
        console.error("No se encontró la campana de notificaciones.");
        return;
    }
    
    let contadorNotificaciones = campanaContenedor.querySelector('.contador-notificaciones');

    if (!contadorNotificaciones) {
        // Crea el contador si no existe
        contadorNotificaciones = document.createElement('span');
        contadorNotificaciones.classList.add('contador-notificaciones');
        campanaContenedor.appendChild(contadorNotificaciones);
    }

    // Actualiza el número de notificaciones
    contadorNotificaciones.textContent = notificaciones.cantidad;
    contadorNotificaciones.style.display = notificaciones.cantidad > 0 ? 'block' : 'none';
}



function obtenerNotificacionesPendientes() {
	fetch('ObtenerNumeroIntercambiosPendientesPorUsuario', { method: 'GET' })
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					respuesta.text().then(cantidadNotificaciones => {
						const notificaciones = { cantidad: parseInt(cantidadNotificaciones) };
						pintarCampana(notificaciones); // Actualiza la campana con el número de notificaciones
					});
					break;
				case 400:
					console.error("Error en la solicitud para obtener notificaciones: 400");
					break;
				case 404:
					console.error("No se encontraron notificaciones pendientes");
					break;
				case 500:
					console.error("Error en el servidor al obtener notificaciones");
					break;
				default:
					console.error("Código de respuesta no manejado: ", respuesta.status);
			}
		})
		.catch(error => console.error('Error en la solicitud al servidor:', error));
}

function pintarHabilidadOfertada(habilidad, divpadre) {
	//crearme el div para le eiqueta
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	let hijosDiv = "<label>" + habilidad.nombre + "</label>" + botonHabilidadOf;
	divHabilidad.innerHTML = hijosDiv.trim();

	divpadre.appendChild(divHabilidad);
}

function pintarHabilidadDemandada(habilidad, divpadre) {
	//crearme el div para le eiqueta
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	let hijosDiv = "<label>" + habilidad.nombre + "</label>" + botonHabilidadDe;
	divHabilidad.innerHTML = hijosDiv.trim();

	divpadre.appendChild(divHabilidad);
}

//function pintarHabilidades (listaHabilidades, divpadre)
//{

//}

function obtenerHabilidadesOfertadas() {
	//pintarHabilidades(null, document.getElementById("container-hb-ofertadas"));
	//FETCH HABILIDAES OFERTADAS
	fetch("ConsultarHabilidadOfertada")//obtengo las habilidades del Servlet Ofertadas
		.then(respuesta => respuesta.json())//lo paso a Objeto javascritp
		.then(listaHabilidadesOfertadas => {
			console.log("Tamaño lista habilidades ofertadas " + listaHabilidadesOfertadas.length);
			let divPadre = document.getElementById("container-hb-ofertadas");
			listaHabilidadesOfertadas.forEach(habilidad => {
				pintarHabilidadOfertada(habilidad, divPadre);
			});//foreach

			// Mostrar el modal si las listas están vacías
			if (listaHabilidadesOfertadas.length == 0) {
				setTimeout(function() {
					document.getElementById("modal").style.display = "block";
				}, 800);
			}

			// Cerrar el modal al hacer clic en el botón
			document.getElementById("cerrar-modal").onclick = function() {
				document.getElementById("modal").style.display = "none";
			};
		});//then json()
	//FOR - PARA CADA HABLIDAD
	//PINTAR HABILIDADES
}

function obtenerHabilidadesDemandadas() {
	//FETCH HABILIDAES DEMANDADAS
	fetch("ConsultarHabilidadDemandada")//obtengo las habilidades del Servlet Ofertadas
		.then(respuesta => respuesta.json())//lo paso a Objeto javascritp
		.then(listaHabilidadesDemandadas => {
			console.log("Tamaño lista habilidades demandadas " + listaHabilidadesDemandadas.length);
			let divPadre = document.getElementById("container-hb-demandadas");
			listaHabilidadesDemandadas.forEach(habilidad => {
				pintarHabilidadDemandada(habilidad, divPadre);
			});//foreach

			// Mostrar el modal si las listas están vacías
			if (listaHabilidadesDemandadas.length == 0) {
				setTimeout(function() {
					document.getElementById("modal").style.display = "block";
				}, 800);
			}

			// Cerrar el modal al hacer clic en el botón
			document.getElementById("cerrar-modal").onclick = function() {
				document.getElementById("modal").style.display = "none";
			};
		});//then json()
	//FOR - PARA CADA HABLIDAD
	//PINTAR HABILIDADES

}

function borrarHabilidadOfertada(evento) {
	console.log("Ha tocado borrar una habilidad Ofertada");
	console.log(evento.parentElement.id);
	let url = "BorrarHabilidadesOfertadas?idHabilidad=" + evento.parentElement.id;
	fetch(url, {
		method: "DELETE"
	})
		.then(respuesta => {
			switch (respuesta.status) {
				case 204:
					alert("Habilidad borrada correctamente");
					//window.location.href = "perfil.html";
					//TODO borrar botón
					evento.parentElement.remove();
					break;
				case 400:
					alert("Error en la petición");
					break;
				case 500:
					alert("Error en el servidor");
					break;
			}
		})
		.catch(error => {
			console.error("Error en la solicitud:", error);
			mensajeDiv.innerHTML += 'Error en la conexión con el servidor.<br>';
		});
}

function borrarHabilidadDemandada(evento) {
	console.log("Ha tocado borrar una habilidad Demandada");
	console.log(evento.parentElement.id);
	let url = "BorrarHabilidadesDemandadas?idHabilidad=" + evento.parentElement.id;
	fetch(url, {
		method: "DELETE"
	})
		.then(respuesta => {
			switch (respuesta.status) {
				case 204:
					alert("Habilidad borrada correctamente");
					//window.location.href = "perfil.html";
					//TODO borrar botón
					evento.parentElement.remove();
					break;
				case 400:
					alert("Error en la petición");
					break;
				case 500:
					alert("Error en el servidor");
					break;
			}
		})
		.catch(error => {
			console.error("Error en la solicitud:", error);
			mensajeDiv.innerHTML += 'Error en la conexión con el servidor.<br>';
		});
}


function obtenerPerfilUsuario() {

	fetch('ObtenerPerfilUsuario', {
	})
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Perfil obtenido");
					respuesta.json().then(infoUsuario => mostrarPerfil(infoUsuario));
					break;

				case 404:
					console.log("Nombre de usuario repetido");
					break;

				case 500:
					console.log("Error al obtener el perfil");
					break;
			}
		})

		.catch(error => {
			console.error('Error al obtener el perfil de usuario:', error);
		})
}
function mostrarPerfil(infousuario) {

	console.log("INFO = " + infousuario);
	document.getElementById("usuario").innerHTML = infousuario.usuario;
	document.getElementById("nombre").innerHTML = infousuario.nombre;
	document.getElementById("apellidos").innerHTML = infousuario.apellidos;
	document.getElementById("edad").innerHTML = infousuario.edad;
	document.getElementById("genero").innerHTML = infousuario.genero;
	document.getElementById("telefono").innerHTML = infousuario.telefono;
	document.getElementById("email").innerHTML = infousuario.email;
	document.getElementById('hablasobreti').innerHTML = infousuario.hablaSobreTi;
	document.getElementById("foto").src = "ObtenerFoto?idfoto=" + infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
}

function obtenerCategorias() {

	fetch('ListadoCategoriasHabilidadesServlet', {
	})
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Listado obtenido");
					respuesta.json().then(categorias => mostrarCategorias(categorias));
					break;


				case 500:
					console.log("Error al obtener el listado");
					break;
			}
		})

		.catch(error => {
			console.error('Error al obtener el listado:', error);
		})
	console.log("ha pedido las categorias")
}

function mostrarCategorias(categorias) {
	console.log("INFO = " + categorias);
	let selectOfertadas = document.getElementById("habilidadOfertada");

	categorias.sort((c1, c2) => {
		let res = 0;
		if (c1.nombre == 'Otras') {
			res = 1;
		}
		else if (c2.nombre == 'Otras') {
			res = -1;

		} else {
			res = c1.nombre.localeCompare(c2.nombre);
		}
		return res;

	});
	categorias.forEach(categoria => {

		let option = document.createElement("option");
		option.innerHTML = categoria.nombre;
		option.value = categoria.id_categoria;
		selectOfertadas.appendChild(option);
	})

	console.log("INFO = " + categorias);
	let selectDemandadas = document.getElementById("habilidadDemandada");

	categorias.forEach(categoria => {

		let option = document.createElement("option");
		option.innerHTML = categoria.nombre;
		option.value = categoria.id_categoria;
		selectDemandadas.appendChild(option);
	})
};





