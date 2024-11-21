document.addEventListener("DOMContentLoaded", function() {
	obtenerUsuarioSesion();
	obtenerIntercambios();
});

let listaIntercambios = [];
let nintercambio = 0;//levamos la cuenta del número de intermcabios renderizado


/**
 * 
 * usuarioUno = {
				
				nombre: nombreUsuario1,
				id: intercambio.id_usuario_demandada,
				ofertada: habilidadUsuario1,
				foto: fotoUsuario1.src 
			}
			
			usuarioDos = {
				
				nombre: nombreUsuario2,
				id: intercambio.id_usuario_ofertada,
				ofertada: habilidadUsuario2,
				foto: fotoUsuario2.src
			}
			
 * intercambioAux = {
				idintercambio: intercambio.idIntercambio,
				usuario1: usuarioUno,
				usuario2: usuarioDos
			}
			
		listaIntercambios.push(intercambioAux)
 */
let intercambioAux;
let usuarioUno;
let usuarioDos;

let usuarioSesionId = null;
let estadoBD = "";
let textoEstado = "";
let claseEstado = "";



function obtenerUsuarioSesion() {
	fetch('ObtenerUsuarioSesion')
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("ID obtenido");
					return respuesta.text().then(id => {
						usuarioSesionId = id;
						console.log("Usuario: " + usuarioSesionId);
					});
				case 404:
					console.log("Error en la solicitud");
					break;
				case 500:
					console.log("Error al obtener el ID");
					break;
			}
		})
		.catch(error => {
			console.error('Error al obtener el usuario de sesión:', error);
		});

}

function pintarActivo(numintercambio) {
	let intercambioActivo = listaIntercambios[numintercambio]
	let divUsuario1 = document.getElementsByClassName("user-info")[0]
	let divUsuario2 = document.getElementsByClassName("user-info")[1]

	divUsuario1.children[0].src = intercambioActivo.usuario1.foto;
	divUsuario1.children[1].innerHTML = intercambioActivo.usuario1.nombre;
	divUsuario1.children[2].innerHTML = intercambioActivo.usuario1.ofertada;

	divUsuario2.children[0].src = intercambioActivo.usuario2.foto;
	divUsuario2.children[1].innerHTML = intercambioActivo.usuario2.nombre;
	divUsuario2.children[2].innerHTML = intercambioActivo.usuario2.ofertada;

	document.getElementById("btnEnviar").dataset.numintercambio = numintercambio;
	document.getElementById("btnEnviar").removeEventListener('click', enviarMensaje)
	document.getElementById("btnEnviar").addEventListener('click', enviarMensaje)
}

function mostrarIntercambioActivo(intercambios) {
	if (intercambios.length == 0) {
		//no tiene intermcabios
		document.getElementsByClassName("mensajeSin")[0].style.display = "block"
		document.getElementsByClassName("chat-container")[0].style.display = "none"
		document.getElementsByClassName("table-container")[0].style.display = "none"

	} else {
		//tiene intermcabios
		//coger el primero
		//si es aceptado/rechazado o pendiente

		let estado = intercambios[0].estado
		console.log("Estado = " + estado);
		switch (estado) {
			case "ACEPTADO":
				pintarActivo(0)
				break;
			case "RECHAZADO":
				pintarActivo(0)
				break;
			case "PENDIENTE":
				pintarActivo(0)
				break;
		}
	}

}


function obtenerIntercambios() {
	fetch('ObtenerTodosLosIntercambiosCursados')
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Intercambios obtenidos");
					return respuesta.json()
						.then(infoIntercambios => {
							mostrarIntercambios(infoIntercambios);
							mostrarIntercambioActivo(infoIntercambios)
						});
				case 404:
					console.log("Error en la solicitud");
					break;
				case 500:
					console.log("Error al obtener los intercambios");
					break;
			}
		})
		.catch(error => {
			console.error('Error al obtener los intercambios:', error);
		});
}



