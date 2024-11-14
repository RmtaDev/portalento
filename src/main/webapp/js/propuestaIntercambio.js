let seleccionDd = 0;
let seleccionOf = 0;
var idusuario = 0;
let idhabilidadDd = 0;
let idhabilidadOf = 0;

window.onload = () => {
	
	var matches = /idusuario=([^&#=]*)/.exec(window.location.search);
	idusuario = matches[1];
	obtenerPerfilUsuario();
	obtenerHabilidadesOfertadas();
	obtenerHabilidadesDemandadas();

}


function pintarHabilidad (habilidad, divpadre)
{
		//crearme el div para le eiqueta
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	let hijosDiv = "<label>"+habilidad.nombre+"</label>";
	divHabilidad.innerHTML = hijosDiv.trim();
	divpadre.appendChild (divHabilidad);
}

function programarBotonesSeleccionOfertadas () {
	let etiquetas = Array.from (document.getElementById("container-etiquetas-ofertadas").children)
	etiquetas.forEach(
		(etiqueta) => {
			etiqueta.addEventListener("click", habilidadOfertadaTocada);
		}
	)
}

function programarBotonesseleccionDdemandadas () {
	let etiquetas = Array.from (document.getElementById("container-etiquetas-demandadas").children)
	etiquetas.forEach(
		(etiqueta) => {
			etiqueta.addEventListener("click", habilidadDemandadaTocada);
		}
	)
}

function obtenerHabilidadesOfertadas() {
	fetch("ConsultarHabiidadOfertadaIntercambio?idusuario="+idusuario)//obtengo las habilidades del Servlet Ofertadas
	.then(respuesta=> respuesta.json())//lo paso a Objeto javascritp
	.then (listaHabilidadesOfertadas => {
		console.log("Tamaño lista habilidades ofertadas " + listaHabilidadesOfertadas.length);
		let divPadre = document.getElementById("container-etiquetas-ofertadas");
		listaHabilidadesOfertadas.forEach(habilidad=> {
			pintarHabilidad (habilidad, divPadre);
		});//foreach
		programarBotonesSeleccionOfertadas();
	});//then json()
}

function obtenerHabilidadesDemandadas()
{
	//FETCH HABILIDAES DEMANDADAS
	fetch("ConsultarHabiidadDemandadaIntercambio?idusuario="+idusuario)//obtengo las habilidades del Servlet Ofertadas
	.then(respuesta=> respuesta.json())//lo paso a Objeto javascritp
	.then (listaHabilidadesDemandadas => {
		console.log("Tamaño lista habilidades demandadas " + listaHabilidadesDemandadas.length);
		let divPadre = document.getElementById("container-etiquetas-demandadas");
		listaHabilidadesDemandadas.forEach(habilidad=> {
			pintarHabilidad(habilidad, divPadre);
		});//foreach
		programarBotonesseleccionDdemandadas();
	});//then json()	
}



function marcarHabilidad(label, div, ofertada) {
	div.style.backgroundColor = "#198754";
	label.style.color = "white";
	div.dataset.marcado = '1';
	label.dataset.marcado = '1';
	if (ofertada==1)
	{
		idhabilidadOf = div.id;
	}
	else {
		idhabilidadDd = div.id; 
	}
	
}

function desmarcarHabilidad(label, div) {
	div.style.backgroundColor = "#ffc107";
	label.style.color = "black";
	div.dataset.marcado = '0';
	label.dataset.marcado = '0';
}

function habilidadDemandadaTocada(evento) {
	let label;
	let div;


	if (seleccionDd == 0) {
		console.log("no hay habilidad demandad seleccionada")
		if (evento.target instanceof HTMLDivElement) {
			div = evento.target;
			label = div.firstElementChild;
			//console.log("Ha tocado el div");

		} else {
			//console.log("Ha tocado el label");
			label = evento.target;
			div = label.parentNode;
		}
		marcarHabilidad(label, div, 0);
		seleccionDd = 1;
	} else {
		console.log("Ya hay una seleccionada");
		if (evento.target.dataset.marcado == '1') {
			//hay que desmarcarlo
			if (evento.target instanceof HTMLDivElement) {
				div = evento.target;
				label = div.firstElementChild;
				console.log("Ha tocado el div");

			} else {
				console.log("Ha tocado el label");
				label = evento.target;
				div = label.parentNode;
			}
			desmarcarHabilidad(label, div);
			seleccionDd=0;
		}

	}
}


function habilidadOfertadaTocada(evento) {
	let label;
	let div;


	if (seleccionOf == 0) {
		console.log("no hay habilidad demandad seleccionada")
		if (evento.target instanceof HTMLDivElement) {
			div = evento.target;
			label = div.firstElementChild;
			//console.log("Ha tocado el div");

		} else {
			//console.log("Ha tocado el label");
			label = evento.target;
			div = label.parentNode;
		}
		marcarHabilidad(label, div, 1);
		seleccionOf = 1;
	} else {
		console.log("Ya hay una seleccionada");
		if (evento.target.dataset.marcado == '1') {
			//hay que desmarcarlo
			if (evento.target instanceof HTMLDivElement) {
				div = evento.target;
				label = div.firstElementChild;
				console.log("Ha tocado el div");

			} else {
				console.log("Ha tocado el label");
				label = evento.target;
				div = label.parentNode;
			}
			desmarcarHabilidad(label, div);
			seleccionOf=0;
		}

	}
}


function obtenerPerfilUsuario() {

	
	fetch('ObtenerPerfilUsuarioIntercambio?idusuario='+idusuario, {
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
	//document.getElementById("telefono").innerHTML = infousuario.telefono;
	//document.getElementById("email").innerHTML = infousuario.email;
	document.getElementById('hablasobreti').innerHTML = infousuario.hablaSobreTi;
	document.getElementById("foto").src = "ObtenerFoto?idfoto=" + infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
}

function proponer() {

	if (seleccionDd ==1 && seleccionOf == 1) {
		fetch("CrearIntercambioControlador?usuarioDemandada=" + idusuario + "&habilidadDemandada=" + idhabilidadDd+"&habilidadOfertada="+idhabilidadOf, {
			method: "POST"
		})//12 ACTUALIZO LA INTERFAZ DE USUARIO
			.then(respuesta => {
				console.log("Procesando la vuelta ..");
				switch (respuesta.status) {
					case 201:
						window.location.href = "intercambios.html";
						break;
					case 400:
						console.log("Datos no validados");
						alert("Datos no validados");
						break;
					case 401:
						console.log("No tiene sesion");
						alert("No tiene sesion");
						window.location.href = "inicio.html";
						break;
					case 500:
						console.log("Error en el proceso");
						alert("Error en el proceso");
						break;
				}
			});
	} else {
		alert("Elige una habilidad ofertada y demandada");
	}
	
	

}

function volver ()
{
	window.history.back();
}
