// funcion para insertar un habilidad ofertada
function insertarHabilidadOfertada() {
	const boton = document.getElementById("botonInsertar");

	boton.addEventListener("click", function() {
		console.log("haz tocado el boton de insertar");
		const idCategoria = document.getElementById("ofertadas").value;
		const habilidadOfertada = document.getElementById("texto").value;


		if (idCategoria && habilidadOfertada) {
			console.log(`Has insertado : Categoria = ${idCategoria}, Texto = ${habilidadOfertada}`);

			//Crear la URL con parametros de consulta
			const url = `InsertarHabilidadesOfertadas?habilidadOfertada=${encodeURIComponent(habilidadOfertada)}&idCategoria=${encodeURIComponent(idCategoria)}`;

			// Enviar los datos al servidor usando fetch
			fetch(url, {
				method: "POST",
				
			})
				.then(response => {
					if (response.ok) {
						return response.json();
					} else {
						throw new Error("Error en la respuesta del servidor");
					}

				})
				.then(data => {
					console.log("respuesta del servidor: ", data);
					
					let divPadre = document.getElementById("container-hb-ofertadas");
					pintarHabilidadOfertada (data, divPadre);
					
					const selectCategoria = document.getElementById("ofertadas");
                selectCategoria.selectedIndex = 0; 

                document.getElementById("texto").value = "";
					
					
				})
				.catch(error => {
					console.error("Hubo un problema con la solicitud: ", error);
					alert("Error al insertar la hibilidad. Intentelo de nuevo.");
				})

		}

		else {
			console.log("por favor, completa todos los campos.");
			alert("Por favor, completa todos los campos antes de insertar.");
		}


	});

}

document.addEventListener("DOMContentLoaded", insertarHabilidadOfertada);