function mostrarIntercambios(infoIntercambios) {
	console.log("INFO = " + infoIntercambios);


	let nombreUsuario1, habilidadUsuario1, nombreUsuario2, habilidadUsuario2;

	infoIntercambios.forEach(intercambio => {

		//Coge la tabla y crea la fila
		let tabla = document.getElementById("tabla-intercambios");
		let tr = document.createElement("tr");
		tr.id = intercambio.idIntercambio;
		tr.classList.add("fila");
		tabla.appendChild(tr);

		//Crea columna icono estado
		let icono = document.createElement("td");
		icono.classList.add("icono-estado");
		tr.appendChild(icono);
		estadoBD = intercambio.estado;




		//Crea columna de foto usuario 1
		let contenedorFoto1 = document.createElement("td");
		tr.appendChild(contenedorFoto1);
		contenedorFoto1.classList.add("foto-usuario1");
		let picture1 = document.createElement("picture");
		contenedorFoto1.appendChild(picture1);
		let fotoUsuario1 = document.createElement("img");
		picture1.appendChild(fotoUsuario1);


		//Crea columna de nombre y habilidad usuario 1
		let usuario1 = document.createElement("td");
		usuario1.classList.add("nombre-usuario1");
		let nombre1 = document.createElement("p");
		nombre1.classList.add("nombre");

		usuario1.appendChild(nombre1);
		let habilidad1 = document.createElement("p");

		usuario1.appendChild(habilidad1);
		tr.appendChild(usuario1);


		//Crea columna de estado de intercambio
		let estadoIntercambio = document.createElement("td");

		let estado = document.createElement("p");

		estadoIntercambio.appendChild(estado);
		tr.appendChild(estadoIntercambio);


		//Crea columna de foto usuario 2
		let contenedorFoto2 = document.createElement("td");
		tr.appendChild(contenedorFoto2);
		contenedorFoto2.classList.add("foto-usuario2");
		let picture2 = document.createElement("picture");
		contenedorFoto2.appendChild(picture2);
		let fotoUsuario2 = document.createElement("img");
		picture2.appendChild(fotoUsuario2);


		//Crea columna de nombre y habilidad usuario 2
		let usuario2 = document.createElement("td");
		usuario2.classList.add("nombre-usuario2");
		let nombre2 = document.createElement("p");
		nombre2.classList.add("nombre");

		usuario2.appendChild(nombre2);
		let habilidad2 = document.createElement("p");

		usuario2.appendChild(habilidad2);
		tr.appendChild(usuario2);


		//Crea columna de botones y mensajes
		let botones = document.createElement("td");
		botones.classList.add("botones");
		tr.appendChild(botones);


		//IDENTIFICAR USUARIO 1 Y USUARIO 2
		if (usuarioSesionId == intercambio.id_usuario_ofertada) {
			//idintercambio, idemisor, idreceptor, mensaje, 

			nombreUsuario1 = intercambio.nombre_usuario_ofertada;
			habilidadUsuario1 = intercambio.nombre_habilidad_demandada;
			fotoUsuario1.src = "ObtenerFoto?idfoto=" + intercambio.ruta_foto_ofertada.split('\\').pop();
			nombreUsuario2 = intercambio.nombre_usuario_demandada;
			habilidadUsuario2 = intercambio.nombre_habilidad_ofertada;
			fotoUsuario2.src = "ObtenerFoto?idfoto=" + intercambio.ruta_foto_demandada.split('\\').pop();

			nombre1.innerHTML = nombreUsuario1;
			habilidad1.innerHTML = habilidadUsuario1;
			nombre2.innerHTML = nombreUsuario2;
			habilidad2.innerHTML = habilidadUsuario2;

			usuarioUno = {

				nombre: nombreUsuario1,
				id: intercambio.id_usuario_ofertada,
				ofertada: habilidadUsuario1,
				foto: fotoUsuario1.src
			}

			usuarioDos = {

				nombre: nombreUsuario2,
				id: intercambio.id_usuario_demandada,
				ofertada: habilidadUsuario2,
				foto: fotoUsuario2.src
			}

			actualizarEnlaces(contenedorFoto1, usuario1, picture1, fotoUsuario1, nombre1, habilidad1, intercambio.id_usuario_ofertada);
			actualizarEnlaces(contenedorFoto2, usuario2, picture2, fotoUsuario2, nombre2, habilidad2, intercambio.id_usuario_demandada);

			if (estadoBD == "PENDIENTE") {
				let mensaje = document.createElement("p");
				mensaje.innerText = "Esperando respuesta";
				botones.appendChild(mensaje);
			}

		} else {
			nombreUsuario2 = intercambio.nombre_usuario_ofertada;
			habilidadUsuario2 = intercambio.nombre_habilidad_demandada;
			fotoUsuario2.src = "ObtenerFoto?idfoto=" + intercambio.ruta_foto_ofertada.split('\\').pop();
			nombreUsuario1 = intercambio.nombre_usuario_demandada;
			habilidadUsuario1 = intercambio.nombre_habilidad_ofertada;
			fotoUsuario1.src = "ObtenerFoto?idfoto=" + intercambio.ruta_foto_demandada.split('\\').pop();

			nombre2.innerHTML = nombreUsuario2;
			habilidad2.innerHTML = habilidadUsuario2;
			nombre1.innerHTML = nombreUsuario1;
			habilidad1.innerHTML = habilidadUsuario1;

			usuarioUno = {

				nombre: nombreUsuario1,
				id: intercambio.id_usuario_demandada,
				ofertada: habilidadUsuario1,
				foto: fotoUsuario1.src
			}

			usuarioDos = {

				nombre: nombreUsuario2,
				id: intercambio.id_usuario_ofertada,
				ofertada: habilidadUsuario2,
				foto: fotoUsuario2.src
			}

			actualizarEnlaces(contenedorFoto1, usuario1, picture1, fotoUsuario1, nombre1, habilidad1, intercambio.id_usuario_demandada);
			actualizarEnlaces(contenedorFoto2, usuario2, picture2, fotoUsuario2, nombre2, habilidad2, intercambio.id_usuario_ofertada);

			if (estadoBD == "PENDIENTE") {
				let botonRechazar = document.createElement("button");
				botonRechazar.setAttribute('data-intercambio-id', intercambio.idIntercambio);
				botonRechazar.setAttribute('data-nuevo-estado', "RECHAZADO");
				botonRechazar.onclick = () => actualizarEstadoIntercambio(intercambio.idIntercambio, "RECHAZADO", nintercambio); botonRechazar.innerText = "Rechazar";
				botonRechazar.classList.add('btn', 'btn-danger');
				botones.appendChild(botonRechazar);



				let botonAceptar = document.createElement("button");
				botonAceptar.innerText = "Aceptar";
				botonAceptar.setAttribute('data-intercambio-id', intercambio.idIntercambio);
				botonAceptar.setAttribute('data-nuevo-estado', "ACEPTADO");
				botonAceptar.onclick = () => actualizarEstadoIntercambio(intercambio.idIntercambio, "ACEPTADO", nintercambio);
				botonAceptar.classList.add('btn', 'btn-success');
				botones.appendChild(botonAceptar);

			}

		}
		intercambioAux = {
			idintercambio: intercambio.idIntercambio,
			usuario1: usuarioUno,
			usuario2: usuarioDos
		}

		listaIntercambios.push(intercambioAux)
		//let nintercambio = listaIntercambios.length-1;
		// Llamar a la función pintarEstado para pintar el estado de la fila
		pintarEstado(intercambio, icono, estado, estadoIntercambio, botones, estadoBD, nintercambio);
		nintercambio = nintercambio + 1;
	});
}

