let seleccionada = 0;
var idusuario = 0;
let idhabilidaddemandad = 0;

window.onload = () => {
	
//obtenemos id de usuario de la url
	var matches = /idusuario=([^&#=]*)/.exec(window.location.search);
	idusuario = matches[1];
	//window.alert("pagina cargada")
	obtenerPerfilUsuario();
	obtenerHabilidadesOfertadas();
	obtenerHabilidadesDemandadas();
	
	//proponer el intercambio
}
function pintarHabilidadOfertada (habilidad, divpadre)
{
		//crearme el div para le eiqueta
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiqueta";
	divHabilidad.id = habilidad.idHabilidad;
	let hijosDiv = "<label>"+habilidad.nombre+"</label>";
	divHabilidad.innerHTML = hijosDiv.trim();
	
	divpadre.appendChild (divHabilidad);
}

function obtenerHabilidadesOfertadas() {
	//pintarHabilidades(null, document.getElementById("container-hb-ofertadas"));
	//FETCH HABILIDAES OFERTADAS
	fetch("ConsultarHabiidadOfertadaIntercambio?idusuario="+idusuario)//obtengo las habilidades del Servlet Ofertadas
	.then(respuesta=> respuesta.json())//lo paso a Objeto javascritp
	.then (listaHabilidadesOfertadas => {
		console.log("Tamaño lista habilidades ofertadas " + listaHabilidadesOfertadas.length);
		let divPadre = document.getElementById("container-etiquetas-ofertadas");
		listaHabilidadesOfertadas.forEach(habilidad=> {
			pintarHabilidadOfertada (habilidad, divPadre);
		});//foreach
		programarBotonesSeleccion();
	});//then json()
	//FOR - PARA CADA HABLIDAD
	//PINTAR HABILIDADES
}

function pintarHabilidadDemandada (habilidad, divpadre)
{
		//crearme el div para le eiqueta
	let divHabilidad = document.createElement("div");
	divHabilidad.className = "etiquetad";
	divHabilidad.id = habilidad.idHabilidad;
	let hijosDiv = "<label>"+habilidad.nombre+"</label>";
	divHabilidad.innerHTML = hijosDiv.trim();
	
	divpadre.appendChild (divHabilidad);
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
			pintarHabilidadDemandada(habilidad, divPadre);
		});//foreach
	});//then json()
	//FOR - PARA CADA HABLIDAD
	//PINTAR HABILIDADES
	
}

function programarBotonesSeleccion () {
	let etiquetas = Array.from(document.getElementsByClassName("etiqueta")); // Obtener todas las etiquetas
	//etiquetas [0].addEventListener("click", marcarSelecionada); 
	etiquetas.forEach(
		(etiqueta) => {
			etiqueta.addEventListener("click", habilidadTocada);
		}
	)
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

function marcarHabilidad(label, div) {
	div.style.backgroundColor = "#b2babb";
	label.style.color = "white";
	div.dataset.marcado = '1';
	label.dataset.marcado = '1';
	idhabilidaddemandad = div.id; 
}

function desmarcarHabilidad(label, div) {
	div.style.backgroundColor = "#FFB74D";
	label.style.color = "black";
	div.dataset.marcado = '0';
	label.dataset.marcado = '0';
}

function habilidadTocada(evento) {
	let label;
	let div;

	//alert("Etiqueta tocada " + evento.target);
	if (seleccionada == 0) {
		console.log("no hay habilidad seleccionada")
		if (evento.target instanceof HTMLDivElement) {
			div = evento.target;
			label = div.firstElementChild;
			//console.log("Ha tocado el div");

		} else {
			//console.log("Ha tocado el label");
			label = evento.target;
			div = label.parentNode;
		}
		marcarHabilidad(label, div);
		seleccionada = 1;
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
			seleccionada=0;
		}

	}
}


function proponer() {
	//alert("Proponer tocado");
	//Tiene que saber que etiqueta ha elegido el usuario
	// y si el usuario no selecciona nada mostrar aviso 

	
	
	// CrearIntercambioControlador
	if (seleccionada==1) {
		fetch("CrearIntercambioControlador? usuarioDemandadaParam=" + idusuario + "&habilidadDemandadaParam=" + idhabilidaddemandad, {
			method: "POST",
			body: infousuarioJson
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
		alert("Elige una habilidad antes de proponer");
	}
	
	

}

