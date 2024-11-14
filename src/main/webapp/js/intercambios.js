document.addEventListener("DOMContentLoaded", function() {
	obtenerUsuarioSesion();
	obtenerIntercambios();
});

let usuarioSesionId = null;

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

function obtenerIntercambios() {
	fetch('ObtenerTodosLosIntercambiosCursados')
		.then(respuesta => {
			switch (respuesta.status) {
				case 200:
					console.log("Intercambios obtenidos");
					return respuesta.json()
						.then(infoIntercambios => mostrarIntercambios(infoIntercambios));
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
		tabla.appendChild(tr);

		//Crea columna icono estado
		let icono = document.createElement("td");
		icono.classList.add("icono-estado");
		tr.appendChild(icono);

		let claseEstado;



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
		estadoIntercambio.classList.add("estado-intercambio", claseEstado);
		let estado = document.createElement("p");
		estado.innerHTML = intercambio.estado;
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

			if (intercambio.estado == "PENDIENTE") {
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

			if (intercambio.estado == "PENDIENTE") {
				let botonRechazar = document.createElement("button");
				botonRechazar.innerText = "Rechazar";
				botonRechazar.classList.add('btn', 'btn-danger');
				botones.appendChild(botonRechazar);


				let botonAceptar = document.createElement("button");
				botonAceptar.innerText = "Aceptar";
				botonAceptar.classList.add('btn', 'btn-success');
				botones.appendChild(botonAceptar);
			}

		}

	
	//Identifica estado de la base de datos para pintar el estado correspondiente en la fila
		if (intercambio.estado == "PENDIENTE") {
			icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="orange" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16"><path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5m.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/></svg>';
			intercambio.estado = "Pendiente";
			claseEstado = "pendiente";
		} else if (intercambio.estado == "ACEPTADO") {
			icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/></svg>';
			intercambio.estado = "Aceptado";
			claseEstado = "aceptado";

			let botonMensajes = document.createElement("button");
			botonMensajes.innerText = "Ver mensajes";
			botonMensajes.classList.add('btn', 'btn-warning');

			botones.appendChild(botonMensajes);
		} else {
			icono.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16"><path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/></svg>';
			intercambio.estado = "Rechazado";
			claseEstado = "rechazado";
		}
	});
}
