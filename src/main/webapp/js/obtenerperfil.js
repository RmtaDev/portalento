
// Botones para eliminar habilidades ofertadas y demandadas
let botonHabilidadOf = "<button onclick=\"borrarHabilidadOfertada(this)\" type=\"submit\">...</button>";
let botonHabilidadDe = "<button onclick=\"borrarHabilidadDemandada(this)\" type=\"submit\">...</button>";

// Variables para almacenar las listas de habilidades
let listaHabilidadesOfertadas = [];
let listaHabilidadesDemandadas = [];

// Al cargar el documento, obtenemos el perfil, categorías, habilidades y notificaciones
document.addEventListener("DOMContentLoaded", function() {
	obtenerPerfilUsuario();
	obtenerCategorias();
	obtenerHabilidadesOfertadas();
	obtenerHabilidadesDemandadas();
	obtenerNotificacionesPendientes();

	// Evento para redirigir a intercambios.html al hacer clic en la campana de notificaciones
	const campanaContenedor = document.querySelector('.contenedor-campana');
	if (campanaContenedor) {
		campanaContenedor.addEventListener('click', function() {
			window.location.href = 'intercambios.html';
		});
	} else {
		console.error("No se encontró la campana de notificaciones.");
	}
});

// Función para mostrar el número de notificaciones en la campana
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
	// Actualiza el contador de notificaciones
	contadorNotificaciones.textContent = notificaciones.cantidad;
	contadorNotificaciones.style.display = notificaciones.cantidad > 0 ? 'block' : 'none';
}

