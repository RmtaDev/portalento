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


function obtenerHabilidadesOfertadas() {
	fetch("ConsultarHabiidadOfertadaIntercambio?idusuario="+idusuario)//obtengo las habilidades del Servlet Ofertadas
	.then(respuesta=> respuesta.json())//lo paso a Objeto javascritp
	.then (listaHabilidadesOfertadas => {
		console.log("Tamaño lista habilidades ofertadas " + listaHabilidadesOfertadas.length);
		let divPadre = document.getElementById("container-etiquetas-ofertadas");
		listaHabilidadesOfertadas.forEach(habilidad=> {
			pintarHabilidad (habilidad, divPadre);
		});//foreach
		//programarBotonesSeleccionOfertadas();
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
		//programarBotonesseleccionDdemandadas();
	});//then json()	
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


function volver ()
{
	window.history.back();
}
