let valorConsulta = document.getElementsById("input-buscador").value;
let url = "BuscadorHabServlet?consulta" + valorConsulta;

function habilidadesLike() {
		fetch('url', {
		method: 'GET'
	})
		.then(respuesta => {
            switch (respuesta.status) {
                case 200:
                    respuesta.json().then (infoUsuario=>mostrarPerfil(infoUsuario));
                    break;
                case 400:
                    alert("No ha sido posible el registro");
                    break;
                case 500:
                    alert("Error en el registro");
                    break;
            }
        })

		.catch(error => {
			console.error('Error al obtener el perfil de usuario:', error);
		});
 
function mostrarPerfil (infousuario) {
	console.log ("INFO = " + infousuario);
	//getElementByIdSugerencias, Crearul, crearli, 
	document.getElementById ("nombre").innerHTML = infousuario.nombre
	document.getElementById ("fotoPerfil").src = "ObtenerFoto?idfoto="+infousuario.rutaFoto.substring(infousuario.rutaFoto.lastIndexOf('\\') + 1);
	//fotosperfil\foto1728286525650
}

}