// Función para obtener el número de notificaciones pendientes del servidor
function obtenerNotificacionesPendientes() {
	fetch('ObtenerNumeroIntercambiosPendientesPorUsuario', { method: 'GET' })
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					respuesta.text().then(cantidadNotificaciones => {
						const notificaciones = { cantidad: parseInt(cantidadNotificaciones) };
						pintarCampana(notificaciones);
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

// Función para renderizar habilidades ofertadas en el contenedor correspondiente
function pintarHabilidadOfertada(habilidad, divpadre) {
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	divHabilidad.innerHTML = `<label>${habilidad.nombre}</label>${botonHabilidadOf}`;
	divpadre.appendChild(divHabilidad);
}

// Función para renderizar habilidades demandadas en el contenedor correspondiente
function pintarHabilidadDemandada(habilidad, divpadre) {
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	divHabilidad.innerHTML = `<label>${habilidad.nombre}</label>${botonHabilidadDe}`;
	divpadre.appendChild(divHabilidad);
}

// Función para obtener habilidades ofertadas del servidor y mostrarlas en la página
function obtenerHabilidadesOfertadas() {
	fetch("ConsultarHabilidadOfertada")
		.then(respuesta => respuesta.json())
		.then(listaHabilidadesOfertadas => {
			let divPadre = document.getElementById("container-hb-ofertadas");
			listaHabilidadesOfertadas.forEach(habilidad => {
				pintarHabilidadOfertada(habilidad, divPadre);
			});

			// Mostrar un modal si no hay habilidades ofertadas
			if (listaHabilidadesOfertadas.length == 0) {
				setTimeout(() => document.getElementById("modal").style.display = "block", 800);
			}
			document.getElementById("cerrar-modal").onclick = () => {
				document.getElementById("modal").style.display = "none";
			};
		});
}

// Función para obtener habilidades demandadas del servidor y mostrarlas en la página
function obtenerHabilidadesDemandadas() {
	fetch("ConsultarHabilidadDemandada")
		.then(respuesta => respuesta.json())
		.then(listaHabilidadesDemandadas => {
			let divPadre = document.getElementById("container-hb-demandadas");
			listaHabilidadesDemandadas.forEach(habilidad => {
				pintarHabilidadDemandada(habilidad, divPadre);
			});

			// Mostrar un modal si no hay habilidades demandadas
			if (listaHabilidadesDemandadas.length == 0) {
				setTimeout(() => document.getElementById("modal").style.display = "block", 800);
			}
			document.getElementById("cerrar-modal").onclick = () => {
				document.getElementById("modal").style.display = "none";
			};
		});
}

// Función para borrar una habilidad ofertada al hacer clic en el botón de eliminar
function borrarHabilidadOfertada(evento) {
	let url = "BorrarHabilidadesOfertadas?idHabilidad=" + evento.parentElement.id;
	fetch(url, { method: "DELETE" })
		.then(respuesta => {
			switch (respuesta.status) {
				case 204:
					alert("Habilidad borrada correctamente");
					evento.parentElement.remove(); // Elimina el elemento del DOM
					break;
				case 400:
					alert("Error en la petición");
					break;
				case 500:
					alert("Error en el servidor");
					break;
			}
		})
		.catch(error => console.error("Error en la solicitud:", error));
}

// Función para borrar una habilidad demandada al hacer clic en el botón de eliminar
function borrarHabilidadDemandada(evento) {
	let url = "BorrarHabilidadesDemandadas?idHabilidad=" + evento.parentElement.id;
	fetch(url, { method: "DELETE" })
		.then(respuesta => {
			switch (respuesta.status) {
				case 204:
					alert("Habilidad borrada correctamente");
					evento.parentElement.remove(); // Elimina el elemento del DOM
					break;
				case 400:
					alert("Error en la petición");
					break;
				case 500:
					alert("Error en el servidor");
					break;
			}
		})
		.catch(error => console.error("Error en la solicitud:", error));
}

// Función para obtener el perfil del usuario y mostrarlo en la página
function obtenerPerfilUsuario() {
	fetch('ObtenerPerfilUsuario')
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
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
		.catch(error => console.error('Error al obtener el perfil de usuario:', error));
}

// Función para mostrar los datos del perfil del usuario en el DOM
function mostrarPerfil(infousuario) {
	document.getElementById("usuario").innerHTML = infousuario.usuario;
	document.getElementById("nombre").innerHTML = infousuario.nombre;
	document.getElementById("apellidos").innerHTML = infousuario.apellidos;
	document.getElementById("edad").innerHTML = infousuario.edad;
	document.getElementById("genero").innerHTML = infousuario.genero;
	document.getElementById("telefono").innerHTML = infousuario.telefono;
	document.getElementById("email").innerHTML = infousuario.email;
	document.getElementById('hablasobreti').innerHTML = infousuario.hablaSobreTi;
	document.getElementById("foto").src = "ObtenerFoto?idfoto=" + infousuario.rutaFoto.split('\\').pop();
}

// Función para obtener la lista de categorías de habilidades y mostrarlas en los select de habilidades
function obtenerCategorias() {
	fetch('ListadoCategoriasHabilidadesServlet')
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					respuesta.json().then(categorias => mostrarCategorias(categorias));
					break;
				case 500:
					console.log("Error al obtener el listado");
					break;
			}
		})
		.catch(error => console.error('Error al obtener el listado:', error));
}

// Función para mostrar las categorías en los select de habilidades ofertadas y demandadas
function mostrarCategorias(categorias) {
	let selectOfertadas = document.getElementById("habilidadOfertada");

	// Ordena las categorías y luego las añade al select
	categorias.sort((c1, c2) => {
		if (c1.nombre === 'Otras') return 1;
		if (c2.nombre === 'Otras') return -1;
		return c1.nombre.localeCompare(c2.nombre);
	});

	// Agrega las opciones a los select correspondientes
	categorias.forEach(categoria => {
		let option = document.createElement("option");
		option.innerHTML = categoria.nombre;
		option.value = categoria.id_categoria;
		selectOfertadas.appendChild(option);
	});

	let selectDemandadas = document.getElementById("habilidadDemandada");
	categorias.forEach(categoria => {
		let option = document.createElement("option");
		option.innerHTML = categoria.nombre;
		option.value = categoria.id_categoria;
		selectDemandadas.appendChild(option);
	});
};