//Identifica estado de la base de datos para pintar el estado correspondiente en la fila
function pintarEstado(intercambio, icono, estado, estadoIntercambio, botones, estadoBD, nintercambio) {
	icono.innerHTML = '';
	estado.innerHTML = '';
	estadoIntercambio.className = 'estado-intercambio';

	if (estadoBD == "PENDIENTE") {
		icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="orange" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16"><path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5m.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/></svg>';
		textoEstado = "Pendiente";
		estado.innerHTML = textoEstado;
		claseEstado = "pendiente";
		estadoIntercambio.classList.add("estado-intercambio", claseEstado);

	} else if (estadoBD == "ACEPTADO") {
		icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/></svg>';
		textoEstado = "Aceptado";
		estado.innerHTML = textoEstado;
		claseEstado = "aceptado";
		estadoIntercambio.classList.add("estado-intercambio", claseEstado);

		let botonMensajes = document.createElement("button");
		botonMensajes.innerText = "Ver mensajes";
		botonMensajes.onclick = () => {
			obtenerMensajes(intercambio.idIntercambio);
			pintarActivo(nintercambio);
		}

		botonMensajes.classList.add('btn', 'btn-warning');

		//TODO añadir clase seleccionada a la fila y el mensaje de chat activo

		botones.appendChild(botonMensajes);
	} else if (estadoBD == "RECHAZADO") {
		icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/></svg>';
		textoEstado = "Rechazado";
		estado.innerHTML = textoEstado;
		claseEstado = "rechazado";
		estadoIntercambio.classList.add("estado-intercambio", claseEstado);
	}



}


//CAMBIO DE ESTADO AL CLICAR BOTON ACEPTAR O RECHAZAR
function actualizarEstadoIntercambio(intercambioId, nuevoEstado, numintercambio) {
	let estadoIntercambio = {
		idIntercambio: intercambioId,
		nuevoEstado: nuevoEstado
	};

	let estadoIntercambioJson = JSON.stringify(estadoIntercambio);

	fetch("GestionarEstados", {
		method: "POST",
		body: estadoIntercambioJson
	})
		.then(respuesta => {
			console.log("Procesando la vuelta ..");
			switch (respuesta.status) {
				case 200:
					let fila = document.getElementById(intercambioId);

					if (fila) {
						estadoBD = nuevoEstado;

						let icono = fila.querySelector('.icono-estado');
						let estado = fila.querySelector('.estado-intercambio p');
						let botones = fila.querySelector('.botones');
						let estadoIntercambio = fila.querySelector('.estado-intercambio');

						botones.innerHTML = '';

						pintarEstado({ idIntercambio: intercambioId }, icono, estado, estadoIntercambio, botones, estadoBD, numintercambio);
					} else {
						console.error("No se encontró la fila del intercambio en el DOM.");
					}
					break;

				case 400:
					console.log("Datos no validados");
					alert("Datos no validados");
					break;

				case 403:
					console.log("No existe ese usuario/password");
					alert("No existe ese usuario/password");
					break;

				case 500:
					console.log("Error en la autenticación");
					alert("Error en la autenticación");
					break;
			}
		})
		.catch(error => {
			console.error("Error al actualizar el estado del intercambio:", error);
		});
}

//actualizarEnlaces (contenedorFoto1, usuario1, picture1, fotoUsuario1, nombre1, habilidad1, intercambio.id_usuario_ofertada);
function actualizarEnlaces(tdfoto, tdnombre, picture, imgu, pnombre, phabilidad, id) {
	tdfoto.dataset.user = id;
	tdnombre.dataset.user = id;
	picture.dataset.user = id;
	imgu.dataset.user = id;
	pnombre.dataset.user = id;
	phabilidad.dataset.user = id;

	tdfoto.addEventListener('click', verPerfil);
	tdnombre.addEventListener('click', verPerfil);
	picture.addEventListener('click', verPerfil);
	imgu.addEventListener('click', verPerfil);
	pnombre.addEventListener('click', verPerfil);
	phabilidad.addEventListener('click', verPerfil);
}

function verPerfil(event) {
	let id = event.target.dataset.user;
	console.log(event.target + " " + id)
	window.location.href = 'verperfil.html?idusuario=' + id;

}

function obtenerMensajes(idIntercambio) {
	let url = "ConsultarMensajes?idintercambio=" + idIntercambio;
	fetch(url)
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Mensajes obtenidos");
					return respuesta.json()
						.then(listaMensajes => mostrarMensajes(listaMensajes));
				case 404:
					console.log("Error en la solicitud");
					break;
				case 500:
					console.log("Error al obtener los mensajes");
					break;
			}
		})
		.catch(error => {
			console.error('Error al obtener los mensajes:', error);
		});
}

function mostrarMensajes(listaMensajes) {
	console.log("INFO = " + listaMensajes);
	let divContenedorMensajes = document.getElementById("contenedor-mensajes");
	divContenedorMensajes.innerHTML = "";

	listaMensajes.forEach(mensaje => {
		let divMensaje = document.createElement("div");



		if (mensaje.emisor == usuarioSesionId) {
			divMensaje.classList.add("message", "user1");
		} else {
			divMensaje.classList.add("message", "user2");
		}

		divMensaje.innerHTML = `${mensaje.fecha_hora}<br>${mensaje.texto}`;

		divContenedorMensajes.appendChild(divMensaje);
	});

	divContenedorMensajes.scrollTop = divContenedorMensajes.scrollHeight;
}


function enviarMensaje() {
	//TODO coger el listaIntercambios [numintercambio]
	
	let nintermcabio = document.getElementById("btnEnviar").dataset.numintercambio ;
	let usuarioid = usuarioDos.id;


	let user2 = usuarioid;
	let idtabla = listaIntercambios[nintermcabio].idintercambio;

	// Validación para asegurarte de que los datos son correctos
	if (!user2 || !idtabla) {
		console.error("Faltan datos necesarios para el mensaje");
	}

	console.log("usario dos", user2);
	console.log("numero de tabla es: ", idtabla);
	// Llamar a prepararDatos con los datos correctos
	prepararDatos(user2, idtabla);
	
}

